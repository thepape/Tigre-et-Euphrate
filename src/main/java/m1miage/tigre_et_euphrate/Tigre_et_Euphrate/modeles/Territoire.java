package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles;

import java.util.ArrayList;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;

/**
 * Class représentant un territoire.
 * un royaume doit avoir au moins un chef et une tuile civilisation
 *
 */

public class Territoire {

	/**
	 * Boolean pour savoir si le territoire est un royaume
	 */
	private boolean estRoyaume = false;

	/**
	 *  Incrément de l'id des objets territoire.
	 */
	private static int idIncrementTerritoire = 0;

	/**
	 * Liste contenant toute les tuiles civilisation
	 * du territoire.
	 */
	private ArrayList<TuileCivilisation> tuilesCivilisation;

	/**
	 * Liste contenant tous les chefs du royaume
	 */
	private ArrayList<Chef> chefs;

	/**
	 * L'id du territoire
	 */
	private int idTerritoire;

	/**
	 * Constructeur vide d'un territoire
	 */
	public Territoire() {

		this.tuilesCivilisation = new ArrayList<TuileCivilisation>();
		this.chefs = new ArrayList<Chef>();
		this.estRoyaume = false;
		this.idTerritoire = Territoire.idIncrementTerritoire;
		Territoire.idIncrementTerritoire++;
	}

	/**
	 * Constructeur d'un territoire avec les listes
	 * @param tuilesCivilisation  listes des tuiles civilisation
	 * @param chefs liste des chefs
	 */
	public Territoire(TuileCivilisation pTuileInit) {
		super();
		this.estRoyaume = false;
		this.tuilesCivilisation = new ArrayList<TuileCivilisation>();
		this.tuilesCivilisation.add(pTuileInit);
		pTuileInit.setTerritoire(this);
		this.chefs = new ArrayList<Chef>();
		this.idTerritoire = Territoire.idIncrementTerritoire;
		Territoire.idIncrementTerritoire++;
	}

	//Méthodes concernant l'ajout

	/**
	 * Methode pour ajouter une tuile civilisation au royaume
	 * @param pTuileCivilisation la tuile à ajouter
	 */
	public void addTuile(TuileCivilisation pTuileCivilisation){
		this.tuilesCivilisation.add(pTuileCivilisation);
	}

	/**
	 * Méthode pour ajouter un chef au royaume.
	 * Vérifie également si cela créer un conflit
	 * @param pChef le chef à ajouter
	 * @return ok si le chef est ajouté
	 */
	public void addChefs(Chef pChef){
		this.chefs.add(pChef);
		this.estRoyaume = true;
	}

	/**
	 * Méthode pour ajouter une liste de tuiles civilisation au royaume.
	 * Utile pour la jonction de deux royaumes
	 * @param pTuilesCivilisation la liste des tuiles à ajouter
	 */
	public void addListeTuiles(ArrayList<TuileCivilisation> pTuilesCivilisation)
	{
		//For Each
		for(TuileCivilisation pTuiles : pTuilesCivilisation){
			this.tuilesCivilisation.add(pTuiles);
		}
	}

	/**
	 * Méthode pour ajouter une liste de chefs au royaume.
	 * Utile pour la jonction de deux royaume
	 * @param pChefs la liste de chefs à ajouter
	 * @return ok si les chef est ajouté
	 */
	public void addListeChefs(ArrayList<Chef> pChefs){

		for(Chef c : pChefs){
			this.chefs.add(c);
			this.estRoyaume = true;
		}
	}


	//Méthodes pour la supprésion

	/**
	 * Méthode pour supprimer une tuile Civilisation du royaume
	 * @param pTuile la tuile à supprimer
	 */
	public void deletTuilesCivilisation(TuileCivilisation pTuile){
		if(this.tuilesCivilisation.contains(pTuile)){
			int tuileRemove = this.tuilesCivilisation.indexOf(pTuile);
			this.tuilesCivilisation.remove(tuileRemove);
		}
	}

	/**
	 * Méthode pour supprimer un chef du royaume
	 * @param pChef le chef à supprimer
	 */
	public void deletChef(Chef pChef){
		if(this.chefs.contains(pChef)){
			int chefRemove = this.chefs.indexOf(pChef);
			this.chefs.remove(chefRemove);
		}
	}


	//Getter & Setter

	/**
	 * return la liste des tuiles civilisation
	 * @return
	 */
	public ArrayList<TuileCivilisation> getTuilesCivilisation() {
		return tuilesCivilisation;
	}

	/**
	 * return la liste des chefs
	 * @return
	 */
	public ArrayList<Chef> getChefs() {
		return chefs;
	}


	/**
	 * return l'id du royaume
	 * @return
	 */
	public int getIdTerritoire() {
		return idTerritoire;
	}

	/**
	 * return si le territoire est un royaume
	 * @return
	 */
	public boolean isEstRoyaume() {
		return estRoyaume;
	}

	/**
	 * Change le territoire en royaume ou l'inverse
	 * @param estRoyaume
	 */
	public void setEstRoyaume(boolean estRoyaume) {
		this.estRoyaume = estRoyaume;
	}

	/**
	 * setter de la liste des tuiles civilisations
	 * @param tuilesCivilisation
	 */
	public void setTuilesCivilisation(ArrayList<TuileCivilisation> tuilesCivilisation) {
		this.tuilesCivilisation = tuilesCivilisation;
	}

	/**
	 * setter de la liste chefs
	 * @param chefs
	 */
	public void setChefs(ArrayList<Chef> chefs) {
		this.chefs = chefs;
	}




}
