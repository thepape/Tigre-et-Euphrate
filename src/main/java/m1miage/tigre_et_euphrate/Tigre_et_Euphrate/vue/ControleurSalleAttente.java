package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.vue;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Dynastie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.connexion.Client;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.connexion.EncoderJSON;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.connexion.InterfaceServeurClient;
import javafx.collections.ListChangeListener;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.WindowEvent;
import javafx.collections.*;
import javafx.scene.control.ListView;

/**
 * Controlleur de la salle d'attente
 * @author
 *
 */
public class ControleurSalleAttente implements ChangeListener {

	/**
	 * Application gérer par ce controleur
	 */
	private MainApp mainApp;

	/**
	 * Recupere la partie chargée
	 */
	private Partie partieChargee = null;

	/**
	 * Une liste d'observable des joueurs
	 */
	public ObservableList<Joueur> joueurs = FXCollections.observableArrayList();

	/**
	 * Une liste d'observable du nom des joueurs
	 */
	public ObservableList<String> nomJoueurs = FXCollections.observableArrayList();

	/**
	 * Une Hashmap pour les buttons liés au dynastie
	 */
	public HashMap<Dynastie, Button> boutonsDynastie = new HashMap<Dynastie, Button>();

	/**
	 * Une liste de Dynastie
	 */
	private ArrayList<Dynastie> dynastiesDispo = new ArrayList<Dynastie>();

	//Bouton des differentes dynasties
		/**
		 * Bouton de la dynastie lanister
		 */
		@FXML
		private Button lanister;

		/**
		 * Bouton de la dynastie stark
		 */
		@FXML
		private Button stark;

		/**
		 * Bouton de la dynastie targaryen
		 */
		@FXML
		private Button targaryen;

		/**
		 * Bouton de la dynastie tyrell
		 */
		@FXML
		private Button tyrell;

		@FXML
		private ListView listeJoueur;
		
		@FXML
		private Button BtnPret;
		
		private boolean dynastiechoisie = false;

	public MainApp getMainApp() {
		return mainApp;
	}


	/**
	 * Methode pour set le MainApp avec les dynasties
	 * @param mainApp
	 * @throws RemoteException
	 */
	public void setMainApp(MainApp mainApp) throws RemoteException {
		this.mainApp = mainApp;
		Client client = (Client)this.mainApp.getClient();
		this.boutonsDynastie.put(Dynastie.Lanister, this.lanister);
		this.boutonsDynastie.put(Dynastie.Stark, this.stark);
		this.boutonsDynastie.put(Dynastie.Tyrell, this.tyrell);
		this.boutonsDynastie.put(Dynastie.Targaryen, this.targaryen);
		
		this.mainApp.getPrimaryStage().setOnCloseRequest(new EventHandler<WindowEvent>(){

			public void handle(WindowEvent arg0) {
				quitterJeu();
			}
			
		});
	}




	/**
	 * Constructeur
	 */
	public ControleurSalleAttente() {
	}

	/**
	 * Methode pour savoir si le client s'est bien deconnecter
	 * @return
	 */
	private boolean deconnecterClient(){
		//DECONNEXION
				InterfaceServeurClient client = MainApp.getInstance().getClient();
				boolean deconnexionOK = false;
				try {
					deconnexionOK = client.deconnecter();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return deconnexionOK;
	}

	// Fonctions

	/**
	 * Methode permettant de retourner au menu grace au bouton retour
	 */
	@FXML
	public void retourAuMenu(){
		Dynastie d;
		try {
			d = MainApp.getInstance().getClient().getJoueur().getDynastie();
			MainApp.getInstance().getServeur().libererDynastie(d);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			MainApp.getInstance().goToMenuPage();
		}

		this.deconnecterClient();

		MainApp.getInstance().goToMenuPage();
	}
	
	public void quitterJeu(){
		Dynastie d;
		try {
			d = MainApp.getInstance().getClient().getJoueur().getDynastie();
			MainApp.getInstance().getServeur().libererDynastie(d);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.deconnecterClient();
		
		System.exit(0);
	}

	/**
	 * Methode permettant de mettre un joueur a pret
	 */
	@FXML
	public void handleBoutonPret(){
		InterfaceServeurClient serveur =  this.mainApp.getServeur();
		InterfaceServeurClient client = this.mainApp.getClient();
		try {
			if(dynastiechoisie){//faire des trucs
				serveur.switchJoueurEstPret(client);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Methode pour assigner une dynastie a un joueur
	 * @param dynastie
	 */
	public void handleChoixDynastie(Dynastie dynastie){
		InterfaceServeurClient serveur =  this.mainApp.getServeur();
		InterfaceServeurClient client = this.mainApp.getClient();

		try {
			serveur.setDynastieOfClient(client, dynastie);
			dynastiechoisie = true;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	/**
	 * Fonction de mis a jour lorsqu'une dynastie est choisi
	 */
	@FXML
	public void dynastieChoisi(Event event) throws RemoteException {

		Client client = (Client) this.mainApp.getClient();
		if(client.getJoueur().getDynastie() == null)
		{
			Button pButton = (Button) event.getSource();
			pButton.setDisable(true);
			Dynastie dynastie = null;
			if(pButton.getId().equals("lanister"))
			{
				dynastie = Dynastie.Lanister;
			} else if(pButton.getId().equals("stark"))
			{
				dynastie = Dynastie.Stark;
			} else if(pButton.getId().equals("tyrell"))
			{
				dynastie = Dynastie.Tyrell;
			} else if(pButton.getId().equals("targaryen"))
			{
				dynastie = Dynastie.Targaryen;
			}

			this.handleChoixDynastie(dynastie);
		}
	}
	
	/**
	 * Fonction permettant d'activer ou desactiver le bouton pret en fonction du nombre de joueurs présents dans la partie
	 */
	public void joueursSuffisants(){
		if(this.nomJoueurs.size() > 1 && this.nomJoueurs.size() < 5)
			this.BtnPret.setDisable(false);
		else
			this.BtnPret.setDisable(true);
	}
	

	public void ajouterJoueurDansListe(Joueur j){
		this.joueurs.add(j);
	}

	public void retirerJoueurDansListe(Joueur j){
		this.joueurs.remove(j);
	}

	/**
	 * Methode permettant de mettre a jour le salon
	 */
	public void majSalon(){
		try {
			this.majListeJoueur();
			this.majListeDynasties();
			this.joueursSuffisants();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Methode permettant de mettre a jour la liste des joueurs de la partie
	 * @throws RemoteException
	 */
	public void majListeJoueur() throws RemoteException{

		ObservableList<String> items = FXCollections.observableArrayList();
		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		InterfaceServeurClient client = MainApp.getInstance().getClient();
		InterfaceServeurClient serveur = MainApp.getInstance().getServeur();
		ArrayList<InterfaceServeurClient> clients = serveur.getClients();

		for(InterfaceServeurClient i: clients){
			try {
				Joueur j = i.getJoueur();
				Dynastie d = j.getDynastie();
				boolean pret = j.estPret();
				String n = i.getNomJoueur();

				if(d != null){
					n += " - "+d.getNom();
				}

				if(pret){
					n = n + " [PRET]";
				}

				items.add(n);
				System.out.println(n);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		this.nomJoueurs = items;

		Platform.runLater(new Runnable(){

			public void run() {
				if(MainApp.getInstance().currentControler instanceof ControleurSalleAttente){
					((ControleurSalleAttente) MainApp.getInstance().currentControler).majListeJoueurJAVAFX();

				}

			}

		});
	}

	/**
	 * Permet de mettre a jour la liste des joueurs en JavaFX
	 */
	public void majListeJoueurJAVAFX(){
		this.listeJoueur.setItems(this.nomJoueurs);
	}

	/**
	 * Permet de mettre a jour la liste des dynasties dynamiquement
	 */
	public void majListeDynasties(){
		InterfaceServeurClient serveur = MainApp.getInstance().getServeur();
		try {
			dynastiesDispo = serveur.getListeDynastieDispo();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//JAVAFX
		Platform.runLater(new Runnable(){

			public void run() {
				if(MainApp.getInstance().currentControler instanceof ControleurSalleAttente){
					((ControleurSalleAttente) MainApp.getInstance().currentControler).majListeDynastiesJAVAFX();
				}


			}

		});
	}

	/**
	 * Permet de mettre a jour la liste des dynasties en JavaFX
	 */
	public void majListeDynastiesJAVAFX(){

			Iterator<Entry<Dynastie, Button>> ite = this.boutonsDynastie.entrySet().iterator();

			while(ite.hasNext()){
				Entry<Dynastie, Button> entry =  ite.next();
				Dynastie dynastie = entry.getKey();
				Button bouton = entry.getValue();

				if(dynastiesDispo.contains(dynastie)){
					bouton.setDisable(false);
				}
				else{
					bouton.setDisable(true);
				}
			}
	}

	/**
	 * ¨Methode permetant de charger la partie que l'on a precedemment sauvegarder
	 * @throws IOException
	 */
	@FXML
	public void chargerPartie() throws IOException
	{
		File file = new File("partieEnCours.json");
		if(file.exists())
		{
			EncoderJSON e = new EncoderJSON();
			Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
			this.partieChargee = e.convertToPartie(file);
		}

		this.afficherPlateau();
	}

	/**
	 * Methode permettant d'afficher le plateau et tout ce qu'il contient a un joueur
	 * @throws RemoteException
	 */
	@FXML
	public void afficherPlateau() throws RemoteException {
		if(this.partieChargee != null)
		{

			MainApp.getInstance().getServeur().setPartieCourante(this.partieChargee);
			MainApp.getInstance().getServeur().chargerPartie();
			for(int i = 0; i < MainApp.getInstance().getServeur().getClients().size(); i++)
			{
				if(MainApp.getInstance().getServeur().getClients().get(i).getJoueur().getDynastie().getNom().equals(MainApp.getInstance().getClient().getJoueur().getDynastie().getNom()))
				{
					MainApp.getInstance().getClient().setJoueur(MainApp.getInstance().getServeur().getClients().get(i).getJoueur());
				}
			}
		}

		Platform.runLater(new Runnable(){
			public void run() {
				// TODO Auto-generated method stub
				MainApp.getInstance().afficherPlateau();
			}

		});

	}

	/**
	 * Methode permettant de savoir si la partie a été generee ou non 
	 * @return
	 * @throws RemoteException
	 */
	public boolean partieEstGeneree() throws RemoteException{
		InterfaceServeurClient c = this.mainApp.getServeur();
		Partie p = c.getPartie();
		boolean ok = p.IsEstLancee();
		if(ok){
			return true;
		}
		return false;
	}

	/**
	 * Methode permettant de changer dynamiquement les clients
	 */
	public void changed(ObservableValue arg0, Object arg1, Object arg2) {
		String arg = null;

		if(arg2 == null || !(arg2 instanceof ArrayList)){
			return;
		}

		ArrayList<Object> params = (ArrayList<Object>) arg2;

					for(int i = 0; i < params.size(); i++){
						Object param = params.get(i);

						if(param != null && param instanceof String){
							if(param.equals("partieLancee")){
								try
								{
									this.afficherPlateau();
								} catch(RemoteException e)
								{
									e.printStackTrace();
								}
							}
							if(param.equals("refreshSalon")){
								this.majSalon();
							}
						}

					}
	}
}
