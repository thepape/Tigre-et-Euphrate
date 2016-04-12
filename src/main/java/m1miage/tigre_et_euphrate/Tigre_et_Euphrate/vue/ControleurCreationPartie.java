package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.vue;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import javax.swing.SwingWorker;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.PartieInterface;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Dynastie;

public class ControleurCreationPartie {

	/**
	 * Application gérer par ce controleur
	 */
	private App mainApp;

	/**
	 * ProgressBar qui conrrespond au 30 secondes d'attente des connexions
	 */
	@FXML
	private ProgressBar progressBar;

	/**
	 * Controleur vide
	 */
	public ControleurCreationPartie() {
	}

	/**
	 * Lancement du serveur en attente de connexions clients
	 */
	@FXML
	public void connectionServeur()
	{
		try
		{
			//Création du serveur
			LocateRegistry.createRegistry(42000);
			System.setSecurityManager(new SecurityManager());
			System.setProperty("java.rmi.server.hostname","127.0.0.1");

			//Création de la partie du joueur qui héberge
			Joueur joueur = new Joueur();
			joueur.setNom("joueur hébergeur");
    		PartieInterface partie = new Partie();
    		partie.setJoueur(joueur);

    		//Ajout du joueur hébergeur comme joueur de la partie
    		ObservableList<PartieInterface> joueurCourant = FXCollections.observableArrayList();
    		joueurCourant.add(partie);
    		this.mainApp.setListeJoueur(joueurCourant);

			PartieInterface serveur = (Partie)this.mainApp.getListeJoueur().get(0);
			Naming.rebind("rmi://127.0.0.1:42000/ABC",serveur);

			MainApp app = (MainApp) this.mainApp;
			try
			{
				app.afficherPopUpAttente();
			} catch(Exception e)
			{
				e.printStackTrace();
			}

			/*if(serveur.getListePartie().size() < 1)
			{
				app.afficherMenuDepart();
			}*/
		} catch(RemoteException e)
		{
			e.printStackTrace();
		} catch(MalformedURLException ex)
		{
			ex.printStackTrace();
		}
	}

	/**
	 * Fonction qui permet au client de rejoindre la partie qui a été crée
	 */
	public void connexionClient()
	{
		try
		{
			//Recherche du serveur
			System.setSecurityManager(new SecurityManager());
			System.setProperty("java.rmi.server.hostname","127.0.0.1");

			//Création de la partie du client qui se connecte
			PartieInterface client = new Partie();
			Joueur joueur = new Joueur();
			joueur.setNom("joueur client");
			client.setJoueur(joueur);

			//Récupération du serveur
			PartieInterface serveur = (PartieInterface)Naming.lookup("rmi://127.0.0.1:42000/ABC");

			//Ajout du client à la liste du serveur
			serveur.ajouterAdversaire(client);

			//Affichage de l'interface d'une partie à la connection du client
			MainAppClient appClient = (MainAppClient) this.mainApp;
			appClient.initRootLayout();
		} catch(RemoteException e)
		{
			e.printStackTrace();
		} catch(MalformedURLException ex)
		{
			ex.printStackTrace();
		} catch(NotBoundException exp)
		{
			exp.printStackTrace();
		}
	}

	@FXML
	public void initialize()
	{

	}

	/**
	 * getter de l'application
	 * @return mainApp
	 */
	public App getMainApp() {
		return mainApp;
	}

	/**
	 * setter de l'application
	 * @param mainApp
	 */
	public void setMainApp(App mainApp) {
		this.mainApp = mainApp;
	}


}
