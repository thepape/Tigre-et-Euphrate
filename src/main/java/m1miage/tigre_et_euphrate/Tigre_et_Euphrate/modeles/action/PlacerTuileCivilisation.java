package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action;

import java.util.ArrayList;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Position;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Territoire;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.Tuile;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TypeTuileCivilisation;

/**
 *
 * Classe hérite de la classe Action et permet de placer une tuile civilisation
 *
 */
public class PlacerTuileCivilisation extends Action {

	private Position position;

	private TuileCivilisation tuile;

	/**
	 * getter de la position
	 * @return position
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * setter de la position
	 * @param position
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * getter de la tuile
	 * @return tuile
	 */
	public Tuile getTuile() {
		return tuile;
	}

	/**
	 * setter de la tuile
	 * @param tuile
	 */
	public void setTuile(TuileCivilisation tuile) {
		this.tuile = tuile;
	}

	/**
	 * @param partie
	 * @param joueur
	 */
	public PlacerTuileCivilisation(Partie partie, Joueur joueur, Position position, TuileCivilisation tuile) {
		super(partie, joueur);
		this.position = position;
		this.tuile = tuile;
	}
	
	/**
	 * Fonction pour attribuer les points victoire
	 */
	public void AttribuerPoint()
	{
		TypeTuileCivilisation t = this.tuile.getType();
		//if(this.tuile.getType().equals(TypeTuileCivilisation.Ferme))	
	}
	
	/**
	 * Fonction pour attribuer un territoire à
	 * une nouvelle tuile
	 * 
	 * Fonction qui pique les yeux aussi !!! voir pour refactoriser
	 * 
	 * @return true si la tuile peut etre posé
	 */
	public boolean attribuerTerritoire() {
		
		boolean poseOk = false;
		int x = this.position.getX();
		int y = this.position.getY();
		Position pos1 = new Position(x-1,y);
		Position pos2 = new Position(x+1,y);
		Position pos3 = new Position(x,y-1);
		Position pos4 = new Position(x,y+1);
		boolean ok1 = true;
		boolean ok2 = true;
		boolean ok3 = true;
		boolean ok4 = true;
		ArrayList<Position> listparam = new ArrayList<Position>();
		
		//Check si les 4 cases autour sont sur le plateau
		if((pos1.getX() > 11 || pos1.getY() > 16) || (pos1.getY() < 0 || pos1.getX() < 0)){
			ok1 = false;
		}
		if((pos2.getX() > 11 || pos2.getY() > 16) || (pos2.getY() < 0 || pos2.getX() < 0)){
			ok2 = false;
		}
		if((pos3.getX() > 11 || pos3.getY() > 16) || (pos3.getY() < 0 || pos3.getX() < 0)){
			ok3 = false;
		}
		if((pos4.getX() > 11 || pos4.getY() > 16) || (pos4.getY() < 0 || pos4.getX() < 0)){
			ok4 = false;
		}
		
		
		//Check quelle cases ont un chef ou une tuile
		/**
		 * Compteur de tuile/Chef autour
		 */
		int nb = 0;
		if((ok1=true) && ( this.partie.getPlateauJeu().getPlateau()[pos1.getX()][pos1.getY()]!=null)){
			nb+=1;
			listparam.add(pos1);
		}
		if((ok2=true) && ( this.partie.getPlateauJeu().getPlateau()[pos2.getX()][pos2.getY()]!=null)){
			nb+=1;
			listparam.add(pos2);
		}
		if((ok3=true) && ( this.partie.getPlateauJeu().getPlateau()[pos3.getX()][pos3.getY()]!=null)){
			nb+=1;
			listparam.add(pos3);
		}
		if((ok4=true) && ( this.partie.getPlateauJeu().getPlateau()[pos4.getX()][pos4.getY()]!=null)){
			nb+=1;
			listparam.add(pos3);
		}
		
		
		/*
		 * Algo
		 * Si 0 alors nouveau territoire
		 * Si 1 tuile alors add sont territoire
		 * Si 2 tuiles alors
		 * 	checker si chef
		 * 		chef dans 1seul ou pas de chef alors former un nouveau territoire
		 * 	sinon conflit
		 * si plus que 2 interdire la pose
		 */
		
		if(nb==0){
			this.tuile.setTerritoire(new Territoire());
			tuile.getTerritoire().addTuile(tuile);
			poseOk = true;
		}
		
		if(nb==1){
			this.gererUnTerritoire(listparam);
			poseOk = true;
		}
		
		if(nb==2){
			this.gererDeuxTerritoire(listparam);
			poseOk = true;
		}
		
		if(nb>2){
			poseOk = false;
		}
		
		return poseOk;
	}
	
	
	/**
	 * Fonction appeler par Attribuer Territoire si il
	 * n'y a qu'un territoire à attribuer
	 */
	public void gererUnTerritoire(ArrayList<Position> p){
		if ( this.partie.getPlateauJeu().getPlateau()[p.get(0).getX()][p.get(0).getY()] instanceof Chef ){
			Chef chef1 = (Chef) this.partie.getPlateauJeu().getPlateau()[p.get(0).getX()][p.get(0).getY()];
			this.tuile.setTerritoire(chef1.getTerritoire());
			chef1.getTerritoire().addTuile(tuile);
		}
		else{
			TuileCivilisation t1 = (TuileCivilisation) this.partie.getPlateauJeu().getPlateau()[p.get(0).getX()][p.get(0).getY()];
			this.tuile.setTerritoire(t1.getTerritoire());
			t1.getTerritoire().addTuile(tuile);
		}
	}
	
	/**
	 * Fonction appeler par Attribuer Territoire si il
	 * y a deux territoires à attribuer
	 * @param p
	 */
	public void gererDeuxTerritoire(ArrayList<Position> p){
		//TODO completer 
	}

	/**
	 * Execute l'action PlacerTuileCivilisation
	 * @return vrai ou faux, selon le bon déroulement ou non de l'action
	 */
	public boolean executer(){
		boolean ok = false;
		
		ok = this.partie.getPlateauJeu().placerTuile(this.tuile, this.position);

		return ok;
		

		/*f((this.position.getX() > 11 || this.position.getY() > 16) || (this.position.getY() < 0 || this.position.getX() < 0))
		{
			return false;
		}

		if(this.tuile.getType().equals(TypeTuileCivilisation.Ferme))
		{
			if(this.partie.getPlateauJeu().getPlateauTerrain()[this.position.getX()][this.position.getY()] == false
					&& this.partie.getPlateauJeu().getPlateau()[this.getPosition().getX()][this.position.getY()] == null)
			{
				this.partie.getPlateauJeu().getPlateau()[this.position.getX()][this.position.getY()] = this.tuile;
				ok = true;
			} else {
				ok = false;
			}
		} else {
			if(this.partie.getPlateauJeu().getPlateau()[this.getPosition().getX()][this.position.getY()] != null)
			{
				ok = false;
			} else {
				this.partie.getPlateauJeu().getPlateau()[this.position.getX()][this.position.getY()] = this.tuile;
				ok = true;
			}
		}
		return ok;*/
	}
}