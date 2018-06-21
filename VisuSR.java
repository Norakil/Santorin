package santoriniG15.ihm;

import javax.swing.*;
import java.awt.BorderLayout;

public class VisuSR extends JFrame
{
	private JPanelTableauSR panelTableau;
	
	private PanelScore   panelScore;
	
	private String[] tabImageURL;
	
	public VisuSR()
	{
		this.setTitle( "Santorini" );
		this.setLocation( 20, 20 );
		this.setResizable( false );
		this.setLayout ( new BorderLayout() );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		this.setTabImageURL();
		this.panelTableau = new JPanelTableauSR(this, this.tabImageURL);
		
		this.panelScore   = new PanelScore(false);
		
		JPanel panelBlanc = new JPanel();
		
		panelBlanc.add( new JLabel("  ") );
		
		this.add( panelTableau, BorderLayout.CENTER );
		this.add( panelBlanc  , BorderLayout.EAST   );
		this.add( panelScore  , BorderLayout.NORTH  );
		this.add( panelBlanc  , BorderLayout.WEST   );
		this.add( panelBlanc  , BorderLayout.SOUTH  );
		
		this.pack();
		this.setVisible(true);
	}
	
	public void setTabImageURL()
	{
		this.tabImageURL = new String[37];
		String rep = "./images/";
		
		this.tabImageURL[0] = rep + "fond.png";
		this.tabImageURL[1] = rep + "etage1.png";
		this.tabImageURL[2] = rep + "etage2.png";
		this.tabImageURL[3] = rep + "etage3.png";
		this.tabImageURL[4] = rep + "etage4.png";
		
		this.tabImageURL[05] = rep + "perso1.png";
		this.tabImageURL[06] = rep + "perso2.png";
		this.tabImageURL[15] = rep + "etage1bleu.png";
		this.tabImageURL[16] = rep + "etage1rouge.png";
		this.tabImageURL[25] = rep + "etage2bleu.png";
		this.tabImageURL[26] = rep + "etage2rouge.png";
		this.tabImageURL[35] = rep + "etage3bleu.png";
		this.tabImageURL[36] = rep + "etage3rouge.png";
		
	}
	
	public Integer getLigDernierClique() { return this.panelTableau.getLigDernierClique() ;}
	public Integer getColDernierClique() { return this.panelTableau.getColDernierClique() ;}
	
	public int getAge1            () { return this.panelScore.getAge1()               ;}
	public int getAge2            () { return this.panelScore.getAge2()               ;}
	
	public void setMessageUtilisateur( String msg )
	{
		this.panelScore.setMessageUtilisateur(msg );
		this.panelTableau.majTableau();
	}
	
	public void afficherMessageUtilisateur( String msg )
	{
		JOptionPane jop = new JOptionPane();
		
		jop.showMessageDialog(null, msg,"Information", JOptionPane.INFORMATION_MESSAGE );
	}
	
	public void afficherErreurUtilisateur( String msg )
	{
		JOptionPane jop = new JOptionPane();
		
		jop.showMessageDialog(null, msg,"Erreur", JOptionPane.WARNING_MESSAGE );
	}
	
	public void setValeur( int lig, int col, int val )
	{
		this.panelTableau.setValeur( lig, col, val );
	}
	
	public void resetClique() { this.panelTableau.resetClique() ;}
	
	public static void main (String[] arg)
	{
		new VisuSR();
	}
}
