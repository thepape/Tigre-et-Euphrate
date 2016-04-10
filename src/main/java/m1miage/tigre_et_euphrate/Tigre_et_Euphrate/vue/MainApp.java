package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.vue;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.DeckPrive;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.DeckPublic;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
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
	 * Vision principale de l'application
	 */
    private BorderPane rootLayout;

    /**
     * Liste des observables parties
     */
    //private ObservableList<Partie> listeJoueur = FXCollections.observableArrayList();
    private ObservableList<Joueur> listeJoueur = FXCollections.observableArrayList();

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Tigre et Euphrate");

        initRootLayout();

	}

	/**
	 * Fonction qui initialise le plateau de jeu et fait le lien entre la vue et le controleur
	 */
	public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("ApplicationPrincipale.fxml"));
            rootLayout = (BorderPane) loader.load();

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
    		listeJoueur.add(joueur);

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


	/**
	 * getter de la liste des observables joueurs
	 * @return listeJoueur
	 */
	public ObservableList<Joueur> getListeJoueur() {
		return listeJoueur;
	}

	/**
	 * setter de la liste des joueur
	 * @param listeJoueur
	 */
	public void setListeJoueur(ObservableList<Joueur> listeJoueur) {
		this.listeJoueur = listeJoueur;
	}

	/**
	 * Main qui lance le projet
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
