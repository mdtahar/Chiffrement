package securiteL3;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.text.Normalizer;
public class DiskTool {
	public static String normalizer(String chaine) {
		String chaine_nor =  Normalizer.normalize(chaine, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
		return chaine_nor;
	}
	
	public static String lire(String file){
		String text="";
		try{
			InputStream inputStream=new FileInputStream(file); 
			InputStreamReader inputSReader=new InputStreamReader(inputStream);
			BufferedReader br=new BufferedReader(inputSReader);
			String ligne;
			while ((ligne=br.readLine())!=null){
				text+=normalizer(ligne).toLowerCase()+"\n";
			}
			br.close(); 
			
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		return text;
	}
	public static void ecrire(ArrayList<String> r,String file){
	
		try{
			File f = new File(file);
			FileWriter fr = new FileWriter(f);
			for (String s : r) {
				s+="\n";
				fr.write(s);
				
			}			
			fr.close();
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	
	}
	
	
	public static ArrayList<String> filetoArray(String file){
		ArrayList<String> res = new ArrayList<String>();
		try{
			InputStream inputStreamd=new FileInputStream(file); 
			InputStreamReader in=new InputStreamReader(inputStreamd);
			BufferedReader br=new BufferedReader(in);
			String ligne;
		
			
			while ((ligne=br.readLine())!=null){	
				res.add(normalizer(ligne).toLowerCase());
			}
			br.close();
		}
		catch (Exception e){
			System.out.println(e.toString());
		}
		return res;
	}
	
}
