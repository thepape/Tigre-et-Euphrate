package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.DeckPrive;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.DeckPublic;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Plateau;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Position;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Territoire;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Dynastie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.TypeChef;
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

		DeckPrive deck = new DeckPrive();
		TuileCivilisation tuile1 = new TuileCivilisation(TypeTuileCivilisation.Marché);
		deck.ajouter(tuile1);

		joueur.setDynastie(Dynastie.Lanister);
		DeckPublic deckp = new DeckPublic();
		Chef chef1 = new Chef(TypeChef.Marchand,joueur);
		chef1.setDynastie(Dynastie.Lanister);
		deckp.ajouterChef(chef1);
		Chef chef2 = new Chef(TypeChef.Marchand, joueur);
		chef2.setDynastie(Dynastie.Stark);
		deckp.ajouterChef(chef2);
		joueur.setDeckPrive(deck);
		joueur.setDeckPublic(deckp);
	}
 
	@Test
	public void testPlacerTuileNormal() {
		TuileCivilisation tuile = this.partie.getJoueur().getDeckPrive().getDeckPrive().get(0);

		Action action = new PlacerTuileCivilisation(partie, partie.getJoueur(), new Position(3,4), this.partie.getJoueur().getDeckPrive().getDeckPrive().get(0));
		assertTrue(action.executer());
		assertSame(this.partie.getPlateauJeu().getPlateau()[3][4], tuile);
		
	}

	@Test
	public void testPlacerTuileFerme() {
		TuileCivilisation tuile = new TuileCivilisation(TypeTuileCivilisation.Ferme);
		this.partie.getJoueur().getDeckPrive().getDeckPrive().add(tuile);
		Action action = new PlacerTuileCivilisation(partie, partie.getJoueur(), new Position(3,1), this.partie.getJoueur().getDeckPrive().getDeckPrive().get(1));
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
		Action action = new PlacerTuileCivilisation(partie, partie.getJoueur(), new Position(0,1), tuile);
		action.executer();
		TuileCivilisation tuileTemple = (TuileCivilisation) this.partie.getPlateauJeu().getPlateau()[1][1];
		assertEquals(partie.getPlateauJeu().recupererTerritoireTuile(tuile), partie.getPlateauJeu().recupererTerritoireTuile(tuileTemple));
	}


	@Test
	public void testTerritoireConflit() {
		TuileCivilisation tuileT = new TuileCivilisation(TypeTuileCivilisation.Temple);
		Action actionT = new PlacerTuileCivilisation(partie, partie.getJoueur(), new Position(0,2), tuileT);
		actionT.executer();
		Action actionChef = new PlacerChef(partie, partie.getJoueur(), this.partie.getJoueur().getDeckPublic().getDeckPublic().get(0), new Position(0,3));
		actionChef.executer();
		TuileCivilisation tuile = new TuileCivilisation(TypeTuileCivilisation.Marché);
		PlacerTuileCivilisation action = new PlacerTuileCivilisation(partie, partie.getJoueur(), new Position(1,2), tuile);
		Action actionC = new PlacerChef(partie, partie.getJoueur(), this.partie.getJoueur().getDeckPublic().getDeckPublic().get(0), new Position(1,0));
		System.out.println(tuile);
		actionC.executer();
		boolean ok = action.executer();
		assertTrue(action.isConflit());
		assertTrue(ok);

	}
	
	/**
	 * test d'attribution de point Victoire
	 */
	@Test
	public void testAttribuerPoint(){
		TuileCivilisation tuile = this.partie.getJoueur().getDeckPrive().getDeckPrive().get(0);
		Chef chef = this.partie.getJoueur().getDeckPublic().getDeckPublic().get(0);
		Action actionChef = new PlacerChef(partie, partie.getJoueur(), chef,  new Position(3,5));
		Action action = new PlacerTuileCivilisation(partie, partie.getJoueur(), new Position(1,5), tuile);
		assertTrue(actionChef.executer());
		assertTrue(action.executer());
		assertEquals(partie.getJoueur().getPointVictoireVert(),1);
		assertEquals(partie.getJoueur().getPointVictoireBleu(),0);
		assertEquals(partie.getJoueur().getPointVictoireJaune(),0);
		assertEquals(partie.getJoueur().getPointVictoireRouge(),0);
		
		//assertFalse(t.isEstRoyaume());
	}
	
	/**
	 * TestNonAttributionPoint
	 */
	@Test
	public void PasAttribuerPointMauvaisChef(){
		TuileCivilisation tuiletest = new TuileCivilisation(TypeTuileCivilisation.Population);
		this.partie.getJoueur().getDeckPrive().getDeckPrive().add(tuiletest);
		Chef chef = this.partie.getJoueur().getDeckPublic().getDeckPublic().get(0);
		Action actionChef = new PlacerChef(partie, partie.getJoueur(), chef,  new Position(3,5));
		Action action = new PlacerTuileCivilisation(partie, partie.getJoueur(), new Position(1,5), tuiletest);
		assertTrue(actionChef.executer());
		assertTrue(action.executer());
		assertEquals(partie.getJoueur().getPointVictoireVert(),0);
		assertEquals(partie.getJoueur().getPointVictoireBleu(),0);
		assertEquals(partie.getJoueur().getPointVictoireJaune(),0);
		assertEquals(partie.getJoueur().getPointVictoireRouge(),0);
	}
	
	/**
	 * TestNonAttributionPoint
	 */
	@Test
	public void PasAttribuerPointJonction(){
		TuileCivilisation tuile = this.partie.getJoueur().getDeckPrive().getDeckPrive().get(0);
		TuileCivilisation tuiletest = new TuileCivilisation(TypeTuileCivilisation.Population);
		this.partie.getJoueur().getDeckPrive().getDeckPrive().add(tuiletest);
		Chef chef = this.partie.getJoueur().getDeckPublic().getDeckPublic().get(0);
		Action actionChef = new PlacerChef(partie, partie.getJoueur(), chef,  new Position(3,5));
		Action action = new PlacerTuileCivilisation(partie, partie.getJoueur(), new Position(2,6), tuile);
		Action action2 = new PlacerTuileCivilisation(partie, partie.getJoueur(), new Position(2,7), tuiletest);
		assertTrue(actionChef.executer());
		assertTrue(action2.executer());
		assertTrue(action.executer());
		assertTrue(tuile.estJonction());
		assertEquals(partie.getJoueur().getPointVictoireVert(),0);
		assertEquals(partie.getJoueur().getPointVictoireBleu(),0);
		assertEquals(partie.getJoueur().getPointVictoireJaune(),0);
		assertEquals(partie.getJoueur().getPointVictoireRouge(),0);
	}

}
