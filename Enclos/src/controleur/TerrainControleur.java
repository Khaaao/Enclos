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
					System.out.println("Mouton sélectionné");
					this.arbitre.faitClignoterMoutonSelectionneDuJoueur(joueur);
				}
				else if(terrainModele.pickMouton(joueur, x, y) == -1)
					System.out.println("Vous n'avez pas sélectionné un de vos moutons");
				else
					System.out.println("Ce mouton ne peut aller nulle part. Veuillez en choisir un autre.");
			break;
			
			case 2:
				if(terrainModele.pickVoisin(joueur, this.arbitre.getSauvegardeIndiceMoutonADeplacer(), x, y) == 1)
				{
					this.arbitre.arreteClignotementMoutonSelectionDuJoueur(joueur);
//					this.arbitre.faitTransiterMouton(joueur, terrainModele.pickVoisin(joueur, this.arbitre.getSauvegardeIndiceMoutonADeplacer(), x, y));
					this.arbitre.valideEtapes(etape, joueur);
					System.out.println("Déplacement validé");
				}
				else if(terrainModele.pickVoisin(joueur, this.arbitre.getSauvegardeIndiceMoutonADeplacer(), x, y) == -1)
					System.out.println("Déplacement non autorisé : Le chemin est bloqué !");
				else if(terrainModele.pickVoisin(joueur, this.arbitre.getSauvegardeIndiceMoutonADeplacer(), x, y) == -2)
					System.out.println("Déplacement non autorisé : La case est déjà occupé par un mouton");
				else
					System.out.println("Déplacement non autorisé : Choisissez une case voisine à celle sélectionnée !");
			break;
			case 3:
				if(terrainModele.pickChemin(x, y) == 1)
				{
					this.arbitre.valideEtapes(etape, joueur);
					this.arbitre.passeLaMainAuJoueurSuivant(joueur);
					System.out.println("Blocage d'un chemin");
				}
				else if(terrainModele.pickChemin(x, y) == -1)
					System.out.println("Le chemin est déjà bloqué !");
				else 
					System.out.println("Veuillez choisir un chemin à bloquer !");
			break;
			
			default:
		}
			
	}
	
	// Nous sert pour le JPanel
	public TerrainModele getTerrainModele() {
		return terrainModele;
	}
}
