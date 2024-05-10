import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.stream.XMLStreamReader;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class TestJackson {

	public static void main(String[] args) throws IOException {
	
		 	Record r1 = new Record();
	        r1.setGameTitle("Jeu");
			Record[] tabRecords = { r1, new Record(), new Record()};
	        
	        File fichier = new File("./BDDtest.xml");

	        // Create an XmlMapper
	        XmlMapper xmlMapper = new XmlMapper();

	        /*// Serialize the object to XML
	        String xml = xmlMapper.writeValueAsString(tabRecords);
	        System.out.println(xml);
	        
			
	        
	        /* ------------------------------------------------*/
	        
	        /*String s = "<person age=\"30\"><name>John</name></person>";*/

	   
	        // Deserialize XML into a Java object
	        Record[] tlp = xmlMapper.readValue(fichier, Record[].class);

	        System.out.println(Arrays.toString(tlp));
	        System.out.println(tlp.length);
	}
}
