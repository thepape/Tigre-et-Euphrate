package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles;

import java.util.ArrayList;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.Tuile;

/**
 * Classe representant un Deck.
 * Un deck est constitué d'une liste de tuiles et d'une liste de chefs
 * Il contient 2 tuiles catastrophe, 4 chefs  visible
 * Il contient 6 tuiles civilisation
 *
 */
public class Deck {

	/**
	 *  Incrément de l'id des objets Deck.
	 */
	private static int idIncrementDeck = 0;

	/**
	 * id du deck
	 */
	private int idDeck;

	/**
	 * liste des tuiles contenu dans le deck
	 */
	private ArrayList<Tuile> tuiles;

	/**
	 * Liste des chefs contenu dans le deck
	 */
	private ArrayList<Chef> chefs;

	/**
	 * Joueur auquel le deck appartient
	 */
	private Joueur joueur;

	//Constructeur 
	
	/**
	 * Constructeur d'un deck
	 * @param ptuiles la liste des tuiles contenu dans le deck
	 * @param pchefs la liste des chefs contenu dans le deck
	 * @param pjoueur le joueur auquel le deck appartient
	 */
	public Deck(ArrayList<Tuile> ptuiles, ArrayList<Chef> pchefs,
			Joueur pjoueur) {
		super();
		this.tuiles = ptuiles;
		this.chefs = pchefs;
		this.joueur = pjoueur;
		this.idDeck = Deck.idIncrementDeck;
		this.idIncrementDeck++;
	}
	
	/**
	 * Constructeur vide
	 */
	public Deck() {
		super();
		this.tuiles = null;
		this.chefs = null;
		this.joueur = null;
		this.idDeck = 0;
	}
	
	//Methodes
	
	/**
	 * Méthode pour ajouter une tuile dans le deck
	 * tuile catastrophe pour deck visible
	 * tuile civilisation pour deck caché
	 * @param pTuile la tuile à ajouter
	 */
	public void AddTuiles(Tuile pTuile){
		this.tuiles.add(pTuile);
	}
	
	/**
	 * Méthode pour ajouter un chef dans le deck
	 * chef pour le deck visible
	 * @param pChef le chef à ajouter
	 */
	public void AddChefs(Chef pChef){
		this.chefs.add(pChef);
	}

	
	//geter & Setter
	
	/**
	 * return l'id du deck
	 * @return
	 */
	public int getIdDeck() {
		return idDeck;
	}

	/**
	 * return la liste de tuiles
	 * @return
	 */
	public ArrayList<Tuile> getTuiles() {
		return tuiles;
	}

	/**
	 * modifie la liste des tuiles
	 * @param tuiles
	 */
	public void setTuiles(ArrayList<Tuile> tuiles) {
		this.tuiles = tuiles;
	}

	/**
	 * return la liste des chefs
	 * @return
	 */
	public ArrayList<Chef> getChefs() {
		return chefs;
	}

	/**
	 * modifie la liste des chefs
	 * @param chefs
	 */
	public void setChefs(ArrayList<Chef> chefs) {
		this.chefs = chefs;
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
