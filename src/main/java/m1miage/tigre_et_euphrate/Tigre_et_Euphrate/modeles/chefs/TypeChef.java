package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs;

import java.io.Serializable;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TypeTuileCivilisation;

/**
 *
 * Classe représentant un type de chef
 *
 */
public class TypeChef implements Serializable{

	/**
	 * Fournit une instance du type de chef "Roi"
	 */
	public static final TypeChef Roi = new TypeChef("jaune","Roi", "jaune.png");

	/**
	 * Fournit une instance du type de chef "Prêtre"
	 */
	public static final TypeChef Pretre = new TypeChef("rouge","Prêtre", "rouge.png");

	/**
	 * Fournit une instance du type de chef "Fermier"
	 */
	public static final TypeChef Fermier = new TypeChef("bleu","Fermier", "bleu.png");

	/**
	 * Fournit une instance du type de chef "Marchand"
	 */
	public static final TypeChef Marchand = new TypeChef("vert","Marchand", "vert.png");

	/**
	 * Nom de la couleur du type de chef.
	 */
	private String couleur;

	/**
	 * Nom du type de chef.
	 */
	private String nom;

	/**
	 * fin de l'url de l'image de la couleur du chef
	 */
	private String finUrlImage;

	/**
	 * Constructeur vide pour JSON
	 */
	private TypeChef() {

	}
	/**
	 * Constructeur d'un type de chef.
	 * @param pCouleur nom de la couleur du type de chef.
	 * @param pNom nom du type de chef.
	 */
	public TypeChef(String pCouleur, String pNom){
		this.couleur = pCouleur.toLowerCase();
		this.nom = pNom;
	}


	/**
	 * Constructeur d'un type chef
	 * @param pCouleur
	 * @param pNom
	 * @param pFinUrlImage
	 */
	public TypeChef(String pCouleur, String pNom, String pFinUrlImage) {
		super();
		this.couleur = pCouleur;
		this.nom = pNom;
		this.finUrlImage = pFinUrlImage;
	}



	/**
	 * getter de la fin de l'url
	 * @return finUrlImage
	 */
	public String getFinUrlImage() {
		return finUrlImage;
	}


	/**
	 * setter de la fin de l'url
	 * @param finUrlImage
	 */
	public void setFinUrlImage(String pFinUrlImage) {
		this.finUrlImage = pFinUrlImage;
	}



	/**
	 * Retourne la couleur du type de chef.
	 * @return
	 */
	public String getCouleur(){
		return this.couleur;
	}

	/**
	 * Retourne le nom du type de chef.
	 * @return
	 */
	public String getNom(){
		return this.nom;
	}

	/**
	 * Méthode vérifiant si l'objet en paramètre est un type de tuile/chef de même couleur.
	 * @param pObjet objet à tester.
	 * @return vrai si l'objet est un type de tuile/chef de même couleur.
	 */
	public boolean equals(Object pObject){

		if(pObject instanceof TypeTuileCivilisation){
			return this.couleur.equals(((TypeTuileCivilisation) pObject).getCouleur());
		}
		else if(pObject instanceof TypeChef){
			return this.couleur.equals(((TypeChef) pObject).getCouleur());
		}
		else{
			return false;
		}

	}

	/**
	 * Retourne une copie du type de chef.
	 */
	public TypeChef clone(){
		return new TypeChef(this.couleur, this.nom);
	}
}
