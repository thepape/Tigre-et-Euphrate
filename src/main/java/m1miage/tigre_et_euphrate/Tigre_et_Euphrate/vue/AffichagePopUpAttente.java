package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.vue;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AffichagePopUpAttente extends Application {

	/**
	 * Le stage de la PopUp d'attente
	 */
	private Stage primaryStage;

	/**
	 * BorderPane qui contient les éléments de l'interface
	 */
	private BorderPane rootLayout;

	public AffichagePopUpAttente() {
		super();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Attente de connection");
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("FenetreAttenteConnection.fxml"));
        rootLayout = (BorderPane) loader.load();

        Scene scene = new Scene(rootLayout);

        primaryStage.setScene(scene);

	}

	public void afficher()
	{
		primaryStage.show();
	}

	public void fermer()
	{
		primaryStage.close();
	}
}
