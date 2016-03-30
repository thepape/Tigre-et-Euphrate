package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.conflit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Territoire;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.TypeChef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TypeTuileCivilisation;

public class TestConflits {

	private Conflits conflit;

	@Before
	public void initialiser() throws Exception
	{
		ArrayList<TuileRenfort> listeRenfortAttaquant = new ArrayList<TuileRenfort>();
		ArrayList<TuileRenfort> listeRenfortDefenseur = new ArrayList<TuileRenfort>();
		Chef chefAttaquant = new Chef(TypeChef.Fermier, new Joueur());
		Chef chefDefenseur = new Chef(TypeChef.Fermier, new Joueur());
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
		listeTuileTerritoireDefenseur.add(tuile1Defenseur);
		listeTuileTerritoireDefenseur.add(tuile2Defenseur);
		listeTuileTerritoireDefenseur.add(tuile3Defenseur);
		listeTuileTerritoireDefenseur.add(tuile4Defenseur);
		listeTuileTerritoireDefenseur.add(tuile5Defenseur);
		listeTuileTerritoireDefenseur.add(tuile6Defenseur);
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
		TuileRenfort tuileRenfort = new TuileRenfort(new TuileCivilisation(TypeTuileCivilisation.Ferme));
		assertTrue(conflit.ajoutRenfort(conflit.getListeTuileRenfortAttaquant(), tuileRenfort));
		assertEquals(conflit.getListeTuileRenfortAttaquant().size(), 1);

	}

	/**
	 * Test de la fonction ajoutRenfort avec un tuile de couleur différente des chefs engagés
	 */
	@Test
	public void testAjoutRenfortCouleurDiff() {
		TuileRenfort tuileRenfort = new TuileRenfort(new TuileCivilisation(TypeTuileCivilisation.Marché));
		assertFalse(conflit.ajoutRenfort(conflit.getListeTuileRenfortAttaquant(), tuileRenfort));
		assertEquals(conflit.getListeTuileRenfortAttaquant().size(), 0);
	}

	/**
	 * Test de la fonction definirChefGagnant avec ajout de renfort
	 */
	@Test
	public void testGagnantConflitAvecRenfort() {
		TuileRenfort tuile1AttaquantRenfort = new TuileRenfort(new TuileCivilisation(TypeTuileCivilisation.Ferme));
		TuileRenfort tuile2AttaquantRenfort = new TuileRenfort(new TuileCivilisation(TypeTuileCivilisation.Ferme));
		TuileRenfort tuile3AttaquantRenfort = new TuileRenfort(new TuileCivilisation(TypeTuileCivilisation.Ferme));
		TuileRenfort tuile4AttaquantRenfort = new TuileRenfort(new TuileCivilisation(TypeTuileCivilisation.Ferme));
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
		TuileRenfort tuile3AttaquantRenfort = new TuileRenfort(new TuileCivilisation(TypeTuileCivilisation.Ferme));
		TuileRenfort tuile4AttaquantRenfort = new TuileRenfort(new TuileCivilisation(TypeTuileCivilisation.Ferme));
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

		TuileRenfort tuile3AttaquantRenfort = new TuileRenfort(new TuileCivilisation(TypeTuileCivilisation.Ferme));
		TuileRenfort tuile4AttaquantRenfort = new TuileRenfort(new TuileCivilisation(TypeTuileCivilisation.Ferme));
		TuileRenfort tuile5AttaquantRenfort = new TuileRenfort(new TuileCivilisation(TypeTuileCivilisation.Ferme));
		TuileRenfort tuile6AttaquantRenfort = new TuileRenfort(new TuileCivilisation(TypeTuileCivilisation.Ferme));
		conflit.ajoutRenfort(conflit.getListeTuileRenfortAttaquant(), tuile3AttaquantRenfort);
		conflit.ajoutRenfort(conflit.getListeTuileRenfortAttaquant(), tuile4AttaquantRenfort);
		conflit.ajoutRenfort(conflit.getListeTuileRenfortAttaquant(), tuile5AttaquantRenfort);
		conflit.ajoutRenfort(conflit.getListeTuileRenfortAttaquant(), tuile6AttaquantRenfort);

		assertSame(conflit.definirChefGagnant(), conflit.getChefAttaquant());

	}

}
