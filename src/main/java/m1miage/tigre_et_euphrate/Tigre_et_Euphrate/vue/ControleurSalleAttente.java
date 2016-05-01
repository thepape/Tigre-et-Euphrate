package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.vue;

import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Dynastie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.connexion.Client;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.connexion.InterfaceServeurClient;
import javafx.collections.ListChangeListener;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.collections.*;
import javafx.scene.control.ListView;

/**
 * Controlleur de la salle d'attente
 * @author
 *
 */
public class ControleurSalleAttente implements ChangeListener {

	/**
	 * Application gérer par ce controleur
	 */
	private MainApp mainApp;
	
	public ObservableList<Joueur> joueurs = FXCollections.observableArrayList();
	
	public ObservableList<String> nomJoueurs = FXCollections.observableArrayList();
	
	public HashMap<Dynastie, Button> boutonsDynastie = new HashMap<Dynastie, Button>();
	
	private ArrayList<Dynastie> dynastiesDispo = new ArrayList<Dynastie>();
	
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

		@FXML
		private ListView listeJoueur;
		
		private boolean dynastiechoisie = false;

	public MainApp getMainApp() {
		return mainApp;
	}


	public void setMainApp(MainApp mainApp) throws RemoteException {
		this.mainApp = mainApp;
		Client client = (Client)this.mainApp.getClient();
		this.boutonsDynastie.put(Dynastie.Lanister, this.lanister);
		this.boutonsDynastie.put(Dynastie.Stark, this.stark);
		this.boutonsDynastie.put(Dynastie.Tyrell, this.tyrell);
		this.boutonsDynastie.put(Dynastie.Targaryen, this.targaryen);

		//Ajout du listener pour la dynsatie Lanister
		/*
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
		*/
		//ajout du listener pour l'observablelist de joueur
		/*
		this.joueurs.addListener(
				new ListChangeListener<Joueur>(){

					public void onChanged(javafx.collections.ListChangeListener.Change<? extends Joueur> arg0) {
						//ObservableList<Joueur> list = (ObservableList<Joueur>) arg0.getList();
						try {
							Platform.runLater(new Runnable() {
								public void run(){
									try {
										((ControleurSalleAttente) MainApp.getInstance().currentControler).majListeJoueur();
									} catch (RemoteException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									};
								}
							});
							//changeListeJoueur();
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						
					}
					
				}
				);*/
	}

	


	/**
	 * Constructeur
	 */
	public ControleurSalleAttente() {
	}

	private boolean deconnecterClient(){
		//DECONNEXION
				InterfaceServeurClient client = MainApp.getInstance().getClient();
				boolean deconnexionOK = false;
				try {
					deconnexionOK = client.deconnecter();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return deconnexionOK;
	}

	// Fonctions

	@FXML
	public void retourAuMenu(){
		Dynastie d;
		try {
			d = MainApp.getInstance().getClient().getJoueur().getDynastie();
			MainApp.getInstance().getServeur().libererDynastie(d);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.deconnecterClient();
		
		MainApp.getInstance().goToMenuPage();
	}
	
	@FXML
	public void handleBoutonPret(){
		InterfaceServeurClient serveur =  this.mainApp.getServeur();
		InterfaceServeurClient client = this.mainApp.getClient();
		try {
			if(dynastiechoisie){//faire des trucs
				serveur.switchJoueurEstPret(client);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void handleChoixDynastie(Dynastie dynastie){
		InterfaceServeurClient serveur =  this.mainApp.getServeur();
		InterfaceServeurClient client = this.mainApp.getClient();
		
		try {
			serveur.setDynastieOfClient(client, dynastie);
			dynastiechoisie = true;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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
				//this.mainApp.getClient().getListeDynastie().get(0).setEstPrise(true);
			} else if(pButton.getId().equals("stark"))
			{
				dynastie = Dynastie.Stark;
				//this.mainApp.getClient().getListeDynastie().get(1).setEstPrise(true);
			} else if(pButton.getId().equals("tyrell"))
			{
				dynastie = Dynastie.Tyrell;
				//this.mainApp.getClient().getListeDynastie().get(3).setEstPrise(true);
			} else if(pButton.getId().equals("targaryen"))
			{
				dynastie = Dynastie.Targaryen;
				//this.mainApp.getClient().getListeDynastie().get(2).setEstPrise(true);
			}
			
			this.handleChoixDynastie(dynastie);

			//MainApp.getInstance().getServeur().sendDynastieChoisi(dynastie.getNom(), MainApp.getInstance().getClient().getIdObjetPartie());
			//client.getJoueur().setDynastie(dynastie);
			//System.out.println(client.getJoueur().getDynastie().getNom());
		}
	}
	/*
	public void updateListeJoueurs() throws RemoteException{
		InterfaceServeurClient serveur = MainApp.getInstance().getServeur();
		ArrayList<InterfaceServeurClient> clients = serveur.getClients();
		this.joueurs.clear();
		
		for(InterfaceServeurClient i: clients){
			try {
				Joueur j = i.getJoueur();
				this.joueurs.add(j);
				System.out.println(j.getNom());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}*/
	
	public void ajouterJoueurDansListe(Joueur j){
		this.joueurs.add(j);
	}
	
	public void retirerJoueurDansListe(Joueur j){
		this.joueurs.remove(j);
	}
	/*
	public void changeListeJoueur(){
		ObservableList<String> items = FXCollections.observableArrayList();
		
		for(Joueur j : this.joueurs){
			String n = j.getNom();
			
			if(j.estPret()){
				n += " [PRET]";
			}
			
			items.add(n);
		}
		
		this.listeJoueur.setItems(items);
	}*/
	
	public void majSalon(){
		try {
			this.majListeJoueur();
			this.majListeDynasties();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void majListeJoueur() throws RemoteException{
		
		ObservableList<String> items = FXCollections.observableArrayList();
		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		InterfaceServeurClient client = MainApp.getInstance().getClient();
		InterfaceServeurClient serveur = MainApp.getInstance().getServeur();
		ArrayList<InterfaceServeurClient> clients = serveur.getClients();

		for(InterfaceServeurClient i: clients){
			try {
				Joueur j = i.getJoueur();
				Dynastie d = j.getDynastie();
				boolean pret = j.estPret();
				String n = i.getNomJoueur();
				/*if(j.getDynastie() != null){
					n += " - "+j.getDynastie().getNom();
				}*/
				
				if(d != null){
					n += " - "+d.getNom();
				}
				
				if(pret){
					n = n + " [PRET]";
				}
				
				
				
				items.add(n);
				System.out.println(n);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		this.nomJoueurs = items;
		
		Platform.runLater(new Runnable(){

			public void run() {
				if(MainApp.getInstance().currentControler instanceof ControleurSalleAttente){
					((ControleurSalleAttente) MainApp.getInstance().currentControler).majListeJoueurJAVAFX();
					
				}
					
			}
			
		});
	}
	
	public void majListeJoueurJAVAFX(){
		this.listeJoueur.setItems(this.nomJoueurs);
	}
	
	public void majListeDynasties(){
		InterfaceServeurClient serveur = MainApp.getInstance().getServeur();
		try {
			dynastiesDispo = serveur.getListeDynastieDispo();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//JAVAFX
		Platform.runLater(new Runnable(){

			public void run() {
				if(MainApp.getInstance().currentControler instanceof ControleurSalleAttente){
					((ControleurSalleAttente) MainApp.getInstance().currentControler).majListeDynastiesJAVAFX();
				}
					
				
			}
			
		});
	}
	
	public void majListeDynastiesJAVAFX(){
		
			Iterator<Entry<Dynastie, Button>> ite = this.boutonsDynastie.entrySet().iterator();
			
			while(ite.hasNext()){
				Entry<Dynastie, Button> entry =  ite.next();
				Dynastie dynastie = entry.getKey();
				Button bouton = entry.getValue();
				
				if(dynastiesDispo.contains(dynastie)){
					bouton.setDisable(false);
				}
				else{
					bouton.setDisable(true);
				}
			}
	}

	@FXML
	public void afficherPlateau(){
		Platform.runLater(new Runnable(){

			public void run() {
				// TODO Auto-generated method stub
				MainApp.getInstance().afficherPlateau();
			}
			
		});
		
	}

	public boolean partieEstGeneree() throws RemoteException{
		InterfaceServeurClient c = this.mainApp.getServeur();
		Partie p = c.getPartie();
		boolean ok = p.IsEstLancee();
		if(ok){
			return true;
		}
		return false;
		/*if(this.mainApp.getServeur().getPartie().IsEstLancee()){
			return true;
		}
		return false;*/
	}

	public void changed(ObservableValue arg0, Object arg1, Object arg2) {
		
			//try {
				//this.updateListeJoueurs();
		//this.majSalon();
		String arg = null;
		
		if(arg2 == null || !(arg2 instanceof ArrayList)){
			return;
		}
		
		ArrayList<Object> params = (ArrayList<Object>) arg2;
		/*
		try{
			arg = (String) arg2;
		}catch(Exception e){
			
		}*/
		
				/*
					if(arg != null && arg.equals("partieLancee")){
						this.afficherPlateau();
					}
					else{
						
					}*/
					
					for(int i = 0; i < params.size(); i++){
						Object param = params.get(i);
						
						if(param != null && param instanceof String){
							if(param.equals("partieLancee")){
								this.afficherPlateau();
							}
							if(param.equals("refreshSalon")){
								this.majSalon();
							}
						}
						
					}
				
				
			/*} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			/*
			if(arg1 == null && arg2 instanceof Joueur){
				this.ajouterJoueurDansListe((Joueur) arg2);
			}
			else if( arg1 instanceof Joueur && arg2 == null){
				this.retirerJoueurDansListe((Joueur) arg1);
			}*/
		
	}
}
