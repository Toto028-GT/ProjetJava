import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class TestJackson {

	
	public static void main(String[] args) throws JsonProcessingException {
	
		// Create an instance of the Java class you want to serialize
        Person person = new Person();
        person.setName("John");
        person.setAge(30);

        // Create an XmlMapper
        XmlMapper xmlMapper = new XmlMapper();

        // Serialize the object to XML
        String xml = xmlMapper.writeValueAsString(person);

        System.out.println(xml);
		
        
        /* ------------------------------------------------*/
        
        String s = "<person age=\"30\"><name>John</name></person>";


        // Deserialize XML into a Java object
        Person p = xmlMapper.readValue(s, Person.class);

        System.out.println("Name: " + p.getName());
        System.out.println("Age: " + p.getAge());
        
	}
}
