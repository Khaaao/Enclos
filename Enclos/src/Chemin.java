import java.awt.Polygon;


public class Chemin extends Polygon {
	
	private boolean bloque;
	
	public Chemin()
	{
		this.bloque = false;
	}

	public boolean isBloque() {
		return bloque;
	}

	public void setBloque(boolean bloque) {
		this.bloque = bloque;
	}
}
