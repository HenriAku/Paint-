package View.PanelParametres;

import Main.Controller;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.Box;

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

		this.setBackground( this.ctrl.getBackgroundColor() );


		this.setLayout( new BoxLayout(this, BoxLayout.Y_AXIS) );

		this.setAlignmentX(Component.CENTER_ALIGNMENT);

		this.labelContraste = new JLabel( "Ajuster le contraste", JLabel.CENTER );
		this.labelContraste.setAlignmentX(Component.CENTER_ALIGNMENT); 

		this.sliderContraste = new JSlider( -100, 100, 0 );

		this.sliderContraste.setMajorTickSpacing( 50 );
		this.sliderContraste.setMinorTickSpacing( 10 );
		this.sliderContraste.setPaintTicks( true );
		this.sliderContraste.setPaintLabels( true );

		this.sliderContraste.setName( "Contraste" );
		this.sliderContraste.addChangeListener( this );
		
		this.sliderContraste.setAlignmentX(Component.CENTER_ALIGNMENT); 
		
		this.sliderContraste.setMaximumSize(new Dimension(300, 50)); 

		this.sliderContraste.setBackground( this.ctrl.getBackgroundColor() );
		
		this.add( Box.createVerticalStrut(15) ); 
		
		this.add( this.labelContraste );
		
		this.add( Box.createVerticalStrut(10) );
		
		this.add( this.sliderContraste );
		
		this.add( Box.createVerticalGlue() );
	}

	/**
	 * Methode appelee lors d'un changement de valeur du slider
	 * @param e L'evenement de changement
	*/
	public void stateChanged( ChangeEvent e )
	{
		if ( e.getSource() == this.sliderContraste )
		{
			int valeurContraste = this.sliderContraste.getValue();
			
			this.ctrl.adjustContrast( (double) valeurContraste );
		}
	}
}