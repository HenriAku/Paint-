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
	 * @param x Coordonnée x du texte
	 * @param y Coordonnée y du texte 
	 */
	public void appliquer(BufferedImage cible, String cheminTexte, int x, int y) 
	{
		try {
            this.texteImage = ImageIO.read(new File(cheminTexte));
			this.cheminCharge = cheminTexte;
        } catch (Exception e) {
            System.out.println("Erreur chargement image texte");
        }

        if (texteImage == null || cible == null) return;

        int w = texteImage.getWidth();
        int h = texteImage.getHeight();

        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {

                int px = texteImage.getRGB(i, j);

                // ignorer pixels transparents
                if ((px >>> 24) == 0) continue;

                int dx = x + i;
                int dy = y + j;

                if (dx >= 0 && dx < cible.getWidth()
                 && dy >= 0 && dy < cible.getHeight()) {

                    cible.setRGB(dx, dy, px);
                }
            }
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