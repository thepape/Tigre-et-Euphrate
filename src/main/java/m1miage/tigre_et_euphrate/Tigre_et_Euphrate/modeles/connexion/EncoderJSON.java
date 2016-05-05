package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.connexion;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.map.ObjectMapper;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.chefs.Chef;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCatastrophe;
import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.tuiles.TuileCivilisation;

public class EncoderJSON {

	/**
	 * Partie 
	 */
	private Partie partie;

	/**
	 * Constructeur vide de EncoderJSON
	 */
	public EncoderJSON()
	{

	}

	/**
	 * Constructeur de EncoderJSON
	 * @param partie
	 */
	public EncoderJSON(Partie partie) {
		super();
		this.partie = partie;
	}

	/**
	 * Methode permettant de convertir vers le Json
	 * @return
	 * @throws IOException
	 */
	public File convertToJSON() throws IOException
	{

		File file = new File("partieEnCours.json");
		if(file.exists())
		{
			file.delete();
		}

		ObjectMapper mapper = new ObjectMapper();

		mapper.writeValue(new File("partieEnCours.json"), partie);

		return file;
	}

	/**
	 * Methode permettant de convertir la partie en Json
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public Partie convertToPartie(File file) throws IOException
	{
		Partie partie = null;
		ObjectMapper mapper = new ObjectMapper();
		partie = mapper.readValue(file, Partie.class);

		return partie;
	}

}
