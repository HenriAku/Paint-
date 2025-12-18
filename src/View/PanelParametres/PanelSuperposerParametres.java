package View.PanelParametres;

import Main.Controller;

import java.awt.Dimension;
import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;

/**
 * Panel des parametres de fusion
 */
public class PanelSuperposerParametres extends JPanel implements ActionListener
{
	private Controller ctrl;

	private JLabel  labelInstructions;
	private JButton btnSelectionnerImage;

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

		this.labelInstructions = new JLabel("Sélectionnez une image à superposer");
		this.labelInstructions.setAlignmentX(Component.CENTER_ALIGNMENT);

		this.btnSelectionnerImage = new JButton( "Charger l'image de superposition (PNG)" );
		
		this.btnSelectionnerImage.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		Dimension preferredSize = this.btnSelectionnerImage.getPreferredSize();
		this.btnSelectionnerImage.setMaximumSize(new Dimension(preferredSize.width + 50, preferredSize.height));

		this.add( Box.createVerticalStrut(15) ); 

		this.add( this.labelInstructions );
		
		this.add( Box.createVerticalStrut(10) );

		this.add( this.btnSelectionnerImage );
		
		this.add( Box.createVerticalGlue() );

		this.btnSelectionnerImage.addActionListener( this );
	}

	/**
	 * Methode appelee lors d'un clic sur un bouton
	 * @param e L'evenement de clic
	 */
	public void actionPerformed( ActionEvent e )
	{
		if ( e.getSource() == this.btnSelectionnerImage )
		{
			UIManager.put("FileChooser.cancelButtonText", "Annuler");
			UIManager.put("FileChooser.cancelButtonToolTipText", "Fermer la fenêtre");

			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory( new java.io.File(".")  );
			fileChooser.setFileSelectionMode( JFileChooser.FILES_ONLY );

			fileChooser.setDialogTitle("Ouvrir une image");
			fileChooser.setApproveButtonText("Ouvrir");
			fileChooser.setAcceptAllFileFilterUsed( false );
			
			// Ajouter un filtre pour les fichiers PNG
			javax.swing.filechooser.FileNameExtensionFilter filter = 
				new javax.swing.filechooser.FileNameExtensionFilter("Images PNG (*.png)", "png");
			fileChooser.addChoosableFileFilter(filter);
			
			int result = fileChooser.showOpenDialog( this );
			
			
			if (result == JFileChooser.APPROVE_OPTION) 
			{
				String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
				
				this.ctrl.setChemin(selectedFilePath);

				String fileName = fileChooser.getSelectedFile().getName();
				this.labelInstructions.setText("2. Image chargée : " + fileName );
			};
		}
	}
}