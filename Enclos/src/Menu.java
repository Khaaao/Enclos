import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Menu extends JFrame{

	private JButton fastButton = new JButton("Partie Rapide"), resumeButton = new JButton("Reprendre"), profilButton = new JButton("Gestion des profils"), settingButton = new JButton("Parametre du jeu"), cancelButton = new JButton("Retour au menu principal");

	public Menu(){
		this.setTitle("Jeu de l'enclos");
	    this.setSize(700, 800);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);      
	    this.getContentPane().setLayout(new FlowLayout());
	    this.getContentPane().add(fastButton);
	    this.getContentPane().add(resumeButton);
	    this.getContentPane().add(profilButton);
	    this.getContentPane().add(settingButton);
	    this.getContentPane().add(cancelButton);
	    fastButton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent arg0) {
	    		JeuEnclos newfastgame = new JeuEnclos();
	    		setVisible(false); 
	    	}         
	    });
	    resumeButton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent arg0) {
	    		System.out.println("Coder les sauvegardes");
	    	}         
	    });
	    profilButton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent arg0) {
	    		System.out.println("Coder les profils");
	    	}
	    });
	    settingButton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent arg0) {
	    		System.out.println("Coder les parametres du jeu");
	    	}
	    });
	    cancelButton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent arg0) {
	    		MenuPrincipal mainMenu = new MenuPrincipal();
	    		setVisible(false);
	    	}
	    });
	    this.setVisible(true);
	}
}
