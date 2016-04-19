package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Dynastie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.conflit.Conflits;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.connexion.Serveur;

/**
 * Classe representant une partie
 *
 */
public class Partie extends UnicastRemoteObject implements PartieInterface{

	private Serveur serveur;

	/**
	 * Le plateau de jeu des joueurs
	 */
	private Plateau plateauJeu;

	/**
	 * La liste des joueurs jouant la partie
	 */
	private ArrayList<PartieInterface> listeJoueurs = new ArrayList<PartieInterface>();

	/**
	 * Un joueur plutot beau gosse (ou pas)
	 */
	private Joueur joueur;

	/**
	 * nom du joueur de la partie
	 */
	private String nomJoueur;

	/**
	 * La pioche
	 */
	private Pioche pioche;
	
	/**
	 * liste des conflits
	 */
	private ArrayList<Conflits> conflits;

	/**
	 * Constructeur vide d'une partie
	 */
	public Partie() throws RemoteException{
		this.listeJoueurs = new ArrayList<PartieInterface>();
		this.pioche = new Pioche();
		this.conflits = new ArrayList<Conflits>();
	}

	/**
	 * Constructeur simple d'une partie
	 * @param pPlateauJeu plateau du jeu
	 * @param plistejoueur liste des parties
	 */
	public Partie(Plateau pPlateauJeu, ArrayList<PartieInterface> pListeJoueurs, Pioche pPioche) throws RemoteException {
		this.plateauJeu = pPlateauJeu;
		this.listeJoueurs = pListeJoueurs;
		this.pioche = pPioche;
		this.conflits = new ArrayList<Conflits>();
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
	public ArrayList<PartieInterface> getListeJoueurs() {
		return this.listeJoueurs;
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
	 * getter du nom de l'objet joueur de la partie
	 */
	public String getName() throws RemoteException {
		return joueur.getNom();
	}

	/**
	 * getter du nom de joueur de la partie
	 */
	public String getNomJoueur() throws RemoteException {
		return this.nomJoueur;
	}

	public void setNomJoueur(String pNom){
		this.nomJoueur = pNom;
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

	/**
	 * ajoute un joueur a la liste des joueurs de la partie
	 * @param Partie du joueur à ajouter
	 * @return nouveau joueur ajouté à la partie
	 * @throws RemoteException
	 */
	public Joueur ajouterJoueur(PartieInterface pJoueur) throws RemoteException {
		Joueur j = null;

		if(this.listeJoueurs.size() < 4)
		{
			j = new Joueur();
			j.setNom(pJoueur.getNomJoueur());

			this.listeJoueurs.add(pJoueur);
		}

		System.out.println("Le joueur "+pJoueur.getNomJoueur()+" s'est connecté.");

		return j;
	}

	/**
	 * getter du joueur
	 */
	public Joueur getJoueur()
	{
		return this.joueur;
	}

	/**
	 * setter du joueur
	 */
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	public DeckPrive getDeckPrive() throws RemoteException {
		return this.joueur.getDeckPrive();
	}

	public Dynastie getDynastie() throws RemoteException {
		return this.joueur.getDynastie();
	}

	public boolean tousLesJoueursPrets() {
		boolean res = true;
		for(PartieInterface joueur : this.listeJoueurs){
			try {
				if(!joueur.getJoueur().estPret()){
					res = false;
					break;
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return res;
	}

	public void setServeur(Serveur pServeur){
		this.serveur = pServeur;
	}

	public void send(String string, int idClient) throws RemoteException {
		System.out.println(idClient);
		System.out.println(string);
	}
	
	public ArrayList<Conflits> getConflits(){
		return this.conflits;
	}
	
	public void ajouterConflit(Conflits pConflit){
		this.conflits.add(pConflit);
	}
	
	public void retirerConflit(Conflits pConflit){
		this.conflits.remove(pConflit);
	}
}
