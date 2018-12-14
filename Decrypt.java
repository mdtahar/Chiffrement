package securiteL3;

public class Decrypt {
	
	private static final int decalageAscii = 97;
	
	public static void main(String[] args) {
			
		try {
			char type = args[0].charAt(0);
			String f = args[1];
			if (type=='c'){
				int mode = Integer.parseInt(args[2]);
				if (mode==1){
					String mot = args[3];
					long startTime = System.currentTimeMillis();
					Cesar.Decrypt(1, f,mot);
					long endTime = System.currentTimeMillis();
					System.err.println("------------------------------------------------------");
					System.err.println("| Temps de decryptage par Mot de César "+(endTime-startTime)+" ms "  );
					System.err.println("------------------------------------------------------");
					
				}
				else if(mode == 2){
					long startTime = System.currentTimeMillis();
					Cesar.Decrypt(2, f,"");
					long endTime = System.currentTimeMillis();
					System.err.println("------------------------------------------------------");
					System.err.println("| Temps de decryptage par frequence César "+(endTime-startTime)+" ms ");
					System.err.println("------------------------------------------------------");
				}
				else if(mode == 3){
					long startTime = System.currentTimeMillis();
					Cesar.Decrypt(3, f,"");
					long endTime = System.currentTimeMillis();
					System.err.println("------------------------------------------------------");
					System.err.println("| Temps de decryptage par force brute César "+(endTime-startTime)+" ms "  );
					System.err.println("------------------------------------------------------");
				}
			}
			else if (type=='v'){
				if(args.length == 3){
					long startTime = System.currentTimeMillis();
					Vignere.Decrypt_With_Lenght_Key(f, Integer.parseInt(args[2]));
					long endTime = System.currentTimeMillis();
					System.err.println("------------------------------------------------------");
					System.err.println("| Temps de decryptage par taille de la clé de vigener "+ (endTime - startTime) + " ms ");
					System.err.println("------------------------------------------------------");
				}else{
					long startTime = System.currentTimeMillis();
					Vignere.Decrypt_without_Lenght_Key(f);
					long endTime = System.currentTimeMillis();
					System.err.println("------------------------------------------------------");
					System.err.println("| Temps de decryptage sans la taille clé de vigener "+ (endTime - startTime) + " ms ");
					System.err.println("------------------------------------------------------");
				}
				
				
			}
		
		}
		catch (Exception e) {
			System.err.println("Erreur argument");
			e.printStackTrace();
		}
			
		
	}
}

