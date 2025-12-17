package View.PanelParametres;

import Main.Controller;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JLabel;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

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

		this.setLayout( new BorderLayout() );

		this.sliderLuminosite = new JSlider( -100, 100, 0 );

		this.sliderLuminosite.setMajorTickSpacing( 50 );
		this.sliderLuminosite.setMinorTickSpacing( 10 );

		this.sliderLuminosite.setPaintTicks ( true );
		this.sliderLuminosite.setPaintLabels( true );

		this.sliderLuminosite.setName( "Luminosite" );

		this.sliderLuminosite.addChangeListener( this );

		this.labelLuminosite = new JLabel( "Ajuster la luminosité", JLabel.CENTER );

		this.add( this.labelLuminosite,  BorderLayout.NORTH  );
		this.add( this.sliderLuminosite, BorderLayout.CENTER );
	}

	/**
	 * Methode appelee lors d'un changement de valeur du slider
	 * @param e L'evenement de changement
	*/
	public void stateChanged( ChangeEvent e )
	{
		if ( e.getSource() == this.sliderLuminosite )
		{
			int valeurLuminosite = (int) this.sliderLuminosite.getValue();
			this.ctrl.adjustBrightness( valeurLuminosite );
		}
	}
}
