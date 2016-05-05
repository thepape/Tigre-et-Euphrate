package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles;

import java.io.Serializable;

/**
 *
 * Classe représentant un trésor.
 *
 */
public class Tresor implements Serializable {

	private boolean tresor;

	/**
	 * Constructeur d'un trésor.
	 */
	public Tresor()
	{
		tresor = true;
	}

	public boolean isTresor() {
		return tresor;
	}

	public void setTresor(boolean tresor) {
		this.tresor = tresor;
	}


}
