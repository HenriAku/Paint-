package Model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
	 * Applique l'image au texte
	 * @param cible L'image à modifier.
	 * @param cheminTexte Le chemin pour initialiser le BufferedReader.
     * @param texte Le texte à designer.
	 * @param x Coordonnée x du texte
	 * @param y Coordonnée y du texte 
	 */
	public void appliquer(BufferedImage cible,String cheminTexture,String cheminTexteImage, int x,int y)
    {
        try {
            BufferedImage texture = ImageIO.read(new File(cheminTexture));
            BufferedImage texte   = ImageIO.read(new File(cheminTexteImage));

            if (texture == null || texte == null || cible == null) return;

            int w = texte.getWidth();
            int h = texte.getHeight();

            for (int j = 0; j < h; j++) {
                for (int i = 0; i < w; i++) {

                    int maskPx = texte.getRGB(i, j);
                    int alpha  = (maskPx >>> 24);

                    // pixel hors lettre
                    if (alpha == 0) continue;

                    int dx = x + i;
                    int dy = y + j;

                    if (dx < 0 || dx >= cible.getWidth()
                    || dy < 0 || dy >= cible.getHeight()) continue;

                    // répétition de la texture
                    int tx = i % texture.getWidth();
                    int ty = j % texture.getHeight();

                    int texPx = texture.getRGB(tx, ty);

                    // conserver alpha du texte
                    int rgb = (alpha << 24) | (texPx & 0x00FFFFFF);

                    cible.setRGB(dx, dy, rgb);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


	/**
	 * Récupérer les textures dans les dossiers prévus pour
	 * @return Tableau de string avec le nom des textures
	 */
    public String[] getTextureFiles() 
    {
        File textureDir = new File("ressources/textures"); 

        if (!textureDir.exists() || !textureDir.isDirectory()) 
        {
            System.err.println("Erreur: Le dossier 'textures/' est introuvable ou n'est pas un répertoire.");
            System.err.println("Chemin cherché: " + textureDir.getAbsolutePath());
            return new String[]{"[Dossier textures/ introuvable]"}; 
        }

        File[] files = textureDir.listFiles();
        List<String> textureNames = new ArrayList<>();

        if (files != null) 
        {
            for (File file : files) 
            {
                // Filtrer les fichiers image courants
                if (file.isFile() && (file.getName().toLowerCase().endsWith(".png"))) 
                {
                    textureNames.add(file.getName());
                }
            }
        }

        if (textureNames.isEmpty()) 
        {
            textureNames.add("Aucune texture trouvée");
        }

        return textureNames.toArray(new String[0]);
    }
}