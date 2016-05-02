package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.DeckPrive;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Plateau;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Position;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TypeTuileCivilisation;

public class TestPlacerTuileCivilisation {

	private Partie partie;
	private Joueur joueur = new Joueur();

	@Before
	public void initialize() throws RemoteException
	{
		partie = new Partie();
		partie.setPlateauJeu(new Plateau());

		partie.setJoueur(joueur);
		DeckPrive deck = new DeckPrive();
		TuileCivilisation tuile1 = new TuileCivilisation(TypeTuileCivilisation.Marché);
		TuileCivilisation tuile2 = new TuileCivilisation(TypeTuileCivilisation.Marché);
		TuileCivilisation tuile3 = new TuileCivilisation(TypeTuileCivilisation.Marché);
		TuileCivilisation tuile4 = new TuileCivilisation(TypeTuileCivilisation.Marché);
		deck.ajouter(tuile1);
		deck.ajouter(tuile2);
		deck.ajouter(tuile3);
		deck.ajouter(tuile4);

		joueur.setDeckPrive(deck);
	}

	@Test
	public void testPlacerTuileNormal() {
		TuileCivilisation tuile = new TuileCivilisation(TypeTuileCivilisation.Marché);
		Action action = new PlacerTuileCivilisation(partie, partie.getJoueur(), new Position(3,4), tuile);
		assertTrue(action.executer());
		assertSame(this.partie.getPlateauJeu().getPlateau()[3][4], tuile);
	}

	@Test
	public void testPlacerTuileFerme() {
		TuileCivilisation tuile = new TuileCivilisation(TypeTuileCivilisation.Ferme);
		Action action = new PlacerTuileCivilisation(partie, partie.getJoueur(), new Position(3,1), tuile);
		assertTrue(action.executer());
		assertSame(this.partie.getPlateauJeu().getPlateau()[3][1], tuile);
	}

	@Test
	public void testPlacerTuileFermeNonValide() {
		TuileCivilisation tuile = new TuileCivilisation(TypeTuileCivilisation.Ferme);
		Action action = new PlacerTuileCivilisation(partie, partie.getJoueur(), new Position(0,0), tuile);
		assertFalse(action.executer());
	}

	@Test
	public void testPlacerTuileHorsPlateau() {
		TuileCivilisation tuile = new TuileCivilisation(TypeTuileCivilisation.Marché);
		Action action = new PlacerTuileCivilisation(partie, partie.getJoueur(), new Position(18,27), tuile);
		assertFalse(action.executer());
	}

	@Test
	public void testPlacerTuileCaseNonVide() {
		TuileCivilisation tuile = new TuileCivilisation(TypeTuileCivilisation.Marché);
		Action action = new PlacerTuileCivilisation(partie, partie.getJoueur(), new Position(1,3), tuile);
		action.executer();
		action = new PlacerTuileCivilisation(partie, partie.getJoueur(), new Position(1,3), tuile);
		assertFalse(action.executer());
	}

	@Test
	public void testTerritoirePlacerTuile() {
		TuileCivilisation tuile = new TuileCivilisation(TypeTuileCivilisation.Marché);
		Action action = new PlacerTuileCivilisation(partie, partie.getJoueur(), new Position(0,1), joueur.getDeckPrive().getDeckPrive().get(1));
		action.executer();
		TuileCivilisation tuileTemple = (TuileCivilisation) this.partie.getPlateauJeu().getPlateau()[1][1];
		assertEquals(partie.getPlateauJeu().recupererTerritoireTuile(joueur.getDeckPrive().getDeckPrive().get(1)), partie.getPlateauJeu().recupererTerritoireTuile(tuile));
	}


	@Test
	public void testTerritoireConflit() {
		TuileCivilisation tuileT = new TuileCivilisation(TypeTuileCivilisation.Marché);
		Action actionT = new PlacerTuileCivilisation(partie, partie.getJoueur(), new Position(0,0), tuileT);
		actionT.executer();
		TuileCivilisation tuile = new TuileCivilisation(TypeTuileCivilisation.Marché);
		PlacerTuileCivilisation action = new PlacerTuileCivilisation(partie, partie.getJoueur(), new Position(0,1), tuile);
		boolean ok = action.executer();
		assertTrue(ok);
		assertTrue(action.isConflit());

	}

}
