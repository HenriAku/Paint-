import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class PanelParametre extends JPanel implements ChangeListener, ActionListener
{
	private Controller controller;
	private CardLayout cardLayout; 

	private JSlider contrasteSlider;
	private JSlider luminositeSlider;
	private JSlider rotationSlider;
	private JSlider rTeinteSlider;
	private JSlider gTeinteSlider;
	private JSlider bTeinteSlider;

	private JButton btnHorizontal;
	private JButton btnVertical;
	private JButton btnFusion;
	private JButton btnSelectionFusion;
	private JButton btnRedimension;

	private JTextField txtRedimensionHauteur;
	private JTextField txtRedimensionLargeur;

	public PanelParametre(Controller ctrl)
	{
		this.controller = ctrl;
		this.cardLayout = new CardLayout();

		this.setLayout(this.cardLayout);

		// Ajout des panneaux de paramètres

		// Pot de Peinture
		JPanel panelPotPeinture = new JPanel();	
		this.add(panelPotPeinture, "Pot de Peinture");

		// Contraste
		JPanel panelContraste = new JPanel(new BorderLayout());

		this.contrasteSlider = new JSlider(-100, 100, 0);
		this.contrasteSlider.setMajorTickSpacing(50);
		this.contrasteSlider.setMinorTickSpacing(10);
		this.contrasteSlider.setPaintTicks(true);
		this.contrasteSlider.setPaintLabels(true);
		this.contrasteSlider.setName("Contraste");
		this.contrasteSlider.addChangeListener(this);

		JLabel titre = new JLabel("Ajuster le Contraste", JLabel.CENTER);
		panelContraste.add(titre,BorderLayout.NORTH);
		panelContraste.add(this.contrasteSlider, BorderLayout.CENTER);
	
		this.add(panelContraste, "Contraste");

		// Texte
		JPanel panelTexte = new JPanel();
		this.add(panelTexte, "Texte");

		// Rotation
		JPanel panelRotation = new JPanel(new BorderLayout());

		this.rotationSlider = new JSlider(-180, 180, 0);
		this.rotationSlider.setMajorTickSpacing(90);
		this.rotationSlider.setMinorTickSpacing(15);
		this.rotationSlider.setPaintTicks(true);
		this.rotationSlider.setPaintLabels(true);
		this.rotationSlider.setName("Rotation");
		this.rotationSlider.addChangeListener(this);

		JLabel titreRotation = new JLabel("Ajuster la Rotation", JLabel.CENTER);
		panelRotation.add(titreRotation,BorderLayout.NORTH);
		panelRotation.add(this.rotationSlider, BorderLayout.CENTER);

		this.add(panelRotation, "Rotation");

		// Luminosité
		JPanel panelLuminosité = new JPanel(new BorderLayout());

		this.luminositeSlider = new JSlider(-100, 100, 0);
		this.luminositeSlider.setMajorTickSpacing(50);
		this.luminositeSlider.setMinorTickSpacing(10);
		this.luminositeSlider.setPaintTicks(true);
		this.luminositeSlider.setPaintLabels(true);
		this.luminositeSlider.setName("Luminosité");
		this.luminositeSlider.addChangeListener(this);

		JLabel titreLuminosité = new JLabel("Ajuster la Luminosité", JLabel.CENTER);
		panelLuminosité.add(titreLuminosité,BorderLayout.NORTH);
		panelLuminosité.add(this.luminositeSlider, BorderLayout.CENTER);
		
		this.add(panelLuminosité, "Luminosité");

		// Teinte
		JPanel panelTeinte = new JPanel(new BorderLayout());

		this.rTeinteSlider = new JSlider(-255, 255, 0);
		this.gTeinteSlider = new JSlider(-255, 255, 0);
		this.bTeinteSlider = new JSlider(-255, 255, 0);

		this.rTeinteSlider.setMajorTickSpacing(255);
		this.rTeinteSlider.setMinorTickSpacing(51);
		this.rTeinteSlider.setPaintTicks(true);
		this.rTeinteSlider.setPaintLabels(true);
		this.rTeinteSlider.setName("RTeinte");
		this.rTeinteSlider.addChangeListener(this);

		this.gTeinteSlider.setMajorTickSpacing(255);
		this.gTeinteSlider.setMinorTickSpacing(51);
		this.gTeinteSlider.setPaintTicks(true);
		this.gTeinteSlider.setPaintLabels(true);
		this.gTeinteSlider.setName("GTeinte");
		this.gTeinteSlider.addChangeListener(this);

		this.bTeinteSlider.setMajorTickSpacing(255);
		this.bTeinteSlider.setMinorTickSpacing(51);
		this.bTeinteSlider.setPaintTicks(true);
		this.bTeinteSlider.setPaintLabels(true);
		this.bTeinteSlider.setName("BTeinte");
		this.bTeinteSlider.addChangeListener(this);

		JLabel titreTeinte = new JLabel("Ajuster la Teinte", JLabel.CENTER);
		panelTeinte.add(titreTeinte,BorderLayout.NORTH);
		JPanel slidersPanel = new JPanel();
		slidersPanel.setLayout(new BorderLayout());
		slidersPanel.add(this.rTeinteSlider, BorderLayout.NORTH);
		slidersPanel.add(this.gTeinteSlider, BorderLayout.CENTER);
		slidersPanel.add(this.bTeinteSlider, BorderLayout.SOUTH);
		panelTeinte.add(slidersPanel, BorderLayout.CENTER);
		this.add(panelTeinte, "Teinte");

		// Texte
		JPanel panelTexteParam = new JPanel();
		panelTexteParam.setLayout(new BoxLayout(panelTexteParam, BoxLayout.Y_AXIS));

		JTextField txtInput = new JTextField(20);
		txtInput.setMaximumSize(txtInput.getPreferredSize());
		JLabel labelInput = new JLabel("Texte à insérer :");

		String[] textures = this.getTextureFiles();
		JComboBox<String> textureComboBox = new JComboBox<>(textures);
		textureComboBox.setMaximumSize(textureComboBox.getPreferredSize());
		JLabel labelTexture = new JLabel("Sélectionner une texture :");

		panelTexteParam.add(labelInput);
		panelTexteParam.add(txtInput);
		panelTexteParam.add(Box.createVerticalStrut(10));
		panelTexteParam.add(labelTexture);
		panelTexteParam.add(textureComboBox);


		this.add(panelTexteParam, "Texte");

		// Miroir
		JPanel panelMiroir = new JPanel();
		panelMiroir.setLayout(new BoxLayout(panelMiroir, BoxLayout.Y_AXIS));

		this.btnHorizontal = new JButton("Miroir Horizontal");
		this.btnHorizontal.setAlignmentX(CENTER_ALIGNMENT);

		this.btnVertical = new JButton("Miroir Vertical");
		this.btnVertical.setAlignmentX(CENTER_ALIGNMENT);

		panelMiroir.add(btnHorizontal);
		panelMiroir.add(Box.createVerticalStrut(10));
		panelMiroir.add(btnVertical);

		this.btnHorizontal.addActionListener(this);
		this.btnVertical.addActionListener(this);

		this.add(panelMiroir, "Miroir");


		// Fusion
		JPanel panelFusion = new JPanel(new BorderLayout());

		this.btnFusion          = new JButton("Fusionner Images");
		this.btnSelectionFusion = new JButton("Sélectionner Image à Fusionner");
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.add(this.btnFusion, CENTER_ALIGNMENT);
		centerPanel.add(this.btnSelectionFusion, CENTER_ALIGNMENT);
		
		panelFusion.add(centerPanel, BorderLayout.CENTER);
		
		this.add(panelFusion, "Fusion");		
		this.btnFusion.addActionListener(this);
		this.btnSelectionFusion.addActionListener(this);

		// Redimension
		JPanel panelRedimension = new JPanel(new BorderLayout());
		this.btnRedimension = new JButton("Redimensionner Image");
		this.txtRedimensionLargeur = new JTextField();
		this.txtRedimensionHauteur = new JTextField();

		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
		inputPanel.add(new JLabel("Largeur :"));
		inputPanel.add(this.txtRedimensionLargeur);
		inputPanel.add(new JLabel("Hauteur :"));
		inputPanel.add(this.txtRedimensionHauteur);
		inputPanel.add(this.btnRedimension);

		panelRedimension.add(inputPanel, BorderLayout.CENTER);
		this.add(panelRedimension, "Redimension");
		this.btnRedimension.addActionListener(this);

		// Panneau par défaut
		JPanel panelDefault = new JPanel();
		this.add(panelDefault, "Default");
		this.showCard("Default");
	}

	// Méthode pour afficher un panneau spécifique
	public void showCard(String cardName)
	{
		this.cardLayout.show(this, cardName); 
	}

	// Méthode pour gérer les actions des boutons
	public void actionPerformed(ActionEvent e)
	{
		if (this.btnHorizontal == e.getSource()) 
		{
			this.controller.mirrorHorizontal(); 
			this.controller.updateDessin();
		}

		if(this.btnVertical == e.getSource()) 
		{
			this.controller.mirrorVertical(); 
			this.controller.updateDessin();
		}

		if(this.btnFusion == e.getSource()) 
		{
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new java.io.File("."));
			fileChooser.setDialogTitle("Choisir une image");
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooser.setAcceptAllFileFilterUsed(false);

			int result = fileChooser.showOpenDialog(this);
			
			if (result == JFileChooser.APPROVE_OPTION) 
			{

				String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
				this.controller.setChemin(selectedFilePath);
			};
		}

		if (this.btnSelectionFusion == e.getSource()) 
		{
			this.controller.addMouseDessin();	
		}

		if (this.btnRedimension == e.getSource()) 
		{
			try {
				int newWidth  = Integer.parseInt(this.txtRedimensionLargeur.getText());
				int newHeight = Integer.parseInt(this.txtRedimensionHauteur.getText());
				this.controller.redimensionner(newWidth, newHeight);
				this.controller.updateDessin();
			} catch (NumberFormatException ex) {
				System.err.println("Erreur: Veuillez entrer des valeurs numériques valides pour la largeur et la hauteur.");
			}
		}
	}

	// Méthode pour gérer les actions
	public void stateChanged(ChangeEvent e)
	{
		if(this.contrasteSlider == e.getSource())
		{
			int contrastLevel = (int) this.contrasteSlider.getValue();
			this.controller.adjustContrast(contrastLevel);
			this.controller.updateDessin();
		}

		if(this.luminositeSlider == e.getSource())
		{
			int brightnessLevel = (int) this.luminositeSlider.getValue();
			this.controller.adjustBrightness(brightnessLevel);
			this.controller.updateDessin();
		}

		if(this.rotationSlider == e.getSource())
		{
			int angle = (int) this.rotationSlider.getValue();
			this.controller.rotation(angle);
			this.controller.updateDessin();
		}

		if(this.rTeinteSlider == e.getSource())
		{
			int rOffset = (int) this.rTeinteSlider.getValue();
			this.controller.adjustHue(this.controller.getBufferedImage(), rOffset, 0, 0);
			this.controller.updateDessin();
		}

		if(this.gTeinteSlider == e.getSource())
		{
			int gOffset = (int) this.gTeinteSlider.getValue();
			this.controller.adjustHue(this.controller.getBufferedImage(), 0, gOffset, 0);
			this.controller.updateDessin();
		}

		if(this.bTeinteSlider == e.getSource())
		{
			int bOffset = (int) this.bTeinteSlider.getValue();
			this.controller.adjustHue(this.controller.getBufferedImage(), 0, 0, bOffset);
			this.controller.updateDessin();
		}
	}

	private String[] getTextureFiles() 
	{
		// Le chemin doit être relatif à l'exécution de l'application
		File textureDir = new File("Paint-/ressources/textures"); 
		
		if (!textureDir.exists() || !textureDir.isDirectory()) 
		{
			System.err.println("Erreur: Le dossier 'textures/' est introuvable ou n'est pas un répertoire.");
			System.err.println("Chemin cherché: " + textureDir.getAbsolutePath());
			return new String[]{"[Dossier textures/ introuvable]"}; 
		}

		File[] files = textureDir.listFiles();
		List<String> textureNames = new ArrayList<>();

		if (files != null) 
		{
			for (File file : files) 
			{
				// Filtrer les fichiers image courants
				if (file.isFile() && (file.getName().toLowerCase().endsWith(".png"))) 
				{
					textureNames.add(file.getName());
				}
			}
		}
		
		if (textureNames.isEmpty()) 
		{
			textureNames.add("Aucune texture trouvée");
		}

		return textureNames.toArray(new String[0]);
	}
}
