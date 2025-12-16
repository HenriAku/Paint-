import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Toolkit;

import Model.*;
public class Controller 
{
	private FramePrincipale  framePrincipale;

	private ImageLoader      imageLoader;
	private ImageTransformer imageTransformer;
	private BucketTool       bucketTool;
	private TextTool         textTool;
	
	private int width;
	private int height;
	private int color;
	
	public Controller()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.width = (int) screenSize.getWidth();
		this.height = (int) screenSize.getHeight();
		
		this.framePrincipale  = new FramePrincipale(this);
		this.imageLoader      = new ImageLoader(this.width, this.height);
		
		this.imageTransformer = new ImageTransformer();
		this.bucketTool       = new BucketTool();
		//this.textTool         = new TextTool();
	}

	public int getWidth () {return this.width ;}
	public int getHeight() {return this.height;}

	/**
	 * Charge une image depuis le chemin spécifié
	 * @param filePath
	 */
	public void addImage(String filePath){this.imageLoader.loadImage(filePath);}

	/**
	 * Retourne l'image courante
	 * @return BufferedImage
	 */
	public BufferedImage getBufferedImage() {return this.imageLoader.getBufferedImage();}

	/**
	 * Met à jour l'affichage du dessin
	 */
	public void updateDessin(){this.framePrincipale.repaint();}

	/**
	 * Applique l'outil seau de peinture à l'image aux coordonnées spécifiées
	 * @param img
	 * @param x
	 * @param y
	 * @param tolerance
	 */
	public void peindre(BufferedImage img, int x, int y, int tolerance)
	{
		this.bucketTool.peindre(img, x, y, this.color, tolerance);
	}

	public void rotation(double angle)
	{
		this.imageTransformer.rotation(this.getBufferedImage(), this.getBufferedImage(), angle);
	}

	public void adjustContrast(double contrastLevel)
	{
		this.imageTransformer.adjustContrast(this.getBufferedImage(), contrastLevel);
	}

	public void adjustBrightness(int brightnessLevel)
	{
		this.imageTransformer.adjustBrightness(this.getBufferedImage(), brightnessLevel);
	}

	/**
	 * Ajoute l'outil seau de peinture avec la couleur spécifiée
	 * @param color
	 */
	public void addMouseDessin(int color)
	{
		this.color = color;
		this.framePrincipale.addMouseDessin();
	}

	/**
	 * Retire l'outil seau de peinture
	 */
	public void removeMouseDessin(){this.framePrincipale.removeMouseDessin();}


	public static void main(String[] args)
	{
		new Controller();
	}
}