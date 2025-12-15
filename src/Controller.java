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
		this.framePrincipale  = new FramePrincipale(this);

		this.imageLoader      = new ImageLoader(1200, 800);
		//this.imageTransformer = new ImageTransformer();
		//this.bucketTool       = new BucketTool();
		//this.textTool         = new TextTool();
	}

	public void addImage(String filePath)
	{
		this.imageLoader.loadImage(filePath);
	}
	
	public static void main(String[] args)
	{
		new Controller();
	}
}