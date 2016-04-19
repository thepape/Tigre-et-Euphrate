package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.vue;

import java.rmi.RemoteException;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Dynastie;
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


	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
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
		Button pButton = (Button) event.getSource();

		pButton.setDisable(true);
		Dynastie dynastie = null;
		if(pButton.getId().equals("lanister"))
		{
			dynastie = Dynastie.Lanister;
		}
		
		MainApp.getInstance().getServeur().sendDynastieChoisi(dynastie, MainApp.getInstance().getClient().getIdObjetPartie());
		for(int i = 0; i < MainApp.getInstance().getListeDynastie().size(); i++)
		{
			if(MainApp.getInstance().getListeDynastie().get(i).equals(dynastie))
				MainApp.getInstance().getListeDynastie().set(i, dynastie);
		}
		
		/*MainApp.getInstance().getListeDynastie().remove(0, MainApp.getInstance().getListeDynastie().size()-1);
		MainApp.getInstance().getListeDynastie().addAll(MainApp.getInstance().getServeur().getListeDynastie());*/
		//TODO envoyer au serveur + passer la dynastie au joueur
	}
	
	@FXML
	public void afficherPlateau(){
		MainApp.getInstance().afficherPlateau();
	}
}
