package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles;

/**
 * 
 * Classe représentant un monument.
 *
 */
public class Monument {
	
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
	
	/**
	 * Place le monument sur les 4 tuiles civilisation en paramètre.
	 * @param pTuileNO tuile nord ouest
	 * @param pTuileNE tuile nord est
	 * @param pTuileSO tuile sud ouest
	 * @param pTuileSE tuile sud est
	 */
	public void construire(TuileCivilisation pTuileNO, TuileCivilisation pTuileNE, TuileCivilisation pTuileSO, TuileCivilisation pTuileSE){
		this.tuileNE = pTuileNE;
		this.tuileNO = pTuileNO;
		this.tuileSE = pTuileSE;
		this.tuileSO = pTuileSO;
		
		this.tuileNE.setMonument(this);
		this.tuileNO.setMonument(this);
		this.tuileSE.setMonument(this);
		this.tuileSO.setMonument(this);
	}
}
