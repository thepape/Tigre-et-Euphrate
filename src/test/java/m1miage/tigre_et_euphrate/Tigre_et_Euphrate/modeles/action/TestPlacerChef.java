package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

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
	}

	@Test
	public void testPlacerChefNormalChefNonMarchand() {
		Position position = new Position(9,0);

		Action action = new PlacerChef(partie, joueur, chef, position);
		assertTrue(action.executer());
	}

}
