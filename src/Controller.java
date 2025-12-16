import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

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

	public void                sauvegarder           (String filePath){this.imageLoader.sauvegarder(filePath);          }
	public void                addImage              (String filePath){this.imageLoader.loadImage(filePath);            }
	public void                addImageHistorique    ()               {this.imageLoader.addImageHistorique();           }
	public BufferedImage       getBufferedImage      ()               {return this.imageLoader.getBufferedImage();      }
	public List<BufferedImage> getImagesHistorique   ()               {return this.imageLoader.getImagesHistorique();   }
	public BufferedImage       getLastImageHistorique()               {return this.imageLoader.getLastImageHistorique();}


	public void updateDessin(){this.framePrincipale.repaint();}


	public void peindre(BufferedImage img, int x, int y, int tolerance)
	{
		this.addImageHistorique();
		this.bucketTool.peindre(img, x, y, this.color, tolerance);
	}

	public void rotation(double angle)
	{
		this.addImageHistorique();
		this.imageTransformer.rotation(this.getBufferedImage(), angle);
	}

	public void adjustContrast(double contrastLevel)
	{
		this.addImageHistorique();
		this.imageTransformer.adjustContrast(this.getBufferedImage(), contrastLevel);
	}

	public void adjustBrightness(int brightnessLevel)
	{
		this.addImageHistorique();
		this.imageTransformer.adjustBrightness(this.getBufferedImage(), brightnessLevel);
	}

	public void adjustHue(int rOffset, int gOffset, int bOffset)
	{
		this.addImageHistorique();
		this.imageTransformer.adjustHue(this.getBufferedImage(), rOffset, gOffset, bOffset);
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