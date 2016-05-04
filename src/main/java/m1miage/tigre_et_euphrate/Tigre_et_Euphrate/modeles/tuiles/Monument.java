package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles;

import java.io.Serializable;

/**
 *
 * Classe représentant un monument.
 *
 */
public class Monument implements Serializable{

	/**
	 * couleur de l'arche (couleur 1).
	 */
	private String couleurArche;

	/**
	 * couleur des escaliers (couleur 2).
	 */
	private String couleurEscaliers;

	/**
	 * indique si le monument a été placé.
	 */
	private boolean place;

	/**
	 * tuile nord ouest sur laquelle est placée le monument
	 */
	private TuileCivilisation tuileNO;

	public String getCouleurArche() {
		return couleurArche;
	}

	public String getCouleurEscaliers() {
		return couleurEscaliers;
	}

	/**
	 * tuile nord est sur laquelle est placée le monument
	 */
	private TuileCivilisation tuileNE;

	/**
	 * tuile sud ouest sur laquelle est placée le monument
	 */
	private TuileCivilisation tuileSO;

	/**
	 * tuile sud est sur laquelle est placée le monument
	 */
	private TuileCivilisation tuileSE;

	/**
	 * Constructeur pour Json
	 */
	private Monument()
	{

	}
	/**
	 * Constructeur d'un monument. Les deux couleurs doivent être différentes.
	 * @param pCouleurArche couleur 1
	 * @param pCouleurEscaliers couleur 2
	 */
	public Monument(String pCouleurArche, String pCouleurEscaliers){
		this.couleurArche = pCouleurArche;
		this.couleurEscaliers = pCouleurEscaliers;
		this.place = false;

		this.tuileNE = null;
		this.tuileNO = null;
		this.tuileSE = null;
		this.tuileSO = null;
	}

	public TuileCivilisation getTuileNO() {
		return tuileNO;
	}

	/**
	 * FOR TEST PURPOSE
	 * @param tuile
	 */
	public void setTuileNO(TuileCivilisation tuile){
		this.tuileNO = tuile;
	}

	public TuileCivilisation getTuileNE() {
		return tuileNE;
	}


	public TuileCivilisation getTuileSO() {
		return tuileSO;
	}


	public TuileCivilisation getTuileSE() {
		return tuileSE;
	}


	/**
	 * Place le monument sur les 4 tuiles civilisation en paramètre.
	 * @param pTuileNO tuile nord ouest
	 * @param pTuileNE tuile nord est
	 * @param pTuileSO tuile sud ouest
	 * @param pTuileSE tuile sud est
	 */
	public boolean construire(TuileCivilisation pTuileNO, TuileCivilisation pTuileNE, TuileCivilisation pTuileSO, TuileCivilisation pTuileSE){

		if(!this.verifierCouleurCases(pTuileNO, pTuileNE, pTuileSO, pTuileSE)){
			return false;
		}

		this.tuileNE = pTuileNE;
		this.tuileNO = pTuileNO;
		this.tuileSE = pTuileSE;
		this.tuileSO = pTuileSO;

		this.tuileNE.setMonument(this);
		this.tuileNO.setMonument(this);
		this.tuileSE.setMonument(this);
		this.tuileSO.setMonument(this);

		return true;
	}

	/**
	 * Verifie que les 4 cases de base sont de meme couleur
	 * @param pTuileNO
	 * @param pTuileNE
	 * @param pTuileSO
	 * @param pTuileSE
	 * @return vrai si les 4 cases sont de meme couleur
	 */
	public boolean verifierCouleurCases(TuileCivilisation pTuileNO, TuileCivilisation pTuileNE, TuileCivilisation pTuileSO, TuileCivilisation pTuileSE){

		boolean couleursIdentiques = pTuileNO.estDeMemeType(pTuileNE) && pTuileNO.estDeMemeType(pTuileSO) && pTuileNO.estDeMemeType(pTuileSE);
		//verifie que les 4 tuiles ont la meme couleur
		if(!couleursIdentiques){
			return false;
		}

		//verifie qu'un monument ne repose pas deja sur la tuile
		if(pTuileNO.estTuileMonument() || pTuileNE.estTuileMonument() || pTuileSO.estTuileMonument() || pTuileSE.estTuileMonument()){
			return false;
		}

		String couleur = pTuileNO.getType().getCouleur();
		//verifie que l'une des deux couleurs du monument est egale a la couleur des 4 tuiles
		if(!this.couleurArche.equals(couleur) && !this.couleurEscaliers.equals(couleur)){
			return false;
		}

		return true;
	}
}
