package View.PanelParametres;

import Main.Controller;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.border.EmptyBorder;

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

		this.setBackground( this.ctrl.getBackgroundColor() );


		this.setLayout( new BoxLayout(this, BoxLayout.Y_AXIS) );
		this.setBorder(new EmptyBorder(15, 15, 15, 15));

		this.labelRotation = new JLabel( "Ajuster la rotation (-180° à +180°)", JLabel.CENTER );
		this.labelRotation.setAlignmentX(Component.CENTER_ALIGNMENT);

		this.sliderRotation = new JSlider( -180, 180, 0 );

		this.sliderRotation.setMajorTickSpacing( 90 );
		this.sliderRotation.setMinorTickSpacing( 30 );

		this.sliderRotation.setPaintTicks ( true );
		this.sliderRotation.setPaintLabels( true );

		this.sliderRotation.setName( "Rotation" );
		this.sliderRotation.addChangeListener( this );
		
		this.sliderRotation.setAlignmentX(Component.CENTER_ALIGNMENT); 
		this.sliderRotation.setMaximumSize(new Dimension(300, 50));

		this.sliderRotation.setBackground( this.ctrl.getBackgroundColor() );

		this.add( this.labelRotation );
		this.add( Box.createVerticalStrut(10) );
		this.add( this.sliderRotation );
		this.add( Box.createVerticalGlue() );
	}

	/**
	 * Methode appelee lors d'un changement de valeur du slider
	 * @param e L'evenement de changement
	 */
	public void stateChanged( ChangeEvent e )
	{
		if ( e.getSource() == this.sliderRotation )
		{
			double valeurRotation = (double) this.sliderRotation.getValue();
			this.ctrl.rotation( valeurRotation );
		}
	}
}