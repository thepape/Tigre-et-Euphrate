package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Position;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.Tuile;

/**
 *
 * Classe hérite de la classe Action et permet de placer une tuile civilisation
 *
 */
public class PlacerTuileCivilisation extends Action {

	private Position position;

	private Tuile tuile;

	/**
	 * getter de la position
	 * @return position
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * setter de la position
	 * @param position
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * getter de la tuile
	 * @return tuile
	 */
	public Tuile getTuile() {
		return tuile;
	}

	/**
	 * setter de la tuile
	 * @param tuile
	 */
	public void setTuile(Tuile tuile) {
		this.tuile = tuile;
	}

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
		System.out.println("executer Action PlacerTuile");
		return false;
	}
}