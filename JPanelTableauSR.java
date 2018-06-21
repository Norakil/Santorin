package santoriniG15.ihm;

import santoriniG15.abstractClass.APanelPlateau;
import javax.swing.*;
import java.awt.event.*;
import java.awt.GridLayout;
import java.awt.Toolkit;

public class JPanelTableauSR extends APanelPlateau 
{
	private VisuSR visu;
	
	private Integer ligDernierClique;
	private Integer colDernierClique;
	
	public JPanelTableauSR(VisuSR visu, String[] tabUrlImage)
	{
		super ( tabUrlImage );
		
		this.visu = visu;
	}
	
	public String getInitImage() { return "./imgTest.gif" ;}
	
	
	public Integer getLigDernierClique() { return this.ligDernierClique ;}
	public Integer getColDernierClique() { return this.colDernierClique ;}
	
	public void clicSur (int lig, int col, MouseEvent evt)
	{
		
		this.ligDernierClique = lig;
		this.colDernierClique = col;
	}
	
	public void resetClique()
	{
		this.ligDernierClique = null;
		this.colDernierClique = null;
	}
	
	public void majTableau() { super.majPlateau() ;}
	
	public void setValeur( int lig, int col, int val )
	{
		super.setValeur( lig, col, val );
		super.majPlateau();
	}
	
}
