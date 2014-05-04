//package vue;
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
// 
//import javax.swing.BoxLayout;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//
//import modele.TerrainModele;
//import controleur.*;
//
//public class Menu extends JFrame{
//
//	private JButton fastButton = new JButton("Partie Rapide"), resumeButton = new JButton("Reprendre"), profilButton = new JButton("Creer un nouveau profil"), settingButton = new JButton("Parametre du jeu"), cancelButton = new JButton("Retour au menu principal");
//	private JLabel icon = new JLabel(new ImageIcon("images/menu.jpg"));
//	
//	public Menu(){
//		this.setTitle("Jeu de l'enclos");
//	    this.setSize(700, 750);
//	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	    this.setLocationRelativeTo(null);
//
//	    JPanel panIcon = new JPanel();
//	    panIcon.setLayout(new BorderLayout());
//	    panIcon.setBackground(Color.WHITE);
//	    panIcon.add(icon);
//	    
//	    JPanel control = new JPanel();
//	    control.setBackground(Color.BLACK);
//	    control.setLayout(new BoxLayout(control,BoxLayout.Y_AXIS));
//	    control.add(fastButton);
//	    control.add(resumeButton);
//	    control.add(profilButton);
//	    control.add(settingButton);
//	    control.add(cancelButton);
//	    this.getContentPane().add(control, BorderLayout.EAST);
//	    this.getContentPane().add(panIcon, BorderLayout.CENTER);
//	    
//	    
//	    fastButton.addActionListener(new ActionListener(){
//	    	public void actionPerformed(ActionEvent arg0) {
//	    		// Instanciation du modèle 
//	    		TerrainModele modele = new TerrainModele();
//	    		// Création du controleur lié au modèle
//	    		TerrainControleur controleur = new TerrainControleur(modele);
//	    		// Création de la fenetre avec le controleur en param
//	    		Terrain terrain = new Terrain(controleur);
//	    		// Ajout de la fenetre comme observer de notre modèle
//	    		modele.addObserver(terrain);
//	    		setVisible(false); 
//	    	}         
//	    });
//	    resumeButton.addActionListener(new ActionListener(){
//	    	public void actionPerformed(ActionEvent arg0) {
//	    		System.out.println("Coder les sauvegardes");
//	    	}         
//	    });
//	    profilButton.addActionListener(new ActionListener(){
//	    	public void actionPerformed(ActionEvent arg0) {
//	    		System.out.println("Coder les profils");
//	    	}
//	    });
//	    settingButton.addActionListener(new ActionListener(){
//	    	public void actionPerformed(ActionEvent arg0) {
//	    		System.out.println("Coder les parametres du jeu");
//	    	}
//	    });
//	    cancelButton.addActionListener(new ActionListener(){
//	    	public void actionPerformed(ActionEvent arg0) {
//	    		MenuPrincipal mainMenu = new MenuPrincipal();
//	    		setVisible(false);
//	    	}
//	    });
//	    this.setVisible(true);
//	}
//}
