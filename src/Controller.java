import java.awt.image.BufferedImage;
import java.util.List;
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

	public void                sauvegarder           (String filePath){this.imageLoader.sauvegarder(filePath);     }
	public void                addImage              (String filePath){this.imageLoader.loadImage(filePath);       }
	public void                addImageHistorique    ()               {this.imageLoader.addImageHistorique();      }
	public BufferedImage       getBufferedImage      ()               {return this.imageLoader.getBufferedImage(); }
	public List<BufferedImage> getImagesHistoriqueArriere   (){return this.imageLoader.getImagesHistoriqueArriere();   }
	public BufferedImage       getLastImageHistoriqueArriere(){return this.imageLoader.getLastImageHistoriqueArriere();}
	public List<BufferedImage> getImagesHistoriqueAvant     (){return this.imageLoader.getImagesHistoriqueAvant();     }
	public BufferedImage       getNextImageHistoriqueAvant  (){return this.imageLoader.getNextImageHistoriqueAvant();  }

	public void peindre(BufferedImage img, int x, int y, int newColorRGB, int tolerance)
	{
		this.bucketTool.peindre(img, x, y, newColorRGB, tolerance);
	}

	public void rotation(double angle)
	{
		// Toujours appliquer la rotation sur l'image ORIGINALE
		BufferedImage imageRotee = this.imageTransformer.rotation(this.imageLoader.getOriginalImage(), angle);
		this.imageLoader.setBufferedImage(imageRotee);
	}

	public void adjustContrast(double contrastLevel)
	{
		this.imageTransformer.adjustContrast(this.getBufferedImage(), contrastLevel);
	}

	public void adjustBrightness(int brightnessLevel)
	{
		this.imageTransformer.adjustBrightness(this.getBufferedImage(), brightnessLevel);
	}

	public void adjustHue(BufferedImage img, int rOffset, int gOffset, int bOffset)
	{
		this.imageTransformer.adjustHue(img, rOffset, gOffset, bOffset);
	}

	public void updateDessin     (){this.framePrincipale.repaint();}
	public void addMouseDessin   (){this.framePrincipale.addMouseDessin();}
	public void removeMouseDessin(){this.framePrincipale.removeMouseDessin();}

	public void toolSelected(String toolName)
	{
		this.framePrincipale.showParametrePanel(toolName);
	}


	public static void main(String[] args)
	{
		new Controller();
	}
}