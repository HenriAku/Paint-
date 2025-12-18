package View;

import javax.swing.JFrame;
import Main.Controller;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

public class FramePrincipale extends JFrame
{
	// Attributs
	private PanelTools  panelTools ;
	private PanelMenu   panelMenu  ;
	private PanelDessin panelDessin;

	private Controller   controller;

	protected int width ;
	protected int height;

	/**
	 * Constructeur de la FramePrincipale
	 * @param ctrl Le controller de l'application
	 */
	public FramePrincipale(Controller ctrl)
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.width  = (int) screenSize.getWidth ();
		this.height = (int) screenSize.getHeight();

		this.controller  = ctrl;
		this.panelTools  = new PanelTools (this.controller);
		this.panelMenu   = new PanelMenu  (this.controller);
		this.panelDessin = new PanelDessin(this.controller, this.width, this.height);

		this.setTitle("Paint-");
		this.setSize(this.width, this.height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		this.add(this.panelTools , BorderLayout.WEST);
		this.add(this.panelMenu  , BorderLayout.NORTH);
		this.add(this.panelDessin, BorderLayout.CENTER);

		this.setVisible(true);
	}

	/**
	 * Méthodes de mise à jour des panels
	 */
	public void repaint (){this.panelDessin.repaint();}

	/**
	 * Méthodes de redirection des appels aux panels
	 */
	public void updateColorDisplay (){this.panelTools.updateColorDisplay();}

	/**
	 * Méthodes de redirection des appels aux panels
	 */
	public void refreshTextures (){this.panelTools.refreshTextures();}

	/**
	 * Méthodes de redirection des appels aux panels
	 */
	public void addMouseDessin (){this.panelDessin.addMouse();}

	/**
	 * Méthodes de redirection des appels aux panels
	 */
	public void removeMouseDessin (){this.panelDessin.removeMouseDessin();}

	/**
	 * Méthodes de redirection des appels aux panels
	 */
	public void showParametrePanel(String toolName) {this.panelTools.showCard(toolName);}
}	