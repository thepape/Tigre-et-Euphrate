package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles;

import java.util.ArrayList;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCatastrophe;

public class DeckPublic extends Deck{

	/**
	 * Liste des chefs et des tuiles catastrophes contenu dans le deck : Deck Public
	 */
	private ArrayList<Placable> deckPublic;
	
	/**
	 * 
	 */
	public DeckPublic() {
		super();
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
	 * Méthode pour ajouter un chef dans le deckPublic
	 * @param pChef le chef à ajouter
	 */
	public void ajouterChef(Chef pChef){
		this.deckPublic.add(pChef);
	}
	
	/**
	 * Méthode pour supprimer un chef du deck public
	 * @param pChef le chef à supprimer
	 * @return vrai ou faux si ça c'esr bien passé ou non
	 */
	public boolean supprimerChef(Chef pChef){
		return this.deckPublic.remove(pChef);
	}
	
	/**
	 * Méthode pour ajouter une tuile catastrophe au deck public
	 */
	public void ajouterTuileCatastrophe(TuileCatastrophe pTuile){
		this.deckPublic.add(pTuile);
	}
	
	/**
	 * Méthode pour supprimer une tuile catastrophe du deck public
	 * @param pTuile la tuile catastrophe à supprimer
	 * @return vrai ou faux si ça s'est bien passé ou non
	 */
	public boolean supprimerTuileCatastrophe(TuileCatastrophe pTuile){
		return this.deckPublic.remove(pTuile);
	}


}
