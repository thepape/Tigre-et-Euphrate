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
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action.Action;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action.PlacerChef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Dynastie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.conflit.Conflits;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;

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


	private int increment = 0;


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


	}

	public ArrayList<Dynastie> getListeDynastie() throws RemoteException {
		return listeDynastieDispo;
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
		partie = new Partie();
		partie.setServeur(this);

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
			Naming.rebind("rmi://127.0.0.1:"+this.port+"/"+this.namespace, this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Serveur lancé !");

		this.lance = true;
	}

	/**
	 * Fonction qui permet d'attendre des joueurs
	 * @throws RemoteException
	 */
	public void attendreJoueursPrets() throws RemoteException
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

		try {
			this.attendreJoueursPrets();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


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
		action.setPartie(this.partie);
		action.executer();
		
		for(int i = 0; i < this.clients.size(); i++)
		{
			Joueur joueurConcerne = this.clients.get(i).getJoueur();
			if(joueurConcerne.getId() == action.getJoueur().getId())
			{
				this.clients.get(i).setJoueur(action.getJoueur());
			}
		}

		for(InterfaceServeurClient c : this.clients){
			ArrayList<Object> params = new ArrayList<Object>();
			params.add("partie");
			params.add("plateau");
			params.add("deckPrive");
			params.add("deckPublic");
			params.add("message:"+action.toString()+".");
			
			if(action instanceof PlacerChef && ((PlacerChef) action).isConflit()){
				PlacerChef pc = ((PlacerChef) action);
				Conflits conflit = pc.getConflit();
				
				params.add("conflitInterne");
				params.add("message:Conflit entre "+conflit.getChefAttaquant().getJoueur().getNom()+" et "+conflit.getChefDefenseur().getJoueur().getNom());
			}
			
			c.notifierChangement(params);
		}
	}

	/**
	 * Fonction qui ajoute les clients au serveur
	 * @param client
	 */
	public void ajouterClient(InterfaceServeurClient client) throws RemoteException {
		if(this.clients.size() < 4)
		{
			client.setIdObjetPartie(this.clients.size());
			this.clients.add(client);
			Joueur joueur = new Joueur();
			joueur.setNom(client.getNomJoueur());
			client.setJoueur(joueur);

			for(InterfaceServeurClient c : this.clients){
				ArrayList<Object> params = new ArrayList<Object>();
				params.add("refreshSalon");
				c.notifierChangement(params);
			}
		}
	}

	/**
	 * Fonction qui retire un joueur de la liste des clients
	 */
	public boolean retirerClient(InterfaceServeurClient client) throws RemoteException {
		boolean trouve = this.clients.remove(client);

		for(InterfaceServeurClient c : this.clients){
			c.notifierChangement(null);
		}

		return trouve;
	}
	
	public void envoyerRenforts(ArrayList<TuileCivilisation> renforts, Joueur joueur) throws RemoteException{
		Conflits conflit = this.partie.getConflits().get(0);
		
		//si le joueur est l'attaquant
		if(joueur.getId() == conflit.getChefAttaquant().getJoueur().getId()){
			conflit.setListeTuileRenfortAttaquant(renforts);
		}
		else if(joueur.getId() == conflit.getChefDefenseur().getJoueur().getId()){
			conflit.setListeTuileRenfortDefenseur(renforts);
		}
		
		//on regarde si les deux joueurs ont donné leurs renforts pour résoudre le conflit
		if(conflit.getListeTuileRenfortAttaquant() != null && conflit.getListeTuileRenfortDefenseur() != null){
			if(conflit.getTypeConflit().equals("I")){
				conflit.setPartie(this.partie);
				this.resoudreConflitInterne(conflit);
				
			}
		}
	}
	
	public Chef resoudreConflitInterne(Conflits conflit) throws RemoteException{
		Chef gagnant = conflit.definirChefGagnant();
		
		//on met à jour less joueurs des clients concernés par le conflit
		for(InterfaceServeurClient client : this.getClients()){
			Joueur joueurCli = client.getJoueur();
			
			if(joueurCli.getId() == conflit.getChefAttaquant().getJoueur().getId()){
				client.setJoueur(conflit.getChefAttaquant().getJoueur());
			}
			else if(joueurCli.getId() == conflit.getChefDefenseur().getJoueur().getId()){
				client.setJoueur(conflit.getChefDefenseur().getJoueur());
			}
		}
		
		ArrayList<Object> params = new ArrayList<Object>();
		params.add("partie");
		params.add("plateau");
		params.add("deckPrive");
		params.add("deckPublic");
		params.add("conflitInterneResolu");
		params.add("message:Le joueur "+gagnant.getJoueur().getNom()+" a gagné le conflit !");
		this.notifierClient(params);
		
		this.partie.getConflits().remove(conflit);
		
		return gagnant;
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

	/**
	 * Fonction qui retourne le client qui heberge la partie
	 * @param client
	 * @return localClient
	 * @throws RemoteException
	 */
	private InterfaceServeurClient getLocalClient(InterfaceServeurClient client) throws RemoteException{

		for(InterfaceServeurClient c : this.clients){
			if(c.getIdObjetPartie() == client.getIdObjetPartie()){
				return c;
			}
		}

		return null;
	}

	/**
	 * Fonction qui notife le client d'un changement
	 * @param arg
	 * @throws RemoteException
	 */
	private void notifierClient(ArrayList<Object> args) throws RemoteException{
		for(InterfaceServeurClient c : this.clients){

			c.notifierChangement(args);
		}
	}

	/**
	 * Fonction qui change l'etat d'un joueur en fonction de l'envoie de ce dernier
	 */
	public void switchJoueurEstPret(InterfaceServeurClient client) throws RemoteException{
		InterfaceServeurClient local = null;

		for(InterfaceServeurClient c : this.clients){
			if(c.getIdObjetPartie() == client.getIdObjetPartie()){
				local = c;
				break;
			}
		}

		local.switchJoueurPret();
		String arg = "refreshSalon";

		//si tous les joueurs sont prets, on change l'affichage plutot
		if(this.tousPret()){
			this.genererPartie();
			System.out.println("Partie lancée");
			arg="partieLancee";
		}


		for(InterfaceServeurClient c : this.clients){
			ArrayList<Object> params = new ArrayList<Object>();
			params.add(arg);
			c.notifierChangement(params);
		}
	}

	public void libererDynastie(Dynastie dynastie) throws RemoteException{
		synchronized (this.listeDynastieDispo) {

				this.listeDynastieDispo.add(dynastie);

		}

		ArrayList<Object> params = new ArrayList<Object>();
		params.add("refreshSalon");
		this.notifierClient(params);
	}

	/**
	 * Fonction qui change la dynastie d'un client de serveur
	 */
	public boolean setDynastieOfClient(InterfaceServeurClient client, Dynastie dynastie) throws RemoteException {
		InterfaceServeurClient local = this.getLocalClient(client);
		boolean ok = false;
		synchronized (this.listeDynastieDispo) {
			if(this.listeDynastieDispo.contains(dynastie)){
				ok = true;
				this.listeDynastieDispo.remove(dynastie);
			}
		}

		if(!ok){
			return false;
		}

		local.setDynastie(dynastie);

		ArrayList<Object> params = new ArrayList<Object>();
		params.add("refreshSalon");
		this.notifierClient(params);

		return true;
	}

	/**
	 * Setter de la liste de dynastie
	 */
	public void setListeDynastie(ArrayList<Dynastie> liste) throws RemoteException {
		this.listeDynastieDispo = liste;

	}

	public void passerTour() throws RemoteException{
		this.partie.passerTour();
		/*for(InterfaceServeurClient client: this.clients){
			client.passerTour();
		}*/
		ArrayList<Object> params = new ArrayList<Object>();
		params.add("partie");
		params.add("passerTour");
		
		this.notifierClient(params);
	}
	
	public void passerTourConflit() throws RemoteException{
		this.partie.passerTourConflit();
	}
	
	public boolean piocherCartesManquantes(Joueur joueur) throws RemoteException {
		boolean res = this.partie.piocheCartesManquantes(joueur);
		
		for(InterfaceServeurClient client : this.clients){
			if(client.getJoueur().getId() == joueur.getId()){
				client.setJoueur(joueur);
				break;
			}
		}
		
		return res;
	}

	/**
	 * Fonction qui vérifie que tous les joueurs sont prêts
	 * @return pret
	 * @throws RemoteException
	 */
	public boolean tousPret() throws RemoteException{
		for(InterfaceServeurClient client : this.clients){
			if(!client.getJoueur().estPret()){
				return false;
			}
		}
		return true;
	}

	/**
	 * Fonction qui génére partie
	 */
	public void genererPartie(){
		this.partie.initialiserPartie();
	}

	public int getUniqueId() throws RemoteException{
		this.increment++;
		return this.increment;
	}
	
	public void envoyerNouveauConflit(Conflits conflit, int idClientSender) throws RemoteException{
		
		//propagation du conflit chez les partie des autres
		for(InterfaceServeurClient client : this.clients){
			if(client.getIdObjetPartie() != idClientSender){
				client.envoyerNouveauConflit(conflit, idClientSender);
			}
		}
		
		
	}



	/**********************************************************************************
	 * 						FONCTIONS QUE LE SERVEUR N'UTILISE PAS
	 **********************************************************************************/
	public void setJoueur(Joueur j) throws RemoteException {
		// TODO Auto-generated method stub

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

	public boolean deconnecter() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	public void switchJoueurPret() throws RemoteException {
		// TODO Auto-generated method stub

	}

	public void setDynastie(Dynastie d) throws RemoteException {
		// TODO Auto-generated method stub

	}

	public void setPartieCourante(Partie partie) throws RemoteException {
		// TODO Auto-generated method stub

	}

	public void clearListeners() throws RemoteException {
		// TODO Auto-generated method stub

	}

	public void notifierChangement(ArrayList<Object> args) throws RemoteException {
		// TODO Auto-generated method stub

	}

}

