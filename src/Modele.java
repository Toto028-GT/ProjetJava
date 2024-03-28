import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;



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

}
