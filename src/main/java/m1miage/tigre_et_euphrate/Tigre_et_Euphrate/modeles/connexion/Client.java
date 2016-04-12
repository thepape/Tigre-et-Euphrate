package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.connexion;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.PartieInterface;


public class Client{
	
	private String ip = null;
	
	private int port;
	
	private String namespace;
	
	private String nomJoueur = null;
	
	private Joueur joueur = null;
	
	public PartieInterface partie = null;
	
	public PartieInterface serveur = null;
	
	public Client(String pIp, String pNomJoueur){
		this.nomJoueur = pNomJoueur;
		this.ip = pIp;
		
		this.port = 42000;
		this.namespace = "Tigre-et-euphrate";
	}
	
	public Joueur getJoueur()
	{
		return this.joueur;
	}
	
	public void connect()
	{
		System.setSecurityManager(null);
		System.setProperty("java.security.policy", "file:/security.policy");
		System.setProperty("java.rmi.server.hostname", "127.0.0.1");
		
		/////////////// infos de connexion
		
		String url = "rmi://"+this.ip+":"+this.port+"/"+this.namespace;
		PartieInterface partie = null;
		
		try {
			partie = (PartieInterface) Naming.lookup(url);
			this.serveur = partie;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("ERREUR : Serveur introuvable.");
			
		}
		
		System.out.println("Client connecté au serveur !");
		
	}
	
	public void rejoindrePartie()
	{
		
		try {
			
			//on initialise la partie coté client
			this.partie = new Partie();
			this.partie.setNomJoueur(nomJoueur);
			//on envoie la partie au serveur pour récuperer une instance de joueur
			this.joueur = this.serveur.ajouterJoueur(this.partie);
			//on bind le joueur retourné par le serveur a la partie
			this.partie.setJoueur(joueur);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void attendreLancementServeur(Serveur pServeur)
	{
		while(!pServeur.isLance())
		{
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
