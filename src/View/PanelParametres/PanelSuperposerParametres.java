package View.PanelParametres;

import Main.Controller;

import java.awt.Dimension;
import java.awt.Component;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JColorChooser;

/**
 * Panel des parametres de fusion
 */
public class PanelSuperposerParametres extends JPanel implements ActionListener
{
	private Controller ctrl;

	private JLabel  labelInstructions;
	private JButton btnSelectionnerImage;
	
	private JLabel  labelCouleur;
	private JButton btnChoisirCouleur;
	private JButton btnCouleurPipette;
	private JPanel  aperçuCouleur;

	/**
	 * Constructeur du panel des parametres de fusion
	 * @param ctrl Le controller de l'application
	 */
	public PanelSuperposerParametres( Controller ctrl )
	{
		this.ctrl = ctrl;

		this.setBackground( this.ctrl.getBackgroundColor() );
		this.setLayout( new BoxLayout(this, BoxLayout.Y_AXIS) );
		this.setBorder(new EmptyBorder(20, 20, 20, 20));

		this.labelInstructions = new JLabel("1. Sélectionnez une image à superposer");
		this.labelInstructions.setAlignmentX(Component.CENTER_ALIGNMENT);

		this.btnSelectionnerImage = new JButton( "Charger l'image (PNG)" );
		this.btnSelectionnerImage.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.add( Box.createVerticalStrut(20) ); 
		this.labelCouleur = new JLabel("2. Couleur à rendre transparente");
		this.labelCouleur.setAlignmentX(Component.CENTER_ALIGNMENT);

		this.btnChoisirCouleur = new JButton("Choisir la couleur");
		this.btnChoisirCouleur.setAlignmentX(Component.CENTER_ALIGNMENT);

		this.btnCouleurPipette = new JButton("Couleur pipette");
		this.btnCouleurPipette.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.btnCouleurPipette.setOpaque(true);
		
		this.aperçuCouleur = new JPanel();
		this.aperçuCouleur.setBackground(this.ctrl.getCurrentColorSuperposer());
		this.aperçuCouleur.setMaximumSize(new Dimension(30, 30));
		this.aperçuCouleur.setMinimumSize(new Dimension(30, 30));
		this.aperçuCouleur.setBorder(new LineBorder(Color.BLACK, 1));
		this.aperçuCouleur.setAlignmentX(Component.CENTER_ALIGNMENT);

		this.add( Box.createVerticalStrut(15) ); 
		this.add( this.labelInstructions );
		this.add( Box.createVerticalStrut(10) );
		this.add( this.btnSelectionnerImage );
		
		this.add( Box.createVerticalStrut(25) ); 
		this.add( this.labelCouleur );
		this.add( Box.createVerticalStrut(10) );
		this.add( this.aperçuCouleur );
		this.add( Box.createVerticalStrut(10) );
		this.add( this.btnChoisirCouleur );
		this.add( Box.createVerticalStrut(5) );
		this.add( this.btnCouleurPipette );
		
		this.add( Box.createVerticalGlue() );

		this.btnSelectionnerImage.addActionListener( this );
		this.btnChoisirCouleur.addActionListener( this );
		this.btnCouleurPipette.addActionListener( this );
	}

	/**
	 * Methode appelee lors d'un clic sur un bouton
	 * @param e L'evenement de clic
	 */
	public void actionPerformed( ActionEvent e )
	{
		if ( e.getSource() == this.btnSelectionnerImage )
		{
			selectionnerImage();
		}
		
		if ( e.getSource() == this.btnChoisirCouleur )
		{
			Color initialColor = this.ctrl.getCurrentColorSuperposer();
			Color selectedColor = JColorChooser.showDialog(this, "Couleur à supprimer", initialColor);
			
			if (selectedColor != null) {
				this.ctrl.setCurrentColorSuperposer(selectedColor);
				this.aperçuCouleur.setBackground(selectedColor);
			}
		}

		if ( e.getSource() == this.btnCouleurPipette )
		{
			int pipetteRGB = this.ctrl.getPipetteColorRGB();
			Color couleurPipette = new Color(pipetteRGB);
			
			this.ctrl.setCurrentColorSuperposer(couleurPipette);
			this.aperçuCouleur.setBackground(couleurPipette);
		}
	}

	/**
	 * Sélectionne l'image à superposer sur l'image actuelle
	 */
	private void selectionnerImage() {
		UIManager.put("FileChooser.cancelButtonText", "Annuler");
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory( new java.io.File(".")  );
		fileChooser.setFileSelectionMode( JFileChooser.FILES_ONLY );
		fileChooser.setDialogTitle("Ouvrir une image");
		fileChooser.setAcceptAllFileFilterUsed( false );
		
		javax.swing.filechooser.FileNameExtensionFilter filter = 
			new javax.swing.filechooser.FileNameExtensionFilter("Images PNG (*.png)", "png");
		fileChooser.addChoosableFileFilter(filter);
		
		int result = fileChooser.showOpenDialog( this );
		
		if (result == JFileChooser.APPROVE_OPTION) 
		{
			String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
			this.ctrl.setChemin(selectedFilePath);
			this.labelInstructions.setText("Image chargée" );
		}
	}

	/**
	 * Met à jour l'affichage de la couleur de la pipette
	 */
	public void updatePipetteColor()
	{
		int pipetteRGB = this.ctrl.getPipetteColorRGB();
		this.btnCouleurPipette.setBackground( new Color(pipetteRGB) );
	}
}