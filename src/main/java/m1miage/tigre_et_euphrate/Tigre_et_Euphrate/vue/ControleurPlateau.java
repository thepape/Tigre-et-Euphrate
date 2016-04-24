package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.vue;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.Arrays;

import com.sun.prism.paint.Color;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action.RetirerChef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.connexion.Client;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TypeTuileCivilisation;

public class ControleurPlateau {

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

	/**
	 * Position antérieure au retrait du chef
	 */
	private Position positionChefRetire;

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
					        ImageView imageView = (ImageView) event.getSource();
					        Pane pane = (Pane) imageView.getParent();
					        positionChefRetire = new Position(GridPane.getRowIndex(pane), GridPane.getColumnIndex(pane));
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
		} else if(event.getTransferMode() == TransferMode.MOVE)
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

	@FXML
	private void dragDonePlateau(DragEvent event) throws RemoteException
	{
		Pane pane = (Pane) event.getSource();
		System.out.println(event.getTransferMode());
		if(event.getTransferMode() == null)
		{
			Pane image = (Pane) event.getSource();
			System.out.println("DRAG DONE PLATEAU");
			image.setVisible(true);
		} else if(event.getTransferMode() == TransferMode.COPY)
		{
		}
	}

	/**
	 * Fonction générale pour toutes les tuiles des decks. Cette fonction permet de drop sur le deck une tuile que l'on a drag depuis le plateau. Elle dimensionne
	 * la visualition des tuiles en fonction des tuiles et du deck dans lequel l'utilisateur drop.
	 * @param DragEvent event
	 */
	@FXML
	private void dropTuileDecks(DragEvent event) throws RemoteException
	{
		Dragboard db = event.getDragboard();
		Pane target = (Pane)event.getSource();

		if (db.hasImage()) {
			ImageView image = (ImageView) target.getChildren().get(0);
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
						try
						{
							ImageView imageTuile = (ImageView) event.getSource();
							imageTuile.setVisible(false);
							if(imageTuile.getAccessibleText().equals("tuileCivilisation"))
							{
								ControleurPlateau.imageEnDragAndDropTuile = (Pane) imageTuile.getParent();
								ControleurPlateau.imageEnDragAndDropChef = null;
								tuileAction = deckPriveJoueur.get(GridPane.getColumnIndex(imageTuile.getParent()) - 2);
							} else if(imageTuile.getAccessibleText().equals("tuileChef"))
							{
								ControleurPlateau.imageEnDragAndDropTuile = null;
								ControleurPlateau.imageEnDragAndDropChef = (Pane) imageTuile.getParent();
								tuileAction = mainApp.getClient().getJoueur().getDeckPublic().getDeckPublic().get(GridPane.getRowIndex(imageTuile.getParent()));
							}


							Dragboard db = imageTuile.startDragAndDrop(TransferMode.ANY);
							ClipboardContent content = new ClipboardContent();
					        content.putImage(imageTuile.getImage());
					        db.setContent(content);
					        event.consume();
						} catch(RemoteException e)
						{
							e.printStackTrace();
						}
					}
				}
			);
			image.setOnDragDone(new EventHandler<DragEvent>(){
				public void handle(DragEvent event) {
					if(event.getTransferMode() == null)
					{
						ImageView image = (ImageView) event.getSource();
						image.setVisible(true);
					} else if(event.getTransferMode() == TransferMode.MOVE)
					{
						ImageView image = (ImageView) event.getSource();
						Pane pane = (Pane) image.getParent();
						if(ControleurPlateau.imageEnDragAndDropTuile != null)
						{
							indice = GridPane.getColumnIndex(pane) - 2;
							supprimerTuileDeckPrive(indice);
						} else if(ControleurPlateau.imageEnDragAndDropChef != null)
						{
							indice = GridPane.getRowIndex(pane);
						}
					}
				} });
			if((target.getAccessibleText().contains("Chef") && image.getAccessibleText().contains("Chef")))
			{

				Chef chefRetrait = (Chef) MainApp.getInstance().getClient().getPartie().getPlateauJeu().getPlateau()[GridPane.getRowIndex(target)][GridPane.getColumnIndex(target)];
				Action action = new RetirerChef(MainApp.getInstance().getClient().getPartie(), MainApp.getInstance().getClient().getJoueur(), chefRetrait, GridPane.getRowIndex(target), this.positionChefRetire);
				action.executer();
				image.setVisible(true);
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
}
