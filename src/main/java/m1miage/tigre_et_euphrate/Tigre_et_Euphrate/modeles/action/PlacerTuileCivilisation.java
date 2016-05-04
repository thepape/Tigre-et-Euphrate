package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action;

import java.rmi.RemoteException;
import java.util.ArrayList;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Position;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Territoire;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.TypeChef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.connexion.InterfaceServeurClient;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.Tuile;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TypeTuileCivilisation;

/**
 *
 * Classe hérite de la classe Action et permet de placer une tuile civilisation
 *
 */
public class PlacerTuileCivilisation extends Action {

	private Position position;

	private TuileCivilisation tuile;

	/**
	 * boolean pour tester les conflits
	 */
	private boolean conflit;

	/**
	 * getter de la position
	 * @return position
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * setter de la position
	 * @param position
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * getter de la tuile
	 * @return tuile
	 */
	public Tuile getTuile() {
		return tuile;
	}

	/**
	 * setter de la tuile
	 * @param tuile
	 */
	public void setTuile(TuileCivilisation tuile) {
		this.tuile = tuile;
	}

	/**
	 * @param partie
	 * @param joueur
	 */
	public PlacerTuileCivilisation(Partie partie, Joueur joueur, Position position, TuileCivilisation tuile) {
		super(partie, joueur);
		this.position = position;
		this.tuile = tuile;
	}
	

	/**
	 * Execute l'action PlacerTuileCivilisation
	 * @return vrai ou faux, selon le bon déroulement ou non de l'action
	 * @throws RemoteException 
	 */
	public boolean executer(){
		boolean ok = false;
		
		if(!this.verifier()){
			return false;
		}
		
		

		ArrayList<TuileCivilisation> listeAdjacente = this.partie.getPlateauJeu().recupererListeTuileCivilisationAdjacente(position);
		conflit = false;
		if(listeAdjacente.size() > 0)
		{
			//on verifie d'abord si on reunit + de 2 royaumes
			int nbRoyaumesDifferents = 0;
			for(int i = 0; i < listeAdjacente.size()-1; i++){
				for(int j = i+1; j< listeAdjacente.size(); j++){
					Territoire t1 = this.partie.getPlateauJeu().recupererTerritoireTuile(listeAdjacente.get(i));
					Territoire t2 = this.partie.getPlateauJeu().recupererTerritoireTuile(listeAdjacente.get(j));
					if(!t1.equals(t2) && t1.isEstRoyaume() && t2.isEstRoyaume()){
						nbRoyaumesDifferents++;
					}
				}
			}
			if(nbRoyaumesDifferents > 2){
				return false;
			}

			for(int i = 0; i < listeAdjacente.size()-1; i++)
			{
				for(int j = i+1; j < listeAdjacente.size(); j++)
				{
					

					if(!this.partie.getPlateauJeu().recupererTerritoireTuile(listeAdjacente.get(i)).equals(this.partie.getPlateauJeu().recupererTerritoireTuile(listeAdjacente.get(j))))
					{
						//verifier que les 2 territoires sont royaumes et contiennent un chef de la meme couleur
						Territoire t1 = this.partie.getPlateauJeu().recupererTerritoireTuile(listeAdjacente.get(i));
						Territoire t2 = this.partie.getPlateauJeu().recupererTerritoireTuile(listeAdjacente.get(j));
						
						for(Chef chef1 : t1.getChefs()){
							for(Chef chef2 : t2.getChefs()){
								if(chef1.getTypeChef().equals(chef2.getTypeChef())){
									conflit = true;
									System.out.println("conflit externe !");
									this.tuile.setJonction(true);
									
									//il faut créer le conflit et l'ajouter a la liste de conflits de la partie
								}
							}
						}
						
						//si pas de conflit externe detecté, on réunit les 2 territoires
						if(!conflit){
							//on reunit les 2 territoires
							t1.addListeChefs(t2.getChefs());
							t1.addListeTuiles(t2.getTuilesCivilisation());
							this.partie.getPlateauJeu().getListeRoyaume().remove(t2);
							this.tuile.setJonction(true);
						}
					}
				}
			}

			if(!conflit)
			{
				Territoire terr = this.partie.getPlateauJeu().recupererTerritoireTuile(listeAdjacente.get(0));
				terr.addTuile(tuile);
				//si pas de conflit et que la tuile posée permet de recuperer des points tresors
				this.verifierTresors(this.partie.getPlateauJeu().recupererTerritoireTuile(listeAdjacente.get(0)));
			}
		}  else {
			//tuile.setTerritoire(new Territoire());
			Territoire territoire = new Territoire();
			territoire.addTuile(tuile);
			this.partie.getPlateauJeu().getListeRoyaume().add(territoire);
		}


		ok = this.partie.getPlateauJeu().placerTuile(this.tuile, this.position);
		//System.out.println("placement : "+this.tuile.getId()+"-"+this.position.getX()+","+this.position.getY());
		
		// on retire cette tuile du deck privé du joueur
		
		this.joueur.getDeckPrive().getDeckPrive().remove(this.tuile);
		//System.out.println("DECK:"+this.joueur.getNom()+" - "+this.joueur.getDeckPrive());
		
		this.AttributionPointVictoire();
		return ok;
	}

	public boolean isConflit() {
		return conflit;
	}

	public void setConflit(boolean conflit) {
		this.conflit = conflit;
	}

	public String toString(){
		return this.joueur.getNom()+" a placé une tuile "+this.tuile.getType().getNom()+" à la ligne "+this.position.getX()+", colonne "+this.position.getY();
	}

	@Override
	public boolean verifier() {
		// TODO Auto-generated method stub
		return this.partie.getPlateauJeu().verifierPlacerTuile(tuile, position);
	}
	
	/*
	 *  Dans ce royaume se trouve un chef de la même couleur que la nouvelle tuile Civilisation. C’est le
joueur à qui ce chef appartient qui remporte le point de victoire.
— Dans ce Royaume, aucun chef n’est de la couleur de la nouvelle tuile, mais il y a un Roi (chef noir).
Dans ce cas, c’est le joueur à qui appartient le Roi qui remporte le point de victoire.
	 */
	
	/**
	 * Methode qui retourne le nombre de point victoire gagner lorsqu'un joureur
	 * place une tuile civilisation.
	 * @return
	 */
	public void AttributionPointVictoire(){
		Territoire t = this.partie.getPlateauJeu().recupererTerritoireTuile(this.tuile);
		if(t.isEstRoyaume() && !this.tuile.estJonction()){
			String couleurTuile = this.tuile.getType().getCouleur();
			ArrayList<Chef> chefs = t.getChefs();
			for (Chef pchef : chefs){
				if(pchef.getTypeChef().getCouleur().equals(couleurTuile)){
					//ROMAIN JOUEUR !!!!
					pchef.getJoueur().ajouterPointsVictoire(couleurTuile, 1);
				}
				else{
					if(pchef.getTypeChef().getCouleur().equals("jaune")){
						pchef.getJoueur().ajouterPointsVictoire(couleurTuile, 1);
					}
				}
			}
			
		}
		
	}
	
	/**
	 * verifie si quand on pose une tuile civilisation on peut recuperer des tresors
	 * @throws RemoteException 
	 */
	public void verifierTresors(Territoire pterri){
		int nbtuiletresor = 0;
		boolean marchand = false;
		Chef cmarchand = null;
		
		ArrayList<TuileCivilisation> tuileTresor = new ArrayList<TuileCivilisation>();

		ArrayList<TuileCivilisation> listeTuile = pterri.getTuilesCivilisation();
		ArrayList<Chef> listeChef = pterri.getChefs();
		
		for(Chef chef : listeChef){
			if(chef.getTypeChef().equals(TypeChef.Marchand)){
				cmarchand = chef;
				marchand = true;
			}
		}
		
		if(marchand){
			for(TuileCivilisation tuile : listeTuile){
				if(tuile.getType().equals(TypeTuileCivilisation.Temple)){
					if(tuile.aTresor()){
						nbtuiletresor++;
						tuileTresor.add(tuile);
					}
				}
			}
			
			if(nbtuiletresor >1){
				Joueur possesseur = cmarchand.getJoueur();
				for(int i = 0; i<nbtuiletresor-1;i++){
					tuileTresor.get(0).recupererTresor();
					tuileTresor.remove(0);
					
					
					if(possesseur.getId() == this.joueur.getId()){
						this.joueur.ajouterPointsTresor(1);
					}else{
						possesseur.ajouterPointsTresor(1);
					}
					
					
					/*
					//on recupere le bon joueur auquel ajouter les points
					for(InterfaceServeurClient client : this.partie.getServeur().getClients()){
						int idJoueur = cmarchand.getJoueur().getId();
						Joueur joueurAChanger = client.getJoueur();
						
						if(idJoueur == joueurAChanger.getId()){
							joueurAChanger.ajouterPointsTresor(nbtuiletresor-1);
							this.ajouterJoueurImpacte(joueurAChanger);
						}
					}*/
					
					
					//System.out.println("Le joueur "+cmarchand.getJoueur().getNom()+" a recu un point tresor");
				}
				//on ajoute le joueur impacté dans laliste des impactés que si ce joueur n'est pasle joueur qui a send l'action
				if(this.joueur.getId() != possesseur.getId()){
					this.ajouterJoueurImpacte(cmarchand.getJoueur());
				}
				
			}
		}
	}
}