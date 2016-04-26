package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.other;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.DeckPublic;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.TypeChef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCatastrophe;

public class TestDeck {

	DeckPublic deckpublic ; 
	Joueur joueur1 ;

	@Before
	public void initialiser() throws Exception {
		deckpublic = new DeckPublic();
		joueur1 = new Joueur();
	}


	/**
	 * ajout des catastrophes
	 */
	@Test
	public void testAjoutCatastrophe() {
		//Creation catastrophe
		TuileCatastrophe tc1 = new TuileCatastrophe();
		TuileCatastrophe tc2 = new TuileCatastrophe();
		
		deckpublic.ajouterCatastrophe(tc1);
		deckpublic.ajouterCatastrophe(tc2);
		
		assertEquals(deckpublic.getListeTuileCatastrophe().size(),2);
	}
	
	/**
	 * suppression catastrophe
	 */
	@Test
	public void testSupprimerCatastrophe() {
		//Creation catastrophe
		TuileCatastrophe tc1 = new TuileCatastrophe();
		TuileCatastrophe tc2 = new TuileCatastrophe();
		
		deckpublic.ajouterCatastrophe(tc1);
		deckpublic.ajouterCatastrophe(tc2);
		
		deckpublic.supprimerCatastrohe(tc1);
		
		assertEquals(deckpublic.getListeTuileCatastrophe().size(),1);
		assertFalse(deckpublic.getListeTuileCatastrophe().contains(tc1));
	}
	
	/**
	 * Ajouter Chef
	 */
	@Test
	public void testAjoutChef() {
		//Creation des chefs
				Chef chef1 = new Chef(TypeChef.Roi,joueur1);
				Chef chef2 = new Chef(TypeChef.Marchand,joueur1);
				Chef chef3 = new Chef(TypeChef.Fermier,joueur1);
		
		deckpublic.ajouterChef(chef1);
		deckpublic.ajouterChef(chef2);
		deckpublic.ajouterChef(chef3);
		
		assertTrue(deckpublic.getdeckPublic().contains(chef2));
		assertEquals(deckpublic.getdeckPublic().size(),3);
	}
	
	/**
	 * Suppression Chef
	 */
	@Test
	public void testSupprimerChef() {
		//Creation des chefs
				Chef chef1 = new Chef(TypeChef.Roi,joueur1);
				Chef chef2 = new Chef(TypeChef.Marchand,joueur1);
				Chef chef3 = new Chef(TypeChef.Fermier,joueur1);
		
				deckpublic.ajouterChef(chef1);
				deckpublic.ajouterChef(chef2);
				deckpublic.ajouterChef(chef3);
		
		deckpublic.supprimerChef(chef1);
		
		assertEquals(deckpublic.getdeckPublic().size(),2);
		assertFalse(deckpublic.getdeckPublic().contains(chef1));
	}

}
