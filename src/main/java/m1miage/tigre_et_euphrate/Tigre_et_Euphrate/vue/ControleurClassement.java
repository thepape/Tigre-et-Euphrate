package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.vue;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.connexion.InterfaceServeurClient;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeMap;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;


/**
 * Controlleur de l'interface du classement
 * @author
 *
 */
public class ControleurClassement implements ChangeListener{
	
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
	
	private ArrayList<Joueur> joueurs;
	
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
		ArrayList<InterfaceServeurClient> listeClients = MainApp.getInstance().getServeur().getClients();
		this.joueurs = new ArrayList<Joueur>();
		
		for(InterfaceServeurClient client : listeClients){
			joueurs.add(client.getJoueur());
		}
		
		Platform.runLater(new Runnable(){

			public void run() {
				System.out.println("Initialisation des points");
				for(Joueur joueur : joueurs)
				{
					if(joueur.getDynastie().getNom().equals("Stark")){
						starkBleu.setText(joueur.getPointVictoireBleu()+"");
						starkRouge.setText(joueur.getPointVictoireRouge()+"");
						starkVert.setText(joueur.getPointVictoireVert()+"");
						starkJaune.setText(joueur.getPointVictoireJaune()+"");
						minStark = plusPetit(joueur.getPointVictoireBleu(), joueur.getPointVictoireRouge(), joueur.getPointVictoireVert(), joueur.getPointVictoireJaune());
						joueurStark = joueur;
					}else{
						if(joueur.getDynastie().getNom().equals("Lanister")){
							lannisterBleu.setText(joueur.getPointVictoireBleu()+"");
							lannisterRouge.setText(joueur.getPointVictoireRouge()+"");
							lannisterVert.setText(joueur.getPointVictoireVert()+"");
							lannisterJaune.setText(joueur.getPointVictoireJaune()+"");
							minLannister = plusPetit(joueur.getPointVictoireBleu(), joueur.getPointVictoireRouge(), joueur.getPointVictoireVert(), joueur.getPointVictoireJaune());
							joueurLannister = joueur;
						}else{
							if(joueur.getDynastie().getNom().equals("Tyrell")){
								tyrellBleu.setText(joueur.getPointVictoireBleu()+"");
								tyrellRouge.setText(joueur.getPointVictoireRouge()+"");
								tyrellVert.setText(joueur.getPointVictoireVert()+"");
								tyrellJaune.setText(joueur.getPointVictoireJaune()+"");
								minTyrell = plusPetit(joueur.getPointVictoireBleu(), joueur.getPointVictoireRouge(), joueur.getPointVictoireVert(), joueur.getPointVictoireJaune());
								joueurTyrell = joueur;
							}else{
								if(joueur.getDynastie().getNom().equals("Targaryen")){
									targaryenBleu.setText(joueur.getPointVictoireBleu()+"");
									targaryenRouge.setText(joueur.getPointVictoireRouge()+"");
									targaryenVert.setText(joueur.getPointVictoireVert()+"");
									targaryenJaune.setText(joueur.getPointVictoireJaune()+"");
									minTargaryen = plusPetit(joueur.getPointVictoireBleu(), joueur.getPointVictoireRouge(), joueur.getPointVictoireVert(), joueur.getPointVictoireJaune());
									joueurTargaryen = joueur;
								}
							}
						}
					}
				}
				
			}
			
		});
		
		
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
		Collections.sort(this.joueurs, new Comparator<Joueur>(){

			public int compare(Joueur j1, Joueur j2) {
				ArrayList<Integer> points0 = new ArrayList<Integer>();
				ArrayList<Integer> points1 = new ArrayList<Integer>();
				points0.add(j1.getPointVictoireBleu());
				points0.add(j1.getPointVictoireRouge());
				points0.add(j1.getPointVictoireJaune());
				points0.add(j1.getPointVictoireVert());
				points1.add(j2.getPointVictoireBleu());
				points1.add(j2.getPointVictoireRouge());
				points1.add(j2.getPointVictoireJaune());
				points1.add(j2.getPointVictoireVert());
				
				Collections.sort(points0);
				Collections.sort(points1);
				
				int min0 = points0.get(0);
				int min1 = points1.get(0);
				
				if(min0 > min1){
					return 1;
				}
				else if(min0 < min1){
					return -1;
				}
				else{
					return 0;
				}
			}
			
		});
		
		Platform.runLater(new Runnable(){

			public void run() {
				// On affiche sur l'interface le classement final
				classementUn.setText(joueurs.get(0).getNom()+" ["+joueurs.get(0).getDynastie().getNom()+"]");
				classementDeux.setText(joueurs.get(1).getNom()+" ["+joueurs.get(1).getDynastie().getNom()+"]");
				if(joueurs.size() == 3){
					classementTrois.setText(joueurs.get(2).getNom()+" ["+joueurs.get(2).getDynastie().getNom()+"]");
				}else if(joueurs.size() == 4){
					classementQuatre.setText(joueurs.get(3).getNom()+" ["+joueurs.get(3).getDynastie().getNom()+"]");
			}
			}
			
		});
		
	}
	
	@FXML
	public void revenirMenu(){
		MainApp.getInstance().goToMenuPage();
	}

	public void changed(ObservableValue observable, Object oldValue, Object newValue) {
		// TODO Auto-generated method stub
		
	}
	

}
