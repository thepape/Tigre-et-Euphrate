package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles;

/**
 * 
 * Classe représentant une Tuile. Une Tuile est un objet placable sur une case de plateau.
 * A la différence d'un chef, une tuile ne peut pas être déplacée d'une case à une autre.
 * Elle peut être placée une fois, puis retirée du jeu.
 *
 */
public abstract class Tuile extends Placable {
	
	/**
	 * Définit si la tuile doit être placée sur une case d'eau.
	 * Si non, elle doit être placée sur une case de terre.
	 */
	private boolean tuileEau;
	
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
	 * Retire la tuile du jeu.
	 */
	public void retirer(){
		this.position = null;
		this.retiree = true;
	}
	
	/**
	 * Indique si cette tuile est une tuile à placer sur une case d'eau.
	 * @return vrai si la tuile doit être placée sur une case d'eau.
	 */
	public boolean estTuileEau(){
		return this.tuileEau;
	}
	
	/**
	 * Indique si la tuile est retirée du jeu.
	 * @return vrai si la tuile est retirée du jeu.
	 */
	public boolean estRetiree(){
		return this.retiree;
	}
}
