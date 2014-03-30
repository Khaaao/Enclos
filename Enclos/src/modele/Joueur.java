package modele;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


public class Joueur {
	
	private String nom;
	private String prenom;
	private  ArrayList<Mouton> listeMoutons = new ArrayList<Mouton>();
	private boolean estActif;
	private boolean aChoisiMouton;
	private boolean aDeplacerMouton;
	private boolean aChoisiBarriere;
	static int idJoueur = 0;
	private int id;

	public Joueur(String nom, String prenom) {
	
		this.nom = nom;
		this.prenom = prenom;
		this.estActif = false;
		this.id = ++idJoueur;
	}

	public void ajouterMouton(Mouton mouton)
	{
		this.listeMoutons.add(mouton);
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setListeMoutons(ArrayList<Mouton> listeMoutons) {
		this.listeMoutons = listeMoutons;
	}
	
	public boolean isEstActif() {
		return estActif;
	}
	
	public void setEstActif(boolean estActif) {
		this.estActif = estActif;
	}

	public ArrayList<Mouton> getListeMoutons() {
		return listeMoutons;
	}
	
	// MUTATEURS
	public boolean getChoisirMouton()
	{
		return aChoisiMouton;
	}
	
	public void setChoisirMouton(boolean value)
	{
		this.aChoisiMouton = value;
	}
	
	public boolean getDeplacerMouton()
	{
		return aDeplacerMouton;
	}
	
	public void setDeplacerMouton(boolean value)
	{
		this.aDeplacerMouton = value;
	}
	
	public boolean getPlacerBarriere()
	{
		return aChoisiBarriere;
	}
	
	public void setPlacerBarriere(boolean value)
	{
		this.aChoisiBarriere = value;
	}
	
	public int getId()
	{
		return this.id;
	}
}
