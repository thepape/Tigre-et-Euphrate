package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles;

import java.util.ArrayList;

/**
 * Class représentant un royaume.
 * un royaume doit avoir au moins un chef et une tuile civilisation
 * 
 *
 */
//TODO tester la class !!
public class Royaume {

	/**
	 * Liste contenant toute les tuiles civilisation
	 * du royaume.
	 */
	private ArrayList<TuileCivilisation> tuilesCivilisation;

	/**
	 * Liste contenant tous les chefs du royaume
	 */
	private ArrayList<Chef> chefs;

	/**
	 * L'id du royaume
	 */
	private int idRoyaume = 0;



	/**
	 * Constructeur d'un royaume avec les listes
	 * @param tuilesCivilisation  listes des tuiles civilisation
	 * @param chefs liste des chefs
	 */
	public Royaume(ArrayList<TuileCivilisation> tuilesCivilisation, ArrayList<Chef> chefs) {
		super();
		this.tuilesCivilisation = tuilesCivilisation;
		this.chefs = chefs;
		this.idRoyaume +=1;
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
	 */
	public void addChefs(Chef pChef){
		this.chefs.add(pChef);
		//On vérifie s'il y a un conflit (2 chefs du meme type)
		for(Chef c : this.chefs)
		{
			if(pChef.getTypeChef().equals(c.getTypeChef())){
				//TODO faire un conflit
			}
		}
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
	 */
	public void addListeChefs(ArrayList<Chef> pChefs){
		//For Each
		for(Chef c : pChefs){
			this.chefs.add(c);
		}
		//On vérifie s'il y a un conflit (2 chefs du meme type)
		for(int i=1; i<chefs.size();i++){
			for(int j=1; j<pChefs.size(); j++){
				if(chefs.get(i).getTypeChef().equals(pChefs.get(j).getTypeChef())){
					//TODO faire un conflit
				}
			}
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
	
	
	//Getter

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
	public int getIdRoyaume() {
		return idRoyaume;
	}


}
