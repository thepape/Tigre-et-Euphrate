package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.other;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Placable;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Plateau;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Position;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Territoire;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCatastrophe;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TypeTuileCivilisation;

public class TestCissionRoyaume {
	
	public Plateau plateau;

	@Before
	public void initialiser() throws Exception {
		this.plateau = new Plateau();
	}


	/**
	 * test methode addTuile
	 */
	@Test
	public void testCissionTerritoires3x1Cission() {
		Territoire terr1 = new Territoire();
		this.plateau.getListeRoyaume().add(terr1);
		
		TuileCivilisation t1 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		t1.setTerritoire(terr1);
		TuileCivilisation t2 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		t2.setTerritoire(terr1);
		TuileCivilisation t3 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		t3.setTerritoire(terr1);
		
		this.plateau.placerTuile(t1, 4, 0);
		this.plateau.placerTuile(t2, 4, 1);
		this.plateau.placerTuile(t3, 4, 2);
		
		System.out.println("avant cission :");
		System.out.println(this.printTerritoiresPlateau());
		
		TuileCatastrophe tcata = new TuileCatastrophe();
		tcata.setPosition(new Position(4,1));
		this.plateau.getPlateau()[4][1] = tcata;
		this.plateau.reconstruireTerritoires(new Position(4,1));
		
		System.out.println("après cission :");
		System.out.println(this.printTerritoiresPlateau());
	}
	
	@Test
	public void testCissionTerritoires3x3NonCission() {
		Territoire terr1 = new Territoire();
		this.plateau.getListeRoyaume().add(terr1);
		
		TuileCivilisation t11 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		t11.setTerritoire(terr1);
		TuileCivilisation t21 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		t21.setTerritoire(terr1);
		TuileCivilisation t31 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		t31.setTerritoire(terr1);
		
		TuileCivilisation t12 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		t12.setTerritoire(terr1);
		TuileCivilisation t22 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		t22.setTerritoire(terr1);
		TuileCivilisation t32 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		t32.setTerritoire(terr1);
		
		TuileCivilisation t13 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		t13.setTerritoire(terr1);
		TuileCivilisation t23 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		t23.setTerritoire(terr1);
		TuileCivilisation t33 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		t33.setTerritoire(terr1);
		
		this.plateau.placerTuile(t11, 4, 4);
		this.plateau.placerTuile(t21, 4, 5);
		this.plateau.placerTuile(t31, 4, 6);
		
		this.plateau.placerTuile(t12, 5, 4);
		this.plateau.placerTuile(t22, 5, 5);
		this.plateau.placerTuile(t32, 5, 6);
		
		this.plateau.placerTuile(t13, 6, 4);
		this.plateau.placerTuile(t23, 6, 5);
		this.plateau.placerTuile(t33, 6, 6);
		
		System.out.println("avant cission :");
		System.out.println(this.printTerritoiresPlateau());
		
		TuileCatastrophe tcata = new TuileCatastrophe();
		tcata.setPosition(new Position(5,5));
		this.plateau.getPlateau()[5][5] = tcata;
		
		
		this.plateau.reconstruireTerritoires(new Position(5,5));
		
		System.out.println("après cission :");
		System.out.println(this.printTerritoiresPlateau());
	}
	
	@Test
	public void testCissionTerritoiresPlusNonCission() {
		Territoire terr1 = new Territoire();
		this.plateau.getListeRoyaume().add(terr1);
		
		TuileCivilisation tn = new TuileCivilisation(TypeTuileCivilisation.Temple);
		tn.setTerritoire(terr1);
		TuileCivilisation te = new TuileCivilisation(TypeTuileCivilisation.Temple);
		te.setTerritoire(terr1);
		TuileCivilisation ts = new TuileCivilisation(TypeTuileCivilisation.Temple);
		ts.setTerritoire(terr1);
		TuileCivilisation to = new TuileCivilisation(TypeTuileCivilisation.Temple);
		to.setTerritoire(terr1);
		TuileCivilisation tc = new TuileCivilisation(TypeTuileCivilisation.Temple);
		tc.setTerritoire(terr1);
		
		
		this.plateau.placerTuile(tn, 4, 5);
		this.plateau.placerTuile(te, 5, 6);
		this.plateau.placerTuile(ts, 6, 5);
		this.plateau.placerTuile(to, 5, 4);
		this.plateau.placerTuile(tc, 5, 5);
		
		System.out.println("avant cission :");
		System.out.println(this.printTerritoiresPlateau());
		
		TuileCatastrophe tcata = new TuileCatastrophe();
		tcata.setPosition(new Position(5,5));
		this.plateau.getPlateau()[5][5] = tcata;
		
		
		this.plateau.reconstruireTerritoires(new Position(5,5));
		
		System.out.println("après cission :");
		System.out.println(this.printTerritoiresPlateau());
	}
	
	@Test
	public void testCissionTerritoires2() {
		Territoire terr1 = new Territoire();
		this.plateau.getListeRoyaume().add(terr1);
		
		TuileCivilisation tn = new TuileCivilisation(TypeTuileCivilisation.Temple);
		tn.setTerritoire(terr1);
		TuileCivilisation te = new TuileCivilisation(TypeTuileCivilisation.Temple);
		te.setTerritoire(terr1);
		TuileCivilisation ts = new TuileCivilisation(TypeTuileCivilisation.Temple);
		ts.setTerritoire(terr1);
		TuileCivilisation to = new TuileCivilisation(TypeTuileCivilisation.Temple);
		to.setTerritoire(terr1);
		TuileCivilisation tc = new TuileCivilisation(TypeTuileCivilisation.Temple);
		tc.setTerritoire(terr1);
		TuileCivilisation tne = new TuileCivilisation(TypeTuileCivilisation.Temple);
		tne.setTerritoire(terr1);
		
		
		this.plateau.placerTuile(tn, 4, 5);
		this.plateau.placerTuile(tne, 4, 6);
		this.plateau.placerTuile(te, 5, 6);
		this.plateau.placerTuile(ts, 6, 5);
		this.plateau.placerTuile(to, 5, 4);
		this.plateau.placerTuile(tc, 5, 5);
		
		System.out.println("avant cission :");
		System.out.println(this.printTerritoiresPlateau());
		
		TuileCatastrophe tcata = new TuileCatastrophe();
		tcata.setPosition(new Position(5,5));
		this.plateau.getPlateau()[5][5] = tcata;
		
		
		this.plateau.reconstruireTerritoires(new Position(5,5));
		
		System.out.println("après cission :");
		System.out.println(this.printTerritoiresPlateau());
	}
	
	@Test
	public void testCissionTerritoires3() {
		Territoire terr1 = new Territoire();
		this.plateau.getListeRoyaume().add(terr1);
		
		TuileCivilisation tn = new TuileCivilisation(TypeTuileCivilisation.Temple);
		tn.setTerritoire(terr1);
		TuileCivilisation te = new TuileCivilisation(TypeTuileCivilisation.Temple);
		te.setTerritoire(terr1);
		TuileCivilisation ts = new TuileCivilisation(TypeTuileCivilisation.Temple);
		ts.setTerritoire(terr1);
		TuileCivilisation to = new TuileCivilisation(TypeTuileCivilisation.Temple);
		to.setTerritoire(terr1);
		TuileCivilisation tc = new TuileCivilisation(TypeTuileCivilisation.Temple);
		tc.setTerritoire(terr1);
		TuileCivilisation tne = new TuileCivilisation(TypeTuileCivilisation.Temple);
		tne.setTerritoire(terr1);
		TuileCivilisation tnee = new TuileCivilisation(TypeTuileCivilisation.Temple);
		tnee.setTerritoire(terr1);
		
		
		this.plateau.placerTuile(tn, 4, 5);
		this.plateau.placerTuile(tne, 4, 6);
		this.plateau.placerTuile(tnee, 4, 7);
		this.plateau.placerTuile(te, 5, 6);
		this.plateau.placerTuile(ts, 6, 5);
		this.plateau.placerTuile(to, 5, 4);
		this.plateau.placerTuile(tc, 5, 5);
		
		System.out.println("avant cission :");
		System.out.println(this.printTerritoiresPlateau());
		
		TuileCatastrophe tcata = new TuileCatastrophe();
		tcata.setPosition(new Position(5,5));
		this.plateau.getPlateau()[5][5] = tcata;
		
		
		
		this.plateau.reconstruireTerritoires(new Position(5,5));
		
		System.out.println("après cission 1:");
		System.out.println(this.printTerritoiresPlateau());
		
		TuileCatastrophe tcata2 = new TuileCatastrophe();
		tcata2.setPosition(new Position(6,4));
		this.plateau.getPlateau()[4][6] = tcata2;
		
		this.plateau.reconstruireTerritoires(new Position(4,6));
		
		System.out.println("après cission 2:");
		System.out.println(this.printTerritoiresPlateau());
	}

	private String printTerritoiresPlateau(){
		StringBuffer sb = new StringBuffer("");
		
		for(int i=0; i < 11;i++){
			for(int j=0;j<16;j++){
				Placable p = this.plateau.getPlateau()[i][j];
				String s = "[";
				if(p instanceof TuileCivilisation){
					Territoire t = ((TuileCivilisation)p).getTerritoire();
					
					if(t != null){
						String id = t.getIdTerritoire()+"";
						if(id.length() == 1){
							id += " ";
						}
						s += id;
					}else{
						s += "* ";
					}
				}else if (p instanceof TuileCatastrophe) {
					s += "X ";
				}
				else{
					s += "  ";
				}
				s += "]";
				sb.append(s);
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
}
