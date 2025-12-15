import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class OpenImage extends JFrame
{
	private JFileChooser fileChooser;
	private String 	     selectedFilePath;
	private Controller    controller;

	public OpenImage(Controller ctrl)
	{
		this.controller = ctrl;
		this.setTitle("Ouvrir une image");
		this.setSize(600, 400);

		this.fileChooser = new JFileChooser();
		this.fileChooser.setCurrentDirectory(new java.io.File("."));
		this.fileChooser.setDialogTitle("Choisir une image");
		this.fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		this.fileChooser.setAcceptAllFileFilterUsed(false);

		int result = this.fileChooser.showOpenDialog(this);
		
		if (result == JFileChooser.APPROVE_OPTION) {
			this.selectedFilePath = this.fileChooser.getSelectedFile().getAbsolutePath();
			this.controller.addImage(this.selectedFilePath);
			this.controller.updateDessin();
			this.dispose();
		}

		this.setVisible(true);
	}

}
