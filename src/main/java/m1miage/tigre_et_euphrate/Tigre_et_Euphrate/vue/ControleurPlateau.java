package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.vue;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.Arrays;

import com.sun.prism.paint.Color;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.PartieInterface;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Pioche;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Placable;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Plateau;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Position;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action.Action;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action.PlacerChef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action.PlacerTuileCivilisation;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Dynastie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.connexion.Client;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.*;

public class ControleurPlateau implements ChangeListener{

	/**
	 * objet qui vérifie quel type d'objet est en drag
	 */
	private static Pane imageEnDragAndDropChef = null;
	private static Pane imageEnDragAndDropTuile = null;

	/**
	 * GridPane qui représente le plateau de jeu
	 */
	@FXML
	private GridPane plateau;

	/**
	 * GridPane qui represente le deck public
	 */
	@FXML
	private GridPane deckPublic;

	/**
	 * GridPane qui représente le deck privé
	 */
	@FXML
	private GridPane deckPrive;

	/**
	 * Bouton du fin de tour
	 */
	@FXML
	private Button boutonFinTour;

	/**
	 * Application principale
	 */

	private MainApp mainApp;

	/**
	 * Liste des actions faites pendant le tour
	 */
	private ArrayList<Action> listeActionTour = new ArrayList<Action>();

	/**
	 * Liste des tuiles du deckPrive du joueur
	 */
	private ObservableList<TuileCivilisation> deckPriveJoueur = FXCollections.observableArrayList();

	/**
	 * indice de la carte déplacée dans les gridPane
	 */
	private int indice;

	/**
	 * tuile à conserver pour les actions
	 */
	private Placable tuileAction;
	
	private Partie partie;

	/**
	 * getter de l'application
	 * @return l'application
	 */
	public MainApp getMainApp() {
		return mainApp;
	}

	/**
	 * setter de l'application. Initialise tous les champs de l'interface avec les données du joueur traité
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		// Création aléatoire du deck privé du joueur
		/*for(int i = 0; i < 6; i++)
		{
			joueur.getDeckPrive().ajouter(this.partie.getPioche().piocherTuile());
		}*/

		// Initialisation de l'interface du deck privé
		try
		{
			for(int i = 0; i < mainApp.getClient().getJoueur().getDeckPrive().getDeckPrive().size(); i++)
			{
				Pane pane = (Pane) deckPrive.getChildren().get(i);
				ImageView imageView = (ImageView) pane.getChildren().get(0);
				String urlImage = getClass().getResource(mainApp.getClient().getJoueur().getDeckPrive().getDeckPrive().get(i).getType().getUrlImage()).toExternalForm();
				Image image = new Image(urlImage);
				imageView.setImage(image);
			}

			// Initialisation de l'interface du deck public
			for(int i = 0; i < mainApp.getClient().getJoueur().getDeckPublic().getDeckPublic().size(); i++)
			{
				Pane pane = (Pane) deckPublic.getChildren().get(i);
				ImageView imageView = (ImageView) pane.getChildren().get(0);
				Chef chef = (Chef) mainApp.getClient().getJoueur().getDeckPublic().getDeckPublic().get(i);
				String urlImage = getClass().getResource(mainApp.getClient().getJoueur().getDynastie().getNom().toLowerCase() + "_" + chef.getTypeChef().getFinUrlImage()).toExternalForm();
				Image image = new Image(urlImage);
				imageView.setImage(image);
			}
		} catch(RemoteException e)
		{
			e.printStackTrace();
		}
		this.mainApp = mainApp;
	}

	/**
	 * Costructeur vide du controleur
	 */
	public ControleurPlateau()
	{
	}

	/**
	 * Initalisation de la visualisation du plateau de jeu
	 */
	@FXML
	private void initialize()
	{

	}

	/**
	 * Fonction qui parametre le drag des tuiles du deck privé
	 * @param event
	 */
	@FXML
	private void dragTuileDecks(MouseEvent event) throws RemoteException
	{
		ImageView imageTuile = (ImageView) event.getSource();
		imageTuile.setVisible(false);
		if(imageTuile.getAccessibleText().equals("tuileCivilisation"))
		{
			ControleurPlateau.imageEnDragAndDropTuile = (Pane) imageTuile.getParent();
			ControleurPlateau.imageEnDragAndDropChef = null;
			this.tuileAction = this.deckPriveJoueur.get(GridPane.getColumnIndex(imageTuile.getParent()) - 2);
		} else if(imageTuile.getAccessibleText().equals("tuileChef"))
		{
			ControleurPlateau.imageEnDragAndDropTuile = null;
			ControleurPlateau.imageEnDragAndDropChef = (Pane) imageTuile.getParent();
			this.tuileAction = MainApp.getInstance().getClient().getJoueur().getDeckPublic().getDeckPublic().get(GridPane.getRowIndex(imageTuile.getParent()));
		}


		Dragboard db = imageTuile.startDragAndDrop(TransferMode.ANY);
		ClipboardContent content = new ClipboardContent();
        content.putImage(imageTuile.getImage());
        db.setContent(content);
        event.consume();
	}

	/**
	 * Fonction qui autorise le déposer sur le plateau
	 * @param event
	 */
	@FXML
	private void dragOverTableau(DragEvent event)
	{
		Dragboard db = event.getDragboard();
	    if (db.hasImage()) {
	    	event.acceptTransferModes(TransferMode.COPY);
	     }
	}

	/**
	 * Fonction qui ajoute la tuile en drag lorsqu'elle est drop sur le plateau
	 * @param event
	 */
	@FXML
	private void dropTuileDeckPriveTableau(DragEvent event)
	{
			Dragboard db = event.getDragboard();
			Pane target = (Pane)event.getSource();

			if (db.hasImage()) {
				ImageView image = new ImageView(db.getImage());
				if(ControleurPlateau.imageEnDragAndDropChef != null)
				{
					image.setAccessibleText("tuileChef");
				} else if(ControleurPlateau.imageEnDragAndDropTuile != null)
				{
					image.setAccessibleText("tuileCivilisation");
				}

				if(ControleurPlateau.imageEnDragAndDropChef != null)
				{
					image.setOnDragDetected(new EventHandler<MouseEvent>(){

						public void handle(MouseEvent event) {
							ImageView imageTuile = (ImageView) event.getSource();
							imageTuile.setVisible(false);
							if(imageTuile.getAccessibleText().equals("tuileCivilisation"))
							{
								ControleurPlateau.imageEnDragAndDropTuile = (Pane) imageTuile.getParent();
								ControleurPlateau.imageEnDragAndDropChef = null;
							} else if(imageTuile.getAccessibleText().equals("tuileChef"))
							{
								ControleurPlateau.imageEnDragAndDropTuile = null;
								ControleurPlateau.imageEnDragAndDropChef = (Pane) imageTuile.getParent();
							}
							Dragboard db = imageTuile.startDragAndDrop(TransferMode.ANY);
							ClipboardContent content = new ClipboardContent();
					        content.putImage(imageTuile.getImage());
					        db.setContent(content);
					        event.consume();
						} });

					image.setOnDragDone(new EventHandler<DragEvent>(){
						public void handle(DragEvent event) {
							if(event.getTransferMode() == null)
							{
								ImageView image = (ImageView) event.getSource();
								image.setVisible(true);
							} else if(event.getTransferMode() == TransferMode.COPY)
							{
								ImageView image = (ImageView) event.getSource();
								Pane pane = (Pane) image.getParent();
								pane.getChildren().remove(0);
							}
						} });
				}
				if(target.getChildren().size() == 0)
				{

					try
					{
						Position position = new Position(GridPane.getRowIndex((Pane)event.getSource()), GridPane.getColumnIndex((Pane)event.getSource()));
						Action action = null;
						if(ControleurPlateau.imageEnDragAndDropTuile != null) {
							action = new PlacerTuileCivilisation(MainApp.getInstance().getClient().getPartie(), MainApp.getInstance().getClient().getJoueur(), position, (TuileCivilisation)this.tuileAction);
						} else if(ControleurPlateau.imageEnDragAndDropChef != null)
						{
							action = new PlacerChef(MainApp.getInstance().getClient().getPartie(), MainApp.getInstance().getClient().getJoueur(), (Chef) this.tuileAction, position);
						}
						if(!action.executer())
						{
							event.setDropCompleted(false);
						} else {
							mainApp.getServeur().send(action, MainApp.getInstance().getClient().getIdObjetPartie());
							target.getChildren().add(image);
							event.setDropCompleted(true);
							
							//refresh du plateau du joueur qui a droppé
							//this.construirePlateau();
						}
					} catch(RemoteException e)
					{
						e.printStackTrace();
					}
				} else {

					event.setDropCompleted(false);
				}
			}
	}

	/**
	 * Fonction qui notifie à la source si le drag and drop c'est bien passé. Si non, réaffiche la tuile dragger
	 * @param event
	 */
	@FXML
	private void dragDoneDecks(DragEvent event) throws RemoteException
	{
		if(event.getTransferMode() == null)
		{
			ImageView image = (ImageView) event.getSource();
			image.setVisible(true);
		} else if(event.getTransferMode() == TransferMode.COPY)
		{
			ImageView image = (ImageView) event.getSource();
			Pane pane = (Pane) image.getParent();
			if(ControleurPlateau.imageEnDragAndDropTuile != null)
			{
				this.indice = GridPane.getColumnIndex(pane) - 2;
				this.supprimerTuileDeckPrive(this.indice);
			} else if(ControleurPlateau.imageEnDragAndDropChef != null)
			{
				this.indice = GridPane.getRowIndex(pane);
			}
		}
	}

	/**
	 * Fonction générale pour toutes les tuiles des decks. Cette fonction permet de drop sur le deck une tuile que l'on a drag depuis le plateau. Elle dimensionne
	 * la visualition des tuiles en fonction des tuiles et du deck dans lequel l'utilisateur drop.
	 * @param DragEvent event
	 */
	@FXML
	private void dropTuileDecks(DragEvent event)
	{
		Dragboard db = event.getDragboard();
		Pane target = (Pane)event.getSource();

		if (db.hasImage()) {
			ImageView image = new ImageView(db.getImage());
			if(ControleurPlateau.imageEnDragAndDropChef != null)
			{
				image.setAccessibleText("tuileChef");
			} else if(ControleurPlateau.imageEnDragAndDropTuile != null)
			{
				image.setAccessibleText("tuileCivilisation");
			}
			if(target.getAccessibleText().equals("paneTuileChef"))
			{
				image.setFitHeight(75);
				image.setFitWidth(75);
				image.setTranslateY(25);
			} else if(target.getAccessibleText().equals("paneTuileCivilisation"))
			{
				image.setFitHeight(80);
				image.setFitWidth(80);
				image.setTranslateY(25);
				image.setTranslateX(15);
			}
			image.setOnDragDetected(
				new EventHandler<MouseEvent>(){
					public void handle(MouseEvent event) {
						ImageView imageTuile = (ImageView) event.getSource();
						imageTuile.setVisible(false);
						if(imageTuile.getAccessibleText().equals("tuileCivilisation"))
						{
							ControleurPlateau.imageEnDragAndDropTuile = (Pane) imageTuile.getParent();
							ControleurPlateau.imageEnDragAndDropChef = null;
						} else if(imageTuile.getAccessibleText().equals("tuileChef"))
						{
							ControleurPlateau.imageEnDragAndDropTuile = null;
							ControleurPlateau.imageEnDragAndDropChef = (Pane) imageTuile.getParent();
						}
						Dragboard db = imageTuile.startDragAndDrop(TransferMode.ANY);
						ClipboardContent content = new ClipboardContent();
				        content.putImage(imageTuile.getImage());
				        db.setContent(content);
				        event.consume();
					}
				}
			);
			image.setOnDragDone(new EventHandler<DragEvent>(){
				public void handle(DragEvent event) {
					if(event.getTransferMode() == null)
					{
						ImageView image = (ImageView) event.getSource();
						image.setVisible(true);
					} else if(event.getTransferMode() == TransferMode.COPY)
					{
						ImageView image = (ImageView) event.getSource();
						Pane pane = (Pane) image.getParent();
						pane.getChildren().get(0).setVisible(false);
					}
				} });
			if((target.getChildren().size() == 0) && ((target.getAccessibleText().contains("Civilisation") && image.getAccessibleText().contains("Civilisation")) ||
					(target.getAccessibleText().contains("Chef") && image.getAccessibleText().contains("Chef"))))
			{
				target.getChildren().add(image);
				event.setDropCompleted(true);
			} else {
				event.setDropCompleted(false);
			}
		}
	}

	@FXML
	private void finirTour(MouseEvent event) throws RemoteException
	{
		System.out.println("CLIENT COURANT : " + mainApp.getClient().getIdObjetPartie());
		for(int i = 0; i < this.listeActionTour.size(); i++)
		{

			this.mainApp.getServeur().send(this.listeActionTour.get(i), MainApp.getInstance().getClient().getIdObjetPartie());
		}
	}

	public void setDeckPriveJoueur(ArrayList<TuileCivilisation> pDeckPrive)
	{
		this.deckPriveJoueur = FXCollections.observableArrayList(pDeckPrive);
		deckPriveJoueur.addListener(new ListChangeListener<TuileCivilisation>() {
		      public void onChanged(ListChangeListener.Change change) {
		    	  try
		    	  {
			    	Client client = (Client) mainApp.getClient();
				    for(int i = 0; i < deckPrive.getChildren().size(); i++)
				    {
				    	Pane pane = (Pane) deckPrive.getChildren().get(i);
				    	ImageView image = (ImageView) pane.getChildren().get(0);
				    	if(i < deckPriveJoueur.size())
				    	{
							String urlImage = getClass().getResource(mainApp.getClient().getJoueur().getDeckPrive().getDeckPrive().get(i).getType().getUrlImage()).toExternalForm();
							Image imageUrl = new Image(urlImage);
							image.setImage(imageUrl);
							image.setVisible(true);
				    	}  else if(i >= deckPriveJoueur.size() && i < 6) {
				    		pane.setVisible(false);
				    		image.setVisible(false);
				    	}
				    }
		    	  } catch(RemoteException e)
		    	  {
		    		  e.printStackTrace();
		    	  }
		      }
		    });
	}

	public ObservableList<TuileCivilisation> getDeckPriveJoueur() {
		return deckPriveJoueur;
	}

	public void setDeckPriveJoueur(ObservableList<TuileCivilisation> deckPriveJoueur) {
		this.deckPriveJoueur = deckPriveJoueur;
	}

	private void supprimerTuileDeckPrive(int indice)
	{
		Client client = (Client) mainApp.getClient();
		client.getJoueur().getDeckPrive().getDeckPrive().remove(indice);
		this.deckPriveJoueur.remove(indice);
	}
	
	public void construirePlateau(){
		try {
			//this.partie = MainApp.getInstance().getClient().getPartie();
			this.partie = MainApp.getInstance().getServeur().getPartie();
			Platform.runLater(new Runnable(){

				public void run() {
					ControleurPlateau controleur = (ControleurPlateau) MainApp.getInstance().currentControler;
					controleur.construirePlateauJAVAFX();
				}
				
			});
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void construirePlateauJAVAFX(){
		
		/////////////////   affichage des tuiles ////////////////////
		
		for(int x = 0; x < 16; x++){
			for(int y = 0; y < 11; y++){
				Node child = this.getNode(x, y);
				
				if(child == null){
					continue;
				}
				
				if(child instanceof Pane){
					Pane casePlateau = (Pane) child;
					
					boolean caseNettoyee = false;
					
					
					/////on recupere le placable a cette case////
					Placable placable = null;
					try{
					placable = this.partie.getPlateauJeu().getPlacableAt(new Position(x,y));
					}catch(Exception e){
						continue;
					}
					if(placable != null){
						if(placable instanceof TuileCivilisation){
							
							
							TuileCivilisation tuileCiv = (TuileCivilisation) placable;
							
							String imgUrl = tuileCiv.getType().getUrlImage();
							
							if(tuileCiv.estTuileMonument()){
								imgUrl = "tuile_base.png";
							}
							
							ImageView imgView = new ImageView();
							URL file = this.getClass().getResource(imgUrl);
							Image img = new Image(file.toString());
							imgView.setImage(img);
							imgView.getProperties().put("url", imgUrl);
							
							casePlateau.getChildren().clear();
							caseNettoyee = true;
							casePlateau.getChildren().add(imgView);
						}
						else if(placable instanceof TuileCatastrophe){
							TuileCatastrophe tuile = (TuileCatastrophe) placable;
							
							String imgUrl = "tuile_catastrophe.png";
							
							ImageView imgView = new ImageView();
							URL file = this.getClass().getResource(imgUrl);
							Image img = new Image(file.toString());
							imgView.setImage(img);
							imgView.getProperties().put("url", imgUrl);
							
							casePlateau.getChildren().clear();
							caseNettoyee = true;
							casePlateau.getChildren().add(imgView);
						}
						else if(placable instanceof Chef){
							
							Chef chef = (Chef) placable;
							Client client = (Client) MainApp.getInstance().getClient();
							Joueur joueur = client.getJoueur();
							
							//si le chef present dans cette case appartient au client là, on y touche pas
							//pour garder le drag and drop
							if(joueur.getId() == chef.getJoueur().getId()){
								caseNettoyee = true;
							}
							else{
								//sinon, on met a jour l'affichage
								String dyn = chef.getDynastie().getNom().toLowerCase();
								String coul = chef.getTypeChef().getFinUrlImage();
								String imgUrl = dyn+"_"+coul;
								
								ImageView imgView = new ImageView();
								URL file = this.getClass().getResource(imgUrl);
								Image img = new Image(file.toString());
								imgView.setImage(img);
								imgView.getProperties().put("url", imgUrl);
								
								casePlateau.getChildren().clear();
								caseNettoyee = true;
								casePlateau.getChildren().add(imgView);
							}
						}
						
						
					}
					
					
					if(!caseNettoyee){
						casePlateau.getChildren().clear();
						caseNettoyee = true;
					}
				}
			}
		}
		
		////////////////   affichage des monuments  /////////////////
		
		for(int x = 0; x < 16; x++){
			for(int y = 0; y < 11; y++){
				Node child = this.getNode(x, y);
				
				if(child == null){
					continue;
				}
				
				if(child instanceof Pane){
					Pane casePlateau = (Pane) child;
					
					/////on recupere le placable a cette case////
					Placable placable = null;
					try{
					placable = this.partie.getPlateauJeu().getPlacableAt(new Position(x,y));
					}catch(Exception e){
						continue;
					}
					if(placable != null){
						if(placable instanceof TuileCivilisation){
							TuileCivilisation tuileCiv = (TuileCivilisation) placable;
							
							//si la tuile est une tuile de base monument Nord Ouest
							if(tuileCiv.estTuileMonument() && tuileCiv.getId() == tuileCiv.getMonument().getTuileNO().getId()){
								Monument m = tuileCiv.getMonument();
								//affichage de l'arche
								String arche = "monument_arche_"+m.getCouleurArche()+".png";
								String stairs = "monument_stairs_"+m.getCouleurEscaliers()+".png";
								
								ImageView imgView = new ImageView();
								URL file = this.getClass().getResource(arche);
								Image img = new Image(file.toString());
								imgView.setImage(img);
								
								casePlateau.getChildren().add(imgView);
								
								imgView = new ImageView();
								file = this.getClass().getResource(stairs);
								img = new Image(file.toString());
								imgView.setImage(img);
								
								
								casePlateau.getChildren().add(imgView);
							}
						}
					}
				}
			}
		}
		
		
		
////////////////affichage des tresor  /////////////////
		
for(int x = 0; x < 16; x++){
	for(int y = 0; y < 11; y++){
		Node child = this.getNode(x, y);
		
		if(child == null){
			continue;
		}
		
		if(child instanceof Pane){
			Pane casePlateau = (Pane) child;
			
			/////on recupere le placable a cette case////
			Placable placable = null;
			try{
			placable = this.partie.getPlateauJeu().getPlacableAt(new Position(x,y));
			}catch(Exception e){
				continue;
			}
			if(placable != null){
				if(placable instanceof TuileCivilisation){
					TuileCivilisation tuileCiv = (TuileCivilisation) placable;
					
					if(tuileCiv.aTresor()){
						String url = "tresor.png";
						ImageView imgView = new ImageView();
						URL file = this.getClass().getResource(url);
						Image img = new Image(file.toString());
						imgView.setImage(img);
						
						casePlateau.getChildren().add(imgView);
					}
				}
			}
		}
	}
}
		
	}
	
	private Node getNode(int x, int y){
		for(Node child : this.plateau.getChildren()){
			int nx, ny = 0;
			try{
			nx = GridPane.getColumnIndex(child);
			ny = GridPane.getRowIndex(child);
			}catch(Exception e){
				continue;
			}
			
			if(x == nx && y == ny){
				return child;
			}
		}
		
		return null;
	}
	
	public void construirePlateauJAVAFX2(){
		ObservableList<Node> children = this.plateau.getChildren();
		
		
		//this.plateau.add(child, columnIndex, rowIndex);
		
		for(Node child : children){
			
			if(child instanceof Pane){
				Pane casePlateau = (Pane) child;
				int x, y = 0;
				casePlateau.getChildren().clear();
				
				try{
				x = GridPane.getColumnIndex(casePlateau);
				y = GridPane.getRowIndex(casePlateau);
				}catch(Exception e){
					break;
				}
				
				/////on recupere le placable a cette case////
				Placable placable = null;
				try{
				placable = this.partie.getPlateauJeu().getPlacableAt(new Position(x,y));
				}catch(Exception e){
					continue;
				}
				if(placable != null){
					if(placable instanceof TuileCivilisation){
						TuileCivilisation tuileCiv = (TuileCivilisation) placable;
						
						String imgUrl = tuileCiv.getType().getUrlImage();
						
						ImageView imgView = new ImageView();
						URL file = this.getClass().getResource(imgUrl);
						Image img = new Image(file.toString());
						imgView.setImage(img);
						imgView.getProperties().put("url", imgUrl);
						
						
						casePlateau.getChildren().add(imgView);
						
						if(tuileCiv.recupererTresor() != null){
							imgUrl = "tresor.png";
							imgView = new ImageView();
							file = this.getClass().getResource(imgUrl);
							img = new Image(file.toString());
							imgView.setImage(img);
							imgView.getProperties().put("url", imgUrl);
							
							casePlateau.getChildren().add(imgView);
						}
					}
				}
			}
		}
	}

	/**
	 * Methode appelée par le client ou le serveur pour indiquer au controleur de rafraichir sa vue
	 */
	public void changed(ObservableValue arg0, Object arg1, Object arg2) {
		// TODO Auto-generated method stub
		if(arg2 == null || !(arg2 instanceof String)){
			return;
		}
		String[] vues = ((String) arg2).split("-");
		
		for(int i = 0; i < vues.length; i++){
			String vue = vues[i].toLowerCase();
			
			if(vue.equals("plateau")){
				//rafraichir le plateau
				this.construirePlateau();
			}
		}
	}
}
