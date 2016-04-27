package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Pioche;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Plateau;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Position;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCatastrophe;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TypeTuileCivilisation;

/**
 * test pour placer une tuile
 * @author 
 *
 */
public class TestPlacerCatastrophe {
	
	private Partie partie;
	private Joueur joueur;
	private TuileCatastrophe tuilecatastrophe;
	private Plateau plateau;
	
	

	@Before
	public void initialise(){
		joueur = new Joueur();
		
		tuilecatastrophe= new TuileCatastrophe();
		plateau = new Plateau();
		
	}
	
	/**
	 * test de la vérification
	 */
	@Test
	public void testVerifierCase() {
		Position pos2 = new Position(0,0); //Terre avec Tuile
		Position pos3 = new Position(2,4); //Eau vide
		Position pos4 = new Position(0,1); //Terre avec Chef
		
		
		plateau.placerTuile(new TuileCivilisation(TypeTuileCivilisation.Temple), pos2);
		//plateau.placerChef(new Chef(TypeChef.Roi,joueur), pos4);
		
		partie = new Partie(plateau, null, new Pioche());
		PlacerTuileCatastrophe ptc = new PlacerTuileCatastrophe(partie,joueur,tuilecatastrophe,pos2);
		PlacerTuileCatastrophe ptc2 = new PlacerTuileCatastrophe(partie,joueur,tuilecatastrophe,pos3);
		PlacerTuileCatastrophe ptc3 = new PlacerTuileCatastrophe(partie,joueur,tuilecatastrophe,pos4);
		assertTrue(ptc.VerifierCase());
		assertTrue(ptc2.VerifierCase());
		//assertFalse(ptc3.VerifierCase());
		
	}
	
	/**
	 * test de l'execution
	 */
	@Test
	public void TestExecute(){
		Position pos2 = new Position(0,0); //Terre avec Tuile
		Position pos3 = new Position(2,4); //Eau vide
		Position pos4 = new Position(0,1); //Terre avec Chef
		
		
		plateau.placerTuile(new TuileCivilisation(TypeTuileCivilisation.Temple), pos2);
		//plateau.placerChef(new Chef(TypeChef.Roi,joueur), pos4);
		
		partie = new Partie(plateau, null, new Pioche());
		PlacerTuileCatastrophe ptc = new PlacerTuileCatastrophe(partie,joueur,tuilecatastrophe,pos2);
		PlacerTuileCatastrophe ptc2 = new PlacerTuileCatastrophe(partie,joueur,tuilecatastrophe,pos3);
		//PlacerTuileCatastrophe ptc3 = new PlacerTuileCatastrophe(partie,joueur,tuilecatastrophe,pos4);
		assertTrue(ptc.executer());
		assertTrue(ptc2.executer());
		assertEquals(this.partie.getPlateauJeu().getPlacableAt(pos2).getId(), tuilecatastrophe.getId());
		assertEquals(this.partie.getPlateauJeu().getPlacableAt(pos3).getId(), tuilecatastrophe.getId());
		//assertFalse(ptc3.VerifierCase());
		
	}

}
