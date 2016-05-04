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
	 * Le nombre de trésors présents sur le plateau
	 */
	private static int nombreTresors = 10;


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
		pplateau[1][1].setPosition(new Position(1,1));
		this.listeTerritoire.add(new Territoire((TuileCivilisation) pplateau[1][1]));
		pplateau[0][10] = new TuileCivilisation(new Tresor());
		pplateau[0][10].setPosition(new Position(0,10));
		this.listeTerritoire.add(new Territoire((TuileCivilisation) pplateau[0][10]));
		pplateau[1][15] = new TuileCivilisation(new Tresor());
		pplateau[1][15].setPosition(new Position(1,15));
		this.listeTerritoire.add(new Territoire((TuileCivilisation) pplateau[1][15]));
		pplateau[2][5] = new TuileCivilisation(new Tresor());
		pplateau[2][5].setPosition(new Position(2,5));
		this.listeTerritoire.add(new Territoire((TuileCivilisation) pplateau[2][5]));
		pplateau[4][13] = new TuileCivilisation(new Tresor());
		pplateau[4][13].setPosition(new Position(4,13));
		this.listeTerritoire.add(new Territoire((TuileCivilisation) pplateau[4][13]));
		pplateau[6][8] = new TuileCivilisation(new Tresor());
		pplateau[6][8].setPosition(new Position(6,8));
		this.listeTerritoire.add(new Territoire((TuileCivilisation) pplateau[6][8]));
		pplateau[7][1] = new TuileCivilisation(new Tresor());
		pplateau[7][1].setPosition(new Position(7,1));
		this.listeTerritoire.add(new Territoire((TuileCivilisation) pplateau[7][1]));
		pplateau[8][14] = new TuileCivilisation(new Tresor());
		pplateau[8][14].setPosition(new Position(8,14));
		this.listeTerritoire.add(new Territoire((TuileCivilisation) pplateau[8][14]));
		pplateau[9][5] = new TuileCivilisation(new Tresor());
		pplateau[9][5].setPosition(new Position(9,5));
		this.listeTerritoire.add(new Territoire((TuileCivilisation) pplateau[9][5]));
		pplateau[10][10] = new TuileCivilisation(new Tresor());
		pplateau[10][10].setPosition(new Position(10,10));
		this.listeTerritoire.add(new Territoire((TuileCivilisation) pplateau[10][10]));

	}

	/**
	 * Fonction retournant le nombre de trésors restants sur le plateau
	 * @return le nombreTresors
	 */
	public static int getNombreTresors() {
		return nombreTresors;
	}

	/**
	 * Fonction permettant de décrémenter le nombre de trésors
	 */
	public static void decrementerNombreTresors(){
		if(nombreTresors > 0)
			nombreTresors--;
	}

	/**
	 * @param nombreTresors the nombreTresors to set
	 */
	public static void setNombreTresors(int nombreTresors) {
		Plateau.nombreTresors = nombreTresors;
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
		if(pPos.getX() < 0 || pPos.getX() > 10 || pPos.getY() < 0 || pPos.getY() > 15){
			return null;
		}


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

	public boolean placerTuile(TuileCivilisation pTuile, int x, int y){
		return this.placerTuile(pTuile, new Position(x,y));
	}
	
	public boolean verifierPlacerTuile(TuileCivilisation ptuile, Position ppos){
		int x = ppos.getX();
		int y = ppos.getY();

		if(x < 0 || y < 0 || x > 10 || y > 15)
		{
			return false;
		} else {
			if(this.plateau[x][y] != null){
				return false;
			}
			if(!this.plateauTerrain[x][y] && !ptuile.estTuileEau()){
				return false;
			}
			if(this.plateauTerrain[x][y] && ptuile.estTuileEau()){
				return false;
			}
			return true;

		}
	}

	/**
	 * Permet de placer une tuile civilisation sur le plateau
	 * @param ptuile tuile a placer
	 * @param ppos position ou placer
	 */
	public boolean placerTuile(TuileCivilisation ptuile, Position ppos){
		int x = ppos.getX();
		int y = ppos.getY();

		if(x < 0 || y < 0 || x > 10 || y > 15)
		{
			return false;
		} else {
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
	}


	/**
	 * Permet de placer une tuile chef sur le plateau
	 * @param pchef chef a placer
	 * @param ppos position ou placer
	 * @return true si placer sinon false
	 */
	public boolean placerChef(Chef pchef, Position ppos){
		if(!this.verifierPlacerChef(pchef, ppos)){
			return false;
		}
		
		this.plateau[ppos.getX()][ppos.getY()] = pchef;
		
		return true;
	}
	
	public boolean verifierPlacerChef(Chef pchef, Position ppos){
		int x = ppos.getX();
		int y = ppos.getY();

		if(x > 10 || y > 15 || x < 0 || y < 0)
		{
			return false;
		}
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

		if(x-1>=0 && x-1<=10){
			if(this.plateau[x-1][y] instanceof TuileCivilisation){
				tuileCivilisation = (TuileCivilisation)this.plateau[x-1][y];
				if(tuileCivilisation.getType().equals(TypeTuileCivilisation.Temple) && !tuileCivilisation.estTuileMonument())
					return true;
			}
		}

		if(x+1<=10 && x+1>=0){
			if(this.plateau[x+1][y] instanceof TuileCivilisation){
				tuileCivilisation = (TuileCivilisation)this.plateau[x+1][y];
				if(tuileCivilisation.getType().equals(TypeTuileCivilisation.Temple) && !tuileCivilisation.estTuileMonument())
					return true;
			}
		}
		if(y-1>=0 && y-1<=15){
			if(this.plateau[x][y-1] instanceof TuileCivilisation){
				tuileCivilisation = (TuileCivilisation)this.plateau[x][y-1];
				if(tuileCivilisation.getType().equals(TypeTuileCivilisation.Temple) && !tuileCivilisation.estTuileMonument())
					return true;
			}
		}

		if(y+1<=15 && y+1>=0){
			if(this.plateau[x][y+1] instanceof TuileCivilisation){
				tuileCivilisation = (TuileCivilisation)this.plateau[x][y+1];
				if(tuileCivilisation.getType().equals(TypeTuileCivilisation.Temple) && !tuileCivilisation.estTuileMonument())
					return true;
			}
		}
		return false;
	}
	
	public ArrayList<Placable> recupererListePlacableAdjacants(Position position)
	{
		ArrayList<Placable> listeAdjacente = new ArrayList<Placable>();
		if(position.getX() < 11 && position.getY() < 16 && position.getX() > -1 && position.getY() > -1)
		{

			if(position.getX() + 1 < 11 && (this.getPlateau()[position.getX() + 1 ][position.getY()] instanceof TuileCivilisation
					|| this.getPlateau()[position.getX() + 1 ][position.getY()] instanceof Chef))
			{
				Placable tuileAdjacente = (Placable) this.getPlateau()[position.getX() + 1][position.getY()];
				listeAdjacente.add(tuileAdjacente);
			}

			if(position.getX() - 1 > -1 && (this.getPlateau()[position.getX() - 1][position.getY()] instanceof TuileCivilisation
					|| this.getPlateau()[position.getX() - 1 ][position.getY()] instanceof Chef))
			{
				Placable tuileAdjacente = (Placable) this.getPlateau()[position.getX() - 1][position.getY()];
				listeAdjacente.add(tuileAdjacente);
			}

			if(position.getY() + 1 < 16 && (this.getPlateau()[position.getX()][position.getY() + 1] instanceof TuileCivilisation
					|| this.getPlateau()[position.getX()][position.getY()+1] instanceof Chef))
			{
				Placable tuileAdjacente = (Placable) this.getPlateau()[position.getX()][position.getY()+1];
				listeAdjacente.add(tuileAdjacente);
			}

			if(position.getY() - 1 > -1 && (this.getPlateau()[position.getX()][position.getY() - 1] instanceof TuileCivilisation
					|| this.getPlateau()[position.getX()][position.getY() - 1] instanceof Chef))
			{
				Placable tuileAdjacente = (Placable) this.getPlateau()[position.getX()][position.getY()-1];
				listeAdjacente.add(tuileAdjacente);
			}
		}
		return listeAdjacente;
	}

	/**
	 * Fonction qui renvoie la liste des tuiles civilisation adjacentes
	 * @param position
	 * @return liste des tuiles adjacentes
	 */
	public ArrayList<TuileCivilisation> recupererListeTuileCivilisationAdjacente(Position position)
	{
		ArrayList<TuileCivilisation> listeAdjacente = new ArrayList<TuileCivilisation>();
		if(position.getX() < 11 && position.getY() < 16 && position.getX() > -1 && position.getY() > -1)
		{

			if(position.getX() + 1 < 11 && this.getPlateau()[position.getX() + 1 ][position.getY()] instanceof TuileCivilisation)
			{
				TuileCivilisation tuileAdjacente = (TuileCivilisation) this.getPlateau()[position.getX() + 1][position.getY()];
				listeAdjacente.add(tuileAdjacente);
			}

			if(position.getX() - 1 > -1 && this.getPlateau()[position.getX() - 1][position.getY()] instanceof TuileCivilisation)
			{
				//System.out.println("X - 1");
				TuileCivilisation tuileAdjacente = (TuileCivilisation) this.getPlateau()[position.getX() - 1][position.getY()];
				listeAdjacente.add(tuileAdjacente);
			}

			if(position.getY() + 1 < 16 && this.getPlateau()[position.getX()][position.getY() + 1] instanceof TuileCivilisation)
			{
				TuileCivilisation tuileAdjacente = (TuileCivilisation) this.getPlateau()[position.getX()][position.getY() + 1];
				listeAdjacente.add(tuileAdjacente);
			}

			if(position.getY() - 1 > -1 && this.getPlateau()[position.getX()][position.getY() - 1] instanceof TuileCivilisation)
			{
				TuileCivilisation tuileAdjacente = (TuileCivilisation) this.getPlateau()[position.getX()][position.getY() - 1];
				listeAdjacente.add(tuileAdjacente);
			}
		}
		return listeAdjacente;
	}
	
	public ArrayList<Chef> recupererListeChefsAdjacente(Position position){
		ArrayList<Chef> listeChefs = new ArrayList<Chef>();
		int x = position.getX();
		int y = position.getY();
		
		Placable pNord = this.getPlacableAt(new Position(x-1,y));
		Placable pEst = this.getPlacableAt(new Position(x,y+1));
		Placable pSud = this.getPlacableAt(new Position(x+1,y));
		Placable pOuest = this.getPlacableAt(new Position(x,y-1));
		
		if(pNord != null && pNord instanceof Chef){
			listeChefs.add((Chef) pNord);
		}
		if(pEst != null && pEst instanceof Chef){
			listeChefs.add((Chef) pEst);
		}
		if(pSud != null && pSud instanceof Chef){
			listeChefs.add((Chef) pSud);
		}
		if(pOuest != null && pOuest instanceof Chef){
			listeChefs.add((Chef) pOuest);
		}
		
		return listeChefs;
	}

	public void reconstruireTerritoires(Position pDepart){
		Territoire tNord = new Territoire();
		Territoire tEst = new Territoire();
		Territoire tSud = new Territoire();
		Territoire tOuest = new Territoire();

		
		
		//construction de 4 territoires en partant des 4 voisins
		this.reconstruireTerritoiresRecurs(new Position(pDepart.getX()-1, pDepart.getY()), tNord);
		this.reconstruireTerritoiresRecurs(new Position(pDepart.getX(), pDepart.getY()+1), tEst);
		this.reconstruireTerritoiresRecurs(new Position(pDepart.getX()+1, pDepart.getY()), tSud);
		this.reconstruireTerritoiresRecurs(new Position(pDepart.getX(), pDepart.getY()-1), tOuest);

		//on élimine les territoires identiques pour ne garder que les territoires differents
		ArrayList<Territoire> territoires = new ArrayList<Territoire>();

		if(tNord.getTuilesCivilisation().size() > 0)
			territoires.add(tNord);
		if(tEst.getTuilesCivilisation().size() > 0)
			territoires.add(tEst);
		if(tSud.getTuilesCivilisation().size() > 0)
			territoires.add(tSud);
		if(tOuest.getTuilesCivilisation().size() > 0)
			territoires.add(tOuest);

		for(int i = 0; i < territoires.size(); i ++){
			for(int j = i+1;j < territoires.size(); j++){
				Territoire t1 = territoires.get(i);
				Territoire t2 = territoires.get(j);
				boolean same = t1.comparerTuilesEtChef(t2);

				if(same){
					territoires.remove(j);
					j--;
				}
			}
		}

		//on applique les nouveaux territoires aux tuiles
		for(Territoire t : territoires){
			//Territoire oldTerritoire = t.getTuilesCivilisation().get(0).getTerritoire();
			Territoire oldTerritoire = this.recupererTerritoireTuile(t.getTuilesCivilisation().get(0));
			/*for(TuileCivilisation tuile : t.getTuilesCivilisation()){
				//tuile.setTerritoire(t);
			}*/

			/*for(Chef chef : t.getChefs()){
				chef.setTerritoire(t);
			}*/

			//on supprime l'ancien territoire on et on ajoute le nouveau
			this.listeTerritoire.remove(oldTerritoire);
			this.listeTerritoire.add(t);
		}
	}

	private void reconstruireTerritoiresRecurs(Position pDepart, Territoire territoire){
		/*
		Placable pNord = this.getPlacableAt(new Position(pDepart.getX()-1, pDepart.getY()));
		this.gererTuileDansReconstruction(pNord, territoire);

		Placable pEst = this.getPlacableAt(new Position(pDepart.getX(), pDepart.getY()+1));
		this.gererTuileDansReconstruction(pEst, territoire);

		Placable pSud = this.getPlacableAt(new Position(pDepart.getX()+1, pDepart.getY()));
		this.gererTuileDansReconstruction(pSud, territoire);

		Placable pOuest = this.getPlacableAt(new Position(pDepart.getX(), pDepart.getY()-1));
		this.gererTuileDansReconstruction(pOuest, territoire);

		//on ajoute finallement cette tuile
		Placable centre = this.getPlacableAt(pDepart);
		this.gererTuileDansReconstruction(centre, territoire);*/
		
		Placable placable = this.getPlacableAt(pDepart);
		this.gererTuileDansReconstruction(placable, territoire);
	}

	private void gererTuileDansReconstruction(Placable placable, Territoire territoire){
		//on verifie que la case Nord est une tuile civ
				if(placable != null && placable instanceof TuileCivilisation){
					TuileCivilisation tuile = (TuileCivilisation) placable;

					//on verifie si elle est n'est pas deja dans la liste et qu'elle n'est pas jonction
					if(!territoire.contientTuileCivilisation(tuile) && !tuile.estJonction()){
						territoire.addTuile(tuile);

						//appel recursif sur les 4 voisins
						int x= tuile.getPosition().getX();
						int y= tuile.getPosition().getY();
						
						this.reconstruireTerritoiresRecurs(new Position(x-1,y), territoire);
						this.reconstruireTerritoiresRecurs(new Position(x,y-1), territoire);
						this.reconstruireTerritoiresRecurs(new Position(x+1,y), territoire);
						this.reconstruireTerritoiresRecurs(new Position(x,y+1), territoire);
					}
				}else if(placable != null && placable instanceof Chef){
					Chef chef = (Chef) placable;

					//on verifie si elle est n'est pas deja dans la liste
					if(!territoire.contientChef(chef)){
						territoire.addChefs(chef);

						//appel recursif
						int x= chef.getPosition().getX();
						int y= chef.getPosition().getY();
						
						this.reconstruireTerritoiresRecurs(new Position(x-1,y), territoire);
						this.reconstruireTerritoiresRecurs(new Position(x,y-1), territoire);
						this.reconstruireTerritoiresRecurs(new Position(x+1,y), territoire);
						this.reconstruireTerritoiresRecurs(new Position(x,y+1), territoire);
					}
				}
	}



	public Territoire recupererTerritoireTuile(Placable tuile) {
		Territoire territoire = null;

		for(int i = 0; i < this.listeTerritoire.size(); i++)
		{
			Territoire t = this.listeTerritoire.get(i);
			
			if(tuile instanceof TuileCivilisation)
			{
				if(t.getTuilesCivilisation().contains(tuile)){
					territoire = t;
					break;
				}
				/*
				for(int j = 0; j < t.getTuilesCivilisation().size(); j++)
				{
					if(t.getTuilesCivilisation().get(j).getId() == tuile.getId())
					{
						territoire = t;
					}
				}*/
			} else if(tuile instanceof Chef) {
				if(t.getChefs().contains(tuile)){
					territoire = t;
					break;
				}
				/*
				for(int j = 0; j < t.getChefs().size(); j++)
				{
					if(t.getChefs().get(j).getId() == tuile.getId())
					{
						territoire = t;
					}
				}*/
			}

		}
		return territoire;
	}
	
	public String afficher(){
		StringBuffer sb = new StringBuffer("");
		
		for(int x = 0; x < 11; x++){
			for(int y = 0; y < 16;y++){
				sb.append("[");
				
				Placable placable = this.plateau[x][y];
				
				if(placable instanceof TuileCivilisation){
					TuileCivilisation tuile = (TuileCivilisation) placable;
					sb.append(tuile.getType().getNom().substring(0, 1)+" ");
				}
				else if(placable instanceof Chef){
					Chef chef = (Chef) placable;
					sb.append(chef.getTypeChef().getNom().substring(0, 1)+"*");
				}
				else{
					sb.append("  ");
				}
				
				sb.append("]");
			}
			
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	public String afficherTuilesId(){
		StringBuffer sb = new StringBuffer("");
		
		for(int x = 0; x < 11; x++){
			for(int y = 0; y < 16;y++){
				sb.append("[");
				
				Placable placable = this.plateau[x][y];
				
				if(placable != null){
					int id = placable.getId();
					if(id < 10){
						sb.append(" "+id);
					}
					else{
						sb.append(id);
					}
				}
				else{
					sb.append("  ");
				}
				
				sb.append("]");
			}
			
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	public String afficherTerritoires(){
		StringBuffer sb = new StringBuffer("");
		
		for(int x = 0; x < 11; x++){
			for(int y = 0; y < 16;y++){
				sb.append("[");
				
				Placable placable = this.plateau[x][y];
				
				Territoire terr = this.recupererTerritoireTuile(placable);
				
				if(terr != null){
					if(terr.getIdTerritoire() < 10)
						sb.append(" "+terr.getIdTerritoire());
					else
						sb.append(terr.getIdTerritoire());
				}
				else{
					sb.append("  ");
				}
				
				sb.append("]");
			}
			
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	public TuileCivilisation recupererTuileJonction(){
		for(int x = 0; x < 11 ; x++){
			for(int y = 0;y < 16; y++){
				Placable p = this.plateau[x][y];
				
				if(p instanceof TuileCivilisation && ((TuileCivilisation) p).estJonction()){
					return (TuileCivilisation) p;
				}
			}
		}
		
		return null;
	}
}
