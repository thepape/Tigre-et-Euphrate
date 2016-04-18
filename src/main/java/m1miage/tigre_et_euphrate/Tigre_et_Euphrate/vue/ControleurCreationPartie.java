package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.vue;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

import javax.swing.SwingWorker;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.PartieInterface;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Dynastie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.connexion.Client;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.connexion.Serveur;

public class ControleurCreationPartie {

	/**
	 * Application gérer par ce controleur
	 */
	private MainApp mainApp;

	/**
	 * ProgressBar qui conrrespond au 30 secondes d'attente des connexions
	 */
	@FXML
	private ProgressBar progressBar;

	@FXML
	private TextField TFNomJoueur;

	@FXML
	private Button BTNHeberger;

	@FXML
	private Button BTNRejoindre;

	@FXML
	private Button BTNRetour;

	@FXML
	private TextField TFIP;

	/**
	 * Controleur vide
	 */
	public ControleurCreationPartie() {
	}

	@FXML
	public void lancerServeur(){
		//creation du serveur
		Serveur serveur = null;
		try
		{
			serveur = new Serveur();
		} catch(RemoteException e)
		{
			e.printStackTrace();
		}
		//this.mainApp.setServeur(serveur);
		MainApp.getInstance().setServeur(serveur);

		//creation du thread qui contient le serveur
		Thread thread = new Thread(serveur);
		//lancement du serveur
		thread.start();
		//serveur.initialiser();
		//serveur.attendreJoueursPrets();

		String nomJoueur = this.TFNomJoueur.getText();
		Client client = null;
		try
		{
			client = new Client("localhost", nomJoueur);
		} catch(RemoteException e)
		{
			e.printStackTrace();
		}
		client.attendreLancementServeur(serveur);
		MainApp.getInstance().setClient(client);
		client.connect();
		//client.rejoindrePartie();
		this.goToSalon();
	}

	@FXML
	public void rejoindreServeur(){
		String nomJoueur = this.TFNomJoueur.getText();
		String ip = this.TFIP.getText();
		try
		{
			Client client = new Client(ip, nomJoueur);
			client.connect();
			MainApp.getInstance().setServeur(client.getServeur());
			MainApp.getInstance().setClient(client);
			//client.rejoindrePartie();
		} catch(RemoteException e)
		{
			e.printStackTrace();
		}
		
		this.goToSalon();
	}

	@FXML
	public void testerSend()
	{
		try
		{
			System.out.println(MainApp.getInstance().getClient().getNomJoueur());
			System.out.println("Message du serveur");
			MainApp.getInstance().getServeur().send("client envoie", MainApp.getInstance().getClient().getIdObjetPartie());
		} catch(RemoteException e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	public void goToHebergerPartie(){
		MainApp.getInstance().goToHebergerPartiePage();
	}

	@FXML
	public void goToRejoindrePartie(){
		MainApp.getInstance().goToRejoindrePartiePage();
	}

	@FXML
	public void retourAuMenu(){
		MainApp.getInstance().goToMenuPage();
	}

	@FXML
	public void goToSalon(){
		MainApp.getInstance().goToSalon();
	}
	
	@FXML
	public void initialize()
	{

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
