package securiteL3;

public interface Cipher {
	public char chiffrer(char c);
	public char dechiffrer(char c);
	public String chiffrer(String s);	
	public String dechiffrer(String s);
	public String run(char mode);
	final char  [] alphabet= {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	
}
