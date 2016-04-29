package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action;

import java.util.ArrayList;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Position;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Territoire;
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
	 * boolean pour tester les conflits
	 */
	private boolean conflit;

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
	 * Execute l'action PlacerTuileCivilisation
	 * @return vrai ou faux, selon le bon déroulement ou non de l'action
	 */
	public boolean executer(){
		boolean ok = false;
		
		if(!this.verifier()){
			return false;
		}

		ArrayList<TuileCivilisation> listeAdjacente = this.partie.getPlateauJeu().recupererListeTuileCivilisationAdjacente(position);
		conflit = false;
		if(listeAdjacente.size() > 0)
		{

			for(int i = 0; i < listeAdjacente.size()-1; i++)
			{
				for(int j = 1; j < listeAdjacente.size(); j++)
				{
					/*if(!listeAdjacente.get(i).getTerritoire().equals(listeAdjacente.get(j).getTerritoire()))
					{
						//TODO conflits
						conflit = true;
					}*/

					if(!this.partie.getPlateauJeu().recupererTerritoireTuile(listeAdjacente.get(i)).equals(this.partie.getPlateauJeu().recupererTerritoireTuile(listeAdjacente.get(j))))
					{
						conflit = true;
						System.out.println(conflit);
					}
				}
			}

			if(!conflit)
			{
				this.partie.getPlateauJeu().recupererTerritoireTuile(listeAdjacente.get(0)).addTuile(tuile);
			}
		}  else {
			//tuile.setTerritoire(new Territoire());
			Territoire territoire = new Territoire();
			territoire.addTuile(tuile);
			this.partie.getPlateauJeu().getListeRoyaume().add(territoire);
		}


		ok = this.partie.getPlateauJeu().placerTuile(this.tuile, this.position);
		
		// on retire cette tuile du deck privé du joueur
		this.joueur.getDeckPrive().getDeckPrive().remove(this.tuile);
		
		return ok;
	}

	public boolean isConflit() {
		return conflit;
	}

	public void setConflit(boolean conflit) {
		this.conflit = conflit;
	}

	public String toString(){
		return this.joueur.getNom()+" a placé une tuile "+this.tuile.getType().getNom()+" à la ligne "+this.position.getX()+", colonne "+this.position.getY();
	}

	@Override
	public boolean verifier() {
		// TODO Auto-generated method stub
		return this.partie.getPlateauJeu().verifierPlacerTuile(tuile, position);
	}
}