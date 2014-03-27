import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.ArrayList;


public class Joueur extends MouseAdapter{
	
	private String nom;
	private String prenom;
	private  ArrayList<Mouton> listeMoutons = new ArrayList<Mouton>();
	private int id;
	
	public int getId() {
		return id;
	}

	private static int idJoueur = 0;

	public Joueur(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.id = idJoueur++;
	}
	
	public ArrayList<Mouton> getListeMoutons() {
		return listeMoutons;
	}

	public void deplacerMouton()
	{
		
	}
	
	public void ajouterMouton(Mouton mouton)
	{
		this.listeMoutons.add(mouton);
	}
	
	
}
