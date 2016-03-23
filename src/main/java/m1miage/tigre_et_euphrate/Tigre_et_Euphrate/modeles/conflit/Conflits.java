package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.conflit;

import java.util.ArrayList;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Royaume;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;

public class Conflits {

	/**
	 * Chef qui attaque
	 */
	private Chef chefAttaquant;
	/**
	 * Chef qui défend
	 */
	private Chef chefDefenseur;

	/**
	 * Royaume du chef qui défend
	 */
	private Royaume royaumeDefenseur;

	/**
	 * Royaume du chef qui attaque. A NULL si il n'y a pas de jonction de royaume
	 */
	private Royaume royaumeAttaquant;

	/**
	 * Boolean qui représente la résolution du conflit
	 */
	private boolean estResolu;

	/**
	 * Constructeur vide
	 */
	public Conflits()
	{
		super();
		this.chefAttaquant = null;
		this.chefDefenseur = null;
		this.royaumeDefenseur = null;
		this.royaumeAttaquant = null;
	}

	/**
	 * Constructeur d'un conflit qui débute
	 * @param chefAttaquant
	 * @param chefDefenseur
	 * @param royaumeDefenseur
	 * @param royaumeAttaquant
	 */
	public Conflits(Chef chefAttaquant, Chef chefDefenseur, Royaume royaumeDefenseur, Royaume royaumeAttaquant) {
		super();
		this.chefAttaquant = chefAttaquant;
		this.chefDefenseur = chefDefenseur;
		this.royaumeDefenseur = royaumeDefenseur;
		this.royaumeAttaquant = royaumeAttaquant;
	}

	/**
	 * Liste des tuiles ajoutées en renforts par l'attaquant
	 */
	private ArrayList<TuileRenfort> listeTuileRenfortAttaquant;

	/**
	 * Liste des tuiles ajoutées en renforts par le défenseur
	 */
	private ArrayList<TuileRenfort> listeTuileRenfortDefenseur;

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
		this.chefAttaquant = chefAttaquant;
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
		this.chefDefenseur = chefDefenseur;
	}

	/**
	 * @return royaume du chef qui défend
	 */
	public Royaume getRoyaumeDefenseur() {
		return royaumeDefenseur;
	}

	/**
	 * setter du royaumeDefenseur
	 * @param royaumeDefenseur
	 */
	public void setRoyaumeDefenseur(Royaume royaumeDefenseur) {
		this.royaumeDefenseur = royaumeDefenseur;
	}

	/**
	 * @return royaume du chef qui attaque (à NULL s'il n'y a pas de jonction de royaume)
	 */
	public Royaume getRoyaumeAttaquant() {
		return royaumeAttaquant;
	}

	/**
	 * setter de royaumeAttaquant
	 * @param royaumeAttaquant
	 */
	public void setRoyaumeAttaquant(Royaume royaumeAttaquant) {
		this.royaumeAttaquant = royaumeAttaquant;
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

	public Chef definirChefGagnant()
	{
		int nbTuileCivilisationDefenseur = 0;
		int nbTuileCivilisationAttaquant = 0;
		for(int i = 0; i < this.getRoyaumeDefenseur().getTuilesCivilisation().size(); i++)
		{
			TuileCivilisation tuileDefenseur = (TuileCivilisation) this.getRoyaumeDefenseur().getTuilesCivilisation().get(i);
			if(tuileDefenseur.getType().getCouleur().equals(this.getChefDefenseur().getTypeChef().getCouleur()))
			{
				nbTuileCivilisationDefenseur++;
			}
		}

		if(this.getRoyaumeAttaquant() != null)
		{
			for(int i = 0; i < this.getRoyaumeAttaquant().getTuilesCivilisation().size(); i++)
			{
				TuileCivilisation tuileAttaquant = (TuileCivilisation) this.getRoyaumeAttaquant().getTuilesCivilisation().get(i);
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
			return this.getChefDefenseur();
		} else {
			return this.getChefAttaquant();
		}
	}
}
