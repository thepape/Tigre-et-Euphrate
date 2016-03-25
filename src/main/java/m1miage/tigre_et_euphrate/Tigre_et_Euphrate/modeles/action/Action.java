package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.action;

/**
 * 
 * Classe Action qui permet d effectuer des actions
 *
 */
public abstract class Action {

	/**
	 * Execute l'action
	 * @return vrai ou faux, selon le bon déroulement ou non de l'action
	 */
	public abstract boolean executer();

}