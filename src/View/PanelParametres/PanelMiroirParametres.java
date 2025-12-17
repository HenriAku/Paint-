package View.PanelParametres;

import Main.Controller;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * Panel des parametres de miroir
 */
public class PanelMiroirParametres extends JPanel implements ActionListener
{
	private Controller ctrl;

	private JButton btnMiroirHorizontal;
	private JButton btnMiroirVertical;

	/**
	 * Constructeur du panel des parametres de miroir
	 * @param ctrl Le controller de l'application
	 */
	public PanelMiroirParametres( Controller ctrl )
	{
		this.ctrl = ctrl;

		this.setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );

		this.btnMiroirHorizontal = new JButton( "Miroir Horizontal" );
		this.btnMiroirHorizontal.addActionListener( this );

		this.btnMiroirVertical = new JButton( "Miroir Vertical" );
		this.btnMiroirVertical.addActionListener( this );

		this.add( this.btnMiroirHorizontal );
		this.add( Box.createVerticalStrut( 10 ) );
		this.add( this.btnMiroirVertical   );
	}

	/**
	 * Methode appelee lors d'un clic sur un bouton
	 * @param e L'evenement de clic
	 */
	public void actionPerformed( ActionEvent e )
	{
		if ( e.getSource() == this.btnMiroirHorizontal )
		{
			this.ctrl.mirrorHorizontal(); 
		}
		else if ( e.getSource() == this.btnMiroirVertical )
		{
			this.ctrl.mirrorVertical();
		}
	}
}
