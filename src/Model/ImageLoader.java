package Model;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageLoader 
{
	private BufferedImage src;

	public ImageLoader(int taille)
	{
		try {
			src = new BufferedImage(taille, taille, BufferedImage.TYPE_INT_ARGB);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public ImageLoader(String chemin)
	{
		try {
			src = ImageIO.read(new File(chemin));
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public BufferedImage getBufferedImage()
	{
		return src;
	}
}