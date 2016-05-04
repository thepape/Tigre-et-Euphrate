package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action;

import java.util.ArrayList;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.connexion.Client;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.vue.MainApp;

/**
 * 
 * Classe hérite de la classe Action et permet d echanger une tuile civilisation
 *
 */
public class EchangerTuileCivilisation extends Action {

	/**
	 * Tuile Civilisation a echanger
	 */
	private ArrayList<TuileCivilisation> tuileAEchanger;
	
	/**
	 * @param partie
	 * @param joueur
	 */
	public EchangerTuileCivilisation(Partie ppartie, Joueur pjoueur, ArrayList<TuileCivilisation> ptuile) {
		super(ppartie, pjoueur);
		this.tuileAEchanger = ptuile;
	}

	/**
	 * Execute l'action EchangerTuileCivilisation
	 * Puis attribuer éventuellement un point de victoire (voir page 4)
	 * @return vrai ou faux, selon le bon déroulement ou non de l'action
	 */
	public boolean executer(){
		if(!this.verifier())
			return false;
		
		
		for(TuileCivilisation tuile : this.tuileAEchanger){
			
			this.joueur.getDeckPrive().getDeckPrive().remove(tuile);
			System.out.println("Suppression dune carte");
		}
		this.partie.piocheCartesManquantes(this.joueur);
		return true;
	}
	
	/**
	 * Methode permettant de verifier si la pioche est vide 
	 */
	public boolean verifier(){
		if(this.partie.getPioche().estVide())
			return false;
		
		return true;
	}
	
	/**
	 * Methode toString d'un echange de tuile civilisation
	 */
	public String toString(){
		return "Le joueur "+this.joueur.getNom()+" a echangé ses tuiles.";
	}
}