package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.vue;

import javafx.scene.control.Button;
import javafx.fxml.FXML;

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
	public void dynastieChoisi(){
		
	}
	
	
}
