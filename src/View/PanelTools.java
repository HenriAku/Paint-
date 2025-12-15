import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
	private JMenuItem rotation;

	private JButton   btnSauvegarder;
	private JButton   btnAnnuler;

	private JTextField txtAngle;

	private Controller controller;
	
	// Constructeur
	public PanelTools(Controller ctrl)
	{
		this.controller = ctrl;

		JMenu menu   = new JMenu("Outils");
		this.toolBar = new JMenuBar();
		
		this.potPeinture  = new JMenuItem("Pot de peinture");
		this.contraste    = new JMenuItem("Contraste");
		this.ouvrirImage  = new JMenuItem("Ouvrir une image");
		this.text         = new JMenuItem("Texte");	
		this.rotation	  = new JMenuItem("Rotation");

		this.btnSauvegarder  = new JButton("Sauvegarder");
		this.btnAnnuler      = new JButton("Annuler");

		this.txtAngle    = new JTextField(10);

		// Ajout des composants
		this.toolBar.add(menu);
		this.toolBar.add(this.btnSauvegarder);
		this.toolBar.add(this.btnAnnuler);
		this.toolBar.add(this.txtAngle);

		menu.add(this.potPeinture);
		menu.add(this.contraste);
		menu.add(this.ouvrirImage);
		menu.add(this.text);
		menu.add(this.rotation);

		this.add(this.toolBar);

		// Activation des composants
		this.potPeinture.addActionListener(this);
		this.contraste  .addActionListener(this);
		this.ouvrirImage.addActionListener(this);
		this.text       .addActionListener(this);
		this.rotation   .addActionListener(this);

		this.btnSauvegarder.addActionListener(this);
		this.btnAnnuler    .addActionListener(this);

		this.txtAngle.addActionListener(this);
	}

	public void actionPerformed(java.awt.event.ActionEvent e) 
	{
		if (this.ouvrirImage == e.getSource()) 
		{
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new java.io.File("."));
			fileChooser.setDialogTitle("Choisir une image");
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooser.setAcceptAllFileFilterUsed(false);

			int result = fileChooser.showOpenDialog(this);
			
			if (result == JFileChooser.APPROVE_OPTION) {
				String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
				controller.addImage(selectedFilePath);
				controller.updateDessin();
			};

		} 
		
		if (this.potPeinture == e.getSource()) 
		{
			this.controller.addMouseDessin();
		}
		
		if (this.contraste == e.getSource()) 
		{
			Double contrastLevel = Double.parseDouble(this.txtAngle.getText());
			this.controller.adjustContrast(contrastLevel);
			this.controller.updateDessin();
		}
		
		if (this.text == e.getSource()) {
			// Activer l'outil texte
		}	

		if (this.rotation == e.getSource()) 
		{
			Double angleText = Double.parseDouble(this.txtAngle.getText());
			this.controller.rotation(angleText);
			this.controller.updateDessin();
		}

		if (this.btnSauvegarder == e.getSource()) {
			// Sauvegarder l'image
		}

		if (this.btnAnnuler == e.getSource()) 
		{
			this.controller.removeMouseDessin();
		}
		
	}
}
