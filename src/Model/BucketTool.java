package Model;

import java.util.Queue;
import java.util.LinkedList;

import java.awt.image.BufferedImage;

/**
 * Classe utilitaire pour l'outil "Pot de peinture"
 */
public class BucketTool
{
	/**
	 * Constructeur par défaut
	 */
	public BucketTool() {}
	
	/**
	 * Calcule la distance entre deux couleurs exprimées en entiers
	 * @param coul1 Première couleur
	 * @param coul2 Deuxième couleur
	 * @return Distance entre les deux couleurs
	 */
	private static double distance( int coul1, int coul2 )
	{
		int r1 = ( coul1 >> 16 ) & 0xFF;
		int g1 = ( coul1 >> 8  ) & 0xFF;
		int b1 =   coul1         & 0xFF;

		int r2 = ( coul2 >> 16 ) & 0xFF;
		int g2 = ( coul2 >> 8  ) & 0xFF;
		int b2 =   coul2         & 0xFF;

		return Math.sqrt( Math.pow( r1 - r2, 2 ) + Math.pow( g1 - g2, 2 ) + Math.pow( b1 - b2, 2 ) );
	}


	/**
	 * Peint une zone contiguë de l'image à partir du point (x, y) avec la nouvelle couleur,
	 * @param imageTarget Image cible à modifier
	 * @param x Coordonnée x du pixel de départ
	 * @param y Coordonnée y du pixel de départ
	 * @param newColorRGB Nouvelle couleur en format RGB
	 * @param tolerance Tolérance de couleur pour la sélection des pixels à peindre
	 */
	public void peindre( BufferedImage imageTarget, int x, int y, int newColorRGB, int tolerance )
	{
		int width  = imageTarget.getWidth();
		int height = imageTarget.getHeight();
		
		boolean[][] visited = new boolean[width][height];
		
		Queue<Point> file = new LinkedList<Point>();
		int colorOrig = imageTarget.getRGB( x, y ) & 0xFFFFFF;
		
		// Si la couleur de départ est déjà la couleur cible, ne rien faire
		if ( ( newColorRGB & 0xFFFFFF ) == colorOrig ) {
			return;
		}
		
		file.add( new Point( x, y ) );
		visited[x][y] = true;
		
		while ( !file.isEmpty() )
		{
			Point p = file.remove();
			
			// Vérifier les limites et la couleur
			if ( p.x() >= 0 && p.x() < width && p.y() >= 0 && p.y() < height &&
				 distance( colorOrig, imageTarget.getRGB( p.x(), p.y() ) & 0xFFFFFF ) < tolerance )
			{
				imageTarget.setRGB( p.x(), p.y(), newColorRGB );
				
				// Ajouter les voisins seulement s'ils n'ont pas été visités
				if ( p.x() + 1 < width && !visited[p.x() + 1][p.y()] )
				{
					file.add( new Point( p.x() + 1, p.y() ) );
					visited[p.x() + 1][p.y()] = true;
				}
				if ( p.x() - 1 >= 0 && !visited[p.x() - 1][p.y()] )
				{
					file.add( new Point( p.x() - 1, p.y() ) );
					visited[p.x() - 1][p.y()] = true;
				}
				if ( p.y() + 1 < height && !visited[p.x()][p.y() + 1] )
				{
					file.add( new Point( p.x(), p.y() + 1 ) );
					visited[p.x()][p.y() + 1] = true;
				}
				if ( p.y() - 1 >= 0 && !visited[p.x()][p.y() - 1] )
				{
					file.add( new Point( p.x(), p.y() - 1 ) );
					visited[p.x()][p.y() - 1] = true;
				}
			}
		}
	}
}
