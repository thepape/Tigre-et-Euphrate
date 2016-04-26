package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.DeckPublic;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Pioche;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Plateau;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Position;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Territoire;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Dynastie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.TypeChef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TypeTuileCivilisation;

public class TestPlacerChef {

	private Partie partie;
	private Joueur joueur;
	private Chef chef;

	@Before
	public void initialize() {
		Plateau plateau = new Plateau();
		this.joueur = new Joueur();
		this.joueur.setDynastie(Dynastie.Lanister);
		this.chef = new Chef(TypeChef.Marchand, joueur);
		ArrayList<Joueur> listeJoueur = new ArrayList<Joueur>();
		listeJoueur.add(joueur);
		Pioche pioche = new Pioche();
		this.partie = new Partie(plateau, listeJoueur, pioche);
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
	}

	@Test
	public void testPlacerChefNormalChefNonMarchand() {
		Position position = new Position(0,1);
		Action action = new PlacerChef(this.partie, this.joueur, (Chef) this.joueur.getDeckPublic().getDeckPublic().get(1), position);
		assertTrue(action.executer());
		assertEquals(this.joueur.getDeckPublic().getDeckPublic().size(), 3);
		Chef chef = (Chef)this.partie.getPlateauJeu().getPlateau()[0][1];
		assertSame(chef.getTypeChef(), TypeChef.Roi);
	}

	@Test
	public void testPlacerChefMauvaiseCase() {
		Position position = new Position(8,8);

		Action action = new PlacerChef(partie, joueur, (Chef) this.joueur.getDeckPublic().getDeckPublic().get(1), position);
		assertFalse(action.executer());
	}

	@Test
	public void testPlacerChefHorsLimite() {
		Position position = new Position(17,18);

		Action action = new PlacerChef(partie, joueur, (Chef) this.joueur.getDeckPublic().getDeckPublic().get(1), position);
		assertFalse(action.executer());
	}

	@Test
	public void testPlacerChefHorsLimite2() {
		Position position = new Position(-4,0);

		Action action = new PlacerChef(partie, joueur, (Chef) this.joueur.getDeckPublic().getDeckPublic().get(1), position);
		assertFalse(action.executer());
	}

	@Test
	public void testPlacerChefCaseNonVide() {
		Position position = new Position(9,0);
		Action action = new PlacerChef(this.partie, this.joueur, (Chef) this.joueur.getDeckPublic().getDeckPublic().get(1), position);
		action.executer();
		assertFalse(action.executer());
	}

	@Test
	public void testTerritoireChef() {
		Position position = new Position(0,1);
		Action action = new PlacerChef(this.partie, this.joueur, (Chef) this.joueur.getDeckPublic().getDeckPublic().get(1), position);
		action.executer();
		Chef chef = (Chef)this.partie.getPlateauJeu().getPlateau()[0][1];
		assertEquals(chef.getTerritoire(), this.partie.getPlateauJeu().getListeRoyaume().get(0));
	}

	@Test
	public void testTerritoireChefDeuxTerritoire() {
		TuileCivilisation tuile = new TuileCivilisation(TypeTuileCivilisation.Marché);
		Territoire teritoire = new Territoire(tuile);
		this.partie.getPlateauJeu().getPlateau()[0][0] = tuile;
		Position position = new Position(0,1);
		Action action = new PlacerChef(this.partie, this.joueur, (Chef) this.joueur.getDeckPublic().getDeckPublic().get(1), position);
		assertFalse(action.executer());
	}

	@Test
	public void testConflitChef() {
		ArrayList<Chef> listeChefConflit = new ArrayList<Chef>();
		Chef chefRoi1 = new Chef(TypeChef.Roi, this.joueur);
		listeChefConflit.add(chefRoi1);
		this.joueur.setDynastie(Dynastie.Stark);
		Chef chefRoi2 = new Chef(TypeChef.Roi, this.joueur);
		listeChefConflit.add(chefRoi2);
		this.joueur.getDeckPublic().setDeckPublic(listeChefConflit);
		Action action = new PlacerChef(this.partie, this.joueur, this.joueur.getDeckPublic().getDeckPublic().get(0), new Position(0,1));
		action.executer();
		PlacerChef actionT = new PlacerChef(this.partie, this.joueur, this.joueur.getDeckPublic().getDeckPublic().get(0), new Position(1,0));
		assertTrue(actionT.executer());
		assertTrue(actionT.isConflit());

	}
}
