package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.connexion;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

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
	public InterfaceServeurClient getServeur() throws RemoteException;
	public String getNamespace() throws RemoteException;
	public String getNomJoueur() throws RemoteException;
	public int getIdObjetPartie() throws RemoteException;
	public void setIdObjetPartie(int idObjetPartie) throws RemoteException;
	//test send
	public void send(String string, int idClient) throws RemoteException;

}
