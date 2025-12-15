import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class PanelTools extends JPanel
{
	// Attributs
	private JMenuBar toolBar;
	private JMenuItem potPeinture;
	private JMenuItem contraste;
	private JMenuItem ouvrireImage;
	private JMenuItem text;
	private JMenuItem sauvegarder;
	
	// Constructeur
	public PanelTools()
	{
		JMenu menu = new JMenu("Outils");
		this.toolBar = new JMenuBar();
		this.toolBar.add(menu);
		
		this.potPeinture  = new JMenuItem("Pot de peinture");
		this.contraste    = new JMenuItem("Contraste");
		this.ouvrireImage = new JMenuItem("Ouvrir une image");
		this.text         = new JMenuItem("Texte");	
		this.sauvegarder  = new JMenuItem("Sauvegarder");

		menu.add(this.potPeinture);
		menu.add(this.contraste);
		menu.add(this.ouvrireImage);
		menu.add(this.text);
		this.toolBar.add(this.sauvegarder);

		this.add(this.toolBar);
	}
}
