package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles;

import java.io.Serializable;
import java.util.ArrayList;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCatastrophe;

public class DeckPublic extends Deck implements Serializable {

	/**
	 * Liste des chefs contenu dans le deck : Deck Public
	 */
	private ArrayList<Chef> deckPublic;
	
	/**
	 * Liste des chefs et des tuiles catastrophes contenu dans le deck : Deck Public
	 */
	private ArrayList<Placable> listeTuileCatastrophe;

	/**
	 * Constructeur du deck public
	 */
	public DeckPublic() {
		super();
		this.deckPublic = new ArrayList<Chef>();
		this.listeTuileCatastrophe = new ArrayList<Placable>();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param pjoueur le joueur
	 * @param pdeckPublic la liste
	 */
	public DeckPublic(Joueur pjoueur, ArrayList<Chef> plisteChef,  ArrayList<Placable> plisteTuile) {
		super(pjoueur);
		this.deckPublic = plisteChef;
		this.listeTuileCatastrophe = plisteTuile;
	}

//Getter & Settter

	public ArrayList<Chef> getDeckPublic() {
		return deckPublic;
	}

	public void setDeckPublic(ArrayList<Chef> listeChef) {
		this.deckPublic = listeChef;
	}

	public ArrayList<Placable> getListeTuileCatastrophe() {
		return listeTuileCatastrophe;
	}

	public void setListeTuileCatastrophe(ArrayList<Placable> listeTuileCatastrophe) {
		this.listeTuileCatastrophe = listeTuileCatastrophe;
	}

	
	/**
	 * Méthode pour ajouter un chef dans le deckPublic
	 * @param pchef
	 * @return
	 */
	public boolean ajouterChef(Chef pchef){
			return this.deckPublic.add(pchef);
	}
	
	
	/**
	 * Méthode pour ajouter une tuile catastrophe dans le deckPublic
	 * @param pchef
	 * @return
	 */
	public boolean ajouterCatastrophe(Placable pcata){
			return this.listeTuileCatastrophe.add(pcata);
	}

	/**
	 * Méthode pour supprimer un chef du deck public
	 * @param l'objet chef
	 * @return vrai ou faux
	 */
	public boolean supprimerChef(Chef pchef){
			return this.deckPublic.remove(pchef);
	}
	
	/**
	 * Méthode pour supprimer un chef ou une tuile catastrophe du deck public
	 * @param l'objet placable
	 * @return vrai ou faux
	 */
	public boolean supprimerCatastrohe(Placable pcata){
		return this.listeTuileCatastrophe.remove(pcata);
	}


}
