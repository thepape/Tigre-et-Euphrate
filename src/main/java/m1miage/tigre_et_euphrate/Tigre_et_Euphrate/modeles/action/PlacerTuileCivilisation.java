package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;

/**
 * 
 * Classe hérite de la classe Action et permet de placer une tuile civilisation
 *
 */
public class PlacerTuileCivilisation extends Action {

	/**
	 * @param partie
	 * @param joueur
	 */
	public PlacerTuileCivilisation(Partie partie, Joueur joueur) {
		super(partie, joueur);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Execute l'action PlacerTuileCivilisation
	 * Puis attribuer éventuellement un point de victoire (voir page 4)
	 * @return vrai ou faux, selon le bon déroulement ou non de l'action
	 */
	public boolean executer(){
		return false;
	}
}