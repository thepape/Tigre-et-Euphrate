package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action;

import java.util.ArrayList;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Joueur;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Placable;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Plateau;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Position;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Territoire;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.conflit.Conflits;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.Monument;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TypeTuileCivilisation;

public class ConstruireMonument extends Action{

	/**
	 * Le monument qu'on construit
	 */
	private Monument monument;

	/**
	 * La position du monument
	 */
	private Position position;

	/**
	 * Tuile NordOuest du monument
	 */
	private TuileCivilisation pNO;

	/**
	 * Tuile NordEst du monument
	 */
	private TuileCivilisation pNE;

	/**
	 * Tuile SUdOuest du monument
	 */
	private TuileCivilisation pSO;

	/**
	 * Tuile SudEst du monument
	 */
	private TuileCivilisation pSE;


	/**
	 * Constructeur du monument
	 * @param pPartie
	 * @param pJoueur
	 * @param pMonument
	 * @param pPosition
	 */
	public ConstruireMonument(Partie pPartie, Joueur pJoueur, Monument pMonument, Position pPosition){
		super(pPartie, pJoueur);
		this.monument = pMonument;
		this.position = pPosition;
	}

	/**
	 * Verifie la constructibilité du monument
	 * @return
	 */
	public boolean verifier(){
		boolean ok;
		//verifier la non présence de conflits concernant le territoire où poser le monument
		ok = this.verifierNonConflits();
		if(!ok){
			return false;
		}
		//verifier l'adjacence de 4 cases de meme type
		ok = this.verifierToutesLesCasesPossibles();
		if(!ok){
			return false;
		}

		return true;
	}

	/**
	 * Vérifie la placabilité du monument sur toutes les combinaisons de 4 cases possibles contenant la position
	 * @return
	 */
	private boolean verifierToutesLesCasesPossibles() {

		Plateau plateau = this.partie.getPlateauJeu();
		int x = this.position.getX();
		int y = this.position.getY();
		boolean ok = false;

		//verification avec position = tuileNO
		ok = this.verifierLesQuatreCases(this.position, new Position(x,y+1), new Position(x+1,y), new Position(x+1,y+1));
		if(ok){
			return true;
		}

		//verification avec position = tuileNE
		ok = this.verifierLesQuatreCases(new Position(x,y-1), this.position, new Position(x+1,y-1), new Position(x+1,y));
		if(ok){
			return true;
		}

		//verification avec position = tuileSO
		ok = this.verifierLesQuatreCases(new Position(x-1,y), new Position(x-1,y+1), this.position, new Position(x,y+1));
		if(ok){
			return true;
		}

		//verification avec position = tuileSE
		ok = this.verifierLesQuatreCases(new Position(x-1,y-1), new Position(x-1,y), new Position(x,y-1), this.position);
		if(ok){
			return true;
		}

		return false;
	}

	/**
	 * verifie si les 4 position remplissent les conditions suivantes :
	 * - pas hors limites
	 * - contiennent des tuile civilisation de même couleur
	 * - aucune ne sert deja de base a un monument
	 * - l'une des deux couleurs du monument correspond
	 * @param ppNO
	 * @param ppNE
	 * @param ppSO
	 * @param ppSE
	 * @return
	 */
	private boolean verifierLesQuatreCases(Position ppNO, Position ppNE, Position ppSO, Position ppSE){
		//on recupere 4 cases, dont la position
		Placable pNO, pNE, pSO, pSE;
		Plateau plateau = this.partie.getPlateauJeu();
				try{
					pNO = plateau.getPlacableAt(ppNO);
					pNE = plateau.getPlacableAt(ppNE);
					pSO = plateau.getPlacableAt(ppSO);
					pSE = plateau.getPlacableAt(ppSE);
				}catch(IndexOutOfBoundsException e){
					return false;
				}


				//on verifie qu'elles sont bien de type tuile civ
				if(!(pNO instanceof TuileCivilisation
						&& pNE instanceof TuileCivilisation
						&& pSO instanceof TuileCivilisation
						&& pSE instanceof TuileCivilisation)){
					return false;
				}

				//on verifie si les 4 cases ont la meme couleur et qu'un monument n'est pas deja construit dessus
				boolean baseOk = this.monument.verifierCouleurCases((TuileCivilisation) pNO, (TuileCivilisation) pNE, (TuileCivilisation) pSO, (TuileCivilisation) pSE);

				if(baseOk){
					this.pNO = (TuileCivilisation) pNO;
					this.pNE = (TuileCivilisation) pNE;
					this.pSO = (TuileCivilisation) pSO;
					this.pSE = (TuileCivilisation) pSE;

					return true;
				}
				else{
					return false;
				}
	}

	/**
	 * Verifie la non présence de conflits sur le territoire qui accueille le monument
	 * @return
	 */
	private boolean verifierNonConflits(){

		Placable placable = this.partie.getPlateauJeu().getPlacableAt(this.position);

		if(placable == null && !(placable instanceof TuileCivilisation)){
			return false;
		}

		TuileCivilisation tuile = (TuileCivilisation) placable;
		//Territoire t = tuile.getTerritoire();
		Territoire t = this.partie.getPlateauJeu().recupererTerritoireTuile(tuile);
		//on verifie que le territoire ne soit pas en conflit
		for(Conflits conflit : this.partie.getConflits()){
			boolean found = (conflit.getTerritoireAttaquant().getIdTerritoire() == t.getIdTerritoire()
					|| conflit.getTerritoireDefenseur().getIdTerritoire() == t.getIdTerritoire());

			if(found){
				return false;
			}
		}

		return true;
	}

	/**
	 * Construit un monument à la position indiquée
	 */
	@Override
	public boolean executer() {
		boolean ok = this.verifier();
		if(!ok){
			return false;
		}

		ok = this.monument.construire(this.pNO, pNE, pSO, pSE);
		this.partie.getListeMonuments().remove(this.monument);

		//Territoire territoire = this.pNO.getTerritoire();
		Territoire territoire = this.partie.getPlateauJeu().recupererTerritoireTuile(pNO);
		ArrayList<Chef> chefs = territoire.getChefs();

		//on ejecte les chefs qui ne sont plus a coté d'un temple actif
		for(Chef chef : chefs){
			int x = chef.getPosition().getX();
			int y = chef.getPosition().getY();
			Plateau plateau = this.partie.getPlateauJeu();

			Placable pNord = plateau.getPlacableAt(new Position(x-1,y));
			Placable pEst = plateau.getPlacableAt(new Position(x,y+1));
			Placable pSud = plateau.getPlacableAt(new Position(x+1,y));
			Placable pOuest = plateau.getPlacableAt(new Position(x,y-1));

			//on verifie si le chef n'est desormais plus adjacent a aucune tuile temple
			if(pNord instanceof TuileCivilisation && ((TuileCivilisation) pNord).getType().equals(TypeTuileCivilisation.Temple) && !((TuileCivilisation) pNord).estTuileMonument()){
				continue;
			}
			if(pEst instanceof TuileCivilisation && ((TuileCivilisation) pEst).getType().equals(TypeTuileCivilisation.Temple) && !((TuileCivilisation) pEst).estTuileMonument()){
				continue;
			}
			if(pSud instanceof TuileCivilisation && ((TuileCivilisation) pSud).getType().equals(TypeTuileCivilisation.Temple) && !((TuileCivilisation) pSud).estTuileMonument()){
				continue;
			}
			if(pOuest instanceof TuileCivilisation && ((TuileCivilisation) pOuest).getType().equals(TypeTuileCivilisation.Temple) && !((TuileCivilisation) pOuest).estTuileMonument()){
				continue;
			}

			//on ejecte le chef dans le deck de son joueur si c'est le cas
			plateau.getPlateau()[x][y] = null;
			Joueur possesseur = chef.getJoueur();
			possesseur.getDeckPublic().ajouterChef(chef);
			this.joueursImpactes.add(possesseur);
		}

		//attribution des points de victoire


				for(Chef chef : chefs){
					Joueur joueurChef = chef.getJoueur();
					String couleurChef = chef.getTypeChef().getCouleur();

					if(joueurChef.equals(this.joueur) && (couleurChef.equals(this.monument.getCouleurArche()) || couleurChef.equals(this.monument.getCouleurEscaliers()))){
						joueur.ajouterPointsVictoire(this.monument.getTuileNO().getType().getCouleur(),1);
					}
				}

		return ok;
	}
	
	public String toString(){
		return this.joueur.getNom()+" a construit un monument";
	}

}
