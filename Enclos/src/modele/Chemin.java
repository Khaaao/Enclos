package modele;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


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
