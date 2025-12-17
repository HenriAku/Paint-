package View.PanelParametres;

import Main.Controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.border.EmptyBorder;


/**
 * Panel des parametres du pot de peinture
*/
public class PanelPotDePeintureParametres extends JPanel implements ActionListener
{
	private Controller ctrl;

	private JLabel     labelPotDePeinture ;
	private JLabel     labelTolerance     ;
	private JTextField textTolerance      ;

	private JButton btnCouleur         ;
	private JButton btnCouleurPipette  ;

	/**
	 * Constructeur du panel des parametres du pot de peinture
	 * @param ctrl Le controller de l'application
	 */
	public PanelPotDePeintureParametres( Controller ctrl )
	{
		this.ctrl = ctrl;

		this.setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );
		this.setBorder(new EmptyBorder(20, 20, 20, 20));

		this.labelPotDePeinture = new JLabel( "Pot de Peinture", JLabel.CENTER );
		this.labelPotDePeinture.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.labelTolerance = new JLabel( "Ajuster la tolérance (0-255)", JLabel.CENTER );
		this.labelTolerance.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.btnCouleur = new JButton( "Choisir la couleur" );
		this.btnCouleur.setBackground( this.ctrl.getCurrentBucketColor() );
		this.btnCouleur.setOpaque(true);
		this.btnCouleur.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.btnCouleur.addActionListener( this );
		
		this.btnCouleurPipette = new JButton( "Couleur pipette" );
		this.btnCouleurPipette.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.btnCouleurPipette.setOpaque(true);
		this.btnCouleurPipette.addActionListener( this );
		
		this.textTolerance = new JTextField( Integer.toString( this.ctrl.getCurrentBucketTolerance() ) );
		
		Dimension toleranceSize = new Dimension(80, this.textTolerance.getPreferredSize().height);
		this.textTolerance.setMaximumSize(toleranceSize);
		this.textTolerance.setAlignmentX(Component.CENTER_ALIGNMENT);

		this.textTolerance.addActionListener( this );

		// --- Ajout des Composants ---
		this.add( this.labelPotDePeinture );
		this.add( Box.createVerticalStrut(10) );

		this.add( this.btnCouleur );
		this.add( Box.createVerticalStrut(5) );
		this.add( this.btnCouleurPipette );

		this.add( Box.createVerticalStrut(20) );

		this.add( this.labelTolerance );
		this.add( Box.createVerticalStrut(5) );
		this.add( this.textTolerance ); 
		
		// 🌟 Pousser le contenu vers le haut
		this.add( Box.createVerticalGlue() );
	}

	/**
	 * Gestion des actions sur les boutons
	 * @param e L'evenement declencheur
	 */
	public void actionPerformed( ActionEvent e )
	{
		if (e.getSource() == this.btnCouleur)
		{
			Color couleurInitiale = this.ctrl.getCurrentBucketColor();
			Color newCouleur = JColorChooser.showDialog( this, "Choisir une couleur", couleurInitiale );

			if ( newCouleur != null )
			{
				this.btnCouleur.setBackground( newCouleur );
				this.ctrl.setCurrentBucketColor( newCouleur );
			}
		}

		if (e.getSource() == this.btnCouleurPipette)
		{
			int pipetteRGB = this.ctrl.getPipetteColorRGB();
			Color couleurPipette = new Color(pipetteRGB);
			
			this.btnCouleur.setBackground( couleurPipette );
			this.ctrl.setCurrentBucketColor( couleurPipette );
		}

		if ( e.getSource() == this.textTolerance )
		{
			try
			{
				int tolerance = Integer.parseInt( this.textTolerance.getText() );
				if (tolerance >= 0 && tolerance <= 255) {
					this.ctrl.setCurrentBucketTolerance( tolerance );
				} else {
					this.textTolerance.setText(Integer.toString(this.ctrl.getCurrentBucketTolerance())); 
				}
			}
			catch ( NumberFormatException ex )
			{
				this.textTolerance.setText(Integer.toString(this.ctrl.getCurrentBucketTolerance()));
			}
		}
	}

	/**
	 * Met à jour l'affichage de la couleur de la pipette
	 */
	public void updatePipetteColor()
	{
		int pipetteRGB = this.ctrl.getPipetteColorRGB();
		this.btnCouleurPipette.setBackground( new Color(pipetteRGB) );
	}
}