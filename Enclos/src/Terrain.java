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
	static final int r = 20;
	static final int d = 55;
	static final int niveau = 4;
	
	// Centre de notre Terrain
	private Point centre = new Point(300, 300);
	
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
		
		//ajoutDesVoisins();  // ETAPE 3	
		
		/* === Etape 2 : Construction des chemins === */
		
		
	}
	
	// Dessine le terrain
	public void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);
		graphics.setColor(Color.LIGHT_GRAY);
		for(int i = 0; i < arChamps.size(); i++)
			graphics.fillPolygon(arChamps.get(i));
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
}


