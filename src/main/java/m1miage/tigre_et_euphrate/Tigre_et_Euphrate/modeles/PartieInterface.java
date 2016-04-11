package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Dynastie;

/**
 * Classe interface de Partie pour l'echange d'informations sur les parties
 *
 */
public interface PartieInterface extends Remote {


	public String getName() throws RemoteException;
	public int getPointTresor() throws RemoteException;
	public int getPointVictoire() throws RemoteException;
	public DeckPublic getDeckPublic() throws RemoteException;
	public void setListeJoueur(ArrayList<PartieInterface> listeClient) throws RemoteException;
	public ArrayList<PartieInterface> getListePartie() throws RemoteException;
	public void ajouterAdversaire(PartieInterface adversaire) throws RemoteException;
	public Joueur getJoueur() throws RemoteException;
	public void setJoueur(Joueur joueur) throws RemoteException;
	public DeckPrive getDeckPrive() throws RemoteException;
	public Dynastie getDynastie() throws RemoteException;
}
