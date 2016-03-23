package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.Tresor;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;

/**
 * Classe representant un plateau de jeu
 *
 */
public class Plateau {
	
	/**
	 * Contient toutes les tuiles civilisations du plateau
	 */
	private TuileCivilisation[][] plateau;
	
	/**
	 * Contient les données de terrain du plateau (1 = terre/0 = eau)
	 */
	private boolean[][] plateauTerrain;
	
	
	/**
	 * Constructeur d'un plateau avec son initialisation
	 */
	public Plateau(){
		this.plateau = new TuileCivilisation[16][11];
		this.plateauTerrain = new boolean[16][11];
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
	
	public void genererSphynx(TuileCivilisation[][] pplateau){
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
	public TuileCivilisation[][] getPlateau() {
		return plateau;
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
	
	
}
