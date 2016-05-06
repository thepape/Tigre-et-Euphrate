package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Dynastie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.conflit.Conflits;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.TypeChef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.connexion.InterfaceServeurClient;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.connexion.Serveur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.Monument;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCatastrophe;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TypeTuileCivilisation;

/**
 * Classe representant une partie
 *
 */
public class Partie implements Serializable {

	@JsonIgnore
	private InterfaceServeurClient serveur;

	public ArrayList<Monument> getMonuments() {
		return monuments;
	}

	public void setMonuments(ArrayList<Monument> monuments) {
		this.monuments = monuments;
	}

	/**
	 * Le plateau de jeu des joueurs
	 */
	private Plateau plateauJeu;

	/**
	 * La liste des joueurs jouant la partie
	 */
	@JsonProperty("listeJoueurs")
	private ArrayList<Joueur> listeJoueurs = new ArrayList<Joueur>();

	/**
	 * La liste des tours de joueur
	 */
	private ArrayList<Joueur> listeTours = new ArrayList<Joueur>();

	/**
	 * La liste des tours des conflits
	 */
	@JsonProperty("listeConflits")
	private ArrayList<Joueur> listeToursConflits = new ArrayList<Joueur>();

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
	@JsonProperty("conflits")
	private ArrayList<Conflits> conflits;

	/**
	 * boolean pour savoir si partie est lancee
	 */
	private boolean estLancee = false;

	@JsonProperty("monuments")
	private ArrayList<Monument> monuments = new ArrayList<Monument>();

	/**
	 * Constructeur vide d'une partie
	 */
	public Partie() {
		this.listeJoueurs = new ArrayList<Joueur>();
		this.pioche = new Pioche();
		this.conflits = new ArrayList<Conflits>();
	}

	/**
	 * Constructeur simple d'une partie
	 * @param pPlateauJeu plateau du jeu
	 * @param plistejoueur liste des parties
	 */
	public Partie(Plateau pPlateauJeu, ArrayList<Joueur> pListeJoueurs, Pioche pPioche) {
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
	public ArrayList<Joueur> getListeTours() {
		return listeTours;
	}

	@JsonProperty("listeJoueurs")
	public ArrayList<Joueur> getListeJoueurs() {
		return listeJoueurs;
	}

	public void setListeJoueurs(ArrayList<Joueur> listeJoueurs) {
		this.listeJoueurs = listeJoueurs;
	}

	public void setListeTours(ArrayList<Joueur> listeTours) {
		this.listeTours = listeTours;
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
	@JsonIgnore
	public String getName()  {
		return joueur.getNom();
	}

	/**
	 * getter du nom de joueur de la partie
	 */
	@JsonIgnore
	public String getNomJoueur()  {
		return this.nomJoueur;
	}

	public void setNomJoueur(String pNom){
		this.nomJoueur = pNom;
	}

	/**
	 * getter du nombre de point tresor du joueur de la partie
	 */
	@JsonIgnore
	public int getPointTresor()  {
		return joueur.getPointTresor();
	}


	/**
	 * getter du deck public du joueur de la partie
	 */
	@JsonIgnore
	public DeckPublic getDeckPublic()  {
		return joueur.getDeckPublic();
	}

	/**
	 * ajoute un joueur a la liste des joueurs de la partie
	 * @param Partie du joueur à ajouter
	 * @return nouveau joueur ajouté à la partie
	 * @throws RemoteException
	 */
	public Joueur ajouterJoueur(Joueur pJoueur) {
		Joueur j = null;

		if(this.listeJoueurs.size() < 4)
		{
			j = new Joueur();
			j.setNom(pJoueur.getNom());

			this.listeJoueurs.add(pJoueur);
		}

		//System.out.println("Le joueur "+pJoueur.getNom()+" s'est connecté.");

		return j;
	}

	/**
	 * getter du joueur
	 */
	@JsonIgnore
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

	@JsonIgnore
	public DeckPrive getDeckPrive()  {
		return this.joueur.getDeckPrive();
	}

	@JsonIgnore
	public Dynastie getDynastie()  {
		return this.joueur.getDynastie();
	}

	public boolean tousLesJoueursPrets() {
		boolean res = true;
		for(Joueur joueur : this.listeJoueurs){
			if(!joueur.estPret()){
				res = false;
				break;
			}
		}

		return res;
	}

	public void setServeur(Serveur pServeur){
		this.serveur = pServeur;
	}

	public InterfaceServeurClient getServeur(){
		return this.serveur;
	}

	public void send(String string, int idClient) {
		//System.out.println(idClient);
		//System.out.println(string);
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

	public boolean IsEstLancee(){
		return this.estLancee;
	}

	@JsonProperty("monuments")
	public ArrayList<Monument> getListeMonuments(){
		return this.monuments;
	}

	/**
	 * méthode d'initialisation de la partie une fois que tous les joueurs ont prets
	 */
	public void initialiserPartie(){
		this.plateauJeu = new Plateau();
		this.pioche = new Pioche();

		//on recupere les joueurs
		ArrayList<InterfaceServeurClient> listeClients = new ArrayList<InterfaceServeurClient>();
		try
		{
			listeClients = this.serveur.getClients();
		} catch(RemoteException e)
		{
			e.printStackTrace();
		}
		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		ArrayList<Joueur> tours = new ArrayList<Joueur>();

		for(InterfaceServeurClient client : listeClients){
			try {
				Joueur joueur = client.getJoueur();
				joueurs.add(joueur);
				tours.add(client.getJoueur());

				//attribution des chefs
				Chef roi = new Chef(TypeChef.Roi, joueur);
				Chef marchand = new Chef(TypeChef.Marchand, joueur);
				Chef fermier = new Chef(TypeChef.Fermier, joueur);
				Chef pretre = new Chef(TypeChef.Pretre, joueur);

				DeckPublic dpub = new DeckPublic();
				DeckPrive dpriv = new DeckPrive();

				joueur.setDeckPublic(dpub);
				joueur.setDeckPrive(dpriv);


				joueur.getDeckPublic().ajouterChef(roi);
				joueur.getDeckPublic().ajouterChef(marchand);
				joueur.getDeckPublic().ajouterChef(fermier);
				joueur.getDeckPublic().ajouterChef(pretre);

				//attribution de 2 cartes cata
				joueur.getDeckPublic().ajouterCatastrophe(new TuileCatastrophe());
				joueur.getDeckPublic().ajouterCatastrophe(new TuileCatastrophe());
				//attribution au hasard de 6 tuile civilisation
				for(int i = 0; i < 6; i++){
					TuileCivilisation tuile = this.pioche.piocherTuile();
					joueur.getDeckPrive().ajouter(tuile);
				}

				//on renvoie le joueur au client
				client.setJoueur(joueur);
				//joueurs.add(client.getJoueur()); DEJA FAIT
				this.listeTours.add(client.getJoueur());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//construction des monuments
		Monument mrb = new Monument("rouge","bleu");
		Monument mrv = new Monument("rouge","vert");
		Monument mrj = new Monument("rouge","jaune");
		Monument mbv = new Monument("bleu","vert");
		Monument mbj = new Monument("bleu","jaune");
		Monument mvj = new Monument("vert","jaune");
		this.monuments.add(mrb);
		this.monuments.add(mrv);
		this.monuments.add(mrj);
		this.monuments.add(mbv);
		this.monuments.add(mbj);
		this.monuments.add(mvj);
		//attribution en dur des dynasties
		ArrayList<Dynastie> dynasties = new ArrayList<Dynastie>();
		dynasties.add(Dynastie.Lanister);
		dynasties.add(Dynastie.Stark);
		dynasties.add(Dynastie.Targaryen);
		dynasties.add(Dynastie.Tyrell);
		int it = 0;

		//////initialisation des decks de chaque joueur et de leurs dynasties
		/*
		for(Joueur joueur : joueurs){

			joueur.setDynastie(dynasties.get(it));
			it++;

			//attribution des chefs
			Chef roi = new Chef(TypeChef.Roi, joueur);
			Chef marchand = new Chef(TypeChef.Marchand, joueur);
			Chef fermier = new Chef(TypeChef.Fermier, joueur);
			Chef pretre = new Chef(TypeChef.Pretre, joueur);

			DeckPublic dpub = new DeckPublic();
			DeckPrive dpriv = new DeckPrive();

			joueur.setDeckPublic(dpub);
			joueur.setDeckPrive(dpriv);


			joueur.getDeckPublic().ajouterChef(roi);
			joueur.getDeckPublic().ajouterChef(marchand);
			joueur.getDeckPublic().ajouterChef(fermier);
			joueur.getDeckPublic().ajouterChef(pretre);

			//attribution de 2 cartes cata
			joueur.getDeckPublic().ajouterCatastrophe(new TuileCatastrophe());
			joueur.getDeckPublic().ajouterCatastrophe(new TuileCatastrophe());

			//attribution au hasard de 6 tuile civilisation
			for(int i = 0; i < 6; i++){
				TuileCivilisation tuile = this.pioche.piocherTuile();
				joueur.getDeckPrive().ajouter(tuile);
			}
		}*/

		this.estLancee=true;


	}

	/**
	 * Methode qui permet de retourner le joueur qui a le tour
	 * @return
	 */
	@JsonIgnore
	public Joueur getJoueurTour(){
		return this.listeTours.get(0);
	}

	/**
	 * Methode qui permet de passer le tour du joueur et de donner place au prochain
	 */
	public void passerTour(){
		Joueur temp = this.getJoueurTour();
		this.listeTours.remove(0);
		this.listeTours.add(temp);
		//System.out.println("C'est le tour de "+this.listeTours.get(0).getNom());


	}

	/**
	 * Methode qui va faire passer le tour du joueur en conflit
	 */
	public void passerTourConflit(){
		this.listeToursConflits.remove(0);
	}

	/**
	 * Methode permettant d'ajouter un joueur dans la liste des conflits
	 */
	public void addJoueurConflit(Joueur j){
		this.listeToursConflits.add(j);
	}

	/**
	 * methode permettant de piocher les cartes manquante a la fin du tour
	 * @param j1
	 * @return boolean true = fin de game
	 */
	public boolean piocheCartesManquantes(Joueur j1){
		if(j1.getNom().equals("aaa"))
			System.out.println("DECK AVANT PIOCHE:"+j1.getNom()+" - "+j1.getDeckPrive());

		int nbTuiles = j1.getDeckPrive().getDeckPrive().size();
		if(nbTuiles != 6){
			if(pioche.getTotalCarte() >= 6-nbTuiles ){
				for(int j = 0; j<6-nbTuiles;j++){
					TuileCivilisation tuile = this.pioche.piocherTuile();
					j1.getDeckPrive().ajouter(tuile);
					//System.out.println("Carte piochée: "+tuile.getId());
				}
			}else{

				return true;
			}
		}
		if(j1.getNom().equals("aaa"))
			System.out.println("DECK APRES PIOCHE:"+j1.getNom()+" - "+j1.getDeckPrive());

		return false;
	}

	@JsonProperty("listeConflits")
	public ArrayList<Joueur> getListeToursConflits(){
		return this.listeToursConflits;
	}

	public void setListeToursConflits(ArrayList<Joueur> joueurs){
		this.listeToursConflits = joueurs;
	}

	public boolean ajouterTourConflit(Joueur joueur){
		if(this.listeToursConflits.contains(joueur)){
			return false;
		}

		this.listeToursConflits.add(joueur);
		return true;
	}

	public boolean retirerTourConflit(Joueur joueur){
		if(!this.listeToursConflits.contains(joueur)){
			return false;
		}

		this.listeToursConflits.remove(joueur);
		return true;
	}

	public int nombreTresorsRestant(){
		int tresors = 0;

		for(Territoire territoire : this.getPlateauJeu().getListeRoyaume()){
			for(TuileCivilisation tuile : territoire.getTuilesCivilisation()){
				if(tuile.aTresor()){
					tresors++;
				}
			}
		}

		return tresors;
	}

	public boolean contientDejaConflitIdentique(Conflits conflit){
		for(Conflits c : this.conflits){
			Chef attaquantc = c.getChefAttaquant();
			Chef defenseurc = c.getChefDefenseur();
			Territoire tattc = c.getTerritoireAttaquant();
			Territoire tdefc = c.getTerritoireDefenseur();

			Chef attaquant = conflit.getChefAttaquant();
			Chef defenseur = conflit.getChefDefenseur();
			Territoire tatt = conflit.getTerritoireAttaquant();
			Territoire tdef = conflit.getTerritoireDefenseur();

			if(attaquantc.getId() == attaquant.getId()
					&& defenseurc.getId() == defenseur.getId()
					&& tattc.getIdTerritoire() == tatt.getIdTerritoire()
					&& tdefc.getIdTerritoire() == tdef.getIdTerritoire()){
				return true;
			}
		}

		return false;
	}
}
