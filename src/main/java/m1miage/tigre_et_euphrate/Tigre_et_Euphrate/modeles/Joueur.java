package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Dynastie;

/**
 * Classe representant un joueur
 *
 */
public class Joueur implements Serializable{

	@JsonProperty("id")
	private int id;

	private static int incId = 1;

	/**
	 * Le nom du joueur
	 */
	@JsonProperty("nom")
	private String nom;

	/**
	 * Dynastie du joueur
	 */
	@JsonProperty("dynastie")
	private Dynastie dynastie;

	/**
	 * Le deck public du joueur
	 */
	@JsonProperty("deckPublic")
	private DeckPublic deckPublic;

	/**
	 * Nombre de point victoire du joueur
	 */
	@JsonProperty("PointVictoire")
	private int PointVictoire;

	/**
	 * Nombre de point tresor du joueur
	 */
	@JsonProperty("PointTresor")
	private int PointTresor;

	/**
	 * Le deck public du joueur
	 */
	@JsonProperty("deckPrive")
	private DeckPrive deckPrive;

	/**
	 * boolean pour savoir si c'est le tour du joueur ou non
	 */
	@JsonProperty("tour")
	private boolean tour;

	/**
	 * Nombre d'actions restantes
	 */
	//private ArrayList<Action> liste;

	/**
	 * place dans le jeu par tour
	 */
	private int place;

	private boolean pret = false;

	/**
	 * Constructeur vide d'un joueur
	 */
	public Joueur() {
		super();
		this.id = incId++;
	}

	/**
	 * Constructeur d'un joueur
	 * @param pnom nom du joueur
	 * @param pdeckPublic le deck public
	 * @param pdeckPrive le deck prive
	 */
	public Joueur(String pnom, Dynastie pdynastie, DeckPublic pdeckPublic, DeckPrive pdeckPrive){
		this.dynastie = pdynastie;
		this.nom = pnom;
		this.deckPublic = pdeckPublic;
		this.deckPrive = pdeckPrive;
		this.PointVictoire = 0;
		this.PointTresor = 0;
		this.id = incId++;
	}

	/**
	 * retourne la dynastie du joueur
	 * @return
	 */
	@JsonProperty("dynastie")
	public Dynastie getDynastie() {
		return dynastie;
	}



	/**
	 * Méthode permettant de passer le tour du joueur
	 */
	public void passerTour(){
		this.tour = false;
	}

	/**
	 * @return the deckPublic
	 */
	@JsonProperty("deckPublic")
	public DeckPublic getDeckPublic() {
		return deckPublic;
	}

	/**
	 * @param pdeckPublic the deckPublic to set
	 */
	public void setDeckPublic(DeckPublic pdeckPublic) {
		this.deckPublic = pdeckPublic;
	}

	/**
	 * @return the deckPrive
	 */
	@JsonProperty("deckPrive")
	public DeckPrive getDeckPrive() {
		return deckPrive;
	}

	/**
	 * @param pdeckPrive the deckPrive to set
	 */
	public void setDeckPrive(DeckPrive pdeckPrive) {
		this.deckPrive = pdeckPrive;
	}

	/**
	 * getter du nom du joueur
	 * @return le nom
	 */
	@JsonProperty("nom")
	public String getNom(){
		return this.nom;
	}

	/**
	 * getter des points victoire du joueur
	 * @return points victoire du joueur
	 */
	@JsonProperty("PointVictoire")
	public int getPointVictoire(){
		return this.PointVictoire;
	}

	public void ajouterPointsVictoire(int points){
		this.PointVictoire += points;
	}
	/**
	 * getter des points tresor du joueur
	 * @return points tresor du joueur
	 */
	@JsonProperty("PointTresor")
	public int getPointTresor(){
		return this.PointTresor;
	}

	public void ajouterPointsTresor(int points){
		this.PointTresor += points;
	}

	/**
	 * setter du nom du joueur
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * setter de la dynastie
	 * @param dynastie
	 */
	public void setDynastie(Dynastie dynastie) {
		this.dynastie = dynastie;
	}

	/**
	 * getter de la place
	 * @return place
	 */
	public int getPlace() {
		return place;
	}

	/**
	 * getter du tour
	 * @return tour
	 */
	public boolean isTour() {
		return tour;
	}

	/**
	 * setter de tour
	 * @param tour
	 */
	public void setTour(boolean tour) {
		this.tour = tour;
	}

	/**
	 * setter de la place
	 * @param place
	 */
	public void setPlace(int place) {
		this.place = place;
	}

	public void jeSuisPret(){
		this.pret = true;
	}

	public void setEstPret(boolean pPret){
		this.pret = pPret;
	}

	public boolean estPret(){
		return this.pret;
	}

	public int getId(){
		return this.id;
	}

	/**
	 * FOR TEST PURPOSE
	 * @param id
	 */
	@JsonProperty("id")
	public void setId(int id){
		this.id = id;
	}
	
	public boolean equals(Object o){
		if(o instanceof Joueur){
			return this.id == ((Joueur) o).getId();
		}
		
		return false;
	}
	
	public void ajouterPointVictoire(int points){
		this.PointVictoire += points;
	}
	
	public void ajouterPointTresor(int points){
		this.PointTresor += points;
	}
}
