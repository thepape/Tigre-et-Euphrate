package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action;

import java.io.Serializable;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;

/**
 *
 * Classe Action qui permet d effectuer des actions
 *
 */
public abstract class Action implements Serializable {


	/**
	 * Partie sur laquelle l action est effectuee
	 */
	protected Partie partie;

	/**
	 * Joueur effectuant l action
	 */
	protected Joueur joueur;

	/**
	 * Constructeur d une Action
	 * @param partie
	 * @param joueur
	 */
	public Action(Partie partie, Joueur joueur) {
		super();
		this.partie = partie;
		this.joueur = joueur;
	}

	public Partie getPartie() {
		return partie;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	/**
	 * Execute l'action
	 * @return vrai ou faux, selon le bon déroulement ou non de l'action
	 */
	public abstract boolean executer();

}