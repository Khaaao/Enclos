import java.awt.Polygon;


public class Mouton extends Polygon{
	
	private Point centre;

	public Mouton(Point pPoint)
	{
		this.centre = pPoint;
	}
	
	public Point getCentre() {
		return centre;
	}

	public void setCentre(Point centre) {
		this.centre = centre;
	}
}
