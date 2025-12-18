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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panel des parametres de rotation
*/
public class PanelRotationParametres extends JPanel implements ActionListener
{
	private Controller ctrl;

	private JLabel  labelRotation ;

	private JPanel panelBtn;

	private JTextField txtAngleRotation;
	private JButton btnApplyRotation;

	private JButton btn90;
	private JButton btn90Negatif;
	private JButton btn180;
	private JButton btn180Negatif;

	/**
	 * Constructeur du panel des parametres de rotation
	 * @param ctrl
	 */
	public PanelRotationParametres( Controller ctrl )
	{
		this.ctrl = ctrl;

		this.setBackground( this.ctrl.getBackgroundColor() );

		this.panelBtn = new JPanel();
		this.panelBtn.setLayout( new BoxLayout(this.panelBtn, BoxLayout.X_AXIS) );
		this.panelBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.panelBtn.setBackground( this.ctrl.getBackgroundColor() );

		this.setLayout( new BoxLayout(this, BoxLayout.Y_AXIS) );
		this.setBorder(new EmptyBorder(15, 15, 15, 15));

		this.labelRotation = new JLabel( "Ajuster la rotation (-180° à +180°)", JLabel.CENTER );
		this.labelRotation.setAlignmentX(Component.CENTER_ALIGNMENT);

		this.btnApplyRotation = new JButton( "Appliquer la rotation" );
		this.btnApplyRotation.setAlignmentX(Component.CENTER_ALIGNMENT);

		this.btn90 = new JButton( "90°" );
		this.btn90.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.btn90Negatif = new JButton( "-90°" );
		this.btn90Negatif.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.btn180 = new JButton( "180°" );
		this.btn180.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.btn180Negatif = new JButton( "-180°" );
		this.btn180Negatif.setAlignmentX(Component.CENTER_ALIGNMENT);

		this.txtAngleRotation = new JTextField();
		this.txtAngleRotation.setMaximumSize(new Dimension(100, 25));
		this.txtAngleRotation.setAlignmentX(Component.CENTER_ALIGNMENT);

		this.panelBtn.add( this.btn90 );
		this.panelBtn.add(Box.createHorizontalStrut(10));
		this.panelBtn.add( this.btn90Negatif );
		this.panelBtn.add(Box.createHorizontalStrut(10));
		this.panelBtn.add( this.btn180 );
		this.panelBtn.add(Box.createHorizontalStrut(10));
		this.panelBtn.add( this.btn180Negatif );
		this.add( Box.createVerticalGlue() );

		this.add( this.labelRotation );
		this.add( Box.createVerticalStrut(10) );
		this.add( this.txtAngleRotation );
		this.add( Box.createVerticalStrut(10) );
		this.add( this.btnApplyRotation );
		this.add( Box.createVerticalStrut(10) );
		this.add(this.panelBtn);
		this.add( Box.createVerticalGlue() );

		this.btnApplyRotation.addActionListener( this );
		
		this.btn90        .addActionListener( this );
		this.btn180       .addActionListener( this );
		this.btn90Negatif .addActionListener( this );
		this.btn180Negatif.addActionListener( this );
	}

	/**
	 * Gestion des actions
	 * @param e
	 */
	public void actionPerformed( ActionEvent e )
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

		if ( e.getSource() == this.btn90 )
		{
			double angle = 90.00;
			this.ctrl.rotation( angle );
		}

		if ( e.getSource() == this.btn90Negatif )
		{
			double angle = -90.00;
			this.ctrl.rotation( angle );
		}

		if ( e.getSource() == this.btn180 )
		{
			double angle = 180.00;
			this.ctrl.rotation( angle );
		}

		if ( e.getSource() == this.btn180Negatif )
		{
			double angle = -180.00;
			this.ctrl.rotation( angle );
		}
	}
}