package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles;

import java.util.ArrayList;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;

public class DeckPrive extends Deck{
	
	/**
	 * Liste des tuiles civilisation : Deck Privé
	 */
	private ArrayList<TuileCivilisation> deckPrive;
	
	/**
	 * 
	 */
	public DeckPrive() {
		super();
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
	 * Méthode pour ajouter une tuile civilisation dans le deck prive
	 * @param pTuile la tuile à ajouter
	 * @return vrai ou faux
	 */
	public boolean ajouterTuileCivilisation(TuileCivilisation pTuile){
		return this.deckPrive.add(pTuile);
	}
	
	/**
	 * Méthode pour supprimer une tuile civilisation du deck prive
	 * @param pTuile la tuile à supprimer
	 * @return vrai ou faux
	 */
	public boolean supprimerTuileCivilisation(TuileCivilisation pTuile){
		return this.deckPrive.remove(pTuile);
	}


}
