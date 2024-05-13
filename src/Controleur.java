import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.tools.FileObject;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

public class Controleur {
	
	int trashVar = 0;
	boolean isSortByNote = false;
	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
		 
		
		Modele p = new Modele("./BDDtest2.xml");
		p.enregistrer();
		Controleur c = new Controleur();
		Vue v = new Vue(p.game, c, p);
		SwingUtilities.invokeLater(() -> {
			try {
				Vue.createAndShowGUI();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	
	public void SetGamePage(Record[] r, int gameIndex, Modele m) throws StreamReadException, DatabindException, IOException {	
		System.out.println(gameIndex);
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
		
		Vue.pGames.removeAll();
		
		System.out.println(m.getReco(gameIndex, r[gameIndex]).length + " a");
		
		System.out.println(Vue.m.backupGame.length + " b");
		
		for(int i=0;i <m.getReco(gameIndex, r[gameIndex]).length;i++){
			System.out.println(m.getReco(gameIndex, Vue.m.backupGame[gameIndex])[i] +  " c");
			System.out.println(Vue.m.backupGame[m.getReco(gameIndex, Vue.m.backupGame[gameIndex])[i]] + " d");
		}
		
		System.out.println(Vue.jbGameTab.size() + " e");
		
		JButton game = new JButton();
		JButton game2 = new JButton();
		JButton game3 = new JButton();
		
		/*if(Vue.pageIndexBackup == 1) {
			game  =  Vue.cloneJButton(Vue.jbGameTab.get(m.getReco(gameIndex, r[gameIndex])[0]));    
	        game2 =  Vue.cloneJButton(Vue.jbGameTab.get(m.getReco(gameIndex, r[gameIndex])[1]));        
	        game3 =   Vue.cloneJButton(Vue.jbGameTab.get(m.getReco(gameIndex, r[gameIndex])[2]));
		}else {
	        game  =  Vue.cloneJButton(Vue.jbGameTabBackup.get(m.getReco(gameIndex, Vue.m.backupGame[gameIndex])[0]));    
	        game2 =  Vue.cloneJButton(Vue.jbGameTabBackup.get(m.getReco(gameIndex, Vue.m.backupGame[gameIndex])[1]));        
	        game3 =   Vue.cloneJButton(Vue.jbGameTabBackup.get(m.getReco(gameIndex, Vue.m.backupGame[gameIndex])[2]));
		}*/
		

        game  =  Vue.cloneJButton(Vue.jbGameTabBackup.get(m.getReco(gameIndex, Vue.m.backupGame[gameIndex])[0]));    
        game2 =  Vue.cloneJButton(Vue.jbGameTabBackup.get(m.getReco(gameIndex, Vue.m.backupGame[gameIndex])[1]));        
        game3 =   Vue.cloneJButton(Vue.jbGameTabBackup.get(m.getReco(gameIndex, Vue.m.backupGame[gameIndex])[2]));
        
		game.setPreferredSize(new Dimension((int) ((Vue.windowSize.width/100)*10.4),(Vue.windowSize.height/100)*28));
		game2.setPreferredSize(new Dimension((int) ((Vue.windowSize.width/100)*10.4),(Vue.windowSize.height/100)*28));
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
   
   public void RefreshGame() {
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
        	   
        	//System.out.println(index);
        	   
           	Vue.pBodyGamePage.setVisible(true);
           	Vue.pFootGamePage.setVisible(true);
           	
           	Vue.pBodyGame.setVisible(false);
           	Vue.pHeadGame.setVisible(false);
           	
           	Vue.pBodyHome.setVisible(false);
           	Vue.pFootHome.setVisible(false);
           	
           	Vue.pBodyFav.setVisible(false);
           	Vue.pFootFav.setVisible(false);
           	
           	Vue.spTab[0].setVisible(false);
           	Vue.spTab[1].setVisible(false); 	

           	Vue.gameIndex = index;

           	try {
				Vue.c.SetGamePage(Vue.r, Vue.gameIndex, Vue.m);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
           }
       });
   }
   
   public void ApplyButton() throws StreamReadException, DatabindException, IOException {
       	Vue.r = Vue.m.game;
		Vue.c.RefreshGame();
		
		//System.out.println("a");
		
		for(int i=0;i<Vue.jbGameTab.size();i++) {
			final int index = i;
			Vue.c.setButtonClickable(Vue.jbGameTab.get(i), index);
       }
	
       Vue.pBodyGamePage.setVisible(false);
       Vue.pFootGamePage.setVisible(false);
       Vue.pBodyGame.setVisible(false);
       Vue.pHeadGame.setVisible(false);
       Vue.pBodyHome.setVisible(false);
       Vue.pFootHome.setVisible(false);
   	   Vue.spTab[0].setVisible(false);
   	   Vue.spTab[1].setVisible(false);
   	   Vue.pBodyGame.setVisible(true);
   	   Vue.pHeadGame.setVisible(true);
   	   Vue.spTab[0].setVisible(true);
   }
 
   public void addFiltreListener() {
       Vue.cbCheckBox.addItemListener((ItemListener) new ItemListener() {
           public void itemStateChanged(ItemEvent e) {
               if (e.getStateChange() == ItemEvent.SELECTED) {
            	   isSortByNote = true;
               }else {
            	   isSortByNote = false;
               }
           }
       });
       
       Vue.bValider.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
        	   try {
				Vue.m.enregistrer();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        	   String selectedOption1 = (String) Vue.jbBoxGenre.getSelectedItem();
        	   String selectedOption2 = (String) Vue.jbBoxPlatform.getSelectedItem();
        	   String selectedOption3 = (String) Vue.jbBoxDev.getSelectedItem();
        	   if(isSortByNote) {
            	   try {
					Vue.m.sortByScore(Vue.m.game);
				} catch (IOException e1) {
					e1.printStackTrace();
				}   
            	   
        	   }
        	   Vue.m.sortByGenre(selectedOption1);
        	   Vue.m.sortByPlatform(selectedOption2);
        	   Vue.m.sortByDEV(selectedOption3);
        	   presetActionListener();
           }
       });
       
	   
       Vue.bReset.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
        	   try {
				Vue.m.enregistrer();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        	   Vue.jbBoxGenre.setSelectedIndex(0);
        	   Vue.jbBoxPlatform.setSelectedIndex(0);
        	   Vue.jbBoxDev.setSelectedIndex(0);
        	   Vue.cbCheckBox.setSelected(false);
        	   presetActionListener();
        	
           }
       });
   }
   
   public void presetActionListener() {
       trashVar = 0;
       try {
		RefreshGame();
		ApplyButton();
	} catch (IOException e1) {

		e1.printStackTrace();
	}
       Vue.pBodyGame.setVisible(false);
  	   Vue.pBodyGame.setVisible(true);
   }
   
	
}
	
	

