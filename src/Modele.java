import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.xml.stream.XMLStreamReader;


public class Modele {
	
	public Record[] game;
	public File fichier;
	public XmlMapper xmlMapper = new XmlMapper();
	
	public Modele(String file) {
		this.fichier=new File(file);
		
	}
	
	public void sauvgarder (Record[] game) throws StreamReadException, DatabindException, IOException {
		
		 // Serialize the object to XML
        String xml = xmlMapper.writeValueAsString(game);
        System.out.println(xml);
	}
	
	public void enregistrer () throws StreamReadException, DatabindException, IOException {
		
        // Deserialize XML into a Java object
        Record[] tlp = xmlMapper.readValue(fichier, Record[].class);

        this.game=tlp;
	}
	
	public String toString() {
		String txt="[ -";
		for(int i=0; i<game.length; i++) {
			txt+=" "+game[i].toString()+" -";
		}
		return txt +" ]"; 
	}

	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
	}

}
