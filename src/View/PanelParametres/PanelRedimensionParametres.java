package View.PanelParametres;

import Main.Controller;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	 * 	Constructeur du panel des parametres de redimension
	 * @param ctrl Le controller de l'application
	 */
	public PanelRedimensionParametres( Controller ctrl )
	{
		this.ctrl = ctrl;

		this.setLayout( new BorderLayout() );

		this.btnRedimension = new JButton( "Redimensionner" );

		this.panelTextField = new JPanel();

		this.txtRedimensionLabelLargeur = new JLabel( "Largeur :" );
		this.txtRedimensionLabelHauteur = new JLabel( "Hauteur :" );

		this.txtRedimensionLargeur = new JTextField();
		this.txtRedimensionHauteur = new JTextField();

		this.txtRedimensionLargeur.setColumns( 10 );
		this.txtRedimensionHauteur.setColumns( 10 );

		this.panelTextField.add( this.txtRedimensionLabelLargeur );
		this.panelTextField.add( this.txtRedimensionLargeur );
		this.panelTextField.add( this.txtRedimensionLabelHauteur );
		this.panelTextField.add( this.txtRedimensionHauteur );

		this.add( this.panelTextField, BorderLayout.CENTER );
		this.add( this.btnRedimension, BorderLayout.SOUTH  );

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
			try {
				int newWidth  = Integer.parseInt(this.txtRedimensionLargeur.getText());
				int newHeight = Integer.parseInt(this.txtRedimensionHauteur.getText());
				this.ctrl.redimensionner(newWidth, newHeight);
			} catch (NumberFormatException ex) {
				System.err.println("Erreur: Veuillez entrer des valeurs numériques valides pour la largeur et la hauteur.");
			}
		}
	}
}
