package Model;
import java.awt.*;

public class ImageLoader 
{
	private Image      image;

	public ImageLoader(String chemin)
	{
		this.image = Toolkit.getDefaultToolkit().getImage(chemin);	
	}

	public Image getImage()
	{
		return image;
	}
}