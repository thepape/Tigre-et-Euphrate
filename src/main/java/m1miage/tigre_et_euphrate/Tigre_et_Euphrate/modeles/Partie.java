package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles;

import java.util.ArrayList;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.Monument;

/**
 * Classe representant une partie
 *
 */
public class Partie {

	/**
	 * Le plateau de jeu des joueurs
	 */
	private Plateau plateauJeu;
	
	/**
	 * La liste des joueurs jouant la partie
	 */
	private ArrayList<Joueur> listeJoueur;
	
	/**
	 * Constructeur simple d'une partie
	 * @param pPlateauJeu plateau du jeu
	 * @param plistejoueur liste des joueurs
	 */
	public Partie(Plateau pPlateauJeu, ArrayList<Joueur> plistejoueur){
		this.plateauJeu = pPlateauJeu;
		this.listeJoueur = plistejoueur;
	}

	public Plateau getPlateauJeu() {
		return plateauJeu;
	}

	public void setPlateauJeu(Plateau plateauJeu) {
		this.plateauJeu = plateauJeu;
	}

	public ArrayList<Joueur> getListeJoueur() {
		return listeJoueur;
	}

	public void setListeJoueur(ArrayList<Joueur> listeJoueur) {
		this.listeJoueur = listeJoueur;
	}
	
	
}
