package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Position;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;

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

	/**
	 * Execute l'action PlacerChef
	 * On place le chef dans la case du tableau
	 * On supprimer le chef du deck du joueur
	 * @return vrai ou faux, selon le bon déroulement ou non de l'action
	 */
	public boolean executer(){
		boolean place = this.partie.getPlateauJeu().placerChef(this.chef, this.position);
		//this.joueur.getDeckVisible().deleteChef(this.chef);
		return place;
	}
}