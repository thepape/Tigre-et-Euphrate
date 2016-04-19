package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.vue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.connexion.*;
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
    private ObservableList<PartieInterface> joueurs = FXCollections.observableArrayList();

    /**
     * instance de l'application en cours d'execution
     */
    private static MainApp instance;

    private InterfaceServeurClient serveur;

    private InterfaceServeurClient client;

    private FXMLLoader currentLoader;
    
	ArrayList<Dynastie> listeDynastieDispo = new ArrayList<Dynastie>();
	
	private ObservableList<Dynastie> listeDynastie;
    /**
     * retourne l'instance unique de l'application en cours d'execution
     * @return
     */
    public static MainApp getInstance()
	{
		return MainApp.instance;
	}

	@Override
	public void start(Stage primaryStage) {
		/*listeDynastieDispo.add(Dynastie.Lanister);
		listeDynastieDispo.add(Dynastie.Stark);
		listeDynastieDispo.add(Dynastie.Targaryen);
		listeDynastieDispo.add(Dynastie.Tyrell);*/
		
		MainApp.instance = this;
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
        	try
    		{
        		popUpStage.hide();
    			if(this.getListeJoueur().size() == 0)
    			{
    				this.afficherMenuDepart();
    			} else {
    				primaryStage = new Stage();
    				primaryStage.setTitle("Tigre et Euphrate : vous êtes le joueur hébergeur");
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
		    		PartieInterface partie = (Partie) this.getListeJoueur().get(0);
		    		partie.setJoueur(joueur);
		    		this.joueurs.add(partie);


		    		ControleurPlateau controleurPlateau = loader.getController();
		            controleurPlateau.setMainApp(this);

		            Scene scene = new Scene(rootLayout);

		            primaryStage.setScene(scene);
		            primaryStage.show();
    			}
    		} catch(RemoteException exp)
    		{
    				exp.printStackTrace();
    		}
        } catch (IOException e) {
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
	        System.out.println(this.getListeJoueur());
	        Scene scene = new Scene(rootLayout);

	        popUpStage.setScene(scene);
	        primaryStage.hide();
	        popUpStage.show();

	        ControleurFenetreAttente controleur = loader.getController();
	        controleur.setMainApp(this);
	        System.out.println(controleur.getMainApp());
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
		return joueurs;
	}

	/**
	 * setter de la liste des joueur
	 * @param listeJoueur
	 */
	public void setListeJoueur(ObservableList<PartieInterface> listeJoueur) {
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

	public InterfaceServeurClient getServeur() {
		return serveur;
	}

	public void setServeur(InterfaceServeurClient serveur) {
		this.serveur = serveur;
	}

	public InterfaceServeurClient getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	private Parent replaceSceneContent(String fxml) throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource(fxml));

		//FXMLLoader l = new FXMLLoader(getClass().getClassLoader().getResource(fxml));
		this.currentLoader = loader;
		Parent page = loader.load();



		//Parent page = (Parent) this.fxmLoader.load(getClass().getClassLoader().getResource(fxml), null, new JavaFXBuilderFactory());
		Scene scene = this.primaryStage.getScene();

		if(scene == null)
		{
			scene = new Scene(page, 600,400);
			scene.getStylesheets().add(App.class.getResource("application.css").toExternalForm());
			this.primaryStage.setScene(scene);
		}
		else
		{
			scene.setRoot(page);
		}

		return page;
	}

	public void goToHebergerPartiePage() {
		try {
			this.replaceSceneContent("CreerPartie.fxml");


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ObservableList<Dynastie> getListeDynastie() {
		return listeDynastie;
	}

	public void setListeDynastie(ObservableList<Dynastie> listeDynastie) {
		this.listeDynastie = listeDynastie;
	}

	public void goToMenuPage(){
		try {
			this.replaceSceneContent("MenuDepart.fxml");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void goToRejoindrePartiePage() {
		try {
			this.replaceSceneContent("RejoindrePartie.fxml");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void goToSalon(){
		try{
			this.replaceSceneContent("Salleattente.fxml");
			listeDynastie = FXCollections.observableArrayList(this.getServeur().getListeDynastie());
			MainApp.getInstance().getListeDynastie().addListener(new ListChangeListener<Dynastie>() {
			      public void onChanged(ListChangeListener.Change change) {
			        System.out.println("change!");
			      }
			    });
			this.client.setListeDynastie(this.getServeur().getListeDynastie());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void afficherPlateau(){
		try{
			this.replaceSceneContent("ApplicationPrincipale.fxml");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
