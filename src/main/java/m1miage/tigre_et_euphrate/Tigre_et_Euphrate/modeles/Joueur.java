package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Dynastie;

/**
 * Classe representant un joueur
 *
 */
public class Joueur {

	/**
	 * Le nom du joueur
	 */
	private String nom;

	/**
	 * Dynastie du joueur
	 */
	private Dynastie dynastie;

	/**
	 * Le deck public du joueur
	 */
	private DeckPublic deckPublic;
	
	/**
	 * Le deck public du joueur
	 */
	private DeckPrive deckPrive;

	/**
	 * boolean pour savoir si c'est le tour du joueur ou non
	 */
	private boolean tour;

	/**
	 * Nombre d'actions restantes
	 */
	//private ArrayList<Action> liste;

	/**
	 * place dans le jeu par tour
	 */
	private int place;

	/**
	 * Constructeur vide d'un joueur
	 */
	public Joueur() {
		super();
	}

	/**
	 * Constructeur d'un joueur
	 * @param pnom nom du joueur
	 * @param pdeckPublic le deck public
	 * @param pdeckPrive le deck prive
	 */
	public Joueur(String pnom, Dynastie pdynastie, DeckPublic pdeckPublic, DeckPrive pdeckPrive){
		this.dynastie = pdynastie;
		this.nom = pnom;
		this.deckPublic = pdeckPublic;
		this.deckPrive = pdeckPrive;
	}

	/**
	 * retourne la dynastie du joueur
	 * @return
	 */
	public Dynastie getDynastie() {
		return dynastie;
	}



	/**
	 * Méthode permettant de passer le tour du joueur
	 */
	public void passerTour(){
		this.tour = false;
	}

	/**
	 * @return the deckPublic
	 */
	public DeckPublic getDeckPublic() {
		return deckPublic;
	}

	/**
	 * @param pdeckPublic the deckPublic to set
	 */
	public void setDeckPublic(DeckPublic pdeckPublic) {
		this.deckPublic = pdeckPublic;
	}

	/**
	 * @return the deckPrive
	 */
	public DeckPrive getDeckPrive() {
		return deckPrive;
	}

	/**
	 * @param pdeckPrive the deckPrive to set
	 */
	public void setDeckPrive(DeckPrive pdeckPrive) {
		this.deckPrive = pdeckPrive;
	}
	
	
}
