package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCatastrophe;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;

/**
 *
 * Classe représentant un objet placable sur une case de plateau. Deux placables ou plus ne peuvent pas être empilées sur une même case.
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
	@JsonSubTypes.Type(value = TuileCivilisation.class, name = "tuileCivilisation"),
	@JsonSubTypes.Type(value = Chef.class, name = "chef"),
	@JsonSubTypes.Type(value = TuileCatastrophe.class, name = "tuileCatastrophe")
})
public abstract class Placable implements Serializable {
	/**
	 * Incrément de l'id des objets placables.
	 */
	protected static int idIncrement = 1;

	/**
	 * Id de l'objet placable.
	 */
	protected int id;

	/**
	 * Position de l'objet placable.
	 */
	protected Position position;

	/**
	 * Getter de la position du placable.
	 * @return position du placable.
	 */
	public Position getPosition(){
		return this.position;
	}

	/**
	 * setter de la position d'un placable
	 * @param position
	 */
	public void setPosition(Position position) {
		this.position = position;
	}


	/**
	 * Vérifie si deux placables sont adjacents.
	 * @param pPlacable dont l'adjacence est à vérifier.
	 * @return vrai si les deux placables sont adjacents.
	 */
	public boolean estAdjacent(Placable pPlacable){
		return this.getPosition().estAdjacente(pPlacable.getPosition());
	}

	/**
	 * Retourne l'id de l'objet placable.
	 * @return id du placable.
	 */
	public int getId(){
		return this.id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public boolean equals(Object o){
		if(o instanceof Placable){
			return this.id == ((Placable) o).id;
		}

		return false;
	}
}
