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
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.TypeChef;

public class TestPlacerChef {

	private Partie partie;
	private Joueur joueur;
	private Chef chef;

	@Before
	public void initialize() {
		Plateau plateau = new Plateau();
		this.joueur = new Joueur();
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
		Position position = new Position(9,0);

		Action action = new PlacerChef(this.partie, this.joueur, (Chef) this.joueur.getDeckPublic().getDeckPublic().get(1), position);
		assertTrue(action.executer());
		assertEquals(this.joueur.getDeckPublic().getDeckPublic().size(), 3);
		Chef chef = (Chef)this.partie.getPlateauJeu().getPlateau()[9][0];
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
}
