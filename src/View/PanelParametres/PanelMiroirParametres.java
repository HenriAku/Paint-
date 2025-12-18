package View.PanelParametres;

import Main.Controller;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


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

		this.setBackground( this.ctrl.getBackgroundColor() );


		this.setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );
		this.setBorder(new EmptyBorder(20, 20, 20, 20));

		this.btnMiroirHorizontal = new JButton( "Miroir Horizontal" );
		this.btnMiroirHorizontal.addActionListener( this );
		this.btnMiroirHorizontal.setAlignmentX(Component.CENTER_ALIGNMENT);

		Dimension maxBtnSize = new Dimension(200, 30); 
		this.btnMiroirHorizontal.setMaximumSize(maxBtnSize); 

		this.btnMiroirVertical = new JButton( "Miroir Vertical" );
		this.btnMiroirVertical.addActionListener( this );
		this.btnMiroirVertical.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.btnMiroirVertical.setMaximumSize(maxBtnSize);

		this.add( this.btnMiroirHorizontal );
		this.add( Box.createVerticalStrut( 10 ) );
		this.add( this.btnMiroirVertical   );
		
		this.add( Box.createVerticalGlue() );
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