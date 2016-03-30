package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Placable;

/**
 * Class représentant un chef. Un chef peut etre placé, déplacé ou retiré
 * Il ne doit pas réunir deux royaumes !!!
 * @author jerome
 *
 */
public class Chef extends Placable{

	/**
	 * Type du chef
	 */
	private TypeChef typeChef;

	/**
	 * Joueur auquel il correspond
	 */
	private Joueur joueur;

	/**
	 * Dynastie du chef
	 */
	private Dynastie dynastie;

	/**
	 * le nombres de points gagnés par le chef
	 * par rapport aux nombres de tuiles de
	 * meme couleur que la sienne.
	 * (Sorte de compteur)
	 */
	private int puissance;

	/**
	 * Boolean pour savoir si le chef est sur le plateau (False)
	 * ou dans le deck ( True )
	 */
	private boolean retiree;


	/**
	 * Constructeur d'un chef
	 * @param ptypeChef type du chef
	 * @param pjoueur joueur auquel il correspond
	 */
	public Chef(TypeChef ptypeChef, Joueur pjoueur) {
		super();
		this.typeChef = ptypeChef.clone(); //clone pour eviter le couplage
		this.joueur = pjoueur;
		this.dynastie = pjoueur.getDynastie();
		this.puissance = 0;
		this.retiree = true;
		this.id = Placable.idIncrement++;
	}

	/**
	 * Constructeur vide
	 */
	public Chef() {
		super();
		this.typeChef = null; //clone pour eviter le couplage
		this.joueur = null;
		this.dynastie = null;
		this.puissance = 0;
		this.retiree = true;
		this.id = Placable.idIncrement++;
	}



	/**
	 * return le type chef
	 * @return
	 */
	public TypeChef getTypeChef() {
		return typeChef;
	}

	/**
	 * return le joueur auquel le chef correspond
	 * @return
	 */
	public Joueur getJoueur() {
		return joueur;
	}

	/**
	 * return la dynastie du chef
	 * @return
	 */
	public Dynastie getDynastie() {
		return dynastie;
	}


	/**
	 * return la puissance du chef
	 * @return
	 */
	public int getPuissance() {
		return puissance;
	}


	/**
	 * Met à jour la puissance d'un chef
	 * @param puissance
	 */
	public void setPuissance(int puissance) {
		this.puissance = puissance;
	}


	/**
	 * Return true si le chef est dans le deck
	 * Return false si le chef est sur le plateau
	 * @return
	 */
	public boolean isRetiree() {
		return retiree;
	}


	/**
	 * Met à joueur le placement du chef ( deck ou platau )
	 * False pour mettre le chef sur le plateau
	 * True pour mettre le chef dans le deck
	 * @param retiree
	 */
	public void setRetiree(boolean retiree) {
		this.retiree = retiree;
	}



}
