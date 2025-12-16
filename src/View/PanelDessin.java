import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
public class PanelDessin extends JPanel implements MouseListener
{
	// Attributs
	private Controller    controller;
	private int color;
	// Variables pour stocker les informations d'affichage
	private int displayX, displayY, displayWidth, displayHeight;

	// Constructeur
	public PanelDessin(Controller ctrl ,int width, int height)
	{
		this.controller = ctrl;
		this.color	    = 0;

		this.setSize(this.controller.getWidth(), this.controller.getHeight());
		this.setVisible(true);	
	}

	public void paintComponent(java.awt.Graphics g)
	{
		super.paintComponent(g);

		// Récupérer l'image source depuis le controller
		BufferedImage image = this.controller.getBufferedImage();

		if (image != null) 
		{
			// Les calculs de mise à l'échelle pour s'assurer que l'image RENTRE
			// dans le JPanel, même si elle est tournée et plus grande.
			int panelWidth  = getWidth();
			int panelHeight = getHeight();
			int imageWidth  = image.getWidth();
			int imageHeight = image.getHeight();

			double scaleX = (double)panelWidth / imageWidth;
			double scaleY = (double)panelHeight / imageHeight;
			double scale = Math.min(scaleX, scaleY); // Mise à l'échelle pour TOUT rentrer
			
			this.displayWidth  = (int) (imageWidth * scale);
			this.displayHeight = (int) (imageHeight * scale);

			this.displayX = (panelWidth - this.displayWidth) / 2;
			this.displayY = (panelHeight - this.displayHeight) / 2;
			
			g.drawImage(image, this.displayX, this.displayY, this.displayWidth, this.displayHeight, null);
		}
	}

	public void addMouse         (){this.addMouseListener(this);}
	public void removeMouseDessin(){this.removeMouseListener(this);}

	public void mouseClicked(MouseEvent e) 
	{
		int mouseX = e.getX();
		int mouseY = e.getY();

		// Obtenir l'image source réelle
		BufferedImage image = this.controller.getBufferedImage();
		if (image == null) return;

		// Convertir les coordonnées de la souris en coordonnées de l'image source
		// Vérifier si le clic est dans la zone d'affichage de l'image
		if (mouseX < this.displayX || mouseX > this.displayX + this.displayWidth ||
			mouseY < this.displayY || mouseY > this.displayY + this.displayHeight) {
			return; // Clic en dehors de l'image
		}

		// Convertir les coordonnées écran en coordonnées image
		int imageX = (int)((mouseX - this.displayX) * image.getWidth() / (double)this.displayWidth);
		int imageY = (int)((mouseY - this.displayY) * image.getHeight() / (double)this.displayHeight);

		int tolerance = 20;

		switch (this.controller.getCurrentTool()) {
			case ToolType.BUCKET:
					this.controller.peindre(image, imageX, imageY, this.color, tolerance);
				break;

			case ToolType.FUSION:
					this.controller.fusionner(imageX, imageY);
					this.controller.removeMouseDessin();
				break;
			default:
				break;
		}
		this.controller.updateDessin();
	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}
