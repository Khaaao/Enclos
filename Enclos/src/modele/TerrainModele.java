package modele;
 
import java.util.ArrayList;
import java.util.Observable;


public class TerrainModele extends Observable{
	
	public static final int r = 25;
	static final int d = (int)(2.76 * r);
	static final int niveau = 2;
	
	private Point centre = new Point(300, 300);
	private ArrayList<Champ> arChamps = new ArrayList<Champ>();
	private ArrayList<Joueur> arJoueurs = new ArrayList<Joueur>();
	private ArrayList<Point> arPoints = new ArrayList<Point>();
	private ArrayList<Point> arPointsCheck = new ArrayList<Point>();
	
	public void ajouterJoueur()
	{
		Joueur joueur = new Joueur("FORSAIN", "Jean-Luc");
		Joueur joueur2 = new Joueur("KHAO", "Kévin");
		
		this.arJoueurs.add(joueur);
		this.arJoueurs.add(joueur2);
		
		this.arJoueurs.get(0).setEstActif(true);
	}
	
	public TerrainModele()
	{
		ajouterJoueur();
	}
	
	/* Méthode servant à la génération du terrain */
	public void generationTerrain()
	{
		arPointsCheck.add(centre);
		preparationDessinerVoisinsChamps(TerrainModele.niveau); // ETAPE 1.1 : on place les centres de chaque hexagone 
		arChamps.add(new Champ(centre));
		for(Point point : arPoints) // ETAPE 1.2 : On instancie les objets Champs selon les coordonnées des centres
			arChamps.add(new Champ(point));
		ajoutDesVoisins();  // ETAPE 1.3 : On identifie les voisins de chaque hexagone (6, 4 ou 3)
		
		/* === Etape 2 : Construction des chemins === */
		preparationConstructionChemin();
		
		/* === Etape 3 : Mise en place des pions des deux joueurs */
		placementPions();
		
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
		// On remplit les 6 prmeières cases de arPoints.
		Point nCentre = centre; // Sera mis à jour 
		Point point = new Point(nCentre.getX(), nCentre.getY() - d);
		
		int niveau = 1;
		int indiceSauvegarde = 0; // Sert pour nos parcours
		
		// On stocke dans arPoints les 6 points voisins du centre
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
			point = new Point(arChamps.get(i).getCentre().getX(), arChamps.get(i).getCentre().getY() - TerrainModele.d);
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

	// On prépare les chemins et on les sauvegarde dans les champs
	public void preparationConstructionChemin()
	{
		Chemin chemin;
		Point pointCentre, pivot;
		
		// Etape 1 : On stocke les chemins autour des hexagones + sauvegarde les chemins dans les champs en question
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
					
					arChamps.get(j).ajoutChemin(chemin);
					
					a++;
					b++;
					c++;
					d++;
				}
			}
			// On s'occupe des hexagones ayant 3 ou 4 voisins
			else
			{
				int a = 4, b = 5, c = 1, d = 2, indice = 0;
				pointCentre = arChamps.get(j).getCentre();
				pivot = new Point(arChamps.get(j).getCentre().getX(), arChamps.get(j).getCentre().getY() - TerrainModele.d);
				int angle = 0;
				
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
					
					if(estDansLeTableau(calculPivot(pointCentre, pivot, angle)))
					{
						chemin.addPoint((int)arChamps.get(j).getCoordonnesPointsDuChamp()[a].getX(), (int)arChamps.get(j).getCoordonnesPointsDuChamp()[a].getY());
						chemin.addPoint((int)arChamps.get(j).getCoordonnesPointsDuChamp()[b].getX(), (int)arChamps.get(j).getCoordonnesPointsDuChamp()[b].getY());
						chemin.addPoint((int)arChamps.get(j).getVoisins().get(indice).getCoordonnesPointsDuChamp()[c].getX(), (int)arChamps.get(j).getVoisins().get(indice).getCoordonnesPointsDuChamp()[c].getY());
						chemin.addPoint((int)arChamps.get(j).getVoisins().get(indice).getCoordonnesPointsDuChamp()[d].getX(), (int)arChamps.get(j).getVoisins().get(indice).getCoordonnesPointsDuChamp()[d].getY());
						arChamps.get(j).ajoutChemin(chemin);
						indice++;
					}
										
					a++;
					b++;
					c++;
					d++;
					angle += 60;
				}
			}
		}
	}

	public void placementPions()
	{
		for(int i = 1; i < 7; i++)
		{
			if(i%2 == 0)
			{
				arJoueurs.get(0).ajouterMouton(new Mouton(arChamps.get(i).getCentre()));
				arChamps.get(i).setAUnJoueur(true);
			}
			else
			{
				arJoueurs.get(1).ajouterMouton(new Mouton(arChamps.get(i).getCentre()));
				arChamps.get(i).setAUnJoueur(true);
			}
		}
	}
	
	// Méthode servant à savoir si qaund on clique les corrdonées sont dans le cercle
	public boolean inCircle(int circleX, int circleY, int clickX, int clickY, int radius){
		return Math.sqrt(Math.pow(circleX - clickX, 2) + Math.pow(circleY - clickY, 2)) <= radius;
	}
	
	// Méthode retournant vrai/faux selon que le joueur a choisi ou pas son mouton
	public int pickMouton(Joueur joueur, int x, int y)
	{
		for(int i = 0; i < joueur.getListeMoutons().size(); i++)
		{
			if(inCircle((int)joueur.getListeMoutons().get(i).getCentre().getX(), (int)joueur.getListeMoutons().get(i).getCentre().getY(), x, y, TerrainModele.r - 10))
				return i;
		}
		return -1;
	}
	
	// Méthode retournant vrai/faux selon le déplacement du mouton
	public boolean pickVoisin(Joueur joueurCourant, int indiceMoutonChoisi, int x, int y)
	{
		int p = 0;
		int indiceARetenir = 0;
		boolean trouveCentre = false;
		
		//1. On cherche dans le tableau champ, le champ correspond aux coordonnées du centre du mouton
		Point centreM = joueurCourant.getListeMoutons().get(indiceMoutonChoisi).getCentre();
		while(!trouveCentre && p < arChamps.size())
		{
			if(egalitePoints(centreM, arChamps.get(p).getCentre()))
			{
				indiceARetenir = p;
				trouveCentre = true;
			}
			p++;
		}
		
		//2. parcourt la liste des voisins du champ
		int l = 0;
		boolean trouve = false;
		while(l < arChamps.get(indiceARetenir).getVoisins().size() && !trouve)
		{
			// Si le clic est contenu dans un des voisins du mouton/champ
			if(arChamps.get(indiceARetenir).getVoisins().get(l).contains(x, y))
			{
				// Si le chemin n'est pas bloqué et que la case souhaité n'est pas occupé par un joueur
				if(!arChamps.get(indiceARetenir).getChemins().get(l).isBloque() && !arChamps.get(indiceARetenir).getVoisins().get(l).getaUnJoueur())
				{
					// Le centre du mouton est mis à jour
					joueurCourant.getListeMoutons().get(indiceMoutonChoisi).setCentre(arChamps.get(indiceARetenir).getVoisins().get(l).getCentre());
					trouve = true;
				}
			}
			l++;
		}
		
		if(trouve)
		{
			// On notifie à notre vue qu'un des pions du joueur a changé de place !
			setChanged();
			notifyObservers();
			return true;
		}
		else
			return false;
	}
	
	public boolean pickChemin(int x, int y)
	{
		int m = 0;
		int n;
		boolean trouveChemin = false;
		
		// Parcours des champs
		while(m < arChamps.size())
		{
			System.out.println(m);
			// Parcours des chemins d'un champ
			n = 0;
			while(n < arChamps.get(m).getChemins().size() && !trouveChemin)
			{
				System.out.println("test");
				// Si le point cliqué est contenu dans un chemin et qu'il n'est pas bloqué
				if(arChamps.get(m).getChemins().get(n).contains(x, y) && !arChamps.get(m).getChemins().get(n).isBloque())
				{
					System.out.println("test2");
					// Modification du chemin en rouge
					arChamps.get(m).getChemins().get(n).setBloque(true);
					miseAJourChemins(x, y);
					trouveChemin = true;
					
					// MàJ des vues
					setChanged();
					notifyObservers();
				}
				n++;
			}
			m++;
		}
		System.out.println(trouveChemin);
		return trouveChemin;
	}
	// Méthode testant l'égalité entre 2 points
	public boolean egalitePoints(Point point, Point point2)
	{
		return ((int)point.getX() == (int)point2.getX()) && ((int)point.getY() == (int)point2.getY());
	}
	
	public void miseAJourChemins(int x, int y)
	{
		int o = 0;
		int p;
		boolean trouveChemin = false;
		
		//Mise à jour des chemins concernés
		while(o < arChamps.size() && !trouveChemin)
		{
			p = 0;
			while(p < arChamps.get(o).getChemins().size() && !trouveChemin)
			{
				if(arChamps.get(o).getChemins().get(p).contains(x, y) && !arChamps.get(o).getChemins().get(p).isBloque())
				{
					// Modification de chemin
					arChamps.get(o).getChemins().get(p).setBloque(true);
					trouveChemin = true;
				}
				p++;
			}
			o++;
		}
	}
	
	
	/* MUTATEURS */
	public ArrayList<Champ> getArChamps() {
		return arChamps;
	}

	public void setArChamps(ArrayList<Champ> arChamps) {
		this.arChamps = arChamps;
		setChanged();
		notifyObservers();
	}

	public ArrayList<Joueur> getArJoueurs() {
		return arJoueurs;
	}

	public void setArJoueurs(ArrayList<Joueur> arJoueurs) {
		this.arJoueurs = arJoueurs;
		setChanged();
		notifyObservers();
	}
}
