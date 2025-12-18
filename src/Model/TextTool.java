package Model;

import java.util.ArrayList;
import java.util.List;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.image.BufferedImage;

import java.io.File;

import javax.imageio.ImageIO;

/** Classe utilitaire pour le traitement de texte */
public class TextTool
{
	private BufferedImage texteImage;
	private String cheminCharge;

	/**
	 * Constructeur du TextTool
	 */
	public TextTool()
	{
		this.texteImage   = null;
		this.cheminCharge = null;
	}

	/**
	 * Transforme le texte en forme vectorielle et applique une texture (créée via l'image texture) pour remplir les formes du texte sur l'image cible.
	 * @param cible L'image à modifier.
	 * @param cheminTexte Le chemin pour initialiser le BufferedReader.
	 * @param texte Le texte à designer.
	 * @param x Coordonnée x du texte
	 * @param y Coordonnée y du texte 
	 */
	public void appliquer( BufferedImage cible, String cheminTexture, String texte, int x, int y )
	{
		if ( cible == null || texte == null || texte.isEmpty() ) return;

		try {
			BufferedImage texture = ImageIO.read( new File(cheminTexture) );
			if ( texture == null ) return;

			Graphics2D g2 = cible.createGraphics();

			g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

			Font font = new Font( "Arial", Font.BOLD, 72 );
			g2.setFont( font );

			FontRenderContext frc = g2.getFontRenderContext();
			GlyphVector gv = font.createGlyphVector( frc, texte );
			Shape textShape = gv.getOutline( x, y );

			Rectangle2D rect = new Rectangle2D.Double( 0, 0, texture.getWidth(), texture.getHeight() );

			TexturePaint texturePaint = new TexturePaint( texture, rect );
			g2.setPaint( texturePaint );

			g2.fill( textShape );

			g2.dispose();

		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}


	/**
	 * Récupérer les textures dans les dossiers prévus pour
	 * @return Tableau de string avec le nom des textures
	 */
	public String[] getTextureFiles() 
	{
		File textureDir = new File( "ressources/textures" ); 

		if ( !textureDir.exists() || !textureDir.isDirectory() ) 
		{
			System.err.println( "Erreur: Le dossier 'textures/' est introuvable ou n'est pas un répertoire." );
			System.err.println( "Chemin cherché: " + textureDir.getAbsolutePath() );
			return new String[] { "[Dossier textures/ introuvable]" }; 
		}

		File[] files = textureDir.listFiles();
		List<String> textureNames = new ArrayList<>();

		if ( files != null ) 
		{
			for ( File file : files ) 
			{
				// Filtrer les fichiers image courants
				if ( file.isFile() && ( file.getName().toLowerCase().endsWith( ".png" ) ) ) 
				{
					textureNames.add( file.getName() );
				}
			}
		}

		if ( textureNames.isEmpty() ) 
		{
			textureNames.add( "Aucune texture trouvée" );
		}

		return textureNames.toArray( new String[0] );
	}
}