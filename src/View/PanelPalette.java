package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFileChooser;

import Main.Controller;
import Main.ToolType;


/**
 * Panel contenant les outils de l'application Paint.
 */
public class PanelPalette extends JPanel implements ActionListener
{
	private static final String[] TOOL_NAMES = {
		"Bucket", "Contraste", "Texte", "Rotation", "Luminosite",
		"Teinte", "Texte", "Miroir", "Fusion", "Redimension"
	};

	private ArrayList<JButton> toolButtons;

	private JButton   ouvrirImage;
	private JButton   btnSauvegarder;
	private JButton   btnAnnuler;

	private Controller ctrl;

	/**
	 * Constructeur du panel des outils.
	 * @param ctrl Le contrôleur de l'application.
	 */
	public PanelPalette( Controller ctrl )
	{
		this.ctrl = ctrl;

		this.toolButtons = new ArrayList<JButton>();

		this.setLayout( new BoxLayout( this, BoxLayout.Y_AXIS) );
		this.setBorder( BorderFactory.createEmptyBorder( 10,10,10,10) );

		for( String toolName : TOOL_NAMES ) 
		{
			JButton button = new JButton( toolName );
			button.addActionListener( this );
			this.toolButtons.add( button );
			this.add( button );
		}

		this.ouvrirImage     = new JButton( "Ouvrir Image" );
		this.btnSauvegarder  = new JButton( "Sauvegarder"  );
		this.btnAnnuler      = new JButton( "Annuler"      );

		this.ouvrirImage   .addActionListener( this );
		this.btnSauvegarder.addActionListener( this );
		this.btnAnnuler    .addActionListener( this );

		this.add( this.ouvrirImage    );
		this.add( this.btnSauvegarder );
		this.add( this.btnAnnuler     );
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
		}

		if ( toolName != null && this.toolButtons.contains( e.getSource() ) ) 
		{
			this.ctrl.toolSelected( toolName ); 
			this.ctrl.setCurrentTool( ToolType.valueOf( toolName.toUpperCase() ) );
		}

		// Action pour ouvrir une image
		if( this.ouvrirImage == e.getSource() ) 
		{
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory( new java.io.File(".") );
			fileChooser.setDialogTitle( "Choisir une image" );
			fileChooser.setFileSelectionMode( JFileChooser.FILES_ONLY );
			fileChooser.setAcceptAllFileFilterUsed( false );

			int result = fileChooser.showOpenDialog( this );
			
			if ( result == JFileChooser.APPROVE_OPTION )
			{
				String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
				this.ctrl.addImage( selectedFilePath );
			};
		}

		if( this.btnSauvegarder == e.getSource() ) 
		{
			// Action pour sauvegarder l'image
		}

		// Action pour annuler la dernière opération
		if( this.btnAnnuler == e.getSource() ) 
		{
			this.ctrl.removeMouseDessin();
		}
	}
}
