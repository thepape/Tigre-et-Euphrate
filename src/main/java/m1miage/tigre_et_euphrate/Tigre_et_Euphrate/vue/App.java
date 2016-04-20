package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.vue;

import javafx.collections.ObservableList;
import javafx.stage.Stage;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.PartieInterface;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.connexion.InterfaceServeurClient;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.connexion.Serveur;

public interface App {

	public ObservableList<Partie> getListeJoueur();
	public void setListeJoueur(ObservableList<Partie> joueur);
	public Stage getPrimaryStage();
	public InterfaceServeurClient getServeur();
	public void setServeur(InterfaceServeurClient serveur);
	public void goToHebergerPartiePage();
	public void goToRejoindrePartiePage();
	public void goToMenuPage();
}
