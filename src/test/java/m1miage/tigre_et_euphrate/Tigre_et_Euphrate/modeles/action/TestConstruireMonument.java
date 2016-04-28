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
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.conflit.Conflits;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.Monument;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TypeTuileCivilisation;

public class TestConstruireMonument {
	
	private Plateau plateau;
	private Partie partie;
	private Territoire t1;

	@Before
	public void initialize() {
		this.partie = new Partie();
		this.plateau = new Plateau();
		this.partie.setPlateauJeu(plateau);
		TuileCivilisation t1,t2,t3,t4;
		t1 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		t2 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		t3 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		t4 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		
		this.plateau.placerTuile(t1, 4, 0);
		this.plateau.placerTuile(t2, 5, 0);
		this.plateau.placerTuile(t3, 4, 1);
		this.plateau.placerTuile(t4, 5, 1);
		
		Territoire t = new Territoire();
		this.t1 = t;
		
		t1.setTerritoire(t);
		t2.setTerritoire(t);
		t3.setTerritoire(t);
		t4.setTerritoire(t);
	}
	
	@Test
	public void testConstructionOKNO(){
		Monument m = new Monument("rouge","vert");
		
		ConstruireMonument action = new ConstruireMonument(this.partie,null,m,new Position(4,0));
		boolean ok = action.executer();
		
		assertTrue(ok);
		
		TuileCivilisation tuileNO = (TuileCivilisation) this.plateau.getPlacableAt(new Position(4,0));
		assertEquals(tuileNO, m.getTuileNO());
	}
	
	@Test
	public void testConstructionOKNE(){
		Monument m = new Monument("rouge","vert");
		
		ConstruireMonument action = new ConstruireMonument(this.partie,null,m,new Position(4,1));
		boolean ok = action.executer();
		
		assertTrue(ok);
		
		TuileCivilisation tuileNO = (TuileCivilisation) this.plateau.getPlacableAt(new Position(4,0));
		assertEquals(tuileNO, m.getTuileNO());
	}

	@Test
	public void testConstructionOKSO(){
		Monument m = new Monument("rouge","vert");
		
		ConstruireMonument action = new ConstruireMonument(this.partie,null,m,new Position(5,0));
		boolean ok = action.executer();
		
		assertTrue(ok);
		
		TuileCivilisation tuileNO = (TuileCivilisation) this.plateau.getPlacableAt(new Position(4,0));
		assertEquals(tuileNO, m.getTuileNO());
	}
	
	@Test
	public void testConstructionOKSE(){
		Monument m = new Monument("rouge","vert");
		
		ConstruireMonument action = new ConstruireMonument(this.partie,null,m,new Position(5,1));
		boolean ok = action.executer();;
		
		assertTrue(ok);
		
		
		
		TuileCivilisation tuileNO = (TuileCivilisation) this.plateau.getPlacableAt(new Position(4,0));
		assertEquals(tuileNO, m.getTuileNO());
	}
	
	@Test
	public void testConstructionFailConflits(){
		TuileCivilisation tn = new TuileCivilisation(TypeTuileCivilisation.Temple);
		this.plateau.placerTuile(tn,5, 2);
		tn.setTerritoire(new Territoire());
		
		Conflits conflit = new Conflits();
		conflit.setTerritoireAttaquant(this.t1);
		conflit.setTerritoireDefenseur(tn.getTerritoire());
		this.partie.ajouterConflit(conflit);
		
		Monument m = new Monument("rouge","vert");
		
		ConstruireMonument action = new ConstruireMonument(this.partie,null,m,new Position(5,1));
		boolean ok = action.executer();
		
		assertTrue(!ok);
	}
	
	@Test
	public void testConstructionEjectionChef(){
		
		Joueur j1 = new Joueur();
		j1.setDeckPublic(new DeckPublic());
		Chef chef = new Chef(TypeChef.Marchand,j1);
		chef.setPosition(new Position(5,2));
		
		this.plateau.getPlateau()[5][2] = chef;
		this.t1.addChefs(chef);
		
		Monument m = new Monument("rouge","vert");
		
		ConstruireMonument action = new ConstruireMonument(this.partie,j1,m,new Position(5,1));
		action.executer();
		
		assertNull(this.plateau.getPlateau()[5][2]);
		assertEquals(j1.getDeckPublic().getDeckPublic().get(0), chef);
	}
	
	@Test
	public void testConstructionPoints(){
		TuileCivilisation tn = new TuileCivilisation(TypeTuileCivilisation.Temple);
		tn.setTerritoire(t1);
		this.plateau.placerTuile(tn, 5, 2);
		
		Joueur j1 = new Joueur();
		j1.setDeckPublic(new DeckPublic());
		Chef chef = new Chef(TypeChef.Marchand,j1);
		chef.setPosition(new Position(5,3));
		
		this.plateau.getPlateau()[5][3] = chef;
		this.t1.addChefs(chef);
		
		Monument m = new Monument("rouge","vert");
		
		assertEquals(j1.getPointVictoire(), 0);
		
		ConstruireMonument action = new ConstruireMonument(this.partie,j1,m,new Position(5,1));
		action.executer();
		
		assertEquals(j1.getPointVictoire(),1);
	}
}
