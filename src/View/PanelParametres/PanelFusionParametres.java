package View.PanelParametres;

import javax.swing.JFileChooser;
import Main.Controller;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;

public class PanelFusionParametres extends JPanel implements ActionListener
{
	private Controller ctrl;

	private JLabel     labelInstructions;
	private JButton    btnSelectionnerImage;
	private JLabel     labelBound;
	private JTextField txtBound;

	public PanelFusionParametres( Controller ctrl )
	{
		this.ctrl = ctrl;

		this.setBackground( this.ctrl.getBackgroundColor() );


		this.setLayout( new BoxLayout(this, BoxLayout.Y_AXIS) );
		this.setBorder(new EmptyBorder(20, 20, 20, 20));

		this.labelInstructions = new JLabel("Sélectionnez une image à fusionner");
		this.labelInstructions.setAlignmentX(Component.CENTER_ALIGNMENT);

		this.btnSelectionnerImage = new JButton( "Charger l'image de fusion (PNG)" );
		this.btnSelectionnerImage.setAlignmentX(Component.CENTER_ALIGNMENT);

		this.labelBound = new JLabel("Largeur de la zone de fusion (en pixels) : ");
		this.labelBound.setAlignmentX(Component.CENTER_ALIGNMENT);

		this.txtBound = new JTextField();
		this.txtBound.setMaximumSize(new Dimension(100, 25));
		
		Dimension preferredSize = this.btnSelectionnerImage.getPreferredSize();
		this.btnSelectionnerImage.setMaximumSize(new Dimension(preferredSize.width + 50, preferredSize.height));

		this.add( Box.createVerticalStrut(15) ); 

		this.add( this.labelInstructions );
		
		this.add( Box.createVerticalStrut(10) );

		this.add( this.btnSelectionnerImage );
		
		this.add( this.labelBound );

		this.add( Box.createVerticalStrut(5) );

		this.add( this.txtBound );

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
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new java.io.File("."));
			fileChooser.setDialogTitle("Choisir une image à fusionner (PNG)");
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooser.setAcceptAllFileFilterUsed(false);

			int result = fileChooser.showOpenDialog(this);
			
			if (result == JFileChooser.APPROVE_OPTION) 
			{
				String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
				int bound = this.txtBound.getText().isEmpty() ? 50 : Integer.parseInt( this.txtBound.getText() );
				
				this.ctrl.fusion( selectedFilePath, bound ); 

				String fileName = fileChooser.getSelectedFile().getName();
				this.labelInstructions.setText("2. Image chargée : " + fileName );
			};
		}
	}
}
