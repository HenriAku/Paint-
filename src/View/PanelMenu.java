package View;

import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;

import Main.Controller;
import java.awt.event.ActionListener;

public class PanelMenu extends JPanel implements ActionListener
{
	private Controller controller;
	
	private JMenuItem menuOuvrirImage  ;
	private JMenuItem menuSauvegarder  ;
	private JMenuItem menuRetourArriere;
	private JMenuItem menuRetourAvant  ;
	private JMenuItem menuAnnulerAction;

	/**
	 * Constructeur du PanelMenu.
	 * @param ctrl pemet de contrôler les actions du menu.
	 */
	public PanelMenu(Controller ctrl)
	{
		//Initialisation des attributs
		this.controller = ctrl;

		JMenuBar menuBar = new JMenuBar();

		this.menuOuvrirImage   = new JMenuItem("Ouvrir Image"  );
		this.menuSauvegarder   = new JMenuItem("Sauvegarder"   );
		this.menuRetourArriere = new JMenuItem("Retour Arrière");
		this.menuRetourAvant   = new JMenuItem("Retour Avant"  );
		this.menuAnnulerAction = new JMenuItem("Annuler Action");

		//Ajout composants au JPanel
		this.add(menuBar);

		menuBar.add(this.menuOuvrirImage  );
		menuBar.add(this.menuSauvegarder  );
		menuBar.add(this.menuRetourArriere);
		menuBar.add(this.menuRetourAvant  );
		menuBar.add(this.menuAnnulerAction);

		//Activation des composants
		this.menuOuvrirImage  .addActionListener(this);
		this.menuSauvegarder  .addActionListener(this);
		this.menuRetourArriere.addActionListener(this);
		this.menuRetourAvant  .addActionListener(this);
		this.menuAnnulerAction.addActionListener(this);
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
			this.controller.getImagesHistoriqueArriere();
		}
		
		if (e.getSource() == this.menuRetourAvant)
		{
			this.controller.getImagesHistoriqueAvant();
		}

		if (e.getSource() == this.menuAnnulerAction)
		{
			this.controller.removeMouseDessin();
		}
	}


	public String ouvrirFileChooser(int binaire) 
	{
		UIManager.put("FileChooser.cancelButtonText", "Annuler");
		UIManager.put("FileChooser.cancelButtonToolTipText", "Fermer la fenêtre");

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory( new java.io.File(".")  );
		fileChooser.setDialogTitle     ( "Choisir une image" );

		if (binaire == 0) 
			fileChooser.setFileSelectionMode( JFileChooser.FILES_ONLY );
		else 
			fileChooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );

		fileChooser.setAcceptAllFileFilterUsed( false );
		fileChooser.setApproveButtonText("Ouvrir");
		int result = fileChooser.showOpenDialog( this );
		
		String selectedFilePath = null;
		if ( result == JFileChooser.APPROVE_OPTION )
			return selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();

		return null;
	}
	
}
