package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles;

import java.io.Serializable;

/**
 *
 * Classe représentant une position sur le plateau. Cette classe n'est pas liée à la classe Plateau.
 *
 */
public class Position implements Serializable {

	/**
	 * Coordonnée en abscisse (X) de la position.
	 */
	private int X;

	/**
	 * Coordonnée en ordonnée (Y) de la position.
	 */
	private int Y;

	/**
	 * Constructeur d'une position à partir des coordonnées en abscisse et en ordonnée.
	 * @param pX coordonnée en abscisse.
	 * @param pY coordonnée en ordonnée.
	 */
	public Position(int pX, int pY){
		this.X = pX;
		this.Y = pY;
	}

	/**
	 * Constructeur d'une position à partir d'une autre position.
	 * @param pPosition
	 */
	public Position(Position pPosition){
		this.X = pPosition.X;
		this.Y = pPosition.Y;
	}

	/**
	 * Méthode permettant de récupérer la coordonnée en abscisse de la position.
	 * @return coordonnée en abscisse.
	 */
	public int getX(){
		return this.X;
	}

	/**
	 * Méthode permettant de récupérer la coordonnée en ordonnée de la position.
	 * @return coordonnée en ordonnée.
	 */
	public int getY(){
		return this.Y;
	}

	/**
	 * Méthode vérifiant si deux positions sont équivalantes, i.e. leurs coordonnées sont identiques.
	 * @param pPosition Position à comparer.
	 * @return vrai si les deux position sont équivalantes.
	 */
	public boolean equals(Position pPosition){
		return this.X == pPosition.X && this.Y == pPosition.Y;
	}

	/**
	 * Méthode vérifiant si deux positions sont adjacentes, i.e. elles ont un bord de case en commun.
	 * @param pPosition Position dont l'adjacence est à vérifier.
	 * @return vrai si les deux positions sont adjacentes.
	 */
	public boolean estAdjacente(Position pPosition){
		int diffX = Math.abs(this.X - pPosition.X);
		int diffY = Math.abs(this.Y - pPosition.Y);

		return (diffX == 1 && diffY == 0) || (diffX == 0 && diffY == 1);
	}
}
