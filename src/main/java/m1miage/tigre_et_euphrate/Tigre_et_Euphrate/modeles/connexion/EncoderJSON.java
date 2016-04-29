package m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.connexion;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import m1miage.tigre_et_euphrate.Tigre_et_Euphrate.modeles.Partie;

public class EncoderJSON {

	private Partie partie;

	public EncoderJSON(Partie partie) {
		super();
		this.partie = partie;
	}

	public File convertToJSON() throws IOException
	{
		File file = new File("partieEnCours.json");

		ObjectMapper mapper = new ObjectMapper();

		mapper.writeValue(new File("partieEnCours.json"), partie);

		return file;
	}

	public Partie convertToPartie(File file) throws IOException
	{
		Partie partie = null;
		ObjectMapper mapper = new ObjectMapper();

		partie = mapper.readValue(file, Partie.class);

		return partie;
	}

}
