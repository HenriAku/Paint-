package Model;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Fusionner
{
    public Fusionner(BufferedImage fond, BufferedImage dessus, String nomFichierSortie, int couleurTransparente, int posX, int posY){

        try {

            if (dessus.getWidth() + posX > fond.getWidth() ||
                dessus.getHeight() + posY > fond.getHeight()) {
                System.out.println("Erreur : l'image à superposer dépasse les limites de l'image de fond.");
                return;
            }

            for (int y = 0; y < dessus.getHeight(); y++) {
                for (int x = 0; x < dessus.getWidth(); x++) {
                    int pixel = dessus.getRGB(x, y) & 0xFFFFFF;
                    if (pixel != couleurTransparente) {
                        fond.setRGB(posX + x, posY + y, dessus.getRGB(x, y));
                    }
                }
            }

            ImageIO.write(fond, "png", new File(nomFichierSortie));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}