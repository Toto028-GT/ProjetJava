import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
import java.util.Random;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.formdev.flatlaf.FlatDarkLaf;

class ImagePanel extends JPanel {
    private BufferedImage image;

    // Constructeur initial
    public ImagePanel(String imagePath) {
        loadImage(imagePath);
    }

    // Méthode pour charger une nouvelle image
    public void loadImage(String imagePath) {
        try {
            // Charger l'image depuis l'URL
            URL url = new URL(imagePath);
            image = ImageIO.read(url);
            // Redessiner le composant pour refléter le changement d'image
            repaint();
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
	
	public static Record[] r;
	public static Controleur c;
	public static Modele m;
	public static int gameIndex;
	 
	public Vue(Record[] r, Controleur c, Modele m) {
		this.r = r;
		this.c = c;
		this.m = m;
		
	}
	
	static ArrayList<JButton> jbGameTab = new ArrayList<JButton>();
	static ArrayList<JButton> bFavGame  = new ArrayList<JButton>();
	
	static JPanel pBodyGamePage = new JPanel();
	static JPanel pFootGamePage = new JPanel();
	
	static JPanel pBodyGame = new JPanel();
	static JPanel pFootGame = new JPanel();
	
	static JPanel pBodyHome = new JPanel();
	static JPanel pFootHome = new JPanel();
	
	static JPanel pBodyFav = new JPanel();
	static JPanel pFootFav = new JPanel();
	
	static JScrollPane scrollListGameBodyHome = new JScrollPane(pBodyGame); 
	static JScrollPane scrollListBodyFav      = new JScrollPane(pBodyFav);
	static JScrollPane[] spTab                = {scrollListGameBodyHome, scrollListBodyFav};
	
	static JPanel pTextGame            = new JPanel();
	static JPanel container            = new JPanel();
	static JLabel title                = new JLabel("TITRE");
	static JLabel date                 = new JLabel("DATE");
	static JLabel author               = new JLabel("AUTEUR");
	static JTextArea lDescGame         = new JTextArea("REVIEW");
	static JLabel lNote                = new JLabel("NOTE");
	static JTextArea[] lReviewGameHome = new JTextArea[9];
	static JTextArea taGenre           = new JTextArea("GENRE");
	static ImagePanel imagePanel       = new ImagePanel("https://static.metacritic.com/images/products/games/5/98ded8914dd98a1efd777a592289c756-98.jpg");
	
	static int numberOfElements = pBodyFav.getComponentCount();
	
	static Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize(); 	
	static int maxXGame = windowSize.width/200;
	static int maxYGame = windowSize.height/300;
	
	static JPanel pReviewTab = new JPanel();
	static JScrollPane scrollPaneReview;
	
	static JPanel pGames = new JPanel();
	
	
	
    public static JButton cloneJButton(JButton originalButton) {
        JButton clonedButton = new JButton(originalButton.getIcon());
        
        final int index = jbGameTab.indexOf(originalButton);
        
        clonedButton.addMouseListener(new MouseAdapter() {
    		
            public void mouseClicked(MouseEvent e)
            {
            	pBodyGamePage.setVisible(true);
            	pFootGamePage.setVisible(true);
            	
            	pBodyGame.setVisible(false);
            	pFootGame.setVisible(false);
            	
            	pBodyHome.setVisible(false);
            	pFootHome.setVisible(false);
            	
            	pBodyFav.setVisible(false);
            	pFootFav.setVisible(false);
            	
            	spTab[0].setVisible(false);
            	spTab[1].setVisible(false);
            	
            	gameIndex = index;
            	
            	c.SetGamePage(r, gameIndex, m);
            }
        });
        
        return clonedButton;
    }   

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
		
		if(pageIndex==2) {
			spTab[1].setVisible(true);
			
			pBodyFav.removeAll();
			
			if(bFavGame.size() >0) {
		        for(int i=0; i<bFavGame.size();i++) {
		        	JButton gameToAdd = cloneJButton(bFavGame.get(i));
		        	gameToAdd.setPreferredSize(new Dimension((int) ((windowSize.width/100)*10.4),(windowSize.height/100)*28));
		        	pBodyFav.add(gameToAdd);
		        }
			}
			
		}else {
			spTab[1].setVisible(false);
		}
        
	}
	
    public static void createAndShowGUI() throws StreamReadException, DatabindException, IOException {
    	
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
        pBodyHome.setBackground(Color.white);
        pBodyHome.setPreferredSize(new Dimension(windowSize.width,(windowSize.height/100)*95));
        
        
        // PANEL FOOT HOME
        pFootHome.setPreferredSize(new Dimension(windowSize.width,(windowSize.height/100)*0));
        
        /* --------------------------------------- */
        
        // PANEL BODY JEUX
    	pBodyGame.setVisible(false);
    	pBodyGame.setBackground(Color.white);
        pBodyGame.setPreferredSize(new Dimension(windowSize.width,(windowSize.height/100)*95));
        
        m.fiveBest();
        
        // PANEL LIST JEUX (BODY HOME)
        for(int i=0; i<r.length;i++) {
        	try {
				URL imageURL = new URL(r[i].getGamePoster());
				ImageIcon icon = new ImageIcon(imageURL);
				
	        	JButton jLGame = new JButton(icon);
	        	jLGame.setPreferredSize(new Dimension((int) ((windowSize.width/100)*10.42),(windowSize.height/100)*28));

	        	jbGameTab.add(jLGame);
	        	pBodyGame.add(jLGame);
            	
            	if(jbGameTab.size() >= (maxXGame* maxYGame) && jbGameTab.size()%maxXGame == 0) {
            		pBodyGame.setPreferredSize(new Dimension((int) ((windowSize.width/100)*66.7),pBodyGame.getPreferredSize().height + (int) ((windowSize.height/100)*28)));
        		}
	        	
			} catch (IOException e) {
				e.printStackTrace();
			}
        	
        }

        scrollListGameBodyHome.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollListGameBodyHome.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        scrollListGameBodyHome.getVerticalScrollBar().setUnitIncrement(20);
        scrollListGameBodyHome.setVisible(false);
        
        // PANEL FOOT JEUX
    	pFootGame.setVisible(false);
        pFootGame.setPreferredSize(new Dimension(windowSize.width,(windowSize.height/100)*0));
        
        /* --------------------------------------- */
        
        // PANEL BODY FAVORIS
    	pBodyFav.setVisible(false);
    	pBodyFav.setBackground(Color.white);
        pBodyFav.setPreferredSize(new Dimension(windowSize.width,(windowSize.height/100)*95));

        scrollListBodyFav.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollListBodyFav.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        scrollListBodyFav.getVerticalScrollBar().setUnitIncrement(20);
        scrollListBodyFav.setVisible(false);
        
        // PANEL FOOT FAVORIS
    	pFootFav.setVisible(false);
        pFootFav.setPreferredSize(new Dimension(windowSize.width,(windowSize.height/100)*0));
        
        /* --------------------------------------- */
        
     // PANEL BODY myGAME
        JPanel pBodyMyGame = new JPanel();
        pBodyMyGame.setVisible(false);
        pBodyMyGame.setBackground(Color.white);
        pBodyMyGame.setPreferredSize(new Dimension(windowSize.width,(windowSize.height/100)*95));
        
        // PANEL FOOT myGAME
    	JPanel pFootMyGame = new JPanel();
    	pFootMyGame.setVisible(false);
    	pFootMyGame.setPreferredSize(new Dimension(windowSize.width,(windowSize.height/100)*0));
    	
    	/* --------------------------------------- */
    	
        // PANEL BODY GAMEPAGE
        pBodyGamePage.setVisible(false);
        pBodyGamePage.setBackground(Color.white);
        pBodyGamePage.setLayout(new BorderLayout());
        pBodyGamePage.setPreferredSize(new Dimension(windowSize.width,(windowSize.height/100)*95));
        
        // PANEL FOOT GAMEPAGE
    	pFootGamePage.setVisible(false);
    	pFootGamePage.setPreferredSize(new Dimension(windowSize.width,(windowSize.height/100)*0));
    	
    	// LEFT PANEL BODY GAMEPAGE
    	JPanel lpBodyGamePage = new JPanel();
    	lpBodyGamePage.setBackground(Color.white);
    	lpBodyGamePage.setPreferredSize(new Dimension((int) ((windowSize.width/100)*26),(windowSize.height/100)*47));
    	lpBodyGamePage.setBorder(BorderFactory.createEmptyBorder((int) ((windowSize.height/100)*1.85), (int) ((windowSize.width/100)*2.6), (int) ((windowSize.height/100)*9.26), 0)); // 20 50 100
    	lpBodyGamePage.setLayout(new BorderLayout());
        lpBodyGamePage.add(imagePanel, BorderLayout.CENTER);
        title.setForeground(Color.WHITE);
        
        date.setFont(new Font("Arial", Font.PLAIN, 24));
        date.setForeground(Color.WHITE);
        
        author.setFont(new Font("Arial", Font.PLAIN, 24));
        author.setForeground(Color.WHITE);
        
        pBodyGamePage.add(lpBodyGamePage, BorderLayout.WEST);
        
        pTextGame.setBorder(BorderFactory.createEmptyBorder(0, 0, (int) ((windowSize.height/100)*0.926), (int) ((windowSize.width/100)*0.521))); // 0 0 10 10
        pTextGame.setBackground(Color.decode("#3c3f41"));
        pTextGame.setPreferredSize(new Dimension((int) ((windowSize.width/100)*5.3),(windowSize.height/100)*15));
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
        pcBodyGamePage.setBorder(BorderFactory.createEmptyBorder(0, (int) ((windowSize.width/100)*2.6), 0, 0)); // 0 50 0 0
        
        JPanel pDescGame = new JPanel();
        pDescGame.setLayout(new FlowLayout(FlowLayout.LEFT));
        pDescGame.setBorder(BorderFactory.createEmptyBorder((int) ((windowSize.height/100)*0.926), 0, 0, 0)); // 10 0 0 0
        pDescGame.setPreferredSize(new Dimension((int) ((windowSize.width/100)*20.8),(windowSize.height/100)*47));
        pDescGame.setBackground(Color.white);
        
        lDescGame.setFont(new Font("Arial", Font.PLAIN, 24));
        lDescGame.setPreferredSize(new Dimension((int) ((windowSize.width/100)*46.8),(windowSize.height/100)*56));
        lDescGame.setBackground(Color.decode("#3c3f41"));
        lDescGame.setForeground(Color.white);
        lDescGame.setBorder(BorderFactory.createEmptyBorder((int) ((windowSize.height/100)*0.463), (int) ((windowSize.width/100)*0.26), 0, 0)); // 5 5 0 0
        lDescGame.setEditable(false);
        lDescGame.setLineWrap(true);
        lDescGame.setWrapStyleWord(true);
        
        // PARTIE AUTRE JEU RECOMMANDE
        JPanel pRecommendedGame = new JPanel();
        pRecommendedGame.setBackground(Color.white);
        pRecommendedGame.setLayout(new FlowLayout(FlowLayout.LEFT));
        pRecommendedGame.setPreferredSize(new Dimension((int) ((windowSize.width/100)*47),(windowSize.height/100)*38)); 
        
        JPanel pYAxisPanel = new JPanel();
        pYAxisPanel.setPreferredSize(new Dimension((int) ((windowSize.width/100)*46.8),(windowSize.height/100)*93)); // 890 1000
        pYAxisPanel.setLayout(new BoxLayout(pYAxisPanel, BoxLayout.Y_AXIS));
      
        pGames.setLayout(new FlowLayout(FlowLayout.LEFT));
        pGames.setBorder(BorderFactory.createEmptyBorder( (int) ((windowSize.height/100)*3.7), (int) ((windowSize.width/100)*7.8125), 0, 0)); // 40 150 0 0
        
        
        JLabel lOtherGame = new JLabel("JEU RECOMMANDE");
        lOtherGame.setFont(new Font("Arial", Font.PLAIN, (int) ((windowSize.width/100)*1.25)));
        lOtherGame.setBorder(BorderFactory.createEmptyBorder((int) ((windowSize.height/100)*0.926), 0, 0, (int) ((windowSize.width/100)*8.1))); // 10 0 0 155
        
        pRecommendedGame.add(pYAxisPanel);
        pYAxisPanel.add(lOtherGame);
        pYAxisPanel.add(pGames);   
        
        pDescGame.add(lDescGame);
        
        pcBodyGamePage.add(pDescGame);
        pcBodyGamePage.add(pRecommendedGame);
        
        pBodyGamePage.add(pcBodyGamePage, BorderLayout.CENTER);

        // RIGHT PANEL BODY GAMEPAGE
        
        JPanel rpBodyGamePage = new JPanel();
        rpBodyGamePage.setPreferredSize(new Dimension((int) ((windowSize.width/100)*23.4375),(windowSize.height/100)*47)); // 450 / 500
        rpBodyGamePage.setBackground(Color.white);
        
        // LISTE DES TAGS
        JPanel pGenreTag = new JPanel();
        pGenreTag.setLayout(new FlowLayout(FlowLayout.LEFT));
        pGenreTag.setPreferredSize(new Dimension((int) ((windowSize.width/100)*20.8),(windowSize.height/100)*19)); // 400 200
        pGenreTag.setBorder(BorderFactory.createEmptyBorder((int) ((windowSize.height/100)*0.463), 0, 0, 0));
        pGenreTag.setBackground(Color.white);
        
        taGenre.setFont(new Font("Arial", Font.PLAIN, 24));
        taGenre.setPreferredSize(new Dimension((int) ((windowSize.width/100)*20.8),(windowSize.height/100)*19)); // 400 200
		taGenre.setBackground(Color.decode("#3c3f41"));
		taGenre.setForeground(Color.white);
		taGenre.setBorder(BorderFactory.createEmptyBorder((int) ((windowSize.height/100)*0.463), (int) ((windowSize.width/100)*0.26), 0, 0)); // 5 5 0 0
        taGenre.setEditable(false);
        taGenre.setLineWrap(true);
        taGenre.setWrapStyleWord(true);
        
        // NOTE DU JEU
        JPanel pNote = new JPanel();
        pNote.setPreferredSize((new Dimension((int) ((windowSize.width/100)*20.3125),(windowSize.height/100)*5))); // 390 50
        pNote.setBorder(BorderFactory.createEmptyBorder((int) ((windowSize.height/100)*0.463), (int) ((windowSize.width/100)*0.78125), 0, 0)); // 5 15 0 0
        
        lNote.setFont(new Font("Arial", Font.PLAIN, 24));
        lNote.setForeground(Color.white);
     
        pNote.add(lNote);
        
        // BOUTTON AJOUTER FAVORIS
        JButton bAddFavorite = new JButton("AJOUTER AUX FAVORIS");
        bAddFavorite.setFont(new Font("Arial", Font.PLAIN, (int) ((windowSize.width/100)*1.25))); // 24
        bAddFavorite.setPreferredSize((new Dimension((int) ((windowSize.width/100)*20.3125),(windowSize.height/100)*5))); // 390 50
        
        rpBodyGamePage.add(pGenreTag);
        rpBodyGamePage.add(pNote);
        rpBodyGamePage.add(bAddFavorite);
        pGenreTag.add(taGenre);
        
        pBodyGamePage.add(rpBodyGamePage, BorderLayout.EAST);
        
        /* --------------------------------------- */
        
        JPanel pGameMainHome = new JPanel();
        pGameMainHome.setLayout(new BoxLayout(pGameMainHome, BoxLayout.Y_AXIS));
        
        JPanel pGamesHome = new JPanel();
        pGamesHome.setLayout(new FlowLayout(FlowLayout.LEFT));
        pGamesHome.setPreferredSize(new Dimension((windowSize.width/100)*55,(windowSize.height/100)*32));
        pGamesHome.setBorder(BorderFactory.createEmptyBorder((int) ((windowSize.height/100)*0.93), (int) ((windowSize.width/100)*0.78125), 0, 0)); // 10 15 0 0
        
        JLabel lTitle = new JLabel("LES 5 JEUX LES MIEUX NOTES");
        lTitle.setFont(new Font("Arial", Font.PLAIN, (int) ((windowSize.width/100)*1.25))); // 24
        lTitle.setPreferredSize(new Dimension((int) ((windowSize.width/100)*0.78125),(int) ((windowSize.height/100)*2.8))); // 30 30
        lTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, (int) ((windowSize.width/100)*10.5))); // 0 0 0 200
        pBodyHome.add(lTitle);
        
        pGameMainHome.add(lTitle);
        pGameMainHome.add(pGamesHome);
        
        int homeButtonX = (int) ((windowSize.width/100)*10.4);
        int homeButtonY = (int) ((windowSize.height/100)*30);
        

        JButton BGameHome1 = cloneJButton(jbGameTab.get(0));
        BGameHome1.setPreferredSize(new Dimension(homeButtonX,homeButtonY));
        
        JButton BGameHome2 = cloneJButton(jbGameTab.get(1));
        BGameHome2.setPreferredSize(new Dimension(homeButtonX,homeButtonY));
        
        JButton BGameHome3 = cloneJButton(jbGameTab.get(2));
        BGameHome3.setPreferredSize(new Dimension(homeButtonX,homeButtonY));      
        
        JButton BGameHome4 = cloneJButton(jbGameTab.get(3));
        BGameHome4.setPreferredSize(new Dimension(homeButtonX,homeButtonY));  
        
        JButton BGameHome5 = cloneJButton(jbGameTab.get(4));
        BGameHome5.setPreferredSize(new Dimension(homeButtonX,homeButtonY));  
        
        pReviewTab.setLayout(new FlowLayout(FlowLayout.LEFT));
        pReviewTab.setPreferredSize(new Dimension((windowSize.width/100)*80,(windowSize.height/100)*50));
        Random random = new Random();
        
        ArrayList<Integer> reviewIndex = new ArrayList<>();
        for(int i=0;i<9;i++) {
        	
        	int rand = random.nextInt(35);
        	while(reviewIndex.contains(rand)) {
        		rand = random.nextInt(35);
        	}
        	reviewIndex.add(rand);

            JPanel pReview = new JPanel();
            pReview.setLayout(new FlowLayout(FlowLayout.LEFT));
            pReview.setPreferredSize(new Dimension((windowSize.width/100)*26,(windowSize.height/100)*16));

            JButton bReviewGameHome =  cloneJButton(jbGameTab.get(reviewIndex.get(i)));

            bReviewGameHome.setPreferredSize(new Dimension((int) ((windowSize.width/100)*5.2),(windowSize.height/100)*15));
            
            lReviewGameHome[i] = new JTextArea("REVIEW");
            
            lReviewGameHome[i].setFont(new Font("Arial", Font.PLAIN, 16));          
            lReviewGameHome[i].setEditable(false);
            lReviewGameHome[i].setPreferredSize(new Dimension((int) ((windowSize.width/100)*18),(windowSize.height/100)*15));
 
            
            scrollPaneReview = new JScrollPane(lReviewGameHome[i]);
            scrollPaneReview.setPreferredSize(new Dimension((int) ((windowSize.width/100)*19),(windowSize.height/100)*15));
            scrollPaneReview.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPaneReview.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            
            scrollPaneReview.getVerticalScrollBar().setUnitIncrement(20);
            
            c.SetReviewHomePage(r , i, rand);
            
            pReview.add(bReviewGameHome);
            pReview.add(scrollPaneReview);
            pReviewTab.add(pReview);       
        }
        
        
        pBodyHome.add(pGameMainHome);
        pBodyHome.add(pReviewTab);
        
        pGamesHome.add(BGameHome1);
        pGamesHome.add(BGameHome2);
        pGamesHome.add(BGameHome3);
        pGamesHome.add(BGameHome4);
        pGamesHome.add(BGameHome5);
        
    	/* --------------------------------------- */
    	
    	// SEARCH BAR FOOT
    	JTextField searchBar = new JTextField("Rechercher");
    	searchBar.setPreferredSize(new Dimension((int) ((windowSize.width/100)*10.42),(windowSize.height/100)*3)); // 200 30
    	
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
        
        // PANEL NOM (HEAD)
        JPanel pName = new JPanel();
        pName.setLayout(new BorderLayout());
        pName.add(nameB, BorderLayout.WEST);

        // PANEL HEAD
        JPanel pHead = new JPanel();
        pHead.setLayout(new BorderLayout());
        pHead.setPreferredSize(new Dimension(windowSize.width,(windowSize.height/100)*5));
        pHead.add(pName, BorderLayout.WEST);
        pHead.add(pButton, BorderLayout.CENTER);
        pHead.add(searchBar, BorderLayout.EAST);
        
        /* --------------------------------------- */
        
        // ACTION DE L'UTILISATEUR 
        
        JButton[] bHeadTab = {nameB,jvB,favB,/*myGameB ,*/bAddFavorite};
        JPanel[][] pTab = { {pBodyHome,pFootHome} , {pBodyGame, pFootGame}, {pBodyFav,pFootFav}, /*{pBodyMyGame,pFootMyGame},*/ {pBodyGamePage,pFootGamePage}};

        
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
                }
        	});
        }
        
        for(int i=0;i<jbGameTab.size();i++) {
        	final int index = i;
        	jbGameTab.get(i).addMouseListener(new MouseAdapter() {
        		
                public void mouseClicked(MouseEvent e) {
                	pBodyGamePage.setVisible(true);
                	pFootGamePage.setVisible(true);
                	
                	pBodyGame.setVisible(false);
                	pFootGame.setVisible(false);
                	
                	pBodyHome.setVisible(false);
                	pFootHome.setVisible(false);
                	
                	spTab[0].setVisible(false);
                	spTab[1].setVisible(false);
                	
                	gameIndex = index;

                	c.SetGamePage(r, gameIndex, m);
                	
                }
        	});
        	
        }

        
        bAddFavorite.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	
            	if(!bFavGame.contains(jbGameTab.get(gameIndex)))
            	{
            		bFavGame.add(jbGameTab.get(gameIndex));
                	numberOfElements = pBodyFav.getComponentCount();
                	
                	Dimension windowSize = container.getSize(); 	
                	maxXGame = windowSize.width/200;
                	maxYGame = windowSize.height/300;
                	
                	if(bFavGame.size() >= (maxXGame* maxYGame) && bFavGame.size()%maxXGame == 0) {
                		pBodyFav.setPreferredSize(new Dimension(1280,pBodyFav.getPreferredSize().height + 300));
                	}

            	}
            	
            }
    	});

        
        /* --------------------------------------- */
        
        // CONTAINER DE TOUTS LES PANELS
    	container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
 
    	container.add(pHead);
    	
        container.add(pBodyHome);
        container.add(pFootHome);
        
        container.add(scrollListGameBodyHome);
        container.add(pFootGame);
        
        container.add(scrollListBodyFav);
        container.add(pFootFav);
        
        //container.add(pBodyMyGame);
        //container.add(pFootMyGame);  
        
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
    
    // Méthode pour charger une image depuis une URL et la convertir en ImageIcon
    protected static ImageIcon createImageIcon(String path) {
        URL imageURL = Vue.class.getResource(path);
        if (imageURL != null) {
            return new ImageIcon(imageURL);
        } else {
            System.err.println("Impossible de charger l'image : " + path);
            return null;
        }
    }
    
}