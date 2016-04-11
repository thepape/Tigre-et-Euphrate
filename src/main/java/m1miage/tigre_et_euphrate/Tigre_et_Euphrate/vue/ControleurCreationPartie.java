package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.vue;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
	 * Stage pris en compte
	 */
	private Stage popUp;


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
			System.out.println("Le jeu est prêt");
			LocateRegistry.createRegistry(42000);
			System.setSecurityManager(new SecurityManager());
			System.setProperty("java.rmi.server.hostname","127.0.0.1");
			Joueur joueur = new Joueur();
			joueur.setNom("joueur hébergeur");
    		PartieInterface partie = new Partie();
    		partie.setJoueur(joueur);
    		MainApp app = (MainApp) this.mainApp;
    		app.getListeJoueur().add(partie);
			PartieInterface serveur = (Partie)this.mainApp.getListeJoueur().get(0);
			Naming.rebind("rmi://127.0.0.1:42000/ABC",serveur);
			try
			{
				app.afficherPopUpAttente();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
			int i = 0;
			while(i < 30)
			{
				i++;
				try
				{
					//this.progressBar.setProgress(this.progressBar.getProgress() + 0.03);
					//System.out.println(i);
					System.out.println(serveur.getListePartie().size());
					Thread.sleep(1000);
				} catch(Exception e)
				{
					e.printStackTrace();
				}

			}

			System.out.println("FINI ATTENTE");
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
			System.setSecurityManager(new SecurityManager());
			System.setProperty("java.rmi.server.hostname","127.0.0.1");
			PartieInterface client = new Partie();
			Joueur joueur = new Joueur();
			joueur.setNom("joueur client");
			client.setJoueur(joueur);
			PartieInterface serveur = (PartieInterface)Naming.lookup("rmi://127.0.0.1:42000/ABC");
			serveur.ajouterAdversaire(client);
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
