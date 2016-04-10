package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Classe interface de Partie pour l'echange d'informations sur les parties
 *
 */
public interface PartieInterface extends Remote{

	
	public String getName() throws RemoteException;
	public int getPointTresor() throws RemoteException;
	public int getPointVictoire() throws RemoteException;
	public DeckPublic getDeckPublic() throws RemoteException;
}
