package Model;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Rotation {
	public Rotation(String entree, String sortie, double angle)
	{	
		try {
			
			BufferedImage src = ImageIO.read(new File(entree));
			int largeur = src.getWidth();
			int hauteur = src.getHeight();

			BufferedImage dest = new BufferedImage(largeur, hauteur, BufferedImage.TYPE_INT_ARGB);

			double a = Math.toRadians(angle);
			double cosA = Math.cos(a);
			double sinA = Math.sin(a);

			int i0 = largeur/2;
			int j0 = hauteur/2;

			for (int i = 0; i < largeur; i++) {
				for (int j = 0; j < hauteur; j++) {
					double x = i-i0;
					double y = j-j0;

					double x2 = x * cosA + y * sinA;
					double y2 = -x * sinA + y * cosA;

					int i2 = (int) Math.round(x2+i0);
					int j2 = (int) Math.round(y2+j0);

					if (i2 >= 0 && i2 < largeur && j2 >= 0 && j2 < hauteur) {
						dest.setRGB(i2, j2, src.getRGB(i, j));
					}
				}
			}
			ImageIO.write(dest,"png", new File(sortie));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
