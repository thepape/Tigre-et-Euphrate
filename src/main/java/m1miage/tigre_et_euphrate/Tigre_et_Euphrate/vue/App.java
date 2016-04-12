package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.vue;

import javafx.collections.ObservableList;
import javafx.stage.Stage;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.PartieInterface;

public interface App {

	public ObservableList<PartieInterface> getListeJoueur();
	public void setListeJoueur(ObservableList<PartieInterface> joueur);
	public Stage getPrimaryStage();
}
