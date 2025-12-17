package Model;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * Classe utilitaire pour la transformation d'images
 */
public class ImageTransformer
{
	/**
	 * Constructeur par défaut
	 */
	public ImageTransformer() {}
	
	/**
	 * Méthode pour modifier le contraste d'une couleur
	 * @param c Couleur d'origine
	 * @param contraste Niveau de contraste à appliquer
	 * @return Nouvelle couleur avec le contraste appliqué
	 */
	private static Color contraste( Color c, int contraste )
	{
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();
		int a = c.getAlpha();

		r = (int)( r + contraste / 100.0 * (r - 127) );
		g = (int)( g + contraste / 100.0 * (g - 127) );
		b = (int)( b + contraste / 100.0 * (b - 127) );

		// S'assurer que les valeurs restent dans la plage 0-255
		r = Math.max( 0, Math.min(255, r) );
		g = Math.max( 0, Math.min(255, g) );
		b = Math.max( 0, Math.min(255, b) );

		return new Color( r, g, b, a );
	}

	/**
	 * Méthode pour ajuster le contraste de l'image
	 * @param image Image à modifier
	 * @param contrastLevel Niveau de contraste à appliquer
	 */
	public void adjustContrast( BufferedImage image, double contrastLevel )
	{
		int width  = image.getWidth();
		int height = image.getHeight();

		// Parcourir chaque pixel de l'image
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Color originalColor = new Color( image.getRGB(x, y), true );
				Color newColor = contraste( originalColor, (int)contrastLevel );
				image.setRGB( x, y, newColor.getRGB() );
			}
		}
	}

	/**
	 * Méthode pour modifier la luminosité d'une couleur
	 * @param c Couleur d'origine
	 * @param luminosite Niveau de luminosité à appliquer
	 * @return Nouvelle couleur avec la luminosité appliquée
	 */
	private static Color luminosite( Color c, int luminosite )
	{
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();
		int a = c.getAlpha();

		r = r + luminosite;
		g = g + luminosite;
		b = b + luminosite;

		// S'assurer que les valeurs restent dans la plage 0-255
		r = Math.max( 0, Math.min(255, r) );
		g = Math.max( 0, Math.min(255, g) );
		b = Math.max( 0, Math.min(255, b) );

		return new Color( r, g, b, a );
	}

	/**
	 * Méthode pour ajuster la luminosité de l'image
	 * @param image Image à modifier
	 * @param brightnessLevel Niveau de luminosité à appliquer
	 */
	public void adjustBrightness( BufferedImage image, int brightnessLevel )
	{
		int width = image.getWidth();
		int height = image.getHeight();

		// Parcourir chaque pixel de l'image
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Color originalColor = new Color( image.getRGB(x, y), true );
				Color newColor = luminosite( originalColor, brightnessLevel );
				image.setRGB( x, y, newColor.getRGB() );
			}
		}
	}

	/**
	 * Méthode pour modifier la teinte d'une couleur
	 * @param c Couleur d'origine
	 * @param rOffset Décalage rouge
	 * @param gOffset Décalage vert
	 * @param bOffset Décalage bleu
	 * @return Nouvelle couleur avec la teinte appliquée
	 */
	private static Color teinte( Color c, int rOffset, int gOffset, int bOffset )
	{
		int r = c.getRed() + rOffset;
		int g = c.getGreen() + gOffset;
		int b = c.getBlue() + bOffset;
		int a = c.getAlpha();

		// S'assurer que les valeurs restent dans la plage 0-255
		r = Math.max( 0, Math.min(255, r) );
		g = Math.max( 0, Math.min(255, g) );
		b = Math.max( 0, Math.min(255, b) );

		return new Color( r, g, b, a );
	}

	/**
	 * Méthode pour ajuster la teinte de l'image
	 * @param image Image à modifier
	 * @param rOffset Décalage rouge
	 * @param gOffset Décalage vert
	 * @param bOffset Décalage bleu
	 */
	public void adjustHue( BufferedImage image, int rOffset, int gOffset, int bOffset )
	{
		int width = image.getWidth();
		int height = image.getHeight();

		// Parcourir chaque pixel de l'image
		for ( int y = 0; y < height; y++ )
		{
			for ( int x = 0; x < width; x++ )
			{
				Color originalColor = new Color( image.getRGB(x, y), true );
				Color newColor = teinte( originalColor, rOffset, gOffset, bOffset );
				image.setRGB( x, y, newColor.getRGB() );
			}
		}
	}

	/**
	 * Fusionne une image par-dessus une autre en respectant une couleur transparente
	 * @param fond Image de fond
	 * @param dessus Image à superposer
	 * @param couleurTransparente Couleur considérée comme transparente dans l'image à superposer
	 * @param posX Position X où coller l'image à superposer
	 * @param posY Position Y où coller l'image à superposer
	 */
	public void fusionner( BufferedImage fond, String dessusPath, int couleurTransparente, int posX, int posY )
	{		
		BufferedImage imageDessus = null;
        try {
			File file = new File(dessusPath);

			if ( file.getName().toLowerCase().endsWith(".png") ) 
			{
				imageDessus = ImageIO.read(file);
			}

            //Vérification de la position de l'image à superposer
            if ( imageDessus.getWidth() + posX > fond.getWidth() ||
                 imageDessus.getHeight() + posY > fond.getHeight() ) 
			{
                System.out.println("Erreur : l'image à superposer dépasse les limites de l'image de fond.");
                return;
            }

            //Parcourir l'image pour envoyer la couleur de tout les pixels au fond si la couleur n'est pas transparente 
            for ( int y = 0; y < imageDessus.getHeight(); y++ ) 
			{
                for ( int x = 0; x < imageDessus.getWidth(); x++ ) 
				{
                    int pixel = imageDessus.getRGB(x, y) & 0xFFFFFF;
                    if ( pixel != couleurTransparente ) 
						{
                        fond.setRGB( posX + x, posY + y, imageDessus.getRGB(x, y) );
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

	/**
	 * Rotation de l'image d'un angle spécifié
	 * @param orig Image originale
	 * @param angle Angle de rotation en degrés
	 * @return Image modifiée après rotation
	 */
	public BufferedImage rotation( BufferedImage orig, double angle )
	{
		try {
			int origWidth = orig.getWidth();
			int origHeight = orig.getHeight();
			
			angle = angle % 360;
			if ( angle > 180  ) angle -= 360;
			if ( angle < -180 ) angle += 360;

			int largeur, hauteur;
			
			//Pour les rotations de 90° et -90°, inverser largeur et hauteur
			if (Math.abs(angle - 90) < 0.01 || Math.abs(angle + 90) < 0.01 || 
			    Math.abs(angle - 270) < 0.01 || Math.abs(angle + 270) < 0.01) 
			{
				largeur = origHeight;
				hauteur = origWidth;
			} // Pour les rotations de 180° et -180° (et 0°), garder les mêmes dimensions
			else if ( Math.abs(angle) < 0.01 || Math.abs(Math.abs(angle) - 180) < 0.01 ) 
			{
				largeur = origWidth;
				hauteur = origHeight;
			}// Pour les autres angles, calculer les dimensions nécessaires
			else 
			{
				double a = Math.toRadians( angle );

				double cosA = Math.abs( Math.cos(a) );
				double sinA = Math.abs( Math.sin(a) );

				largeur = (int) Math.ceil( origWidth * cosA + origHeight * sinA );
				hauteur = (int) Math.ceil( origWidth * sinA + origHeight * cosA );
			}

			BufferedImage nouvelleImage = new BufferedImage( largeur, hauteur, BufferedImage.TYPE_INT_ARGB );

			double a = Math.toRadians( angle );
			double cosAngle = Math.cos( a );
			double sinAngle = Math.sin( a );

			// Centres de l'image source et destination
			int cxSrc  = origWidth  / 2;
			int cySrc  = origHeight / 2;
			int cxDest = largeur    / 2;
			int cyDest = hauteur    / 2;

			// Appliquer la rotation
			for ( int i2 = 0; i2 < largeur; i2++ )
			{
				for (int j2 = 0; j2 < hauteur; j2++ )
				{

					double xd = i2 - cxDest;
					double yd = j2 - cyDest;

					// Rotation inverse pour retrouver le pixel source
					double xs =  xd * cosAngle + yd * sinAngle;
					double ys = -xd * sinAngle + yd * cosAngle;

					int i = (int) Math.round( xs + cxSrc );
					int j = (int) Math.round( ys + cySrc );

					// Vérifier si le pixel source est dans les limites de l'image originale
					if ( i >= 0 && i < origWidth && j >= 0 && j < origHeight )
					{
						nouvelleImage.setRGB( i2, j2, orig.getRGB( i, j ) );
					}
					else {
						nouvelleImage.setRGB( i2, j2, 0x00000000 ); // Transparent
					}
				}
			}

			return nouvelleImage;
		} catch (Exception e) {
			System.out.println(e);
			return orig; // En cas d'erreur, retourner l'image originale
		}
	}

	/**
	 * Miroir horizontal de l'image
	 * @param src Image à modifier
	 */
	public void mirrorHorizontal( BufferedImage src )
	{
		if ( src == null ) return;

		int width  = src.getWidth ();
		int height = src.getHeight();

		for ( int y = 0; y < height; y++ )
		{
			for ( int x = 0; x < width / 2; x++ ) 
			{
				int mirrorX = width - 1 - x;

				int leftPixelColor  = src.getRGB( x, y );
				int rightPixelColor = src.getRGB( mirrorX, y );

				src.setRGB( x, y, rightPixelColor      );     				
				src.setRGB( mirrorX, y, leftPixelColor );
			}
		}
	}

	/**
	 * Miroir vertical de l'image
	 * @param src Image à modifier
	 */
	public void mirrorVertical( BufferedImage src )
	{
		if ( src == null ) return;
		
		int width  = src.getWidth ();
		int height = src.getHeight();
		
		for ( int y = 0; y < height / 2; y++ ) 
		{
			for ( int x = 0; x < width; x++ ) 
			{
				int mirrorY = height - 1 - y;

				int topPixelColor    = src.getRGB( x, y );
				int bottomPixelColor = src.getRGB( x, mirrorY );

				src.setRGB( x, y, bottomPixelColor    );     				
				src.setRGB( x, mirrorY, topPixelColor );
			}
		}
	}

	/**
	 * Redimensionne une image aux dimensions spécifiées en utilisant le rééchantillonnage Nearest Neighbor
	 * @param src Image source à redimensionner
	 * @param hauteur Hauteur souhaitée
	 * @param largeur Largeur souhaitée
	 * @return Image redimensionnée
	 */
	public BufferedImage redimensionner( BufferedImage src, int hauteur, int largeur )
	{
		if ( src == null ) return null;

		int srcWidth  = src.getWidth ();
        int srcHeight = src.getHeight();

		BufferedImage tmp = new BufferedImage( largeur, hauteur, BufferedImage.TYPE_INT_ARGB );

		double scaleX = (double) srcWidth  / largeur;
		double scaleY = (double) srcHeight / hauteur;

		// Parcourir chaque pixel de l'image destination
		for ( int yDest = 0; yDest < hauteur; yDest++ )
		{
			for ( int xDest = 0; xDest < largeur; xDest++ )
			{
				
				// Calculer la coordonnée correspondante dans l'image source (Nearest Neighbor)
				// L'ajout de 0.5 permet un meilleur centrage des pixels lors du rééchantillonnage
				int xSrc = (int) ( xDest * scaleX );
				int ySrc = (int) ( yDest * scaleY );

				// S'assurer que les indices restent dans les limites (même si le cast int devrait le faire)
				if ( xSrc >= srcWidth ) xSrc = srcWidth  - 1;
				if ( ySrc >= srcHeight) ySrc = srcHeight - 1;

				// Récupérer la couleur et la copier dans la destination
				int pixel = src.getRGB( xSrc, ySrc );
				tmp.setRGB( xDest, yDest, pixel );
			}
		}
		return tmp;
	}
}
