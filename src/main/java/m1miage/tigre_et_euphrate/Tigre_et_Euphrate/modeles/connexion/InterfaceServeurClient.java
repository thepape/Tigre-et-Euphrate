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
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action.Action;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Dynastie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.conflit.Conflits;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;

public interface InterfaceServeurClient extends Remote {

	public void setServeur(InterfaceServeurClient serveur) throws RemoteException;
	public void setListeClient(ArrayList<InterfaceServeurClient> client) throws RemoteException;
	public Partie getPartie() throws RemoteException;
	public void ajouterClient(InterfaceServeurClient client) throws RemoteException;
	public boolean retirerClient(InterfaceServeurClient client) throws RemoteException;
	public InterfaceServeurClient getServeur() throws RemoteException;
	public String getNamespace() throws RemoteException;
	public String getNomJoueur() throws RemoteException;
	public int getIdObjetPartie() throws RemoteException;
	public void setIdObjetPartie(int idObjetPartie) throws RemoteException;
	public ArrayList<Dynastie> getListeDynastie() throws RemoteException;
	public void setListeDynastie(ArrayList<Dynastie> liste) throws RemoteException;
	public ArrayList<Dynastie> getListeDynastieDispo() throws RemoteException;
	public void setJoueur(Joueur j) throws RemoteException;

	public boolean deconnecter() throws RemoteException;

	public void notifierChangement(ArrayList<Object> args) throws RemoteException;
	public void addListener(ChangeListener listener) throws RemoteException;
	public void removeListener(ChangeListener listener) throws RemoteException;
	public void clearListeners() throws RemoteException;
	public Joueur getJoueur() throws RemoteException;
	public void setPartieCourante(Partie partie) throws RemoteException;
	public ArrayList<InterfaceServeurClient> getClients() throws RemoteException;
	public void send(Action action, int idClient) throws RemoteException;
	public void passerTour() throws RemoteException;

	public void switchJoueurEstPret(InterfaceServeurClient client) throws RemoteException;
	public void switchJoueurPret() throws RemoteException;
	public boolean setDynastieOfClient(InterfaceServeurClient client, Dynastie dynastie) throws RemoteException;
	public void setDynastie(Dynastie d) throws RemoteException;
	public void libererDynastie(Dynastie d) throws RemoteException;
	public int getUniqueId() throws RemoteException;
	
	public void envoyerNouveauConflit(Conflits conflit, int idClient) throws RemoteException;
	public void envoyerRenforts(ArrayList<TuileCivilisation> renforts, Joueur joueur) throws RemoteException;
	public boolean piocherCartesManquantes(Joueur j) throws RemoteException;
	public void finirPartie() throws RemoteException;
}
