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
import java.util.Set;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.xml.stream.XMLStreamReader;


public class Modele {
	
	public ArrayList<String> genreGame = new ArrayList<String>();
	public ArrayList<String> devGame = new ArrayList<String>();
	public ArrayList<String> platformGame = new ArrayList<String>();
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
        this.getListGenre();
	}
	
	public void recupBackup () throws StreamReadException, DatabindException, IOException {
		
        // Deserialize XML into a Java object
        Record[] tlp = xmlMapper.readValue(fichier, Record[].class);

        this.backupGame=tlp;
	}
	
	public void getListGenre() {
		for(int i=0; i<this.game.length; i++) {
			String[] str1 = this.game[i].getGenre().split(":");
	        String[] genre = str1[1].split(",");
	        
	        for(int y=0; y<genre.length; y++) {
				if (!(this.genreGame.contains(genre[y]))) {
					this.genreGame.add(genre[y]);
				}
	        }
	        
	        String[] platform = this.game[i].getPlatforms().split(",");
	        
	        for(int y=0; y<platform.length; y++) {
				if (!(this.platformGame.contains(platform[y]))) {
					this.platformGame.add(platform[y]);
				}
	        }
	        
			if (!(this.devGame.contains(this.game[i].getGameDeveloper()))) {
				this.devGame.add(this.game[i].getGameDeveloper());
			}
	        
		}
	}
	
	//################################## METHODE DE SPECIAL ##############################################################
	
	public int[] getReco(int indexA,Record gameA) {
		int[] comun = new int[this.backupGame.length];
		
		
		String[] str1 = gameA.getGenre().split(":");
        String[] genreGameA = str1[1].split(",");
		
		for(int i=0; i<this.backupGame.length; i++) {
			if(i == indexA) {
				comun[i] = -1;
			}
			else {
				String[] str2 = this.backupGame[i].getGenre().split(":");
		        String[] genreJeux = str2[1].split(",");
		        
		        int x = this.comparerTableaux(genreGameA, genreJeux);
		        comun[i] = x;
			}
		}
		
		int[] indicesMax = new int[3];

        for (int i = 0; i < 3; i++) {
            indicesMax[i] = indiceMax(comun);
            comun[indicesMax[i]] = Integer.MIN_VALUE; // Mettre à une valeur très basse pour ne pas le réutiliser
        }

        return indicesMax;
		
	}
	
	public int[] fiveBest() throws StreamReadException, DatabindException, IOException {
		this.sortByScore(this.backupGame);
		Record[] bestGame = new Record[5];

		for(int i=0; i<bestGame.length; i++) {
			bestGame[i] = this.backupGame[i];
		}
		
		this.recupBackup();
		
		int[] tabIndex = new int[5];
		
		for(int i=0; i<bestGame.length; i++) {
			for(int y=0; y<this.backupGame.length; y++) {
				if(bestGame[i].getGameTitle().equals(this.backupGame[y].getGameTitle())) {
					tabIndex[i] = y ;
				}
			}
		}
		
		this.recupBackup();
	
		return tabIndex;
	}
	
	public ArrayList<String> getGenre() {
		return this.genreGame;
	}
	
	public ArrayList<String> getDev() {
		return this.devGame;
	}
	
	public ArrayList<String> getPlatform() {
		return this.platformGame;
	}
	
	//################################## METHODE DE TRIE ##############################################################
	public Record[] findGame(String str) {
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
		
		return this.game;
	}
	
	public void sortByScore(Record[] lst) throws StreamReadException, DatabindException, IOException {
		Arrays.sort(lst);
		this.recupBackup();
	}
	
	public void sortByGenre(String genreA) {
		List<Record> newGame = new ArrayList<>();
		
		if(genreA != "Aucun") {
			for(int i=0; i<this.game.length; i++) {
				int find = this.game[i].getGenre().toUpperCase().indexOf(genreA.toUpperCase());
				if (find != -1 ) {
					newGame.add(this.game[i]);
				}
			}
			Record[] arr = newGame.toArray(new Record[newGame.size()]);
			
			this.game = arr;
		}
	}
	
	public void sortByPlatform(String PlatformA) {
		List<Record> newGame = new ArrayList<>();
		
		if(PlatformA != "Aucun")
		{
			for(int i=0; i<this.game.length; i++) {
				int find = this.game[i].getPlatforms().toUpperCase().indexOf(PlatformA.toUpperCase());
				if (find != -1 ) {
					newGame.add(this.game[i]);
				}
			}
			Record[] arr = newGame.toArray(new Record[newGame.size()]);
			this.game = arr;
		}
	}
	
	public void sortByDEV(String devA) {
		List<Record> newGame = new ArrayList<>();
		
		if(devA != "Aucun") {
			for(int i=0; i<this.game.length; i++) {
				int find = this.game[i].getGameDeveloper().toUpperCase().indexOf(devA.toUpperCase());
				if (find != -1 ) {
					newGame.add(this.game[i]);
				}
			}
			Record[] arr = newGame.toArray(new Record[newGame.size()]);
			
			this.game = arr;
		}
	
	}
	
	//################################## METHODE AUXILIAIRE ##############################################################
	
	public static int indiceMax(int[] tableau) {
        int indiceMax = -1;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < tableau.length; i++) {
            if (tableau[i] > max) {
                max = tableau[i];
                indiceMax = i;
            }
        }

        return indiceMax;
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
	
	public String toString() {
		String txt="[ -";
		for(int i=0; i<this.game.length; i++) {
			txt+=" "+this.game[i].toString()+" -";
		}
		return txt +" ]"; 
	}

	//#########################################################################################################################
	
	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
		Modele p = new Modele("./BDDtest2.xml");
		p.enregistrer();
		/*System.out.println(p.toString());
		
		System.out.println(p.getGenre().get(0));
		
		System.out.println(p.getDev());
		
		System.out.println(p.getPlatform());*/
	
		//p.findGame("Assassin's");
		//System.out.println(p.toString());
		//p.sortByPlatform("PlayStation4");
		//System.out.println(p.toString());
		
		
		p.sortByScore(p.game);
		
		//p.sortByDEV("Ubisoft");
		//System.out.println(p.toString());
		
		
		//p.findGame("Assassin's");
		//System.out.println(p.toString());
		
		//p.sortByGenre("Historic");
		//System.out.println(p.toString());
		
	
		
		
		
		/*int[] test = new int[5];
		test=p.fiveBest();
		for(int i=0; i<test.length; i++) {
			System.out.println(test[i]);
		}
		System.out.println(p.toString());
		
		p.getListGenre();
		
		int[] x = p.getReco(3,p.game[3]);
		System.out.println("----------------------");
		for (int j : x) {
		      System.out.println(j);
		    }*/
		
		
	}

}
