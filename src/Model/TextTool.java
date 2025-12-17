package Model;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/** Classe utilitaire pour le traitement de texte */
public class TextTool
{
    private BufferedImage texteImage;
	private String cheminCharge;

	public TextTool()
	{
		this.texteImage   = null;
		this.cheminCharge = null;
	}

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
}