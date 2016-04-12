package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.vue;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

public class ControleurFenetreAttente {

	@FXML
	private ProgressBar progressBar;

	@FXML
	private Button boutonAcceder;

	private MainApp mainApp;

	@FXML
	public void initialize()
	{
		Task task = new Task<Void>() {
		    @Override public void run() {
				boutonAcceder.setDisable(true);
				boutonAcceder.setText("Attente 30 sec...");

		        final int max = 30;
		        for (int i=1; i<=max; i++) {
		        	try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
		            updateProgress(i, max);
		        }

		        Platform.runLater(new Runnable() {
	                 public void run() {
	                	 boutonAcceder.setText("Vérifier les adversaires");
	     		        boutonAcceder.setDisable(false);
	                 }
	             });
		    }

			@Override
			protected Void call() throws Exception {
				return null;
			}
		};

		progressBar.progressProperty().bind(task.progressProperty());
		Thread thread = new Thread(task);

		thread.start();
	}

	/**
	 * Fonction qui permet d'accéder à la partie si le nombre de de joueurs est assz élèvé sinon repart sur le menu départ
	 */
	@FXML
	public void accederPartie()
	{
		this.mainApp.initRootLayout();
	}

	/**
	 * getter de l'application
	 * @return mainApp
	 */
	public MainApp getMainApp() {
		return mainApp;
	}

	/**
	 * setter de l'application
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}


}
