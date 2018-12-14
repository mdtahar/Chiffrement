package securiteL3;

public class Dechiffre {

	public static void main(String[] args) {
		try {
			// Lecture des parametres
			char type = args[0].charAt(0);
			String cle = args[1];
			String file =args[2];
			//lecture du fichier texte	
			String text=DiskTool.lire(file);
			exec(type,text,cle);
		}
		catch (Exception e){
			System.err.println("Erreur dans les arguments");
		}
	}
	
	public static void exec(char t,String txt,String key){
		switch (t) {
			case 'c':
				int cleI = Integer.parseInt(key);
				Cesar ces = new Cesar(-cleI,txt);
				System.out.print(ces.run('D'));
				break;
			
			case 'p':
				Permutation perm = new Permutation(key, txt);
				System.out.print(perm.run('D'));
				break;
			case 'v':
				Vignere vig = new Vignere(key,txt);
				System.out.print(vig.run('D'));
				break;

			default:
				System.err.println("Type de Dechiffrement exite pas choisir c (Cesar) ou p (Permutation) ou v (Vignere) svp");
				break;
		}
		
	}
}
