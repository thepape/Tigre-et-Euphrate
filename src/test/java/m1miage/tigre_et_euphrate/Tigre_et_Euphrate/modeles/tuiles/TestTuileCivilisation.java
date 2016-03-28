package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles;

import static org.junit.Assert.*;

import org.junit.Test;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Position;

public class TestTuileCivilisation {

	@Test
	public void testInstanciationFerme() {
		//test qu'une ferme est bien tuile eau
		TuileCivilisation ferme = new TuileCivilisation(TypeTuileCivilisation.Ferme);
		assertTrue(ferme.estTuileEau());
	}
	
	@Test
	public void testInstanciationSphynx() {
		
		TuileCivilisation sphynx = new TuileCivilisation(new Tresor());
		assertEquals(sphynx.getType(), TypeTuileCivilisation.Temple);
		assertNotNull(sphynx.recupererTresor());
	}

	@Test
	public void testAdjacence() {
		TuileCivilisation t1 = new TuileCivilisation(TypeTuileCivilisation.Population);
		TuileCivilisation t2 = new TuileCivilisation(TypeTuileCivilisation.Marché);
		TuileCivilisation t3 = new TuileCivilisation(TypeTuileCivilisation.Marché);
		
		t1.placer(new Position(1,1));
		t2.placer(new Position(1,2));
		t3.placer(new Position(2,2));
		
		assertTrue(t1.estAdjacent(t2));
		assertTrue(!t1.estAdjacent(t3));
	}
	
	@Test
	public void testRetrait(){
		TuileCivilisation t1 = new TuileCivilisation(TypeTuileCivilisation.Ferme);
		t1.placer(new Position(1,1));
		
		t1.retirer();
		
		assertTrue(t1.estRetiree());
		assertNull(t1.getPosition());
	}
	
	@Test
	public void testRetraitDefinitif(){
		TuileCivilisation t1 = new TuileCivilisation(TypeTuileCivilisation.Ferme);
		t1.placer(new Position(1,1));
		
		t1.retirer();
		
		t1.placer(new Position(2,2));
		
		assertTrue(t1.estRetiree());
		assertNull(t1.getPosition());
	}
}
