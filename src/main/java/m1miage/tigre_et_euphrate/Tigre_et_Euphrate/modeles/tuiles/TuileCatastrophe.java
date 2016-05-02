package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Placable;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;

/**
 * 
 * Classe représentant une tuile catastrophe.
 *
 */
public class TuileCatastrophe extends Tuile {

	public TuileCatastrophe(){
		super();
		TuileCatastrophe.idIncrement += 10;
		this.id = TuileCatastrophe.idIncrement;
	}
}
