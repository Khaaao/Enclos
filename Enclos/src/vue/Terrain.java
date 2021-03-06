package vue;
 
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controleur.TerrainControleur;

public class Terrain extends JFrame implements Observer, MouseListener, KeyListener{
	
	private PlateauJeu plateauJeu;
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu1 = new JMenu("Fichier");
	private JMenu menu2 = new JMenu("Edit");
	private JMenu menu3 = new JMenu("Affichage");
	private JMenu menu4 = new JMenu("A propos");
	
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
	
	// Instance de l'objet controleur
	private TerrainControleur controleur;
	public Terrain(TerrainControleur pControleur)
	{
		
		this.setSize(700, 750);
		this.setTitle("Jeu de l'enclos");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.controleur = pControleur;
		this.plateauJeu = new PlateauJeu(controleur.getTerrainModele());
		
		this.setContentPane(plateauJeu);
		this.addMouseListener(this);
		this.addKeyListener(this);
		
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
	    
	    this.setJMenuBar(menuBar);
	    
	    
	    // Les ActionListener
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
			}
		});
		
		
		
		this.setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY()-25;
		controleur.click(x, y);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		repaint();
	}
}
