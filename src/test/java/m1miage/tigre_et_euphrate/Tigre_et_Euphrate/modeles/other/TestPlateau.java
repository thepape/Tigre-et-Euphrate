package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.other;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Placable;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Plateau;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Position;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.Tresor;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TypeTuileCivilisation;

public class TestPlateau {


	@Test
	public void testplacerChef(){
		Plateau p = new Plateau();
		Joueur j1 = new Joueur();

		Chef c = new Chef();
		Chef c2 = new Chef();
		Chef c3 = new Chef();
		Chef c4 = new Chef();

		Position p1 = new Position(1, 3); //On le place dans l'eau pas a coté d'un temple
		Position p2 = new Position(4, 2); //On le place dans l'eau a coté d'un temple
		Position p3 = new Position(0, 0); // On le place sur le terrain pas a côté d'un temple
		Position p4 = new Position(0, 1); // On le place sur le terrain a côté d'un temple

		p.placerChef(c,p1);
		p.placerChef(c2, p2);
		p.placerChef(c3, p3);
		p.placerChef(c4, p4);

		Placable[][] terrain = p.getPlateau();

		assertNotEquals(terrain[1][3], c);
		assertNotEquals(terrain[4][2], c2);
		assertNotEquals(terrain[0][0], c3);
		assertEquals(terrain[0][1], c4);
	}

	@Test
	public void testVerifierTemple(){
		Plateau p = new Plateau();

		Position p1 = new Position(0, 0);
		Position p2 = new Position(0, 1);

		boolean sol1 = p.verifierTemple(p1);
		boolean sol2 = p.verifierTemple(p2);

		assertEquals(false, sol1);
		assertEquals(true, sol2);
	}

	@Test
	public void testplacerTuile(){
		Plateau p = new Plateau();

		TuileCivilisation t1 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		TuileCivilisation t2 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		TuileCivilisation t3 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		TuileCivilisation t4 = new TuileCivilisation(TypeTuileCivilisation.Temple);

		TuileCivilisation f1 = new TuileCivilisation(TypeTuileCivilisation.Ferme);
		TuileCivilisation f2 = new TuileCivilisation(TypeTuileCivilisation.Ferme);
		TuileCivilisation f3 = new TuileCivilisation(TypeTuileCivilisation.Ferme);

		Position p1 = new Position(1, 3);//eau
		Position p2 = new Position(0, 0);//terre
		Position p3 = new Position(1, 1);//temple

		boolean ok1 = p.placerTuile(t1, p1); //On place la tuile dans l'eau
		boolean ok2 = p.placerTuile(t2, p2); //On place la tuile sur terre
		boolean ok3 = p.placerTuile(t3, p3); //On place la tuile sur un temple
		boolean ok4 = p.placerTuile(t4, p2); //On place la tuile sur une autre tuile

		boolean ok5 = p.placerTuile(f1, p1); //On place un fermier dans l'eau
		boolean ok6 = p.placerTuile(f2, p2); //On place un fermier sur la terre


		assertEquals(false,ok1);
		assertEquals(true,ok2);
		assertEquals(false,ok3);
		assertEquals(false,ok3);
		assertEquals(true,ok5);
		assertEquals(false,ok6);

	}

	@Test
	public void testRecupererAdjacent()
	{
		Plateau p = new Plateau();

		Position position = new Position(1,2);

		ArrayList<TuileCivilisation> liste = p.recupererListeTuileCivilisationAdjacente(position);
		liste = p.recupererListeTuileCivilisationAdjacente(position);

		assertSame(liste.get(liste.size()-1).getType(), TypeTuileCivilisation.Temple);

	}

}
