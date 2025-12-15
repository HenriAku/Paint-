import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class PanelTools extends JPanel implements ActionListener
{
	// Attributs
	private JMenuBar  toolBar;
	private JMenuItem potPeinture;
	private JMenuItem contraste;
	private JMenuItem ouvrirImage;
	private JMenuItem text;
	private JMenuItem sauvegarder;

	private Controller controller;
	
	// Constructeur
	public PanelTools(Controller ctrl)
	{
		this.controller = ctrl;

		JMenu menu   = new JMenu("Outils");
		this.toolBar = new JMenuBar();
		
		this.potPeinture  = new JMenuItem("Pot de peinture");
		this.contraste    = new JMenuItem("Contraste");
		this.ouvrirImage = new JMenuItem("Ouvrir une image");
		this.text         = new JMenuItem("Texte");	
		this.sauvegarder  = new JMenuItem("Sauvegarder");


		// Ajout des composants
		this.toolBar.add(menu);
		this.toolBar.add(this.sauvegarder);

		menu.add(this.potPeinture);
		menu.add(this.contraste);
		menu.add(this.ouvrirImage);
		menu.add(this.text);

		this.add(this.toolBar);

		this.potPeinture.addActionListener(this);
		this.contraste.addActionListener(this);
		this.ouvrirImage.addActionListener(this);
		this.text.addActionListener(this);
		this.sauvegarder.addActionListener(this);
	}

	public void actionPerformed(java.awt.event.ActionEvent e) 
	{
		if (this.ouvrirImage == e.getSource()) {
			OpenImage openImage = new OpenImage(this.controller);
		} else if (this.sauvegarder == e.getSource()) {
			// Sauvegarder l'image
		} else if (this.potPeinture == e.getSource()) {
			// Activer l'outil pot de peinture
		} else if (this.contraste == e.getSource()) {
			// Activer l'outil contraste
		} else if (this.text == e.getSource()) {
			// Activer l'outil texte
		}
		
	}
}
