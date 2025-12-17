package View.PanelParametres;

import Main.Controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.BoxLayout;
import javax.swing.Box;


/**
 * Panel des parametres du pot de peinture
*/
public class PanelPotDePeintureParametres extends JPanel implements ActionListener
{
	private Controller ctrl;

	private JLabel     labelPotDePeinture ;
	private JLabel     labelTolerance     ;
	private JTextField textTolerance      ;

	private JButton btnCouleur    ;

	/**
	 * Constructeur du panel des parametres du pot de peinture
	 * @param ctrl Le controller de l'application
	 */
	public PanelPotDePeintureParametres( Controller ctrl )
	{
		this.ctrl = ctrl;

		this.setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );

		this.labelPotDePeinture = new JLabel( "Pot de Peinture"     , JLabel.CENTER );
		this.labelTolerance     = new JLabel( "Ajuster la tolerance", JLabel.CENTER );
		
		this.btnCouleur = new JButton( "Choisir la couleur" );
		this.btnCouleur.setBackground( this.ctrl.getCurrentBucketColor() );
		
		this.btnCouleur.addActionListener( this );
		
		this.textTolerance = new JTextField( "30" );

		this.textTolerance.setMaximumSize( this.textTolerance.getPreferredSize() );
		this.textTolerance = new JTextField( Integer.toString( this.ctrl.getCurrentBucketTolerance() ) );

		this.textTolerance.addActionListener( this );

		this.add( this.labelPotDePeinture );
		this.add( this.btnCouleur );

		this.add( Box.createVerticalStrut(10) );

		this.add( this.labelTolerance );
		this.add( this.textTolerance );	
	}

	/**
	 * Gestion des actions sur les boutons
	 * @param e L'evenement declencheur
	 */
	public void actionPerformed( ActionEvent e )
	{
		if (e.getSource() == this.btnCouleur)
		{
			Color newCouleur = JColorChooser.showDialog( this, "Choisir une couleur", this.ctrl.getCurrentBucketColor() );

			if ( newCouleur != null )
			{
				this.btnCouleur.setBackground( newCouleur );
				this.ctrl.setCurrentBucketColor( newCouleur );
			}
		}

		if ( e.getSource() == this.textTolerance )
		{
			try
			{
				int tolerance = Integer.parseInt( this.textTolerance.getText() );
				this.ctrl.setCurrentBucketTolerance( tolerance );
			}
			catch ( NumberFormatException ex )
			{
				// Ignorer les entrees non numeriques
			}
		}
	}
}
