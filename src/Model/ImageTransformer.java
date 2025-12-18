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
	 * Supeposer une image par-dessus une autre en respectant une couleur transparente
	 * @param imageFond Image de imageFond
	 * @param cheminimage2 String chemin de l'image à superposer
	 * @param couleurTransparente Couleur considérée comme transparente dans l'image à superposer
	 * @param posX Position X où coller l'image à superposer
	 * @param posY Position Y où coller l'image à superposer
	 */
	public void superposer( BufferedImage imageFond, String cheminimage2, int couleurTransparente, int posX, int posY )
	{		
		BufferedImage image2 = null;
		System.out.println(couleurTransparente);
		try {
			File file = new File(cheminimage2);

			if ( file.getName().toLowerCase().endsWith(".png") ) 
			{
				image2 = ImageIO.read(file);
			}

			//Vérification de la position de l'image à superposer
			if ( image2.getWidth() > imageFond.getWidth() ||
				 image2.getHeight() > imageFond.getHeight() ) 
			{
				image2 = redimensionner(image2, image2.getHeight()/2, image2.getWidth()/2);
			}

			//Parcourir l'image pour envoyer la couleur de tout les pixels au imageFond si la couleur n'est pas transparente 
			for ( int y = 0; y < image2.getHeight(); y++ ) 
			{
				for (int x = 0; x < image2.getWidth(); x++) 
				{
					int destX = posX + x;
					int destY = posY + y;

					if (destX < 0 || destY < 0 ||
						destX >= imageFond.getWidth() ||
						destY >= imageFond.getHeight()) {
						continue;
					}

					int pixel = image2.getRGB(x, y) & 0xFFFFFF;

					if (pixel != couleurTransparente) 
					{
						imageFond.setRGB(destX, destY, image2.getRGB(x, y));
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

	/**
	 * Applique un anti-aliasing simple en lissant les pixels transparents autour des pixels non transparents
	 * @param src Image à modifier
	 */
	public void antiAliasing( BufferedImage src )
	{
		if ( src == null ) return;
		
		int width  = src.getWidth ();
		int height = src.getHeight();
		
		for ( int y = 0; y < height; y++ ) 
		{
			for ( int x = 0; x < width; x++ ) 
			{
				int colorPixel = src.getRGB( x, y );
				int alpha = (colorPixel >> 24) & 0xFF;

				// Appliquer l'anti-aliasing aux pixels semi-transparents
				if (alpha > 0 && alpha < 255) {
					int count = 0;
					int rTotal = 0, gTotal = 0, bTotal = 0;

					// Parcourir les pixels voisins
					for (int j = -2; j <= 2; j++) {
						for (int i = -2; i <= 2; i++) {
							if (i == 0 && j == 0) continue;

							int neighborX = x + i;
							int neighborY = y + j;

							if (neighborX >= 0 && neighborX < width && neighborY >= 0 && neighborY < height) {
								int neighborColor = src.getRGB(neighborX, neighborY);
								int neighborAlpha = (neighborColor >> 24) & 0xFF;
								
								if (neighborAlpha > 0) {
									Color c = new Color(neighborColor, true);
									rTotal += c.getRed();
									gTotal += c.getGreen();
									bTotal += c.getBlue();
									count++;
								}
							}
						}
					}

					if (count > 0) {
						int rAvg = rTotal / count;
						int gAvg = gTotal / count;
						int bAvg = bTotal / count;
						
						Color avgColor = new Color(rAvg, gAvg, bAvg, alpha);
						src.setRGB(x, y, avgColor.getRGB());
					}
				}
			}
		}
	}

	/**
	 * Fusionne deux images avec un fondu (blend) sur une zone spécifiée
	 * @param imageSrc
	 * @param cheminimage2
	 * @param bnd
	 * @return
	 */
	public BufferedImage fusion(BufferedImage imageSrc, String cheminimage2, int bnd) 
	{
		BufferedImage biImg2 = null;
		BufferedImage biOutput = null;

		try {
			biImg2 = ImageIO.read(new File(cheminimage2));
		} catch (Exception e) {
			e.printStackTrace();
			return imageSrc; // Retourne l'original si erreur de lecture
		}

		if (biImg2.getWidth() != imageSrc.getWidth() && biImg2.getHeight() != imageSrc.getHeight()) 
		{
			biImg2 = redimensionner(biImg2, imageSrc.getHeight(), imageSrc.getWidth());
		}

		// Calcul de la taille de sortie
		int outW = imageSrc.getWidth() + biImg2.getWidth() - bnd; // On soustrait bnd pour les superposer
		int outH = Math.max(imageSrc.getHeight(), biImg2.getHeight());

		biOutput = new BufferedImage(outW, outH, BufferedImage.TYPE_INT_ARGB);

		// 1. Dessiner la première image
		for(int x = 0; x < imageSrc.getWidth(); x++) {
			for(int y = 0; y < imageSrc.getHeight(); y++) {
				biOutput.setRGB(x, y, imageSrc.getRGB(x, y));
			}
		}

		// 2. Dessiner la deuxième image (décalée de la largeur de la 1ère - bnd)
		int offset = imageSrc.getWidth() - bnd;
		for(int x = 0; x < biImg2.getWidth(); x++) {
			for(int y = 0; y < biImg2.getHeight(); y++) {
				// On ne dessine que si on ne dépasse pas la largeur totale
				if (x + offset >= 0 && x + offset < outW) {
					biOutput.setRGB(x + offset, y, biImg2.getRGB(x, y));
				}
			}
		}

		// 3. Appliquer le fondu (Blend) uniquement sur la zone de contact
		for(int x = 0; x < bnd; x++) {
			for(int y = 0; y < outH; y++) {
				// VERIFICATION DES BORNES pour y
				if (y < imageSrc.getHeight() && y < biImg2.getHeight()) {
					
					int rgbA = imageSrc.getRGB(imageSrc.getWidth() - bnd + x, y);
					int rgbB = biImg2.getRGB(x, y);

					int aA = (rgbA >> 24) & 0xff;
					int rA = (rgbA >> 16) & 0xff;
					int gA = (rgbA >> 8) & 0xff;
					int bA = (rgbA) & 0xff;

					int rB = (rgbB >> 16) & 0xff;
					int gB = (rgbB >> 8) & 0xff;
					int bB = (rgbB) & 0xff;

					// Calcul du ratio de mélange (0.0 à 1.0)
					double ratio = (double) x / bnd;
					int r = (int) (rA * (1 - ratio) + rB * ratio);
					int g = (int) (gA * (1 - ratio) + gB * ratio);
					int b = (int) (bA * (1 - ratio) + bB * ratio);

					int rgb = (0xff << 24) | (r << 16) | (g << 8) | b;
					biOutput.setRGB(imageSrc.getWidth() - bnd + x, y, rgb);
				}
			}
		}

		return biOutput; // IMPORTANT : On retourne la nouvelle image
	}
}

