package securiteL3;

public class Permutation implements Cipher{
	private String text;
	private char [] perm;
	
	
	public Permutation(String chiff, String txt) {
		this.text = txt;
		this.perm = chiff.toCharArray();
	}
	
	/**
     * l'indice de la lettre l dans  la permutation
     * @param l 
     * @return indice 
     */
	private int getPerm(char l) {
		for (int i = 0; i < this.alphabet.length; i++) {
			if (this.perm[i] == l) return i;
		}
		return -1;
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
     * chiffrer un caractère
     */
    public char chiffrer(char l) {
    	int indice = getLettre(l);
    	if (indice == -1)
    		return ' ';
    	else
    		return perm[indice];
    }
    /**
     * chiffrer une chaine de caractères(text)
     */
    public String chiffrer(String txt){
    	char [] t1 = txt.toCharArray();
    	char [] t2 = new char[t1.length];
    	for (int i = 0; i< t2.length;i++){
    		if(t1[i]=='\n'){
    			t2[i]='\n';
    		}
    		else{
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
    public char dechiffrer(char l) {
    	int indice = getPerm(l);
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
    	for (int i = 0; i< t1.length;i++){
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
