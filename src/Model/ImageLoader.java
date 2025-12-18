package Model;

import java.util.List;
import java.util.ArrayList;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Classe pour charger et sauvegarder des images
 */
public class ImageLoader 
{
	private BufferedImage imageModif;
	private BufferedImage imageAvantModif;
	private List<BufferedImage> imagesHistoriqueArriere;
	private List<BufferedImage> imagesHistoriqueAvant;

	/**
	 * Constructeur
	 * @param largeur Largeur de l'image
	 * @param hauteur Hauteur de l'image
	 */
	public ImageLoader( int largeur, int hauteur )
	{
		try {
			this.imageModif = new BufferedImage( largeur, hauteur, BufferedImage.TYPE_INT_ARGB );
			this.imageAvantModif = this.imageModif;
		} catch ( Exception e ) {
			System.out.println(e);
		}

		this.imagesHistoriqueArriere = new ArrayList<BufferedImage>();
		this.imagesHistoriqueAvant   = new ArrayList<BufferedImage>();
	}

	/**
	 * Retourne l'image chargée
	 * @return BufferedImage
	 */
	public BufferedImage getBufferedImage () { return this.imageModif     ; }
	public BufferedImage getOriginalImage () { return this.imageAvantModif; }

	/**
	 * Retourne la liste des images de l'historique arrière
	 * @return List<BufferedImage>
	 */
	public List<BufferedImage> getImagesHistoriqueArriere() { return this.imagesHistoriqueArriere; }

	/**
	 * Retourne la liste des images de l'historique avant
	 * @return List<BufferedImage>
	 */
	public List<BufferedImage> getImagesHistoriqueAvant  () { return this.imagesHistoriqueAvant  ; }

	/**
	 * Retourne la dernière image de l'historique
	 * @return BufferedImage
	 */
	public BufferedImage getLastImageHistoriqueArriere()
	{
		if ( this.imagesHistoriqueArriere.isEmpty() )
		{
			return this.imageModif;
		}
		
		BufferedImage img = this.imagesHistoriqueArriere.get( this.imagesHistoriqueArriere.size() - 1) ;

		this.imagesHistoriqueArriere.remove( this.imagesHistoriqueArriere.size() - 1 );
		this.imagesHistoriqueAvant.add( img );
		this.imageModif = img;

		return this.imageModif;
	}

	/**
	 * Retourne la prochaine image de l'historique
	 * @return BufferedImage
	 */
	public BufferedImage getNextImageHistoriqueAvant()
	{
		if ( this.imagesHistoriqueAvant.isEmpty() )
		{
			return this.imageModif;
		}
		
		BufferedImage img = this.imagesHistoriqueAvant.get( this.imagesHistoriqueAvant.size() - 1 );

		this.imagesHistoriqueAvant.remove( this.imagesHistoriqueAvant.size() - 1 );
		this.imagesHistoriqueArriere.add( img );
		this.imageModif = img;

		return this.imageModif;
	}

	/**
	 * Ajoute une image à l'historique
	 * @param img BufferedImage
	 */
	public void addImageHistorique()
	{
		BufferedImage copie = new BufferedImage( this.imageModif.getWidth(), this.imageModif.getHeight(), this.imageModif.getType() );

		copie.getGraphics().drawImage( this.imageModif, 0, 0, null );
		this.imagesHistoriqueArriere.add( copie );
	}

	/**
	 * Remplace l'image source par une nouvelle image
	 * @param newImage BufferedImage
	 */
	public void setBufferedImage( BufferedImage newImage ) { this.imageModif      = newImage; }

	/**
	 * Remplace l'image originale par une nouvelle image
	 * @param newImage BufferedImage
	 */
	public void setOriginalImage( BufferedImage newImage ) { this.imageAvantModif = newImage; }

	/**
	 * Charge une image depuis le chemin spécifié
	 * @param chemin String
	 */
	public void loadImage( String chemin )
	{
		try
		{
			File file = new File( chemin );

			if ( file.getName().toLowerCase().endsWith(".png") )
			{
				this.imageModif = ImageIO.read( file );
				this.imageAvantModif = this.imageModif;
			}
		} catch ( IOException e ) {
			System.out.println(e);
		}
		this.imagesHistoriqueArriere.add( imageModif );
	}

	/**
	 * Sauvegarde l'image courante vers le chemin spécifié
	 * @param chemin String
	 */
	public void sauvegarder( String chemin )
	{
		try
		{
			File file = new File( chemin );
			ImageIO.write( this.imageModif, "png", file );
		} catch ( IOException e ) {
			System.out.println( e );
		}
	}
}