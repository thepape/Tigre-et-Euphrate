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

	private int indice;

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
	public RetirerChef(Partie ppartie, Joueur pjoueur, Chef pchef, int indice, Position position) {
		super(ppartie, pjoueur);
		this.chef = pchef;
		this.indice = indice;
		this.position = position;
	}

	/**
	 * Execute l'action RetirerChef
	 * On met à null la case du tableau du chef
	 * On ajoute le chef dans le deck du joueur
	 * @return vrai ou faux, selon le bon déroulement ou non de l'action
	 */
	public boolean executer(){
		boolean ok = false;
		// VERIFIER SI IL FAIT PARTIE D'UN ROYAUME OU D'UN TERRITOIRE
		/*if(this.chef.getDynastie() != this.joueur.getDynastie())
			return false;
		this.partie.getPlateauJeu().getPlateau()[this.position.getX()][this.position.getY()] = null;
<<<<<<< HEAD
		this.joueur.getDeckPublic().ajouter(this.chef);
		return true;*/
		this.partie.getPlateauJeu().getPlateau()[this.position.getX()][this.position.getY()] = null;
		this.joueur.getDeckPublic().getDeckPublic().add(indice, chef);

		return ok;
	}
}