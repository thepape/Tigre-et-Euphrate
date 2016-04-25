package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Plateau;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Position;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TypeTuileCivilisation;

public class TestPlacerTuileCivilisation {

	private Partie partie;

	@Before
	public void initialize() throws RemoteException
	{
		partie = new Partie();
		partie.setPlateauJeu(new Plateau());
		Joueur joueur = new Joueur();
		partie.setJoueur(joueur);
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
		Action action = new PlacerTuileCivilisation(partie, partie.getJoueur(), new Position(1,3), tuile);
		assertTrue(action.executer());
		assertSame(this.partie.getPlateauJeu().getPlateau()[1][3], tuile);
	}

	@Test
	public void testPlacerTuileFermeNonValide() {
		TuileCivilisation tuile = new TuileCivilisation(TypeTuileCivilisation.Ferme);
		Action action = new PlacerTuileCivilisation(partie, partie.getJoueur(), new Position(0,5), tuile);
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

}