package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Position;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCatastrophe;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TypeTuileCivilisation;

/**
 * 
 * Classe hérite de la classe Action et permet de placer une tuile catastrophe
 *
 */
public class PlacerTuileCatastrophe extends Action {
	
	/**
	 * Catastrophe
	 */
	private TuileCatastrophe tuileCatastrophe;

	/**
	 * Position 
	 */
	private Position position;

	/**
	 * @param partie
	 * @param joueur
	 */
	public PlacerTuileCatastrophe(Partie ppartie, Joueur pjoueur, TuileCatastrophe pTuile, Position ppos) {
		super(ppartie, pjoueur);
		this.tuileCatastrophe = pTuile;
		this.position = ppos;
	}
	
	public boolean VerifierCase(){
		
		boolean ok = false;
		int x = this.position.getX();
		int y = this.position.getY();
		//getClass().isInstance(TuileCivilisation.class)
		//|| this.partie.getPlateauJeu().getPlateau()[x][y].equals(TuileCivilisation.class)
		if(this.partie.getPlateauJeu().getPlateau()[x][y]==null ||
				this.partie.getPlateauJeu().getPlateau()[x][y].equals(new TuileCivilisation(TypeTuileCivilisation.Marché)) || 
				this.partie.getPlateauJeu().getPlateau()[x][y].equals(new TuileCivilisation(TypeTuileCivilisation.Ferme)) || 
				this.partie.getPlateauJeu().getPlateau()[x][y].equals(new TuileCivilisation(TypeTuileCivilisation.Population)) || 
				this.partie.getPlateauJeu().getPlateau()[x][y].equals(new TuileCivilisation(TypeTuileCivilisation.Temple)) ){
			ok = true;
		}
		return ok;
	}
	
	/**
	 * Fonction qui permet de gérer le cas ou une tuile catastrophe sépare 2 royaume
	 * @return
	 */
	public boolean VerifierTeritoire(){
		//TODO Algo romain
		return true;
	}

	/**
	 * Execute l'action PlacerTuileCatastrophe
	 * On place ces tuiles soit sur une case vide, soit sur une tuile Civilisation.
	 * @return vrai ou faux, selon le bon déroulement ou non de l'action
	 */
	public boolean executer(){
		boolean ok = false;
		if((this.position.getX() > 11 || this.position.getY() > 16) || (this.position.getY() < 0 || this.position.getX() < 0))
		{
			ok = false;
			System.out.println("Hors Plateau !!");
		}
		else
		{
			ok = this.VerifierCase();
			ok = this.VerifierTeritoire();
		}
		return ok;
	}
}