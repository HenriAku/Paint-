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

		this.image = this.controller.getBufferedImage();
		this.setSize(1200, 800);

		this.setVisible(true);	
	}

	public void paintComponent(java.awt.Graphics g)
	{
		super.paintComponent(g);
		this.image = this.controller.getBufferedImage();
		if (this.image != null) {
			g.drawImage(this.image, 0, 0, null);
		}
	}
}
