import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuPrincipal extends JFrame{
	
	private JButton launchButton = new JButton("Jouer"), h2hButton = new JButton("Head To Head"), quitButton = new JButton("Quitter");
	private JLabel icon = new JLabel(new ImageIcon("images/menu_principal.jpg"));
	
	public MenuPrincipal(){
	    this.setTitle("Jeu de l'enclos");
	    this.setSize(700, 750);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    
	    JPanel panIcon = new JPanel();
	    panIcon.setBackground(Color.WHITE);
	    panIcon.setLayout(new BorderLayout());
	    panIcon.add(icon);
	    
	    JPanel control = new JPanel();
	    control.setBackground(Color.BLACK);
	    control.add(launchButton);
	    control.add(h2hButton);
	    control.add(quitButton);
	    	
	    this.getContentPane().add(control,BorderLayout.SOUTH);
	    this.getContentPane().add(panIcon, BorderLayout.CENTER);
	    
	    launchButton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent arg0) {
	    		Menu men = new Menu();
	    		setVisible(false); 
	    	}         
	    });
	    h2hButton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent arg0) {
	    		System.out.println("headtohead");
	    	}         
	    });
	    quitButton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent arg0) {
	    		System.exit(0);
	    	}
	    });
	    this.setVisible(true);
	}
}
