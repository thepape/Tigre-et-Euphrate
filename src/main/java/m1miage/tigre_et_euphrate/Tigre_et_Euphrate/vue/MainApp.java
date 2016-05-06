package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.vue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.DeckPrive;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.DeckPublic;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Plateau;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Dynastie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.TypeChef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.connexion.*;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.Monument;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TypeTuileCivilisation;

public class MainApp extends Application implements App {

	/**
	 * Stage de l'interface
	 */
	private Stage primaryStage;

	/**
	 * Stage de la popUp d'attente
	 */
	private Stage popUpStage = new Stage();

	/**
	 * Vision principale de l'application
	 */
	private BorderPane rootLayout;

	/**
	 * Liste des observables parties
	 */
	private ObservableList<Partie> joueurs = FXCollections.observableArrayList();

	/**
	 * instance de l'application en cours d'execution
	 */
	private static MainApp instance;

	private InterfaceServeurClient serveur;

	private InterfaceServeurClient client;

	private FXMLLoader currentLoader;

	public Object currentControler;
	
	public Exception currentException;

	ArrayList<Dynastie> listeDynastieDispo = new ArrayList<Dynastie>();

	// private ObservableList<Dynastie> listeDynastie;

	/**
	 * retourne l'instance unique de l'application en cours d'execution
	 *
	/**
	 * retourne l'instance unique de l'application en cours d'execution
	 *
	 * @return
	 */
	public static MainApp getInstance() {
		return MainApp.instance;
	}



	@Override
	public void start(Stage primaryStage) {
		listeDynastieDispo.add(Dynastie.Lanister);
		listeDynastieDispo.add(Dynastie.Stark);
		listeDynastieDispo.add(Dynastie.Targaryen);
		listeDynastieDispo.add(Dynastie.Tyrell);
		MainApp.instance = this;
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Tigre et Euphrate");

		this.afficherMenuDepart();
		// this.initRootLayout();

	}

	/**
	 * Fonction qui affiche le menu principal
	 */
	public void afficherMenuDepart() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("MenuDepart.fxml"));
			rootLayout = (BorderPane) loader.load();

			ControleurCreationPartie controleur = loader.getController();
			controleur.setMainApp(this);

			Scene scene = new Scene(rootLayout);

			primaryStage.setScene(scene);

			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * getter de la liste des observables joueurs
	 * @return listeJoueur
	 */
	public ObservableList<Partie> getListeJoueur() {
		return joueurs;
	}

	/**
	 * setter de la liste des joueur
	 * @param listeJoueur
	 */
	public void setListeJoueur(ObservableList<Partie> listeJoueur) {
		this.joueurs = listeJoueur;
	}

	/**
	 * getter de la primaryStage
	 * @return primaryStage
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	/**
	 * setter de la primaryStage
	 * @param primaryStage
	 */
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	/**
	 * Main qui lance le projet
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Getter du serveur
	 */
	public InterfaceServeurClient getServeur() {
		return serveur;
	}

	/**
	 * Setter du serveur
	 */
	public void setServeur(InterfaceServeurClient serveur) {
		this.serveur = serveur;
	}

	/**
	 * Getter du client
	 * @return
	 */
	public InterfaceServeurClient getClient() {
		return client;
	}

	/**
	 * Setter du client
	 * @param client
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	public void setClient(InterfaceServeurClient client) {
		this.client = client;
	}

	public void setPartieJoueur(Partie partie)
	{
		try
		{
			MainApp.getInstance().getClient().setPartieCourante(partie);
		} catch(RemoteException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Fonction qui remplace le contenu de la fenetre principale
	 * @param fxml
	 * @return
	 * @throws Exception
	 */
	private Parent replaceSceneContent(String fxml) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource(fxml));

		// FXMLLoader l = new
		// FXMLLoader(getClass().getClassLoader().getResource(fxml));
		this.currentLoader = loader;
		Parent page = loader.load();
		this.currentControler = this.currentLoader.getController();
		// Parent page = (Parent)
		// this.fxmLoader.load(getClass().getClassLoader().getResource(fxml),
		// null, new JavaFXBuilderFactory());
		Scene scene = this.primaryStage.getScene();
		if(fxml.equals("Salleattente.fxml"))
		{
			ControleurSalleAttente controleur = loader.getController();
			controleur.setMainApp(this);
		}

		if (scene == null) {
			scene = new Scene(page, 600, 400);
			scene.getStylesheets().add(App.class.getResource("application.css").toExternalForm());
			this.primaryStage.setScene(scene);
		} else {
			scene.setRoot(page);
		}

		return page;
	}

	/**
	 * Fonction qui affiche la page d'hebergement de partie
	 */
	public void goToHebergerPartiePage() {
		try {
			this.replaceSceneContent("CreerPartie.fxml");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	/**
	 * Getter de la liste des dynasties
	 * @return liste des dynasties
	 */
	public ArrayList<Dynastie> getListeDynastie() {
		return listeDynastieDispo;
	}

	/**
	 * Setter de la liste des dynasties
	 * @param listeDynastie
	 */
	public void setListeDynastie(ArrayList<Dynastie> listeDynastie) {
		this.listeDynastieDispo = listeDynastie;
	}

	/**
	 * Fonction qui affiche le menu principal du jeu
	 */
	public void goToMenuPage() {
		try {
			if(this.client != null)
				this.client.clearListeners();
			this.replaceSceneContent("MenuDepart.fxml");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Fonction qui affiche la page de rejoindre une partie
	 */
	public void goToRejoindrePartiePage() {
		try {
			this.replaceSceneContent("RejoindrePartie.fxml");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Fonction qui affiche le salon
	 */
	public void goToSalon() {
		try {
			this.replaceSceneContent("Salleattente.fxml");

			ControleurSalleAttente controler = (ControleurSalleAttente) this.currentLoader.getController();
			this.client.addListener(controler);
			this.currentControler = controler;
			controler.majSalon();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Fonction qui affiche l'interface d'attribution des tresors
	 */
	public void goToAttributionTresors(int nombreDeTresors){
		try{
			this.replaceSceneContent("InterfaceTresors.fxml");
			ControleurAttributionTresors controler = (ControleurAttributionTresors)this.currentControler;
			this.client.clearListeners();
			this.client.addListener(controler);
			// Le nombre de trésors que le joueur possède
			controler.initialiser(nombreDeTresors);
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Fonction qui affiche l'interface du classement
	 */
	public void goToClassement(){
		try{
			this.replaceSceneContent("InterfaceClassement.fxml");
			ControleurClassement controler = (ControleurClassement)this.currentControler;
			this.client.clearListeners();
			this.client.addListener(controler);
			controler.initialiser();
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public void afficherPopTuile() {
		//TODO
	}

	/**
	 * Fonctionq qui affiche le plateau
	 */
	public void afficherPlateau(){
		try{
			//on supprime le controleurSalon des listeners du client
			this.client.clearListeners();

			this.replaceSceneContent("ApplicationPrincipale.fxml");

			ControleurPlateau controleurPlateau = this.currentLoader.getController();
			Partie partie = this.serveur.getPartie();
			Joueur joueur = this.client.getJoueur();

			this.client.setPartieCourante(partie);
			this.client.setJoueur(joueur);

			controleurPlateau.setMainApp(this);

			//on ajoute le controleurPlateau comme listener de ce client
			this.client.addListener(controleurPlateau);
			controleurPlateau.construirePlateau();
			controleurPlateau.construireMonument();
			controleurPlateau.initialiserBoutonPasserTour();
			controleurPlateau.rafraichirAffichagePoints();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * permet de set le message d'erreur
	 */
	public void setMesageErreur(String message){
		ControleurCreationPartie cp = this.currentLoader.getController();
		cp.setMessageErreur(message);
	}

}
