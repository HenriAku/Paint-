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

	//Envoi de l'image de fond, à mettre au dessus, le fichier de sortie, la couleur transparente à supprimer pendant la fusion, ainsi que la position de l'image à mettre au dessus 
    public void fusionner(BufferedImage fond, BufferedImage dessus, int couleurTransparente, int posX, int posY){

        try {
            //Vérification de la position de l'image à superposer
            if (dessus.getWidth() + posX > fond.getWidth() ||
                dessus.getHeight() + posY > fond.getHeight()) {
                System.out.println("Erreur : l'image à superposer dépasse les limites de l'image de fond.");
                return;
            }

            //Parcourir l'image pour envoyer la couleur de tout les pixels au fond si la couleur n'est pas transparente 
            for (int y = 0; y < dessus.getHeight(); y++) {
                for (int x = 0; x < dessus.getWidth(); x++) {
                    int pixel = dessus.getRGB(x, y) & 0xFFFFFF;
                    if (pixel != couleurTransparente) {
                        fond.setRGB(posX + x, posY + y, dessus.getRGB(x, y));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

	public void rotation(BufferedImage src, double angle)
	{	
		try {
			
			int largeur = src.getWidth();
			int hauteur = src.getHeight();

			BufferedImage tmp = new BufferedImage(largeur,hauteur,BufferedImage.TYPE_INT_ARGB);

			double a = Math.toRadians(angle);
			double cosA = Math.cos(a);
			double sinA = Math.sin(a);

			int cx = (largeur)/2;
			int cy = (hauteur)/2;

			for (int i2 = 0; i2 < largeur; i2++) {
				for (int j2 = 0; j2 < hauteur; j2++) {

					double xd = i2 - cx;
					double yd = j2 - cy;

					// rotation inverse
					double xs =  xd * cosA + yd * sinA;
					double ys = -xd * sinA + yd * cosA;

					int i = (int) Math.round(xs + cx);
					int j = (int) Math.round(ys + cy);

					if (i >= 0 && i < largeur && j >= 0 && j < hauteur) {
						tmp.setRGB(i2, j2, src.getRGB(i, j));
					} else {
						tmp.setRGB(i2, j2, 0x00000000);
					}
				}
			}

			for (int i2 = 0; i2 < largeur; i2++) {
				for (int j2 = 0; j2 < hauteur; j2++) {
					src.setRGB(i2, j2, tmp.getRGB(i2, j2));
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
