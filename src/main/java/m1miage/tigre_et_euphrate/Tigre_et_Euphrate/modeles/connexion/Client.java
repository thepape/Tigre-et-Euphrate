package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.connexion;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.UnknownHostException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Dynastie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.conflit.Conflits;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action.Action;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action.PlacerTuileCivilisation;


public class Client extends UnicastRemoteObject implements InterfaceServeurClient, ObservableValue {

	private ArrayList<ChangeListener> listeners = new ArrayList<ChangeListener>();

	/**
	 * ip du joueur qui va se connecter
	 */
	private String ip = null;

	/**
	 * idClient du coté serveur
	 */
	private int idClientCourant;

	/**
	 * port utilisé par RMI
	 */
	private int port;

	/**
	 * Nom de l'espace de jeu
	 */
	private String namespace;

	/**
	 * nom du joueur qui va se connecter
	 */
	private String nomJoueur = null;

	/**
	 * joueur courant de la partie
	 */
	private Joueur joueur = null;

	/**
	 * ensemble des données de la partie
	 */
	public Partie partieCourante = null;

	/**
	 * serveur auquel le client est connecté
	 */
	public InterfaceServeurClient serveur = null;

	private ArrayList<Dynastie> listeDynastie = new ArrayList<Dynastie>();

	/**
	 * Constructeur du client
	 * @param pIp
	 * @param pNomJoueur
	 * @throws RemoteException
	 */
	public Client(String pIp, String pNomJoueur) throws RemoteException {
		this.listeDynastie.add(Dynastie.Lanister);
		this.listeDynastie.add(Dynastie.Stark);
		this.listeDynastie.add(Dynastie.Targaryen);
		this.listeDynastie.add(Dynastie.Tyrell);

		this.nomJoueur = pNomJoueur;
		this.ip = pIp;
		this.port = 42000;
		this.namespace = "Tigre-et-euphrate";
		this.partieCourante = new Partie();
	}

	/**
	 * getter de l'id du client
	 * @return id du client
	 */
	public int getIdClientCourant() {
		return idClientCourant;
	}

	/**
	 * setter de l'id du client
	 * @param idClientCourant
	 */
	public void setIdClientCourant(int idClientCourant) {
		this.idClientCourant = idClientCourant;
	}

	/**
	 * getter du joueur
	 * @return joueur
	 */
	public Joueur getJoueur()
	{
		return this.joueur;
	}

	/**
	 * Fonction qui permet de se connecter au serveur
	 * @throws NotBoundException
	 * @throws RemoteException
	 * @throws MalformedURLException
	 */
	public void connect() throws MalformedURLException, RemoteException, NotBoundException
	{
		System.setSecurityManager(null);
		System.setProperty("java.security.policy", "file:/security.policy");
		System.setProperty("java.rmi.server.hostname", this.ip);

		/////////////// infos de connexion

		String url = "rmi://"+this.ip+":"+this.port+"/"+this.namespace;
		//PartieInterface partie = null;

			//serveur = (Serveur) Naming.lookup(url);
			this.serveur = (InterfaceServeurClient) Naming.lookup("rmi://"+this.ip+":"+this.port+"/"+this.namespace);
			//this.serveur = partie;

		System.out.println("Client connecté au serveur !");
			serveur.ajouterClient(this);

	}

	public boolean deconnecter() throws RemoteException{
		return this.serveur.retirerClient(this);
	}

	/**
	 * getter du namespace
	 * @return namespace
	 */
	public String getNamespace() throws RemoteException{
		return namespace;
	}

	/**
	 * setter du namespace
	 * @param namespace
	 */
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	/**
	 * Fonction qui permet d'attendre le lancement du serveur si un client veut se connecter sans que le serveur doit lancer
	 * @param pServeur
	 */
	public void attendreLancementServeur(Serveur pServeur)
	{
		while(!pServeur.isLance())
		{
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * setter du serveur
	 * @param serveur
	 */
	public void setServeur(InterfaceServeurClient serveur) throws RemoteException {

	}

	/**
	 * setter de la liste des clients (inutile dans le client)
	 * @param client
	 */
	public void setListeClient(ArrayList<InterfaceServeurClient> client) {

	}

	/**
	 * Fonction qui permet d'envoyer des données entre serveur et client
	 */
	public boolean send(Action action, int idClient) throws RemoteException {
		action.setPartie(this.getPartie());
		return action.executer();

		//this.notifierChangement("plateau");
	}

	/**
	 * getter de la partie
	 * @return partie
	 */
	public Partie getPartie() {
		return this.partieCourante;
	}

	/**
	 * Fonction qui permet d'ajouter un client (inutile dans leclient)
	 */
	public void ajouterClient(InterfaceServeurClient client) throws RemoteException {

	}

	/**
	 * getter du nom dujoueur
	 * @return nomJoueur
	 */
	public String getNomJoueur() throws RemoteException {
		return this.nomJoueur;
	}

	/**
	 * getter du serveur
	 * @return serveur
	 */
	public InterfaceServeurClient getServeur() throws RemoteException {
		return this.serveur;
	}

	/**
	 * getter de l'id du client
	 * return idClientCourant
	 */
	public int getIdObjetPartie() throws RemoteException {
		return this.idClientCourant;
	}

	/**
	 * setter de l'id objet partie
	 * @param idObjetPartie
	 */
	public void setIdObjetPartie(int idObjetPartie) throws RemoteException {
		this.idClientCourant = idObjetPartie;
	}

	/**
	 * Setter de la dynastie
	 */
	public void setDynastie(Dynastie dynastie) throws RemoteException {
		this.joueur.setDynastie(dynastie);
	}

	/**
	 * Fonction qui change l'état d'un joueur en fonction de son état précedent
	 */
	public void switchJoueurPret() throws RemoteException{
		if(this.joueur.estPret()){
			this.joueur.setEstPret(false);
		}
		else{
			this.joueur.setEstPret(true);
		}
	}


	/**
	 * Getter de la liste de dynastie
	 */
	public ArrayList<Dynastie> getListeDynastie() throws RemoteException {
		return this.listeDynastie;
	}

	/**
	 * Setter de la liste de dynastie
	 */
	public void setListeDynastie(ArrayList<Dynastie> liste) throws RemoteException {
		this.listeDynastie = liste;

	}

	/**
	 *
	 */
	public void addListener(InvalidationListener arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 *
	 */
	public void removeListener(InvalidationListener arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * fonction qui ajoute un changeListener à la vue
	 */
	public void addListener(ChangeListener listener) {
		this.listeners.add(listener);
	}

	public Object getValue() {
		return this;
	}

	/**
	 * fonction qui supprime un ChangeListener de la vue
	 */
	public void removeListener(ChangeListener listener) {
		this.listeners.remove(listener);
	}

	/**
	 * fonction qui supprime tous les ChangeListeners du client
	 */
	public void clearListeners(){
		this.listeners.clear();
	}

	/**
	 * Fonction qui notifie le changement du modele à la vue
	 */
	public void notifierChangement(ArrayList<Object> args){
		for(ChangeListener listener : this.listeners){
			listener.changed(this, null, args);
		}
	}

	/**
	 * getter de la partie
	 * @return partie
	 */
	public Partie getPartieCourante() {
		return partieCourante;
	}

	/**
	 * setter du Joueur
	 * @param joueur
	 */
	public void setJoueur(Joueur joueur) throws RemoteException {
		this.joueur = joueur;
	}

	/**
	 * setter de la partie courante
	 */
	public void setPartieCourante(Partie partie) throws RemoteException {
		this.partieCourante = partie;

	}

	public void passerTour() throws RemoteException{
		this.partieCourante.passerTour();
		//System.out.println("CLIENT MIS A JOUR");
	}

	public void envoyerNouveauConflit(Conflits conflit, int idSender) throws RemoteException {
		this.partieCourante.ajouterConflit(conflit);

	}


	/********************************************************************************************************************
	 * 									FONCTIONS NON UTILISE PAR LE CLIENT
	 ********************************************************************************************************************/
	public void setDynastieOfClient(InterfaceServeurClient client) throws RemoteException {
		// TODO Auto-generated method stub

	}

	public boolean setDynastieOfClient(InterfaceServeurClient client, Dynastie dynastie) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	public ArrayList<Dynastie> getListeDynastieDispo() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public void libererDynastie(Dynastie d) throws RemoteException {
		// TODO Auto-generated method stub

	}

	public ArrayList<InterfaceServeurClient> getClients() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean retirerClient(InterfaceServeurClient client) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	public void switchJoueurEstPret(InterfaceServeurClient client) throws RemoteException {
		// TODO Auto-generated method stub

	}

	public int getUniqueId() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	public void envoyerRenforts(ArrayList<TuileCivilisation> renforts, Joueur joueur) throws RemoteException {
		// TODO Auto-generated method stub

	}

	public boolean piocherCartesManquantes(Joueur j) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	public void finirPartie() throws RemoteException {

	}

	public void envoyerPointsAttribues(Joueur joueur) throws RemoteException {
		// TODO Auto-generated method stub

	}

	public ArrayList<Joueur> recupererListeJoueurPartie() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}



}
