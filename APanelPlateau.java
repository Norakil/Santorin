package santoriniG15.abstractClass;

import javax.swing.*;
import java.awt.event.*;
import java.awt.GridLayout;
import java.awt.Toolkit;

public abstract class APanelPlateau extends JPanel
{
	
	private static final int LARGEUR_IMAGE = 145;
	private static final int HAUTEUR_IMAGE = 145;
	
	private int[][]    tabValeur;
	private JLabel[][] tabLabel;
	
	private String[] tabUrlImage;
	
	public APanelPlateau(String[] tabUrlImage)
	{
		this.tabUrlImage = tabUrlImage;
		
		this.tabValeur = new int[5][5];
		this.tabLabel  = new JLabel[5][5];
		
		this.setLayout( new GridLayout( 5, 5 ) );
		
		this.addMouseListener( new GereMouvementSouris( this ) );
		
		this.initImage( this.tabUrlImage[0] );
		
	}
	
	public void initImage(String urlImgBase)
	{
		for ( int lig=0; lig < this.tabValeur.length; lig++)
			for ( int col=0; col < this.tabValeur[0].length; col++)
			{
				ImageIcon img = new ImageIcon( Toolkit.getDefaultToolkit().getImage( this.tabUrlImage[ this.tabValeur[lig][col] ] ) );
				
				this.tabLabel[lig][col] = new JLabel( img );
				
				this.add( this.tabLabel[lig][col] );
			}
	}
	
	private int getLargeurImage() { return APanelPlateau.LARGEUR_IMAGE ;}
	private int getHauteurImage() { return APanelPlateau.HAUTEUR_IMAGE ;}
	
	public int getValeur( int lig, int col ) { return this.tabValeur[lig][col] ;}
	
	
	public void setValeur( int lig, int col, int val )
	{
		if ( lig >= 0 && col >= 0 && lig < this.tabValeur.length && col < this.tabValeur[0].length )
			this.tabValeur[lig][col] = val;
	}
	
	public void majPlateau()
	{
		for ( int lig=0; lig < this.tabValeur.length; lig++)
			for ( int col=0; col < this.tabValeur[0].length; col++)
			{
				ImageIcon img = new ImageIcon( Toolkit.getDefaultToolkit().getImage( this.tabUrlImage[ this.tabValeur[lig][col] ] ) );
				
				this.tabLabel[lig][col].setIcon ( img );
			}
		
		this.updateUI();
		
	}
	
	
	public abstract void clicSur( int lig, int col, MouseEvent evt);
	
	
	private class GereMouvementSouris extends MouseAdapter
	{
		
		private APanelPlateau pnlPlateau;
		
		public GereMouvementSouris( APanelPlateau pnlPlateau )
		{
			this.pnlPlateau = pnlPlateau;
		}
		
		public void mouseClicked ( MouseEvent evt)
		{
			
			int posX = ( evt.getX()/(this.pnlPlateau.getLargeurImage()+1) );
			int posY = ( evt.getY()/(this.pnlPlateau.getHauteurImage()+1) );
			
			this.pnlPlateau.clicSur( posY, posX, evt );
			
		}
	}
}
