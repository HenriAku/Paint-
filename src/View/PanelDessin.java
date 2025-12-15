import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class PanelDessin extends JPanel 
{
	// Attributs
	private BufferedImage image;
	private Controller    controller;

	// Constructeur
	public PanelDessin(Controller ctrl)
	{
		this.controller = ctrl;
		
		this.image = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_ARGB);
		this.image.getGraphics().fillRect(0, 0, 1200, 800);
		this.setSize(1200, 800);
	}
}
