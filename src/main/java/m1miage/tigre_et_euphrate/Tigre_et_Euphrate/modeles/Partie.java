package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Classe representant une partie
 *
 */
public class Partie implements PartieInterface{

	/**
	 * Le plateau de jeu des joueurs
	 */
	private Plateau plateauJeu;
	
	/**
	 * La liste des joueurs jouant la partie
	 */
	private ArrayList<Partie> listePartie;
	
	/**
	 * Un joueur plutot beau gosse (ou pas)
	 */
	private Joueur joueur;
	
	/**
	 * La pioche
	 */
	private Pioche pioche;
	
	
	/**
	 * Constructeur simple d'une partie
	 * @param pPlateauJeu plateau du jeu
	 * @param plistejoueur liste des parties
	 */
	public Partie(Plateau pPlateauJeu, ArrayList<Partie> plistepartie, Pioche pPioche){
		this.plateauJeu = pPlateauJeu;
		this.listePartie = plistepartie;
		this.pioche = pPioche;
	}

	/**
	 * getter du plateau de jeu
	 * @return le plateau de jeu
	 */
	public Plateau getPlateauJeu() {
		return plateauJeu;
	}

	/**
	 * setter du plateau de jeu
	 * @param plateauJeu
	 */
	public void setPlateauJeu(Plateau plateauJeu) {
		this.plateauJeu = plateauJeu;
	}

	/**
	 * getter de la liste des parties de la game (désolé anglais)
	 * @return
	 */
	public ArrayList<Partie> getListePartie() {
		return listePartie;
	}

	/**
	 * setter de la liste des parties
	 * @param listePartie
	 */
	public void setListeJoueur(ArrayList<Partie> listePartie) {
		this.listePartie = listePartie;
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

	/**
	 * getter du nom du joueur de la partie
	 */
	public String getName() throws RemoteException {
		return joueur.getNom();
	}

	/**
	 * getter du nombre de point tresor du joueur de la partie
	 */
	public int getPointTresor() throws RemoteException {
		return joueur.getPointTresor();
	}

	/**
	 * getter du point victoire du joueur de la partie
	 */
	public int getPointVictoire() throws RemoteException {
		return joueur.getPointVictoire();
	}

	/**
	 * getter du deck public du joueur de la partie
	 */
	public DeckPublic getDeckPublic() throws RemoteException {
		return joueur.getDeckPublic();
	}
	
}
