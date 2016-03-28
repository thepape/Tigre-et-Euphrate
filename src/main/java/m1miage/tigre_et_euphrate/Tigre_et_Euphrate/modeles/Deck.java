package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles;

/**
 * Classe abstraite representant un Deck.
 */
public abstract class Deck {

	/**
	 *  Incrément de l'id des objets Deck.
	 */
	private static int idIncrementDeck = 0;

	/**
	 * id du deck
	 */
	private int idDeck;

	/**
	 * Joueur auquel le deck appartient
	 */
	private Joueur joueur;

	//Constructeur 
	
	/**
	 * Constructeur d'un deck
	 * @param pjoueur le joueur auquel le deck appartient
	 */
	public Deck(Joueur pjoueur) {
		super();
		this.joueur = pjoueur;
		this.idDeck = Deck.idIncrementDeck;
		Deck.idIncrementDeck++;
	}
	
	/**
	 * Constructeur vide
	 */
	public Deck() {
		super();
		this.joueur = null;
		this.idDeck = 0;
	}
	
	/**
	 * Méthode abstraite pour l'ajout d'un objet placable
	 * @param objet à inserer
	 */
	public abstract boolean ajouter(Placable pobjet);
	
	/**
	 * Méthode abstraite pour la suppression d'un objet placable
	 * @param objet à supprimer
	 */
	public abstract boolean supprimer(Placable pobjet);
	
	//geter & Setter
	
	/**
	 * return l'id du deck
	 * @return
	 */
	public int getIdDeck() {
		return idDeck;
	}

	/**
	 * return le joueur auquel le deck appartient
	 * @return
	 */
	public Joueur getJoueur() {
		return joueur;
	}

	/**
	 * modifie le joueur auquel le deck appartient
	 * @param joueur
	 */
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
	
	

}
