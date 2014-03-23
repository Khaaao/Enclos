import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Terrain extends JPanel{
	
	// Constantes
	static final Color couleurChamp = Color.green;
	static final Color couleurChemin = Color.yellow;
	static final Color mouton1 = Color.orange;
	static final Color mouton2 = Color.black;
	static final Color couleurCheminBarre = Color.red;
	static final int r = 25;
	static final int d = (int)(2.76 * r);
	static final int niveau = 1;
	
	// Centre de notre Terrain
	private Point centre = new Point(300, 300);
	
	// JOKER
	private int superIndice = 0;
	
	private ArrayList<Champ> arChamps = new ArrayList<Champ>();
	private ArrayList<Chemin> arChemins = new ArrayList<Chemin>();
	
	private ArrayList<Point> arPoints = new ArrayList<Point>();
	private ArrayList<Point> arPointsCheck = new ArrayList<Point>();
	
	public Terrain()
	{
		/* === Etape1 : Construction des hexagones + leurs voisins === */
		arPointsCheck.add(centre);
		preparationDessinerVoisinsChamps(Terrain.niveau); // ETAPE 1
		arChamps.add(new Champ(centre));
		for(Point point : arPoints) // ETAPE 2
			arChamps.add(new Champ(point));
		ajoutDesVoisins();  // ETAPE 3	
		
		/* === Etape 2 : Construction des chemins === */
		preparationConstructionChemin();
	}
	
	// Dessine le terrain
	public void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);
		graphics.setColor(Color.GREEN);
		for(int i = 0; i < arChamps.size(); i++)
			graphics.fillPolygon(arChamps.get(i));
		
		graphics.setColor(Color.YELLOW);
		for(Chemin chemin : arChemins)
			graphics.fillPolygon(chemin);
	}
	
	/* Méthode servant au pivotement d'un point par rapport au centre */
	public Point calculPivot(Point pPointCentre, Point pPoint, int angle)
	{
		Point point = new Point();
		double a = Math.toRadians((double)angle);
		double x, y;
		x = Math.cos(a) * (pPoint.getX() - pPointCentre.getX()) - Math.sin(a) * (pPoint.getY() - pPointCentre.getY()) + pPointCentre.getX();
		y = Math.sin(a) * (pPoint.getX() - pPointCentre.getX()) + Math.cos(a) * (pPoint.getY() - pPointCentre.getY()) + pPointCentre.getY();
		point.setX(x);
		point.setY(y);
		return point;
	}
	
	/* Méthode servant au dessin des hexagones voisins */
	public void preparationDessinerVoisinsChamps(int pNiveau)
	{
		Point nCentre = centre; // Sera mis à jour 
		Point point = new Point(nCentre.getX(), nCentre.getY() - d);
		
		int niveau = 1;
		int indiceSauvegarde = 0; // Sert pour nos parcours
		
		// On stocke dans notre tableau les 6 premiers points + le centre
		preparationDessinerVoisinsChamps2(centre, point);
		
		while(niveau <= pNiveau)
		{
			for(int i = 0; i < (6 * niveau); i++)
			{
				nCentre = arPoints.get(indiceSauvegarde);
				point = new Point(nCentre.getX(), nCentre.getY() - d);
				if(!estDansLeTableau(point))
				{
					arPoints.add(point);
					arPointsCheck.add(point);
				}
				// On dessine les points correspondant au centre en question
				preparationDessinerVoisinsChamps2(nCentre, point);
				indiceSauvegarde++;
			}
			niveau++;
			
			// Nous servira ...
			if(niveau == Terrain.niveau)
				this.superIndice = indiceSauvegarde + 1;
		}
	}
	
	// Reporte dans arPoint et arPointCheck les points nouveaux
	public void preparationDessinerVoisinsChamps2(Point pPointCentre, Point pPointDepart)
	{
		Point point;
		int angle = 0;
		
		// Dessine les hexagones voisins
		for(int j = 0; j < 6; j++)
		{
			point = calculPivot(pPointCentre, pPointDepart, angle);
			if(!estDansLeTableau(point))
			{
				arPoints.add(point);
				arPointsCheck.add(point);
			}
			angle = angle + 60;
		}
	}
	
	/* Méthode vérifiant si le point envoyé en paramètre est déjà présent dans le tableau */
	public boolean estDansLeTableau(Point pPoint)
	{
		boolean trouve = false;
		int i = 0;
		while(!trouve && i < arPointsCheck.size())
		{
			if((int)(arPointsCheck.get(i).getX()) == (int)(pPoint.getX()) && (int)(arPointsCheck.get(i).getY()) == (int)(pPoint.getY()))
				trouve = true;
			i++;
		}
		
		return trouve;
	}

	public void ajoutDesVoisins()
	{
		Champ champ;
		Point point;
		int angle;
		int j;
		
		for(int i = 0; i < arChamps.size(); i++)
		{
			champ = arChamps.get(i);
			point = new Point(arChamps.get(i).getCentre().getX(), arChamps.get(i).getCentre().getY() - Terrain.d);
			j = 0;
			angle = 0;
			
			while(j < 6)
			{
				if(estDansLeTableau(calculPivot(champ.getCentre(), point, angle)))
					champ.ajoutVoisin(calculPivot(champ.getCentre(), point, angle));
				angle += 60;
				j++;
			}
		}
	}

	public void preparationConstructionChemin()
	{
		Chemin chemin;
		
		// Etape 1 On stocke les chemins autour des hexagones.
		for(int j = 0; j < arChamps.size(); j++)
		{
			if(arChamps.get(j).getNbreVoisins() == 6)
			{
				int a = 4, b = 5, c = 1, d = 2;
				for(int i = 0; i < 6; i++)
				{
					chemin = new Chemin();
					
					if(a == 6)
						a = 0;
					if(b == 6)
						b = 0;
					if(c == 6)
						c = 0;
					if(d == 6)
						d = 0;
					
					
					chemin.addPoint((int)arChamps.get(j).getCoordonnesPointsDuChamp()[a].getX(), (int)arChamps.get(j).getCoordonnesPointsDuChamp()[a].getY());
					chemin.addPoint((int)arChamps.get(j).getCoordonnesPointsDuChamp()[b].getX(), (int)arChamps.get(j).getCoordonnesPointsDuChamp()[b].getY());
					chemin.addPoint((int)arChamps.get(j).getVoisins().get(i).getCoordonnesPointsDuChamp()[c].getX(), (int)arChamps.get(j).getVoisins().get(i).getCoordonnesPointsDuChamp()[c].getY());
					chemin.addPoint((int)arChamps.get(j).getVoisins().get(i).getCoordonnesPointsDuChamp()[d].getX(), (int)arChamps.get(j).getVoisins().get(i).getCoordonnesPointsDuChamp()[d].getY());
					
					
					this.arChemins.add(chemin);
					
					a++;
					b++;
					c++;
					d++;
				}
			}
		}
		
		// On stocke les chemins autour du 3è niveau
		for(int i = superIndice; i < superIndice + (Terrain.niveau * 6); i++)
		{
			int a = 0, b = 1, c = 3, d = 4;
			for(int j = 0; j < 6; j++)
			{
				chemin = new Chemin();
				
				if(a == 6)
					a = 0;
				if(b == 6)
					b = 0;
				if(c == 6)
					c = 0;
				if(d == 6)
					d = 0;
				
				chemin.addPoint((int)arChamps.get(i).getVoisins().get(j).getCoordonnesPointsDuChamp()[a].getX(), (int)arChamps.get(i).getVoisins().get(j).getCoordonnesPointsDuChamp()[a].getY());
				chemin.addPoint((int)arChamps.get(i).getVoisins().get(j).getCoordonnesPointsDuChamp()[b].getX(), (int)arChamps.get(i).getVoisins().get(j).getCoordonnesPointsDuChamp()[b].getY());
				if(j != 5)
				{
					chemin.addPoint((int)arChamps.get(i).getVoisins().get(j + 1).getCoordonnesPointsDuChamp()[c].getX(), (int)arChamps.get(i).getVoisins().get(j + 1).getCoordonnesPointsDuChamp()[c].getY());
					chemin.addPoint((int)arChamps.get(i).getVoisins().get(j + 1).getCoordonnesPointsDuChamp()[d].getX(), (int)arChamps.get(i).getVoisins().get(j + 1).getCoordonnesPointsDuChamp()[d].getY());
				}
				else
				{
					chemin.addPoint((int)arChamps.get(i).getVoisins().get(0).getCoordonnesPointsDuChamp()[c].getX(), (int)arChamps.get(i).getVoisins().get(0).getCoordonnesPointsDuChamp()[c].getY());
					chemin.addPoint((int)arChamps.get(i).getVoisins().get(0).getCoordonnesPointsDuChamp()[d].getX(), (int)arChamps.get(i).getVoisins().get(0).getCoordonnesPointsDuChamp()[d].getY());
				}
				
				
				arChemins.add(chemin);
				
				a++;
				b++;
				c++;
				d++;
				
			}
		}
	}
}


