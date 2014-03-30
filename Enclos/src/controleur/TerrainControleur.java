package controleur;

import modele.Joueur;
import modele.TerrainModele;

public class TerrainControleur {
	
	private TerrainModele terrainModele;
	private Arbitre arbitre; // Va nous aider à mieux aiguiller les actions à utiliser sur le modèle
	 
	public TerrainControleur(TerrainModele pModele)
	{
		this.terrainModele = pModele;
		this.arbitre = new Arbitre(this.terrainModele);
		terrainModele.generationTerrain();
	}
	
	
	public void click(int x, int y)
	{
		Joueur joueur = this.arbitre.getJoueurActif();
		int etape = this.arbitre.checkEtapesJoueur(joueur);
		switch(etape)
		{
			case 1:
				if(terrainModele.pickMouton(joueur, x, y) >= 0)
				{
					this.arbitre.valideEtapes(etape, joueur);
					this.arbitre.sauvegardeIndiceMoutonADeplacer(terrainModele.pickMouton(joueur, x, y));
				}
			break;
			
			case 2:
				if(terrainModele.pickVoisin(joueur, this.arbitre.getSauvegardeIndiceMoutonADeplacer(), x, y))
					this.arbitre.valideEtapes(etape, joueur);
			break;
			case 3:
				if(terrainModele.pickChemin(x, y))
				{
					this.arbitre.valideEtapes(etape, joueur);
					this.arbitre.passeLaMainAuJoueurSuivant(joueur);
				}
			break;
			
			default:
		}
			
	}
	
	// On notifie le modèle
	public void control()
	{
		
	}

	// Nous sert pour le JPanel
	public TerrainModele getTerrainModele() {
		return terrainModele;
	}
}
