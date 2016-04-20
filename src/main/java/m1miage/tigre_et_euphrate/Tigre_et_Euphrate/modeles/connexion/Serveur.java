package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.connexion;

import java.awt.List;
import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Dynastie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.PartieInterface;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action.Action;

public class Serveur extends UnicastRemoteObject implements Runnable, InterfaceServeurClient, Serializable
{
	/**
	 * ensemble des objets de la partie
	 */
	private Partie partie;

	/**
	 * liste des clients
	 */
	private ArrayList<InterfaceServeurClient> clients;

	/**
	 * id du serveur
	 */
	private int idServeur;

	/**
	 * boolean qui vérifie le lancement
	 */
	private boolean lance = false;

	/**
	 * port utilisé par RMI
	 */
	private int port;

	/**
	 * nom de l'espace de connection
	 */
	private String namespace;

	/**
	 * registry de RMI
	 */
	private Registry registry;

	/**
	 * url du serveur
	 */
	private String url;

	ArrayList<Dynastie> listeDynastieDispo = new ArrayList<Dynastie>();

	private ObservableList<Dynastie> listeDynastie;


	/**
	 * constructeur du serveur
	 * @throws RemoteException
	 */
	public Serveur() throws RemoteException
	{
		this.clients = new ArrayList<InterfaceServeurClient>();

		this.port = 42000;
		this.namespace = "Tigre-et-euphrate";

		listeDynastieDispo.add(Dynastie.Lanister);
		listeDynastieDispo.add(Dynastie.Stark);
		listeDynastieDispo.add(Dynastie.Targaryen);
		listeDynastieDispo.add(Dynastie.Tyrell);

		listeDynastie = FXCollections.observableArrayList(listeDynastieDispo);

	}

	public ArrayList<Dynastie> getListeDynastie() throws RemoteException {
		return listeDynastieDispo;
	}

	public void setListeDynastie(ObservableList<Dynastie> listeDynastie) {
		this.listeDynastie = listeDynastie;
	}

	public ArrayList<Dynastie> getListeDynastieDispo() throws RemoteException{
		return listeDynastieDispo;
	}

	public void setListeDynastieDispo(ArrayList<Dynastie> listeDynastieDispo) {
		this.listeDynastieDispo = listeDynastieDispo;
	}

	/**
	 * Fonction qui initialiser le serveur
	 */
	public void initialiser(){
		try {
			partie = new Partie();
			partie.setServeur(this);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//lancement du registre
		try {
			this.registry = LocateRegistry.createRegistry(this.port);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.setSecurityManager(null);

		System.setProperty("java.security.policy", "file:/security.policy");
		System.setProperty("java.rmi.server.hostname", "127.0.0.1");


		//this.url = "rmi://localhost:"+this.port+"/"+this.namespace;
		try {
			//Naming.rebind(url, this);
			Naming.rebind("rmi://127.0.0.1:42000/ABC", this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Serveur lancé !");

		this.lance = true;
	}

	/**
	 * Fonction qui permet d'attendre des joueurs
	 */
	public void attendreJoueursPrets()
	{
		while(!partie.tousLesJoueursPrets())
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * run du thread Serveur
	 */
	public void run() {
		// TODO Auto-generated method stub

		this.initialiser();

		this.attendreJoueursPrets();


	}

	/**
	 * Fonction qui permet d'éteindre le serveur
	 */
	public void shutDown()
	{
		try {
			Naming.unbind(this.url);
			UnicastRemoteObject.unexportObject(registry, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * getter des clients
	 * @return clients
	 */
	public ArrayList<InterfaceServeurClient> getClients() {
		return clients;
	}

	/**
	 * setter des clients
	 * @param clients
	 */
	public void setClients(ArrayList<InterfaceServeurClient> clients) {
		this.clients = clients;
	}

	/**
	 * getter de lancer
	 * @return lance
	 */
	public boolean isLance() {
		return lance;
	}

	/**
	 * setter de lance
	 * @param lance
	 */
	public void setLance(boolean lance) {
		this.lance = lance;
	}

	/**
	 * getter du port
	 * @return port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * setter du port
	 * @param port
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * getter du namespace
	 * @return namespace
	 */
	public String getNamespace() {
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
	 * setter du serveur
	 * @param serveur
	 */
	public void setServeur(InterfaceServeurClient serveur) throws RemoteException {
	}

	/**
	 * setter des clients
	 * @param client
	 */
	public void setListeClient(ArrayList<InterfaceServeurClient> client) {
		this.clients = client;
	}

	/**
	 * Fonction qui permet d'envoyer des données du serveur aux clients
	 */
	public void send(Action action, int idClient) throws RemoteException {
		System.out.println("Action envoyée par le client " + idClient);
		action.executer();
		for(int i = 0; i < this.clients.size(); i++)
		{
			System.out.println("Client testé : " + this.getClients().get(i).getIdObjetPartie());
			System.out.println("idClient envoyé : " + idClient);
			if(this.getClients().get(i).getIdObjetPartie() != idClient)
			{
				System.out.println("J'envoie au client");
				this.getClients().get(i).send(action, idClient);
			}
		}
	}

	/**
	 * Fonction qui ajoute les clients au serveur
	 * @param client
	 */
	public void ajouterClient(InterfaceServeurClient client) throws RemoteException {
		System.out.println("on aJoute Un client");
		if(this.clients.size() < 4)
		{
			client.setIdObjetPartie(this.clients.size());
			this.clients.add(client);
			Joueur joueur = new Joueur();
			joueur.setNom(client.getNomJoueur());
			client.setJoueur(joueur);

			for(InterfaceServeurClient c : this.clients){
				c.notifierChangement(joueur);
			}
		}
	}

	public boolean retirerClient(InterfaceServeurClient client) throws RemoteException {
		boolean trouve = this.clients.remove(client);

		for(InterfaceServeurClient c : this.clients){
			c.notifierChangement(null);
		}

		return trouve;
	}

	/**
	 * getter de la partie
	 * @return partie
	 */
	public Partie getPartie() throws RemoteException {
		return this.partie;
	}

	/**
	 * getter du nom dujoueur
	 * @return nomJoueur
	 */
	public String getNomJoueur() throws RemoteException {
		return "Serveur";
	}

	/**
	 * getter du serveur
	 * @return serveur
	 */
	public InterfaceServeurClient getServeur() throws RemoteException {
		return this;
	}

	/**
	 * getter de l'idObjet
	 * @return idClient
	 */
	public int getIdObjetPartie() throws RemoteException {
		return 0;
	}

	/**
	 * setter de l'idObjet
	 * @parem idObjet
	 */
	public void setIdObjetPartie(int idObjetPartie) throws RemoteException {
		this.idServeur = idObjetPartie;
	}

	public Joueur getJoueur() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public void switchJoueurEstPret(InterfaceServeurClient client) throws RemoteException{
		InterfaceServeurClient local = null;

		for(InterfaceServeurClient c : this.clients){
			if(c.getIdObjetPartie() == client.getIdObjetPartie()){
				local = c;
				break;
			}
		}

		local.switchJoueurPret();

		for(InterfaceServeurClient c : this.clients){
			c.notifierChangement(null);
		}
	}

	public void sendDynastieChoisi(String dynastie, int idClient) throws RemoteException {
		System.out.println("J'ai recu la dynastie :" + dynastie + " du client :" + idClient);
		for(int i = 0 ; i < this.listeDynastie.size(); i++)
		{
			Dynastie dynastieTest = this.listeDynastie.get(i);
			if(dynastieTest.getNom().equals(dynastie))
			{
				dynastieTest.setEstPrise(true);
			}

		}

		for(int i = 0; i < this.clients.size(); i++)
		{
			InterfaceServeurClient client = this.clients.get(i);
			if(client.getIdObjetPartie() != idClient)
			{
				client.sendDynastieChoisi(dynastie, idClient);
			}
		}
		/*
		for(InterfaceServeurClient c : this.clients){
			c.notifierChangement(null);
		}*/
	}

	public void setListeDynastie(ArrayList<Dynastie> liste) throws RemoteException {
		this.listeDynastieDispo = liste;

	}

	public void notifierChangement() throws RemoteException {
		// TODO Auto-generated method stub

	}

	public void addListener(ChangeListener listener) throws RemoteException {
		// TODO Auto-generated method stub

	}

	public void removeListener(ChangeListener listener) throws RemoteException {
		// TODO Auto-generated method stub

	}

	public void notifierChangement(Object arg) throws RemoteException {
		// TODO Auto-generated method stub

	}

	public boolean deconnecter() throws RemoteException {
		return false;
	}

	public void switchJoueurPret() throws RemoteException {
	}

	public void setJoueur(Joueur joueur) throws RemoteException {
	}

	public void setPartieCourante(Partie partie) throws RemoteException {
		this.partie = partie;
	}

	public void send(String string, int idClient) throws RemoteException {
		// TODO Auto-generated method stub

	}
}
