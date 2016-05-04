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
	 * TODO
	 */
	@Test
	public void testCissionTerritoires3x1Cission() {
		Territoire terr1 = new Territoire();
		this.plateau.getListeRoyaume().add(terr1);

		TuileCivilisation t1 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		terr1.addTuile(t1);
		TuileCivilisation t2 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		terr1.addTuile(t2);
		TuileCivilisation t3 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		terr1.addTuile(t3);

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
		terr1.addTuile(t11);
		TuileCivilisation t21 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		terr1.addTuile(t21);
		TuileCivilisation t31 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		terr1.addTuile(t31);

		TuileCivilisation t12 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		terr1.addTuile(t12);
		TuileCivilisation t22 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		terr1.addTuile(t22);
		TuileCivilisation t32 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		terr1.addTuile(t32);

		TuileCivilisation t13 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		terr1.addTuile(t13);
		TuileCivilisation t23 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		terr1.addTuile(t23);
		TuileCivilisation t33 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		terr1.addTuile(t33);

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
		terr1.addTuile(tn);
		TuileCivilisation te = new TuileCivilisation(TypeTuileCivilisation.Temple);
		terr1.addTuile(te);
		TuileCivilisation ts = new TuileCivilisation(TypeTuileCivilisation.Temple);
		terr1.addTuile(ts);
		TuileCivilisation to = new TuileCivilisation(TypeTuileCivilisation.Temple);
		terr1.addTuile(to);
		TuileCivilisation tc = new TuileCivilisation(TypeTuileCivilisation.Temple);
		terr1.addTuile(tc);


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
		terr1.addTuile(tn);
		TuileCivilisation te = new TuileCivilisation(TypeTuileCivilisation.Temple);
		terr1.addTuile(te);
		TuileCivilisation ts = new TuileCivilisation(TypeTuileCivilisation.Temple);
		terr1.addTuile(ts);
		TuileCivilisation to = new TuileCivilisation(TypeTuileCivilisation.Temple);
		terr1.addTuile(to);
		TuileCivilisation tc = new TuileCivilisation(TypeTuileCivilisation.Temple);
		terr1.addTuile(tc);
		TuileCivilisation tne = new TuileCivilisation(TypeTuileCivilisation.Temple);
		terr1.addTuile(tne);


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
		terr1.addTuile(tn);
		TuileCivilisation te = new TuileCivilisation(TypeTuileCivilisation.Temple);
		terr1.addTuile(te);
		TuileCivilisation ts = new TuileCivilisation(TypeTuileCivilisation.Temple);
		terr1.addTuile(ts);
		TuileCivilisation to = new TuileCivilisation(TypeTuileCivilisation.Temple);
		terr1.addTuile(to);
		TuileCivilisation tc = new TuileCivilisation(TypeTuileCivilisation.Temple);
		terr1.addTuile(tc);
		TuileCivilisation tne = new TuileCivilisation(TypeTuileCivilisation.Temple);
		terr1.addTuile(tne);
		TuileCivilisation tnee = new TuileCivilisation(TypeTuileCivilisation.Temple);
		terr1.addTuile(tnee);


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
	
	@Test
	public void testCissionTerritoiresJonction() {
		TuileCivilisation temple = (TuileCivilisation) this.plateau.getPlacableAt(new Position(9,5));
		Territoire terr1 = this.plateau.recupererTerritoireTuile(temple);
		//this.plateau.getListeRoyaume().add(terr1);

		TuileCivilisation t1 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		terr1.addTuile(t1);
		TuileCivilisation t2 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		terr1.addTuile(t2);
		TuileCivilisation t3 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		terr1.addTuile(t3);

		this.plateau.placerTuile(t1, 9, 4);
		this.plateau.placerTuile(t2, 9, 3);
		this.plateau.placerTuile(t3, 10, 5);
		
		TuileCivilisation temple2 = (TuileCivilisation) this.plateau.getPlacableAt(new Position(7,1));
		Territoire terr2 = this.plateau.recupererTerritoireTuile(temple2);
		//this.plateau.getListeRoyaume().add(terr2);
		
		TuileCivilisation t4 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		terr2.addTuile(t4);
		TuileCivilisation t5 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		terr2.addTuile(t5);
		TuileCivilisation t6 = new TuileCivilisation(TypeTuileCivilisation.Temple);
		terr2.addTuile(t6);
		
		this.plateau.placerTuile(t4, 8, 1);
		this.plateau.placerTuile(t5, 8, 2);
		this.plateau.placerTuile(t6, 9, 2);
		
		t6.setJonction(true);
		terr2.deletTuilesCivilisation(t6);
		
		System.out.println("avant cission jonction:");
		System.out.println(this.printTerritoiresPlateau());

		this.plateau.reconstruireTerritoires(new Position(9,4));

		System.out.println("apres cission jonction:");
		System.out.println(this.printTerritoiresPlateau());

	}

	private String printTerritoiresPlateau(){
		StringBuffer sb = new StringBuffer("");

		for(int i=0; i < 11;i++){
			for(int j=0;j<16;j++){
				Placable p = this.plateau.getPlateau()[i][j];
				String s = "[";
				if(p instanceof TuileCivilisation){
					Territoire t = plateau.recupererTerritoireTuile(p);

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
