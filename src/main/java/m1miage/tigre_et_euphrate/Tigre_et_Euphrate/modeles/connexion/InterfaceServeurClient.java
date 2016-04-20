package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.connexion;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.DeckPrive;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.DeckPublic;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.PartieInterface;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Dynastie;

public interface InterfaceServeurClient extends Remote {

	public void setServeur(InterfaceServeurClient serveur) throws RemoteException;
	public void setListeClient(ArrayList<InterfaceServeurClient> client) throws RemoteException;
	public Partie getPartie() throws RemoteException;
	public void ajouterClient(InterfaceServeurClient client) throws RemoteException;
	public boolean retirerClient(InterfaceServeurClient client) throws RemoteException;
	public InterfaceServeurClient getServeur() throws RemoteException;
	public String getNamespace() throws RemoteException;
	public String getNomJoueur() throws RemoteException;
	public Joueur getJoueur() throws RemoteException; //Ligne en trop
	public int getIdObjetPartie() throws RemoteException;
	public void setIdObjetPartie(int idObjetPartie) throws RemoteException;
	public void sendDynastieChoisi(String dynastie, int idClient) throws RemoteException;
	public ArrayList<Dynastie> getListeDynastie() throws RemoteException;
	public void setListeDynastie(ArrayList<Dynastie> liste) throws RemoteException;
	public void setJoueur(Joueur j) throws RemoteException;
	
	public boolean deconnecter() throws RemoteException;
	
	public void notifierChangement(Object arg) throws RemoteException;
	public void addListener(ChangeListener listener) throws RemoteException;
	public void removeListener(ChangeListener listener) throws RemoteException;
	//test send
	public void send(String string, int idClient) throws RemoteException;
	public ArrayList<InterfaceServeurClient> getClients() throws RemoteException;

	public void switchJoueurEstPret(InterfaceServeurClient client) throws RemoteException;
	public void switchJoueurPret() throws RemoteException;
}
