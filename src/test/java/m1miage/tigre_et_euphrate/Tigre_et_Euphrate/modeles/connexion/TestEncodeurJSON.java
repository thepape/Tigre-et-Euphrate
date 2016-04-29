package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.connexion;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.DeckPrive;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.DeckPublic;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Pioche;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Plateau;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Dynastie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.TypeChef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TypeTuileCivilisation;

public class TestEncodeurJSON {

	private Partie partie;

	@Before
	public void initialize()
	{
		DeckPrive deckPrive = new DeckPrive();
		TuileCivilisation tuile1 = new TuileCivilisation(TypeTuileCivilisation.Marché);
		TuileCivilisation tuile2 = new TuileCivilisation(TypeTuileCivilisation.Ferme);
		TuileCivilisation tuile3 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		TuileCivilisation tuile4 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		deckPrive.ajouter(tuile1);
		deckPrive.ajouter(tuile2);
		deckPrive.ajouter(tuile3);
		deckPrive.ajouter(tuile4);

		DeckPublic deckPublic = new DeckPublic();
		Chef chef1 = new Chef(TypeChef.Marchand);
		Chef chef2 = new Chef(TypeChef.Roi);
		Chef chef3 = new Chef(TypeChef.Pretre);
		Chef chef4 = new Chef(TypeChef.Fermier);
		deckPublic.ajouterChef(chef1);
		deckPublic.ajouterChef(chef2);
		deckPublic.ajouterChef(chef3);
		deckPublic.ajouterChef(chef4);

		DeckPrive deckPrive2 = new DeckPrive();
		TuileCivilisation tuile21 = new TuileCivilisation(TypeTuileCivilisation.Marché);
		TuileCivilisation tuile22 = new TuileCivilisation(TypeTuileCivilisation.Ferme);
		TuileCivilisation tuile23 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		TuileCivilisation tuile24 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		deckPrive.ajouter(tuile21);
		deckPrive.ajouter(tuile22);
		deckPrive.ajouter(tuile23);
		deckPrive.ajouter(tuile24);

		DeckPublic deckPublic2 = new DeckPublic();
		Chef chef21 = new Chef(TypeChef.Marchand);
		Chef chef22 = new Chef(TypeChef.Roi);
		Chef chef23 = new Chef(TypeChef.Pretre);
		Chef chef24 = new Chef(TypeChef.Fermier);
		deckPublic.ajouterChef(chef1);
		deckPublic.ajouterChef(chef2);
		deckPublic.ajouterChef(chef3);
		deckPublic.ajouterChef(chef4);

		Joueur joueur2 = new Joueur("joueur2", Dynastie.Lanister, deckPublic, deckPrive);
		Joueur joueur1 = new Joueur("joueur1", Dynastie.Stark, deckPublic2, deckPrive2);

		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		joueurs.add(joueur1);
		joueurs.add(joueur2);
		this.partie = new Partie(new Plateau(), joueurs, new Pioche());
		this.partie.setListeTours(joueurs);
	}

	@Test
	public void testEncodeToJSON() throws IOException {
		EncoderJSON e = new EncoderJSON(this.partie);

		File file = e.convertToJSON();
		assertEquals(file, new File("partieEnCours.json"));
	}

	@Test
	public void testEncodeToPartie() throws IOException {
		File file = new File("partieEnCours.json");

		EncoderJSON e = new EncoderJSON(this.partie);
		Partie partieSauv = e.convertToPartie(file);
		assertEquals(this.partie.getListeJoueurs().get(0).getNom(), partieSauv.getListeJoueurs().get(0).getNom());
	}

}
