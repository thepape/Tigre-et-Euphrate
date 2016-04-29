package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action;

import java.util.ArrayList;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Position;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Territoire;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.TypeChef;
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

			for(int i = 0; i < listeAdjacente.size()-1; i++)
			{
				for(int j = 1; j < listeAdjacente.size(); j++)
				{
					/*if(!listeAdjacente.get(i).getTerritoire().equals(listeAdjacente.get(j).getTerritoire()))
					{
						//TODO conflits
						conflit = true;
					}*/

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
						}
					}
				}
			}

			if(!conflit)
			{
				this.partie.getPlateauJeu().recupererTerritoireTuile(listeAdjacente.get(0)).addTuile(tuile);
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
		
		// on retire cette tuile du deck privé du joueur
		this.joueur.getDeckPrive().getDeckPrive().remove(this.tuile);
		
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
	
	/**
	 * verifie si quand on pose une tuile civilisation on peut recuperer des tresors
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
				for(int i = 0; i<nbtuiletresor-1;i++){
					tuileTresor.get(0).recupererTresor();
					tuileTresor.remove(0);
					cmarchand.getJoueur().ajouterPointsTresor(1);
					System.out.println("Le joueur "+cmarchand.getJoueur().getNom()+" a recu un point tresor");
				}
			}
		}
	}
}