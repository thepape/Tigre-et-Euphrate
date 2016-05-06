package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action;

import java.rmi.RemoteException;
import java.util.ArrayList;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Position;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Territoire;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.connexion.InterfaceServeurClient;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCatastrophe;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TypeTuileCivilisation;

/**
 * 
 * Classe hérite de la classe Action et permet de placer une tuile catastrophe
 *
 */
public class PlacerTuileCatastrophe extends Action {
	
	/**
	 * Catastrophe
	 */
	private TuileCatastrophe tuileCatastrophe;

	/**
	 * Position 
	 */
	private Position position;

	/**
	 * @param partie
	 * @param joueur
	 */
	public PlacerTuileCatastrophe(Partie ppartie, Joueur pjoueur, TuileCatastrophe pTuile, Position ppos) {
		super(ppartie, pjoueur);
		this.tuileCatastrophe = pTuile;
		this.position = ppos;
	}
	
	public boolean VerifierCase(){
		
		boolean ok = false;
		int x = this.position.getX();
		int y = this.position.getY();
		//getClass().isInstance(TuileCivilisation.class)
		/*if(this.partie.getPlateauJeu().getPlateau()[x][y]==null ||
				((TuileCivilisation)this.partie.getPlateauJeu().getPlateau()[x][y]).getType().equals(TypeTuileCivilisation.Marché) || 
				((TuileCivilisation)this.partie.getPlateauJeu().getPlateau()[x][y]).getType().equals(TypeTuileCivilisation.Ferme) || 
				((TuileCivilisation)this.partie.getPlateauJeu().getPlateau()[x][y]).getType().equals(TypeTuileCivilisation.Population) || 
				((TuileCivilisation)this.partie.getPlateauJeu().getPlateau()[x][y]).getType().equals(TypeTuileCivilisation.Temple) ){*/
		if(this.partie.getPlateauJeu().getPlateau()[x][y]==null || 
				((this.partie.getPlateauJeu().getPlateau()[x][y] instanceof TuileCivilisation)
				&&((TuileCivilisation) this.partie.getPlateauJeu().getPlateau()[x][y]).estTuileMonument()==false)){	
			ok = true;
		}
		return ok;
	}
	

	/**
	 * Execute l'action PlacerTuileCatastrophe
	 * On place ces tuiles soit sur une case vide, soit sur une tuile Civilisation.
	 * @return vrai ou faux, selon le bon déroulement ou non de l'action
	 * @throws RemoteException 
	 */
	public boolean executer(){
		boolean ok = false;
		if(this.verifier()){
			ok = true;
			this.partie.getPlateauJeu().getPlateau()[this.position.getX()][this.position.getY()]=this.tuileCatastrophe;
			this.tuileCatastrophe.setPosition(this.position);
			this.partie.getPlateauJeu().reconstruireTerritoires(this.tuileCatastrophe.getPosition());
			this.joueur.getDeckPublic().getListeTuileCatastrophe().remove(this.tuileCatastrophe);
			
			//verifier si des joueurs doivent retourner dans leurs decks apres la pose
			ArrayList<Chef> chefsAdjacents = this.partie.getPlateauJeu().recupererListeChefsAdjacente(this.position);
			for(Chef chef : chefsAdjacents){
				//on verifie si un temple se trouve encore a coté du chef
				ArrayList<TuileCivilisation> tuilesAdj = this.partie.getPlateauJeu().recupererListeTuileCivilisationAdjacente(chef.getPosition());
				boolean templeAdj = false;
				for(TuileCivilisation tuileCiv : tuilesAdj){
					if(tuileCiv.getType().equals(TypeTuileCivilisation.Temple)){
						templeAdj = true;
						break;
					}
				}
				
				if(!templeAdj){
					//si aucun temple adjacent au chef suite au placement de la tuile cata, on ejecte le chef
					this.partie.getPlateauJeu().getPlateau()[chef.getPosition().getX()][chef.getPosition().getY()] = null;
					
					
					Joueur possesseur = chef.getJoueur();
					
					//si le chef a virer appartient au joueur ayant joué l'action, on modifie ddirectement l'objet
					//joueur ayant joué
					if(possesseur.getId() == this.joueur.getId()){
						this.joueur.getDeckPublic().ajouterChef(chef);
					}
					else{
						//sinon, on modifie le joueur et on l'ajoute dans la liste des autres joueurs a impacter
						possesseur.getDeckPublic().ajouterChef(chef);
						this.ajouterJoueurImpacte(possesseur);
					}
				}
			}
		}
		return ok;
	}
	
	/**
	 * Verifie les conditions de pose
	 */
	public boolean verifier(){
		boolean ok = false;
		if((this.position.getX() > 10 || this.position.getY() > 15) || (this.position.getY() < 0 || this.position.getX() < 0))
		{
			ok = false;
		}
		else
		{
			ok = this.VerifierCase();
		}
		return ok;
	}
	
	public String toString(){
		return this.joueur.getNom()+" a posé une tuile catastrophe à la ligne "+this.position.getX()+", colonne "+this.position.getY();
	}
}