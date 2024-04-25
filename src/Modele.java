import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


public class Modele {
	
	public ArrayList<String> jeux;
	File fichier = new File("output.xml");
	
	public Modele() {
		
		/*XMLDecoder decoder = null;
        try {
            FileInputStream fis = new FileInputStream(fichier);
            decoder = new XMLDecoder(fis);

            this.jeux = (ArrayList<Record>) decoder.readObject();

            fis.close();
        } catch (Exception e) {
            throw new RuntimeException("Impossible de lire les données");
        } finally {
            if (decoder != null) decoder.close();
        }*/
		this.jeux = new ArrayList<>();
		this.jeux.add("un");
		this.jeux.add("deux");
		this.jeux.add("trois");
		
		
	}
	
	public void enregistrer () {
		XMLEncoder encoder = null;
        try {
        	FileOutputStream fos = new FileOutputStream(fichier);
			ObjectOutputStream bos = new ObjectOutputStream(fos);
            encoder = new XMLEncoder(bos);
            encoder.writeObject(this.jeux);
            encoder.flush();
            encoder.close();
            bos.close();
            fos.close();
        } catch (final java.io.IOException e) {
            throw new RuntimeException("écriture impossible");
        } finally {
        	System.out.println("Terminé");
        }
	}

	public static void main(String[] args) {
		Modele p = new Modele();
		System.out.println(p.jeux);
		p.enregistrer();
		
	}
	
	
	public ArrayList<String> recherche(String o) {		
		Record record = new Record();
		ArrayList<String> ret = new ArrayList<String>();
		
		if (record.getA().contains(o)) {
			 ret.add(record.getA());
		}
		return ret;
		
	}
	
	/*public ArrayList<String> croissant(){
		Record record = new Record();
		ArrayList<String> ret = new ArrayList<String>();
		
		Arrays.sort(ret.add(record.getH()));
	}*/
	
	/*public ArrayList<String> decroissant(){
	Record record = new Record();
	ArrayList<String> ret = new ArrayList<String>();
	
	Arrays.sort(ret.add(record.getH()),Comparator.reverseOrder());
}*/
	
	/*public ArrayList<String> genre(){
	Record record = new Record();
	ArrayList<String> ret = new ArrayList<String>();
	
	Arrays.sort(ret.add(record.getH()));
	*/
	
	
	

}
