import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

public class Controleur {
	
	int trashVar = 0;
	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
		 
		
		Modele p = new Modele("./BDDtest2.xml");
		p.enregistrer();
		Controleur c = new Controleur();
		Vue v = new Vue(p.game, c, p);
		SwingUtilities.invokeLater(() -> {
			try {
				Vue.createAndShowGUI();
			} catch (StreamReadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DatabindException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	public void SetGamePage(Record[] r, int gameIndex, Modele m) {	
		
		
		Record[] t = null ;
		//System.out.println(gameIndex + " a ");
		
		if(Vue.pageIndexBackup == 2 || Vue.pageIndexBackup == 0) {
			t = Vue.m.backupGame;
		}else {
			t=r;
		}
		
		Vue.title.setText(t[gameIndex].getGameTitle());
		Vue.title.setFont(new Font("Arial", Font.PLAIN, 48));
		while(checkTextIfCutOffWidthJLabel(Vue.title)) {
			Vue.title.setFont(new Font("Arial", Font.PLAIN, Vue.title.getFont().getSize() - 1));
		}
		Vue.date.setText(t[gameIndex].getGameReleaseDate());
		Vue.author.setText(t[gameIndex].getGameDeveloper());
		Vue.lDescGame.setText(t[gameIndex].getReviewText());
		Vue.taGenre.setText(t[gameIndex].getGenre());
		Vue.lNote.setText(t[gameIndex].getOverallMetascore() + " / 100");
		Vue.imagePanel.loadImage(t[gameIndex].getGamePoster());
		
		Vue.pGames.removeAll();
		
        JButton game =  Vue.cloneJButton(Vue.jbGameTab.get(m.getReco(gameIndex, t[gameIndex])[0]));
        game.setPreferredSize(new Dimension((int) ((Vue.windowSize.width/100)*10.4),(Vue.windowSize.height/100)*28));
        
        JButton game2 =  Vue.cloneJButton(Vue.jbGameTab.get(m.getReco(gameIndex, t[gameIndex])[1]));
        game2.setPreferredSize(new Dimension((int) ((Vue.windowSize.width/100)*10.4),(Vue.windowSize.height/100)*28));
        
        JButton game3 =   Vue.cloneJButton(Vue.jbGameTab.get(m.getReco(gameIndex, t[gameIndex])[2]));
        game3.setPreferredSize(new Dimension((int) ((Vue.windowSize.width/100)*10.4),(Vue.windowSize.height/100)*28));   
		
		Vue.pGames.add(game);
        Vue.pGames.add(game2);
        Vue.pGames.add(game3);
	
	}
	
	public void SetReviewHomePage(Record[] r, int gameIndex, int gameIndex2) {
		

		CutOffWidth(Vue.lReviewGameHome[gameIndex], gameIndex, gameIndex2, r);
		
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

        return textWidth > textAreaWidth - 15 ;
   }
	
	private static boolean checkTextIfCutOffWidthJLabel(JLabel label) {
		
        FontMetrics fontMetrics = label.getFontMetrics(label.getFont());
        String texte = label.getText();
        int textWidth = fontMetrics.stringWidth(texte); 
        
        int labelWidth = Vue.pTextGame.getWidth();

        return textWidth > labelWidth - 30 ; // 30
   }
	
   private static void CutOffWidth(JTextArea textArea, int gameIndex, int gameIndex2, Record[] r) {
		textArea.setText("");
		int index=0;
		
		while(checkTextIfCutOffWidthTextArea(textArea) == false && index < r[gameIndex2].getReviewText().length()-1) {
			textArea.setText(textArea.getText() + r[gameIndex2].getReviewText().charAt(index));
			index = index+1;
		}
		index=index-1;
		
		textArea.setText(r[gameIndex2].getReviewText());
		int subIndex = 1;
		for(int i=1;i<r[gameIndex2].getReviewText().length();i++) {
			if(i%index==0) {
				textArea.setText(textArea.getText().substring(0, index*subIndex) + '\n' +  textArea.getText().substring(index*subIndex));
				subIndex=subIndex+1;
			}
		}
   }
   
   public void RefreshGame() throws StreamReadException, DatabindException, IOException {
       // PANEL LIST JEUX (BODY HOME)
	   
	   Vue.jbGameTab.clear();
	   Vue.pBodyGame.removeAll();
	   Vue.pBodyGame.setPreferredSize(new Dimension(Vue.windowSize.width,(Vue.windowSize.height/100)*95));
	   
       for(int i=0; i<Vue.r.length;i++) {
       	try {
				URL imageURL = new URL(Vue.r[i].getGamePoster());
				ImageIcon icon = new ImageIcon(imageURL);
				
	        	JButton jLGame = new JButton(icon);
	        	jLGame.setPreferredSize(new Dimension((int) ((Vue.windowSize.width/100)*10.42),(Vue.windowSize.height/100)*28));

	        	Vue.jbGameTab.add(jLGame);
	        	Vue.pBodyGame.add(jLGame);
	        	
           	
           	if(Vue.jbGameTab.size() >= (Vue.maxXGame* Vue.maxYGame) && Vue.jbGameTab.size()%Vue.maxXGame == 0) {
           		Vue.pBodyGame.setPreferredSize(new Dimension((int) ((Vue.windowSize.width/100)*66.7),Vue.pBodyGame.getPreferredSize().height + (int) ((Vue.windowSize.height/100)*28)));
       		}
           	
           	if(trashVar == 0) {
           		Vue.jbGameTabBackup.add(jLGame);
           	}
	        	
			} catch (IOException e) {
				e.printStackTrace();
			}  	
       }
       
   	   trashVar=1;
       
   }
   
   public void setButtonClickable(JButton button, int index) {
	   
	   button.addMouseListener(new MouseAdapter() {
   		
           public void mouseClicked(MouseEvent e)
           {
        	   
           	Vue.pBodyGamePage.setVisible(true);
           	Vue.pFootGamePage.setVisible(true);
           	
           	Vue.pBodyGame.setVisible(false);
           	Vue.pFootGame.setVisible(false);
           	
           	Vue.pBodyHome.setVisible(false);
           	Vue.pFootHome.setVisible(false);
           	
           	Vue.pBodyFav.setVisible(false);
           	Vue.pFootFav.setVisible(false);
           	
           	Vue.spTab[0].setVisible(false);
           	Vue.spTab[1].setVisible(false);
           	

           	Vue.gameIndex = index;

           	Vue.c.SetGamePage(Vue.r, Vue.gameIndex, Vue.m);
           }
       });
   }
   
   public void ApplyButton() throws StreamReadException, DatabindException, IOException {
       	Vue.r = Vue.m.game;
		Vue.c.RefreshGame();
		
		for(int i=0;i<Vue.jbGameTab.size();i++) {
			final int index = i;
			System.out.println(i);
 
			Vue.c.setButtonClickable(Vue.jbGameTab.get(i), index);
       	
       }
	
       Vue.pBodyGamePage.setVisible(false);
       Vue.pFootGamePage.setVisible(false);
       Vue.pBodyGame.setVisible(false);
       Vue.pFootGame.setVisible(false);
       Vue.pBodyHome.setVisible(false);
       Vue.pFootHome.setVisible(false);
   	   Vue.spTab[0].setVisible(false);
   	   Vue.spTab[1].setVisible(false);
   	   Vue.pBodyGame.setVisible(true);
   	   Vue.pFootGame.setVisible(true);
   	   Vue.spTab[0].setVisible(true);
   }
 
	
}
	
	

