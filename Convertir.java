package securiteL3;

import java.util.ArrayList;

public class Convertir {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// Lecture des parametres
			String file =args[0];
			//lecture du fichier texte	
			ArrayList<String> text=DiskTool.filetoArray(file);
			DiskTool.ecrire(text,file);
		}
		catch (Exception e){
			System.err.println("Erreur dans les arguments");
		}

	}

}
