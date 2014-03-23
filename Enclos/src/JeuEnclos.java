import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.border.Border;


public class JeuEnclos extends JFrame{
	
	public JeuEnclos()
	{
		// Caractéristiques du terrain
		Terrain terrain = new Terrain();
		
		this.setTitle("Jeu de l'enclos");
		this.setSize(600, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		// Ajout du terrain dans BorderLayout CENTER - - 
		this.add(terrain);
		
		// Fin
		this.setVisible(true);
	}
	
	public void init()
	{
		
	}
	
	public void start()
	{
		
	}
	
	public void stop()
	{
		
	}
	
	
	public void run()
	{
		
	}
	
	public void upDate(Graphics graphics)
	{
		
	}
	
}
