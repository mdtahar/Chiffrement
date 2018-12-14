package securiteL3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;

public class Cesar implements Cipher{
	private int cle;
    private String text;
    
    public Cesar(int chiff, String txt) {
    	this.cle = chiff;
    	this.text = txt;
    }
    /**
     * l'indice de la lettre dans l'alphabet
     * @param l 
     * @return indice 
     */
    public int getLettre(char l){
    	for (int i = 0; i < this.alphabet.length; i++) {
    		if (this.alphabet[i] == l) return i;
    	}
    	return -1;
    }
    /**
     * mise a jour de la position en appliquent la cle de chiffrement 
     * @param i l'indice courant
     * @return indice aprés l'update
     */

    public int update(int i){
    	int indice = i;
    	if(indice > -1){
    		int j = 0;
    		while(j<Math.abs(this.cle)){
    			if (this.cle<0){
    				if (indice-1 == -1)
    					indice = 25;
    				else
    					indice --;
    			}
    			else{
    				if (indice >= 25)
    					indice = 0;
    				else 
    					indice++;
    			}
    			j++;
    		}
    	}
    	return indice;
    }
    /**
     * chiffrer un caractère
     */
    public char chiffrer(char c) {
    	int indice = update(getLettre(c));
    	if (indice == -1)
    		return ' ';
    	else
    		return alphabet[indice];
    }

    /**
     * chiffrer une chaine de caractères(text)
     */
    public String chiffrer(String txt){
    	char [] t1 = txt.toCharArray();
    	char [] t2 = new char[t1.length];
    	for (int i = 0; i< t1.length;i++){
    		if(t1[i]=='\n'){
    			t2[i]='\n';
    		}
    		else {
    			if((int)t1[i]>=97 && (int)t1[i]<=122){
    				t2[i] = chiffrer(t1[i]);
    			}
    			else{
    				t2[i]=t1[i];
    			}
    			
    		}
    	}
    	return new String(t2);
    }
    /**
     * dechiffrer un caractère
     */
    public char dechiffrer(char c) {
    	int indice = update(getLettre(c));
    	if (indice == -1)
    		return ' ';
    	else
    		return alphabet[indice];
    }

    /**
     * dechiffrer une chaine de caractères(text)
     */
    public String dechiffrer(String txt){
    	char [] t1 = txt.toCharArray();
    	char [] t2 = new char[t1.length];
    	for (int i = 0; i< t1.length;i++)
    	{
    		if(t1[i]=='\n'){
    			t2[i]='\n';
    		}
    		else{
    			if((int)t1[i]>=97 && (int)t1[i]<=122){
    				t2[i] = dechiffrer(t1[i]);
    			}
    			else{
    				t2[i]=t1[i];
    			}
    		}
    	}
    	
    	return new String(t2);
    }
    
    /**
     * decrypt en choisir le mode d
     * @param d
     * @param file
     * @param mots
     */
    public static void Decrypt(int d,String file,String mots ){
    	if(d==1){
    		String text = DiskTool.lire(file);
			String mot = DiskTool.normalizer(mots.toLowerCase());
			char mot_chiffre_array[] = mot.toCharArray();
			char mot_chiffre_array_bis[] = new char[mot_chiffre_array.length];
			int cle = 0;
			boolean trouver = false;

			for (int i = 0; i < 26; i++) {
				for (int j = 0; j < mot_chiffre_array.length; j++) {
					mot_chiffre_array_bis[j] = (char) ((((mot_chiffre_array[j] - 97) + i) % 26) + 97);
				}
				String mot_chiffre = new String(mot_chiffre_array_bis);
				for (int j = 0; j < text.length() - mots.length(); j++) {
					if (mot_chiffre.equals(text.substring(j, mot.length() + j))) {
						cle = i;
						j = j + mot.length();
						trouver = true;
						break;
					}
				}
				if (trouver == true) {
					break;
				}

			}
			Cesar ces = new Cesar(-cle,text);
    		System.out.print(ces.run('D'));
    		
    	}
    	if(d==2){
    		ArrayList<String> t = DiskTool.filetoArray(file);
    		String text="";
    		Hashtable<Character, Integer> table = new Hashtable<Character, Integer>();
    		for (String ligne : t) {
    			ligne+='\n';
    			for (int i = 0; i < ligne.length(); i++) {
    				if ((int)ligne.charAt(i) >= 97  && (int)ligne.charAt(i) <= 122){
    					if(table.containsKey(ligne.charAt(i))){
    						table.put(ligne.charAt(i), table.get(ligne.charAt(i)) + 1 );
    					}
    					else
    						table.put(ligne.charAt(i), 1);
    				}
    			}
    			text+=ligne;
    		}
    		Iterator<Character> key = table.keySet().iterator();
    		Iterator<Integer> val = table.values().iterator();
						
    		int max = Collections.max(table.values());
    		char e =' ';
    		while(key.hasNext()){
    			char k=(Character)key.next();
    			if ((Integer)val.next()==max){
    				e = k;
    			}
    		}
    		int cle = (int)e - (int)'e';
    		Cesar ces = new Cesar(-cle,text);
    		System.out.print(ces.run('D'));
    	}
    	if(d==3){
    		ArrayList<String> text = DiskTool.filetoArray(file);
			String ligne = text.get(0)+"\n";
			int cpt = 0;
			String mot ="";
			for (int i = 0; i < ligne.length(); i++) {
				mot+=ligne.charAt(i);
				if ((int)ligne.charAt(i)==32){
					cpt++;
					if (cpt == 10)
						break;
				}
			}
			ArrayList<String> dictionnaire = DiskTool.filetoArray("dic");
			int trouver = 0;
			int cle=0;
			String[] tab;
			for (int i = 0; i<25; i++){
				trouver = 0;
				Cesar ces = new Cesar(i,mot);
				String lligne = ces.run('D');
				tab = lligne.split("\\ ");

				for (int j = 0; j < tab.length-1; j++) {
					if (dictionnaire.contains(tab[j])){
						trouver++;
					}
				}
				if (trouver>=5){
					cle = i;
					break;
				}
			}
			
			String cryp="";
			for (String l : text) {
				l+='\n';
				cryp +=l;
			}
			Cesar cesa = new Cesar(cle,cryp);
			System.out.print(cesa.run('D'));
    		
    	}
    	
    	
    }
    /**
     * lance le chiffrement avec mode 'C' et dechiffrement avec le mode 'D'
     */
    public String run(char mode){
    	if(mode=='C'){
    		return this.chiffrer(this.text);
    	}
    	if(mode=='D'){
    		return this.dechiffrer(this.text);
    	}
    	return "";
    }

 
}
