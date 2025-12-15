import java.awt.image.BufferedImage;

import Model.*;
public class Controller 
{
	private FramePrincipale  framePrincipale;

	private ImageLoader      imageLoader;
	private ImageTransformer imageTransformer;
	private BucketTool       bucketTool;
	private TextTool         textTool;
	
	public Controller()
	{
		this.imageLoader      = new ImageLoader(1200, 800);
		this.framePrincipale  = new FramePrincipale(this);

		this.imageTransformer = new ImageTransformer();
		this.bucketTool       = new BucketTool();
		//this.textTool         = new TextTool();
	}

	public void addImage(String filePath){this.imageLoader.loadImage(filePath);}
	public BufferedImage getBufferedImage() {return this.imageLoader.getBufferedImage();}


	public void peindre(BufferedImage img, int x, int y, int newColorRGB, int tolerance)
	{
		this.bucketTool.peindre(img, x, y, newColorRGB, tolerance);
	}

	public void rotation(double angle)
	{
		this.imageTransformer.rotation(this.getBufferedImage(), this.getBufferedImage(), angle);
	}

	public void adjustContrast(double contrastLevel)
	{
		this.imageTransformer.adjustContrast(this.getBufferedImage(), contrastLevel);
	}

	public void updateDessin     (){this.framePrincipale.repaint();}
	public void addMouseDessin   (){this.framePrincipale.addMouseDessin();}
	public void removeMouseDessin(){this.framePrincipale.removeMouseDessin();}


	public static void main(String[] args)
	{
		new Controller();
	}
}