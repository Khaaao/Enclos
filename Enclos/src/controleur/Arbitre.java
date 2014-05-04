package controleur;
import java.awt.Color;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import modele.*;

public class Arbitre {

	private TerrainModele modele;
	private int indiceDuMoutonChosi;
	private Timer timer;
	
	public Arbitre(TerrainModele pModele)
	{
		this.modele = pModele;
	}
	
	public void faitClignoterMoutonSelectionneDuJoueur(Joueur joueurCourant)
	{
		final Color colorSave = joueurCourant.getColor();
		final Joueur joueur = joueurCourant;
		this.timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(joueur.getListeMoutons().get(indiceDuMoutonChosi).getColor() != TerrainModele.colorTerrain)
					joueur.getListeMoutons().get(indiceDuMoutonChosi).setColor(TerrainModele.colorTerrain);
				else
					joueur.getListeMoutons().get(indiceDuMoutonChosi).setColor(colorSave);
				
				modele.notifierVues();
			}
		}, 0, 1000);
		
	}
	
	public void arreteClignotementMoutonSelectionDuJoueur(Joueur joueurCourant)
	{
		timer.cancel();
		for(Mouton mouton : joueurCourant.getListeMoutons())
			mouton.setColor(joueurCourant.getColor());
		modele.notifierVues();
	}
	
	public Joueur getJoueurActif()
	{
		for(Joueur joueur : modele.getArJoueurs())
			if(joueur.isEstActif())
				return joueur;
		
		return null;
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
	
	public boolean verifieSiFinDePartie()
	{	
		int nbreJoueurs = this.modele.getNbreJoueurs();
		int nbreDeJoueursAyantPerdu = 0;
		boolean finDePartie = false;
		
		// 1. Appel de la méthode qui va checker si les moutons de chaque joueur sont bloqués
		this.modele.bilanTour();
		// 2. L'arbitre aura juste a voir si l'attr. aPerdu est vrai pout incrémenter nbreDeJoueursAyantPerdu
		for(Joueur joueur : this.modele.getArJoueurs())
		{
			if(joueur.getAPerdu())
				nbreDeJoueursAyantPerdu++;
		}
		
		if(nbreDeJoueursAyantPerdu == nbreJoueurs - 1)
			finDePartie = true;
		
		return finDePartie;
	}
}
