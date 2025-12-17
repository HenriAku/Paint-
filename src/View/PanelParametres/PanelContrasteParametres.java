package View.PanelParametres;

import Main.Controller;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JLabel;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

/**
 * Panel des parametres de contraste
*/
public class PanelContrasteParametres extends JPanel implements ChangeListener
{
	private Controller ctrl;

	private JSlider sliderContraste;
	private JLabel  labelContraste ;

	/**
	 * Constructeur du panel des parametres de contraste
	 * @param ctrl Le controller de l'application
	*/
	public PanelContrasteParametres( Controller ctrl )
	{
		this.ctrl = ctrl;

		this.setLayout( new BorderLayout() );

		this.sliderContraste = new JSlider( -100, 100, 0 );

		this.sliderContraste.setMajorTickSpacing( 50 );
		this.sliderContraste.setMinorTickSpacing( 10 );

		this.sliderContraste.setPaintTicks( true );
		this.sliderContraste.setPaintLabels( true );

		this.sliderContraste.setName( "Contraste" );
		this.sliderContraste.addChangeListener( this );

		this.labelContraste = new JLabel( "Ajuster le contraste", JLabel.CENTER );
		
		this.add( this.labelContraste,  BorderLayout.NORTH  );
		this.add( this.sliderContraste, BorderLayout.CENTER );
	}

	/**
	 * Methode appelee lors d'un changement de valeur du slider
	 * @param e L'evenement de changement
	*/
	public void stateChanged( ChangeEvent e )
	{
		if ( e.getSource() == this.sliderContraste )
		{
			int valeurContraste = (int) this.sliderContraste.getValue();
			this.ctrl.adjustContrast( valeurContraste );
		}
	}
}
