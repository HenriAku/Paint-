package Model;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageLoader 
{
	private BufferedImage src;

	//Constructeur pour créer une image vide de la taille spécifiée
	public ImageLoader(int largeur, int hauteur)
	{
		try {
			this.src = new BufferedImage(largeur, hauteur, BufferedImage.TYPE_INT_ARGB);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Constructeur pour charger une image depuis le chemin spécifié
	 * @param chemin
	 */
	public ImageLoader(String chemin)
	{
		try {
			File file = new File(chemin);

			if (file.getName().toLowerCase().endsWith(".png")) {
				this.src = ImageIO.read(file);
			}
			
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Retourne l'image chargée
	 * @return BufferedImage
	 */
	public BufferedImage getBufferedImage(){return this.src;}


	/**
	 * Charge une image depuis le chemin spécifié
	 * @param chemin
	 */
	public void loadImage(String chemin)
	{
		try {
			File file = new File(chemin);

			if (file.getName().toLowerCase().endsWith(".png")) {
				this.src = ImageIO.read(file);
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Sauvegarde l'image courante vers le chemin spécifié
	 * @param chemin
	 */
	public void sauvegarder(String chemin)
	{
		try {
			File file = new File(chemin);
			ImageIO.write(this.src, "png", file);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}