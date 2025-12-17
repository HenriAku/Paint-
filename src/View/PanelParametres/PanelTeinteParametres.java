package View.PanelParametres;

import Main.Controller;

import javax.swing.BoxLayout;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JLabel;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

/**
 * Panel des parametres de teinte
 */
public class PanelTeinteParametres extends JPanel implements ChangeListener
{
	private Controller ctrl;

	private JSlider sliderTeinteR;
	private JSlider sliderTeinteG;
	private JSlider sliderTeinteB;

	private JLabel  labelTeinteR ;
	private JLabel  labelTeinteG ;
	private JLabel  labelTeinteB ;

	/**
	 * Constructeur du panel des parametres de teinte
	 * @param ctrl Le controller de l'application
	 */
	public PanelTeinteParametres( Controller ctrl )
	{
		this.ctrl = ctrl;

		this.setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );

		this.sliderTeinteR = new JSlider( -255, 255, 0 );
		this.sliderTeinteG = new JSlider( -255, 255, 0 );
		this.sliderTeinteB = new JSlider( -255, 255, 0 );

		this.sliderTeinteR.setMajorTickSpacing( 100 );
		this.sliderTeinteG.setMajorTickSpacing( 100 );
		this.sliderTeinteB.setMajorTickSpacing( 100 );

		this.sliderTeinteR.setPaintTicks( true );
		this.sliderTeinteG.setPaintTicks( true );
		this.sliderTeinteB.setPaintTicks( true );

		this.sliderTeinteR.setName( "TeinteR" );
		this.sliderTeinteG.setName( "TeinteG" );
		this.sliderTeinteB.setName( "TeinteB" );

		this.sliderTeinteR.addChangeListener( this );
		this.sliderTeinteG.addChangeListener( this );
		this.sliderTeinteB.addChangeListener( this );

		this.labelTeinteR = new JLabel( "Ajuster la teinte Rouge", JLabel.CENTER );
		this.labelTeinteG = new JLabel( "Ajuster la teinte Verte", JLabel.CENTER );
		this.labelTeinteB = new JLabel( "Ajuster la teinte Bleue", JLabel.CENTER );

		this.add( this.labelTeinteR  );
		this.add( this.sliderTeinteR );

		this.add( this.labelTeinteG  );
		this.add( this.sliderTeinteG );
		
		this.add( this.labelTeinteB  );
		this.add( this.sliderTeinteB );
	}

	/**
	 * Methode appelee lors d'un changement de valeur du slider
	 * @param e L'evenement de changement
	 */
	public void stateChanged( ChangeEvent e )
	{
		if ( e.getSource() == this.sliderTeinteR )
		{
			int valeurTeinteR = (int) this.sliderTeinteR.getValue();
			this.ctrl.adjustHue( valeurTeinteR, 0, 0 );
		}
		else if ( e.getSource() == this.sliderTeinteG )
		{
			int valeurTeinteG = (int) this.sliderTeinteG.getValue();
			this.ctrl.adjustHue( 0, valeurTeinteG, 0 );
		}
		else if ( e.getSource() == this.sliderTeinteB )
		{
			int valeurTeinteB = (int) this.sliderTeinteB.getValue();
			this.ctrl.adjustHue( 0, 0, valeurTeinteB );
		}
	}
}
