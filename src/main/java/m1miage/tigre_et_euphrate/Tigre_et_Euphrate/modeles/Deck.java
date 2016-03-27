package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles;

import java.util.ArrayList;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCatastrophe;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;

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
	 * Liste des tuiles civilisation : Deck Privé
	 */
	private ArrayList<TuileCivilisation> deckPrive;

	/**
	 * Liste des chefs et des tuiles catastrophes contenu dans le deck : Deck Public
	 */
	private ArrayList<Placable> deckPublic;

	/**
	 * Joueur auquel le deck appartient
	 */
	private Joueur joueur;

	//Constructeur 
	
	/**
	 * Constructeur d'un deck
	 * @param pdeckPrive la liste des tuiles civilisation contenu dans le deck prive
	 * @param pchefs la liste des chefs et tuiles catastrophe contenu dans le deck public
	 * @param pjoueur le joueur auquel le deck appartient
	 */
	public Deck(ArrayList<TuileCivilisation> pdeckPrive, ArrayList<Placable> pdeckPublic,
			Joueur pjoueur) {
		super();
		this.deckPrive = pdeckPrive;
		this.deckPublic = pdeckPublic;
		this.joueur = pjoueur;
		this.idDeck = Deck.idIncrementDeck;
		Deck.idIncrementDeck++;
	}
	
	/**
	 * Constructeur vide
	 */
	public Deck() {
		super();
		this.deckPrive = null;
		this.deckPublic = null;
		this.joueur = null;
		this.idDeck = 0;
	}
	
	//Methodes
	
	/**
	 * Méthode pour ajouter une tuile civilisation dans le deck prive
	 * @param pTuile la tuile à ajouter
	 * @return vrai ou faux
	 */
	public boolean ajouterTuileCivilisation(TuileCivilisation pTuile){
		return this.deckPrive.add(pTuile);
	}
	
	/**
	 * Méthode pour supprimer une tuile civilisation du deck prive
	 * @param pTuile la tuile à supprimer
	 * @return vrai ou faux
	 */
	public boolean supprimerTuileCivilisation(TuileCivilisation pTuile){
		return this.deckPrive.remove(pTuile);
	}
	
	/**
	 * Méthode pour ajouter un chef dans le deck
	 * chef pour le deck public
	 * @param pChef le chef à ajouter
	 */
	public void ajouterChef(Chef pChef){
		this.deckPublic.add(pChef);
	}
	
	/**
	 * Méthode pour supprimer un chef du deck public
	 * @param pChef le chef à supprimer
	 * @return vrai ou faux si ça c'esr bien passé ou non
	 */
	public boolean supprimerChef(Chef pChef){
		return this.deckPublic.remove(pChef);
	}
	
	/**
	 * Méthode pour ajouter une tuile catastrophe au deck public
	 */
	public void ajouterTuileCatastrophe(TuileCatastrophe pTuile){
		this.deckPublic.add(pTuile);
	}
	
	/**
	 * Méthode pour supprimer une tuile catastrophe du deck public
	 * @param pTuile la tuile catastrophe à supprimer
	 * @return vrai ou faux si ça s'est bien passé ou non
	 */
	public boolean supprimerTuileCatastrophe(TuileCatastrophe pTuile){
		return this.deckPublic.remove(pTuile);
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

	/**
	 * @return the deckPrive
	 */
	public ArrayList<TuileCivilisation> getDeckPrive() {
		return deckPrive;
	}

	/**
	 * @param pdeckPrive the deckPrive to set
	 */
	public void setDeckPrive(ArrayList<TuileCivilisation> pdeckPrive) {
		this.deckPrive = pdeckPrive;
	}

	/**
	 * @return the deckPublic
	 */
	public ArrayList<Placable> getDeckPublic() {
		return deckPublic;
	}

	/**
	 * @param pdeckPublic the deckPublic to set
	 */
	public void setDeckPublic(ArrayList<Placable> pdeckPublic) {
		this.deckPublic = pdeckPublic;
	}
	
	

}
