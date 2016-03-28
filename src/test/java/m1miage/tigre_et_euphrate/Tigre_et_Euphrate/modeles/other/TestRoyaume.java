package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.other;

import java.util.ArrayList;
import static org.junit.Assert.*;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Royaume;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.TypeChef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TypeTuileCivilisation;

import org.junit.Before;
import org.junit.Test;

public class TestRoyaume {

	private Royaume royaume1;

	@Before
	public void initialiser() throws Exception {

		//Instance des tuiles civilisation
		TuileCivilisation tuile1 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		TuileCivilisation tuile2 = new TuileCivilisation(TypeTuileCivilisation.Population);
		TuileCivilisation tuile3 = new TuileCivilisation(TypeTuileCivilisation.Population);
		TuileCivilisation tuile4 = new TuileCivilisation(TypeTuileCivilisation.Marché);
		TuileCivilisation tuile5 = new TuileCivilisation(TypeTuileCivilisation.Ferme);
		TuileCivilisation tuile6 = new TuileCivilisation(TypeTuileCivilisation.Ferme);

		//instance du chef
		Chef chef1 = new Chef(TypeChef.Fermier, new Joueur());

		//création des listes
		ArrayList<TuileCivilisation> listeTuiles = new ArrayList<TuileCivilisation>();
		ArrayList<Chef> listeChef = new ArrayList<Chef>();

		listeTuiles.add(tuile1);
		listeTuiles.add(tuile2);
		listeTuiles.add(tuile3);
		listeTuiles.add(tuile4);
		listeTuiles.add(tuile5);
		listeTuiles.add(tuile6);
		listeChef.add(chef1);

		//Creation du royaume
		this.royaume1 = new Royaume(listeTuiles, listeChef);
	}


	/**
	 * test methode addTuile
	 */
	@Test
	public void testAddTuile() {
		TuileCivilisation tuile7 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		royaume1.addTuile(tuile7);
		
		assertEquals(royaume1.getTuilesCivilisation().size(), 7);
		// 7tuiles + 1chef = id num 8
		assertEquals(royaume1.getTuilesCivilisation().get(royaume1.getTuilesCivilisation().size()-1).getId(),8);
		assertTrue(royaume1.getTuilesCivilisation().get(royaume1.getTuilesCivilisation().size()-1)==tuile7);
	}
	
	/**
	 * test de la methode addListeTuiles
	 */
	@Test
	public void testAddListeTuiles() {
		TuileCivilisation tuile7 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		TuileCivilisation tuile8 = new TuileCivilisation(TypeTuileCivilisation.Marché);
		TuileCivilisation tuile9 = new TuileCivilisation(TypeTuileCivilisation.Population);
		ArrayList<TuileCivilisation> listeInsert = new ArrayList<TuileCivilisation>();	
		listeInsert.add(tuile7);
		listeInsert.add(tuile8);
		listeInsert.add(tuile9);
		
		royaume1.addListeTuiles(listeInsert);
		
		assertEquals(royaume1.getTuilesCivilisation().size(), 9);
		assertTrue(royaume1.getTuilesCivilisation().contains(tuile7));
		assertTrue(royaume1.getTuilesCivilisation().contains(tuile8));
		assertTrue(royaume1.getTuilesCivilisation().get(royaume1.getTuilesCivilisation().size()-1)==tuile9);
		
	}

}
