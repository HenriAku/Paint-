package View.PanelParametres;

import Main.Controller;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

/**
 * Panel des parametres de fusion
 */
public class PanelPipetteParametres extends JPanel
{
	private Controller ctrl;
	private JLabel  labelInstructions;
	private JLabel  labelCouleurAffichee;
	private JPanel  colorSamplePanel;

	/**
	 * Constructeur du panel des parametres de pipette
	 * @param ctrl Le controller de l'application
	 */
	public PanelPipetteParametres( Controller ctrl )
	{
		this.ctrl = ctrl;

		this.setBackground( this.ctrl.getBackgroundColor() );


		this.setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );
		this.setBorder( new EmptyBorder( 20, 20, 20, 20 ) );

		this.labelInstructions = new JLabel( "Cliquez sur l'image" );
		this.labelInstructions.setAlignmentX( Component.CENTER_ALIGNMENT );

		this.colorSamplePanel = new JPanel();
		this.colorSamplePanel.setPreferredSize( new Dimension( 80, 40 ) ); 
		this.colorSamplePanel.setMaximumSize( new Dimension( 100, 50 ) );
		this.colorSamplePanel.setOpaque( true );
		this.colorSamplePanel.setBackground( Color.WHITE );
		this.colorSamplePanel.setAlignmentX( Component.CENTER_ALIGNMENT );

		this.labelCouleurAffichee = new JLabel( "#FFFFFF" );
		this.labelCouleurAffichee.setAlignmentX( Component.CENTER_ALIGNMENT );
		
		this.add( this.labelInstructions );
		this.add( Box.createVerticalStrut( 15 ) );
		this.add( this.colorSamplePanel );
		this.add( Box.createVerticalStrut( 5 ) );
		this.add( this.labelCouleurAffichee );
		this.add( Box.createVerticalGlue() );
	}

	/**
	 * Met à jour l'affichage de la couleur sélectionnée par la pipette.
	 */
	public void updateColorDisplay()
	{
		int rgb = this.ctrl.getPipetteColorRGB();
		
		Color selectedColor = new Color( rgb );

		this.colorSamplePanel.setBackground( selectedColor );
		
		String hex = String.format( "#%06X", (0xFFFFFF & rgb) );
		this.labelCouleurAffichee.setText( hex );
		
		this.repaint();
	}
}