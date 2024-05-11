import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.SwingUtilities;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

public class Controleur {
	
	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
		
		Modele p = new Modele("./BDDtest.xml");
		p.enregistrer();
		Controleur c = new Controleur();
		Vue v = new Vue(p.game, c);
		SwingUtilities.invokeLater(Vue::createAndShowGUI);
	}
	
	public void SetGamePage(Record[] r, int gameIndex) {	
		Vue.title.setText(r[gameIndex].getGameTitle());
		Vue.date.setText(r[gameIndex].getGameReleaseDate());
		Vue.author.setText(r[gameIndex].getGameDeveloper());
		Vue.lDescGame.setText(r[gameIndex].getReviewText());
		Vue.taGenre.setText(r[gameIndex].getGenre());
		Vue.lNote.setText(r[gameIndex].getOverallMetascore() + " / 100");
		Vue.imagePanel.loadImage(r[gameIndex].getGamePoster());
	
	}
	
	public void SetReviewHomePage(Record[] r, int gameIndex) {
		Vue.lReviewGameHome[gameIndex].setText(r[gameIndex].getReviewText());
	}
	
	
}
