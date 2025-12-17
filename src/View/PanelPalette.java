package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Box;
import java.awt.Dimension;

import Main.Controller;
import Main.ToolType;


/**
 * Panel contenant les outils de l'application Paint.
 */
public class PanelPalette extends JPanel implements ActionListener
{
	private static final String[] TOOL_NAMES = {
		"Bucket", "Contraste", "Rotation", "Luminosite",
		"Teinte", "Texte", "Miroir", "Fusion", "Redimension", "Pipette"
	};

	private ArrayList<JButton> toolButtons;

	private Controller ctrl;

	/**
	 * Constructeur du panel des outils.
	 * @param ctrl Le contrôleur de l'application.
	 */
	public PanelPalette( Controller ctrl )
	{
		this.ctrl = ctrl;

		this.toolButtons = new ArrayList<JButton>();

		this.setLayout( new BoxLayout(this, BoxLayout.Y_AXIS) );
		this.setBorder( BorderFactory.createEmptyBorder( 10,10,10,10) );

		Dimension buttonSize = new Dimension(150, 35);
		
		for( String toolName : TOOL_NAMES ) 
		{
			JButton button = new JButton( toolName );
			button.setMaximumSize( buttonSize );
			button.setPreferredSize( buttonSize );
			button.setMinimumSize( buttonSize );
			button.setAlignmentX( LEFT_ALIGNMENT );
			button.addActionListener( this );
			this.toolButtons.add( button );
			this.add( button );
			this.add( Box.createVerticalStrut(5) );
		}
	}

	/**
	 * Gère les actions des boutons du panneau des outils.
	 * @param e L'événement d'action déclenché.
	 */
	public void actionPerformed( ActionEvent e )
	{
		String toolName = null;

		if( e.getSource() instanceof JButton )
		{
			JButton clickedButton = (JButton) e.getSource();
			toolName = clickedButton.getText();
			this.ctrl.addMouseDessin();
		}

		if ( toolName != null && this.toolButtons.contains( e.getSource() ) ) 
		{
			this.ctrl.toolSelected( toolName ); 
			this.ctrl.setCurrentTool( ToolType.valueOf( toolName.toUpperCase() ) );
		}
	}
}
