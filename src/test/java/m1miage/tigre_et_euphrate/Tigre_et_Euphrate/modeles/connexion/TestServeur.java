package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.connexion;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

public class TestServeur {

	private Serveur serveur;

	@Before
	public void initialiser()
	{
		try
		{
			this.serveur = new Serveur();
		} catch(RemoteException e)
		{
			e.printStackTrace();
		}
	}

	@Test
	public void testAjoutClient() {
		InterfaceServeurClient client1;
		try
		{
			client1 = new Client("000", "nomJoueur1");
			serveur.ajouterClient(client1);
		} catch(RemoteException e)
		{
			e.printStackTrace();
		}

		assertEquals(serveur.getClients().size(), 1);
	}

	@Test
	public void testAjouterClientPlus4() {
		InterfaceServeurClient client1;
		InterfaceServeurClient client2;
		InterfaceServeurClient client3;
		InterfaceServeurClient client4 = null;
		InterfaceServeurClient client5;

		try
		{
			client1 = new Client("000", "nomJoueur1");
			client2 = new Client("000", "nomJoueur2");
			client3 = new Client("000", "nomJoueur3");
			client4 = new Client("000", "nomJoueur4");
			client5 = new Client("000", "nomJoueur5");
			serveur.ajouterClient(client1);
			serveur.ajouterClient(client2);
			serveur.ajouterClient(client3);
			serveur.ajouterClient(client4);
			serveur.ajouterClient(client5);
		} catch(RemoteException e)
		{
			e.printStackTrace();
		}

		assertEquals(serveur.getClients().size(), 4);
		assertSame(serveur.getClients().get(serveur.getClients().size() - 1), client4);
	}

}
