package controleur;
import java.io.File;

import modele.*;

public class Arbitre {

	private TerrainModele modele;
	private int indiceDuMoutonChosi;
	
	public Arbitre(TerrainModele pModele)
	{
		this.modele = pModele;
	}
	
	public void abandon(Joueur joueur)
	{
		
	}
	
	public void annulerCoup(Joueur joueur)
	{
		
	}
	
	public void changerJoueur()
	{ 
		
	}
	
	public void chargerPartie(File fichier)
	{
		
	}
	
	
	public void illumineMouvementObligatoire()
	{
		
	}
	
	public void finDePartie()
	{
		
	}
	
	public Joueur getJoueurActif()
	{
		for(Joueur joueur : modele.getArJoueurs())
			if(joueur.isEstActif())
				return joueur;
		
		return null;
	}
	
	public void nouvellePartie()
	{
		
	}
	
	public void sauvegarderPartie(File file)
	{
		
	}
	
	
	public void setJoueurs()
	{
		
	}
	
	public int checkEtapesJoueur(Joueur joueur)
	{
		if(!joueur.getChoisirMouton())
			return 1;
		if(!joueur.getDeplacerMouton())
			return 2;
		if(!joueur.getPlacerBarriere())
			return 3;
		
		return 0;
		
	}
	
	public void valideEtapes(int nEtape, Joueur joueurCourant)
	{
		if(nEtape == 1)
			joueurCourant.setChoisirMouton(true);
		if(nEtape == 2)
			joueurCourant.setDeplacerMouton(true);
		if(nEtape == 3)
			joueurCourant.setDeplacerMouton(true);
		
	}
	
	public void sauvegardeIndiceMoutonADeplacer(int indice)
	{
		this.indiceDuMoutonChosi = indice;
	}

	public int getSauvegardeIndiceMoutonADeplacer()
	{
		return indiceDuMoutonChosi;
	}

	public void passeLaMainAuJoueurSuivant(Joueur joueur)
	{
		// On remet à zéro les étapes 1, 2 et 3 du joueur courant
		joueur.setEstActif(false);
		joueur.setChoisirMouton(false);
		joueur.setDeplacerMouton(false);
		joueur.setPlacerBarriere(false);
		
		// C'est au tour du prochain joueur de jouer !
		if(joueur.getId() == modele.getArJoueurs().size())
			this.modele.getArJoueurs().get(0).setEstActif(true);
		else
			this.modele.getArJoueurs().get(joueur.getId()).setEstActif(true);
	}
}
