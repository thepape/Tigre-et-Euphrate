package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.conflit;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;

public class TuileRenfort {

	/**
	 * Tuile correspondante du deck d'un joueur
	 */
	private TuileCivilisation tuileCorrespondante;

	/**
	 * @return Tuile correspondante du deck d'un joueur
	 */
	public TuileCivilisation getTuileCorrespondante() {
		return tuileCorrespondante;
	}

	/**
	 * setter de TuileCorrespondante
	 * @param tuileCorrespondante
	 */
	public void setTuileCorrespondante(TuileCivilisation tuileCorrespondante) {
		this.tuileCorrespondante = tuileCorrespondante;
	}


}
