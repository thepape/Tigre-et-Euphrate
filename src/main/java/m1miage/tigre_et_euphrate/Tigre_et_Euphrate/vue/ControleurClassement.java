package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.vue;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.connexion.InterfaceServeurClient;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.TreeMap;

import javafx.fxml.FXML;


/**
 * Controlleur de l'interface du classement
 * @author
 *
 */
public class ControleurClassement {
	
	// ATTRIBUTS
	
	/**
	 * Application principale
	 */
	private MainApp mainApp;
	
	/**
	 * Le plus petit score des Stark
	 */
	private int minStark = -1;
	
	/**
	 * Le plus petit score des Lannister
	 */
	private int minLannister = -1;
	
	/**
	 * Le plus petit score des Tyrell
	 */
	private int minTyrell = -1;
	
	/**
	 * Le plus petit score des Targaryen
	 */
	private int minTargaryen = -1;
	
	/**
	 * Nom du joueur Stark
	 */
	private Joueur joueurStark = null;
	/**
	 * Nom du joueur Lannister
	 */
	private Joueur joueurLannister = null;
	/**
	 * Nom du joueur Tyrell
	 */
	private Joueur joueurTyrell = null;
	/**
	 * Nom du joueur Targaryen
	 */
	private Joueur joueurTargaryen = null;
	
	@FXML
	private Label starkRouge;
	@FXML
	private Label starkBleu;
	@FXML
	private Label starkVert;
	@FXML
	private Label starkJaune;
	@FXML
	private Label lannisterRouge;
	@FXML
	private Label lannisterBleu;
	@FXML
	private Label lannisterVert;
	@FXML
	private Label lannisterJaune;
	@FXML
	private Label tyrellRouge;
	@FXML
	private Label tyrellBleu;
	@FXML
	private Label tyrellVert;
	@FXML
	private Label tyrellJaune;
	@FXML
	private Label targaryenRouge;
	@FXML
	private Label targaryenBleu;
	@FXML
	private Label targaryenVert;
	@FXML
	private Label targaryenJaune;
	@FXML
	private Label classementUn;
	@FXML
	private Label classementDeux;
	@FXML
	private Label classementTrois;
	@FXML
	private Label classementQuatre;
	@FXML
	private Button quitterJeuBtn;
	
	// FONCTIONS
	
	/**
	 * Fonction permettant d'initialiser le tableau recapitulatif et le classement.
	 * @throws RemoteException 
	 */
	public void initialiser() throws RemoteException{
		this.remplirTableauRecaputulatif();
		this.remplirClassement();
	}
	
	/**
	 * Fonction retournant le plus petit parmis 4 entiers
	 */
	public int plusPetit(int a, int b, int c, int d){
		int min = a;
		if(b < min)
			min = b;
		if(c < min)
			min = c;
		if(d < min)
			min = d;
		return min;
	}
	
	/**
	 * Fonction permettant de remplir le tableau recapitulatif des points
	 * @throws RemoteException
	 */
	public void remplirTableauRecaputulatif() throws RemoteException{
		ArrayList<InterfaceServeurClient> listeClients = this.mainApp.getServeur().getClients();
		for(InterfaceServeurClient client : listeClients)
		{
			if(client.getJoueur().getDynastie().getNom().equals("Stark")){
				this.starkBleu.setText(client.getJoueur().getPointsVictoireBleu()+"");
				this.starkRouge.setText(client.getJoueur().getPointsVictoireRouge()+"");
				this.starkVert.setText(client.getJoueur().getPointsVictoireVert()+"");
				this.starkJaune.setText(client.getJoueur().getPointsVictoireJaune()+"");
				this.minStark = this.plusPetit(client.getJoueur().getPointsVictoireBleu(), client.getJoueur().getPointsVictoireRouge(), client.getJoueur().getPointsVictoireVert(), client.getJoueur().getPointsVictoireJaune());
				this.joueurStark = client.getJoueur();
			}else{
				if(client.getJoueur().getDynastie().getNom().equals("Lanister")){
					this.lannisterBleu.setText(client.getJoueur().getPointsVictoireBleu()+"");
					this.lannisterRouge.setText(client.getJoueur().getPointsVictoireRouge()+"");
					this.lannisterVert.setText(client.getJoueur().getPointsVictoireVert()+"");
					this.lannisterJaune.setText(client.getJoueur().getPointsVictoireJaune()+"");
					this.minLannister = this.plusPetit(client.getJoueur().getPointsVictoireBleu(), client.getJoueur().getPointsVictoireRouge(), client.getJoueur().getPointsVictoireVert(), client.getJoueur().getPointsVictoireJaune());
					this.joueurLannister = client.getJoueur();
				}else{
					if(client.getJoueur().getDynastie().getNom().equals("Tyrell")){
						this.tyrellBleu.setText(client.getJoueur().getPointsVictoireBleu()+"");
						this.tyrellRouge.setText(client.getJoueur().getPointsVictoireRouge()+"");
						this.tyrellVert.setText(client.getJoueur().getPointsVictoireVert()+"");
						this.tyrellJaune.setText(client.getJoueur().getPointsVictoireJaune()+"");
						this.minTyrell = this.plusPetit(client.getJoueur().getPointsVictoireBleu(), client.getJoueur().getPointsVictoireRouge(), client.getJoueur().getPointsVictoireVert(), client.getJoueur().getPointsVictoireJaune());
						this.joueurTyrell = client.getJoueur();
					}else{
						if(client.getJoueur().getDynastie().getNom().equals("Targaryen")){
							this.targaryenBleu.setText(client.getJoueur().getPointsVictoireBleu()+"");
							this.targaryenRouge.setText(client.getJoueur().getPointsVictoireRouge()+"");
							this.targaryenVert.setText(client.getJoueur().getPointsVictoireVert()+"");
							this.targaryenJaune.setText(client.getJoueur().getPointsVictoireJaune()+"");
							this.minTargaryen = this.plusPetit(client.getJoueur().getPointsVictoireBleu(), client.getJoueur().getPointsVictoireRouge(), client.getJoueur().getPointsVictoireVert(), client.getJoueur().getPointsVictoireJaune());
							this.joueurTargaryen = client.getJoueur();
						}
					}
				}
			}
		}
	}
	
	/**
	 * Fonction permettant de faire le classement de fin de partie
	 */
	public void remplirClassement(){
		TreeMap<Joueur, Integer> classement = new TreeMap<Joueur, Integer>();
		if(this.joueurLannister != null)
			classement.put(this.joueurLannister, this.minLannister);
		if(this.joueurTyrell != null)
			classement.put(this.joueurTyrell, this.minTyrell);
		if(this.joueurStark != null)
			classement.put(this.joueurStark, this.minStark);
		if(this.joueurTargaryen != null)
			classement.put(this.joueurTargaryen, this.minTargaryen);
		// Là, on a les joueurs de la partie avec leur score minimale dans le TreeMap.
		// Il faut trier
		
		// On affiche sur l'interface le classement final
		this.classementUn.setText(this.joueurLannister+"");
		this.classementDeux.setText(this.joueurLannister+"");
		this.classementTrois.setText(this.joueurLannister+"");
		this.classementQuatre.setText(this.joueurLannister+"");
	}
	

}
