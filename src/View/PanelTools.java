import java.awt.BorderLayout;
import javax.swing.JPanel;

public class PanelTools extends JPanel
{
	// Attributs
	private Controller controller;
	private PanelPalette panelPalette;
	private PanelParametre panelParametres;
	
	public PanelTools(Controller ctrl)
	{
		this.controller = ctrl;

		this.panelPalette    = new PanelPalette(this.controller);
		this.panelParametres = new PanelParametre(this.controller);

		this.setLayout(new BorderLayout());
		this.add(this.panelPalette, BorderLayout.NORTH);
		this.add(this.panelParametres, BorderLayout.CENTER);
	}

	public void showCard(String toolName)
	{
		// Délègue la demande de changement de vue à l'objet PanelParametre
		this.panelParametres.showCard(toolName);
	}
}