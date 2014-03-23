import java.awt.Polygon;
import java.util.ArrayList;


// JL
public class Champ extends Polygon {
	
	private ArrayList<Chemin> chemins;
	private ArrayList<Champ> voisins = new ArrayList<Champ>();
	private Point[] coordonnesPointsDuChamp = new Point[6]; // Va nous permettre d'avoir les coordonnées de chaque point d'un champ
	private Point centre; // Coordonnées du centre du point
	private boolean aUnMouton;
	
	public ArrayList<Champ> getVoisins() {
		return voisins;
	}

	public Point[] getCoordonnesPointsDuChamp() {
		return coordonnesPointsDuChamp;
	}

	public Champ(Point pPoint)
	{
		this.centre = pPoint;
		calculEmplacementChamp(centre);
	}
	
	/* Méthode servant à l'enregistrement de ses sommets */
	public void calculEmplacementChamp(Point pPoint)
	{
		int i = 0;
		for (i = 0; i < 6; i++) {
			// On enregistre les sommets de l'hexagone
			coordonnesPointsDuChamp[i] = new Point(pPoint.getX() + Terrain.r * Math.cos(i * 2 * Math.PI / 6), pPoint.getY() + Terrain.r * Math.sin(i * 2 * Math.PI / 6));
			
			this.addPoint(
				(int)(pPoint.getX() + Terrain.r * Math.cos(i * 2 * Math.PI / 6)),
				(int)(pPoint.getY() + Terrain.r * Math.sin(i * 2 * Math.PI / 6))
			);
		}	
	}
	
	/* Méthode permettant d'avoir le centre des six voisins de l'hexagone en question */
	public void ajoutVoisinsCentreCoordonnees()
	{
		// Instanciation
		Point pointDepart = new Point(this.centre.getX(), this.centre.getY() - Terrain.d);
		int angle = 0;
		
		// Calcule les centres des hexagones voisins
		for(int j = 0; j < 6; j++)
		{
			coordonnesPointsDuChamp[j] = calculPivot(this.centre, pointDepart, angle);
			angle = angle + 60;
		}
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
	
	/* Méthode permettant d'ajouter un vosin à l'heagone courant */
	public void ajoutVoisin(Point pPoint)
	{
		Champ champ = new Champ(pPoint);
		voisins.add(champ);
	}

	
	public Point getCentre() {
		return centre;
	}
	
	public int getNbreVoisins()
	{
		return  voisins.size();
	}
	
	public void ajoutChemin()
	{
		
	}
}


