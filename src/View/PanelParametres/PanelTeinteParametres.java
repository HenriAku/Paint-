package View.PanelParametres;

import Main.Controller;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

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

		this.setBackground( this.ctrl.getBackgroundColor() );


		this.setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );
		this.setBorder(new EmptyBorder(15, 15, 15, 15));

		this.sliderTeinteR = new JSlider( -255, 255, 0 );
		this.sliderTeinteG = new JSlider( -255, 255, 0 );
		this.sliderTeinteB = new JSlider( -255, 255, 0 );

		int majorTick = 100;
		Dimension sliderMax = new Dimension(350, 50);
		
		this.sliderTeinteR.setMajorTickSpacing( majorTick );
		this.sliderTeinteR.setPaintTicks( true );
		this.sliderTeinteR.setPaintLabels( true );
		this.sliderTeinteR.setName( "TeinteR" );
		this.sliderTeinteR.addChangeListener( this );
		this.sliderTeinteR.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.sliderTeinteR.setMaximumSize(sliderMax);
		this.sliderTeinteG.setBackground( this.ctrl.getBackgroundColor() );

		this.sliderTeinteG.setMajorTickSpacing( majorTick );
		this.sliderTeinteG.setPaintTicks( true );
		this.sliderTeinteG.setPaintLabels( true );
		this.sliderTeinteG.setName( "TeinteG" );
		this.sliderTeinteG.addChangeListener( this );
		this.sliderTeinteG.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.sliderTeinteG.setMaximumSize(sliderMax);
		this.sliderTeinteB.setBackground( this.ctrl.getBackgroundColor() );
		
		this.sliderTeinteB.setMajorTickSpacing( majorTick );
		this.sliderTeinteB.setPaintTicks( true );
		this.sliderTeinteB.setPaintLabels( true );
		this.sliderTeinteB.setName( "TeinteB" );
		this.sliderTeinteB.addChangeListener( this );
		this.sliderTeinteB.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.sliderTeinteB.setMaximumSize(sliderMax);
		this.sliderTeinteR.setBackground( this.ctrl.getBackgroundColor() );

		this.labelTeinteR = new JLabel( "Ajuster la teinte Rouge (-255 à +255)", JLabel.CENTER );
		this.labelTeinteG = new JLabel( "Ajuster la teinte Verte (-255 à +255)", JLabel.CENTER );
		this.labelTeinteB = new JLabel( "Ajuster la teinte Bleue (-255 à +255)", JLabel.CENTER );

		this.labelTeinteR.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.labelTeinteG.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.labelTeinteB.setAlignmentX(Component.CENTER_ALIGNMENT);

		this.add( this.labelTeinteR  );
		this.add( Box.createVerticalStrut(5) );
		this.add( this.sliderTeinteR );
		this.add( Box.createVerticalStrut(15) );

		this.add( this.labelTeinteG  );
		this.add( Box.createVerticalStrut(5) );
		this.add( this.sliderTeinteG );
		this.add( Box.createVerticalStrut(15) );
		
		this.add( this.labelTeinteB  );
		this.add( Box.createVerticalStrut(5) );
		this.add( this.sliderTeinteB );
		
		this.add( Box.createVerticalGlue() );
	}

	/**
	 * Methode appelee lors d'un changement de valeur du slider
	 * @param e L'evenement de changement
	 */
	public void stateChanged( ChangeEvent e )
	{
		int rOffset = this.sliderTeinteR.getValue();
		int gOffset = this.sliderTeinteG.getValue();
		int bOffset = this.sliderTeinteB.getValue();
		
		this.ctrl.adjustHue( rOffset, gOffset, bOffset );
	}
}