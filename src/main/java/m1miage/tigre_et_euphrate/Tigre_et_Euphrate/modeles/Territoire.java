package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles;

import java.util.ArrayList;
import java.util.ListIterator;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;

public class Territoire {
	
	/**
	 * Liste contenant toute les tuiles civilisation
	 * du royaume.
	 */
	protected ArrayList<TuileCivilisation> tuilesCivilisation;
	
	/**
	 * Constructeur d'un territoire sans paramètre
	 */
	public Territoire(){
		this.tuilesCivilisation = new ArrayList<TuileCivilisation>();
	}
	
	/**
	 * Constructeur d'un territoire
	 * @param pTuiles
	 */
	public Territoire(ArrayList<TuileCivilisation> pTuiles){
		this.tuilesCivilisation = pTuiles;
	}
	
	/**
	 * return la liste des tuiles civilisation
	 * @return
	 */
	public ArrayList<TuileCivilisation> getTuilesCivilisation() {
		return tuilesCivilisation;
	}

	/**
	 * Méthode pour savoir si une position est adjacente au territoire
	 * @param Position
	 * @return vrai ou faux
	 */
	public boolean estAdjacent(Position ppos){
		boolean trouve = false;
		ListIterator<TuileCivilisation> it = this.getTuilesCivilisation().listIterator();
		while(it.hasNext() && !trouve){
			if(it.next().getPosition().estAdjacente(ppos))
				trouve = true;
		}
		return trouve;
	}

	/**
	 * @param ptuilesCivilisation the tuilesCivilisation to set
	 */
	public void setTuilesCivilisation(ArrayList<TuileCivilisation> ptuilesCivilisation) {
		this.tuilesCivilisation = ptuilesCivilisation;
	}
	
	

}
