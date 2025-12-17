package View.PanelParametres;

import Main.Controller;

import java.awt.Dimension;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.Box;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.border.EmptyBorder;

/**
 * Panel des parametres de luminosite
*/
public class PanelLuminositeParametres extends JPanel implements ChangeListener
{
	private Controller ctrl;

	private JSlider sliderLuminosite;
	private JLabel  labelLuminosite ;

	/**
	 * Constructeur du panel des parametres de luminosite
	 * @param ctrl Le controller de l'application
	*/
	public PanelLuminositeParametres( Controller ctrl )
	{
		this.ctrl = ctrl;

		this.setLayout( new BoxLayout(this, BoxLayout.Y_AXIS) );
		this.setBorder(new EmptyBorder(15, 15, 15, 15));

		this.labelLuminosite = new JLabel( "Ajuster la luminosité", JLabel.CENTER );
		this.labelLuminosite.setAlignmentX(Component.CENTER_ALIGNMENT);

		this.sliderLuminosite = new JSlider( -100, 100, 0 );

		this.sliderLuminosite.setMajorTickSpacing( 100 );
		this.sliderLuminosite.setMinorTickSpacing( 25 );

		this.sliderLuminosite.setPaintTicks ( true );
		this.sliderLuminosite.setPaintLabels( true );

		this.sliderLuminosite.setName( "Luminosite" );
		this.sliderLuminosite.addChangeListener( this );
		this.sliderLuminosite.setAlignmentX(Component.CENTER_ALIGNMENT); 
		this.sliderLuminosite.setMaximumSize(new Dimension(300, 50));

		this.add( this.labelLuminosite );
		this.add( Box.createVerticalStrut(10) );
		this.add( this.sliderLuminosite );
		this.add( Box.createVerticalGlue() );
	}

	/**
	 * Methode appelee lors d'un changement de valeur du slider
	 * @param e L'evenement de changement
	*/
	public void stateChanged( ChangeEvent e )
	{
		if ( e.getSource() == this.sliderLuminosite )
		{
			int valeurLuminosite = this.sliderLuminosite.getValue();
			this.ctrl.adjustBrightness( valeurLuminosite );
		}
	}
}