import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;

public class Vue {

    public static void ShowPage(int pageIndex, JPanel[][] pTab) {
        for(int i=0;i<pTab.length;i++) {
        	for(int y=0;y<pTab[0].length;y++) {
        		if (i == pageIndex) {
        			pTab[i][y].setVisible(true);
        		}
        		else {
        			pTab[i][y].setVisible(false);
        		}
        	}
        }
	}
	
    private static void createAndShowGUI() {
    	FlatDarkLaf.setup();  
    	try {
    	    UIManager.setLookAndFeel( new FlatDarkLaf() );
    	} catch( Exception ex ) {
    	    System.err.println( "Failed to initialize LaF" );
    	}
    	
    	/* ----------------------------------------- */
    	
    	// BUTTON HOME (HEAD)
    	JButton nameB = new JButton("GAMEFLIX");
        nameB.setFont(new Font("Arial", Font.PLAIN, 34));
        nameB.setBorderPainted(false);
        nameB.setContentAreaFilled(false);
        
        // BUTTON JEUX (HEAD)
        JButton jvB = new JButton("JEUX");
        jvB.setFont(new Font("Arial", Font.PLAIN, 28));
        jvB.setBorderPainted(false);
        jvB.setContentAreaFilled(false);
        
        // BUTTON FAVORIS (HEAD)
        JButton favB = new JButton("FAVORIS");
        favB.setFont(new Font("Arial", Font.PLAIN, 28)); 
        favB.setBorderPainted(false);
        favB.setContentAreaFilled(false);
        
        // BUTTON TEST
        JButton myGameB = new JButton("MON JEU");
        myGameB.setFont(new Font("Arial", Font.PLAIN, 28)); 
        myGameB.setBorderPainted(false);
        myGameB.setContentAreaFilled(false);
        
        /* ----------------------------------------- */
    	
        // PANEL BODY HOME
        JPanel pBodyHome = new JPanel();
        pBodyHome.setBackground(Color.white);
        pBodyHome.add(new JLabel("BODY HOME"));
        pBodyHome.setPreferredSize(new Dimension(1280,720));
        
        // PANEL FOOT HOME
    	JPanel pFootHome = new JPanel();
        pFootHome.add(new JLabel("FOOT HOME"));
        pFootHome.setPreferredSize(new Dimension(1280,75));
        
        /* --------------------------------------- */
        
        // PANEL BODY JEUX
        JPanel pBodyGame = new JPanel();
    	pBodyGame.setVisible(false);
    	pBodyGame.setBackground(Color.white);
        pBodyGame.add(new JLabel("BODY JEUX"));
        pBodyGame.setPreferredSize(new Dimension(1280,720));
        
        // PANEL FOOT JEUX
        JPanel pFootGame = new JPanel();
    	pFootGame.setVisible(false);
        pFootGame.add(new JLabel("FOOT JEUX"));
        pFootGame.setPreferredSize(new Dimension(1280,75));
        
        /* --------------------------------------- */
        
        // PANEL BODY FAVORIS
        JPanel pBodyFav = new JPanel();
    	pBodyFav.setVisible(false);
    	pBodyFav.setBackground(Color.white);
        pBodyFav.add(new JLabel("BODY FAVORIS"));
        pBodyFav.setPreferredSize(new Dimension(1280,720));
        
        // PANEL FOOT FAVORIS
    	JPanel pFootFav = new JPanel();
    	pFootFav.setVisible(false);
        pFootFav.add(new JLabel("FOOT FAVORIS"));
        pFootFav.setPreferredSize(new Dimension(1280,75));
        
        /* --------------------------------------- */
        
     // PANEL BODY myGAME
        JPanel pBodyMyGame = new JPanel();
        pBodyMyGame.setVisible(false);
        pBodyMyGame.setBackground(Color.white);
        pBodyMyGame.add(new JLabel("BODY MY GAME"));
        pBodyMyGame.setPreferredSize(new Dimension(1280,720));
        
        // PANEL FOOT myGAME
    	JPanel pFootMyGame = new JPanel();
    	pFootMyGame.setVisible(false);
    	pFootMyGame.add(new JLabel("FOOT MY GAME"));
    	pFootMyGame.setPreferredSize(new Dimension(1280,75));
        
        /* --------------------------------------- */
        
        // PANEL BUTTON (HEAD)
        JPanel pButton = new JPanel();
        pButton.setLayout(new GridLayout(0,2));
        pButton.add(jvB);
        pButton.add(favB);
        pButton.add(myGameB);
        
        // PANEL NOM (HEAD)
        JPanel pName = new JPanel();
        pName.setLayout(new BorderLayout());
        pName.setPreferredSize(new Dimension(400,50));
        pName.add(nameB, BorderLayout.WEST);

        // PANEL HEAD
        JPanel pHead = new JPanel();
        pHead.setLayout(new BorderLayout());
        pHead.add(pName, BorderLayout.WEST);
        pHead.add(pButton, BorderLayout.CENTER);
        JPanel pfillerHead = new JPanel();
        pfillerHead.setPreferredSize(pName.getPreferredSize());
        pHead.add(pfillerHead, BorderLayout.EAST);
        pHead.setPreferredSize(new Dimension(1280,75));
        
        /* --------------------------------------- */
        
        // ACTION DE L'UTILISATEUR 
        
        JButton[] bHeadTab = {nameB,jvB,favB,myGameB};
        JPanel[][] pTab = { {pBodyHome,pFootHome} , {pBodyGame, pFootGame}, {pBodyFav,pFootFav}, {pBodyMyGame,pFootMyGame}};
        
        for(int i=0;i<bHeadTab.length;i++) {
        	int index = i;
        	bHeadTab[i].addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                	bHeadTab[index].setForeground(Color.WHITE);
                }
                public void mouseExited(MouseEvent e) {
                	bHeadTab[index].setForeground(UIManager.getColor("Button.foreground"));
                }
                public void mouseClicked(MouseEvent e) {
                	ShowPage(index,pTab);
                }
        	});
        }
        
        /* --------------------------------------- */
        
        // CONTAINER DE TOUTS LES PANELS
        JPanel container = new JPanel();
    	container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
    	container.add(pHead);
    	
        container.add(pBodyHome);
        container.add(pFootHome);
        
        container.add(pBodyGame);
        container.add(pFootGame);
        
        container.add(pBodyFav);
        container.add(pFootFav);
        
        container.add(pBodyMyGame);
        container.add(pFootMyGame);
        
        
    	// FRAME
        JFrame frame = new JFrame("GAMEFLIX");
        frame.add(container);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(Vue::createAndShowGUI);
    }
}
