import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.util.ArrayList;

import com.formdev.flatlaf.FlatDarkLaf;

class ImagePanel extends JPanel {
    private BufferedImage image;

    public ImagePanel(String imagePath) {
        try {
            // Charger l'image depuis le fichier
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dessiner l'image sur le JPanel
        super.paintComponent(g);
        if (image != null) {
            // Récupérer les dimensions de l'image et du panneau
            int imgWidth = image.getWidth();
            int imgHeight = image.getHeight();
            int panelWidth = getWidth();
            int panelHeight = getHeight();

            // Calculer le facteur d'échelle pour ajuster l'image à la taille du panneau
            double scaleX = (double) panelWidth / imgWidth;
            double scaleY = (double) panelHeight / imgHeight;
            double scale = Math.min(scaleX, scaleY);

            // Calculer les nouvelles dimensions de l'image
            int newWidth = (int) (imgWidth * scale);
            int newHeight = (int) (imgHeight * scale);

            // Dessiner l'image en utilisant le facteur d'échelle calculé
            int x = (panelWidth - newWidth) / 2;
            int y = (panelHeight - newHeight) / 2;
            g.drawImage(image, x, y, newWidth, newHeight, null);
        }
    }
}

public class Vue {
	
	static ArrayList<JButton> jbGameTab = new ArrayList<JButton>();

    public static void ShowPage(int pageIndex, JPanel[][] pTab, JScrollPane[] spTab) {
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
        
		if(pageIndex==1) {
			spTab[0].setVisible(true);
		}else {
			spTab[0].setVisible(false);
		}
        
	}
	
    public static void createAndShowGUI() {
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
        pBodyHome.setPreferredSize(new Dimension(1280,1080));
        
        
        // PANEL FOOT HOME
    	JPanel pFootHome = new JPanel();
        pFootHome.add(new JLabel("FOOT HOME"));
        pFootHome.setPreferredSize(new Dimension(1280,75));
        
        /* --------------------------------------- */
        
        // PANEL BODY JEUX
        JPanel pBodyGame = new JPanel();
    	pBodyGame.setVisible(false);
    	pBodyGame.setBackground(Color.white);
        pBodyGame.setPreferredSize(new Dimension(1280,1080));
        
        // PANEL LIST JEUX (BODY HOME)
        for(int i=0; i<150;i++) {
        	JButton jLGame = new JButton("JEU " +i);
        	jLGame.setPreferredSize(new Dimension(200,300));
        	jbGameTab.add(jLGame);
        	pBodyGame.add(jLGame);
        }
        JScrollPane scrollListGameBodyHome = new JScrollPane(pBodyGame); 
        scrollListGameBodyHome.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollListGameBodyHome.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        scrollListGameBodyHome.getVerticalScrollBar().setUnitIncrement(20);
        scrollListGameBodyHome.setVisible(false);
        
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
        pBodyFav.setPreferredSize(new Dimension(1280,1080));
        
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
        pBodyMyGame.setPreferredSize(new Dimension(1280,1080));
        
        // PANEL FOOT myGAME
    	JPanel pFootMyGame = new JPanel();
    	pFootMyGame.setVisible(false);
    	pFootMyGame.add(new JLabel("FOOT MY GAME"));
    	pFootMyGame.setPreferredSize(new Dimension(1280,75));
    	
    	/* --------------------------------------- */
    	
        // PANEL BODY GAMEPAGE
        JPanel pBodyGamePage = new JPanel();
        pBodyGamePage.setVisible(false);
        pBodyGamePage.setBackground(Color.white);
        pBodyGamePage.setLayout(new BorderLayout());
        pBodyGamePage.setPreferredSize(new Dimension(1280,1080));
        
        // PANEL FOOT GAMEPAGE
    	JPanel pFootGamePage = new JPanel();
    	pFootGamePage.setVisible(false);
    	pFootGamePage.add(new JLabel("FOOT GAME PAGE"));
    	pFootGamePage.setPreferredSize(new Dimension(1280,75));
    	
    	// LEFT PANEL BODY GAMEPAGE
    	
    	JPanel lpBodyGamePage = new JPanel();
    	lpBodyGamePage.setBackground(Color.white);
    	lpBodyGamePage.setPreferredSize(new Dimension(500,500));
    	lpBodyGamePage.setBorder(BorderFactory.createEmptyBorder(20, 50, 100, 0));
    	lpBodyGamePage.setLayout(new BorderLayout());
    	
    	String imagePath = "/home/egazzoli/Documents/test.jpg";
        ImagePanel imagePanel = new ImagePanel(imagePath);  
        lpBodyGamePage.add(imagePanel, BorderLayout.CENTER);
        
        JLabel title = new JLabel("TITRE JEU");
        title.setFont(new Font("Arial", Font.PLAIN, 48));
        title.setForeground(Color.BLACK);
        
        JLabel date = new JLabel("DATE");
        date.setFont(new Font("Arial", Font.PLAIN, 24));
        date.setForeground(Color.BLACK);
        
        JLabel author = new JLabel("AUTEUR");
        author.setFont(new Font("Arial", Font.PLAIN, 24));
        author.setForeground(Color.BLACK);
        
        pBodyGamePage.add(lpBodyGamePage, BorderLayout.WEST);
        
        JPanel pTextGame = new JPanel();
        pTextGame.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));
        pTextGame.setBackground(Color.white);
        pTextGame.setPreferredSize(new Dimension(100,120));
        pTextGame.setLayout(new BoxLayout(pTextGame, BoxLayout.Y_AXIS));
        lpBodyGamePage.add(pTextGame, BorderLayout.SOUTH);
        
        pTextGame.add(title);
        pTextGame.add(date);
        pTextGame.add(author);
        
        // PANEL CENTRAL BODY GAMEPAGE   
        
        // PARTIE DESCRITPTION
        
        JPanel pcBodyGamePage = new JPanel();
        pcBodyGamePage.setBackground(Color.white);
        pcBodyGamePage.setLayout(new BoxLayout(pcBodyGamePage, BoxLayout.Y_AXIS));
        pcBodyGamePage.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        
        JPanel pDescGame = new JPanel();
        pDescGame.setLayout(new FlowLayout(FlowLayout.LEFT));
        pDescGame.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        pDescGame.setPreferredSize(new Dimension(400,500));
        pDescGame.setBackground(Color.white);
        
        JTextArea lDescGame = new JTextArea("Le jeu est une activité, humaine ou animale, pratiquée pour se divertir. Propre aux mammifères, cette activité d'ordre psychique ou bien physique crée une dépense d'énergie décorrélée des intérêts essentiels immédiats autres que le plaisir.");
        lDescGame.setFont(new Font("Arial", Font.PLAIN, 24));
        lDescGame.setPreferredSize(new Dimension(900,600));
        lDescGame.setEditable(false);
        lDescGame.setLineWrap(true);
        lDescGame.setWrapStyleWord(true);
        
        // PARTIE AUTRE JEU RECOMMANDE
        
        JPanel pRecommendedGame = new JPanel();
        pRecommendedGame.setBackground(Color.white);
        pRecommendedGame.setLayout(new FlowLayout(FlowLayout.LEFT));
        pRecommendedGame.setPreferredSize(new Dimension(900,500));
        
        JPanel pYAxisPanel = new JPanel();
        pYAxisPanel.setPreferredSize(new Dimension(900,1000));
        pYAxisPanel.setLayout(new BoxLayout(pYAxisPanel, BoxLayout.Y_AXIS));
        
        JPanel pGames = new JPanel();
        pGames.setLayout(new FlowLayout(FlowLayout.LEFT));
        pGames.setBorder(BorderFactory.createEmptyBorder(50, 150, 0, 0));
        
        JLabel lOtherGame = new JLabel("JEU RECOMMANDE");
        lOtherGame.setFont(new Font("Arial", Font.PLAIN, 24));
        lOtherGame.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 75));
        
        JButton game = jbGameTab.get(3);
        game.setPreferredSize(new Dimension(200,300));
        
        JButton game2 = jbGameTab.get(1);
        game2.setPreferredSize(new Dimension(200,300));
        
        JButton game3 = jbGameTab.get(2);
        game3.setPreferredSize(new Dimension(200,300));      
        
        pRecommendedGame.add(pYAxisPanel);
        pYAxisPanel.add(lOtherGame);
        pYAxisPanel.add(pGames);   
        
        pGames.add(game);
        pGames.add(game2);
        pGames.add(game3);
        
        pDescGame.add(lDescGame);
        
        pcBodyGamePage.add(pDescGame);
        pcBodyGamePage.add(pRecommendedGame);
        
        pBodyGamePage.add(pcBodyGamePage, BorderLayout.CENTER);

        // RIGHT PANEL BODY GAMEPAGE
        
        JPanel rpBodyGamePage = new JPanel();
        rpBodyGamePage.setPreferredSize(new Dimension(450,500));
        rpBodyGamePage.setBackground(Color.white);
        
        // LISTE DES TAGS
        
        JPanel pGenreTag = new JPanel();
        pGenreTag.setLayout(new FlowLayout(FlowLayout.LEFT));
        pGenreTag.setPreferredSize(new Dimension(400,200));
        pGenreTag.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        pGenreTag.setBackground(Color.white);
        
        JTextArea taGenre = new JTextArea("genre1 " + "genre2 " + "genre3 " + "genre4 " + "genre5 " + "genre6 ");
        taGenre.setFont(new Font("Arial", Font.PLAIN, 24));
        taGenre.setPreferredSize(new Dimension(400,200));
        taGenre.setEditable(false);
        taGenre.setLineWrap(true);
        taGenre.setWrapStyleWord(true);
        
        // NOTE DU JEU
        
        JPanel pNote = new JPanel();
        pNote.setPreferredSize(new Dimension(390,50));
        
        JLabel lNote = new JLabel("20/20");
        lNote.setFont(new Font("Arial", Font.PLAIN, 24));
        lNote.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        
        pNote.add(lNote);
        
        // BOUTTON AJOUTER FAVORIS
        
        JButton bAddFavorite = new JButton("AJOUTER AUX FAVORIS");
        bAddFavorite.setFont(new Font("Arial", Font.PLAIN, 24));
        bAddFavorite.setPreferredSize(new Dimension(390,50));
        
        rpBodyGamePage.add(pGenreTag);
        rpBodyGamePage.add(pNote);
        rpBodyGamePage.add(bAddFavorite);
        pGenreTag.add(taGenre);
        
        pBodyGamePage.add(rpBodyGamePage, BorderLayout.EAST);
        
        /* --------------------------------------- */
        
        JPanel pGamesHome = new JPanel();
        pGamesHome.setLayout(new FlowLayout(FlowLayout.LEFT));
        pGamesHome.setPreferredSize(new Dimension(1050,330));
        pGamesHome.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
        
        JButton GameHome1 = jbGameTab.get(99);
        GameHome1.setPreferredSize(new Dimension(200,300));
        
        JButton GameHome2 = jbGameTab.get(99);
        GameHome2.setPreferredSize(new Dimension(200,300));
        
        JButton GameHome3 = jbGameTab.get(97);
        GameHome3.setPreferredSize(new Dimension(200,300));      
        
        JButton GameHome4 = jbGameTab.get(96);
        GameHome4.setPreferredSize(new Dimension(200,300));  
        
        JButton GameHome5 = jbGameTab.get(12);
        GameHome5.setPreferredSize(new Dimension(200,300));  
        
        pBodyHome.add(pGamesHome);
        
        pGamesHome.add(GameHome1);
        pGamesHome.add(GameHome2);
        pGamesHome.add(GameHome3);
        pGamesHome.add(GameHome4);
        pGamesHome.add(GameHome5);
        
    	/* --------------------------------------- */
    	
    	// SEARCH BAR FOOT
    	JTextField searchBar = new JTextField("Rechercher");
    	searchBar.setPreferredSize(new Dimension(200, 30));
    	
    	searchBar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	searchBar.setText("");
            }
    	});
    	
    	searchBar.addKeyListener(new KeyListener() {
            public void mouseClicked(MouseEvent e) {
            	searchBar.setText("");
            }

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==10) {
					System.out.println("RECHERCHE");
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
    	});
        
        /* --------------------------------------- */
        
        // PANEL BUTTON (HEAD)
        JPanel pButton = new JPanel();
        pButton.setLayout(new GridLayout(0,2));
        pButton.add(jvB);
        pButton.add(favB);
        //pButton.add(myGameB);
        
        // PANEL NOM (HEAD)
        JPanel pName = new JPanel();
        pName.setLayout(new BorderLayout());
        pName.setPreferredSize(new Dimension(400,50));
        pName.add(nameB, BorderLayout.WEST);

        // PANEL HEAD
        JPanel pHead = new JPanel();
        pHead.setLayout(new BorderLayout());
        pHead.setPreferredSize(new Dimension(1920,75));
        pHead.add(pName, BorderLayout.WEST);
        pHead.add(pButton, BorderLayout.CENTER);
        pHead.add(searchBar, BorderLayout.EAST);
        //pHead.add(searchButton, BorderLayout.EAST);
        
        /* --------------------------------------- */
        
        // ACTION DE L'UTILISATEUR 
        
        JButton[] bHeadTab = {nameB,jvB,favB,myGameB,bAddFavorite};
        JPanel[][] pTab = { {pBodyHome,pFootHome} , {pBodyGame, pFootGame}, {pBodyFav,pFootFav}, {pBodyMyGame,pFootMyGame}, {pBodyGamePage,pFootGamePage}};
        JScrollPane[] spTab = {scrollListGameBodyHome};
        
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
                	ShowPage(index,pTab,spTab);
                	System.out.println(index);
                }
        	});
        }
        
        for(int i=0;i<jbGameTab.size();i++) {
        	jbGameTab.get(i).addMouseListener(new MouseAdapter() {
        		
                public void mouseClicked(MouseEvent e) {
                	pBodyGamePage.setVisible(true);
                	pFootGamePage.setVisible(true);
                	
                	pBodyGame.setVisible(false);
                	pFootGame.setVisible(false);
                	
                	pBodyHome.setVisible(false);
                	pFootHome.setVisible(false);
                	
                	spTab[0].setVisible(false);
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
        
        container.add(scrollListGameBodyHome);
        container.add(pFootGame);
        
        container.add(pBodyFav);
        container.add(pFootFav);
        
        container.add(pBodyMyGame);
        container.add(pFootMyGame);  
        
        container.add(pBodyGamePage);
        container.add(pFootGamePage);
        
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