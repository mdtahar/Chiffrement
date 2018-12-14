package securiteL3;



public class Vignere implements Cipher {

	private String cle;
	private String text;
	private static final double If = 0.074 ;
	private static final double Ia = 0.038 ;
	
	public Vignere(String chiff, String txt) {
		this.cle = chiff;
		this.text = txt;
	}

	public String dechiffrer(String s) {
		char arraykey[] = DiskTool.normalizer(cle).toCharArray();
		char arraytxt[] = s.toCharArray();
		char arraychiffrer[] = new char[arraytxt.length];
		int j = 0;
		for (int i = 0; i < arraytxt.length; i++) {
			if ((int) arraytxt[i] >= 97 && (int) arraytxt[i] <= 122) {
				arraychiffrer[i] = (char) (Math.abs((((getCharInt(arraytxt[i]) - getCharInt(arraykey[j% arraykey.length])) + 26) % 26)) + 97);
				j = (j + 1) % arraykey.length;
			} else {
				arraychiffrer[i] = arraytxt[i];
			}
		}
		return new String(arraychiffrer);
	}

	public String chiffrer(String txt) {
		char arraykey[] = DiskTool.normalizer(cle).toCharArray();
		char arraytxt[] = txt.toCharArray();
		char arraychiffrer[] = new char[arraytxt.length];
		int j = 0;

		for (int i = 0; i < arraychiffrer.length; i++) {
			if ((int) arraytxt[i] >= 97 && (int) arraytxt[i] <= 122) {
				arraychiffrer[i] = (char) ((((getCharInt(arraytxt[i]) + getCharInt(arraykey[j% arraykey.length]))) % 26) + 97);
				j = (j + 1) % arraykey.length;
			} else {
				arraychiffrer[i] = arraytxt[i];
			}
		}
		return new String(arraychiffrer);
	}

	
	private static String dechiffrer(String s,String cle) {
//		char arraykey[] = DiskTool.normalizer(cle).toCharArray();
		char arraykey[] = cle.toCharArray();
		char arraytxt[] = s.toCharArray();
		char arraychiffrer[] = new char[arraytxt.length];
		int j = 0;
		for (int i = 0; i < arraytxt.length; i++) {
			if ((int) arraytxt[i] >= 97 && (int) arraytxt[i] <= 122) {
				arraychiffrer[i] = (char) (Math.abs((((getCharInt_bis(arraytxt[i]) - getCharInt_bis(arraykey[j% arraykey.length])) + 26) % 26)) + 97);
				j = (j + 1) % arraykey.length;
			} else {
				arraychiffrer[i] = arraytxt[i];
			}
		}
		return new String(arraychiffrer);
	}
	
	
	
	public int getCharInt(char a) {
		return (int) ((((int) a - 97)));
	}
	
	
	private static int getCharInt_bis(char a) {
		return (int) ((((int) a - 97)));
	}
	
	
	
	
	
	
	public static void Decrypt_With_Lenght_Key(String file, int taille) {
		String text = DiskTool.lire(file);
		int tout_les_frequ[][] = null;
		char resultat[] = new char[taille];
		int compteur_lettre = 0 ;
		
		for (int i = 0; i < taille + 1; i++) {
			tout_les_frequ = new int[i][26];
		}

		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) >= 97 && text.charAt(i) <= 122) {
				tout_les_frequ[compteur_lettre ][((int) (text.charAt(i)))-97] +=1 ;
				compteur_lettre  = (compteur_lettre+1)%taille;			
			}
		}

		for (int i = 0; i < taille; i++) {
			int plusgrand = 0 ;
			int indice = -1 ;
			for (int j = 0; j < tout_les_frequ[1].length; j++) {
				if(plusgrand < tout_les_frequ[i][j]){
					plusgrand = tout_les_frequ[i][j];
					indice = j ;
				}
			}
			
			resultat[i] = (char)((((int)'e'+indice)%26)+97-1) ;
		}
		
		String cle = new String(resultat);
//		System.out.println("la cle = "+cle);
		System.out.print(dechiffrer(text,cle));
		

	}
	
	public static void Decrypt_without_Lenght_Key(String fichier){
		String text = DiskTool.lire(fichier);
		double indice = calcul_indice_coïncidence(text);
		double taille_text = text.length();
		
		double length_key = taille_cle(indice, taille_text);
		
		//System.out.println(length_key);
		Decrypt_With_Lenght_Key(fichier, (int)length_key);
	}
	
	
	public static double calcul_indice_coïncidence(String text ){
		
		double nb_occurence[] = new double[26];
		double indice_coïncidence = 0 ;
		double n = 0;
	
		for (int i = 0; i < text.length(); i++) {
			if(text.charAt(i)>=97 && text.charAt(i)<=122){
				nb_occurence[text.charAt(i)-97] +=1 ;
				n=n+1 ;
			}		
		}
	
		for (int i = 0; i < nb_occurence.length; i++) {
			indice_coïncidence += ((nb_occurence[i])*(nb_occurence[i]-1))/((n)*(n-1));
		}
		return indice_coïncidence;
	}
	
	public static double taille_cle(double ind_coïncidence,double taille_text){
		double Ic = ind_coïncidence;
		double n = taille_text ;		
		double key = (((If -Ia)*n)/(((n-1)*Ic)-(n*Ia)+If));

		return (key) ;
	}

	/**
	 * lance le chiffrement avec mode 'C' et dechiffrement avec le mode 'D'
	 */
	public String run(char mode) {
		if (mode == 'C' || mode == 'c') {
			return this.chiffrer(this.text);
		}
		if (mode == 'D' || mode == 'd') {
			return this.dechiffrer(this.text);
		}
		return "";
	}

	public char chiffrer(char c) {
		// TODO Auto-generated method stub
		return 0;
	}

	public char dechiffrer(char c) {
		// TODO Auto-generated method stub
		return 0;
	}

	

	
}