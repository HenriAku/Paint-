package View;

import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;

import Main.Controller;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class PanelMenu extends JPanel implements ActionListener
{
	private Controller controller;
	
	private JMenuItem menuOuvrirImage  ;
	private JMenuItem menuSauvegarder  ;
	private JMenuItem menuRetourArriere;
	private JMenuItem menuRetourAvant  ;
	private JMenuItem menuAnnulerAction;
	private JMenuItem menuAntiAliasing ;
	private JMenuItem menuTexture      ;

	/**
	 * Constructeur du PanelMenu.
	 * @param ctrl pemet de contrôler les actions du menu.
	 */
	public PanelMenu(Controller ctrl)
	{
		//Initialisation des attributs
		this.controller = ctrl;

		this.setLayout(new BorderLayout());

		JMenuBar menuBar = new JMenuBar();
		menuBar.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		this.menuOuvrirImage   = new JMenuItem("Ouvrir Image"   );
		this.menuSauvegarder   = new JMenuItem("Sauvegarder"    );
		this.menuRetourArriere = new JMenuItem("Retour Arrière" );
		this.menuRetourAvant   = new JMenuItem("Retour Avant"   );
		this.menuAnnulerAction = new JMenuItem("Annuler Action" );
		this.menuAntiAliasing  = new JMenuItem("Anti-Aliasing"  );
		this.menuTexture       = new JMenuItem("Ajouter Texture");

		//Ajout composants au JPanel
		this.add(menuBar);

		menuBar.add(this.menuOuvrirImage  );
		menuBar.add(this.menuSauvegarder  );
		menuBar.add(this.menuRetourArriere);
		menuBar.add(this.menuRetourAvant  );
		menuBar.add(this.menuAnnulerAction);
		menuBar.add(this.menuAntiAliasing );
		menuBar.add(this.menuTexture      );

		//Activation des composants
		this.menuOuvrirImage  .addActionListener(this);
		this.menuSauvegarder  .addActionListener(this);
		this.menuRetourArriere.addActionListener(this);
		this.menuRetourAvant  .addActionListener(this);
		this.menuAnnulerAction.addActionListener(this);
		this.menuAntiAliasing .addActionListener(this);
		this.menuTexture      .addActionListener(this);
	}

	public void actionPerformed(java.awt.event.ActionEvent e)
	{
		if (e.getSource() == this.menuOuvrirImage)
		{
			String chemin = this.ouvrirFileChooser(0);
			if ( chemin != null )
				this.controller.addImage( chemin );
		}
		
		if (e.getSource() == this.menuSauvegarder)
		{
			String chemin = this.ouvrirFileChooser(1);
			if ( chemin != null )
				this.controller.sauvegarder(chemin);
		}
		
		if (e.getSource() == this.menuRetourArriere)
		{
			this.controller.getLastImageHistoriqueArriere();
		}
		
		if (e.getSource() == this.menuRetourAvant)
		{
			this.controller.getNextImageHistoriqueAvant();
		}

		if (e.getSource() == this.menuAnnulerAction)
		{
			this.controller.removeMouseDessin();
		}

		if (e.getSource() == this.menuAntiAliasing)
		{
			this.controller.antiAliasing();
		}

		if (e.getSource() == this.menuTexture)
		{
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Ajouter une texture");
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

			int result = fileChooser.showOpenDialog(this);

			if (result == JFileChooser.APPROVE_OPTION)
			{
				File selectedFile = fileChooser.getSelectedFile();

				File textureDir = new File("ressources/textures/");
				if (!textureDir.exists())
					textureDir.mkdirs();

				File destFile = new File(textureDir, selectedFile.getName());

				try (
					FileInputStream in  = new FileInputStream(selectedFile);
					FileOutputStream out = new FileOutputStream(destFile)
				) {
					byte[] buffer = new byte[4096];
					int bytesRead;

					while ((bytesRead = in.read(buffer)) != -1) {
						out.write(buffer, 0, bytesRead);
					}

					// this.controller.ajoutTextures();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}

	}

	/**
	 * Ouvre une boîte de dialogue pour choisir un fichier à ouvrir ou sauvegarder.
	 * @param binaire permet de différencier l'ouverture (0) et la sauvegarde (1).
	 * @return String chemin de l'imaege / dossier
	 */
	public String ouvrirFileChooser(int binaire) 
	{
		UIManager.put("FileChooser.cancelButtonText", "Annuler");
		UIManager.put("FileChooser.cancelButtonToolTipText", "Fermer la fenêtre");

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory( new java.io.File(".")  );
		fileChooser.setFileSelectionMode( JFileChooser.FILES_ONLY );

		if (binaire == 0) 
		{
			fileChooser.setDialogTitle("Ouvrir une image");
			fileChooser.setApproveButtonText("Ouvrir");
		} else 
		{
			fileChooser.setDialogTitle("Sauvegarder l'image");
			fileChooser.setApproveButtonText("Sauvegarder");
		}

		fileChooser.setAcceptAllFileFilterUsed( false );
		
		// Ajouter un filtre pour les fichiers PNG
		javax.swing.filechooser.FileNameExtensionFilter filter = 
			new javax.swing.filechooser.FileNameExtensionFilter("Images PNG (*.png)", "png");
		fileChooser.addChoosableFileFilter(filter);
		
		int result;
		if (binaire == 0)
			result = fileChooser.showOpenDialog( this );
		else
			result = fileChooser.showSaveDialog( this );
		
		if ( result == JFileChooser.APPROVE_OPTION ) {
			String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
			
			// Pour la sauvegarde, s'assurer que le fichier a l'extension .png
			if (binaire == 1 && !selectedFilePath.toLowerCase().endsWith(".png")) {
				selectedFilePath += ".png";
			}
			return selectedFilePath;
		}
		return null;
	}
}