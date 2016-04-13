package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles;

import java.io.Serializable;
import java.util.ArrayList;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCatastrophe;

public class DeckPublic extends Deck implements Serializable {

	/**
	 * Liste des chefs et des tuiles catastrophes contenu dans le deck : Deck Public
	 */
	private ArrayList<Placable> deckPublic;

	/**
	 *
	 */
	public DeckPublic() {
		super();
		this.deckPublic = new ArrayList<Placable>();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param pjoueur le joueur
	 * @param pdeckPublic la liste
	 */
	public DeckPublic(Joueur pjoueur, ArrayList<Placable> pdeckPublic) {
		super(pjoueur);
		this.deckPublic = pdeckPublic;
	}

	/**
	 * getter du deck public
	 * @return deckPublic
	 */
	public ArrayList<Placable> getDeckPublic() {
		return deckPublic;
	}

	/**
	 * setter du deck public
	 * @param deckPublic
	 */
	public void setDeckPublic(ArrayList<Placable> pDeckPublic) {
		this.deckPublic = pDeckPublic;
	}

	/**
	 * Méthode pour ajouter un chef ou une tuile catastrophe dans le deckPublic
	 * @param l'objet placable
	 * @return vrai ou faux
	 */
	public boolean ajouter(Placable pobj){
		if(pobj instanceof Chef)
			return this.deckPublic.add((Chef)pobj);
		if(pobj instanceof TuileCatastrophe)
			return this.deckPublic.add((TuileCatastrophe)pobj);
		return false;
	}

	/**
	 * Méthode pour supprimer un chef ou une tuile catastrophe du deck public
	 * @param l'objet placable
	 * @return vrai ou faux
	 */
	public boolean supprimer(Placable pobj){
		if(pobj instanceof Chef)
			return this.deckPublic.remove((Chef)pobj);
		if(pobj instanceof TuileCatastrophe)
			return this.deckPublic.remove((Chef)pobj);
		return false;
	}

	/*public static void main(String[] args) {
		DeckPublic deck = new DeckPublic();
		System.out.println(deck.ajouter(new TuileCatastrophe()));
	}*/


}
