package santoriniG15.ihm;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.*;

public class PanelScore extends JPanel implements ActionListener
{
	private boolean bAffiche = true;
	
	private JLabel lblScore;
	private JLabel lblMsg;
	
	private JTextField txtAge1;
	private JTextField txtAge2;
	
	private int age1;
	private int age2;
	
	public PanelScore(boolean score)
	{
		this.setLayout(new FlowLayout( FlowLayout.RIGHT ));
		
		this.lblScore = new JLabel ("Score: 0");
		
		this.lblMsg  = new JLabel("");
		this.txtAge1 = new JTextField ();
		this.txtAge2 = new JTextField ();
		this.txtAge1.setColumns(5);
		this.txtAge2.setColumns(5);
		
		this.txtAge1.addActionListener(this);
		this.txtAge2.addActionListener(this);
		
		if (score)
		{
			this.add(this.lblScore);
		}
		else
		{
			this.add( this.lblMsg );
			this.add(new JLabel ("Age du 1er joueur: "));
			this.add(this.txtAge1);
			this.add(new JLabel ("Age du 2eme joueur: "));
			this.add(this.txtAge2);
		}
		
		this.setVisible(bAffiche);
	}
	
	public void setMessageUtilisateur(String msg ) { this.lblMsg.setText(msg) ;}
	
	public int  getAge1() { return this.age1 ;}
	public int  getAge2() { return this.age2 ;}
	
	public void setVisiblee ( boolean visible) { this.bAffiche = visible; }
	
	public void actionPerformed (ActionEvent e)
	{
		if (e.getSource() == this.txtAge1)
		{
			String s = this.txtAge1.getText();
			try {
				this.age1 = Integer.parseInt(s);
				if ( this.age1 != 0 ) this.txtAge1.setEditable(false);
			} catch( Exception e1 ) {  this.age1 = 0 ;}
			
			this.txtAge1.setText( (this.age1==0 ? "" : "" + this.age1) );
		}
		
		if (e.getSource() == this.txtAge2)
		{
			String s = this.txtAge2.getText();
			try {
				this.age2 = Integer.parseInt(s);
				if ( this.age2 != 0 ) this.txtAge2.setEditable(false);
			} catch( Exception e1 ) {  this.age2 = 0 ;}
			
			this.txtAge2.setText( (this.age2==0 ? "" : "" + this.age2) );
		}
	}
	
	
	
}
