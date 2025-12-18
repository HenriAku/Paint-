package View.PanelParametres;

import Main.Controller;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.border.EmptyBorder;

/**
 * Panel des parametres de redimension
 */
public class PanelRedimensionParametres extends JPanel implements ActionListener
{
	private Controller ctrl;

	private JButton btnRedimension;

	private JPanel panelTextField;

	private JLabel txtRedimensionLabelLargeur;
	private JLabel txtRedimensionLabelHauteur;

	private JTextField txtRedimensionHauteur;
	private JTextField txtRedimensionLargeur;

	/**
	 * Constructeur du panel des parametres de redimension
	 * @param ctrl Le controller de l'application
	 */
	public PanelRedimensionParametres( Controller ctrl )
	{
		this.ctrl = ctrl;

		this.setBackground( this.ctrl.getBackgroundColor() );


		this.setLayout( new BoxLayout(this, BoxLayout.Y_AXIS) );
		this.setBorder(new EmptyBorder(20, 20, 20, 20));

		this.btnRedimension = new JButton( "Redimensionner" );
		this.btnRedimension.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.btnRedimension.setMaximumSize(new Dimension(200, 30));

		this.panelTextField = new JPanel();
		
		// Utiliser un BoxLayout horizontal pour les champs de texte dans le sous-panneau
		this.panelTextField.setLayout(new BoxLayout(this.panelTextField, BoxLayout.X_AXIS));

		this.txtRedimensionLabelLargeur = new JLabel( "Largeur :" );
		this.txtRedimensionLabelHauteur = new JLabel( "Hauteur :" );

		this.txtRedimensionLargeur = new JTextField();
		this.txtRedimensionHauteur = new JTextField();

		this.txtRedimensionLargeur.setColumns( 6 );
		this.txtRedimensionHauteur.setColumns( 6 );

		// Limiter la taille maximale pour éviter l'étirement excessif des JTextField
		Dimension fieldMaxSize = new Dimension(80, this.txtRedimensionLargeur.getPreferredSize().height);
		this.txtRedimensionLargeur.setMaximumSize(fieldMaxSize);
		this.txtRedimensionHauteur.setMaximumSize(fieldMaxSize);

		this.panelTextField.add( this.txtRedimensionLabelLargeur );
		this.panelTextField.add( Box.createHorizontalStrut(5) );
		this.panelTextField.add( this.txtRedimensionLargeur );
		this.panelTextField.add( Box.createHorizontalStrut(15) ); // Espace entre les deux groupes
		this.panelTextField.add( this.txtRedimensionLabelHauteur );
		this.panelTextField.add( Box.createHorizontalStrut(5) );
		this.panelTextField.add( this.txtRedimensionHauteur );
		
		this.panelTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.panelTextField.setBackground( this.ctrl.getBackgroundColor() );

		// Ajout au panneau principal (BoxLayout vertical)
		this.add( this.panelTextField );
		this.add( Box.createVerticalStrut(15) );
		this.add( this.btnRedimension );
		
		this.add( Box.createVerticalGlue() ); // Pousser le contenu vers le haut

		this.btnRedimension.addActionListener( this );
	}

	/**
	 * Methode appelee lors d'un clic sur le bouton de redimension
	 * @param e L'evenement de clic
	 */
	public void actionPerformed( ActionEvent e )
	{
		if ( e.getSource() == this.btnRedimension )
		{
			try 
			{
				int newWidth  = Integer.parseInt(this.txtRedimensionLargeur.getText());
				int newHeight = Integer.parseInt(this.txtRedimensionHauteur.getText());
				
				if (newWidth > 0 && newHeight > 0) 
				{
					this.ctrl.redimensionner(newWidth, newHeight);
				} else {
					System.err.println("Erreur: La largeur et la hauteur doivent être supérieures à zéro.");
				}
			} catch (NumberFormatException ex) {
				System.err.println("Erreur: Veuillez entrer des valeurs numériques valides pour la largeur et la hauteur.");
			}
		}
	}
}