package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Position;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.Tuile;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TypeTuileCivilisation;

/**
 *
 * Classe hérite de la classe Action et permet de placer une tuile civilisation
 *
 */
public class PlacerTuileCivilisation extends Action {

	private Position position;

	private TuileCivilisation tuile;

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
	public void setTuile(TuileCivilisation tuile) {
		this.tuile = tuile;
	}

	/**
	 * @param partie
	 * @param joueur
	 */
	public PlacerTuileCivilisation(Partie partie, Joueur joueur, Position position, TuileCivilisation tuile) {
		super(partie, joueur);
		this.position = position;
		this.tuile = tuile;
	}

	/**
	 * Execute l'action PlacerTuileCivilisation
	 * @return vrai ou faux, selon le bon déroulement ou non de l'action
	 */
	public boolean executer(){
		boolean ok = false;

		if((this.position.getX() > 11 || this.position.getY() > 16) || (this.position.getY() < 0 || this.position.getX() < 0))
		{
			return false;
		}

		if(this.tuile.getType().equals(TypeTuileCivilisation.Ferme))
		{
			if(this.partie.getPlateauJeu().getPlateauTerrain()[this.position.getX()][this.position.getY()] == false
					&& this.partie.getPlateauJeu().getPlateau()[this.getPosition().getX()][this.position.getY()] == null)
			{
				this.partie.getPlateauJeu().getPlateau()[this.position.getX()][this.position.getY()] = this.tuile;
				ok = true;
			} else {
				ok = false;
			}
		} else {
			if(this.partie.getPlateauJeu().getPlateau()[this.getPosition().getX()][this.position.getY()] != null)
			{
				ok = false;
			} else {
				this.partie.getPlateauJeu().getPlateau()[this.position.getX()][this.position.getY()] = this.tuile;
				ok = true;
			}
		}
		return ok;
	}
}