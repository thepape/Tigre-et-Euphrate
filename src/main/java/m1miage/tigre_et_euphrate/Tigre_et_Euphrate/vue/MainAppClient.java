package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.vue;


import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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

public class MainAppClient extends Application implements App {

	/**
	 * Stage de l'interface
	 */
	private Stage primaryStage;

	/**
	 * Vision principale de l'application
	 */
    private BorderPane rootLayout;

    /**
     * Liste contenant le joueur courant
     */
    private ObservableList<PartieInterface> joueur = FXCollections.observableArrayList();
    //private ObservableList<Joueur> listeJoueur = FXCollections.observableArrayList();

	public ObservableList<PartieInterface> getListeJoueur() {
		return joueur;
	}

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Tigre et Euphrate");

        this.afficherMenuDepart();
        //initRootLayout();

        primaryStage.show();

	}

	/**
	 * Fonction qui affiche le menu principal
	 */
	public void afficherMenuDepart()
	{
		try {
			// Load root layout from fxml file.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("MenuDepart.fxml"));
	        rootLayout = (BorderPane) loader.load();

	        ControleurCreationPartie controleur = loader.getController();
	        controleur.setMainApp(this);

	        Scene scene = new Scene(rootLayout);

            primaryStage.setScene(scene);

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
        	primaryStage = new Stage();
            // Load root layout from fxml file.
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

    		Joueur joueur = new Joueur("joueur test", Dynastie.Lanister, deckPublic, deckPrive);
    		PartieInterface partie = new Partie();
    		partie.setJoueur(joueur);
    		this.joueur.add(partie);

    		ControleurPlateau controleurPlateau = loader.getController();
            controleurPlateau.setMainApp(this);

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);

            primaryStage.setScene(scene);

            primaryStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public void cacherMenu()
	{
		this.primaryStage.hide();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public Stage getPrimaryStage() {
		return this.primaryStage;
	}

}
