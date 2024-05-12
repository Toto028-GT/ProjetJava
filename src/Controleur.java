import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

public class Controleur {
	
	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
		 
		
		Modele p = new Modele("./BDDtest2.xml");
		p.enregistrer();
		Controleur c = new Controleur();
		Vue v = new Vue(p.game, c);
		SwingUtilities.invokeLater(Vue::createAndShowGUI);
	}
	
	public void SetGamePage(Record[] r, int gameIndex) {	
		Vue.title.setText(r[gameIndex].getGameTitle());
		Vue.title.setFont(new Font("Arial", Font.PLAIN, 48));
		while(checkTextIfCutOffWidthJLabel(Vue.title)) {
			Vue.title.setFont(new Font("Arial", Font.PLAIN, Vue.title.getFont().getSize() - 1));
		}
		Vue.date.setText(r[gameIndex].getGameReleaseDate());
		Vue.author.setText(r[gameIndex].getGameDeveloper());
		Vue.lDescGame.setText(r[gameIndex].getReviewText());
		Vue.taGenre.setText(r[gameIndex].getGenre());
		Vue.lNote.setText(r[gameIndex].getOverallMetascore() + " / 100");
		Vue.imagePanel.loadImage(r[gameIndex].getGamePoster());
	
	}
	
	public void SetReviewHomePage(Record[] r, int gameIndex) {
		
		CutOffWidth(Vue.lReviewGameHome[gameIndex], gameIndex, r);
		
		FontMetrics fontMetrics = Vue.lReviewGameHome[gameIndex].getFontMetrics(Vue.lReviewGameHome[gameIndex].getFont());
		int textHeight = fontMetrics.getHeight()*Vue.lReviewGameHome[gameIndex].getLineCount();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize(); 
		double test =  ((double) textHeight / windowSize.height) * 100;
		test = test + 4;
		Vue.lReviewGameHome[gameIndex].setPreferredSize(new Dimension((int) ((windowSize.width/100)*18), ((windowSize.height/100)*(int) Math.round(test)  )));
		
	}
	
	private static boolean checkTextIfCutOffWidthTextArea(JTextArea textArea) {
		
        int textAreaWidth = (int) textArea.getPreferredSize().getWidth();
        FontMetrics fontMetrics = textArea.getFontMetrics(textArea.getFont());
        String texte = textArea.getText();
        int textWidth = fontMetrics.stringWidth(texte);        
        return textWidth > textAreaWidth - 10 ;
   }
	
	private static boolean checkTextIfCutOffWidthJLabel(JLabel label) {
		
        FontMetrics fontMetrics = label.getFontMetrics(label.getFont());
        String texte = label.getText();
        int textWidth = fontMetrics.stringWidth(texte);   
        
        int labelWidth = Vue.pTextGame.getWidth();

        return textWidth > labelWidth - 30; // 30
   }
	
   private static void CutOffWidth(JTextArea textArea, int gameIndex, Record[] r) {
		textArea.setText("");
		int index=0;
		
		while(checkTextIfCutOffWidthTextArea(textArea) == false) {
			textArea.setText(textArea.getText() + r[gameIndex].getReviewText().charAt(index));
			index = index+1;
		}
		index=index-1;
		
		textArea.setText(r[gameIndex].getReviewText());
		int subIndex = 1;
		for(int i=1;i<r[gameIndex].getReviewText().length();i++) {
			if(i%index==0) {
				textArea.setText(textArea.getText().substring(0, index*subIndex) + '\n' +  textArea.getText().substring(index*subIndex));
				subIndex=subIndex+1;
			}
		}
   }
 
	
}
	
	

