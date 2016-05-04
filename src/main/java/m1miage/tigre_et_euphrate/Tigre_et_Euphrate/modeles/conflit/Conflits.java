package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.conflit;

import java.io.Serializable;
import java.util.ArrayList;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Plateau;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Position;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Territoire;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action.PlacerTuileCivilisation;
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
		
		ArrayList<TuileCivilisation> soutiensAttaquant = new ArrayList<TuileCivilisation>();
		ArrayList<TuileCivilisation> soutiensDefenseur = new ArrayList<TuileCivilisation>();

		if(this.getTerritoireAttaquant() != null)
		{
			for(int i = 0; i < this.getTerritoireDefenseur().getTuilesCivilisation().size(); i++)
			{
				TuileCivilisation tuileDefenseur = (TuileCivilisation) this.getTerritoireDefenseur().getTuilesCivilisation().get(i);
				if(tuileDefenseur.getType().getCouleur().equals(this.getChefDefenseur().getTypeChef().getCouleur()) && !tuileDefenseur.estTuileMonument())
				{
					nbTuileCivilisationDefenseur++;
					soutiensDefenseur.add(tuileDefenseur);
				}
			}

			for(int i = 0; i < this.getTerritoireAttaquant().getTuilesCivilisation().size(); i++)
			{
				TuileCivilisation tuileAttaquant = (TuileCivilisation) this.getTerritoireAttaquant().getTuilesCivilisation().get(i);
				if(tuileAttaquant.getType().getCouleur().equals(this.getChefAttaquant().getTypeChef().getCouleur()) && !tuileAttaquant.estTuileMonument())
				{
					nbTuileCivilisationAttaquant++;
					soutiensAttaquant.add(tuileAttaquant);
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
			
			//pioche des nouvelles tuiles pour les deux joueurs
			this.partie.piocheCartesManquantes(this.chefAttaquant.getJoueur());
			this.partie.piocheCartesManquantes(this.chefDefenseur.getJoueur());
			
			if(this.getTypeConflit().equals("I")){
				this.getChefDefenseur().getJoueur().ajouterPointsVictoire("rouge", 1);
			}
			else{
				//si c'est un conflit externe, on enleve en plus les soutiens et on attribue les points
				for(TuileCivilisation tuileARetirer : soutiensAttaquant){
					//si c'est un temple avec un tresor, on le vire pas
					if(tuileARetirer.aTresor()){
						continue;
					}
					this.partie.getPlateauJeu().getPlateau()[tuileARetirer.getPosition().getX()][tuileARetirer.getPosition().getY()] = null;
					tuileARetirer.setPosition(null);
					this.territoireAttaquant.deletTuilesCivilisation(tuileARetirer);
					this.getChefDefenseur().getJoueur().ajouterPointsVictoire(chefAttaquant.getTypeChef().getCouleur(), 1);
				}
				//ajout du point supplémentaire pour la victoire contre le chef
				this.getChefDefenseur().getJoueur().ajouterPointsVictoire(chefAttaquant.getTypeChef().getCouleur(), 1);
			}
			System.out.println("SERVEUR: points de "+this.getChefDefenseur().getJoueur().getNom()+"="+this.getChefDefenseur().getJoueur().getPointVictoireRouge());
			this.partie.getConflits().remove(this);
			this.partie.retirerTourConflit(this.chefAttaquant.getJoueur());
			this.partie.retirerTourConflit(this.chefDefenseur.getJoueur());
			//on regarde s'il existe d'autres conflits. 
			this.reconstruireTerritoires(this.territoireAttaquant);
			this.reverifierConflits(this.chefAttaquant);
			
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
			
			//pioche des nouvelles tuiles pour les deux joueurs
			this.partie.piocheCartesManquantes(this.chefAttaquant.getJoueur());
			this.partie.piocheCartesManquantes(this.chefDefenseur.getJoueur());
			
			if(this.getTypeConflit().equals("I")){
				this.chefAttaquant.getJoueur().ajouterPointsVictoire("rouge", 1);
			}
			else{
				//si c'est un conflit externe, on enleve en plus les soutiens et on attribue les points
				for(TuileCivilisation tuileARetirer : soutiensDefenseur){
					//si c'est un temple avec un tresor, on le vire pas
					if(tuileARetirer.aTresor()){
						continue;
					}
					this.partie.getPlateauJeu().getPlateau()[tuileARetirer.getPosition().getX()][tuileARetirer.getPosition().getY()] = null;
					tuileARetirer.setPosition(null);
					this.territoireDefenseur.deletTuilesCivilisation(tuileARetirer);
					this.chefAttaquant.getJoueur().ajouterPointsVictoire(chefDefenseur.getTypeChef().getCouleur(), 1);
				}
				//ajout du point supplémentaire pour la victoire contre le chef
				this.chefAttaquant.getJoueur().ajouterPointsVictoire(chefDefenseur.getTypeChef().getCouleur(), 1);
			}
			//System.out.println("SERVEUR: points de "+this.getChefAttaquant().getJoueur().getNom()+"="+this.getChefAttaquant().getJoueur().getPointVictoireRouge());
			this.partie.getConflits().remove(this);
			this.partie.retirerTourConflit(this.chefAttaquant.getJoueur());
			this.partie.retirerTourConflit(this.chefDefenseur.getJoueur());
			//on regarde s'il existe d'autres conflits. 
			this.reconstruireTerritoires(this.territoireDefenseur);
			this.reverifierConflits(this.chefAttaquant);
			
			return this.getChefAttaquant();
		}
	}
	
	public void reconstruireTerritoires(Territoire perdant){
		//on reconstruit les territoires suite au retrait de tuiles soutiens
		
		System.out.println("conflit:reconstruireTerritoires AVANT");
		System.out.println(this.partie.getPlateauJeu().afficherTerritoires());
		
		for(TuileCivilisation tuile : perdant.getTuilesCivilisation()){
			this.partie.getPlateauJeu().reconstruireTerritoires(tuile.getPosition());
			System.out.println("conflit:reconstruireTerritoires TUILE:"+tuile.getId());
			System.out.println(this.partie.getPlateauJeu().afficherTerritoires());
		}
		
		for(Chef chef : perdant.getChefs()){
			//on verifie si le chef est toujours a coté d'un temple
			ArrayList<TuileCivilisation> listeAdj = this.partie.getPlateauJeu().recupererListeTuileCivilisationAdjacente(chef.getPosition());
			
			TuileCivilisation templeAdj = null;
			for(TuileCivilisation tuileAdj : listeAdj){
				if(tuileAdj.getType().equals(TypeTuileCivilisation.Temple)){
					templeAdj = tuileAdj;
					break;
				}
			}
			
			if(templeAdj != null){
				/*
				this.partie.getPlateauJeu().reconstruireTerritoires(chef.getPosition());
				System.out.println("conflit:reconstruireTerritoires CHEF:"+chef.getId());
				System.out.println(this.partie.getPlateauJeu().afficherTerritoires());*/
				Territoire oldTerritoire = this.partie.getPlateauJeu().recupererTerritoireTuile(chef);
				if(oldTerritoire != null){
					oldTerritoire.deletChef(chef);
					Territoire nouveauTerri = this.partie.getPlateauJeu().recupererTerritoireTuile(templeAdj);
					nouveauTerri.addChefs(chef);
				}
			}else{
				//si le chef n'as plus dde temple a coté, on l'ejecte
				Joueur possesseur = chef.getJoueur();
				this.partie.getPlateauJeu().getPlateau()[chef.getPosition().getX()][chef.getPosition().getY()] = null;
				chef.setPosition(null);
				possesseur.getDeckPublic().ajouterChef(chef);
			}
		}
		
		System.out.println("conflit:reconstruireTerritoires");
		System.out.println(this.partie.getPlateauJeu().afficherTerritoires());
	}
	
	public boolean reverifierConflits(Chef attaquant){
		//reverifie si après reconstruction il y a toujours des conflits
		TuileCivilisation jonction = this.partie.getPlateauJeu().recupererTuileJonction();
		Position position = jonction.getPosition();
		
		//on réexecute l'action de placer une tuile civilisation pour checker les conflits
		this.partie.getPlateauJeu().getPlateau()[position.getX()][position.getY()] = null;
		jonction.setJonction(false);
		PlacerTuileCivilisation action = new PlacerTuileCivilisation(this.partie, attaquant.getJoueur(), position, jonction);
		boolean ok = action.executer();
		
		if(this.partie.getConflits().size() > 0){
			return true;
		}
		
		System.out.println("conflit:reverifierconflit");
		System.out.println(this.partie.getPlateauJeu().afficherTerritoires());
		
		return false;
	}
	
	/*
	public Conflits unifierTerritoires(Territoire territoireGagnant, Territoire territoirePerdant){
		//on verifie si deux chefs de meme couleur sont présents
		for(Chef chef1 : territoireGagnant.getChefs()){
			for(Chef chef2 : territoirePerdant.getChefs()){
				if(chef1.getTypeChef().equals(chef2.getTypeChef())){
					return new Conflits(chef1, chef2, territoireGagnant, territoirePerdant);
				}
			}
		}
		
		//si pas de nouveau conflits, on unifie les 2 territoires
		territoireGagnant.addListeChefs(territoirePerdant.getChefs());
		territoireGagnant.addListeTuiles(territoirePerdant.getTuilesCivilisation());
		
		this.partie.getPlateauJeu().supprRoyaume(territoirePerdant);
		
		return null;
	}*/

	public boolean ajoutRenfort(ArrayList<TuileCivilisation> listeTuileRenfort, TuileCivilisation tuileRenfort)
	{
		if(this.typeConflit.equals("E"))
		{
			if(tuileRenfort.getType().getCouleur().equals(this.chefAttaquant.getTypeChef().getCouleur()))
			{
				listeTuileRenfort.add(tuileRenfort);
				return true;
			} else {
				return false;
			}
		} else
		{
			if(tuileRenfort.getType().equals(TypeTuileCivilisation.Temple))
			{
				listeTuileRenfort.add(tuileRenfort);
				return true;
			} else {
				return false;
			}
		}
	}
}
