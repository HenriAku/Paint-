package View.PanelParametres;

import Main.Controller;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

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

		this.setBackground( this.ctrl.getBackgroundColor() );


		this.setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );
		this.setBorder(new EmptyBorder(20, 20, 20, 20));

		this.textureFiles = this.ctrl.getTextureFiles();
		
		Dimension fieldMaxSize = new Dimension(300, 30); 

		this.labelTexte = new JLabel( "Entrer le texte :", JLabel.CENTER );
		this.labelTexte.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.textFieldTexte = new JTextField( 20 );
		this.textFieldTexte.setMaximumSize(fieldMaxSize);
		this.textFieldTexte.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.textFieldTexte.addActionListener( this );

		this.labelTexture = new JLabel( "Choisir une texture :", JLabel.CENTER );
		this.labelTexture.setAlignmentX(Component.CENTER_ALIGNMENT);

		this.textureComboBox = new JComboBox<String>( this.textureFiles );
		this.textureComboBox.setMaximumSize(fieldMaxSize);
		this.textureComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.textureComboBox.addActionListener( this );

		this.add( this.labelTexte     );
		this.add( Box.createVerticalStrut( 5 ) );
		this.add( this.textFieldTexte );

		this.add( Box.createVerticalStrut( 20 ) );
		
		this.add( this.labelTexture    );
		this.add( Box.createVerticalStrut( 5 ) );
		this.add( this.textureComboBox );
		
		this.add( Box.createVerticalGlue() );
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
			if (texture != null) {
				this.ctrl.setCurrentTextTexture(texture);
			}
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