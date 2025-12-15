import javax.swing.JFrame;
import javax.swing.plaf.PanelUI;
import java.awt.BorderLayout;

public class FramePrincipale extends JFrame
{
	// Attributs
	private PanelTools  panelTools ;
	private PanelDessin panelDessin;

	public FramePrincipale()
	{
		this.panelTools  = new PanelTools();
		this.panelDessin = new PanelDessin();
		
		this.setTitle("Paint-");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		this.add(this.panelTools, BorderLayout.NORTH);
		this.add(this.panelDessin, BorderLayout.CENTER);
		

		this.setVisible(true);
	}

	public static void main(String[] args)
	{
		new FramePrincipale();
	}
}	