package santoriniG15;

import santoriniG15.metier.*;
import santoriniG15.ihm.*;

public class ControleurGUI
{
	private Santorini metier;
	private VisuSR    visu;
	
	public ControleurGUI()
	{
		
		this.visu   = new VisuSR();
		this.metier = null;
		boolean test = true;
		int age1=0, age2=0;
		
		this.visu.setMessageUtilisateur("Entrez vos ages ici : ");
		this.choixDesAges(test);
		
		this.placementInitDesBatisseur(test);
		
		// Le jeu 
		do
		{
			this.visu.setMessageUtilisateur("A Joueur " + this.metier.getNumJoueur() + " de selectionner son batisseur");
			this.selectionBatisseur(test);
			
			
			if ( this.metier.aPerdu() )
			{
				this.visu.afficherErreurUtilisateur("Joueur" + this.metier.getNumJoueur() +" votre" + 
				                                     "batisseur choisi est coincé, veuillez"         + 
				                                     "choisir l'autre."                                );
				this.selectionBatisseur(test);
				
				if(this.metier.aPerdu())
				{
					this.visu.afficherMessageUtilisateur("Vos 2 batisseurs sont bloqués, vous avez perdu joueur " + this.metier.getNumJoueur());
					System.exit(0);
				}
			}
			
			this.visu.setMessageUtilisateur("A Joueur " + this.metier.getNumJoueur() + " de deplacer son batisseur.");
			this.deplacerBatisseur(test);
			
			this.visu.setMessageUtilisateur("A Joueur " + this.metier.getNumJoueur() + " de construire.");
			
			this.gererConstruction(test);
			
			if (!this.metier.estFini() ) this.metier.finTour();
		} while ( !this.metier.estFini() );
		
		this.visu.afficherMessageUtilisateur("Joueur " + this.metier.getNumJoueur() + " a gagné!\n" +
		                                     "( Fermer cette fenetre quittera le jeu )"              );
		
		System.exit(0);
		
	}
	
	public void gererConstruction(boolean test)
	{
		if (!this.metier.estFini() )
			{
				do {
					test = this.visu.getLigDernierClique() != null && this.visu.getColDernierClique() != null;
				
					System.out.println( this.visu.getLigDernierClique() + " -- " + this.visu.getColDernierClique() );
					if ( test )
					{
						test = this.metier.construire( this.visu.getLigDernierClique(),
							                           this.visu.getColDernierClique() );
						if ( !test )
							this.visu.afficherErreurUtilisateur( "Vous ne pouvez pas construire sur" +
									                             " cette case."                        );
					
						this.traitementInfo();
						this.visu.resetClique();
					}
				
					try { Thread.sleep(10) ;} catch( Exception e ) {}
				
				} while (!test);
			}
	}
	
	public void deplacerBatisseur(boolean test)
	{
		do 
			{
				test = this.visu.getLigDernierClique() != null && this.visu.getColDernierClique() != null;
				
				System.out.println( this.visu.getLigDernierClique() + " -- " + this.visu.getColDernierClique() );
				if ( test )
				{
					test = this.metier.mouvement( this.visu.getLigDernierClique(),
					                              this.visu.getColDernierClique() );
					if ( !test )
						this.visu.afficherErreurUtilisateur( "Vous ne pouvez pas deplacer votre" +
							                                 " batisseur sur cette case."       );
					
					this.traitementInfo();
					this.visu.resetClique();
				}
				
				try { Thread.sleep(10) ;} catch( Exception e ) {}
				
			} while ( !test );
	}
	
	public void selectionBatisseur(boolean test)
	{
		do 
		{
			test = this.visu.getLigDernierClique() != null && this.visu.getColDernierClique() != null;
		
			System.out.println( this.visu.getLigDernierClique() + " -- " + this.visu.getColDernierClique() );
			if ( test )
			{
				test = this.metier.selectBat( (int)this.visu.getLigDernierClique(),
					                          (int)this.visu.getColDernierClique() );
				if ( !test )
					this.visu.afficherErreurUtilisateur( "Cette case n'est pas selectionnable.");
			
				this.traitementInfo();
				this.visu.resetClique();
			}
		
			try { Thread.sleep(10) ;} catch( Exception e ) {}
		
		} while ( !test );
	}
	
	public void choixDesAges(boolean test)
	{
		do 
		{
			test = this.visu.getAge1() != 0 && this.visu.getAge2() != 0;
			if ( test ) this.metier = new Santorini( this.visu.getAge1(), this.visu.getAge2() );
			try { Thread.sleep(10) ;} catch( Exception e ) {}
			
		} while ( !test );
	}
	
	public void placementInitDesBatisseur(boolean test)
	{
		this.visu.resetClique();
		
		for ( int cpt=0; cpt < 4; cpt++)
		{
			//Positionnement des batisseurs des joueurs
			this.visu.setMessageUtilisateur("Joueur " + (cpt/2+1) +" doit positionner son " + (cpt%2==0 ? "1er" : "2eme") + " batisseur.");
			do 
			{
				test = this.visu.getLigDernierClique() != null && this.visu.getColDernierClique() != null;
				
				System.out.println( this.visu.getLigDernierClique() + " -- " + this.visu.getColDernierClique() );
				if ( test )
				{
					test = this.metier.initBatisseur( cpt/2, (int)this.visu.getLigDernierClique(),
					                                         (int)this.visu.getColDernierClique()  );
					if ( !test )
						this.visu.afficherErreurUtilisateur( "Vous ne pouvez pas poser votre" +
							                                 " batisseur sur cette case."       );
					
					this.traitementInfo();
					this.visu.resetClique();
				}
				
				try { Thread.sleep(10) ;} catch( Exception e ) {}
				
			} while ( !test );
		}
		this.visu.resetClique();
	}
	
	private void traitementInfo()
	{
		int[][] tabInt = this.metier.getInfo();
		for ( int lig=0; lig < tabInt.length; lig++ )
		{
			for ( int col=0; col < tabInt[0].length; col++ )
			{
				this.visu.setValeur( lig, col, tabInt[lig][col] );
				System.out.println(""+tabInt[lig][col]);
			}
		}
	}
	
	public static void main(String[] args)
	{
		new ControleurGUI();
	}
}
