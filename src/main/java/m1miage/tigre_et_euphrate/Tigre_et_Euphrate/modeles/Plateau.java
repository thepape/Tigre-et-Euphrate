package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles;

import java.io.Serializable;
import java.util.ArrayList;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.Tresor;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TypeTuileCivilisation;

/**
 * Classe representant un plateau de jeu
 *
 */
public class Plateau implements Serializable {

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
		this.plateau = new Placable[11][16];
		this.plateauTerrain = new boolean[11][16];
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
		pplateauTerrain[3][0] = false;
		pplateauTerrain[3][1] = false;
		pplateauTerrain[3][2] = false;
		pplateauTerrain[3][3] = false;
		pplateauTerrain[2][3] = false;
		pplateauTerrain[2][4] = false;
		pplateauTerrain[1][4] = false;
		pplateauTerrain[0][4] = false;
		pplateauTerrain[0][5] = false;
		pplateauTerrain[0][6] = false;
		pplateauTerrain[0][7] = false;
		pplateauTerrain[0][8] = false;
		pplateauTerrain[0][12] = false;
		pplateauTerrain[1][12] = false;
		pplateauTerrain[2][12] = false;
		pplateauTerrain[2][13] = false;
		pplateauTerrain[3][13] = false;
		pplateauTerrain[3][14] = false;
		pplateauTerrain[3][15] = false;
		pplateauTerrain[4][14] = false;
		pplateauTerrain[4][15] = false;
		pplateauTerrain[5][14] = false;
		pplateauTerrain[6][14] = false;
		pplateauTerrain[6][13] = false;
		pplateauTerrain[6][12] = false;
		pplateauTerrain[7][12] = false;
		pplateauTerrain[8][12] = false;
		pplateauTerrain[8][11] = false;
		pplateauTerrain[8][10] = false;
		pplateauTerrain[8][9] = false;
		pplateauTerrain[8][8] = false;
		pplateauTerrain[8][7] = false;
		pplateauTerrain[8][6] = false;
		pplateauTerrain[7][6] = false;
		pplateauTerrain[7][5] = false;
		pplateauTerrain[7][4] = false;
		pplateauTerrain[7][3] = false;
		pplateauTerrain[6][3] = false;
		pplateauTerrain[6][2] = false;
		pplateauTerrain[6][1] = false;
		pplateauTerrain[6][0] = false;
	}

	public void genererSphynx(Placable[][] pplateau){
		for(int i =0; i<pplateau.length;i++){
			for(int j=0; j<pplateau[i].length;j++){
				pplateau[i][j] = null;
			}
		}

		//Les 10 sphinx et les tresors
		pplateau[1][1] = new TuileCivilisation(new Tresor());
		pplateau[0][10] = new TuileCivilisation(new Tresor());
		pplateau[1][15] = new TuileCivilisation(new Tresor());
		pplateau[2][5] = new TuileCivilisation(new Tresor());
		pplateau[4][13] = new TuileCivilisation(new Tresor());
		pplateau[6][8] = new TuileCivilisation(new Tresor());
		pplateau[1][7] = new TuileCivilisation(new Tresor());
		pplateau[8][14] = new TuileCivilisation(new Tresor());
		pplateau[9][5] = new TuileCivilisation(new Tresor());
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
		return this.plateau[pPos.getY()][pPos.getX()];
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

		if(x-1>=0 && x-1<=11){
			if(this.plateau[x-1][y] instanceof TuileCivilisation){
				tuileCivilisation = (TuileCivilisation)this.plateau[x+1][y];
				if(tuileCivilisation.getType().equals(new TypeTuileCivilisation("rouge","Temple")))
					return true;
			}
		}

		if(x+1<=11 && x+1>=0){
			if(this.plateau[x+1][y] instanceof TuileCivilisation){
				tuileCivilisation = (TuileCivilisation)this.plateau[x+1][y];
				if(tuileCivilisation.getType().equals(new TypeTuileCivilisation("rouge","Temple")))
					return true;
			}
		}
		if(y-1>=0 && y-1<=16){
			if(this.plateau[x][y-1] instanceof TuileCivilisation){
				tuileCivilisation = (TuileCivilisation)this.plateau[x+1][y];
				if(tuileCivilisation.getType().equals(new TypeTuileCivilisation("rouge","Temple")))
					return true;
			}
		}

		if(y+1<=16 && y+1>=0){
			if(this.plateau[x][y+1] instanceof TuileCivilisation){
				tuileCivilisation = (TuileCivilisation)this.plateau[x+1][y];
				if(tuileCivilisation.getType().equals(new TypeTuileCivilisation("rouge","Temple")))
					return true;
			}
		}
		return false;
	}

	/**
	 * Fonction qui renvoie la liste des tuiles civilisation adjacentes
	 * @param position
	 * @return liste des tuiles adjacentes
	 */
	public ArrayList<TuileCivilisation> recupererListeTuileCivilisationAdjacente(Position position)
	{
		ArrayList<TuileCivilisation> listeAdjacente = new ArrayList<TuileCivilisation>();

		if(position.getX() + 1 < 11 && this.getPlateau()[position.getX() + 1 ][position.getY()] instanceof TuileCivilisation)
		{
			TuileCivilisation tuileAdjacente = (TuileCivilisation) this.getPlateau()[position.getX() + 1][position.getY()];
			listeAdjacente.add(tuileAdjacente);
		} else if(position.getX() - 1 > -1 && this.getPlateau()[position.getX() - 1][position.getY()] instanceof TuileCivilisation)
		{
			TuileCivilisation tuileAdjacente = (TuileCivilisation) this.getPlateau()[position.getX() - 1][position.getY()];
			listeAdjacente.add(tuileAdjacente);
		} else if(position.getY() + 1 < 15 && this.getPlateau()[position.getX()][position.getY() + 1] instanceof TuileCivilisation)
		{
			TuileCivilisation tuileAdjacente = (TuileCivilisation) this.getPlateau()[position.getX()][position.getY() + 1];
			listeAdjacente.add(tuileAdjacente);
		} else if(position.getY() - 1 > -1 && this.getPlateau()[position.getX() ][position.getY() - 1] instanceof TuileCivilisation)
		{
			TuileCivilisation tuileAdjacente = (TuileCivilisation) this.getPlateau()[position.getX()][position.getY() - 1];
			listeAdjacente.add(tuileAdjacente);
		}
		return listeAdjacente;
	}


}
