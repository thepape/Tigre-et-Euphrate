package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles;

/**
 * 
 * Classe représentant une tuile civilisation.
 *
 */
public class TuileCivilisation extends Tuile {
	
	/**
	 * Type de la tuile civilisation.
	 */
	private TypeTuileCivilisation type;
	
	/**
	 * Tresor si la tuile en possède un (Seule une tuile temple peut avoir un trésor).
	 */
	private Tresor tresor;
	
	/**
	 * Réference vers le monument qui se trouve sur la tuile si monument il y a.
	 */
	private Monument monument;
	
	/**
	 * Indique si la tuile est une tuile de jonction.
	 */
	private boolean estJonction;
	
	/**
	 * Constructeur d'une tuile civilisation.
	 * @param pType type de la tuile civilisation.
	 */
	public TuileCivilisation(TypeTuileCivilisation pType){
		this.tresor = null;
		this.type = pType.clone();	//clone pour eviter le couplage au maximum entre les objets.
		this.monument = null;
		
		//on définit si la tuile est dde type eau
		if(this.type.equals(TypeTuileCivilisation.Ferme)){
			this.tuileEau = true;
		}
		else{
			this.tuileEau = false;
		}
	}
	
	/**
	 * Retourne la carte et en fait une tuile de monument.
	 * @param pMonument référence vers le monument construit par dessus la tuile.
	 */
	public void setMonument(Monument pMonument){
		if(this.monument == null){
			this.monument = pMonument;
		}
	}
	
	/**
	 * Indique si la tuile est une tuile sur laquelle repose un monument.
	 * @return vrai si un monument repose sur la tuile.
	 */
	public boolean estTuileMonument(){
		return this.monument != null;
	}
	
	/**
	 * Retourne le monument qui repose sur la tuile si monument il y a.
	 * @return le monument qui repose sur la tuile ou NULL si pas de monument.
	 */
	public Monument getMonument(){
		return this.monument;
	}

	/**
	 * indique si la tuile est une jonction entre deux royaumes.
	 * @return
	 */
	public boolean estJonction(){
		return this.estJonction;
	}
	
	/**
	 * définit si la tuile est une jonction entre deux royaumes.
	 * @param pEstJonction
	 */
	public void setJonction(boolean pEstJonction){
		this.estJonction = pEstJonction;
	}
}
