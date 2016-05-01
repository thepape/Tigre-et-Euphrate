package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.DeckPrive;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.DeckPublic;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Pioche;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Plateau;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Position;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Dynastie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.TypeChef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCatastrophe;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TypeTuileCivilisation;

public class TestPlacerTuileCivilisation {

	private Partie partie;
	private Joueur joueur;
	private Plateau plateau;
	TuileCivilisation tuile;
	TuileCivilisation tuile2;
	
	@Before
	public void initialize() throws RemoteException
	{
		
		
		joueur = new Joueur();
		this.joueur.setDynastie(Dynastie.Lanister);
		plateau = new Plateau();
		ArrayList<Joueur> listeJoueur = new ArrayList<Joueur>();
		listeJoueur.add(joueur);
		Pioche pioche = new Pioche();
		this.partie = new Partie(plateau, listeJoueur, pioche);
		this.partie.setPlateauJeu(plateau);
		DeckPublic deckpublic = new DeckPublic();
		Chef chefFermier = new Chef(TypeChef.Fermier);
		Chef chefRoi = new Chef(TypeChef.Roi);
		Chef chefMarchand = new Chef(TypeChef.Marchand);
		Chef chefPretre = new Chef(TypeChef.Pretre);
		deckpublic.ajouterChef(chefFermier);
		deckpublic.ajouterChef(chefRoi);
		deckpublic.ajouterChef(chefMarchand);
		deckpublic.ajouterChef(chefPretre);
		this.joueur.setDeckPublic(deckpublic);
		tuile = new TuileCivilisation(TypeTuileCivilisation.Marché);
		tuile2 = new TuileCivilisation(TypeTuileCivilisation.Ferme);
		DeckPrive deckprive = new DeckPrive();
		deckprive.ajouter(tuile);
		deckprive.ajouter(tuile2);
	}

	@Test
	public void testPlacerTuileNormal() {
		//TuileCivilisation tuile = new TuileCivilisation(TypeTuileCivilisation.Marché);
		Action action = new PlacerTuileCivilisation(partie, partie.getJoueur(), new Position(3,4), tuile);
		assertTrue(action.executer());
		assertSame(this.partie.getPlateauJeu().getPlateau()[3][4], tuile);
	}

	@Test
	public void testPlacerTuileFerme() {
		//TuileCivilisation tuile = new TuileCivilisation(TypeTuileCivilisation.Ferme);
		Action action = new PlacerTuileCivilisation(partie, partie.getJoueur(), new Position(3,1), tuile2);
		assertTrue(action.executer());
		assertSame(this.partie.getPlateauJeu().getPlateau()[3][1], tuile2);
	}

	@Test
	public void testPlacerTuileFermeNonValide() {
		//TuileCivilisation tuile = new TuileCivilisation(TypeTuileCivilisation.Ferme);
		Action action = new PlacerTuileCivilisation(partie, partie.getJoueur(), new Position(0,0), tuile2);
		assertFalse(action.executer());
	}

	@Test
	public void testPlacerTuileHorsPlateau() {
		//TuileCivilisation tuile = new TuileCivilisation(TypeTuileCivilisation.Marché);
		Action action = new PlacerTuileCivilisation(partie, partie.getJoueur(), new Position(18,27), tuile);
		assertFalse(action.executer());
	}

	@Test
	public void testPlacerTuileCaseNonVide() {
		//TuileCivilisation tuile = new TuileCivilisation(TypeTuileCivilisation.Marché);
		Action action = new PlacerTuileCivilisation(partie, partie.getJoueur(), new Position(1,3), tuile);
		action.executer();
		action = new PlacerTuileCivilisation(partie, partie.getJoueur(), new Position(1,3), tuile);
		assertFalse(action.executer());
	}

	@Test
	public void testTerritoirePlacerTuile() {
		//TuileCivilisation tuile = new TuileCivilisation(TypeTuileCivilisation.Marché);
		Action action = new PlacerTuileCivilisation(partie, partie.getJoueur(), new Position(0,1), tuile);
		action.executer();
		TuileCivilisation tuileTemple = (TuileCivilisation) this.partie.getPlateauJeu().getPlateau()[1][1];
		assertEquals(partie.getPlateauJeu().recupererTerritoireTuile(tuile), partie.getPlateauJeu().recupererTerritoireTuile(tuileTemple));
	}


	@Test
	public void testTerritoireConflit() {
		TuileCivilisation tuileT = new TuileCivilisation(TypeTuileCivilisation.Marché);
		Action actionT = new PlacerTuileCivilisation(partie, partie.getJoueur(), new Position(0,0), tuileT);
		actionT.executer();
		//TuileCivilisation tuile = new TuileCivilisation(TypeTuileCivilisation.Marché);
		PlacerTuileCivilisation action = new PlacerTuileCivilisation(partie, partie.getJoueur(), new Position(0,1), tuile);
		boolean ok = action.executer();
		assertTrue(action.isConflit());
		assertTrue(ok);

	}

}
