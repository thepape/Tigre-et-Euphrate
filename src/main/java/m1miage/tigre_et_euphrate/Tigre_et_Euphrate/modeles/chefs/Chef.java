package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonTypeName;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Placable;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Territoire;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;

/**
 * Class représentant un chef. Un chef peut etre placé, déplacé ou retiré
 * Il ne doit pas réunir deux royaumes !!!
 * @author jerome
 *
 */
@JsonTypeName("chef")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Chef extends Placable implements Serializable{

	/**
	 * Type du chef
	 */
	@JsonProperty("typeChef")
	private TypeChef typeChef;

	/**
	 * Joueur auquel il correspond
	 */
	@JsonIgnore
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
	 * Territoire dans lequel le chef se trouve
	 */
	//private Territoire territoire;

	/**
	 * Constructeur d'un chef
	 * @param ptypeChef type du chef
	 * @param pjoueur joueur auquel il correspond
	 */
	public Chef(TypeChef ptypeChef, Joueur pjoueur) {
		super();
		this.typeChef = ptypeChef; //clone pour eviter le couplage
		this.joueur = pjoueur;
		this.dynastie = pjoueur.getDynastie();
		this.puissance = 0;
		this.retiree = true;
		//this.territoire = null;
		Chef.idIncrement += 1;
		this.id = Chef.idIncrement;
	}

	/**
	 * Constructeur d'un chef seulement par son typeChef
	 * @param typeChef
	 */
	public Chef(TypeChef typeChef) {
		super();
		this.typeChef = typeChef;
		Chef.idIncrement += 1;
		this.id = Chef.idIncrement;
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
		//this.territoire = null;
		Chef.idIncrement += 10;
		this.id = Chef.idIncrement;
	}



	/**
	 * return le type chef
	 * @return
	 */
	@JsonProperty("typeChef")
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

	public void setJoueur(Joueur joueur){
		this.joueur = joueur;
	}

	/**
	 * return la dynastie du chef
	 * @return
	 */
	public Dynastie getDynastie() {
		return dynastie;
	}

	public void setDynastie(Dynastie dynastie)
	{
		this.dynastie = dynastie;
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
