package View.PanelParametres;

import Main.Controller;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Panel des parametres de fusion
 */
public class PanelFusionParametres extends JPanel implements ActionListener
{
	private Controller ctrl;

	private JButton btnSelectionnerImage;

	/**
	 * Constructeur du panel des parametres de fusion
	 * @param ctrl Le controller de l'application
	 */
	public PanelFusionParametres( Controller ctrl )
	{
		this.ctrl = ctrl;

		this.setLayout( new BorderLayout() );

		this.btnSelectionnerImage = new JButton( "Selectionner Image" );

		this.add( this.btnSelectionnerImage, BorderLayout.CENTER );

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
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new java.io.File("."));
			fileChooser.setDialogTitle("Choisir une image");
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooser.setAcceptAllFileFilterUsed(false);

			int result = fileChooser.showOpenDialog(this);
			
			if (result == JFileChooser.APPROVE_OPTION) 
			{

				String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
				this.ctrl.setChemin(selectedFilePath);
			};
		}
	}
}
