package Main;

import Model.*;
import View.*;

import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;

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
	private int    pipetteColorRGB;

	private Color currentBucketColor;
	private int   currentBucketTolerance;

	private String currentTextContent;
	private String currentTextTexture;

	private Color currentColorSuperposer;

	private Color backgroundColor = new Color( 200, 200, 200, 255 );

	/**
	 * Constructeur du Controller.
	 */
	public Controller()
	{
		this.currentTool = ToolType.DEFAULT;

		this.chemin          = null    ;
		this.pipetteColorRGB = 0x000000;

		this.currentBucketColor	    = Color.WHITE;
		this.currentBucketTolerance = 30         ;

		this.currentColorSuperposer = Color.WHITE;

		this.currentTextContent = "Vide";
		this.currentTextTexture = "feu.png";

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		this.width  = (int) screenSize.getWidth ();
		this.height = (int) screenSize.getHeight();

		this.imageLoader      = new ImageLoader( this.width, this.height );
		
		this.imageTransformer = new ImageTransformer();
		this.bucketTool       = new BucketTool();
		this.textTool         = new TextTool();

		this.framePrincipale  = new FramePrincipale( this );
	}

	/**
	 * Obtient la couleur courante du superposeur.
	 * @return La couleur courante du superposeur.
	 */
	public Color getCurrentColorSuperposer() { return this.currentColorSuperposer; }

	/**
	 * Définit la couleur courante du superposeur.
	 * @param color La couleur à définir.
	 */
	public void setCurrentColorSuperposer( Color color ) { this.currentColorSuperposer = color; }

	/**
	 * Obtient la couleur de fond actuelle.
	 * @return La couleur de fond actuelle.
	 */
	public Color getBackgroundColor() { return this.backgroundColor; }

	/**
	 * Obtient la couleur courante du pot de peinture.
	 * @param color La couleur à définir.
	 */
	public void setCurrentBucketColor( Color color ) { this.currentBucketColor = color; }

	/**
	 * Obtient la couleur courante du pot de peinture.
	 * @param tolerance La tolérance à définir.
	 */
	public void setCurrentBucketTolerance( int tolerance ) { this.currentBucketTolerance = tolerance; }

	/**
	 * Obtient la couleur courante du pot de peinture.
	 * @return La couleur courante du pot de peinture.
	 */
	public Color getCurrentBucketColor() { return this.currentBucketColor; }

	/**
	 * Obtient la tolérance courante du pot de peinture.
	 * @return La tolérance courante du pot de peinture.
	 */
	public int getCurrentBucketTolerance() { return this.currentBucketTolerance; }

	/**
	 * Obtient le contenu du texte courant de l'éditeur de texte.
	 * @param texte Le texte à définir.
	 */
	public void setCurrentTextContent( String texte ) { this.currentTextContent = texte; }

	/**
	 * Obtient la texture courante de l'éditeur de texte.
	 * @param texture La texture à définir.
	 */
	public void setCurrentTextTexture( String texture ) { this.currentTextTexture = texture; }

	/**
	 * Obtient le contenu du texte courant de l'éditeur de texte.
	 * @return Le texte à définir.
	 */
	public String getCurrentTextContent() { return this.currentTextContent; }

	/**
	 * Obtient la texture courante de l'éditeur de texte.
	 * @return La texture à définir.
	 */
	public String getCurrentTextTexture() { return this.currentTextTexture; }

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
	public void setCurrentTool( ToolType tool )
	{
		this.currentTool = tool;
		this.imageLoader.addImageHistorique();
	}

	/**
	 * Obtient le chemin de l'image courante.
	 * @return Le chemin de l'image courante.
	 */
	public String getChemin () { return this.chemin; }

	/**
	 * Définit le chemin de l'image courante.
	 * @param path Le chemin de l'image courante.
	 */
	public void setChemin ( String path ) { this.chemin = path; }

	/**
	 * Obtient la couleur RGB sélectionnée par la pipette.
	 * @return La couleur RGB sélectionnée par la pipette.
	 */
	public int getPipetteColorRGB () { return this.pipetteColorRGB; }

	/**
	 * Définit la couleur RGB sélectionnée par la pipette.
	 * @param colorRGB La couleur RGB sélectionnée par la pipette.
	 */
	public void setPipetteColorRGB ( int x, int y ) { this.pipetteColorRGB = this.imageLoader.getBufferedImage().getRGB(x, y); }

	/**
	 * Sauvegarde l'image courante dans le chemin spécifié.
	 * @param filePath Le chemin du fichier où sauvegarder l'image.
	 */
	public void sauvegarder ( String filePath ) { this.imageLoader.sauvegarder(filePath); }

	/**
	 * Charge une image depuis le chemin spécifié.
	 * @param filePath Le chemin du fichier à charger.
	 */
	public void addImage ( String filePath ) 
	{ 
		this.imageLoader.loadImage(filePath); 
		this.updateDessin();
	}

	/**
	 * Obtient l'image courante sous forme de BufferedImage.
	 * @return L'image courante.
	 */
	public BufferedImage getBufferedImage (){ return this.imageLoader.getBufferedImage(); }

	/**
	 * Obtient la dernière image de l'historique des modifications.
	 * @return La dernière image de l'historique.
	 */
	public void getLastImageHistoriqueArriere ()
	{
		this.imageLoader.getLastImageHistoriqueArriere(); 
		this.updateDessin();
	}

	/**
	 * Obtient la prochaine image de l'historique des modifications avant l'état actuel.
	 * @return La prochaine image de l'historique avant l'état actuel.
	 */
	public void getNextImageHistoriqueAvant ()
	{ 
		this.imageLoader.getNextImageHistoriqueAvant(); 
		this.updateDessin();
	}

	/**
	 * Remplit une zone de l'image avec une nouvelle couleur en utilisant l'outil seau.
	 * @param img L'image à modifier.
	 * @param x La coordonnée x du pixel à modifier.
	 * @param y La coordonnée y du pixel à modifier.
	 * @param newColorRGB La nouvelle couleur en format RGB.
	 * @param tolerance La tolérance pour la correspondance des couleurs.
	 */
	public void peindre( int x, int y )
	{
		this.bucketTool .peindre           ( this.getBufferedImage(), x, y, this.currentBucketColor.getRGB(), this.currentBucketTolerance );
		this.imageLoader.setImageAvantModif( this.getBufferedImage() );
		this.updateDessin();

		this.bucketTool .peindre( this.imageLoader.getImageOriginale(), x, y, this.currentBucketColor.getRGB(), this.currentBucketTolerance );
	}

	/**
	 * Fait une rotation de l'image courante d'un angle spécifié.
	 * @param angle L'angle de rotation en degrés.
	 */
	public void rotation( double angle )
	{
		BufferedImage imageRotate = this.imageTransformer.rotation( this.imageLoader.getImageOriginale(), angle );

		this.imageLoader.setBufferedImage( imageRotate );
		this.updateDessin();

		BufferedImage copie      = this.imageTransformer.rotation( this.imageLoader.getImageOriginale (), angle );
		this.imageLoader.setOriginalImage  ( copie      );
	}

	/**
	 * Fusionne une deuxième image par-dessus l'image courante à des coordonnées spécifiées.
	 * @param x La coordonnée x où fusionner l'image.
	 * @param y La coordonnée y où fusionner l'image.
	 */
	public void superposer( int x, int y )
	{
		BufferedImage baseImg   = this.imageLoader.getBufferedImage ();

		this.imageTransformer.superposer        ( baseImg, this.chemin, this.currentColorSuperposer.getRGB() & 0xFFFFFF, x, y );
		this.imageLoader     .setImageAvantModif( this.getBufferedImage() );
		this.updateDessin();

		this.imageTransformer.superposer( this.imageLoader.getImageOriginale (), this.chemin, this.currentColorSuperposer.getRGB() & 0xFFFFFF, x, y );
	}

	/**
	 * Fusionne une image depuis le chemin spécifié avec l'image courante en utilisant une zone de fusion définie.
	 * @param filePath
	 * @param bound
	 */
	public void fusion( String filePath, int bound )
	{
		BufferedImage fusedImage = this.imageTransformer.fusion( this.getBufferedImage(), filePath, bound );

		this.imageLoader     .setImageAvantModif ( fusedImage );
		this.imageLoader	 .setBufferedImage   ( fusedImage );
		this.updateDessin();

		BufferedImage fusedOriginalImage = this.imageTransformer.fusion( this.imageLoader.getImageOriginale(), filePath, bound );
		this.imageLoader.setOriginalImage ( fusedOriginalImage );
	}

	/**
	 * Ajuste le contraste de l'image courante.
	 * @param contrastLevel Le niveau de contraste à appliquer.
	 */
	public void adjustContrast(double contrastLevel) 
	{
		BufferedImage source = this.imageLoader.getImageOriginale();
		BufferedImage nouvelleImage = this.imageLoader.copierImage(source);
		
		this.imageTransformer.adjustContrast(nouvelleImage, contrastLevel);
		
		this.imageLoader.setBufferedImage  (nouvelleImage);    
		this.imageLoader.setImageAvantModif(nouvelleImage);
		
		this.updateDessin();
	}

	/**
	 * Ajuste la luminosité de l'image courante. 
	 * @param brightnessLevel Le niveau de luminosité à appliquer.
	 */
	public void adjustBrightness( int brightnessLevel )
	{
		BufferedImage source = this.imageLoader.getImageOriginale();
		BufferedImage nouvelleImage = this.imageLoader.copierImage(source);

		this.imageTransformer.adjustBrightness  ( nouvelleImage, brightnessLevel );

		this.imageLoader.setImageAvantModif( nouvelleImage );
		this.imageLoader.setBufferedImage  ( nouvelleImage ); 
		this.updateDessin();
	}

	/**
	 * Ajuste la teinte de l'image donnée en appliquant des offsets aux canaux rouge, vert et bleu.
	 * @param img L'image à modifier.
	 * @param rOffset L'offset à appliquer au canal rouge.
	 * @param gOffset L'offset à appliquer au canal vert.
	 * @param bOffset L'offset à appliquer au canal bleu.
	 */
	public void adjustHue( int rOffset, int gOffset, int bOffset )
	{
		BufferedImage source = this.imageLoader.getImageOriginale();
		BufferedImage nouvelleImage = this.imageLoader.copierImage(source);

		this.imageTransformer.adjustHue ( nouvelleImage, rOffset, gOffset, bOffset );

		this.imageLoader.setImageAvantModif( nouvelleImage );
		this.imageLoader.setBufferedImage  ( nouvelleImage );
		this.updateDessin();
	}

	/** 
	 * Miroir horizontal de l'image courante.
	 */
	public void mirrorHorizontal() 
	{
		this.imageTransformer.mirrorHorizontal  ( this.getBufferedImage() );
		this.imageLoader     .setImageAvantModif( this.getBufferedImage() );
		this.updateDessin();

		this.imageTransformer.mirrorHorizontal  ( this.imageLoader.getImageOriginale() );
	}

	/** 
	 * Miroir vertical de l'image courante.
	 */
	public void mirrorVertical() 
	{
		this.imageTransformer.mirrorVertical    ( this.getBufferedImage() );
		this.imageLoader     .setImageAvantModif( this.getBufferedImage() );
		this.updateDessin();

		this.imageTransformer.mirrorVertical    ( this.imageLoader.getImageOriginale() );
	}

	/**
	 * Redimensionne l'image courante aux nouvelles dimensions spécifiées.
	 * @param newWidth La nouvelle largeur de l'image.
	 * @param newHeight La nouvelle hauteur de l'image.
	 */
	public void redimensionner( int newWidth, int newHeight )
	{
		BufferedImage resizedImage = this.imageTransformer.redimensionner( this.getBufferedImage(), newHeight, newWidth );

		this.imageLoader.setBufferedImage  ( resizedImage );
		this.imageLoader.setImageAvantModif( resizedImage );
		this.updateDessin();

		BufferedImage resizedOriginalImage = this.imageTransformer.redimensionner( this.imageLoader.getImageOriginale(), newHeight, newWidth );
		this.imageLoader.setOriginalImage  ( resizedOriginalImage );
	}

	public void antiAliasing()
	{
		this.imageTransformer.antiAliasing       ( this.getBufferedImage() );
		this.imageLoader     .setImageAvantModif ( this.getBufferedImage() );
		this.updateDessin();

		this.imageTransformer.antiAliasing       ( this.imageLoader.getImageOriginale() );
	}

	/**
	 * Applique la texture au texte courant en créant une image
	 * @param x Coordonnée x
	 * @param y Coordonnée y
	 */
	public void appliquerText( int x, int y )
	{
		String chemin = "ressources/textures/" + this.currentTextTexture;
		this.textTool.appliquer( this.getBufferedImage(), chemin, this.currentTextContent, x, y );
		this.imageLoader.setImageAvantModif( this.getBufferedImage() );
		this.updateDessin();

		this.textTool.appliquer( this.imageLoader.getImageOriginale(), chemin, this.currentTextContent, x, y );
	}

	/**
	 * Prévient qu'une texture a été ajoutée pour pouvoir rafraichir le JComboBox 
	 */
	public void ajoutTextures() { this.framePrincipale.refreshTextures(); }


	/**
	 * Obtient les fichiers de texture disponibles pour l'outil texte.
	 */
	public String[] getTextureFiles() { return this.textTool.getTextureFiles(); }

	/**
	 * Met à jour le dessin dans l'interface utilisateur.
	 */
	public void updateDessin() { this.framePrincipale.repaint(); }

	/**
	 * Met à jour le panneau pipette dans l'interface utilisateur.
	 */
	public void updateColorDisplay(){ this.framePrincipale.updateColorDisplay(); }

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
	public void toolSelected( String toolName ) { this.framePrincipale.showParametrePanel( toolName ); }

	/**
	 * Point d'entrée principal de l'application.
	 * @param args Les arguments de la ligne de commande.
	 */
	public static void main( String[] args )
	{
		new Controller();
	}
}