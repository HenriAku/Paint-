import java.awt.BorderLayout;

import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.event.ChangeListener;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.BoxLayout; // Import BoxLayout

public class PanelTools extends JPanel
{
	// Attributs
	private JMenuItem potPeinture;
	private JMenuItem contraste;
	private JMenuItem ouvrirImage;
	private JMenuItem fusioner;
	private JMenuItem text;
	private JMenuItem rotation;
	private JMenuItem flip;
	private JMenuItem luminosité;
	private JMenuItem btnSauvegarder;
	private JMenuItem btnAnnuler;

	private JTextField txtAngle;
	private JSlider    sliderTolerance;
	private JSlider    sliderLuminosite;
	private JSlider    sliderContraste;
	private JColorChooser colorChooser;


	private Controller controller;

	private PanelPalette   panelPalette;
	private PanelParametre panelParametres;
	
	// Constructeur
	public PanelTools(Controller ctrl)
	{
		this.controller = ctrl;

		JMenuBar    menu          = new JMenuBar();
		JMenu barContraste  = new JMenu("Contraste");
		JMenu barLuminosite = new JMenu("Luminosité");
		JMenu barRotation   = new JMenu("Rotation");
		JMenu barPeinture   = new JMenu("Peinture");

		this.potPeinture  = new JMenuItem("Pot de peinture");
		this.colorChooser = new JColorChooser();

		this.contraste       = new JMenuItem("Contraste");
		this.sliderContraste = new JSlider(-100, 100, 0);

		this.rotation	  = new JMenuItem("Rotation");
		this.txtAngle    = new JTextField(10);

		this.luminosité   = new JMenuItem("Luminosité");
		this.sliderLuminosite = new JSlider(-255, 255, 0);

		this.flip		  = new JMenuItem("Flip");
		this.ouvrirImage  = new JMenuItem("Ouvrir une image");
		this.text         = new JMenuItem("Texte");	

		this.btnSauvegarder  = new JMenuItem("Sauvegarder");
		this.btnAnnuler      = new JMenuItem("Annuler");


		// Ajout des composants
		menu.add(barContraste);
		menu.add(barLuminosite);
		menu.add(barRotation);
		menu.add(barPeinture);

		menu.add(this.ouvrirImage);
		menu.add(this.text);

		barPeinture.add(this.potPeinture);
		barPeinture.add(this.colorChooser);

		barContraste.add(this.contraste);
		barContraste.add(this.sliderContraste);

		barRotation.add(this.rotation);
		barRotation.add(this.txtAngle);

		barLuminosite.add(this.luminosité);
		barLuminosite.add(this.sliderLuminosite);

		menu.add(this.btnSauvegarder);
		menu.add(this.btnAnnuler);

		this.add(menu);

		// Activation des composants
		this.potPeinture.addActionListener(this);

		this.contraste  .addActionListener(this);

		this.ouvrirImage.addActionListener(this);
		this.text       .addActionListener(this);
		this.rotation   .addActionListener(this);
		this.luminosité .addActionListener(this);

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
			this.controller.addMouseDessin(this.colorChooser.getColor().getRGB());
		}
		
		if (this.contraste == e.getSource()) 
		{
			Double contrastLevel = (double) this.sliderContraste.getValue();
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

		if (this.luminosité == e.getSource()) 
		{
			Integer brightnessLevel = (Integer) this.sliderLuminosite.getValue();
			this.controller.adjustBrightness(brightnessLevel);
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
