import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class PanelDessin extends JPanel implements MouseListener
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

	public void addMouse         (){this.addMouseListener(this);}
	public void removeMouseDessin(){this.removeMouseListener(this);}

	public void mouseClicked(java.awt.event.MouseEvent e) 
	{
		int x = e.getX();
		int y = e.getY();

		int rgbNewColor = 0xFFFF0000;
		int tolerance   = 20;          

		this.controller.peindre(this.image, x, y, rgbNewColor, tolerance);
		this.controller.updateDessin();
	}


	public void mouseEntered(java.awt.event.MouseEvent e) {}
	public void mouseExited(java.awt.event.MouseEvent e) {}
	public void mousePressed(java.awt.event.MouseEvent e) {}
	public void mouseReleased(java.awt.event.MouseEvent e) {}
}
