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

	public void          sauvegarder     (String filePath){this.imageLoader.sauvegarder(filePath);    }
	public void          addImage        (String filePath){this.imageLoader.loadImage(filePath);      }
	public BufferedImage getBufferedImage()               {return this.imageLoader.getBufferedImage();}
	public void          updateDessin    ()               {this.framePrincipale.repaint();            }


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

	public void addMouseDessin(int color)
	{
		this.color = color;
		this.framePrincipale.addMouseDessin();
	}

	public void removeMouseDessin(){this.framePrincipale.removeMouseDessin();}


	public static void main(String[] args)
	{
		new Controller();
	}
}