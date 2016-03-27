package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles;

import java.util.ArrayList;

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
	 * La pioche
	 */
	private Pioche pioche;
	
	/**
	 * Constructeur simple d'une partie
	 * @param pPlateauJeu plateau du jeu
	 * @param plistejoueur liste des joueurs
	 */
	public Partie(Plateau pPlateauJeu, ArrayList<Joueur> plistejoueur, Pioche pPioche){
		this.plateauJeu = pPlateauJeu;
		this.listeJoueur = plistejoueur;
		this.pioche = pPioche;
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

	/**
	 * @return la pioche
	 */
	public Pioche getPioche() {
		return pioche;
	}

	/**
	 * @param ppioche the pioche to set
	 */
	public void setPioche(Pioche ppioche) {
		this.pioche = ppioche;
	}
	
}
