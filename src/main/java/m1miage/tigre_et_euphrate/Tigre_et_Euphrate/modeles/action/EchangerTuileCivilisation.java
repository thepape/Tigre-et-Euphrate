package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;

/**
 * 
 * Classe hérite de la classe Action et permet d echanger une tuile civilisation
 *
 */
public class EchangerTuileCivilisation extends Action {

	/**
	 * Tuile Civilisation a echanger
	 */
	private TuileCivilisation tuileAEchanger;
	
	/**
	 * @param partie
	 * @param joueur
	 */
	public EchangerTuileCivilisation(Partie ppartie, Joueur pjoueur, TuileCivilisation ptuile) {
		super(ppartie, pjoueur);
		this.tuileAEchanger = ptuile;
	}

	/**
	 * Execute l'action EchangerTuileCivilisation
	 * Puis attribuer éventuellement un point de victoire (voir page 4)
	 * @return vrai ou faux, selon le bon déroulement ou non de l'action
	 */
	public boolean executer(){
		if(this.partie.getPioche().estVide())
			return false;
		TuileCivilisation tuilePiochee = this.partie.getPioche().piocherTuile();
		this.joueur.getDeckPrive().supprimer(this.tuileAEchanger);
		this.joueur.getDeckPrive().ajouter(tuilePiochee);
		this.tuileAEchanger.retirer();
		return true;
	}
}