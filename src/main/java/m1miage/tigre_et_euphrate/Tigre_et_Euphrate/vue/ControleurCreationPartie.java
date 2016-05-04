package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.vue;

import javafx.scene.control.Label;

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
	
	@FXML
	private Label LBLErreur;
	
	@FXML
	private Label MessageErreur;

	/**
	 * Controleur vide
	 */
	public ControleurCreationPartie() {
	}

	/**
	 * Methode qui lance le serveur et l'affichage de la creation de partie
	 */
	@FXML
	public void lancerServeur() throws MalformedURLException, RemoteException, NotBoundException{
		String nomJoueur = this.TFNomJoueur.getText();
		if(!this.estUnBonPseudo(nomJoueur)){
			this.LBLErreur.setText("Erreur : Veuillez saisir un nom correct ! \n3 caractères minimum composés de lettres.");
			return;
		}
		//creation du serveur
		Serveur serveur = null;
		try
		{
			serveur = new Serveur();
		} catch(RemoteException e)
		{
			e.printStackTrace();
		}
		MainApp.getInstance().setServeur(serveur);

		//creation du thread qui contient le serveur
		Thread thread = new Thread(serveur);
		//lancement du serveur
		thread.start();


		Client client = null;
		try
		{
			client = new Client("localhost", nomJoueur);
			Joueur joueur = new Joueur();
			client.setJoueur(joueur);
		} catch(RemoteException e)
		{
			e.printStackTrace();
		}
		client.attendreLancementServeur(serveur);
		try
		{
			client.setIdObjetPartie(0);
		} catch(RemoteException e)
		{
			e.printStackTrace();
		}
		MainApp.getInstance().setClient(client);
		client.connect();
		//client.rejoindrePartie();
		this.goToSalon();
	}
	
	/**
	 * Fonction permettant de checker le nom du joueur
	 * @param pnom Le nom du joueur
	 * @return vrai ou faux
	 */
	public boolean estUnBonPseudo(String pnom){
		if( (pnom.length() < 3) || (!pnom.matches("[a-zA-Z]*")) )
			return false;
		return true;
	}

	/**
	 * Methode qui permet de rejoindre un serveur lancé et qui affiche la page d'accueil
	 * @throws MalformedURLException
	 * @throws NotBoundException
	 */
	@FXML
	public void rejoindreServeur() throws MalformedURLException, NotBoundException{
		String nomJoueur = this.TFNomJoueur.getText();
		if(!this.estUnBonPseudo(nomJoueur)){
			this.LBLErreur.setText("Erreur : Veuillez saisir un nom correct ! 3 caractères minimum composés de lettres.");
			return;
		}
		System.out.println("Joueur : " + nomJoueur);
		String ip = this.TFIP.getText();
		try
		{
			
			Joueur joueur = new Joueur();
			joueur.setNom(nomJoueur);
			Client client = new Client(ip, nomJoueur);
			client.setJoueur(joueur);
			try{
				client.connect();
			}catch(MalformedURLException e1){
				System.out.println("HIHI 1");
				return;
			}catch(RemoteException e2){
				this.LBLErreur.setText("Erreur : L'adresse IP est incorrecte !");
				return;
			}catch(NotBoundException e3){
				System.out.println("HIHI 3");
				return;
			}
			MainApp.getInstance().setServeur(client.getServeur());
			System.out.println(client.getIdClientCourant());
			MainApp.getInstance().setClient(client);
			System.out.println("ID a la cReatIon : " + MainApp.getInstance().getClient().getIdObjetPartie());
			//client.rejoindrePartie();
		} catch(RemoteException e)
		{
			e.printStackTrace();
		}

		this.goToSalon();
	}

	/**
	 * Methode qui permet d'afficher la page de la partie
	 */
	@FXML
	public void goToHebergerPartie(){
		MainApp.getInstance().goToHebergerPartiePage();
	}

	/**
	 * Methode qui permet d'afficher la page pour rejoindre une game
	 */
	@FXML
	public void goToRejoindrePartie(){
		MainApp.getInstance().goToRejoindrePartiePage();
	}

	/**
	 * Methode qui permet d'afficher la page de menu
	 */
	@FXML
	public void retourAuMenu(){
		MainApp.getInstance().goToMenuPage();
	}

	/**
	 * Methode qui permet d'afficher la page de salon
	 */
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
	
	/**
	 * permet de passer le message d'erreur
	 */
	public void setMessageErreur(String message){
		this.MessageErreur.setText(message);
		mainApp.currentException.printStackTrace();
	}
}

