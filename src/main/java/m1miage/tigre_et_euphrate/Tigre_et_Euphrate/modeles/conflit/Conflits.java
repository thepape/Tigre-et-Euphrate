package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.conflit;

import java.util.ArrayList;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Territoire;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;

public class Conflits {

	private static int idIncrementConflit = 0;
	/**
	 * Chef qui attaque
	 */
	private Chef chefAttaquant;
	/**
	 * Chef qui défend
	 */
	private Chef chefDefenseur;

	/**
	 * Territoire du chef qui défend
	 */
	private Territoire territoireDefenseur;

	/**
	 * Territoire du chef qui attaque. A NULL si il n'y a pas de jonction de Territoire
	 */
	private Territoire territoireAttaquant;

	/**
	 * Boolean qui représente la résolution du conflit
	 */
	private boolean estResolu = false;

	/**
	 * id du conflit
	 */
	private int conflitId;

	/**
	 * Liste des tuiles ajoutées en renforts par l'attaquant
	 */
	private ArrayList<TuileRenfort> listeTuileRenfortAttaquant;

	/**
	 * Liste des tuiles ajoutées en renforts par le défenseur
	 */
	private ArrayList<TuileRenfort> listeTuileRenfortDefenseur;

	/**
	 * Constructeur vide
	 */
	public Conflits()
	{
		super();
		this.chefAttaquant = null;
		this.chefDefenseur = null;
		this.territoireDefenseur = null;
		this.territoireAttaquant = null;
		this.conflitId = Conflits.idIncrementConflit;
		Conflits.idIncrementConflit++;
	}

	/**
	 * Constructeur d'un conflit qui débute
	 * @param chefAttaquant
	 * @param chefDefenseur
	 * @param TerritoireDefenseur
	 * @param TerritoireAttaquant
	 */
	public Conflits(Chef chefAttaquant, Chef chefDefenseur, Territoire TerritoireDefenseur, Territoire TerritoireAttaquant) {
		super();
		this.setChefDefenseur(chefDefenseur);
		this.setChefAttaquant(chefAttaquant);
		this.territoireAttaquant = TerritoireAttaquant;
		this.territoireDefenseur = TerritoireDefenseur;
		this.conflitId = Conflits.idIncrementConflit;
		Conflits.idIncrementConflit++;
	}

	/**
	 * @return le chef qui attaque
	 */
	public Chef getChefAttaquant() {
		return chefAttaquant;
	}

	/**
	 * setter de chefAttaquant
	 * @param chefAttaquant
	 */
	public void setChefAttaquant(Chef chefAttaquant) {
		if(this.chefDefenseur != null)
		{
			if(chefAttaquant.getTypeChef().getCouleur().equals(chefDefenseur.getTypeChef().getCouleur()))
			{
				this.chefAttaquant = chefAttaquant;
			} else {
				this.chefAttaquant = null;
			}
		} else {
			this.chefAttaquant = chefAttaquant;
		}
	}

	/**
	 * @return le chef qui défend
	 */
	public Chef getChefDefenseur() {
		return chefDefenseur;
	}

	/**
	 * setter du chef defenseur
	 * @param chefDefenseur
	 */
	public void setChefDefenseur(Chef chefDefenseur) {
		if(this.chefAttaquant != null)
		{
			if(chefDefenseur.getTypeChef().getCouleur().equals(this.chefAttaquant.getTypeChef().getCouleur()))
			{
				this.chefDefenseur = chefDefenseur;
			} else {
				this.chefDefenseur = null;
			}
		} else {
			this.chefDefenseur = chefDefenseur;
		}

	}

	/**
	 * @return Territoire du chef qui défend
	 */
	public Territoire getTerritoireDefenseur() {
		return territoireDefenseur;
	}

	/**
	 * setter du TerritoireDefenseur
	 * @param TerritoireDefenseur
	 */
	public void setTerritoireDefenseur(Territoire TerritoireDefenseur) {
		this.territoireDefenseur = TerritoireDefenseur;
	}

	/**
	 * @return Territoire du chef qui attaque (à NULL s'il n'y a pas de jonction de Territoire)
	 */
	public Territoire getTerritoireAttaquant() {
		return territoireAttaquant;
	}

	/**
	 * setter de TerritoireAttaquant
	 * @param TerritoireAttaquant
	 */
	public void setTerritoireAttaquant(Territoire TerritoireAttaquant) {
		this.territoireAttaquant = TerritoireAttaquant;
	}

	/**
	 * @return liste des tuiles ajoutées en renforts par l'attaquant
	 */
	public ArrayList<TuileRenfort> getListeTuileRenfortAttaquant() {
		return listeTuileRenfortAttaquant;
	}

	/**
	 * setter de listeTuileRenfortAttaquant
	 * @param listeTuileRenfortAttaquant
	 */
	public void setListeTuileRenfortAttaquant(ArrayList<TuileRenfort> listeTuileRenfortAttaquant) {
		this.listeTuileRenfortAttaquant = listeTuileRenfortAttaquant;
	}

	/**
	 * @return liste des tuiles ajoutées en renforts par le défenseur
	 */
	public ArrayList<TuileRenfort> getListeTuileRenfortDefenseur() {
		return listeTuileRenfortDefenseur;
	}

	/**
	 * setter de listeTuileRenfortDefenseur
	 * @param listeTuileRenfortDefenseur
	 */
	public void setListeTuileRenfortDefenseur(ArrayList<TuileRenfort> listeTuileRenfortDefenseur) {
		this.listeTuileRenfortDefenseur = listeTuileRenfortDefenseur;
	}

	/**
	 * @return le booléen qui représente la résolution du conflit
	 */
	public boolean isEstResolu() {
		return estResolu;
	}

	/**
	 * setter de estResolu
	 * @param estResolu
	 */
	public void setEstResolu(boolean estResolu) {
		this.estResolu = estResolu;
	}

	/**
	 * Fonction qui retourne le chef gagnant d'Un conflit et l'indique comme résolu. Retire le chef du plateau
	 * @return le chef gagnant du conflit
	 */
	public Chef definirChefGagnant()
	{
		int nbTuileCivilisationDefenseur = 0;
		int nbTuileCivilisationAttaquant = 0;

		for(int i = 0; i < this.getTerritoireDefenseur().getTuilesCivilisation().size(); i++)
		{
			TuileCivilisation tuileDefenseur = (TuileCivilisation) this.getTerritoireDefenseur().getTuilesCivilisation().get(i);
			if(tuileDefenseur.getType().getCouleur().equals(this.getChefDefenseur().getTypeChef().getCouleur()))
			{
				nbTuileCivilisationDefenseur++;
			}
		}

		if(this.getTerritoireAttaquant() != null)
		{
			for(int i = 0; i < this.getTerritoireAttaquant().getTuilesCivilisation().size(); i++)
			{
				TuileCivilisation tuileAttaquant = (TuileCivilisation) this.getTerritoireAttaquant().getTuilesCivilisation().get(i);
				if(tuileAttaquant.getType().getCouleur().equals(this.getChefAttaquant().getTypeChef().getCouleur()))
				{
					nbTuileCivilisationAttaquant++;
				}
			}
		}

		nbTuileCivilisationDefenseur += this.getListeTuileRenfortDefenseur().size();
		nbTuileCivilisationAttaquant += this.getListeTuileRenfortAttaquant().size();

		if(nbTuileCivilisationDefenseur >= nbTuileCivilisationAttaquant)
		{
			this.setEstResolu(true);
			this.getChefAttaquant().setRetiree(true);
			return this.getChefDefenseur();
		} else {
			this.setEstResolu(true);
			this.getChefDefenseur().setRetiree(true);
			return this.getChefAttaquant();
		}
	}

	public boolean ajoutRenfort(ArrayList<TuileRenfort> listeTuileRenfort, TuileRenfort tuileRenfort)
	{
		if(tuileRenfort.getTuileCorrespondante().getType().getCouleur().equals(this.chefAttaquant.getTypeChef().getCouleur()))
		{
			listeTuileRenfort.add(tuileRenfort);
			return true;
		} else {
			return false;
		}
	}
}
