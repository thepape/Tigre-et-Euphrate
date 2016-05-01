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
		/*if(this.partie.getPlateauJeu().getPlateau()[x][y]==null ||
				((TuileCivilisation)this.partie.getPlateauJeu().getPlateau()[x][y]).getType().equals(TypeTuileCivilisation.Marché) || 
				((TuileCivilisation)this.partie.getPlateauJeu().getPlateau()[x][y]).getType().equals(TypeTuileCivilisation.Ferme) || 
				((TuileCivilisation)this.partie.getPlateauJeu().getPlateau()[x][y]).getType().equals(TypeTuileCivilisation.Population) || 
				((TuileCivilisation)this.partie.getPlateauJeu().getPlateau()[x][y]).getType().equals(TypeTuileCivilisation.Temple) ){*/
		if(this.partie.getPlateauJeu().getPlateau()[x][y]==null || 
				this.partie.getPlateauJeu().getPlateau()[x][y] instanceof TuileCivilisation){	
			ok = true;
		}
		return ok;
	}
	

	/**
	 * Execute l'action PlacerTuileCatastrophe
	 * On place ces tuiles soit sur une case vide, soit sur une tuile Civilisation.
	 * @return vrai ou faux, selon le bon déroulement ou non de l'action
	 */
	public boolean executer(){
		boolean ok = false;
		if(this.verifier()){
			ok = true;
			this.partie.getPlateauJeu().getPlateau()[this.position.getX()][this.position.getY()]=this.tuileCatastrophe;
			this.tuileCatastrophe.setPosition(this.position);
			this.partie.getPlateauJeu().reconstruireTerritoires(this.tuileCatastrophe.getPosition());
			this.joueur.getDeckPublic().getListeTuileCatastrophe().remove(this.tuileCatastrophe);
		}
		return ok;
	}
	
	/**
	 * Verifie les conditions de pose
	 */
	public boolean verifier(){
		boolean ok = false;
		if((this.position.getX() > 10 || this.position.getY() > 15) || (this.position.getY() < 0 || this.position.getX() < 0))
		{
			ok = false;
		}
		else
		{
			ok = this.VerifierCase();
		}
		return ok;
	}
}