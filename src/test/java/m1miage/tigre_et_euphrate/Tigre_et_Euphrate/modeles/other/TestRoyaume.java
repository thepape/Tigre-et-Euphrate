package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.other;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Territoire;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.TypeChef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TypeTuileCivilisation;

import org.junit.Before;
import org.junit.Test;

public class TestRoyaume {

	private Territoire territoire1;
	private Joueur joueur1;

	@Before
	public void initialiser() throws Exception {

		//Initiation joueur
		this.joueur1= new Joueur();

		//Instance des tuiles civilisation
		TuileCivilisation tuile1 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		TuileCivilisation tuile2 = new TuileCivilisation(TypeTuileCivilisation.Population);
		TuileCivilisation tuile3 = new TuileCivilisation(TypeTuileCivilisation.Population);
		TuileCivilisation tuile4 = new TuileCivilisation(TypeTuileCivilisation.Marché);
		TuileCivilisation tuile5 = new TuileCivilisation(TypeTuileCivilisation.Ferme);
		TuileCivilisation tuile6 = new TuileCivilisation(TypeTuileCivilisation.Ferme);


		//création des listes
		ArrayList<TuileCivilisation> listeTuiles = new ArrayList<TuileCivilisation>();
		ArrayList<Chef> listeChef = new ArrayList<Chef>();

		listeTuiles.add(tuile2);
		listeTuiles.add(tuile3);
		listeTuiles.add(tuile4);
		listeTuiles.add(tuile5);
		listeTuiles.add(tuile6);

		//Creation du territoire
		this.territoire1 = new Territoire(tuile1);
		territoire1.addListeTuiles(listeTuiles);

	}


	/**
	 * test methode addTuile
	 */
	@Test
	public void testAddTuile() {
		TuileCivilisation tuile7 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		territoire1.addTuile(tuile7);

		assertEquals(territoire1.getTuilesCivilisation().size(), 7);
		assertTrue(territoire1.getTuilesCivilisation().get(territoire1.getTuilesCivilisation().size()-1)==tuile7);
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

		territoire1.addListeTuiles(listeInsert);

		assertEquals(territoire1.getTuilesCivilisation().size(), 9);
		assertTrue(territoire1.getTuilesCivilisation().contains(tuile7));
		assertTrue(territoire1.getTuilesCivilisation().contains(tuile8));
		assertTrue(territoire1.getTuilesCivilisation().get(territoire1.getTuilesCivilisation().size()-1)==tuile9);

	}

	/**
	 * Test de la methode addChefs
	 */
	@Test
	public void testAddChefs(){
		Chef chef2 = new Chef(TypeChef.Roi,joueur1);
		territoire1.addChefs(chef2);
		
		assertEquals(territoire1.getChefs().size(),1);
		assertTrue(territoire1.getChefs().get(territoire1.getChefs().size()-1)==chef2);
		
	}
	
	/**
	 * Test de la methode addListeChefs
	 */
	@Test
	public void testAddListeChefs(){
		
		Chef chef1 = new Chef(TypeChef.Roi,joueur1);
		Chef chef2 = new Chef(TypeChef.Marchand, joueur1);
		Chef chef3 = new Chef(TypeChef.Pretre, joueur1);
		
		ArrayList<Chef> listeChefOk = new ArrayList<Chef>();
		
		listeChefOk.add(chef1);
		listeChefOk.add(chef2);
		listeChefOk.add(chef3);
		
		territoire1.addListeChefs(listeChefOk);
		
		assertEquals(territoire1.getChefs().size(),3);
		assertTrue(territoire1.getChefs().get(territoire1.getChefs().size()-1)==chef3);
		
	}
	
	/**
	 * test de la methode deletTuilesCivilisation
	 */
	@Test
	public void testDeletTuilesCivilisation(){
		
		int taille = territoire1.getTuilesCivilisation().size();
		TuileCivilisation t1 = territoire1.getTuilesCivilisation().get(1);
		territoire1.deletTuilesCivilisation(territoire1.getTuilesCivilisation().get(1));
		TuileCivilisation t2 = territoire1.getTuilesCivilisation().get(1);
		
		assertFalse(t1.equals(t2));
		assertFalse(territoire1.getTuilesCivilisation().contains(t1));
		assertEquals(territoire1.getTuilesCivilisation().size(), taille-1);
	}
	
	/**
	 * test de la methode DeletChef
	 */
	@Test
	public void testDeletChef(){
		
		Chef chef1 = new Chef(TypeChef.Marchand, joueur1);
		Chef chef2 = new Chef(TypeChef.Roi,joueur1);
		territoire1.addChefs(chef1);
		territoire1.addChefs(chef2);
		
		int taille = territoire1.getChefs().size();
		Chef c1 = territoire1.getChefs().get(0);
		territoire1.deletChef(territoire1.getChefs().get(0));
		Chef c2 = territoire1.getChefs().get(0);
		
		assertFalse(c1.equals(c2));
		assertFalse(territoire1.getChefs().contains(c1));
		assertEquals(territoire1.getChefs().size(), taille-1);
		
	}

}
