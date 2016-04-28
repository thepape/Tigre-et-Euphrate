package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action;

import java.util.ArrayList;
import java.util.ListIterator;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Placable;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Plateau;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Position;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Territoire;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TypeTuileCivilisation;

/**
 *
 * Classe hérite de la classe Action et permet de placer un chef
 *
 */
public class PlacerChef extends Action {

	/**
	 * Chef
	 */
	private Chef chef;

	/**
	 * Position souhaitee
	 */
	private Position position;

	/**
	 * Booleen qui permet de tester les conflit
	 */
	private boolean conflit = false;
	public boolean isConflit() {
		return conflit;
	}

	public void setConflit(boolean conflit) {
		this.conflit = conflit;
	}

	/**
	 * @param ppartie
	 * @param pjoueur
	 * @param pchef
	 * @param ppos
	 */
	public PlacerChef(Partie ppartie, Joueur pjoueur, Chef pchef, Position ppos) {
		super(ppartie, pjoueur);
		this.chef = pchef;
		this.position = ppos;
	}

	private void retirerChef(){
		for(int x = 0; x < 16; x++){
			for(int y = 0; y < 11;y++){
				Placable placable = this.partie.getPlateauJeu().getPlateau()[y][x];

				if(placable != null && placable instanceof Chef){
					Chef chefc = (Chef) placable;
					boolean memeDynastie = chefc.getDynastie().getNom().equals(this.chef.getDynastie().getNom());
					boolean memeCouleur = chefc.getTypeChef().getCouleur().equals(this.chef.getTypeChef().getCouleur());

					if(memeDynastie && memeCouleur){
						this.partie.getPlateauJeu().getPlateau()[y][x] = null;
						Territoire t = this.chef.getTerritoire();
						t.deletChef(chef);
						return;
					}
				}
			}
		}
	}

	/**
	 * Execute l'action PlacerChef
	 * On place le chef dans la case du tableau
	 * @return vrai ou faux, selon le bon déroulement ou non de l'action
	 */
	public boolean executer(){
		boolean ok = false;

		ok =  this.partie.getPlateauJeu().placerChef(this.chef, this.position);
		ArrayList<TuileCivilisation> listeAdjacente = this.partie.getPlateauJeu().recupererListeTuileCivilisationAdjacente(position);

		if(ok) {
			for(int i = 0; i < listeAdjacente.size() - 1; i++)
			{
				for(int j = 1 ; j < listeAdjacente.size(); j++)
				{
					if(!listeAdjacente.get(i).getTerritoire().equals(listeAdjacente.get(j).getTerritoire()))
					{
						ok = false;
					}
				}
			}

			if(ok)
			{
				this.retirerChef();
				this.chef.setTerritoire(listeAdjacente.get(0).getTerritoire());
				listeAdjacente.get(0).getTerritoire().addChefs(this.chef);
				this.partie.getPlateauJeu().getPlateau()[this.position.getX()][this.position.getY()] = this.chef;
				this.joueur.getDeckPublic().getDeckPublic().remove(this.chef);
				for(int i = 0; i < this.chef.getTerritoire().getChefs().size() - 1;i++)
				{
					for(int j = i; j < this.chef.getTerritoire().getChefs().size();j++)
					{
						if(this.chef.getTerritoire().getChefs().get(i).getTypeChef().equals(this.chef.getTerritoire().getChefs().get(j).getTypeChef()))
						{
							//TODO conflit
							conflit = true;
						}
					}
				}
			}
		}

		return ok;
	}
	
	public String toString(){
		return this.joueur.getNom()+" a placé son chef "+this.chef.getTypeChef().getNom()+" à la ligne "+this.position.getX()+", colonne "+this.position.getY();
	}

}