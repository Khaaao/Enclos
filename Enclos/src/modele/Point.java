package modele;
 
public class Point {
	
	private float x;
	private float y;
	
	public Point(float pX, float pY)
	{
		this.x = pX;
		this.y = pY;
	}
	
	public Point()
	{
		
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}
