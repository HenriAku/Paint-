package Main;

import java.awt.image.BufferedImage;
import java.util.List;
import java.awt.Dimension;
import java.awt.Toolkit;

import Model.*;
import View.*;

/**
 * Controller principale de l'application.
 */
public class Controller 
{
	private FramePrincipale  framePrincipale;

	private ImageLoader      imageLoader;
	private ImageTransformer imageTransformer;
	private BucketTool       bucketTool;
	private TextTool         textTool;
	
	private int width;
	private int height;

	private ToolType currentTool;

	private String chemin;
	
	/**
	 * Constructeur du Controller.
	 */
	public Controller()
	{
		this.currentTool = ToolType.DEFAULT;
		this.chemin = null;

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.width  = (int) screenSize.getWidth ();
		this.height = (int) screenSize.getHeight();
		
		this.framePrincipale  = new FramePrincipale(this);
		this.imageLoader      = new ImageLoader(this.width, this.height);
		
		this.imageTransformer = new ImageTransformer();
		this.bucketTool       = new BucketTool();
		//this.textTool         = new TextTool();
	}

	/**
	 * Obtient la largeur de l'écran.
	 * @return 	La largeur de l'écran.
	 */
	public int getWidth() { return this.width ; }

	/**
	 * Obtient la hauteur de l'écran.
	 * @return La hauteur de l'écran.
	 */
	public int getHeight() { return this.height; }

	/**
	 * Obtient l'outil courant.
	 * @return L'outil courant.
	 */
	public ToolType getCurrentTool() { return this.currentTool; }
	
	/**
	 * Définit l'outil courant.
	 * @param tool L'outil à définir comme courant.
	 */
	public void setCurrentTool( ToolType tool ){ this.currentTool = tool; }

	/**
	 * Obtient le chemin de l'image courante.
	 * @return Le chemin de l'image courante.
	 */
	public String getChemin () { return this.chemin; }

	/**
	 * Définit le chemin de l'image courante.
	 * @param path Le chemin de l'image courante.
	 */
	public void   setChemin ( String path ) { this.chemin = path; }

	/**
	 * Sauvegarde l'image courante dans le chemin spécifié.
	 * @param filePath Le chemin du fichier où sauvegarder l'image.
	 */
	public void sauvegarder ( String filePath ) { this.imageLoader.sauvegarder(filePath); }

	/**
	 * Charge une image depuis le chemin spécifié.
	 * @param filePath Le chemin du fichier à charger.
	 */
	public void addImage ( String filePath ) { this.imageLoader.loadImage(filePath); }

	/**
	 * Ajoute l'image courante à l'historique des modifications.
	 */
	public void addImageHistorique () { this.imageLoader.addImageHistorique(); }

	/**
	 * Obtient l'image courante sous forme de BufferedImage.
	 * @return L'image courante.
	 */
	public BufferedImage getBufferedImage () { return this.imageLoader.getBufferedImage(); }

	/**
	 * Obtient les images de l'historique des modifications.
	 * @return La liste des images de l'historique.
	 */
	public List<BufferedImage> getImagesHistoriqueArriere   (){return this.imageLoader.getImagesHistoriqueArriere();   }

	/**
	 * Obtient la dernière image de l'historique des modifications.
	 * @return La dernière image de l'historique.
	 */
	public BufferedImage getLastImageHistoriqueArriere(){ return this.imageLoader.getLastImageHistoriqueArriere(); }

	/**
	 * Obtient les images de l'historique des modifications avant l'état actuel.
	 * @return La liste des images de l'historique avant l'état actuel.
	 */
	public List<BufferedImage> getImagesHistoriqueAvant     (){ return this.imageLoader.getImagesHistoriqueAvant(); }

	/**
	 * Obtient la prochaine image de l'historique des modifications avant l'état actuel.
	 * @return La prochaine image de l'historique avant l'état actuel.
	 */
	public BufferedImage getNextImageHistoriqueAvant  (){ return this.imageLoader.getNextImageHistoriqueAvant(); }

	/**
	 * Remplit une zone de l'image avec une nouvelle couleur en utilisant l'outil seau.
	 * @param img L'image à modifier.
	 * @param x La coordonnée x du pixel à modifier.
	 * @param y La coordonnée y du pixel à modifier.
	 * @param newColorRGB La nouvelle couleur en format RGB.
	 * @param tolerance La tolérance pour la correspondance des couleurs.
	 */
	public void peindre( BufferedImage img, int x, int y, int newColorRGB, int tolerance )
	{
		this.bucketTool.peindre( img, x, y, newColorRGB, tolerance );
		this.imageLoader.setOriginalImage( img );
	}

	/**
	 * Fait une rotation de l'image courante d'un angle spécifié.
	 * @param angle L'angle de rotation en degrés.
	 */
	public void rotation( double angle )
	{
		BufferedImage imageRotee = this.imageTransformer.rotation( this.imageLoader.getOriginalImage(), angle );
		this.imageLoader.setBufferedImage( imageRotee );
	}

	/**
	 * Fusionne une deuxième image par-dessus l'image courante à des coordonnées spécifiées.
	 * @param x La coordonnée x où fusionner l'image.
	 * @param y La coordonnée y où fusionner l'image.
	 */
	public void fusionner( int x, int y )
	{
		BufferedImage baseImg   = this.imageLoader.getBufferedImage ();
		this.imageTransformer.fusionner( baseImg, this.chemin, 0xFFFFFF, x, y );
		
		this.imageLoader.setOriginalImage( this.getBufferedImage() );
	}

	/**
	 * Ajuste le contraste de l'image courante.
	 * @param contrastLevel Le niveau de contraste à appliquer.
	 */
	public void adjustContrast( double contrastLevel )
	{
		this.imageTransformer.adjustContrast( this.getBufferedImage(), contrastLevel );
		this.imageLoader.setOriginalImage( this.getBufferedImage() );
		this.updateDessin();
	}

	/**
	 * Ajuste la luminosité de l'image courante. 
	 * @param brightnessLevel Le niveau de luminosité à appliquer.
	 */
	public void adjustBrightness( int brightnessLevel )
	{
		this.imageTransformer.adjustBrightness( this.getBufferedImage(), brightnessLevel );
		this.imageLoader.setOriginalImage( this.getBufferedImage() );
	}

	/**
	 * Ajuste la teinte de l'image donnée en appliquant des offsets aux canaux rouge, vert et bleu.
	 * @param img L'image à modifier.
	 * @param rOffset L'offset à appliquer au canal rouge.
	 * @param gOffset L'offset à appliquer au canal vert.
	 * @param bOffset L'offset à appliquer au canal bleu.
	 */
	public void adjustHue( BufferedImage img, int rOffset, int gOffset, int bOffset )
	{
		this.imageTransformer.adjustHue( img, rOffset, gOffset, bOffset );
		this.imageLoader.setOriginalImage( img );
	}

	/** 
	 * Miroir horizontal de l'image courante.
	 */
	public void mirrorHorizontal() 
	{
		this.imageTransformer.mirrorHorizontal( this.getBufferedImage() );
		this.imageLoader.setOriginalImage( this.getBufferedImage() );
	}

	/** 
	 * Miroir vertical de l'image courante.
	 */
	public void mirrorVertical() 
	{
		this.imageTransformer.mirrorVertical( this.getBufferedImage() );
		this.imageLoader.setOriginalImage( this.getBufferedImage() );
	}

	/**
	 * Redimensionne l'image courante aux nouvelles dimensions spécifiées.
	 * @param newWidth La nouvelle largeur de l'image.
	 * @param newHeight La nouvelle hauteur de l'image.
	 */
	public void redimensionner( int newWidth, int newHeight )
	{
		BufferedImage resizedImage = this.imageTransformer.redimensionner( this.getBufferedImage(), newHeight, newWidth);
		this.imageLoader.setBufferedImage( resizedImage );
		this.imageLoader.setOriginalImage( resizedImage );
	}

	/**
	 * Met à jour le dessin dans l'interface utilisateur.
	 */
	public void updateDessin() { this.framePrincipale.repaint(); }

	/**
	 * Ajoute les écouteurs de souris pour le dessin.
	 */
	public void addMouseDessin   () { this.framePrincipale.addMouseDessin(); }

	/**
	 * Retire les écouteurs de souris pour le dessin.
	 */
	public void removeMouseDessin() { this.framePrincipale.removeMouseDessin(); }

	/**
	 * Gère la sélection d'un outil et affiche le panneau de paramètres correspondant.
	 * @param toolName Le nom de l'outil sélectionné.
	 */
	public void toolSelected( String toolName )
	{
		this.framePrincipale.showParametrePanel( toolName );
	}

	/**
	 * Point d'entrée principal de l'application.
	 * @param args Les arguments de la ligne de commande.
	 */
	public static void main( String[] args )
	{
		new Controller();
	}
}