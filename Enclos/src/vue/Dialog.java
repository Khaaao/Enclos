package vue;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class Dialog extends JDialog {
	public Dialog(JFrame parent, String title, boolean modal){
	    //On appelle le construteur de JDialog correspondant
	    super(parent, title, modal);
	    //On sp�cifie une taille
	    this.setSize(200, 80);
	    //La position
	    this.setLocationRelativeTo(null);
	    //La bo�te ne devra pas �tre redimensionnable
	    this.setResizable(false);
	    //Enfin on l'affiche
	    this.setVisible(true);
	    //Tout ceci ressemble � ce que nous faisons depuis le d�but avec notre JFrame.
	}
}
