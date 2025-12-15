package Model;

import java.awt.image.BufferedImage;
import java.awt.Color;

// Implémentation de la logique pour la transformation d'images
public class ImageTransformer
{
	// Méthode pour ajuster le contraste d'une couleur
	private static Color contraste(Color c, int contraste)
	{
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();

		r = (int)(r + contraste / 100.0 * (r - 127));
		g = (int)(g + contraste / 100.0 * (g - 127));
		b = (int)(b + contraste / 100.0 * (b - 127));

		// S'assurer que les valeurs restent dans la plage 0-255
		r = Math.max(0, Math.min(255, r));
		g = Math.max(0, Math.min(255, g));
		b = Math.max(0, Math.min(255, b));

		return new Color(r, g, b);	
	}

	// Méthode pour modifier le contraste d'une image
	public void adjustContrast(BufferedImage image, double contrastLevel)
	{
		int width = image.getWidth();
		int height = image.getHeight();

		// Parcourir chaque pixel de l'image
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Color originalColor = new Color(image.getRGB(x, y));
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

		r = r + luminosite;
		g = g + luminosite;
		b = b + luminosite;

		// S'assurer que les valeurs restent dans la plage 0-255
		r = Math.max(0, Math.min(255, r));
		g = Math.max(0, Math.min(255, g));
		b = Math.max(0, Math.min(255, b));

		return new Color(r, g, b);
	}

	// Méthode pour modifier la lumière de l'image
	public void adjustBrightness(BufferedImage image, int brightnessLevel)
	{
		int width = image.getWidth();
		int height = image.getHeight();

		// Parcourir chaque pixel de l'image
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Color originalColor = new Color(image.getRGB(x, y));
				Color newColor = luminosite(originalColor, brightnessLevel);
				image.setRGB(x, y, newColor.getRGB());
			}
		}
	}
}
