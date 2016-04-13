package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.vue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.DeckPrive;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.DeckPublic;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.PartieInterface;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Dynastie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.TypeChef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TypeTuileCivilisation;


public class MainApp extends Application {

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
     * Le joueur courant de la partie
     */
    private ObservableList<PartieInterface> joueur = FXCollections.observableArrayList();

    /**
     * Serveur auquel est connecté le client si client est, sinon à null
     */
    private PartieInterface serveur;

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Tigre et Euphrate");

        this.afficherMenuDepart();

	}

	/**
	 * Fonction qui affiche le menu principal
	 */
	public void afficherMenuDepart()
	{
		try {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("MenuDepart.fxml"));
	        rootLayout = (BorderPane) loader.load();

	        ControleurCreationPartie controleur = loader.getController();
	        controleur.setMainApp(this);

	        Scene scene = new Scene(rootLayout);

            primaryStage.setScene(scene);

            primaryStage.show();

		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Fonction qui initialise le plateau de jeu et fait le lien entre la vue et le controleur
	 */
	public void initRootLayout() {
        try {
        	System.out.println(this.joueur.get(0).getName());
        	primaryStage.hide();
        	primaryStage = new Stage();
    		primaryStage.setTitle("Tigre et Euphrate : " + this.joueur.get(0).getName());
		    FXMLLoader loader = new FXMLLoader();
		    loader.setLocation(MainApp.class.getResource("ApplicationPrincipale.fxml"));
		    rootLayout = (BorderPane) loader.load();

		    //Simuation d'un joueur pour vérifier l'affichage
		    TuileCivilisation tuile1 = new TuileCivilisation(TypeTuileCivilisation.Ferme);
		    TuileCivilisation tuile2 = new TuileCivilisation(TypeTuileCivilisation.Marché);
		    TuileCivilisation tuile3 = new TuileCivilisation(TypeTuileCivilisation.Population);
		    TuileCivilisation tuile4 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		    TuileCivilisation tuile5 = new TuileCivilisation(TypeTuileCivilisation.Ferme);
		    TuileCivilisation tuile6 = new TuileCivilisation(TypeTuileCivilisation.Ferme);
		    DeckPrive deckPrive = new DeckPrive();
		    deckPrive.ajouter(tuile1);
		    deckPrive.ajouter(tuile2);
		    deckPrive.ajouter(tuile3);
		    deckPrive.ajouter(tuile4);
		    deckPrive.ajouter(tuile5);
		    deckPrive.ajouter(tuile6);

		    Chef chefFermier = new Chef(TypeChef.Fermier);
		    Chef chefRoi = new Chef(TypeChef.Roi);
		    Chef chefMarchand = new Chef(TypeChef.Marchand);
		    Chef chefPretre = new Chef(TypeChef.Pretre);
		    DeckPublic deckPublic = new DeckPublic();
		    deckPublic.ajouter(chefFermier);
		    deckPublic.ajouter(chefRoi);
		    deckPublic.ajouter(chefMarchand);
		    deckPublic.ajouter(chefPretre);

		    Joueur joueur = this.getListeJoueur().get(0).getJoueur();
		    joueur.setDeckPrive(deckPrive);
		    joueur.setDeckPublic(deckPublic);
		    joueur.setDynastie(Dynastie.Lanister);

		    ControleurPlateau controleurPlateau = loader.getController();
		    controleurPlateau.setMainApp(this);

		    Scene scene = new Scene(rootLayout);

		    primaryStage.setScene(scene);
		    primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	/**
	 * Fonction qui adapte l'affichage du serveur en fonction des adversaires qui se sont connectés ou non.
	 * 0 adversaire : retour au menu de départ.
	 * 1 ou + adversaires : affichage du jeu global
	 */
	public void verifierAdversaire()
	{
		try
		{
			popUpStage.hide();
			if(this.getListeJoueur().get(0).getListePartie().size() == 0)
			{
				this.afficherMenuDepart();
			} else {
				this.initRootLayout();
			}
		}catch(RemoteException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * Fonction affiche la PopUp d'attente de connexion
	 */
	public void afficherPopUpAttente()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("FenetreAttenteConnection.fxml"));
	        rootLayout = (BorderPane) loader.load();
	        Scene scene = new Scene(rootLayout);

	        popUpStage.setScene(scene);
	        primaryStage.hide();
	        popUpStage.show();

	        ControleurFenetreAttente controleur = loader.getController();
	        controleur.setMainApp(this);
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * getter de la liste des observables joueurs
	 * @return listeJoueur
	 */
	public ObservableList<PartieInterface> getListeJoueur() {
		return joueur;
	}

	/**
	 * setter de la liste des joueur
	 * @param listeJoueur
	 */
	public void setListeJoueur(ObservableList<PartieInterface> listeJoueur) {
		this.joueur = listeJoueur;
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
	 * getter du serveur
	 * @return serveur
	 */
	public PartieInterface getServeur() {
		return serveur;
	}

	/**
	 * setter du serveur
	 * @param serveur
	 */
	public void setServeur(PartieInterface serveur) {
		this.serveur = serveur;
	}

	/**
	 * Main qui lance le projet
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}


}
