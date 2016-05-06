package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

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
	 * Liste de joueur impactés par l'action
	 */
	protected ArrayList<Joueur> joueursImpactes = new ArrayList<Joueur>();

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
	 * @throws RemoteException 
	 */
	public abstract boolean executer();
	
	/**
	 * verifie toutes les conditions pour executer l'action
	 * @return
	 */
	public abstract boolean verifier();
	
	/**
	 * Methode permettant d'ajouter un joueur impacte par une action
	 * @param j
	 */
	public void ajouterJoueurImpacte(Joueur j){
		if(!this.joueursImpactes.contains(j)){
			this.joueursImpactes.add(j);
		}
	}
	
	/**
	 * Methode get pour recuperer les joueurs impactés par une action
	 * @return
	 */
	public ArrayList<Joueur> getJoueurImpactes(){
		return this.joueursImpactes;
	}
	
	/**
	 * Methode permettant de supprimer un joueur impacté par une action
	 * @param j
	 */
	public void retirerJoueurImpacte(Joueur j){
		if(this.joueursImpactes.contains(j)){
			this.joueursImpactes.remove(j);
		}
	}

}