package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles;

import java.util.ArrayList;

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
	 * Les cartes civilisations cachées du joueur (hors terrain)
	 */
	//private Deck caché;
	
	/**
	 * Les cartes catastrophes et chefs non cachées du joueur (hors terrain)
	 */
	//private Deck visible;
	
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
	 * Constructeur d'un joueur 
	 * @param pnom nom du joueur
	 */
	public Joueur(String pnom){
		this.nom = pnom;
		//this.caché = new Deck();
		//this.visible = new Deck();
	}
	
	/**
	 * Méthode permettant de passer le tour du joueur
	 */
	public void passerTour(){
		this.tour = false;
	}
}
