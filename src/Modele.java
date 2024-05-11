import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.xml.stream.XMLStreamReader;


public class Modele {
	
	public Map<String, List<Record>> genreGame = new HashMap<String,List<Record>>();
	public Record[] backupGame;
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
        this.backupGame=tlp;
	}
	
	public void getListGenre() {
		for(int i=0; i<this.game.length; i++) {
			String[] str2 = this.game[i].getGenre().split(":");
	        String[] words = str2[1].split(",");
	        
	        for(int y=0; y<words.length; y++) {
				if (this.genreGame.containsKey(words[y])) {
					List<Record> x = this.genreGame.get(words[y]);
					x.add(this.game[i]);
					this.genreGame.put(words[y], x );
				}
				else {
					List<Record> x = new ArrayList<>();
					x.add(this.game[i]);
					this.genreGame.put(words[y], x );
				}
	        }
		}
		System.out.println(this.genreGame);
	}
	
	public void getReco(Record gameA) {
		int[] comun = new int[this.game.length];
		
		String[] str1 = gameA.getGenre().split(":");
        String[] genreGameA = str1[1].split(",");
		
		for(int i=0; i<this.game.length; i++) {
			String[] str2 = this.game[i].getGenre().split(":");
	        String[] genreJeux = str2[1].split(",");
	        
		}
	}
	
	public int comparerTableaux(String[] tableau1, String[] tableau2) {
        int compteur = 0;

        for (int i = 0; i < tableau1.length; i++) {
            for (int j = 0; j < tableau2.length; j++) {
                if (tableau1[i].equals(tableau2[j])) {
                    compteur++;
            
                }
            }
        }
        return compteur;
    }
	
	public void findGame(String str) {
		List<Record> newGame = new ArrayList<>();
		this.game = this.backupGame;
		
		for(int i=0; i<this.game.length; i++) {
			int find = this.game[i].getGameTitle().toUpperCase().indexOf(str.toUpperCase());
			if (find != -1 ) {
				newGame.add(this.game[i]);
			}
		}
		Record[] arr = newGame.toArray(new Record[newGame.size()]);
		
		this.game = arr;
		
	}
	
	public void sortByScore() {
		this.game = this.backupGame;
		Arrays.sort(this.game);
	}
	
	public Record[] fiveBest() throws StreamReadException, DatabindException, IOException {
		this.sortByScore();
		Record[] bestGame = new Record[5];

		for(int i=0; i<bestGame.length; i++) {
			bestGame[i] = this.game[i];
		}	
		this.enregistrer();
	
		return bestGame;
	}
	
	public String toString() {
		String txt="[ -";
		for(int i=0; i<this.game.length; i++) {
			txt+=" "+this.game[i].toString()+" -";
		}
		return txt +" ]"; 
	}

	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
		Modele p = new Modele("./BDDtest2.xml");
		p.enregistrer();
		/*System.out.println(p.toString());
		p.findGame("Assassin's");
		System.out.println(p.toString());
		Record[] test = new Record[5];
		test=p.fiveBest();
		for(int i=0; i<test.length; i++) {
			System.out.println(test[i]);
		}
		System.out.println(p.toString());*/
		p.getListGenre();
		
		String str = "Genre(s):Arcade,Action,Shooter,Modern,fgsjuf,test";
        String[] str2 = str.split(":");
        String[] words = str2[1].split(",");
        for (String j : words) {
            System.out.println(j);
          }
		
		String strt = "Genre(s):Action,Shooter,First-Person,Modern,Arcade";
        String[] str2t = strt.split(":");
        String[] wordst = str2t[1].split(",");
        for (String j : wordst) {
            System.out.println(j);
          }
        
        
        System.out.println(p.comparerTableaux(words, wordst));
		
	}

}
