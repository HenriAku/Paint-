import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import java.awt.Dimension;

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

        // 1. Récupérer l'image (maintenant, elle est déjà tournée et potentiellement plus grande)
        this.image = this.controller.getBufferedImage();

        if (this.image != null) 
        {
            // Les calculs de mise à l'échelle pour s'assurer que l'image RENTRE
            // dans le JPanel, même si elle est tournée et plus grande.
            int panelWidth  = getWidth();
            int panelHeight = getHeight();
            int imageWidth  = this.image.getWidth();
            int imageHeight = this.image.getHeight();

            double scaleX = (double)panelWidth / imageWidth;
            double scaleY = (double)panelHeight / imageHeight;
            double scale = Math.min(scaleX, scaleY); // Mise à l'échelle pour TOUT rentrer
            
            int newWidth  = (int) (imageWidth * scale);
            int newHeight = (int) (imageHeight * scale);

            int x = (panelWidth - newWidth) / 2;
            int y = (panelHeight - newHeight) / 2;
            
            g.drawImage(this.image, x, y, newWidth, newHeight, null);
        }
    }
	
	public void addMouse         (){this.addMouseListener(this);}
	public void removeMouseDessin(){this.removeMouseListener(this);}

	public void mouseClicked(java.awt.event.MouseEvent e) 
	{
		int x = e.getX();
		int y = e.getY();

		int tolerance   = 20;          

		this.controller.peindre(this.image, x, y, this.color, tolerance);
		this.controller.updateDessin();
	}


	public void mouseEntered(java.awt.event.MouseEvent e) {}
	public void mouseExited(java.awt.event.MouseEvent e) {}
	public void mousePressed(java.awt.event.MouseEvent e) {}
	public void mouseReleased(java.awt.event.MouseEvent e) {}
}
