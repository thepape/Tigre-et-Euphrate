package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Placable;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Position;

/**
 * 
 * Classe représentant une Tuile. Une Tuile est un objet placable sur une case de plateau.
 * A la différence d'un chef, une tuile ne peut pas être déplacée d'une case à une autre.
 * Elle peut être placée une fois, puis retirée du jeu.
 *
 */
public abstract class Tuile extends Placable {
	
	
	
	/**
	 * Définit si la tuile est retirée du jeu définitivement.
	 */
	private boolean retiree = false;
	
	/**
	 * Méthode pour placer la tuile sur une case du plateau.
	 * @param pPosition
	 */
	public void placer(Position pPosition){
		
		if(this.getPosition() == null && !this.retiree){
			this.position = pPosition;
		}
	}
	
	/**
	 * Méthode pour placer la tuile sur une case du plateau.
	 * @param pX abscisse
	 * @param pY ordonnée
	 */
	public void placer(int pX, int pY){
		this.placer(new Position(pX,pY));
	}
	
	/**
	 * Retire la tuile du jeu.
	 */
	public void retirer(){
		this.position = null;
		this.retiree = true;
	}
	
	
	
	/**
	 * Indique si la tuile est retirée du jeu.
	 * @return vrai si la tuile est retirée du jeu.
	 */
	public boolean estRetiree(){
		return this.retiree;
	}
}
