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
		this.framePrincipale  = new FramePrincipale();

		//this.imageLoader      = new ImageLoader();
		//this.imageTransformer = new ImageTransformer();
		//this.bucketTool       = new BucketTool();
		//this.textTool         = new TextTool();
	}
	
	public static void main(String[] args)
	{
		new Controller();
	}
}