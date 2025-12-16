import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class PanelDessin extends JPanel implements MouseListener
{
	// Attributs
	private BufferedImage image;
	private Controller    controller;
	private int color;

	// Constructeur
	public PanelDessin(Controller ctrl ,int width, int height)
	{
		this.controller = ctrl;
		this.color	    = 0;

		this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		this.setSize(this.controller.getWidth(), this.controller.getHeight());
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

		int tolerance   = 20;          

		this.controller.peindre(this.image, x, y, tolerance);
		this.controller.updateDessin();
	}


	public void mouseEntered(java.awt.event.MouseEvent e) {}
	public void mouseExited(java.awt.event.MouseEvent e) {}
	public void mousePressed(java.awt.event.MouseEvent e) {}
	public void mouseReleased(java.awt.event.MouseEvent e) {}
}
