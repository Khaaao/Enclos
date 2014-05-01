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
		System.out.println(indiceDuMoutonChosi);
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
	
//	public void faitTransiterMouton(Joueur joueur, int indiceBut)
//	{
//		//1. On récupère le centre du mouton de départ
//		int indiceARetenir = this.modele.recuperationIndice(joueur.getListeMoutons().get(this.indiceDuMoutonChosi).getCentre());
//		Point pointDepart = new Point(this.modele.getArChamps().get(indiceARetenir).getCentre().getX(), this.modele.getArChamps().get(indiceARetenir).getCentre().getY());
//		
//		//2. On récupère le centre de l'hexgaone du point d'arrivée
//		Champ champ;
//		champ = this.modele.getArChamps().get(this.modele.recuperationIndice(this.modele.getArChamps().get(indiceARetenir).getVoisins().get(indiceBut).getCentre()));
//		int xF = (int)champ.getCentre().getX();
//		int yF = (int)champ.getCentre().getY();
//		
//		//3. On définit nos variables de transition
//		int xD = (int)pointDepart.getX(), yD = (int)pointDepart.getY();
//		
//		while(xD < xF || yD < yF)
//		{
//			++xD;
//			++yD;
//			Point point = new Point(xD, yD);
//			joueur.getListeMoutons().get(indiceARetenir).setCentre(point);
//			try{
//				Thread.sleep(1);
//				modele.notifierVues();
//			}catch(InterruptedException e)
//			{
//				e.printStackTrace();
//			}
//		}
//	}
	
	public void arreteClignotementMoutonSelectionDuJoueur(Joueur joueurCourant)
	{
		timer.cancel();
		for(Mouton mouton : joueurCourant.getListeMoutons())
			mouton.setColor(joueurCourant.getColor());
		modele.notifierVues();
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
		boolean finDePartie = false;
		return finDePartie;
	}
	
	public void proposerRejouer()
	{
		
	}
	
	public void virePionsJoueurQuiAPerdu()
	{
		
	}
	
	public void lanceLaPartie()
	{
		
	}
	
	public void maintienPartie()
	{
		
	}
}
