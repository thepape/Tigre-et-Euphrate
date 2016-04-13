package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.connexion;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.PartieInterface;

public class Serveur implements Runnable
{
	private Partie partie;
	
	private ArrayList<PartieInterface> clients;
	
	private boolean lance = false;
	
	private int port;
	
	private String namespace;
	
	private Registry registry;
	
	private String url;
	
	public Serveur()
	{
		this.clients = new ArrayList<PartieInterface>();
		
		this.port = 42000;
		this.namespace = "Tigre-et-euphrate";
	}
	
	public void initialiser(){
		

		
		try {
			partie = new Partie();
			partie.setServeur(this);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//lancement du registre
		try {
			this.registry = LocateRegistry.createRegistry(this.port);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.setSecurityManager(null);
		
		System.setProperty("java.security.policy", "file:/security.policy");
		System.setProperty("java.rmi.server.hostname", "127.0.0.1");
		
		
		this.url = "rmi://localhost:"+this.port+"/"+this.namespace;
		try {
			Naming.rebind(url, partie);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Serveur lancé !");
		
		this.lance = true;
	}
	
	public void attendreJoueursPrets()
	{
		while(!partie.tousLesJoueursPrets())
		{
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void run() {
		// TODO Auto-generated method stub
		
		this.initialiser();
		
		this.attendreJoueursPrets();
		
		
	}
	
	public void shutDown()
	{
		try {
			Naming.unbind(this.url);
			UnicastRemoteObject.unexportObject(registry, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Partie getPartie(){
		return this.partie;
	}

	public ArrayList<PartieInterface> getClients() {
		return clients;
	}

	public void setClients(ArrayList<PartieInterface> clients) {
		this.clients = clients;
	}

	public boolean isLance() {
		return lance;
	}

	public void setLance(boolean lance) {
		this.lance = lance;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	
	public void ajouterClient(PartieInterface pClient){
		this.clients.add(pClient);
	}
}
