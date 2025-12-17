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

	private Color   couleurChoisie;
	private JButton btnCouleur    ;

	/**
	 * Constructeur du panel des parametres du pot de peinture
	 * @param ctrl Le controller de l'application
	 */
	public PanelPotDePeintureParametres( Controller ctrl )
	{
		this.ctrl = ctrl;

		this.setLayout( new BoxLayout(this, BoxLayout.Y_AXIS) );

		this.labelPotDePeinture = new JLabel( "Pot de Peinture"     , JLabel.CENTER );
		this.labelTolerance     = new JLabel( "Ajuster la tolerance", JLabel.CENTER );
		this.textTolerance      = new JTextField( "30" );

		this.btnCouleur = new JButton( "Choisir la couleur" );
		this.btnCouleur.setBackground( this.couleurChoisie );

		this.btnCouleur.addActionListener( this );

		this.labelPotDePeinture = new JLabel( "Pot de Peinture"     , JLabel.CENTER );
		this.labelTolerance     = new JLabel( "Ajuster la tolerance", JLabel.CENTER );
		
		this.textTolerance.setMaximumSize(this.textTolerance.getPreferredSize());
		this.textTolerance = new JTextField( "30" );

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
			Color newCouleur = JColorChooser.showDialog( this, "Choisir une couleur", this.couleurChoisie );

			if (newCouleur != null)
			{
				this.couleurChoisie = newCouleur;
				this.btnCouleur.setBackground( this.couleurChoisie );
			}
		}
	}

	/**
	 * Getter de la couleur choisie
	 * @return La couleur choisie
	 */
	public Color getCouleurChoisie() {
		return this.couleurChoisie;
	}

	/**
	 * Getter de la tolerance
	 * @return La tolerance
	 */
	public int getTolerance() {
		try {
			// Tente de récupérer la valeur du champ texte.
			return Integer.parseInt(this.textTolerance.getText());
		} catch (NumberFormatException e) {
			// En cas d'erreur de format, retourner une valeur par défaut sûre
			return 30; 
		}
	}
}
