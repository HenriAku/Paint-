package View;

import java.awt.CardLayout;

import Main.Controller;

import View.PanelParametres.PanelContrasteParametres;
import View.PanelParametres.PanelPotDePeintureParametres;
import View.PanelParametres.PanelRotationParametres;
import View.PanelParametres.PanelLuminositeParametres;
import View.PanelParametres.PanelTeinteParametres;
import View.PanelParametres.PanelTexteParametres;
import View.PanelParametres.PanelMiroirParametres;
import View.PanelParametres.PanelFusionParametres;
import View.PanelParametres.PanelRedimensionParametres;
import View.PanelParametres.PanelPipetteParametres;

import javax.swing.JPanel;

public class PanelParametre extends JPanel 
{
	private Controller controller;
	private CardLayout cardLayout; 

	private PanelContrasteParametres     panelContraste    ;
	private PanelPotDePeintureParametres panelPotDePeinture;
	private PanelRotationParametres      panelRotation     ;
	private PanelLuminositeParametres    panelLuminosite   ;
	private PanelTeinteParametres        panelTeinte       ;
	private PanelTexteParametres         panelTexte        ;
	private PanelMiroirParametres        panelMiroir       ;
	private PanelFusionParametres        panelFusion       ;
	private PanelRedimensionParametres   panelRedimension  ;
	private PanelPipetteParametres       panelPipette      ;

	public PanelParametre(Controller ctrl)
	{
		this.controller = ctrl;
		this.cardLayout = new CardLayout();

		this.setLayout(this.cardLayout);

		// Ajout des panneaux de paramètrest

		// Pot de Peinture
		this.panelPotDePeinture = new PanelPotDePeintureParametres( this.controller );
		this.add( this.panelPotDePeinture, "Bucket" );

		// Contraste
		this.panelContraste = new PanelContrasteParametres( this.controller );
		this.add( this.panelContraste, "Contraste" );

		// Rotation
		this.panelRotation = new PanelRotationParametres( this.controller );
		this.add( this.panelRotation, "Rotation" );

		// Luminosité
		this.panelLuminosite = new PanelLuminositeParametres( this.controller );
		this.add( this.panelLuminosite, "Luminosite" );

		// Teinte
		this.panelTeinte = new PanelTeinteParametres( this.controller );
		this.add( this.panelTeinte, "Teinte" );

		// Texte
		this.panelTexte = new PanelTexteParametres( this.controller );
		this.add( this.panelTexte, "Texte" );

		// Miroir
		this.panelMiroir = new PanelMiroirParametres( this.controller );
		this.add( this.panelMiroir, "Miroir" );


		// Fusion
		this.panelFusion = new PanelFusionParametres( this.controller );
		this.add( this.panelFusion, "Fusion" );

		// Redimension
		this.panelRedimension = new PanelRedimensionParametres( this.controller );
		this.add( this.panelRedimension, "Redimension" );

		// Pipette	
		this.panelPipette = new PanelPipetteParametres( this.controller );
		this.add( this.panelPipette, "Pipette" );

		// Panneau par défaut
		JPanel panelDefault = new JPanel();
		this.add(panelDefault, "Default");
		this.showCard("Default");
	}

	// Méthode pour afficher un panneau spécifique
	public void showCard(String cardName)
	{
		this.cardLayout.show(this, cardName); 
	}

	public void updateColorDisplay() {this.panelPipette.updateColorDisplay();}
}