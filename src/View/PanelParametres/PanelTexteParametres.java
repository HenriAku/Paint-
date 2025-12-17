package View.PanelParametres;

import Main.Controller;

import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Panel des parametres de texte
 */
public class PanelTexteParametres extends JPanel implements ActionListener
{
	private Controller ctrl;

	private String[] textureFiles;

	private JLabel labelTexture;
	private JComboBox<String> textureComboBox;

	private JLabel labelTexte;
	private JTextField  textFieldTexte;

	/**
	 * Constructeur du panel des parametres de texte
	 * @param ctrl Le controller de l'application
	 */
	public PanelTexteParametres( Controller ctrl )
	{
		this.ctrl = ctrl;

		this.setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );

		this.textureFiles = this.ctrl.getTextureFiles();

		this.labelTexture = new JLabel( "Choisir une texture :", JLabel.CENTER );

		this.textureComboBox = new JComboBox<String>( this.textureFiles );

		this.labelTexte = new JLabel( "Entrer le texte :", JLabel.CENTER );

		this.textFieldTexte = new JTextField( 20 );

		this.textFieldTexte.addActionListener( this );
		this.textureComboBox.addActionListener( this );

		this.add( this.labelTexte     );
		this.add( this.textFieldTexte );

		this.add( Box.createVerticalStrut( 10 ) );
		
		this.add( this.labelTexture    );
		this.add( this.textureComboBox );
	}

	public void actionPerformed( ActionEvent e )
	{
		if ( e.getSource() == this.textFieldTexte )
		{
			String texte = this.textFieldTexte.getText();
			this.ctrl.setCurrentTextContent(texte);
		}
		
		if ( e.getSource() == this.textureComboBox )
		{
			String texture = (String) this.textureComboBox.getSelectedItem();
			this.ctrl.setCurrentTextTexture(texture);
		}
	}

	public void refreshTextures()
	{
		this.textureComboBox.removeAllItems();

		String[] textures = this.ctrl.getTextureFiles();
		for (String t : textures)
		{
			this.textureComboBox.addItem(t);
		}
	}

}