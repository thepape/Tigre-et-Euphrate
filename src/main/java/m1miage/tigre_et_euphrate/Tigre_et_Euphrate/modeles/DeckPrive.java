package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles;

import java.io.Serializable;
import java.util.ArrayList;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;

public class DeckPrive extends Deck implements Serializable{

	/**
	 * Liste des tuiles civilisation : Deck Privé
	 */
	private ArrayList<TuileCivilisation> deckPrive;

	/**
	 *
	 */
	public DeckPrive() {
		super();
		this.deckPrive = new ArrayList<TuileCivilisation>();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param pjoueur
	 * @param pdeckPrive
	 */
	public DeckPrive(Joueur pjoueur, ArrayList<TuileCivilisation> pdeckPrive) {
		super(pjoueur);
		this.deckPrive = pdeckPrive;
	}

	/**
	 * getter du deckPrive
	 * @return deckPrive
	 */
	public ArrayList<TuileCivilisation> getDeckPrive() {
		return deckPrive;
	}

	/**
	 * setter du deck privé
	 * @param pDeckPrive
	 */
	public void setDeckPrive(ArrayList<TuileCivilisation> pDeckPrive) {
		this.deckPrive = pDeckPrive;
	}

	/**
	 * Méthode pour ajouter une tuile civilisation dans le deck prive
	 * @param pTuile l'objet Placable
	 * @return vrai ou faux
	 */
	public boolean ajouter(Placable pTuile){
		return this.deckPrive.add((TuileCivilisation)pTuile);
	}

	/**
	 * Méthode pour supprimer une tuile civilisation du deck prive
	 * @param pTuile l'objet placable
	 * @return vrai ou faux
	 */
	public boolean supprimer(Placable pTuile){
		return this.deckPrive.remove((TuileCivilisation)pTuile);
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer("[");
		for(TuileCivilisation tuile : this.deckPrive){
			sb.append(tuile.getType().getNom()+":"+ tuile.getId()+",");
		}
		sb.append("]");
		
		return sb.toString();
	}
}
