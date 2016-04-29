package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.vue;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.FXML;

/**
 * Controlleur de l'interface d'attribution des trésors
 * @author
 *
 */
public class ControleurAttributionTresors {
	
	
	// ATTRIBUTS
	
	/**
	 * Application principale
	 */
	private MainApp mainApp;
	
	/**
	 * Compteur de tous les trésors à attribuer
	 */
	private int compteurTotal = 6;
	
	/**
	 * Compteur de trésors pour les Fermes
	 */
	private int compteurFerme = 0;
	
	/**
	 * Compteur de trésors pour les Marches
	 */
	private int compteurMarche = 0;
	
	/**
	 * Compteur de trésors pour les Populations
	 */
	private int compteurPopulation = 0;
	
	/**
	 * Compteur de trésors pour les Temples
	 */
	private int compteurTemple = 0;
	
	@FXML
	private Label nbTresorsTotal;
	@FXML
	private Label nbTresorsFerme;
	@FXML
	private Label nbTresorsMarche;
	@FXML
	private Label nbTresorsPopulation;
	@FXML
	private Label nbTresorsTemple;
	@FXML
	private Button moinsFerme;
	@FXML
	private Button moinsMarche;
	@FXML
	private Button moinsPopulation;
	@FXML
	private Button moinsTemple;
	@FXML
	private Button plusFerme;
	@FXML
	private Button plusMarche;
	@FXML
	private Button plusPopulation;
	@FXML
	private Button plusTemple;
	@FXML
	private Button validerBtn;
	
	
	
	// FONCTIONS
	
	public void initialiser(int nbTresorsTotal){
		this.setCompteurTotal(nbTresorsTotal);
		this.nbTresorsTotal.setText(this.compteurTotal+"");
		if(this.compteurTotal > 0)
			this.activerTousLesBoutonsPlus();
	}
	
	/**
	 * Desactiver tous les boutons PLUS
	 */
	public void desactiverTousLesBoutonsPlus(){
		this.plusFerme.setDisable(true);
		this.plusMarche.setDisable(true);
		this.plusPopulation.setDisable(true);
		this.plusTemple.setDisable(true);
	}
	
	/**
	 * Activer tous les boutons PLUS
	 */
	public void activerTousLesBoutonsPlus(){
		this.plusFerme.setDisable(false);
		this.plusMarche.setDisable(false);
		this.plusPopulation.setDisable(false);
		this.plusTemple.setDisable(false);
	}
	
	/**
	 * Fonction permettant de decrémenter le compteur de trésors restants à attribuer
	 */
	public void decrementerCompteurPrincipal(){
		if(this.compteurTotal > 0)
		{
			this.compteurTotal--;
			this.nbTresorsTotal.setText(this.compteurTotal+"");
		}
	}
	
	/**
	 * Fonction permettant d'incrémenter le compteur de trésors restants à attribuer
	 */
	public void incrementerCompteurPrincipal(){
		if(this.compteurTotal == 0)
			this.activerTousLesBoutonsPlus();
		this.compteurTotal++;
		this.nbTresorsTotal.setText(this.compteurTotal+"");
	}
	
	/**
	 * Fonction permettant de savoir si le compteur total des tresors vaut 0 ou non
	 * @return vrai ou faux
	 */
	public boolean estVideCompteurTotal(){
		return this.compteurTotal==0;
	}
	
	/**
	 * Fonction permettant d'incrémenter le compteur de la civilisation Ferme
	 */
	public void incrementerTresorFerme(){
		this.compteurFerme++;
		this.nbTresorsFerme.setText(this.compteurFerme+"");
		this.decrementerCompteurPrincipal();
		if(this.estVideCompteurTotal())
		{
			this.desactiverTousLesBoutonsPlus();
		}
		if(this.moinsFerme.isDisabled())
			this.moinsFerme.setDisable(false);
	}
	
	/**
	 * Fonction permettant d'incrémenter le compteur de la civilisation Marché
	 */
	public void incrementerTresorMarche(){
		this.compteurMarche++;
		this.nbTresorsMarche.setText(this.compteurMarche+"");
		this.decrementerCompteurPrincipal();
		if(this.estVideCompteurTotal())
		{
			this.desactiverTousLesBoutonsPlus();
		}
		if(this.moinsMarche.isDisabled())
			this.moinsMarche.setDisable(false);
	}
	
	/**
	 * Fonction permettant d'incrémenter le compteur de la civilisation Population
	 */
	public void incrementerTresorPopulation(){
		this.compteurPopulation++;
		this.nbTresorsPopulation.setText(this.compteurPopulation+"");
		this.decrementerCompteurPrincipal();
		if(this.estVideCompteurTotal())
		{
			this.desactiverTousLesBoutonsPlus();
		}
		if(this.moinsPopulation.isDisabled())
			this.moinsPopulation.setDisable(false);
	}
	
	/**
	 * Fonction permettant d'incrémenter le compteur de la civilisation Temple
	 */
	public void incrementerTresorTemple(){
		this.compteurTemple++;
		this.nbTresorsTemple.setText(this.compteurTemple+"");
		this.decrementerCompteurPrincipal();
		if(this.estVideCompteurTotal())
		{
			this.desactiverTousLesBoutonsPlus();
		}
		if(this.moinsTemple.isDisabled())
			this.moinsTemple.setDisable(false);
	}
	
	/**
	 * Fonction permettant d'incrémenter le compteur de la civilisation Ferme
	 */
	public void decrementerTresorFerme(){
		if(this.compteurFerme > 0)
		{
			this.compteurFerme--;
			this.nbTresorsFerme.setText(this.compteurFerme+"");
			if(this.compteurFerme == 0)
			{
				this.moinsFerme.setDisable(true);
			}
		}
		this.incrementerCompteurPrincipal();
	}
	
	/**
	 * Fonction permettant d'incrémenter le compteur de la civilisation Marche
	 */
	public void decrementerTresorMarche(){
		if(this.compteurMarche > 0)
		{
			this.compteurMarche--;
			this.nbTresorsMarche.setText(this.compteurMarche+"");
			if(this.compteurMarche == 0)
			{
				this.moinsMarche.setDisable(true);
			}
		}
		this.incrementerCompteurPrincipal();
	}
	
	/**
	 * Fonction permettant d'incrémenter le compteur de la civilisation Population
	 */
	public void decrementerTresorPopulation(){
		if(this.compteurPopulation > 0)
		{
			this.compteurPopulation--;
			this.nbTresorsPopulation.setText(this.compteurPopulation+"");
			if(this.compteurPopulation == 0)
			{
				this.moinsPopulation.setDisable(true);
			}
		}
		this.incrementerCompteurPrincipal();
	}
	
	/**
	 * Fonction permettant d'incrémenter le compteur de la civilisation Temple
	 */
	public void decrementerTresorTemple(){
		if(this.compteurTemple > 0)
		{
			this.compteurTemple--;
			this.nbTresorsTemple.setText(this.compteurTemple+"");
			if(this.compteurTemple == 0)
			{
				this.moinsTemple.setDisable(true);
			}
		}
		this.incrementerCompteurPrincipal();
	}
	
	/**
	 * Fonction validant l'attribution des trésors aux Civilisations
	 */
	public void valider(){
		
	}
	
	/**
	 * @return the mainApp
	 */
	public MainApp getMainApp() {
		return mainApp;
	}

	/**
	 * @param pmainApp the mainApp to set
	 */
	public void setMainApp(MainApp pmainApp) {
		this.mainApp = pmainApp;
	}

	/**
	 * @return the compteurTotal
	 */
	public int getCompteurTotal() {
		return compteurTotal;
	}

	/**
	 * @param pcompteurTotal the compteurTotal to set
	 */
	public void setCompteurTotal(int pcompteurTotal) {
		this.compteurTotal = pcompteurTotal;
	}

	/**
	 * @return the compteurFerme
	 */
	public int getCompteurFerme() {
		return compteurFerme;
	}

	/**
	 * @param pcompteurFerme the compteurFerme to set
	 */
	public void setCompteurFerme(int pcompteurFerme) {
		this.compteurFerme = pcompteurFerme;
	}

	/**
	 * @return the compteurMarche
	 */
	public int getCompteurMarche() {
		return compteurMarche;
	}

	/**
	 * @param pcompteurMarche the compteurMarche to set
	 */
	public void setCompteurMarche(int pcompteurMarche) {
		this.compteurMarche = pcompteurMarche;
	}

	/**
	 * @return the compteurPopulation
	 */
	public int getCompteurPopulation() {
		return compteurPopulation;
	}

	/**
	 * @param pcompteurPopulation the compteurPopulation to set
	 */
	public void setCompteurPopulation(int pcompteurPopulation) {
		this.compteurPopulation = pcompteurPopulation;
	}

	/**
	 * @return the compteurTemple
	 */
	public int getCompteurTemple() {
		return compteurTemple;
	}

	/**
	 * @param pcompteurTemple the compteurTemple to set
	 */
	public void setCompteurTemple(int pcompteurTemple) {
		this.compteurTemple = pcompteurTemple;
	}
	
	
}
