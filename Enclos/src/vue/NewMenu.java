package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import modele.TerrainModele;
import controleur.TerrainControleur;

public class NewMenu extends JFrame implements ActionListener{
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu1 = new JMenu("Fichier");
	private JMenu menu2 = new JMenu("Edit");
	private JMenu menu3 = new JMenu("Affichage");
	private JMenu menu4 = new JMenu("A propos");
	private JLabel icon_ = new JLabel(new ImageIcon("images/menu_principal.jpg"));
	
	private JMenuItem menuitem1 = new JMenuItem("Nouvelle partie");
	private JMenuItem menuitem2 = new JMenuItem("Sauvegarder partie");
	private JMenuItem menuitem3 = new JMenuItem("Charger partie");
	private JMenuItem menuitem4 = new JMenuItem("Options");
	private JMenuItem menuitem5 = new JMenuItem("Quitter");
	private JMenuItem menuitem6 = new JMenuItem("Undo");
	private JMenuItem menuitem7 = new JMenuItem("Gestion utilisateur");
	private JMenuItem menuitem8 = new JMenuItem("Head2Head");
	private JMenuItem menuitem9 = new JMenuItem("?");
	private JMenuItem menuitem10 = new JMenuItem("Partie rapide");
	
	public NewMenu()
	{
		this.setSize(700, 750);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Jeu de l'enclos");
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    
	    JPanel panIcon = new JPanel();
	    panIcon.setLayout(new BorderLayout());
	    panIcon.setBackground(Color.WHITE);
	    panIcon.add(icon_);
	    
	    
	    this.menu1.add(menuitem10);
	    this.menu1.add(menuitem1);
	    this.menu1.add(menuitem2);
	    this.menu1.add(menuitem3);
	    this.menu1.add(menuitem4);
	    this.menu1.addSeparator();
	    this.menu1.add(menuitem5);
	    
	    this.menu2.add(menuitem6);
	    this.menu2.add(menuitem7);
	    
	    this.menu3.add(menuitem8);
	    
	    this.menu4.add(menuitem9);
	    
	    this.menuBar.add(menu1);
	    this.menuBar.add(menu2);
	    this.menuBar.add(menu3);
	    this.menuBar.add(menu4);
	    
	    this.menuitem2.setEnabled(false);
	    
	    this.setJMenuBar(menuBar);
	    
	    
	    // Les ActionListener
	    menuitem1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	    
	    menuitem5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
	    
	    menuitem10.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				creationTerrain();
			}
		});
	    
	    this.getContentPane().add(panIcon, BorderLayout.CENTER);
	    this.setVisible(true);
	}

	protected void creationTerrain() {
		// TODO Auto-generated method stub
		// Instanciation du modèle 
		TerrainModele modele = new TerrainModele();
		// Création du controleur lié au modèle
		TerrainControleur controleur = new TerrainControleur(modele);
		// Création de la fenetre avec le controleur en param
		Terrain terrain = new Terrain(controleur);
		// Ajout de la fenetre comme observer de notre modèle
		modele.addObserver(terrain);
		setVisible(false); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
