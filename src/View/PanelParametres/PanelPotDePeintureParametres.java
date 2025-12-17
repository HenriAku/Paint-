package View.PanelParametres;

import Main.Controller;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

/**
 * Panel des parametres du pot de peinture
*/
public class PanelPotDePeintureParametres extends JPanel
{
	private Controller ctrl;

	private JLabel     labelPotDePeinture ;
	private JLabel     labelTolerance     ;
	private JTextField textTolerance      ;

	/**
	 * Constructeur du panel des parametres du pot de peinture
	 * @param ctrl Le controller de l'application
	 */
	public PanelPotDePeintureParametres( Controller ctrl )
	{
		this.ctrl = ctrl;

		this.setLayout( new BorderLayout() );

		this.labelPotDePeinture = new JLabel( "Pot de Peinture"     , JLabel.CENTER );
		this.labelTolerance     = new JLabel( "Ajuster la tolerance", JLabel.CENTER );

		this.textTolerance = new JTextField( "30" );

		this.add( this.labelPotDePeinture, BorderLayout.NORTH  );
		this.add( this.labelTolerance    , BorderLayout.CENTER );
		this.add( this.textTolerance     , BorderLayout.SOUTH  );
	}
}
