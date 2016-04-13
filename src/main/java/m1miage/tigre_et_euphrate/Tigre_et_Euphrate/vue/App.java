package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.vue;

import javafx.collections.ObservableList;
import javafx.stage.Stage;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.PartieInterface;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.connexion.Serveur;

public interface App {

	public ObservableList<PartieInterface> getListeJoueur();
	public void setListeJoueur(ObservableList<PartieInterface> joueur);
	public Stage getPrimaryStage();
	public Serveur getServeur();
	public void setServeur(Serveur serveur);
	public void goToHebergerPartiePage();
	public void goToRejoindrePartiePage();
	public void goToMenuPage();
}
