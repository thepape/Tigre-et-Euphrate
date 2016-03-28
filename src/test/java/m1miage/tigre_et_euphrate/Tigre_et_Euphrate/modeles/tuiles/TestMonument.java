package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestMonument {

	@Test
	public void testConstructionOK() {
		Monument m = new Monument("rouge","bleu");
		
		TuileCivilisation tno = new TuileCivilisation(TypeTuileCivilisation.Temple);
		TuileCivilisation tne = new TuileCivilisation(TypeTuileCivilisation.Temple);
		TuileCivilisation tso = new TuileCivilisation(TypeTuileCivilisation.Temple);
		TuileCivilisation tse = new TuileCivilisation(TypeTuileCivilisation.Temple);
		
		tno.placer(1, 1);
		tne.placer(1, 2);
		tso.placer(2, 1);
		tse.placer(2, 2);
		
		boolean ok = m.construire(tno, tne, tso, tse);
		
		assertTrue(ok);
		assertSame(tne.getMonument(), m);
	}
	
	@Test
	public void testConstructionFailCouleursCases() {
		Monument m = new Monument("rouge","bleu");
		
		TuileCivilisation tno = new TuileCivilisation(TypeTuileCivilisation.Temple);
		TuileCivilisation tne = new TuileCivilisation(TypeTuileCivilisation.Population);
		TuileCivilisation tso = new TuileCivilisation(TypeTuileCivilisation.Temple);
		TuileCivilisation tse = new TuileCivilisation(TypeTuileCivilisation.Temple);
		
		tno.placer(1, 1);
		tne.placer(1, 2);
		tso.placer(2, 1);
		tse.placer(2, 2);
		
		boolean ok = m.construire(tno, tne, tso, tse);
		
		assertTrue(!ok);
	}

	@Test
	public void testConstructionFailCouleurMonument() {
		Monument m = new Monument("vert","bleu");
		
		TuileCivilisation tno = new TuileCivilisation(TypeTuileCivilisation.Temple);
		TuileCivilisation tne = new TuileCivilisation(TypeTuileCivilisation.Temple);
		TuileCivilisation tso = new TuileCivilisation(TypeTuileCivilisation.Temple);
		TuileCivilisation tse = new TuileCivilisation(TypeTuileCivilisation.Temple);
		
		tno.placer(1, 1);
		tne.placer(1, 2);
		tso.placer(2, 1);
		tse.placer(2, 2);
		
		boolean ok = m.construire(tno, tne, tso, tse);
		
		assertTrue(!ok);
	}
}
