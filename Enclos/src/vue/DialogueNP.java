//package vue;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.JButton;
//import javax.swing.JDialog;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//
//public class DialogueNP extends JDialog implements ActionListener {
//	
//	JButton valider = new JButton("Valider");
//	JButton annuler = new JButton("Annuler");
//	JButton nouveau = new JButton("Nouveau profil");
//	 
//	
//	 
//	public DialogueNP()
//	{
//		JPanel panneau;
//		setTitle("");
//		panneau = new JPanel();
//		
//		panneau.add(new JLabel("pseudo du joueur : "));
//		panneau.ad
//		nouveau.addActionListener(this);
//		valider.addActionListener(this);
//		annuler.addActionListener(this);
//	}
//	
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		Object source = e.getSource();
//		
//		if(source == annuler)
//			dispose();
//		if(source == valider)
//		{
//			
//		}
//		
//		if(source == nouveau)
//		{
//			DialogueCreationProfil creationProfil = new DialogueCreationProfil();
//		}
//	}
//	 
//	 
//}
