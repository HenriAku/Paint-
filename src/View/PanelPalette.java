import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFileChooser;

public class PanelPalette extends JPanel implements ActionListener
{
	// Attributs
	private JButton potPeinture;
	private JButton contraste;
	private JButton text;
	private JButton rotation;
	private JButton luminosite;
	private JButton teinte;
	private JButton texte;
	private JButton miroir;
	private JButton fusion;
	private JButton redimension;
	
	private JButton   ouvrirImage;
	private JButton   btnSauvegarder;
	private JButton   btnAnnuler;

	private Controller ctrl;

	public PanelPalette(Controller ctrl)
	{
		// Initialisation des attributs
		this.ctrl = ctrl;

		this.potPeinture  = new JButton("Bucket");
		this.contraste    = new JButton("Contraste");
		this.text         = new JButton("Texte");	
		this.rotation	  = new JButton("Rotation");
		this.luminosite   = new JButton("Luminosite");
		this.teinte 	  = new JButton("Teinte");
		this.texte	      = new JButton("Texte");
		this.miroir       = new JButton("Miroir");
		this.fusion       = new JButton("Fusion");
		this.redimension  = new JButton("Redimension");

		this.ouvrirImage     = new JButton("Ouvrir Image");
		this.btnSauvegarder  = new JButton("Sauvegarder" );
		this.btnAnnuler      = new JButton("Annuler"     );

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		this.potPeinture.addActionListener(this);
		this.contraste.addActionListener(this);
		this.text.addActionListener(this);
		this.rotation.addActionListener(this);
		this.luminosite.addActionListener(this);
		this.teinte.addActionListener(this);
		this.texte.addActionListener(this);
		this.miroir.addActionListener(this);
		this.fusion.addActionListener(this);
		this.redimension.addActionListener(this);
		
		this.ouvrirImage.addActionListener(this);
		this.btnSauvegarder.addActionListener(this);
		this.btnAnnuler.addActionListener(this);

		this.add(this.potPeinture);
		this.add(this.contraste);
		this.add(this.text);
		this.add(this.rotation);
		this.add(this.luminosite);
		this.add(this.teinte);
		this.add(this.texte);
		this.add(this.miroir);
		this.add(this.fusion);
		this.add(this.redimension);

		this.add(this.ouvrirImage);
		this.add(this.btnSauvegarder);
		this.add(this.btnAnnuler);
	}

	public void actionPerformed(ActionEvent e)
	{
		String toolName = null;

		if(e.getSource() instanceof JButton)
		{
			JButton clickedButton = (JButton) e.getSource();
			toolName = clickedButton.getText();
		}

		if (this.potPeinture == e.getSource() || 
			this.contraste   == e.getSource() || 
			this.text        == e.getSource() ||
			this.rotation    == e.getSource() || 
			this.luminosite  == e.getSource() ||
			this.teinte      == e.getSource() ||
			this.texte       == e.getSource() ||
			this.miroir      == e.getSource() ||
			this.fusion      == e.getSource() ||
			this.redimension == e.getSource()) 
		{
			this.ctrl.toolSelected(toolName); 
			this.ctrl.setCurrentTool(ToolType.valueOf(toolName.toUpperCase()));
		}

		// Action pour ouvrir une image
		if(this.ouvrirImage == e.getSource()) 
		{
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new java.io.File("."));
			fileChooser.setDialogTitle("Choisir une image");
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooser.setAcceptAllFileFilterUsed(false);

			int result = fileChooser.showOpenDialog(this);
			
			if (result == JFileChooser.APPROVE_OPTION) {
				String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
				this.ctrl.addImage(selectedFilePath);
				this.ctrl.updateDessin();
			};
		}

		if(this.btnSauvegarder == e.getSource()) 
		{
			// Action pour sauvegarder l'image
		}

		// Action pour annuler la dernière opération
		if(this.btnAnnuler == e.getSource()) 
		{
			this.ctrl.removeMouseDessin();
		}
	}
}
