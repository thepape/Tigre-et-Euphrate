package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles;

/**
 * 
 * Classe représentant un objet placable sur une case de plateau. Deux placables ou plus ne peuvent pas être empilées sur une même case.
 *
 */
public abstract class Placable {
	
	/**
	 * Incrément de l'id des objets placables.
	 */
	protected static int idIncrement = 1;
	
	/**
	 * Id de l'objet placable.
	 */
	protected int id;
	
	/**
	 * Position de l'objet placable.
	 */
	protected Position position;
	
	/**
	 * Getter de la position du placable.
	 * @return position du placable.
	 */
	public Position getPosition(){
		return this.position;
	}
	
	/**
	 * Vérifie si deux placables sont adjacents.
	 * @param pPlacable dont l'adjacence est à vérifier.
	 * @return vrai si les deux placables sont adjacents.
	 */
	public boolean estAdjacent(Placable pPlacable){
		return this.getPosition().estAdjacente(pPlacable.getPosition());
	}
	
	/**
	 * Retourne l'id de l'objet placable.
	 * @return id du placable.
	 */
	public int getId(){
		return this.id;
	}
}
