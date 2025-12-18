package View.PanelParametres;

import Main.Controller;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

/**
 * Panel des parametres de rotation
*/
public class PanelRotationParametres extends JPanel implements ActionListener
{
	private Controller ctrl;

	private JLabel  labelRotation ;

	private JTextField txtAngleRotation;
	private JButton btnApplyRotation;

	/**
	 * Constructeur du panel des parametres de rotation
	 * @param ctrl
	 */
	public PanelRotationParametres( Controller ctrl )
	{
		this.ctrl = ctrl;

		this.setBackground( this.ctrl.getBackgroundColor() );


		this.setLayout( new BoxLayout(this, BoxLayout.Y_AXIS) );
		this.setBorder(new EmptyBorder(15, 15, 15, 15));

		this.labelRotation = new JLabel( "Ajuster la rotation (-180° à +180°)", JLabel.CENTER );
		this.labelRotation.setAlignmentX(Component.CENTER_ALIGNMENT);

		this.btnApplyRotation = new JButton( "Appliquer la rotation" );
		this.btnApplyRotation.setAlignmentX(Component.CENTER_ALIGNMENT);

		this.txtAngleRotation = new JTextField();
		this.txtAngleRotation.setMaximumSize(new Dimension(100, 25));
		this.txtAngleRotation.setAlignmentX(Component.CENTER_ALIGNMENT);

		this.add( this.labelRotation );
		this.add( Box.createVerticalStrut(10) );
		this.add( this.txtAngleRotation );
		this.add( Box.createVerticalStrut(10) );
		this.add( this.btnApplyRotation );
		this.add( Box.createVerticalGlue() );

		this.btnApplyRotation.addActionListener( this );
	}

	/**
	 * Gestion des actions
	 * @param e
	 */
	public void actionPerformed( java.awt.event.ActionEvent e )
	{
		if ( e.getSource() == this.btnApplyRotation )
		{
			try
			{
				double angle = Double.parseDouble( this.txtAngleRotation.getText() );
				if ( angle < -180 || angle > 180 )
				{
					throw new NumberFormatException();
				}
				this.ctrl.rotation( angle );
			}
			catch ( NumberFormatException ex )
			{
				System.out.println( "Valeur invalide pour l'angle de rotation." );
			}
		}
	}
}