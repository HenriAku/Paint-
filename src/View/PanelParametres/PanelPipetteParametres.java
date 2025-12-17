package View.PanelParametres;

import Main.Controller;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Color;

/**
 * Panel des parametres de fusion
 */
public class PanelPipetteParametres extends JPanel
{
	private Controller ctrl;
	private JLabel  labelSelectionnerPixel;

	/**
	 * Constructeur du panel des parametres de pipette
	 * @param ctrl Le controller de l'application
	 */
	public PanelPipetteParametres( Controller ctrl )
	{
		this.ctrl = ctrl;

		this.setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );

		this.labelSelectionnerPixel = new JLabel("Sélectionner un pixel");

		this.add( this.labelSelectionnerPixel );
	}

	public void updateColorDisplay()
	{
		int rgb = this.ctrl.getPipetteColorRGB();
		this.labelSelectionnerPixel.setBackground(new Color(rgb));
		this.labelSelectionnerPixel.setOpaque(true);
		this.labelSelectionnerPixel.repaint();
	}
}
