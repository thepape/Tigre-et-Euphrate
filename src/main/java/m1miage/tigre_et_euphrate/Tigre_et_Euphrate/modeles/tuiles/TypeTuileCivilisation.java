package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles;

import java.io.Serializable;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.TypeChef;

/**
 *
 * Classe représentant un type de tuile civilisation.
 *
 */
public class TypeTuileCivilisation implements Serializable {

	/**
	 * Fournit une instance du type de tuile "Ferme"
	 */
	public static final TypeTuileCivilisation Ferme = new TypeTuileCivilisation("bleu","Ferme", "tuile_ferme.png");

	/**
	 * Fournit une instance du type de tuile "Marché"
	 */
	public static final TypeTuileCivilisation Marché = new TypeTuileCivilisation("vert","Marché", "tuile_marche.png");

	/**
	 * Fournit une instance du type de tuile "Temple"
	 */
	public static final TypeTuileCivilisation Temple = new TypeTuileCivilisation("rouge","Temple", "tuile_temple.png");

	/**
	 * Fournit une instance du type de tuile "Population"
	 */
	public static final TypeTuileCivilisation Population = new TypeTuileCivilisation("jaune","Population", "tuile_population.png");

	/**
	 * Nom de la couleur du type tuile civilisation (en minuscules).
	 */
	private String couleur;

	/**
	 * Nom du type de tuile civilisation.
	 */
	private String nom;

	/**
	 * Url de l'image dans le projet
	 */
	private String urlImage;

	/**
	 * Constructeur d'un type de tuile civilisation.
	 * @param pCouleur nom de la couleur du type (en minuscules).
	 */
	public TypeTuileCivilisation(String pCouleur, String pNom){
		this.couleur = pCouleur.toLowerCase();
		this.nom = pNom;
	}

	public TypeTuileCivilisation(String pCouleur, String pNom, String pUrlImage){
		this.couleur = pCouleur.toLowerCase();
		this.nom = pNom;
		this.urlImage = pUrlImage;
	}

	/**
	 * Retourne la couleur du type de tuile.
	 * @return couleur du type de tuile.
	 */
	public String getCouleur(){
		return this.couleur;
	}

	/**
	 * Retourne le nom du type de tuile.
	 * @return nom du type de tuile.
	 */
	public String getNom(){
		return this.nom;
	}

	/**
	 * getter de l'url de l'image
	 * @return urlImage
	 */
	public String getUrlImage() {
		return urlImage;
	}

	/**
	 * setter de l'urlImage
	 * @param urlImage
	 */
	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
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
	 * Retourne une copie de cette instance de type tuile civilisation.
	 */
	public TypeTuileCivilisation clone(){
		return new TypeTuileCivilisation(this.couleur, this.nom);
	}

}
