package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.vue;

import java.rmi.RemoteException;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Dynastie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.connexion.Client;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 * Controlleur de la salle d'attente
 * @author
 *
 */
public class ControleurSalleAttente {

	/**
	 * Application gérer par ce controleur
	 */
	private MainApp mainApp;

	public MainApp getMainApp() {
		return mainApp;
	}


	public void setMainApp(MainApp mainApp) throws RemoteException {
		this.mainApp = mainApp;
		Client client = (Client)this.mainApp.getClient();

		//Ajout du listener pour la dynsatie Lanister
		client.getListeDynastie().get(0).estPrise.addListener(
			new ChangeListener<Boolean>() {

					public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
							Boolean newValue) {
						if(newValue.equals(true))
						{
							lanister.setDisable(true);
						}

					}

				}
			);

		//Ajout du listener pour la dynastie Stark
		client.getListeDynastie().get(1).estPrise.addListener(
				new ChangeListener<Boolean>() {

						public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
								Boolean newValue) {
							if(newValue.equals(true))
							{
								stark.setDisable(true);
							}

						}

					}
				);

		//Ajout du listener pour la dynastie Targaryen
		client.getListeDynastie().get(2).estPrise.addListener(
				new ChangeListener<Boolean>() {

						public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
								Boolean newValue) {
							if(newValue.equals(true))
							{
								targaryen.setDisable(true);
							}

						}

					}
				);

		//Ajout du listener pour la dynastie Tyrell
		client.getListeDynastie().get(3).estPrise.addListener(
				new ChangeListener<Boolean>() {

						public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
								Boolean newValue) {
							if(newValue.equals(true))
							{
								tyrell.setDisable(true);
							}

						}

					}
				);
	}

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


	/**
	 * Constructeur
	 */
	public ControleurSalleAttente() {
	}


	// Fonctions

	@FXML
	public void retourAuMenu(){
		MainApp.getInstance().goToMenuPage();
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
				this.mainApp.getClient().getListeDynastie().get(0).setEstPrise(true);
			} else if(pButton.getId().equals("stark"))
			{
				dynastie = Dynastie.Stark;
				this.mainApp.getClient().getListeDynastie().get(1).setEstPrise(true);
			} else if(pButton.getId().equals("tyrell"))
			{
				dynastie = Dynastie.Tyrell;
				this.mainApp.getClient().getListeDynastie().get(3).setEstPrise(true);
			} else if(pButton.getId().equals("targaryen"))
			{
				dynastie = Dynastie.Targaryen;
				this.mainApp.getClient().getListeDynastie().get(2).setEstPrise(true);
			}

			MainApp.getInstance().getServeur().sendDynastieChoisi(dynastie.getNom(), MainApp.getInstance().getClient().getIdObjetPartie());
			client.getJoueur().setDynastie(dynastie);
			System.out.println(client.getJoueur().getDynastie().getNom());
		}
	}

	@FXML
	public void afficherPlateau(){
		MainApp.getInstance().afficherPlateau();
	}
}
