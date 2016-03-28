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

		//instance du chef
		Chef chef1 = new Chef(TypeChef.Fermier, joueur1);

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

	/**
	 * Test de la methode addChefs
	 */
	@Test
	public void testAddChefs(){
		Chef chef2 = new Chef(TypeChef.Roi,joueur1);
		Chef chef3 = new Chef(TypeChef.Fermier, new Joueur());
		
		/*test si l'insertion d'un chef de type different de celui
		 * déja présent dans le royaume fonctionne.
		 */
		assertTrue(royaume1.addChefs(chef2));
		assertEquals(royaume1.getChefs().size(),2);
		
		/*test si l'insertion d'un chef de type idem de celui
		 * déja présent dans le royaume ne fonctionne pas.
		 */
		assertFalse(royaume1.addChefs(chef3));
		assertEquals(royaume1.getChefs().size(),2);	
	}
	
	/**
	 * Test de la methode addListeChefs
	 */
	@Test
	public void testAddListeChefs(){
		
		Chef chef2 = new Chef(TypeChef.Roi,joueur1);
		Chef chef3 = new Chef(TypeChef.Fermier, joueur1);
		Chef chef4 = new Chef(TypeChef.Marchand, joueur1);
		Chef chef5 = new Chef(TypeChef.Pretre, joueur1);
		Chef chef6 = new Chef(TypeChef.Roi, joueur1);
		
		ArrayList<Chef> listeChefOk = new ArrayList<Chef>();
		ArrayList<Chef> listeChefNonOk = new ArrayList<Chef>();
		
		listeChefOk.add(chef2);
		listeChefOk.add(chef4);
		listeChefOk.add(chef5);
		
		listeChefNonOk.add(chef3);
		listeChefNonOk.add(chef6);
		
		/*test si l'insertion d'une liste de chef de type different de celui
		 * déja présent dans le royaume fonctionne.
		 */
		assertTrue(royaume1.addListeChefs(listeChefOk));
		assertEquals(royaume1.getChefs().size(),4);
		
		/*test si l'insertion d'une liste de chef avec un
		 * chef de type idem de celui
		 * déja présent dans le royaume ne fonctionne pas.
		 */
		assertFalse(royaume1.addListeChefs(listeChefNonOk));
		assertEquals(royaume1.getChefs().size(),4);	
		
	}
	
	/**
	 * test de la methode deletTuilesCivilisation
	 */
	@Test
	public void testDeletTuilesCivilisation(){
		
		int taille = royaume1.getTuilesCivilisation().size();
		TuileCivilisation t1 = royaume1.getTuilesCivilisation().get(1);
		royaume1.deletTuilesCivilisation(royaume1.getTuilesCivilisation().get(1));
		TuileCivilisation t2 = royaume1.getTuilesCivilisation().get(1);
		
		assertFalse(t1.equals(t2));
		assertFalse(royaume1.getTuilesCivilisation().contains(t1));
		assertEquals(royaume1.getTuilesCivilisation().size(), taille-1);
	}
	
	/**
	 * test de la methode DeletChef
	 */
	@Test
	public void testDeletChef(){
		
		Chef chef2 = new Chef(TypeChef.Roi,joueur1);
		royaume1.addChefs(chef2);
		
		int taille = royaume1.getChefs().size();
		Chef c1 = royaume1.getChefs().get(0);
		royaume1.deletChef(royaume1.getChefs().get(0));
		Chef c2 = royaume1.getChefs().get(0);
		
		assertFalse(c1.equals(c2));
		assertFalse(royaume1.getChefs().contains(c1));
		assertEquals(royaume1.getChefs().size(), taille-1);
		
	}

}
