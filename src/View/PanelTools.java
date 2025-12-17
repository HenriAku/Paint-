package View;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import Main.Controller;

/**
 * PanelTools est le panneau latéral de l'application Paint qui regroupe les outils de dessin et les paramètres associés.
 */
public class PanelTools extends JPanel
{
	private Controller     controller     ;
	private PanelPalette   panelPalette   ;
	private PanelParametre panelParametres;
	
	public PanelTools( Controller ctrl )
	{
		this.controller = ctrl;

		this.panelPalette    = new PanelPalette  ( this.controller );
		this.panelParametres = new PanelParametre( this.controller );

		this.setLayout( new BorderLayout() );

		this.add( this.panelPalette, BorderLayout.NORTH     );
		this.add( this.panelParametres, BorderLayout.CENTER );
	}

	/**
	 * Affiche le panneau de paramètres correspondant à l'outil sélectionné.
	 * @param toolName Le nom de l'outil dont les paramètres doivent être affichés.
	 */
	public void showCard( String toolName )
	{
		this.panelParametres.showCard( toolName );
	}

	public void updateColorDisplay(){this.panelParametres.updateColorDisplay();}
}