package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Position;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;

/**
 * 
 * Classe hérite de la classe Action et permet de deplacer un chef
 *
 */
public class DeplacerChef extends Action {
	
	/**
	 * Chef 
	 */
	private Chef chef;
	
	/**
	 * Position souhaitee
	 */
	private Position position;
	
	/**
	 * @param ppartie
	 * @param pjoueur
	 * @param pchef
	 * @param ppos
	 */
	public DeplacerChef(Partie partie, Joueur joueur, Chef pchef, Position ppos) {
		super(partie, joueur);
		this.chef = pchef;
		this.position = ppos;
	}

	/**
	 * Permet de deplacer une tuile chef sur le plateau
	 * @param pchef chef a deplacer
	 * @param ppos position ou deplacer
	 * @return true si deplace sinon false
	 */
	public boolean deplacerChef(Chef pchef, Position ppos){
		int x = ppos.getX();
		int y = ppos.getY();
		
		if(this.verifierDeplacerChef(pchef, ppos)){
			return false;
		}
		
		this.partie.getPlateauJeu().getPlateau()[x][y] = pchef;
		return true;
	}
	
	/**
	 * Methode pour verifier si il est possible de deplacer un chef sur une position donnée
	 * @param pchef
	 * @param ppos
	 * @return
	 */
	public boolean verifierDeplacerChef(Chef pchef, Position ppos){
		int x = ppos.getX();
		int y = ppos.getY();
		
		if(this.partie.getPlateauJeu().getPlateau()[x][y] != null){
			return false;
		}
		if(this.partie.getPlateauJeu().getPlateauTerrain()[x][y]){
			return false;
		}
		
		boolean ok = this.partie.getPlateauJeu().verifierTemple(ppos);
		
		if(!ok){
			return false;
		}
		
		return true;
	}

	/**
	 * Execute l'action DeplacerChef
	 * @return vrai ou faux, selon le bon déroulement ou non de l'action
	 */
	public boolean executer(){
		if(this.chef.getDynastie() != this.joueur.getDynastie())
			return false;
		return this.deplacerChef(this.chef, this.position);
	}
	
	public boolean verifier(){
		return this.verifierDeplacerChef(chef, position);
	}
}