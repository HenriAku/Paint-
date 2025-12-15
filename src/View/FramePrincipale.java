import javax.swing.JFrame;
import java.awt.BorderLayout;

public class FramePrincipale extends JFrame
{
	// Attributs
	private PanelTools  panelTools ;
	private PanelDessin panelDessin;

	private Controller   controller;

	public FramePrincipale(Controller ctrl)
	{
		this.controller  = ctrl;
		this.panelTools  = new PanelTools(this.controller);
		this.panelDessin = new PanelDessin(this.controller);

		this.setTitle("Paint-");
		this.setSize(1200, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		this.add(this.panelTools, BorderLayout.NORTH);
		this.add(this.panelDessin, BorderLayout.CENTER);
		

		this.setVisible(true);
	}
}	