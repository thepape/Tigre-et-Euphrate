package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.conflit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.DeckPrive;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.DeckPublic;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Placable;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Plateau;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Position;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Territoire;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.TypeChef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TypeTuileCivilisation;

public class TestConflits {

	private Conflits conflit;
	
	private Partie partie;
	
	private Plateau plateau;

	@Before
	public void initialiser() throws Exception
	{
		partie = new Partie();
		plateau = new Plateau();
		partie.setPlateauJeu(plateau);
		
		ArrayList<TuileCivilisation> listeRenfortAttaquant = new ArrayList<TuileCivilisation>();
		ArrayList<TuileCivilisation> listeRenfortDefenseur = new ArrayList<TuileCivilisation>();
		
		Joueur ja = new Joueur();
		ja.setDeckPrive(new DeckPrive());
		ja.setDeckPublic(new DeckPublic());
		
		Joueur jd = new Joueur();
		jd.setDeckPrive(new DeckPrive());
		jd.setDeckPublic(new DeckPublic());
		
		Chef chefAttaquant = new Chef(TypeChef.Fermier, ja);
		Chef chefDefenseur = new Chef(TypeChef.Fermier, jd);
		chefDefenseur.setPosition(new Position(2,2));
		chefAttaquant.setPosition(new Position(4,2));
		ArrayList<TuileCivilisation> listeTuileTerritoireAttaquant = new ArrayList<TuileCivilisation>();
		ArrayList<TuileCivilisation> listeTuileTerritoireDefenseur = new ArrayList<TuileCivilisation>();
		ArrayList<Chef> listeChefTerritoireAttaquant = new ArrayList<Chef>();
		ArrayList<Chef> listeChefTerritoireDefenseur = new ArrayList<Chef>();


		//Initialisation du Territoire Defenseur
		TuileCivilisation tuile1Defenseur = new TuileCivilisation(TypeTuileCivilisation.Ferme);
		TuileCivilisation tuile2Defenseur = new TuileCivilisation(TypeTuileCivilisation.Marché);
		TuileCivilisation tuile3Defenseur = new TuileCivilisation(TypeTuileCivilisation.Population);
		TuileCivilisation tuile4Defenseur = new TuileCivilisation(TypeTuileCivilisation.Temple);
		TuileCivilisation tuile5Defenseur = new TuileCivilisation(TypeTuileCivilisation.Ferme);
		TuileCivilisation tuile6Defenseur = new TuileCivilisation(TypeTuileCivilisation.Ferme);
		TuileCivilisation tuile7Defenseur = new TuileCivilisation(TypeTuileCivilisation.Temple);
		TuileCivilisation tuile8Defenseur = new TuileCivilisation(TypeTuileCivilisation.Temple);
		tuile1Defenseur.setPosition(new Position(1,1));
		tuile2Defenseur.setPosition(new Position(1,2));
		tuile3Defenseur.setPosition(new Position(1,3));
		tuile4Defenseur.setPosition(new Position(2,1));
		tuile5Defenseur.setPosition(new Position(3,3));
		tuile6Defenseur.setPosition(new Position(3,1));
		tuile7Defenseur.setPosition(new Position(3,2));
		tuile8Defenseur.setPosition(new Position(2,3));
		listeTuileTerritoireDefenseur.add(tuile1Defenseur);
		listeTuileTerritoireDefenseur.add(tuile2Defenseur);
		listeTuileTerritoireDefenseur.add(tuile3Defenseur);
		listeTuileTerritoireDefenseur.add(tuile4Defenseur);
		listeTuileTerritoireDefenseur.add(tuile5Defenseur);
		listeTuileTerritoireDefenseur.add(tuile6Defenseur);
		listeTuileTerritoireDefenseur.add(tuile7Defenseur);
		listeTuileTerritoireDefenseur.add(tuile8Defenseur);
		listeChefTerritoireDefenseur.add(chefDefenseur);
		Territoire territoireDefenseur = new Territoire();
		territoireDefenseur.setChefs(listeChefTerritoireDefenseur);
		territoireDefenseur.setTuilesCivilisation(listeTuileTerritoireDefenseur);

		//Initialisation du Territoire Attaquant
		TuileCivilisation tuile1Attaquant = new TuileCivilisation(TypeTuileCivilisation.Marché);
		TuileCivilisation tuile2Attaquant = new TuileCivilisation(TypeTuileCivilisation.Marché);
		TuileCivilisation tuile3Attaquant = new TuileCivilisation(TypeTuileCivilisation.Population);
		TuileCivilisation tuile4Attaquant = new TuileCivilisation(TypeTuileCivilisation.Temple);
		TuileCivilisation tuile5Attaquant = new TuileCivilisation(TypeTuileCivilisation.Temple);
		TuileCivilisation tuile6Attaquant = new TuileCivilisation(TypeTuileCivilisation.Ferme);
		listeTuileTerritoireAttaquant.add(tuile1Attaquant);
		listeTuileTerritoireAttaquant.add(tuile2Attaquant);
		listeTuileTerritoireAttaquant.add(tuile3Attaquant);
		listeTuileTerritoireAttaquant.add(tuile4Attaquant);
		listeTuileTerritoireAttaquant.add(tuile5Attaquant);
		listeTuileTerritoireAttaquant.add(tuile6Attaquant);
		listeChefTerritoireAttaquant.add(chefAttaquant);
		Territoire territoireAttaquant = new Territoire();
		territoireAttaquant.setChefs(listeChefTerritoireAttaquant);
		territoireAttaquant.setTuilesCivilisation(listeTuileTerritoireAttaquant);

		//Initialisation du conflit
		conflit = new Conflits(chefAttaquant, chefDefenseur, territoireDefenseur, territoireAttaquant);
		conflit.setListeTuileRenfortAttaquant(listeRenfortAttaquant);
		conflit.setListeTuileRenfortDefenseur(listeRenfortDefenseur);
		conflit.setTypeConflit("E");
		conflit.setPartie(partie);
	}

	/**
	 * Test de la fonction definirGagnantConflit sans ajout de renfort
	 */
	@Test
	public void testGagnantConflitSansRenfort() {
		assertSame(conflit.definirChefGagnant(), conflit.getChefDefenseur());
	}

	/**
	 * Test de la fonction ajoutRenfort avec une tuile renfort de la même couleur que les chefs engagés
	 */
	@Test
	public void testAjoutRenfortMemeCouleur() {
		TuileCivilisation tuileRenfort = new TuileCivilisation(TypeTuileCivilisation.Ferme);
		assertTrue(conflit.ajoutRenfort(conflit.getListeTuileRenfortAttaquant(), tuileRenfort));
		assertEquals(conflit.getListeTuileRenfortAttaquant().size(), 1);

	}

	/**
	 * Test de l'ajout de renfort de type Temple pour un conflit interne
	 */
	@Test
	public void testAjoutRenfortConflitInterneTemple() {
		conflit.setTypeConflit("I");
		TuileCivilisation tuileRenfort = new TuileCivilisation(TypeTuileCivilisation.Temple);
		assertTrue(conflit.ajoutRenfort(conflit.getListeTuileRenfortAttaquant(), tuileRenfort));
		assertEquals(conflit.getListeTuileRenfortAttaquant().size(), 1);

	}

	/**
	 * Test de l'ajout de renfort de type différent de temple pour un conflit interne
	 */
	@Test
	public void testAjoutRenfortConflitInterneDiffTemple() {
		conflit.setTypeConflit("I");
		TuileCivilisation tuileRenfort = new TuileCivilisation(TypeTuileCivilisation.Ferme);
		assertFalse(conflit.ajoutRenfort(conflit.getListeTuileRenfortAttaquant(), tuileRenfort));
		assertEquals(conflit.getListeTuileRenfortAttaquant().size(), 0);

	}

	/**
	 * Test de la fonction ajoutRenfort avec un tuile de couleur différente des chefs engagés
	 */
	@Test
	public void testAjoutRenfortCouleurDiff() {
		TuileCivilisation tuileRenfort = new TuileCivilisation(TypeTuileCivilisation.Marché);
		assertFalse(conflit.ajoutRenfort(conflit.getListeTuileRenfortAttaquant(), tuileRenfort));
		assertEquals(conflit.getListeTuileRenfortAttaquant().size(), 0);
	}

	/**
	 * Test de la fonction definirChefGagnant avec ajout de renfort
	 */
	@Test
	public void testGagnantConflitAvecRenfort() {
		TuileCivilisation tuile1AttaquantRenfort = new TuileCivilisation(TypeTuileCivilisation.Ferme);
		TuileCivilisation tuile2AttaquantRenfort = new TuileCivilisation(TypeTuileCivilisation.Ferme);
		TuileCivilisation tuile3AttaquantRenfort = new TuileCivilisation(TypeTuileCivilisation.Ferme);
		TuileCivilisation tuile4AttaquantRenfort = new TuileCivilisation(TypeTuileCivilisation.Ferme);
		conflit.ajoutRenfort(conflit.getListeTuileRenfortAttaquant(), tuile1AttaquantRenfort);
		conflit.ajoutRenfort(conflit.getListeTuileRenfortAttaquant(), tuile2AttaquantRenfort);
		conflit.ajoutRenfort(conflit.getListeTuileRenfortAttaquant(), tuile3AttaquantRenfort);
		conflit.ajoutRenfort(conflit.getListeTuileRenfortAttaquant(), tuile4AttaquantRenfort);
		assertSame(conflit.definirChefGagnant(), conflit.getChefAttaquant());
	}

	/**
	 * Test de la fonction definirChefGagnant si elle retire bien le chef perdant du plateau
	 */
	@Test
	public void testChefRetireApresConflit()
	{
		Chef chefGagnant = conflit.definirChefGagnant();
		assertSame(conflit.getChefAttaquant().isRetiree(), true);
	}

	/**
	 * Test de la fonction definirChefGagnant si les 2 parties ont le même nombre de tuiles
	 */
	@Test
	public void testDefinirChefGagnantMemeNombreTuile()
	{
		TuileCivilisation tuile3AttaquantRenfort = new TuileCivilisation(TypeTuileCivilisation.Ferme);
		TuileCivilisation tuile4AttaquantRenfort = new TuileCivilisation(TypeTuileCivilisation.Ferme);
		conflit.ajoutRenfort(conflit.getListeTuileRenfortAttaquant(), tuile3AttaquantRenfort);
		conflit.ajoutRenfort(conflit.getListeTuileRenfortAttaquant(), tuile4AttaquantRenfort);
		assertSame(conflit.definirChefGagnant(), conflit.getChefDefenseur());
	}

	/**
	 * Test de la fonction setChefAttaquant pour un chef de couleur différente
	 */
	@Test
	public void testSetChefAttaquantCouleurDiff()
	{
		Chef nouveauChefAttaquant = new Chef(TypeChef.Marchand, new Joueur());
		conflit.setChefAttaquant(nouveauChefAttaquant);
		assertNull(conflit.getChefAttaquant());
	}

	/**
	 * Test de la fonction setChefAttaquant pour un chef de même couleur
	 */
	@Test
	public void testSetChefAttaquantMemeCouleur()
	{
		Chef nouveauChefAttaquant = new Chef(TypeChef.Fermier, new Joueur());
		conflit.setChefAttaquant(nouveauChefAttaquant);
		assertSame(conflit.getChefAttaquant(), nouveauChefAttaquant);
	}

	/**
	 * Test de la fonction setChefDefenseur pour un chef de couleur différente
	 */
	@Test
	public void testSetChefDefenseurCouleurDiff()
	{
		Chef nouveauChefDefenseur = new Chef(TypeChef.Marchand, new Joueur());
		conflit.setChefDefenseur(nouveauChefDefenseur);
		assertNull(conflit.getChefDefenseur());
	}

	/**
	 * Test de la fonction setChefAttaquant pour un chef de même couleur
	 */
	@Test
	public void testSetChefDefenseurMemeCouleur()
	{
		Chef nouveauChefDefenseur = new Chef(TypeChef.Fermier, new Joueur());
		conflit.setChefDefenseur(nouveauChefDefenseur);
		assertSame(conflit.getChefDefenseur(), nouveauChefDefenseur);
	}

	/**
	 * Test de la fonction definirChefGagnant si le conflit provient seulement d'un chef et non d'une jonction
	 * sans ajout de renfort
	 */
	@Test
	public void testDefinirGagnantConflitTerritoireAttaquantNullSansRenfort()
	{
		conflit.setTerritoireAttaquant(null);
		assertSame(conflit.definirChefGagnant(), conflit.getChefDefenseur());
	}

	/**
	 * Test de la fonction definirChefGagnant si le conflit provient seulement d'un chef et non d'une jonction
	 * avec ajout de renfort
	 */

	@Test
	public void testDefinirGagnantTerritoireAttaquantNullAvecRenfort()
	{
		conflit.setTerritoireAttaquant(null);

		TuileCivilisation tuile3AttaquantRenfort = new TuileCivilisation(TypeTuileCivilisation.Temple);
		TuileCivilisation tuile4AttaquantRenfort = new TuileCivilisation(TypeTuileCivilisation.Temple);
		TuileCivilisation tuile5AttaquantRenfort = new TuileCivilisation(TypeTuileCivilisation.Temple);
		TuileCivilisation tuile6AttaquantRenfort = new TuileCivilisation(TypeTuileCivilisation.Temple);
		conflit.setTypeConflit("I");
		conflit.ajoutRenfort(conflit.getListeTuileRenfortAttaquant(), tuile3AttaquantRenfort);
		conflit.ajoutRenfort(conflit.getListeTuileRenfortAttaquant(), tuile4AttaquantRenfort);
		conflit.ajoutRenfort(conflit.getListeTuileRenfortAttaquant(), tuile5AttaquantRenfort);
		conflit.ajoutRenfort(conflit.getListeTuileRenfortAttaquant(), tuile6AttaquantRenfort);

		assertSame(conflit.definirChefGagnant(), conflit.getChefAttaquant());

	}

}
