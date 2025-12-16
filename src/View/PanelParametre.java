import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;

public class PanelParametre extends JPanel implements ChangeListener
{
	private Controller controller;

	private CardLayout cardLayout; 

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

		JSlider contrasteSlider = new JSlider(-100, 100, 0);
		contrasteSlider.setMajorTickSpacing(50);
		contrasteSlider.setMinorTickSpacing(10);
		contrasteSlider.setPaintTicks(true);
		contrasteSlider.setPaintLabels(true);
		contrasteSlider.setName("Contraste");
		contrasteSlider.addChangeListener(this);

		JLabel titre = new JLabel("Ajuster le Contraste", JLabel.CENTER);
		panelContraste.add(titre,BorderLayout.NORTH);
		panelContraste.add(contrasteSlider, BorderLayout.CENTER);
	
		this.add(panelContraste, "Contraste");

		// Texte
		JPanel panelTexte = new JPanel();
		this.add(panelTexte, "Texte");

		// Rotation
		JPanel panelRotation = new JPanel(new BorderLayout());

		JSlider rotationSlider = new JSlider(-180, 180, 0);
		rotationSlider.setMajorTickSpacing(90);
		rotationSlider.setMinorTickSpacing(15);
		rotationSlider.setPaintTicks(true);
		rotationSlider.setPaintLabels(true);
		rotationSlider.setName("Rotation");
		rotationSlider.addChangeListener(this);

		JLabel titreRotation = new JLabel("Ajuster la Rotation", JLabel.CENTER);
		panelRotation.add(titreRotation,BorderLayout.NORTH);
		panelRotation.add(rotationSlider, BorderLayout.CENTER);

		this.add(panelRotation, "Rotation");

		// Luminosité
		JPanel panelLuminosité = new JPanel(new BorderLayout());

		JSlider luminositéSlider = new JSlider(-100, 100, 0);
		luminositéSlider.setMajorTickSpacing(50);
		luminositéSlider.setMinorTickSpacing(10);
		luminositéSlider.setPaintTicks(true);
		luminositéSlider.setPaintLabels(true);
		luminositéSlider.setName("Luminosité");
		luminositéSlider.addChangeListener(this);

		JLabel titreLuminosité = new JLabel("Ajuster la Luminosité", JLabel.CENTER);
		panelLuminosité.add(titreLuminosité,BorderLayout.NORTH);
		panelLuminosité.add(luminositéSlider, BorderLayout.CENTER);
		
		this.add(panelLuminosité, "Luminosité");

		// Teinte
		JPanel panelTeinte = new JPanel(new BorderLayout());

		JSlider rTeinteSlider = new JSlider(-255, 255, 0);
		JSlider gTeinteSlider = new JSlider(-255, 255, 0);
		JSlider bTeinteSlider = new JSlider(-255, 255, 0);

		rTeinteSlider.setMajorTickSpacing(255);
		rTeinteSlider.setMinorTickSpacing(51);
		rTeinteSlider.setPaintTicks(true);
		rTeinteSlider.setPaintLabels(true);
		rTeinteSlider.setName("RTeinte");
		rTeinteSlider.addChangeListener(this);

		gTeinteSlider.setMajorTickSpacing(255);
		gTeinteSlider.setMinorTickSpacing(51);
		gTeinteSlider.setPaintTicks(true);
		gTeinteSlider.setPaintLabels(true);
		gTeinteSlider.setName("GTeinte");
		gTeinteSlider.addChangeListener(this);

		bTeinteSlider.setMajorTickSpacing(255);
		bTeinteSlider.setMinorTickSpacing(51);
		bTeinteSlider.setPaintTicks(true);
		bTeinteSlider.setPaintLabels(true);
		bTeinteSlider.setName("BTeinte");
		bTeinteSlider.addChangeListener(this);

		JLabel titreTeinte = new JLabel("Ajuster la Teinte", JLabel.CENTER);
		panelTeinte.add(titreTeinte,BorderLayout.NORTH);
		JPanel slidersPanel = new JPanel();
		slidersPanel.setLayout(new BorderLayout());
		slidersPanel.add(rTeinteSlider, BorderLayout.NORTH);
		slidersPanel.add(gTeinteSlider, BorderLayout.CENTER);
		slidersPanel.add(bTeinteSlider, BorderLayout.SOUTH);
		panelTeinte.add(slidersPanel, BorderLayout.CENTER);
		this.add(panelTeinte, "Teinte");

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

	// Méthode pour gérer les actions
	public void stateChanged(ChangeEvent e)
	{
		JSlider source = (JSlider)e.getSource();

		if(source.getName() != null && source.getName().equals("Contraste"))
		{
			int contrastLevel = (int)source.getValue();
			this.controller.adjustContrast(contrastLevel);
			this.controller.updateDessin();
		}

		if(source.getName() != null && source.getName().equals("Luminosité"))
		{
			int brightnessLevel = (int)source.getValue();
			this.controller.adjustBrightness(brightnessLevel);
			this.controller.updateDessin();
		}

		if(source.getName() != null && source.getName().equals("Rotation"))
		{
			int angle = (int)source.getValue();
			this.controller.rotation(angle);
			this.controller.updateDessin();
		}

		if(source.getName() != null && source.getName().equals("RTeinte"))
		{
			int rOffset = (int)source.getValue();
			this.controller.adjustHue(this.controller.getBufferedImage(), rOffset, 0, 0);
			this.controller.updateDessin();
		}

		if(source.getName() != null && source.getName().equals("GTeinte"))
		{
			int gOffset = (int)source.getValue();
			this.controller.adjustHue(this.controller.getBufferedImage(), 0, gOffset, 0);
			this.controller.updateDessin();
		}

		if(source.getName() != null && source.getName().equals("BTeinte"))
		{
			int bOffset = (int)source.getValue();
			this.controller.adjustHue(this.controller.getBufferedImage(), 0, 0, bOffset);
			this.controller.updateDessin();
		}
	}
}
