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
import javafx.scene.control.TextArea;
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
import javafx.scene.text.Text;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.PartieInterface;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Pioche;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Placable;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Plateau;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Position;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action.Action;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action.EchangerTuileCivilisation;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action.PlacerChef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action.PlacerTuileCatastrophe;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action.PlacerTuileCivilisation;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action.RetirerChef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Dynastie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.conflit.Conflits;
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
	 * Texte area qui affiche toutes les actions de la partie
	 */
	@FXML
	private TextArea texteAction;
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
	 * Liste des tuiles que le joueur veut changer
	 */
	private ArrayList<TuileCivilisation> listeTuileChange = new ArrayList<TuileCivilisation>();

	/**
	 * Boolean pour savoir si le joueur est en echange de cartes
	 */
	private boolean echangeCarte;

	/**
	 * indice de la carte déplacée dans les gridPane
	 */
	private int indice;

	/**
	 * tuile à conserver pour les actions
	 */
	private Placable tuileAction;

	private ArrayList<TuileCivilisation> tuilesRenfort = new ArrayList<TuileCivilisation>();

	private boolean conflitInterne = false;

	private Partie partie;

	/**
	 * Position antérieure au retrait du chef
	 */
	private Position positionChefRetire;

	private String messageTemporaire;

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
		this.mainApp = mainApp;
		this.texteAction.setEditable(false);
		this.texteAction.setWrapText(true);
		// Création aléatoire du deck privé du joueur
		/*for(int i = 0; i < 6; i++)
		{
			joueur.getDeckPrive().ajouter(this.partie.getPioche().piocherTuile());
		}*/

		// Initialisation de l'interface du deck privé
		try
		{
			/*for(int i = 0; i < mainApp.getClient().getJoueur().getDeckPrive().getDeckPrive().size(); i++)
			{
				Pane pane = (Pane) deckPrive.getChildren().get(i);
				ImageView imageView = (ImageView) pane.getChildren().get(0);
				String urlImage = getClass().getResource(mainApp.getClient().getJoueur().getDeckPrive().getDeckPrive().get(i).getType().getUrlImage()).toExternalForm();
				Image image = new Image(urlImage);
				imageView.setImage(image);
			}*/

			this.construireDeckPrivee();

			// Initialisation de l'interface du deck public
			//int size = mainApp.getClient().getJoueur().getDeckPublic().getDeckPublic().size();
			/*int size = 4;
			for(int i = 0; i < size; i++)
			{
				Pane pane = (Pane) deckPublic.getChildren().get(i);
				ImageView imageView = (ImageView) pane.getChildren().get(0);
				Chef chef = (Chef) mainApp.getClient().getJoueur().getDeckPublic().getDeckPublic().get(i);
				String urlImage = getClass().getResource(mainApp.getClient().getJoueur().getDynastie().getNom().toLowerCase() + "_" + chef.getTypeChef().getFinUrlImage()).toExternalForm();
				Image image = new Image(urlImage);
				imageView.setImage(image);
			}*/
			this.construireDeckPublic();
		} catch(RemoteException e)
		{
			e.printStackTrace();
		}
		//this.mainApp = mainApp;
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

	public void selectionnerTuileRenfortConflitInterne(MouseEvent event){
		if(!this.conflitInterne){
			return;
		}

		ImageView imageTuile = (ImageView) event.getSource();
		Pane pane = (Pane) imageTuile.getParent();

		//recupere l'index de la tuile dans le deck prive
		int index = GridPane.getColumnIndex(pane);
		Joueur joueur = ((Client) MainApp.getInstance().getClient()).getJoueur();
		TuileCivilisation renfort = joueur.getDeckPrive().getDeckPrive().get(index-2);

		//on verifie que la tuile est de type temple
		if(!renfort.getType().equals(TypeTuileCivilisation.Temple)){
			return;
		}

		//si le client clique sur une tuile qui est deja en renfort, on la retire
		if(this.tuilesRenfort.contains(renfort)){
			this.tuilesRenfort.remove(renfort);
			imageTuile.setFitHeight(80);
			imageTuile.setFitWidth(80);
			imageTuile.setTranslateX(15);
			imageTuile.setTranslateY(25);

			for(Node n : pane.getChildren()){
				if(n instanceof ImageView && ((ImageView) n).getAccessibleText().equals("background")){
					pane.getChildren().remove(n);
					break;
				}
			}
		}
		else{
			//sinon on l'ajoute en renfort et on la met en "surbrillance"
			this.tuilesRenfort.add(renfort);
			imageTuile.setFitHeight(70);
			imageTuile.setFitWidth(70);
			imageTuile.setTranslateX(20);
			imageTuile.setTranslateY(30);

			ImageView highLight = new ImageView();
			highLight.setAccessibleText("background");

			URL file = this.getClass().getResource("renfortBG.png");
			Image img = new Image(file.toString());
			highLight.setImage(img);
			highLight.setFitHeight(80);
			highLight.setFitWidth(80);
			highLight.setTranslateX(15);
			highLight.setTranslateY(25);

			pane.getChildren().add(highLight);
			imageTuile.toFront();
		}

	}


	public void echangeTuile() throws RemoteException{
		if(this.echangeCarte){
			//supprime les cartes dans le deck prive
			EchangerTuileCivilisation action = new EchangerTuileCivilisation(this.partie,MainApp.getInstance().getClient().getJoueur(),this.listeTuileChange);
			this.mainApp.getServeur().send(action, this.mainApp.getClient().getIdObjetPartie());
			this.listeActionTour.add(action);
			this.echangeCarte = false;
		}else{
			if(this.listeActionTour.size() <2){
				this.echangeCarte = true;
			}
		}
	}

	/**
	 * Parametre qui permet au joueur de choisir ses tuiles a echanger
	 * @param event
	 */
	public void selectionnerTuileAEchanger(MouseEvent event){
		if(!this.echangeCarte){
			return;
		}

		ImageView imageTuile = (ImageView) event.getSource();
		Pane pane = (Pane) imageTuile.getParent();

		//recupere l'index de la tuile dans le deck prive
		int index = GridPane.getColumnIndex(pane);
		Joueur joueur = ((Client) MainApp.getInstance().getClient()).getJoueur();
		TuileCivilisation echange = joueur.getDeckPrive().getDeckPrive().get(index-2);

		//si le client clique sur une tuile qui est deja en echange, on la retire
		if(this.listeTuileChange.contains(echange)){
			this.listeTuileChange.remove(echange);
			imageTuile.setFitHeight(80);
			imageTuile.setFitWidth(80);
			imageTuile.setTranslateX(15);
			imageTuile.setTranslateY(25);

			for(Node n : pane.getChildren()){
				if(n instanceof ImageView && ((ImageView) n).getAccessibleText().equals("background")){
					pane.getChildren().remove(n);
					break;
				}
			}
		}
		else{
			//sinon on l'ajoute en renfort et on la met en "surbrillance"
			this.listeTuileChange.add(echange);
			imageTuile.setFitHeight(70);
			imageTuile.setFitWidth(70);
			imageTuile.setTranslateX(20);
			imageTuile.setTranslateY(30);

			ImageView highLight = new ImageView();
			highLight.setAccessibleText("background");

			URL file = this.getClass().getResource("echangeBG.png");
			Image img = new Image(file.toString());
			highLight.setImage(img);
			highLight.setFitHeight(80);
			highLight.setFitWidth(80);
			highLight.setTranslateX(15);
			highLight.setTranslateY(25);

			pane.getChildren().add(highLight);
			imageTuile.toFront();
		}

	}

	/**
	 * fonction qui permetre le drag sur les chefs deja placés sur le plateau
	 * @param event
	 */
	@FXML
	private void dragChefFromPlateau(MouseEvent event){
		//System.out.println("je touche a un chef placé !!!");

		//on verifie si c'est au tour du joueur actuel
		Joueur joueurTour = this.partie.getJoueurTour();

		if(joueurTour.getId() != ((Client) MainApp.getInstance().getClient()).getJoueur().getId()){
			return;
		}

		//on verifie si le client est en conflit
		if(this.conflitInterne){
			return;
		}

		//verifie si le client a déjà fait 2 actions où si il est en echange de tuile
		if((listeActionTour.size() >= 2) || echangeCarte){
			return;
		}

		ImageView imageTuile = (ImageView) event.getSource();
		imageTuile.setVisible(false);

		ControleurPlateau.imageEnDragAndDropTuile = null;
		ControleurPlateau.imageEnDragAndDropChef = (Pane) imageTuile.getParent();
		int rowIndex = GridPane.getRowIndex(imageTuile.getParent());
		int colIndex = GridPane.getColumnIndex(imageTuile.getParent());

		try {
			Plateau plateau = MainApp.getInstance().getServeur().getPartie().getPlateauJeu();
			Placable placable = plateau.getPlacableAt(new Position(rowIndex, colIndex));

			if(placable instanceof Chef){
				this.tuileAction = (Chef) placable;
				this.positionChefRetire = placable.getPosition();
			}

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Dragboard db = imageTuile.startDragAndDrop(TransferMode.ANY);
		ClipboardContent content = new ClipboardContent();
        content.putImage(imageTuile.getImage());
        db.setContent(content);
        event.consume();
	}

	/**
	 * Fonction qui parametre le drag des tuiles du deck privé
	 * @param event
	 */
	@FXML
	private void dragTuileDecks(MouseEvent event) throws RemoteException
	{
		if(mainApp.getInstance().getServeur().getPartie().getJoueurTour().getId() == mainApp.getInstance().getClient().getJoueur().getId()){
			if((listeActionTour.size() <2) && !echangeCarte){
				ImageView imageTuile = (ImageView) event.getSource();
				imageTuile.setVisible(false);
				if(imageTuile.getAccessibleText().equals("tuileCivilisation"))
				{
					ControleurPlateau.imageEnDragAndDropTuile = (Pane) imageTuile.getParent();
					ControleurPlateau.imageEnDragAndDropChef = null;
					int gridPanColIndex = GridPane.getColumnIndex(imageTuile.getParent());
					this.tuileAction = ((Client) mainApp.getInstance().getClient()).getJoueur().getDeckPrive().getDeckPrive().get(gridPanColIndex-2);
				} else if(imageTuile.getAccessibleText().equals("tuileChef"))
				{
					ControleurPlateau.imageEnDragAndDropTuile = null;
					ControleurPlateau.imageEnDragAndDropChef = (Pane) imageTuile.getParent();
					int gridPanRowIndex = GridPane.getRowIndex(imageTuile.getParent());
					this.tuileAction = MainApp.getInstance().getClient().getJoueur().getDeckPublic().getDeckPublic().get(gridPanRowIndex);
				}


				Dragboard db = imageTuile.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
		        content.putImage(imageTuile.getImage());
		        db.setContent(content);
		        event.consume();
			}
		}
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
							try {
								ImageView pane = (ImageView) event.getSource();
								if(mainApp.getInstance().getServeur().getPartie().getJoueurTour().getId() == mainApp.getInstance().getClient().getJoueur().getId()) {
									if((listeActionTour.size() <2) && !echangeCarte){
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
										positionChefRetire = new Position(GridPane.getRowIndex(pane.getParent()), GridPane.getColumnIndex(pane.getParent()));
										ClipboardContent content = new ClipboardContent();
										content.putImage(imageTuile.getImage());
										db.setContent(content);
										event.consume();
									}
								}
							} catch (RemoteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}} });

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
								//pane.getChildren().remove(0);
							}
						} });
				}
				Position position = new Position(GridPane.getRowIndex((Pane)event.getSource()), GridPane.getColumnIndex((Pane)event.getSource()));
				
				if(target.getChildren().size() == 0 && !(this.tuileAction instanceof TuileCatastrophe))
				{

					try
					{
						Action action = null;
						if(ControleurPlateau.imageEnDragAndDropTuile != null || ControleurPlateau.imageEnDragAndDropChef != null)
						{
							if(ControleurPlateau.imageEnDragAndDropTuile != null) {
								action = new PlacerTuileCivilisation(MainApp.getInstance().getClient().getPartie(), MainApp.getInstance().getClient().getJoueur(), position, (TuileCivilisation)this.tuileAction);
							} else if(ControleurPlateau.imageEnDragAndDropChef != null)
							{
								action = new PlacerChef(MainApp.getInstance().getClient().getPartie(), MainApp.getInstance().getClient().getJoueur(), (Chef) this.tuileAction, position);
							}
							if(!action.verifier())
							{
								event.setDropCompleted(false);
							} else {

								boolean actionOK = mainApp.getServeur().send(action, MainApp.getInstance().getClient().getIdObjetPartie());
								target.getChildren().add(image);
								event.setDropCompleted(true);

								if(actionOK){
									this.listeActionTour.add(action);
								}


								if(action instanceof PlacerChef && ((PlacerChef) action).isConflit()){
									this.conflitInterne = true;
								}

								//refresh du plateau du joueur qui a droppé
								this.construirePlateau();
							}
						} else {
							

						}
					} catch(RemoteException e)
					{
						e.printStackTrace();
					}
				} else {
					
					if(this.tuileAction instanceof TuileCatastrophe)
					{
						
						try {
							Action actionCata;
							actionCata = new PlacerTuileCatastrophe(MainApp.getInstance().getClient().getPartie(), MainApp.getInstance().getClient().getJoueur(), (TuileCatastrophe)this.tuileAction, position);
						
							if(!actionCata.verifier())
							{
								event.setDropCompleted(false);
							} else {

								boolean actionOK = mainApp.getServeur().send(actionCata, MainApp.getInstance().getClient().getIdObjetPartie());
								target.getChildren().add(image);
								event.setDropCompleted(true);

								if(actionOK){
									this.listeActionTour.add(actionCata);
								}
							}
							//refresh du plateau du joueur qui a droppé
							this.construirePlateau();
						
						
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						
					}
					else{
						event.setDropCompleted(false);
					}
					
				}
			}
	}

	@FXML
	private void dragTuileCatastrophe(MouseEvent event) throws RemoteException {
		if(mainApp.getInstance().getServeur().getPartie().getJoueurTour().getId() == mainApp.getInstance().getClient().getJoueur().getId()){
			if((listeActionTour.size() <2) && !echangeCarte){
				ImageView imageTuile = (ImageView) event.getSource();
				imageTuile.setVisible(false);

				this.tuileAction = ((Client) mainApp.getInstance().getClient()).getJoueur().getDeckPublic().getListeTuileCatastrophe().get(GridPane.getColumnIndex(imageTuile.getParent()) - 10);

				Dragboard db = imageTuile.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
		        content.putImage(imageTuile.getImage());
		        db.setContent(content);
		        event.consume();
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
				this.indice = GridPane.getColumnIndex(ControleurPlateau.imageEnDragAndDropTuile) - 2;
				//this.supprimerTuileDeckPrive(this.indice);
			} else if(ControleurPlateau.imageEnDragAndDropChef != null)
			{
				this.indice = GridPane.getRowIndex(ControleurPlateau.imageEnDragAndDropChef);
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
			ImageView image = new ImageView();
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
				Position posChefARetirer = this.tuileAction.getPosition();
				Chef chefRetrait = (Chef) MainApp.getInstance().getClient().getPartie().getPlateauJeu().getPlateau()[this.positionChefRetire.getX()][this.positionChefRetire.getY()];
				String url = getClass().getResource(mainApp.getClient().getJoueur().getDynastie().getNom().toLowerCase() + "_" + chefRetrait.getTypeChef().getFinUrlImage()).toExternalForm();
				Image imageSrc = new Image(url);
				image.setImage(imageSrc);
				Action action = new RetirerChef(MainApp.getInstance().getClient().getPartie(), MainApp.getInstance().getClient().getJoueur(), chefRetrait, GridPane.getRowIndex(target), this.positionChefRetire);
				if(!action.verifier())
				{
					event.setDropCompleted(false);
				} else {
					/*target.getChildren().clear();
					target.getChildren().add(image);*/
					event.setDropCompleted(true);
					this.listeActionTour.add(action);
					MainApp.getInstance().getServeur().send(action, MainApp.getInstance().getClient().getIdObjetPartie());
				}
			} else {
				event.setDropCompleted(false);
			}
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
				    for(int i = 0; i < client.getJoueur().getDeckPrive().getDeckPrive().size(); i++)
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
			MainApp.getInstance().getClient().setPartieCourante(partie);

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

// Gestion des tuiles Catastrophes

public void placerTuile(MouseEvent event) throws RemoteException{

}

	public void construirePlateauJAVAFX(){

		/////////////////   affichage des tuiles ////////////////////

		for(int x = 0; x < 11; x++){
			for(int y = 0; y < 16; y++){
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

							//si la tuile est jonction, on l'affiche
							if(tuileCiv.estJonction()){
								ImageView jonction = new ImageView();
								URL Jonctfile = this.getClass().getResource("jonction.png");
								Image imgJonct = new Image(Jonctfile.toString());
								jonction.setImage(imgJonct);

								casePlateau.getChildren().add(jonction);
							}
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



								//on met a jour l'affichage
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

								//si le chef present dans cette case appartient au client là, on ajoute le drag and drop depuis plateau
								if(joueur.getId() == chef.getJoueur().getId()){

									if(casePlateau.getChildren().size() > 0){
										ImageView image = (ImageView) casePlateau.getChildren().get(0);
										image.setOnDragDetected(new EventHandler<MouseEvent>(){

											public void handle(MouseEvent arg0) {
												dragChefFromPlateau(arg0);

											}

										});
									}

									caseNettoyee = true;
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

		for(int x = 0; x < 11; x++){
			for(int y = 0; y < 16; y++){
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

for(int x = 0; x < 11; x++){
	for(int y = 0; y < 16; y++){
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


	/**
	 * Fonction terminant un tour.
	 * Elle vérifie si il reste des cartes dans la pioche
	 * et si il reste des trésors sur le plateau
	 * @param event
	 * @throws RemoteException
	 */

	@FXML
	private void finirTour(MouseEvent event) throws RemoteException
	{
		//si ce client est en conflit, le bouton fin de tour sert a envoyer ses renforts
		if(this.conflitInterne){
			//envoyer les renforts au serveur
			MainApp.getInstance().getServeur().envoyerRenforts(this.tuilesRenfort, ((Client) MainApp.getInstance().getClient()).getJoueur());

			//remettre le texte "passer tour" au bouton passer tour
			Platform.runLater(new Runnable(){

				public void run() {
					boutonFinTour.setText("Passer tour");
				}

			});
		}

		if(this.echangeCarte){
			//Le joueur ne peut pas passer son tour
		}

		boolean finpartie;
		//On teste si l'action placerChef nous a retourner un conflit ou non
		if(!this.conflitInterne){
			//finpartie = mainApp.getServeur().getPartie().piocheCartesManquantes(mainApp.getClient().getJoueur());
			finpartie = mainApp.getServeur().piocherCartesManquantes(mainApp.getClient().getJoueur());

			//mise a jour du deck privé
			this.construireDeckPrivee();

			System.out.println(mainApp.getServeur().getPartie().getJoueurTour().getNom());
			System.out.println(mainApp.getClient().getPartie().getJoueurTour().getNom());

			//On test si la personne qui a cliquer sur passer tour est la même qui a le tour de jeu
			if(mainApp.getInstance().getServeur().getPartie().getJoueurTour().getId() == mainApp.getInstance().getClient().getJoueur().getId()){
				//On teste si il y a une condition de fin de partie avant de donner la main
				if(!finpartie && mainApp.getServeur().getPartie().getPlateauJeu().getNombreTresors() > 2){
					System.out.println("Le joueur a finit son tour. " + mainApp.getClient().getNomJoueur());
					this.viderListeAction();
					mainApp.getServeur().passerTour();
				}else{
					this.mainApp.getServeur().finirPartie();
				}
			}
			//Si le chef placé est en conflit (interne)
		}else{
			Conflits interne = new Conflits();
		}
	}

	//Permet de vider la liste d'action du joueur qui joue
	public void viderListeAction(){
		this.listeActionTour.clear();
	}

	private Node getNode(int x, int y){
		for(Node child : this.plateau.getChildren()){
			int nx, ny = 0;
			try{
			ny = GridPane.getColumnIndex(child);
			nx = GridPane.getRowIndex(child);
			}catch(Exception e){
				continue;
			}

			if(x == nx && y == ny){
				return child;
			}
		}

		return null;
	}

	public void construireDeckPrivee() throws RemoteException{
		/*Partie partieCourante = mainApp.getServeur().getPartie();
		this.partie = partieCourante;
		((Client) mainApp.getClient()).setPartieCourante(this.partie);*/

		Platform.runLater(new Runnable(){



			public void run() {
				construireDeckPriveeJAVAFX();

			}
		});
	}

	private void construireDeckPriveeJAVAFX()
	{
		for(int i = 0; i < 6; i++){
			Pane pane = (Pane) deckPrive.getChildren().get(i);
			pane.getChildren().clear();
		}

			for(int i = 0; i < ((Client) mainApp.getClient()).getJoueur().getDeckPrive().getDeckPrive().size(); i++)
			{
				Pane pane = (Pane) deckPrive.getChildren().get(i);
				/*
				ImageView imageView = (ImageView) pane.getChildren().get(0);
				String urlImage = getClass().getResource(mainApp.getClient().getJoueur().getDeckPrive().getDeckPrive().get(i).getType().getUrlImage()).toExternalForm();
				Image image = new Image(urlImage);
				imageView.setImage(image);*/
				//pane.getChildren().clear();

				ImageView imageView = new ImageView();
				TuileCivilisation tuile = ((Client) mainApp.getClient()).getJoueur().getDeckPrive().getDeckPrive().get(i);
				URL file = this.getClass().getResource(tuile.getType().getUrlImage());
				Image img = new Image(file.toString());
				imageView.setImage(img);

				pane.getChildren().add(imageView);

				imageView.setOnMouseClicked(new EventHandler<MouseEvent>(){

					public void handle(MouseEvent arg0) {
						selectionnerTuileRenfortConflitInterne(arg0);
						selectionnerTuileAEchanger(arg0);
					}

				});

				imageView.setOnDragDetected(new EventHandler<MouseEvent>(){

					public void handle(MouseEvent arg0) {
						try {
							dragTuileDecks(arg0);
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				});

				imageView.setOnDragDone(new EventHandler<DragEvent>(){

					public void handle(DragEvent event) {
						try {
							dragDoneDecks(event);
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				});

				//propriétés de l'image de la tuile
				imageView.setFitHeight(80);
				imageView.setFitWidth(80);
				imageView.setTranslateX(15);
				imageView.setTranslateY(25);
				imageView.setAccessibleText("tuileCivilisation");
			}
	}

	private void construireDeckPublic() throws RemoteException{
		Platform.runLater(new Runnable(){
			public void run(){
				construireDeckPublicJAVAFX();
			}
		});
	}

	private void construireDeckPublicJAVAFX()
	{
		Joueur joueur = ((Client) mainApp.getClient()).getJoueur();

		for(int i = 0; i < 4; i++){
			Pane pane = (Pane) deckPublic.getChildren().get(i);
			pane.getChildren().clear();
		}

		for(int i = 0; i < joueur.getDeckPublic().getDeckPublic().size() && i < 4; i++)
		{

			Pane pane = (Pane) deckPublic.getChildren().get(i);
			/*
			ImageView imageView = (ImageView) pane.getChildren().get(0);
			Chef chef = (Chef) joueur.getDeckPublic().getDeckPublic().get(i);
			String urlImage = getClass().getResource(joueur.getDynastie().getNom().toLowerCase() + "_" + chef.getTypeChef().getFinUrlImage()).toExternalForm();
			Image image = new Image(urlImage);
			imageView.setImage(image);
			imageView.setVisible(true);*/

			ImageView imageView = new ImageView();
			Chef chef = joueur.getDeckPublic().getDeckPublic().get(i);

			String dyn = chef.getDynastie().getNom().toLowerCase();
			String coul = chef.getTypeChef().getFinUrlImage();
			String imgUrl = dyn+"_"+coul;

			URL file = this.getClass().getResource(imgUrl);
			Image img = new Image(file.toString());
			imageView.setImage(img);

			pane.getChildren().add(imageView);

			imageView.setOnDragDetected(new EventHandler<MouseEvent>(){

				public void handle(MouseEvent arg0) {
					try {
						dragTuileDecks(arg0);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			});

			imageView.setOnDragDone(new EventHandler<DragEvent>(){

				public void handle(DragEvent event) {
					try {
						dragDoneDecks(event);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			});

			//propriétés de l'image de la tuile
			imageView.setFitHeight(75);
			imageView.setFitWidth(75);
			imageView.setTranslateY(25);
			imageView.setAccessibleText("tuileChef");
		}
		/*
		for(int i = joueur.getDeckPublic().getDeckPublic().size() ; i < deckPublic.getChildren().size(); i++)
		{
			Pane pane = (Pane) deckPublic.getChildren().get(i);
			ImageView imageView = (ImageView) pane.getChildren().get(0);
			imageView.setVisible(false);
		}*/
	}

	public void afficherMessageJAVAFX(String message){
		this.texteAction.appendText("\n"+message);
	}

	public void afficherMessage(final String message){

		Platform.runLater(new Runnable(){

			public void run() {
				((ControleurPlateau)MainApp.getInstance().currentControler).afficherMessageJAVAFX(message);
			}

		});
	}




	public void gererConflitInterne(){
		ArrayList<Joueur> toursConflit = this.partie.getListeToursConflits();
		Joueur joueur = ((Client) MainApp.getInstance().getClient()).getJoueur();

		//si ce client là est concerné par le conflit en cours,
		if(toursConflit.contains(joueur)){
			//alors on lui permet de selectionner des tuiles renfort
			this.conflitInterne = true;

			//on change le role du bouton fin de tour, désormais il sert à envoyer les renforts
			Platform.runLater(new Runnable(){

				public void run() {
					boutonFinTour.setText("Envoyer renforts");
				}

			});
		}
	}


	/**
	 * Methode appelée par le client ou le serveur pour indiquer au controleur de rafraichir sa vue
	 */
	public void changed(ObservableValue arg0, Object arg1, Object arg2) {
		// TODO Auto-generated method stub
		if(arg2 == null || !(arg2 instanceof ArrayList)){
			return;
		}

		ArrayList<Object> params = (ArrayList<Object>) arg2;

		for(int i = 0; i < params.size(); i++){

			if(params.get(i) instanceof String){

				String param = (String) params.get(i);

				if(param.equals("plateau")){
					//rafraichir le plateau
					this.construirePlateau();

					Plateau plateau = this.partie.getPlateauJeu();
					System.out.println(plateau.afficher());
				} else if(param.equals("deckPublic"))
				{
					try
					{
						this.construireDeckPublic();
					} catch(RemoteException e)
					{
						e.printStackTrace();
					}
				} else if(param.equals("deckPrive"))
				{
					try
					{
						this.construireDeckPrivee();
					} catch(RemoteException e)
					{
						e.printStackTrace();
					}
				}else if(param.equals("passerTour")){
					Joueur j1 = null;
					try {
						j1 = this.mainApp.getServeur().getPartie().getJoueurTour();

						this.afficherMessage("C'est au tour de "+j1.getNom());
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("COUILLE"+j1.getNom());
				}else if(param.contains("message:")){

					String message = param.split(":")[1];
					this.afficherMessage(message);
					System.out.println(param);
				}else if(param.equals("partie")){
					try {
						this.partie = MainApp.getInstance().getServeur().getPartie();
						Client client = (Client) MainApp.getInstance().getClient();
						client.setPartieCourante(partie);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(param.equals("conflitInterne")){
					this.gererConflitInterne();
				}else if(param.equals("conflitInterneResolu")){
					this.conflitInterne = false;

				}else if(param.equals("finpartie")){
					this.mainApp.goToAttributionTresors(((Client)this.mainApp.getClient()).getJoueur().getPointTresor());
				}

			}
		}
	}
}

