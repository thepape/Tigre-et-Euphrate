package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.conflit;

import java.io.Serializable;
import java.util.ArrayList;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Plateau;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Territoire;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TypeTuileCivilisation;

public class Conflits implements Serializable{

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
	private ArrayList<TuileCivilisation> listeTuileRenfortAttaquant = null;

	/**
	 * Liste des tuiles ajoutées en renforts par le défenseur
	 */
	private ArrayList<TuileCivilisation> listeTuileRenfortDefenseur = null;

	/**
	 * Type du conflit "E" ou "I"
	 */
	private String typeConflit;
	
	private Partie partie;

	public Partie getPartie() {
		return partie;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}

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
	public ArrayList<TuileCivilisation> getListeTuileRenfortAttaquant() {
		return listeTuileRenfortAttaquant;
	}

	/**
	 * setter de listeTuileRenfortAttaquant
	 * @param listeTuileRenfortAttaquant
	 */
	public void setListeTuileRenfortAttaquant(ArrayList<TuileCivilisation> listeTuileRenfortAttaquant) {
		this.listeTuileRenfortAttaquant = listeTuileRenfortAttaquant;
	}

	/**
	 * @return liste des tuiles ajoutées en renforts par le défenseur
	 */
	public ArrayList<TuileCivilisation> getListeTuileRenfortDefenseur() {
		return listeTuileRenfortDefenseur;
	}

	/**
	 * setter de listeTuileRenfortDefenseur
	 * @param listeTuileRenfortDefenseur
	 */
	public void setListeTuileRenfortDefenseur(ArrayList<TuileCivilisation> listeTuileRenfortDefenseur) {
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
	 * getter du type de conflit
	 * @return type du conflit
	 */
	public String getTypeConflit() {
		return typeConflit;
	}

	/**
	 * setter du type de conflit
	 * @param typeConflit
	 */
	public void setTypeConflit(String typeConflit) {
		this.typeConflit = typeConflit;
	}
	
	public boolean equals(Object pObject){
		if(pObject instanceof Conflits){
			return this.conflitId == ((Conflits) pObject).conflitId;
		}
		
		return false;
	}

	/**
	 * Fonction qui retourne le chef gagnant d'Un conflit et l'indique comme résolu. Retire le chef du plateau
	 * @return le chef gagnant du conflit
	 */
	public Chef definirChefGagnant()
	{
		int nbTuileCivilisationDefenseur = 0;
		int nbTuileCivilisationAttaquant = 0;

		if(this.getTerritoireAttaquant() != null)
		{
			for(int i = 0; i < this.getTerritoireDefenseur().getTuilesCivilisation().size(); i++)
			{
				TuileCivilisation tuileDefenseur = (TuileCivilisation) this.getTerritoireDefenseur().getTuilesCivilisation().get(i);
				if(tuileDefenseur.getType().getCouleur().equals(this.getChefDefenseur().getTypeChef().getCouleur()))
				{
					nbTuileCivilisationDefenseur++;
				}
			}

			for(int i = 0; i < this.getTerritoireAttaquant().getTuilesCivilisation().size(); i++)
			{
				TuileCivilisation tuileAttaquant = (TuileCivilisation) this.getTerritoireAttaquant().getTuilesCivilisation().get(i);
				if(tuileAttaquant.getType().getCouleur().equals(this.getChefAttaquant().getTypeChef().getCouleur()))
				{
					nbTuileCivilisationAttaquant++;
				}
			}
		} else {
			for(int i = 0; i < this.getTerritoireDefenseur().getTuilesCivilisation().size(); i++)
			{
				TuileCivilisation tuileAdjacente = this.getTerritoireDefenseur().getTuilesCivilisation().get(i);
				if(this.getChefDefenseur().estAdjacent(tuileAdjacente) && tuileAdjacente.getType().equals(TypeTuileCivilisation.Temple))
				{
					nbTuileCivilisationDefenseur++;
				}
			}

			for(int i = 0; i < this.getTerritoireDefenseur().getTuilesCivilisation().size(); i++)
			{
				TuileCivilisation tuileAdjacente = this.getTerritoireDefenseur().getTuilesCivilisation().get(i);
				if(this.getChefAttaquant().estAdjacent(tuileAdjacente) && tuileAdjacente.getType().equals(TypeTuileCivilisation.Temple))
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
			
			//retrait du chef perdant
			Plateau plateau = this.partie.getPlateauJeu();
			int x = this.chefAttaquant.getPosition().getX();
			int y = this.chefAttaquant.getPosition().getY();
			
			plateau.getPlateau()[x][y] = null;
			Joueur perdant = this.chefAttaquant.getJoueur();
			Territoire territoire = plateau.recupererTerritoireTuile(chefAttaquant);
			if(territoire != null)
				territoire.deletChef(chefAttaquant);
			perdant.getDeckPublic().ajouterChef(chefAttaquant);
			
			this.getChefAttaquant().setRetiree(true);
			
			//retrait des tuiles renforts pour les deux joueurs
			for(TuileCivilisation renfort : this.listeTuileRenfortAttaquant){
				if(this.chefAttaquant.getJoueur().getDeckPrive().getDeckPrive().contains(renfort)){
					this.chefAttaquant.getJoueur().getDeckPrive().getDeckPrive().remove(renfort);
				}
			}
			for(TuileCivilisation renfort : this.listeTuileRenfortDefenseur){
				if(this.chefDefenseur.getJoueur().getDeckPrive().getDeckPrive().contains(renfort)){
					this.chefDefenseur.getJoueur().getDeckPrive().getDeckPrive().remove(renfort);
				}
			}
			
			this.getChefDefenseur().getJoueur().ajouterPointVictoire(1);
			return this.getChefDefenseur();
		} else {
			this.setEstResolu(true);
			
			Plateau plateau = this.partie.getPlateauJeu();
			int x = this.chefDefenseur.getPosition().getX();
			int y = this.chefDefenseur.getPosition().getY();
			
			plateau.getPlateau()[x][y] = null;
			Joueur perdant = this.chefDefenseur.getJoueur();
			Territoire territoire = plateau.recupererTerritoireTuile(chefDefenseur);
			if(territoire != null)
				territoire.deletChef(chefDefenseur);
			perdant.getDeckPublic().ajouterChef(chefDefenseur);
			
			this.getChefDefenseur().setRetiree(true);
			
			//retrait des tuiles renforts pour les deux joueurs
			for(TuileCivilisation renfort : this.listeTuileRenfortAttaquant){
				if(this.chefAttaquant.getJoueur().getDeckPrive().getDeckPrive().contains(renfort)){
					this.chefAttaquant.getJoueur().getDeckPrive().getDeckPrive().remove(renfort);
				}
			}
			for(TuileCivilisation renfort : this.listeTuileRenfortDefenseur){
				if(this.chefDefenseur.getJoueur().getDeckPrive().getDeckPrive().contains(renfort)){
					this.chefDefenseur.getJoueur().getDeckPrive().getDeckPrive().remove(renfort);
				}
			}
			
			this.getChefAttaquant().getJoueur().ajouterPointVictoire(1);
			return this.getChefAttaquant();
		}
	}

	public boolean ajoutRenfort(ArrayList<TuileRenfort> listeTuileRenfort, TuileRenfort tuileRenfort)
	{
		if(this.typeConflit.equals("E"))
		{
			if(tuileRenfort.getTuileCorrespondante().getType().getCouleur().equals(this.chefAttaquant.getTypeChef().getCouleur()))
			{
				listeTuileRenfort.add(tuileRenfort);
				return true;
			} else {
				return false;
			}
		} else
		{
			if(tuileRenfort.getTuileCorrespondante().getType().equals(TypeTuileCivilisation.Temple))
			{
				listeTuileRenfort.add(tuileRenfort);
				return true;
			} else {
				return false;
			}
		}
	}
}
