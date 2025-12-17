package View.PanelParametres;

import Main.Controller;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JLabel;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

/**
 * Panel des parametres de rotation
*/
public class PanelRotationParametres extends JPanel implements ChangeListener
{
	private Controller ctrl;

	private JSlider sliderRotation;
	private JLabel  labelRotation ;

	/**
	 * Constructeur du panel des parametres de rotation
	 * @param ctrl
	 */
	public PanelRotationParametres( Controller ctrl )
	{
		this.ctrl = ctrl;

		this.setLayout( new BorderLayout() );

		this.sliderRotation = new JSlider( -180, 180, 0 );

		this.sliderRotation.setMajorTickSpacing( 60 );
		this.sliderRotation.setMinorTickSpacing( 15 );

		this.sliderRotation.setPaintTicks ( true );
		this.sliderRotation.setPaintLabels( true );

		this.sliderRotation.setName( "Rotation" );

		this.sliderRotation.addChangeListener( this );

		this.labelRotation = new JLabel( "Ajuster la rotation", JLabel.CENTER );

		this.add( this.labelRotation,  BorderLayout.NORTH  );
		this.add( this.sliderRotation, BorderLayout.CENTER );
	}

	/**
	 * Methode appelee lors d'un changement de valeur du slider
	 * @param e L'evenement de changement
	 */
	public void stateChanged( ChangeEvent e )
	{
		if ( e.getSource() == this.sliderRotation )
		{
			int valeurRotation = (int) this.sliderRotation.getValue();
			this.ctrl.rotation( valeurRotation );
		}
	}
}
