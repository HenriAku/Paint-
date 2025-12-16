package Model;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.List;
import java.util.ArrayList;

public class ImageLoader 
{
	private BufferedImage src;
	private List<BufferedImage> imagesHistorique;

	//Constructeur pour créer une image vide de la taille spécifiée
	public ImageLoader(int largeur, int hauteur)
	{
		try {
			this.src = new BufferedImage(largeur, hauteur, BufferedImage.TYPE_INT_ARGB);
		} catch (Exception e) {
			System.out.println(e);
		}

		this.imagesHistorique = new ArrayList<BufferedImage>();
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

		this.imagesHistorique = new ArrayList<BufferedImage>();
	}

	/**
	 * Retourne l'image chargée
	 * @return BufferedImage
	 */
	public BufferedImage getBufferedImage(){return this.src;}

	/**
	 * Retourne la liste des images de l'historique
	 * @return List<BufferedImage>
	 */
	public List<BufferedImage> getImagesHistorique(){return this.imagesHistorique;}

	/**
	 * Retourne la dernière image de l'historique
	 * @return BufferedImage
	 */
	public BufferedImage getLastImageHistorique(){return this.imagesHistorique.get(this.imagesHistorique.size() - 1);}

	/**
	 * Ajoute une image à l'historique
	 * @param img
	 */
	public void addImageHistorique(){this.imagesHistorique.add(this.src);}


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