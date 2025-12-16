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
	private BufferedImage src2;
	private List<BufferedImage> imagesHistoriqueArriere;
	private List<BufferedImage> imagesHistoriqueAvant;

	//Constructeur pour créer une image vide de la taille spécifiée
	public ImageLoader(int largeur, int hauteur)
	{
		try {
			this.src = new BufferedImage(largeur, hauteur, BufferedImage.TYPE_INT_ARGB);
		} catch (Exception e) {
			System.out.println(e);
		}

		this.imagesHistoriqueArriere = new ArrayList<BufferedImage>();
		this.imagesHistoriqueAvant = new ArrayList<BufferedImage>();
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

		this.imagesHistoriqueArriere = new ArrayList<BufferedImage>();
		this.imagesHistoriqueAvant = new ArrayList<BufferedImage>();
	}

	/**
	 * Retourne l'image chargée
	 * @return BufferedImage
	 */
	public BufferedImage getBufferedImage (){return this.src ;}
	public BufferedImage getBufferedImage2(){return this.src2;}

	/**
	 * Retourne la liste des images de l'historique
	 * @return List<BufferedImage>
	 */
	public List<BufferedImage> getImagesHistoriqueArriere(){return this.imagesHistoriqueArriere;}
	public List<BufferedImage> getImagesHistoriqueAvant  (){return this.imagesHistoriqueAvant  ;}

	/**
	 * Retourne la dernière image de l'historique
	 * @return BufferedImage
	 */
	public BufferedImage getLastImageHistoriqueArriere()
	{
		BufferedImage img = this.imagesHistoriqueArriere.get(this.imagesHistoriqueArriere.size() - 1);
		this.imagesHistoriqueArriere.remove(this.imagesHistoriqueArriere.size() - 1);
		this.imagesHistoriqueAvant.add(img);
		return img;
	}

	public BufferedImage getNextImageHistoriqueAvant()
	{
		BufferedImage img = this.imagesHistoriqueAvant.get(this.imagesHistoriqueAvant.size() - 1);
		this.imagesHistoriqueAvant.remove(this.imagesHistoriqueAvant.size() - 1);
		this.imagesHistoriqueArriere.add(img);
		return img;
	}

	/**
	 * Ajoute une image à l'historique
	 * @param img
	 */
	public void addImageHistorique(){this.imagesHistoriqueArriere.add(this.src);}


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

	public void loadImage2(String chemin)
	{
		try {
			File file = new File(chemin);

			if (file.getName().toLowerCase().endsWith(".png")) {
				this.src2 = ImageIO.read(file);
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