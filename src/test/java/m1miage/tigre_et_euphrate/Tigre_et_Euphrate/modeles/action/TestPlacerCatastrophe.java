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
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Dynastie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.TypeChef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCatastrophe;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TypeTuileCivilisation;

/**
 * test pour placer une tuile
 * @author 
 *
 */
public class TestPlacerCatastrophe {
	
	private Partie partie;
	private Joueur joueur;
	private TuileCatastrophe tuilecatastrophe;
	private Plateau plateau;
	
	
	

	@Before
	public void initialise(){
		joueur = new Joueur();
		this.joueur.setDynastie(Dynastie.Lanister);
		tuilecatastrophe= new TuileCatastrophe();
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
		deckpublic.ajouterCatastrophe(tuilecatastrophe);
		this.joueur.setDeckPublic(deckpublic);
		
	}
	
	/**
	 * test de la vérification
	 */
	@Test
	public void testVerifierCase() {
		Position pos2 = new Position(5,1); //Terre avec Tuile
		Position pos3 = new Position(2,4); //Eau vide
		Position pos4 = new Position(0,1); //Terre avec Chef
		
		
		plateau.placerTuile(new TuileCivilisation(TypeTuileCivilisation.Marché), pos2);
		Action a = new PlacerChef(this.partie, this.joueur, (Chef) this.joueur.getDeckPublic().getDeckPublic().get(1),pos4);
		a.executer();
		
		partie = new Partie(plateau, null, new Pioche());
		PlacerTuileCatastrophe ptc = new PlacerTuileCatastrophe(partie,joueur,tuilecatastrophe,pos2);
		PlacerTuileCatastrophe ptc2 = new PlacerTuileCatastrophe(partie,joueur,tuilecatastrophe,pos3);
		PlacerTuileCatastrophe ptc3 = new PlacerTuileCatastrophe(partie,joueur,tuilecatastrophe,pos4);
		assertTrue(ptc.verifier());
		assertTrue(ptc2.verifier());
		assertFalse(ptc3.verifier());
		
	}
	
	/**
	 * test de l'execution
	 */
	@Test
	public void TestExecute(){
	
		Position pos2 = new Position(5,1); //Terre avec Tuile
		Position pos3 = new Position(2,4); //Eau vide
		Position pos4 = new Position(0,1); //Terre avec Chef
		partie = new Partie(plateau, null, new Pioche());
		plateau.placerTuile(new TuileCivilisation(TypeTuileCivilisation.Temple), pos2);
		Action a = new PlacerChef(this.partie, this.joueur, new Chef(TypeChef.Roi,joueur),pos4);
		a.executer();
		
		partie = new Partie(plateau, null, new Pioche());
		PlacerTuileCatastrophe ptc = new PlacerTuileCatastrophe(partie,joueur,tuilecatastrophe,pos2);
		PlacerTuileCatastrophe ptc2 = new PlacerTuileCatastrophe(partie,joueur,tuilecatastrophe,pos3);
		PlacerTuileCatastrophe ptc3 = new PlacerTuileCatastrophe(partie,joueur,tuilecatastrophe,pos4);
		assertTrue(ptc.executer());
		assertTrue(ptc2.executer());
		assertEquals(this.partie.getPlateauJeu().getPlacableAt(pos2).getId(), tuilecatastrophe.getId());
		assertEquals(this.partie.getPlateauJeu().getPlacableAt(pos3).getId(), tuilecatastrophe.getId());
		assertFalse(ptc3.executer());
		
	}

}
