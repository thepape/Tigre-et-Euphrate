package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action;

import java.util.ArrayList;
import java.util.ListIterator;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Plateau;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Position;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Royaume;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TypeTuileCivilisation;

/**
 * 
 * Classe hérite de la classe Action et permet de placer un chef
 *
 */
public class PlacerChef extends Action {
	
	/**
	 * Chef 
	 */
	private Chef chef;
	
	/**
	 * Position souhaitee
	 */
	private Position position;
	
	/**
	 * @param ppartie
	 * @param pjoueur
	 * @param pchef
	 * @param ppos
	 */
	public PlacerChef(Partie ppartie, Joueur pjoueur, Chef pchef, Position ppos) {
		super(ppartie, pjoueur);
		this.chef = pchef;
		this.position = ppos;
	}

	/**
	 * Execute l'action PlacerChef
	 * On place le chef dans la case du tableau
	 * On supprimer le chef du deck du joueur
	 * @return vrai ou faux, selon le bon déroulement ou non de l'action
	 */
	public boolean executer(){
		// Tester si le joueur fait action sur son chef à lui
		if(this.chef.getDynastie() != this.joueur.getDynastie())
			return false;
		// Tester si case vide et si voisinage temple et si non placé sur fleuve
		if(!this.partie.getPlateauJeu().placerChef(this.chef, this.position))
			return false;
		this.joueur.getDeckPublic().supprimer(this.chef);
		ListIterator<Royaume> it = this.partie.getPlateauJeu().getListeRoyaume().listIterator();
		boolean estDansRoyaume = false;
		boolean conflit = false;
		boolean trouve_terr = false;
		while(it.hasNext() && !estDansRoyaume){
			Royaume royaume = it.next();
			// Test si le chef est dans un royaume
			//if(royaume.estAdjacent(this.position))
			if(true)
			{
				// Test si il y a un chef de même couleur dans le royaume
				ListIterator<Chef> itc = royaume.getChefs().listIterator();
				while(itc.hasNext() && !conflit){
					if(itc.next().getTypeChef().equals(this.chef.getTypeChef()))
						conflit = true;
				}
				// Ajouter le chef au royaume
				royaume.addChefs(this.chef);
			}else{
				// CREATION D'UN ROYAUME
				Royaume nouveauRoyaume = new Royaume();
				nouveauRoyaume.addChefs(this.chef);
				/*ArrayList<TuileCivilisation> tuilesCivilisation = new ArrayList<TuileCivilisation>();
				ArrayList<Chef> chefs = new ArrayList<Chef>();
				chefs.add(this.chef);
				Royaume royaume = new Royaume(tuilesCivilisation,chefs);*/	
			}
		}
		if(conflit){
			// CREATION D'UN CONFLIT
		}
		return true;
	}
	
	public static void main(String[] args) {
		Plateau p = new Plateau();
		p.getPlateau()[2][2] = new TuileCivilisation(new TypeTuileCivilisation("rouge","Temple"));
		ArrayList<TuileCivilisation> tc = new ArrayList<TuileCivilisation>();
		TuileCivilisation ter = new TuileCivilisation(new TypeTuileCivilisation("rouge","Temple"));
		ter.placer(new Position(9,0));
		TuileCivilisation ter2 = new TuileCivilisation(new TypeTuileCivilisation("rouge","Temple"));
		ter2.placer(new Position(8,0));
		tc.add(ter);
		tc.add(ter2);
		ArrayList<Chef> tchef = new ArrayList<Chef>();
		Chef c1 = new Chef();
		tchef.add(c1);
		Royaume r = new Royaume(tc,tchef);
		//System.out.println(r.estAdjacent(new Position(9,2)));
	}
}