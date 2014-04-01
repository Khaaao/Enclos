package modele;
import java.awt.Color;
import java.awt.Polygon;
 

public class Mouton extends Polygon{
	
	private Point centre;
	private Color color;

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Mouton(Point pPoint, Color color)
	{
		this.centre = pPoint;
		this.color = color;
	}
	
	public Point getCentre() {
		return centre;
	}

	public void setCentre(Point centre) {
		this.centre = centre;
	}
}
