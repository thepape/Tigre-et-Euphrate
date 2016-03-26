package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles;

import java.util.Random;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TypeTuileCivilisation;

/**
 * Classe representant la pioche
 *
 */
public class Pioche {
	
	/**
	 *  Decrement : nombre de tuiles population restantes
	 */  
	private static int nbTuilesPopulation = 30;
	
	/**
	 *  Decrement : nombre de tuiles temple restantes
	 */
	private static int nbTuilesTemple = 57;
	
	/**
	 *  Decrement : nombre de tuiles ferme restantes
	 */
	private static int nbTuilesFerme = 36;
	
	/**
	 *  Decrement : nombre de tuiles marche restantes
	 */
	private static int nbTuilesMarche = 30;

	/**
	 * Contructeur vide de la Pioche
	 */
	public Pioche() {
		
	}
	
	/**
	 * Fonction permettant de piocher une tuile civilisation aléatoirement dans la pioche
	 * A REFAIRE pcq ca marche mais c est un peu a vomir
	 * @return TuileCivilisation ou null si la pioche est vide
	 */
	public TuileCivilisation piocherTuile(){
		// RANDOM A FAIRE
		if(this.estVide())
			return null;
		int total = this.nbTuilesFerme+this.nbTuilesMarche+this.nbTuilesPopulation+this.nbTuilesTemple;
		Random random = new Random();
		int val = random.nextInt(total)+1;
		TuileCivilisation tuilePiochee;
		if(val <= this.nbTuilesFerme){	
			tuilePiochee = new TuileCivilisation(new TypeTuileCivilisation("bleu","Ferme"));
			this.nbTuilesFerme--;
		}else{
			if(val > this.nbTuilesFerme && val <= (this.nbTuilesFerme+this.nbTuilesMarche)){
				tuilePiochee = new TuileCivilisation(new TypeTuileCivilisation("vert","Marché"));
				this.nbTuilesMarche--;
			}else{
				if(val > (this.nbTuilesFerme+this.nbTuilesMarche) && val <= (this.nbTuilesFerme+this.nbTuilesMarche+this.nbTuilesPopulation)){
					tuilePiochee = new TuileCivilisation(new TypeTuileCivilisation("jaune","Population"));
					this.nbTuilesPopulation--;
				}else{
					tuilePiochee = new TuileCivilisation(new TypeTuileCivilisation("rouge","Temple"));
					this.nbTuilesTemple--;
				}
			}
		}
		return tuilePiochee;
	}
	
	/**
	 * Fonction permettant de savoir si la pioche est vide ou non
	 * @return vrai ou faux
	 */
	public boolean estVide(){
		return (this.nbTuilesFerme == 0 && this.nbTuilesMarche == 0 && this.nbTuilesPopulation == 0 && this.nbTuilesTemple == 0);	
	}

}
