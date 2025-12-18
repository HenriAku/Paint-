package View;

import Main.Controller;

import View.PanelParametres.PanelContrasteParametres;
import View.PanelParametres.PanelPotDePeintureParametres;
import View.PanelParametres.PanelRotationParametres;
import View.PanelParametres.PanelLuminositeParametres;
import View.PanelParametres.PanelTeinteParametres;
import View.PanelParametres.PanelTexteParametres;
import View.PanelParametres.PanelMiroirParametres;
import View.PanelParametres.PanelSuperposerParametres;
import View.PanelParametres.PanelRedimensionParametres;
import View.PanelParametres.PanelPipetteParametres;
import View.PanelParametres.PanelFusionParametres;
import View.PanelParametres.PanelFiltreParametres;

import java.awt.CardLayout;

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
	private PanelSuperposerParametres    panelSuperposer   ;
	private PanelRedimensionParametres   panelRedimension  ;
	private PanelPipetteParametres       panelPipette      ;
	private PanelFiltreParametres        panelFiltre       ;

	/**
	 * Constructeur du panel des parametres
	 * @param ctrl
	 */
	public PanelParametre(Controller ctrl)
	{
		this.controller = ctrl;
		this.cardLayout = new CardLayout();

		this.setBackground(this.controller.getBackgroundColor());

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

		// superposer
		this.panelSuperposer = new PanelSuperposerParametres( this.controller );
		this.add( this.panelSuperposer, "Superposer" );

		// Redimension
		this.panelRedimension = new PanelRedimensionParametres( this.controller );
		this.add( this.panelRedimension, "Redimension" );

		// Pipette	
		this.panelPipette = new PanelPipetteParametres( this.controller );
		this.add( this.panelPipette, "Pipette" );

		// Noir et Blanc
		this.panelFiltre = new PanelFiltreParametres( this.controller );
		this.add( this.panelFiltre, "Noiretblanc" );
		
		// Panneau par défaut
		JPanel panelDefault = new JPanel();
		this.add(panelDefault, "Default");
		panelDefault.setBackground( this.controller.getBackgroundColor() );
		this.showCard("Default");
	}

	/**
	 * Méthodes de redirection des appels aux panels
	 * @param cardName
	 */
	public void showCard(String cardName) {this.cardLayout.show(this, cardName); }

	/**
	 * Méthodes de redirection des appels aux panels
	 */
	public void updateColorDisplay() {this.panelPipette.updateColorDisplay();}

	/**
	 * Méthodes de redirection des appels aux panels
	 */
	public void refreshTextures() {this.panelTexte.refreshTextures();}
}