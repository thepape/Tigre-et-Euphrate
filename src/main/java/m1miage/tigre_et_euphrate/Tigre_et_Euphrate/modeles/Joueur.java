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
	 * Le deck du joueur
	 */
	private Deck deck;

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
	 */
	public Joueur(String pnom, Dynastie pdynastie){
		this.dynastie = pdynastie;
		this.nom = pnom;
		this.deck = new Deck();
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
	 * @return the deck
	 */
	public Deck getDeck() {
		return deck;
	}

	/**
	 * @param pdeck the deck to set
	 */
	public void setDeck(Deck pdeck) {
		this.deck = pdeck;
	}
}
