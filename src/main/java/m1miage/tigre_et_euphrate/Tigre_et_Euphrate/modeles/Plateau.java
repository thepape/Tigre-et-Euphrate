package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles;

import java.util.ArrayList;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.Tresor;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TypeTuileCivilisation;

/**
 * Classe representant un plateau de jeu
 *
 */
public class Plateau {
	
	/**
	 * Contient toutes les tuiles civilisations du plateau
	 */
	private Placable[][] plateau;
	
	/**
	 * Contient les données de terrain du plateau (1 = terre/0 = eau)
	 */
	private boolean[][] plateauTerrain;
	
	/**
	 * Liste des differents royaumes présent sur le plateau
	 */
	private ArrayList<Territoire> listeTerritoire;
	
	
	/**
	 * Constructeur d'un plateau avec son initialisation
	 */
	public Plateau(){
		this.plateau = new Placable[16][11];
		this.plateauTerrain = new boolean[16][11];
		this.listeTerritoire = new ArrayList<Territoire>();
		genererTerrain(this.plateauTerrain);
		genererSphynx(this.plateau);
	}
	
	/**
	 * Permet de generer le terrain en terre puis 41 fois en eau
	 * @param pplateauTerrain
	 */
	public void genererTerrain(boolean[][] pplateauTerrain){
		for(int i =0; i<pplateauTerrain.length;i++){
			for(int j=0; j<pplateauTerrain[i].length;j++){
				pplateauTerrain[i][j] = true;
			}
		}
		
		/*
		 * Le bon code en dur comme on l'aime
		 */
		pplateauTerrain[0][3] = false;
		pplateauTerrain[1][3] = false;
		pplateauTerrain[2][3] = false;
		pplateauTerrain[3][3] = false;
		pplateauTerrain[3][2] = false;
		pplateauTerrain[4][2] = false;
		pplateauTerrain[4][1] = false;
		pplateauTerrain[4][0] = false;
		pplateauTerrain[5][0] = false;
		pplateauTerrain[6][0] = false;
		pplateauTerrain[7][0] = false;
		pplateauTerrain[8][0] = false;
		pplateauTerrain[12][0] = false;
		pplateauTerrain[12][1] = false;
		pplateauTerrain[12][2] = false;
		pplateauTerrain[13][2] = false;
		pplateauTerrain[13][3] = false;
		pplateauTerrain[14][3] = false;
		pplateauTerrain[15][3] = false;
		pplateauTerrain[14][4] = false;
		pplateauTerrain[15][4] = false;
		pplateauTerrain[14][5] = false;
		pplateauTerrain[14][6] = false;
		pplateauTerrain[13][6] = false;
		pplateauTerrain[12][6] = false;
		pplateauTerrain[12][7] = false;
		pplateauTerrain[12][8] = false;
		pplateauTerrain[11][8] = false;
		pplateauTerrain[10][8] = false;
		pplateauTerrain[9][8] = false;
		pplateauTerrain[8][8] = false;
		pplateauTerrain[7][8] = false;
		pplateauTerrain[6][8] = false;
		pplateauTerrain[6][7] = false;
		pplateauTerrain[5][7] = false;
		pplateauTerrain[4][7] = false;
		pplateauTerrain[3][7] = false;
		pplateauTerrain[3][6] = false;
		pplateauTerrain[2][6] = false;
		pplateauTerrain[1][6] = false;
		pplateauTerrain[0][6] = false;
	}
	
	public void genererSphynx(Placable[][] pplateau){
		for(int i =0; i<pplateau.length;i++){
			for(int j=0; j<pplateau[i].length;j++){
				pplateau[i][j] = null;
			}
		}
		
		//Les 10 sphinx et les tresors
		pplateau[1][1] = new TuileCivilisation(new Tresor());
		pplateau[10][0] = new TuileCivilisation(new Tresor());
		pplateau[15][1] = new TuileCivilisation(new Tresor());
		pplateau[5][2] = new TuileCivilisation(new Tresor());
		pplateau[13][4] = new TuileCivilisation(new Tresor());
		pplateau[8][6] = new TuileCivilisation(new Tresor());
		pplateau[7][1] = new TuileCivilisation(new Tresor());
		pplateau[14][8] = new TuileCivilisation(new Tresor());
		pplateau[5][9] = new TuileCivilisation(new Tresor());
		pplateau[10][10] = new TuileCivilisation(new Tresor());
		
	}

	/**
	 * getter de Plateau
	 * @return
	 */
	public Placable[][] getPlateau() {
		return plateau;
	}
	
	/**
	 * getter du placable à la position passée en paramètre
	 * @param pPos
	 * @return
	 */
	public Placable getPlacableAt(Position pPos){
		return this.plateau[pPos.getX()][pPos.getY()];
	}

	/**
	 * setter de plateau
	 * @param plateau
	 */
	public void setPlateau(TuileCivilisation[][] plateau) {
		this.plateau = plateau;
	}

	/**
	 * getter de plateauterrain
	 * @return
	 */
	public boolean[][] getPlateauTerrain() {
		return plateauTerrain;
	}

	/**
	 * setter de plateauterrain
	 * @param plateauTerrain
	 */
	public void setPlateauTerrain(boolean[][] plateauTerrain) {
		this.plateauTerrain = plateauTerrain;
	}
	
	/**
	 * Permet d'afficher le terrain dans la console
	 */
	public void afficherPlateauTerrain(){
		for(int i =0; i<this.plateauTerrain.length;i++){
			for(int j=0; j<this.plateauTerrain[i].length;j++){
				System.out.print(plateauTerrain[i][j]+" ");
			}
			System.out.print("\n");
		}
	}
	
	/**
	 * permet de retourner la liste des royaumes
	 * @return liste des royaumes
	 */
	public ArrayList<Territoire> getListeRoyaume(){
		return listeTerritoire;
	}
	
	/**
	 * permet d'ajouter un royaume dans la liste 
	 * @param proyaume
	 */
	public void addRoyaume(Territoire proyaume){
		this.listeTerritoire.add(proyaume);
	}
	
	/**
	 * permet de supprimer un royaume dans la liste
	 * @param proyaume
	 */
	public void supprRoyaume(Territoire proyaume){
		this.listeTerritoire.remove(proyaume);
	}
	
	/**
	 * Permet de placer une tuile civilisation sur le plateau
	 * @param ptuile tuile a placer
	 * @param ppos position ou placer
	 */
	public boolean placerTuile(TuileCivilisation ptuile, Position ppos){
		int x = ppos.getX();
		int y = ppos.getY();
		
		if(this.plateau[x][y] != null){
			return false;
		}
		if(!this.plateauTerrain[x][y] && !ptuile.estTuileEau()){
			return false;
		}
		if(this.plateauTerrain[x][y] && ptuile.estTuileEau()){
			return false;
		}
		
		this.plateau[x][y] = ptuile;
		ptuile.placer(ppos);
		return true;
	}

	
	/**
	 * Permet de placer une tuile chef sur le plateau
	 * @param pchef chef a placer
	 * @param ppos position ou placer
	 * @return true si placer sinon false
	 */
	public boolean placerChef(Chef pchef, Position ppos){
		int x = ppos.getX();
		int y = ppos.getY();
		
		if(this.plateau[x][y] != null){
			return false;
		}
		if(!this.plateauTerrain[x][y]){
			return false;
		}
		
		boolean ok = verifierTemple(ppos);
		
		if(!ok){
			return false;
		}
		
		this.plateau[x][y] = pchef;
		return true;
	}
	
	/**
	 * Permet de verifier si il y a un temple a côté de la position ou on veut placer le chef #pavéCésar
	 * @param ppos position pour le chef
	 * @return si temple adjacent true sinon false
	 */
	public boolean verifierTemple(Position ppos){
		int x = ppos.getX();
		int y = ppos.getY();
		TuileCivilisation tuileCivilisation;
		
		if(x-1>=0 && x-1<=15){
			if(this.plateau[x-1][y] instanceof TuileCivilisation){
				tuileCivilisation = (TuileCivilisation)this.plateau[x+1][y];
				if(tuileCivilisation.getType().equals(new TypeTuileCivilisation("rouge","Temple")))
					return true;
			}
		}
		
		if(x+1<=15 && x+1>=0){
			if(this.plateau[x+1][y] instanceof TuileCivilisation){
				tuileCivilisation = (TuileCivilisation)this.plateau[x+1][y];
				if(tuileCivilisation.getType().equals(new TypeTuileCivilisation("rouge","Temple")))
					return true;
			}
		}
		if(y-1>=0 && y-1<=11){
			if(this.plateau[x][y-1] instanceof TuileCivilisation){
				tuileCivilisation = (TuileCivilisation)this.plateau[x+1][y];
				if(tuileCivilisation.getType().equals(new TypeTuileCivilisation("rouge","Temple")))
					return true;
			}
		}
		
		if(y+1<=11 && y+1>=0){
			if(this.plateau[x][y+1] instanceof TuileCivilisation){
				tuileCivilisation = (TuileCivilisation)this.plateau[x+1][y];
				if(tuileCivilisation.getType().equals(new TypeTuileCivilisation("rouge","Temple")))
					return true;
			}
		}		
		return false;
	}
	
}
