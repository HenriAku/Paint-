package View.PanelParametres;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Component;

import Main.Controller;

public class PanelFiltreParametres extends JPanel implements ActionListener
{
	private Controller ctrl;
	private JButton    btnNoireEtBlanc;

	public PanelFiltreParametres( Controller controller )
	{
		this.ctrl = controller;

		this.setBackground( this.ctrl.getBackgroundColor() );

		this.setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );
		this.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		this.btnNoireEtBlanc = new JButton( "NoirEtBlanc" );
		this.btnNoireEtBlanc.setBackground( this.ctrl.getCurrentBucketColor() );
		this.btnNoireEtBlanc.setOpaque(true);
		this.btnNoireEtBlanc.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.btnNoireEtBlanc.addActionListener( this );

		// --- Ajout des Composants ---
		this.add( this.btnNoireEtBlanc );
		this.add( Box.createVerticalGlue() );

		this.btnNoireEtBlanc.addActionListener(this);
	}

	/**
	 * Gestion des actions
	 * @param e
	 */
	public void actionPerformed( ActionEvent e )
	{
		if (this.btnNoireEtBlanc == e.getSource()) 
		{
			this.ctrl.noirEtBlanc();
		}
	}
}
