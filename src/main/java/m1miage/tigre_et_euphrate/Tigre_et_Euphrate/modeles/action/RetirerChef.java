package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Position;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;

/**
 * 
 * Classe hérite de la classe Action et permet de retirer un chef
 *
 */
public class RetirerChef extends Action {

	/**
	 * Chef 
	 */
	private Chef chef;
	
	/**
	 * Position du chef
	 */
	private Position position;
	
	/**
	 * @param ppartie
	 * @param pjoueur
	 * @param pchef
	 * @param ppos
	 */
	public RetirerChef(Partie ppartie, Joueur pjoueur, Chef pchef, Position ppos) {
		super(ppartie, pjoueur);
		this.chef = pchef;
		this.position = ppos;
	}

	/**
	 * Execute l'action RetirerChef
	 * On met à null la case du tableau du chef
	 * On ajoute le chef dans le deck du joueur
	 * On peut retirer le chef à tout moment sans aucune contrainte ??
	 * @return vrai ou faux, selon le bon déroulement ou non de l'action
	 */
	public boolean executer(){
		this.partie.getPlateauJeu().getPlateau()[this.position.getX()][this.position.getY()] = null;
		this.joueur.getDeckVisible().AddChefs(this.chef);
		return true;
	}
}