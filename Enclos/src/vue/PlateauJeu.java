package vue;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import modele.Point;
import modele.TerrainModele;

public class PlateauJeu extends JPanel{

	private TerrainModele modele;
	
	public PlateauJeu(TerrainModele modele)
	{
		this.modele = modele;
	}
	
	public void paintComponent(Graphics graphics)
	{
		// Hexagones
		super.paintComponent(graphics);
		graphics.setColor(Color.GREEN);
		for(int i = 0; i < modele.getArChamps().size(); i++)
			graphics.fillPolygon(modele.getArChamps().get(i));
		
		// Chemins
		for(int i = 0; i < modele.getArChamps().size(); i++)
		{
			for(int j = 0; j < modele.getArChamps().get(i).getChemins().size(); j++)
			{
				if(modele.getArChamps().get(i).getChemins().get(j).isBloque())
					graphics.setColor(Color.RED);
				else
					graphics.setColor(Color.YELLOW);
				graphics.fillPolygon(modele.getArChamps().get(i).getChemins().get(j));
			}
		}
		
		//Joueurs
		for(int i = 0; i < modele.getArJoueurs().size(); i++)
		{
			for(int j= 0; j < modele.getArJoueurs().get(i).getListeMoutons().size(); j++)
			{
				if(modele.getArJoueurs().get(i).getId() == 1)
					graphics.setColor(Color.ORANGE);
				else
					graphics.setColor(Color.RED);
				drawCircle(graphics, modele.getArJoueurs().get(i).getListeMoutons().get(j).getCentre());
			}
		}
	}

	public void drawCircle(Graphics cg, Point point) {
        cg.fillOval((int)point.getX()-(TerrainModele.r -10),(int)point.getY()-(TerrainModele.r -10), 2*(TerrainModele.r -10), 2*(TerrainModele.r -10));
    }
}
