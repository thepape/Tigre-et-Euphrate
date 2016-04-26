package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action;

import java.util.ArrayList;
import java.util.ListIterator;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Placable;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Plateau;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Position;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
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
	
	private void retirerChef(){
		for(int x = 0; x < 16; x++){
			for(int y = 0; y < 11;y++){
				Placable placable = this.partie.getPlateauJeu().getPlateau()[y][x];
				
				if(placable != null && placable instanceof Chef){
					Chef chefc = (Chef) placable;
					boolean memeDynastie = chefc.getDynastie().getNom().equals(this.chef.getDynastie().getNom());
					boolean memeCouleur = chefc.getTypeChef().getCouleur().equals(this.chef.getTypeChef().getCouleur());
					
					if(memeDynastie && memeCouleur){
						this.partie.getPlateauJeu().getPlateau()[y][x] = null;
						return;
					}
				}
			}
		}
	}

	/**
	 * Execute l'action PlacerChef
	 * On place le chef dans la case du tableau
	 * @return vrai ou faux, selon le bon déroulement ou non de l'action
	 */
	public boolean executer(){
		boolean ok = false;

		ok =  this.partie.getPlateauJeu().placerChef(this.chef, this.position);

		if(ok){
<<<<<<< HEAD
=======
			System.out.println("Temple trouvé");
			this.retirerChef();
			
>>>>>>> master
			this.partie.getPlateauJeu().getPlateau()[this.position.getX()][this.position.getY()] = this.chef;
			
			this.joueur.getDeckPublic().getDeckPublic().remove(this.chef);
		}

		return ok;


		/*if((this.position.getX() > 11 || this.position.getY() > 16) || (this.position.getY() < 0 || this.position.getX() < 0))
		{
			return false;
		}

		if(this.partie.getPlateauJeu().getPlateau()[this.position.getX()][this.position.getY()] != null)
		{
			ok = false;
		} else {

			ArrayList<TuileCivilisation> listeAdjacente = this.getPartie().getPlateauJeu().recupererListeTuileCivilisationAdjacente(this.position);
			for(int i = 0; i < listeAdjacente.size(); i++)
			{
				if(listeAdjacente.get(i).getType().equals(TypeTuileCivilisation.Temple))
				{
					ok = true;
				}
			}
		}

		if(ok)
		{
			this.partie.getPlateauJeu().getPlateau()[this.position.getX()][this.position.getY()] = this.chef;
			this.joueur.getDeckPublic().getDeckPublic().remove(this.chef);
		}
		return ok;*/
	}

}