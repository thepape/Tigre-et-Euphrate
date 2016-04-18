package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.vue;

import javafx.fxml.FXML;

public class ControleurSalleAttente {

	public ControleurSalleAttente() {
	}
	
	
	
	@FXML
	public void retourAuMenu(){
		MainApp.getInstance().goToMenuPage();
	}
}
