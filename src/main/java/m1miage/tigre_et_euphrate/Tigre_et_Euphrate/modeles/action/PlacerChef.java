package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action;

import java.util.ArrayList;
import java.util.ListIterator;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Placable;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Plateau;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Position;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Territoire;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.TypeChef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.conflit.Conflits;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TypeTuileCivilisation;

/**
 *
 * Classe hérite de la classe Action et permet de placer un chef
 *
 */
public class PlacerChef extends Action {

	/**
	 * Chef
	 */
	private Chef chef;

	/**
	 * Position souhaitee
	 */
	private Position position;

	/**
	 * Booleen qui permet de tester les conflit
	 */
	private boolean conflit = false;

	/**
	 * Instance du conflit en cours causé par le placerChef
	 */
	private Conflits instanceConflit = null;

	/**
	 * Methode permettant de savoir si on est en conflit ou non
	 * @return
	 */
	public boolean isConflit() {
		return conflit;
	}

	public void setConflit(boolean conflit) {
		this.conflit = conflit;
	}

	/**
	 * @param ppartie
	 * @param pjoueur
	 * @param pchef
	 * @param ppos
	 */
	public PlacerChef(Partie ppartie, Joueur pjoueur, Chef pchef, Position ppos) {
		super(ppartie, pjoueur);
		this.chef = pchef;
		this.position = ppos;
	}

	/**
	 * Methode permettant de retirer un chef apres 
	 */
	private void retirerChef(){
		for(int x = 0; x < 11; x++){
			for(int y = 0; y < 16;y++){
				Placable placable = this.partie.getPlateauJeu().getPlateau()[x][y];

				if(placable != null && placable instanceof Chef){
					Chef chefc = (Chef) placable;
					boolean memeDynastie = chefc.getDynastie().getNom().equals(this.chef.getDynastie().getNom());
					boolean memeCouleur = chefc.getTypeChef().getCouleur().equals(this.chef.getTypeChef().getCouleur());

					if(memeDynastie && memeCouleur){

						this.partie.getPlateauJeu().getPlateau()[x][y] = null;
						Territoire t = this.partie.getPlateauJeu().recupererTerritoireTuile(this.chef);
						t.deletChef(chef);
						return;
					}
				}
			}
		}
	}

	/**
	 * Methode permettant de verifier si le placerChef a bien été effectué
	 */
	public boolean verifier(){
		return this.partie.getPlateauJeu().verifierPlacerChef(chef, position);
	}

	/**
	 * Execute l'action PlacerChef
	 * On place le chef dans la case du tableau
	 * @return vrai ou faux, selon le bon déroulement ou non de l'action
	 */
	public boolean executer(){
		boolean ok = false;

		ok =  this.partie.getPlateauJeu().verifierPlacerChef(this.chef, this.position);
		ArrayList<TuileCivilisation> listeAdjacente = this.partie.getPlateauJeu().recupererListeTuileCivilisationAdjacente(position);

		if(ok) {
			for(int i = 0; i < listeAdjacente.size() - 1; i++)
			{
				for(int j = i+1 ; j < listeAdjacente.size(); j++)
				{
					/*if(!listeAdjacente.get(i).getTerritoire().equals(listeAdjacente.get(j).getTerritoire()))
					{
						ok = false;
					}*/
					if(!this.partie.getPlateauJeu().recupererTerritoireTuile(listeAdjacente.get(i)).equals(this.partie.getPlateauJeu().recupererTerritoireTuile(listeAdjacente.get(j))))
					{
						ok = false;
					}
				}
			}

			if(ok)
			{
				this.retirerChef();
				//this.chef.setTerritoire(this.partie.getPlateauJeu().recupererTerritoireTuile(listeAdjacente.get(0)));
				//listeAdjacente.get(0).getTerritoire().addChefs(this.chef);
				Territoire territoire = this.partie.getPlateauJeu().recupererTerritoireTuile(listeAdjacente.get(0));
				territoire.addChefs(this.chef);

				System.out.println(this.chef.getClass());
				this.partie.getPlateauJeu().getPlateau()[this.position.getX()][this.position.getY()] = this.chef;
				this.chef.setPosition(new Position(this.position.getX(), this.position.getY()));

				//Verifie si condition de recuperation de points tresors
				this.verifierTresors();

				this.joueur.getDeckPublic().getDeckPublic().remove(this.chef);


				for(int i = 0; i < this.partie.getPlateauJeu().recupererTerritoireTuile(this.chef).getChefs().size();i++){
					Chef autreChef = this.partie.getPlateauJeu().recupererTerritoireTuile(this.chef).getChefs().get(i);

					if(chef.getTypeChef().equals(autreChef.getTypeChef()) && chef.getId() != autreChef.getId())
					{
						//TODO conflit
						conflit = true;
						Chef attaquant = this.chef;
						Chef defenseur = autreChef;

						Conflits conflit = new Conflits(attaquant, defenseur, this.partie.getPlateauJeu().recupererTerritoireTuile(defenseur), null);
						conflit.setTypeConflit("I");
						this.partie.ajouterConflit(conflit);
						this.partie.ajouterTourConflit(attaquant.getJoueur());
						this.partie.ajouterTourConflit(defenseur.getJoueur());
						this.instanceConflit = conflit;
					}
				}
			}
		}


		return ok;
	}


	public Conflits getConflit(){
		return this.instanceConflit;
	}

	/**
	 * verifie si quand on pose le chef marchand le territoire possède 2 trésors ou plus
	 * @return
	 */
	public void verifierTresors(){
		//supprimer tresors + attribuer tresor
		if(this.chef.getTypeChef().equals(TypeChef.Marchand)){
			int nbtuiletresor = 0;
			ArrayList<TuileCivilisation> tuileTresor = new ArrayList<TuileCivilisation>();

			Territoire terri = this.partie.getPlateauJeu().recupererTerritoireTuile(this.chef);
			ArrayList<TuileCivilisation> listeTuile = terri.getTuilesCivilisation();

			for(TuileCivilisation tuile : listeTuile){
				if(tuile.getType().equals(TypeTuileCivilisation.Temple)){
					if(tuile.aTresor()){
						nbtuiletresor++;
						tuileTresor.add(tuile);
					}
				}
			}

			if(nbtuiletresor >1){
				Joueur possesseur = chef.getJoueur();
				for(int i = 0; i<nbtuiletresor-1;i++){
					tuileTresor.get(0).recupererTresor();
					tuileTresor.remove(0);
					
					if(possesseur.getId() == this.joueur.getId()){
						this.joueur.ajouterPointsTresor(1);
					}else{
						possesseur.ajouterPointsTresor(1);
					}
					//this.ajouterJoueurImpacte(this.chef.getJoueur());
					//System.out.println("Le joueur "+this.chef.getJoueur().getNom()+" a recu un point tresor");
				}
				
				//on ajoute le joueur impacté dans laliste des impactés que si ce joueur n'est pasle joueur qui a send l'action
				if(this.joueur.getId() != possesseur.getId()){
					this.ajouterJoueurImpacte(possesseur);
				}
			}
		}
	}

	public String toString(){
		return this.joueur.getNom()+" a placé son chef "+this.chef.getTypeChef().getNom()+" à la ligne "+this.position.getX()+", colonne "+this.position.getY();
	}

}