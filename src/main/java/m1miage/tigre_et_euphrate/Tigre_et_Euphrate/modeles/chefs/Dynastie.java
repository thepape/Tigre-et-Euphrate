package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs;

/**
 * Class représentant une Dynastie. une dynastie est unique à chaque joueur.
 * les dynasties sont : Lanister, Starks, Tyrell, Targaryens 
 * @author jerome
 *
 */
public class Dynastie {

	/**
	 * Fournit une instance de la dynastie "Lanister"
	 */
	public static final Dynastie Lanister = new Dynastie("Lanister","dynastie_lanister.png");

	/**
	 * Fournit une instance de la dynastie "Stark"
	 */
	public static final Dynastie Stark = new Dynastie("Stark","dynastie_stark.png");

	/**
	 * Fournit une instance de la dynastie "Tyrell"
	 */
	public static final Dynastie Tyrell = new Dynastie("Tyrell","dynastie_tyrell.png");

	/**
	 * Fournit une instance de la dynastie "Targaryens"
	 */
	public static final Dynastie Targaryen = new Dynastie("Targaryen","dynastie_targaryen.png");


	/**
	 * Nom de la dynastie
	 */
	private String nom;
	
	/**
	 * Lien vers l'image
	 */
	private String lien;

	
	/**
	 * Constructeur d'une dynastie
	 * @param pnom nom de la dynastie
	 * @param plien lien vers l'image de la dynastie
	 */
	public Dynastie(String pnom, String plien) {
		super();
		this.nom = pnom;
		this.lien = plien;
	}
	
	/**
	 * Constructeur vide
	 */
	public Dynastie(){
		this.nom = "non defini";
		this.lien = "non defini";
	}


	/**
	 * Return le nom de la dynastie
	 * @return
	 */
	public String getNom() {
		return nom;
	}


	/**
	 * return le lien vers l'image de la dynastie
	 * @return
	 */
	public String getLien() {
		return lien;
	}

	

}