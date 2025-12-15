package Model;

import java.util.Queue;
import java.util.LinkedList;
import java.awt.image.BufferedImage;

// Implémentation de la logique pour l'outil de pot de peinture
public class BucketTool {

	// Méthode pour calculer la distance entre deux couleurs
	private static double distance(int coul1, int coul2)
	{
		int r1 = (coul1 >> 16) & 0xFF;
		int g1 = (coul1 >> 8) & 0xFF;
		int b1 = coul1 & 0xFF;

		int r2 = (coul2 >> 16) & 0xFF;
		int g2 = (coul2 >> 8) & 0xFF;
		int b2 = coul2 & 0xFF;

		return Math.sqrt(Math.pow(r1 - r2, 2) + Math.pow(g1 - g2, 2) + Math.pow(b1 - b2, 2));
	}

	// Méthode pour remplir une zone avec une nouvelle couleur
	public void peindre(BufferedImage imageTarget, int x, int y, int newColorRGB, int tolerance)
	{
		Queue<Point> file = new LinkedList<Point>();
		int colorOrig;

		colorOrig = imageTarget.getRGB( x, y ) & 0xFFFFFF;

		file.add( new Point(x,y) );

		while( !file.isEmpty() )
		{
			Point p = file.remove();

			if ( p.x() >= 0 && p.x() < imageTarget.getWidth() && p.y() >= 0 && p.y() < imageTarget.getHeight() &&
				 distance(colorOrig, imageTarget.getRGB(p.x(), p.y())) < tolerance ) {
				imageTarget.setRGB( p.x(), p.y(), newColorRGB );

				file.add( new Point( p.x() + 1, p.y() ) );
				file.add( new Point( p.x() - 1, p.y() ) );
				file.add( new Point( p.x(), p.y() + 1 ) );
				file.add( new Point( p.x(), p.y() - 1 ) );
			}
		}
	}
}
