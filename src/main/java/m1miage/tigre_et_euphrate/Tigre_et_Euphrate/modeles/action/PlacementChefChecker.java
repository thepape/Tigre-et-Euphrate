package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Placable;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Plateau;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Position;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Territoire;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TypeTuileCivilisation;

/**
 * 
 * Classe ayant la responsabilité de vérifier le bon placement d'un chef.
 *
 */
public class PlacementChefChecker {
	
	/**
	 * chef dont le placement est à vérifier
	 */
	private Chef chef;
	
	/**
	 * position de placement du chef
	 */
	private Position position;
	
	/**
	 * plateau dans lequel placer le chef
	 */
	private Plateau plateau;
	
	public PlacementChefChecker(Chef pChef, Position pPosition, Plateau pPlateau){
		this.chef = pChef;
		this.position = pPosition;
		this.plateau = pPlateau;
	}
	
	/**
	 * Vérifie que toutes les conditions de placement de chef nécéssaires soient remplies
	 * @return vrai si le chef est sur une case vide et terrestre, à coté d'un temple, et ne joint pas 2 royaumes
	 */
	public boolean verifierPlacementChef(){
		return (this.verifierCaseVide()
				&& this.verifierCaseTerre()
				&& this.verifierTempleAdjacent()
				&& this.verifierNonJointureRoyaumes());
	}
	
	/**
	 * vérifie que le chef est placé sur une case vide
	 * @return
	 */
	public boolean verifierCaseVide(){
		Placable casePlateau = this.plateau.getPlacableAt(this.position);
		return casePlateau == null;
	}
	
	/**
	 * vérifie qu'un temple est adjacent à la position
	 * @return
	 */
	public boolean verifierTempleAdjacent(){
		int x = this.position.getX();
		int y = this.position.getY();
		
		Placable caseOuest = this.plateau.getPlacableAt(new Position(x-1, y));
		Placable caseEst = this.plateau.getPlacableAt(new Position(x+1, y));
		Placable caseNord = this.plateau.getPlacableAt(new Position(x, y-1));
		Placable caseSud = this.plateau.getPlacableAt(new Position(x, y+1));
		
		TuileCivilisation temple = new TuileCivilisation(TypeTuileCivilisation.Temple);
		
		if(caseOuest.equals(temple))
			return true;
		if(caseEst.equals(temple))
			return true;
		if(caseNord.equals(temple))
			return true;
		if(caseSud.equals(temple))
			return true;
		
		return false;
	}
	
	/**
	 * vérifie que la case est bien une case de terre et non d'eau
	 * @return
	 */
	public boolean verifierCaseTerre(){
		return this.plateau.getPlateauTerrain()[this.position.getX()][this.position.getY()];
	}
	
	/**
	 * vérifie si deux placables ont un royaume différent
	 * @param pPlacable1
	 * @param pPlacable2
	 * @return
	 */
	private boolean royaumesDifferents(Placable pPlacable1, Placable pPlacable2){
		if(pPlacable1 instanceof TuileCivilisation && pPlacable2 instanceof TuileCivilisation){
			Territoire t1 = ((TuileCivilisation) pPlacable1).getTerritoire();
			Territoire t2 = ((TuileCivilisation) pPlacable2).getTerritoire();
			
			return (t1.getIdTerritoire() != t2.getIdTerritoire()
					&& t1.isEstRoyaume() 
					&& t2.isEstRoyaume());
		}
		
		return false;
	}
	
	/**
	 * vérifie la non jointure de deux royaumes différents en placant le chef
	 * @return
	 */
	public boolean verifierNonJointureRoyaumes(){
		int x = this.position.getX();
		int y = this.position.getY();
		
		Placable caseOuest = this.plateau.getPlacableAt(new Position(x-1, y));
		Placable caseEst = this.plateau.getPlacableAt(new Position(x+1, y));
		Placable caseNord = this.plateau.getPlacableAt(new Position(x, y-1));
		Placable caseSud = this.plateau.getPlacableAt(new Position(x, y+1));
		
		if(this.royaumesDifferents(caseNord, caseOuest)){
			return false;
		}
		if(this.royaumesDifferents(caseNord, caseEst)){
			return false;
		}
		if(this.royaumesDifferents(caseNord, caseSud)){
			return false;
		}
		
		return true;
	}
}
