package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.connexion;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.PartieInterface;


public class Client extends UnicastRemoteObject implements InterfaceServeurClient {

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

	/**
	 * Constructeur du client
	 * @param pIp
	 * @param pNomJoueur
	 * @throws RemoteException
	 */
	public Client(String pIp, String pNomJoueur) throws RemoteException {
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
	 */
	public void connect()
	{
		System.setSecurityManager(null);
		System.setProperty("java.security.policy", "file:/security.policy");
		System.setProperty("java.rmi.server.hostname", "127.0.0.1");

		/////////////// infos de connexion

		String url = "rmi://"+this.ip+":"+this.port+"/"+this.namespace;
		//PartieInterface partie = null;

		try {
			//serveur = (Serveur) Naming.lookup(url);
			this.serveur = (InterfaceServeurClient) Naming.lookup("rmi://127.0.0.1:42000/ABC");
			//this.serveur = partie;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERREUR : Serveur introuvable.");

		}

		System.out.println("Client connecté au serveur !");
		try
		{
			serveur.ajouterClient(this);
		} catch(RemoteException e)
		{
			e.printStackTrace();
		}

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
	 * Fonction qui permet de renjoindre une partie
	 */
	/*public void rejoindrePartie()
	{

		try {

			//on initialise la partie coté client
			this.partieCourante = new Partie();
			this.partieCourante.setNomJoueur(nomJoueur);
			try
			{
				//on envoie la partie au serveur pour récuperer une instance de joueur
				System.out.println(this.serveur.getPartie());
				this.joueur = this.serveur.getPartie().ajouterJoueur(this.partieCourante);
				//on bind le joueur retourné par le serveur a la partie
				this.partieCourante.setJoueur(joueur);
			} catch(RemoteException e)
			{
				e.printStackTrace();
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/

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
	public void send(String string, int idClient) throws RemoteException {
		System.out.println(idClient);
		System.out.println(string);
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

}
