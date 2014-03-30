package vue;
 
import java.util.Observable;
import java.util.Observer;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import controleur.TerrainControleur;

public class Terrain extends JFrame implements Observer, MouseListener, KeyListener{
	
	private PlateauJeu plateauJeu;
	
	// Instance de l'objet controleur
	private TerrainControleur controleur;
	
	public Terrain(TerrainControleur pControleur)
	{
		this.setSize(600, 600);
		this.setTitle("Jeu de l'enclos");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.controleur = pControleur;
		this.plateauJeu = new PlateauJeu(controleur.getTerrainModele());
		
		this.setContentPane(plateauJeu);
		this.addMouseListener(this);
		this.addKeyListener(this);
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
		int y = e.getY();
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
