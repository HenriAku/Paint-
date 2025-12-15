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

		//this.imageTransformer = new ImageTransformer();
		//this.bucketTool       = new BucketTool();
		//this.textTool         = new TextTool();
	}

	public void addImage(String filePath){this.imageLoader.loadImage(filePath);}
	public BufferedImage getBufferedImage() {return this.imageLoader.getBufferedImage();}

	public void updateDessin() {this.framePrincipale.repaint();}


		
	public static void main(String[] args)
	{
		new Controller();
	}
}