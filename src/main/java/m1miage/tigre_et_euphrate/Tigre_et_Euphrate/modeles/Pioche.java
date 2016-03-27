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
		int total = Pioche.nbTuilesFerme+Pioche.nbTuilesMarche+Pioche.nbTuilesPopulation+Pioche.nbTuilesTemple;
		Random random = new Random();
		int val = random.nextInt(total)+1;
		TuileCivilisation tuilePiochee;
		if(val <= Pioche.nbTuilesFerme){	
			tuilePiochee = new TuileCivilisation(new TypeTuileCivilisation("bleu","Ferme"));
			Pioche.nbTuilesFerme--;
		}else{
			if(val > Pioche.nbTuilesFerme && val <= (Pioche.nbTuilesFerme+Pioche.nbTuilesMarche)){
				tuilePiochee = new TuileCivilisation(new TypeTuileCivilisation("vert","Marché"));
				Pioche.nbTuilesMarche--;
			}else{
				if(val > (Pioche.nbTuilesFerme+Pioche.nbTuilesMarche) && val <= (Pioche.nbTuilesFerme+Pioche.nbTuilesMarche+Pioche.nbTuilesPopulation)){
					tuilePiochee = new TuileCivilisation(new TypeTuileCivilisation("jaune","Population"));
					Pioche.nbTuilesPopulation--;
				}else{
					tuilePiochee = new TuileCivilisation(new TypeTuileCivilisation("rouge","Temple"));
					Pioche.nbTuilesTemple--;
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
		return (Pioche.nbTuilesFerme == 0 && Pioche.nbTuilesMarche == 0 && Pioche.nbTuilesPopulation == 0 && Pioche.nbTuilesTemple == 0);	
	}

}
