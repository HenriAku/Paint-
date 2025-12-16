package Model;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import java.awt.Color;

// Implémentation de la logique pour la transformation d'images
public class ImageTransformer
{
	public ImageTransformer()
	{}
	
	// Méthode pour ajuster le contraste d'une couleur
	private static Color contraste(Color c, int contraste)
	{
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();
		int a = c.getAlpha();

		r = (int)(r + contraste / 100.0 * (r - 127));
		g = (int)(g + contraste / 100.0 * (g - 127));
		b = (int)(b + contraste / 100.0 * (b - 127));

		// S'assurer que les valeurs restent dans la plage 0-255
		r = Math.max(0, Math.min(255, r));
		g = Math.max(0, Math.min(255, g));
		b = Math.max(0, Math.min(255, b));

		return new Color(r, g, b, a);
	}

	// Méthode pour modifier le contraste d'une image
	public void adjustContrast(BufferedImage image, double contrastLevel)
	{
		int width = image.getWidth();
		int height = image.getHeight();

		// Parcourir chaque pixel de l'image
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Color originalColor = new Color(image.getRGB(x, y), true);
				Color newColor = contraste(originalColor, (int)contrastLevel);
				image.setRGB(x, y, newColor.getRGB());
			}
		}
	}

	// Méthode pour ajuster la luminosité d'une couleur
	private static Color luminosite(Color c, int luminosite)
	{
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();
		int a = c.getAlpha();

		r = r + luminosite;
		g = g + luminosite;
		b = b + luminosite;

		// S'assurer que les valeurs restent dans la plage 0-255
		r = Math.max(0, Math.min(255, r));
		g = Math.max(0, Math.min(255, g));
		b = Math.max(0, Math.min(255, b));

		return new Color(r, g, b, a);
	}

	// Méthode pour modifier la lumière de l'image
	public void adjustBrightness(BufferedImage image, int brightnessLevel)
	{
		int width = image.getWidth();
		int height = image.getHeight();

		// Parcourir chaque pixel de l'image
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Color originalColor = new Color(image.getRGB(x, y), true);
				Color newColor = luminosite(originalColor, brightnessLevel);
				image.setRGB(x, y, newColor.getRGB());
			}
		}
	}

	// Méthode pour ajuster la teinte d'une couleur
	private static Color teinte(Color c, int rOffset, int gOffset, int bOffset)
	{
		int r = c.getRed() + rOffset;
		int g = c.getGreen() + gOffset;
		int b = c.getBlue() + bOffset;

		// S'assurer que les valeurs restent dans la plage 0-255
		r = Math.max(0, Math.min(255, r));
		g = Math.max(0, Math.min(255, g));
		b = Math.max(0, Math.min(255, b));

		return new Color(r, g, b);
	}

	// Méthode pour modifier la teinte de l'image
	public void adjustHue(BufferedImage image, int rOffset, int gOffset, int bOffset)
	{
		int width = image.getWidth();
		int height = image.getHeight();
		// Parcourir chaque pixel de l'image
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Color originalColor = new Color(image.getRGB(x, y));
				Color newColor = teinte(originalColor, rOffset, gOffset, bOffset);
				image.setRGB(x, y, newColor.getRGB());
			}
		}
	}

	public void fusionner(BufferedImage fond, BufferedImage dessus, int couleurTransparente, int posX, int posY){
        try {
            //Vérification de la position de l'image à superposer
            if (dessus.getWidth() + posX > fond.getWidth() ||
                dessus.getHeight() + posY > fond.getHeight()) 
			{
                System.out.println("Erreur : l'image à superposer dépasse les limites de l'image de fond.");
                return;
            }

            //Parcourir l'image pour envoyer la couleur de tout les pixels au fond si la couleur n'est pas transparente 
            for (int y = 0; y < dessus.getHeight(); y++) 
			{
                for (int x = 0; x < dessus.getWidth(); x++) 
				{
                    int pixel = dessus.getRGB(x, y) & 0xFFFFFF;
                    if (pixel != couleurTransparente) 
						{
                        fond.setRGB(posX + x, posY + y, dessus.getRGB(x, y));
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
	 * @param orig
	 * @param angle
	 * @return orig Image modifier
	 */
	public BufferedImage rotation(BufferedImage orig, double angle)
	{
		try {
			int origWidth = orig.getWidth();
			int origHeight = orig.getHeight();
			
			angle = angle % 360;
			if (angle > 180) angle -= 360;
			if (angle < -180) angle += 360;

			int largeur, hauteur;
			
			//Pour les rotations de 90° et -90°, inverser largeur et hauteur
			if (Math.abs(angle - 90) < 0.01 || Math.abs(angle + 90) < 0.01 || 
			    Math.abs(angle - 270) < 0.01 || Math.abs(angle + 270) < 0.01) 
			{
				largeur = origHeight;
				hauteur = origWidth;
			} // Pour les rotations de 180° et -180° (et 0°), garder les mêmes dimensions
			else if (Math.abs(angle) < 0.01 || Math.abs(Math.abs(angle) - 180) < 0.01) 
			{
				largeur = origWidth;
				hauteur = origHeight;
			}// Pour les autres angles, calculer les dimensions nécessaires
			else 
			{
				double a = Math.toRadians(angle);
				double cosA = Math.abs(Math.cos(a));
				double sinA = Math.abs(Math.sin(a));
				largeur = (int) Math.ceil(origWidth * cosA + origHeight * sinA);
				hauteur = (int) Math.ceil(origWidth * sinA + origHeight * cosA);
			}

			BufferedImage nouvelleImage = new BufferedImage(largeur, hauteur, BufferedImage.TYPE_INT_ARGB);

			double a = Math.toRadians(angle);
			double cosAngle = Math.cos(a);
			double sinAngle = Math.sin(a);

			// Centres de l'image source et destination
			int cxSrc  = origWidth  / 2;
			int cySrc  = origHeight / 2;
			int cxDest = largeur    / 2;
			int cyDest = hauteur    / 2;

			// Appliquer la rotation
			for (int i2 = 0; i2 < largeur; i2++) {
				for (int j2 = 0; j2 < hauteur; j2++) {

					double xd = i2 - cxDest;
					double yd = j2 - cyDest;

					// Rotation inverse pour retrouver le pixel source
					double xs =  xd * cosAngle + yd * sinAngle;
					double ys = -xd * sinAngle + yd * cosAngle;

					int i = (int) Math.round(xs + cxSrc);
					int j = (int) Math.round(ys + cySrc);

					// Vérifier si le pixel source est dans les limites de l'image originale
					if (i >= 0 && i < origWidth && j >= 0 && j < origHeight) {
						nouvelleImage.setRGB(i2, j2, orig.getRGB(i, j));
					} else {
						nouvelleImage.setRGB(i2, j2, 0x00000000); // Transparent
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
	 * @param src
	 */
	public void mirrorHorizontal(BufferedImage src)
	{
		if (src == null) return;
		
		int width  = src.getWidth();
		int height = src.getHeight();
		
		for (int y = 0; y < height; y++) 
		{
			for (int x = 0; x < width / 2; x++) 
			{
				int mirrorX = width - 1 - x;
				
				int leftPixelColor  = src.getRGB(x, y);
				int rightPixelColor = src.getRGB(mirrorX, y);
				
				src.setRGB(x, y, rightPixelColor);     				
				src.setRGB(mirrorX, y, leftPixelColor);
			}
		}
	}

	/**
	 * Miroir horizontal de l'image
	 * @param src
	 */
	public void mirrorVertical(BufferedImage src)
	{
		if (src == null) return;
		
		int width  = src.getWidth();
		int height = src.getHeight();
		
		for (int y = 0; y < height / 2; y++) 
		{
			for (int x = 0; x < width; x++) 
			{
				int mirrorY = height - 1 - y;
				
				int topPixelColor    = src.getRGB(x, y);
				int bottomPixelColor = src.getRGB(x, mirrorY);
				
				src.setRGB(x, y, bottomPixelColor);     				
				src.setRGB(x, mirrorY, topPixelColor);
			}
		}
	}

	public void redimensionner(BufferedImage src, int hauteur, int largeur) {

	}
}
