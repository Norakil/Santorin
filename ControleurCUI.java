package santoriniG15;

import santoriniG15.metier.*;
import java.util.Scanner;
/**
 * <b>ControleurCUI est la classe contenant le main faisant tourner le jeu .</b> 
 * @see Case
 * 
 * @author groupe15
 *
 */
public class ControleurCUI
{
	
	public static final boolean DEBUG = true;
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner( System.in );
		int age1, age2;
		boolean test=true;
		Santorini jeu = null;
		
		if ( ControleurCUI.DEBUG )
		{
			jeu = new Santorini( 1, 2 );
		}
		else
		{
			System.out.println("Donnez l'age de : ");
		
			System.out.print("\t -Joueur 1 : ");
			age1 = sc.nextInt(); sc.nextLine();
		
			System.out.print("\t -Joueur 2 : ");
			age2 = sc.nextInt(); sc.nextLine();
		
			System.out.println("\n");
			jeu = new Santorini(age1, age2);
			System.out.println("Le joueur le plus agé deviens joueur 2"+"\n\n");
			System.out.println("Positionnement des batisseurs");
		}
		
		if ( ControleurCUI.DEBUG )
		{
			//                       Ligne : A  B  C  D  E
			int[][] tabVal = new int[][] { { 0, 2, 0, 0, 0},   // 1
			                               { 2, 2, 0, 0, 0},   // 2
			                               { 0, 0, 0, 0, 0},   // 3
			                               { 0, 0, 0, 2, 2},   // 4
			                               { 0, 0, 0, 2, 0} }; // 5
			
			for ( int cpt=0; cpt < tabVal.length * tabVal[0].length; cpt++)
			{
				jeu.setCase( cpt/tabVal.length, cpt%tabVal[0].length, 
				             tabVal[cpt/tabVal.length][cpt%tabVal[0].length] );
			}
			
			//                    lig - col
			jeu.initBatisseur( 0,   0,    0); // Init le 1er  batisseur du joueur 1 en A1
			jeu.initBatisseur( 0,   4,    4); // Init le 2eme batisseur du joueur 1 en E1
			jeu.initBatisseur( 1,   2,    2); // Init le 1er  batisseur du joueur 2 en A5
			jeu.initBatisseur( 1,   3,    1); // Init le 2eme batisseur du joueur 2 en E5
			
		}
		else
		{
			for (int cpt=0; cpt < 4; cpt++ )
			{
				do {
				
					System.out.println( jeu );
					String dir="";
					System.out.println("Joueur " + (cpt/2+1) +" - Entrez les coordonnées de votre " + (cpt%2==0 ? "1er" : "2eme" ) + " batisseur ( ex : A1 ) : ");
					dir = sc.nextLine();
				
					test = dir.length() == 2;
					dir = dir.toUpperCase();
					if ( test ) test = jeu.initBatisseur( cpt/2, dir.charAt(1) - '1', dir.charAt(0)-'A' );
					if ( !test) System.out.println( "Coordonnees incorrectes" );
				
				
				} while (!test);
			}
		
			System.out.println("\nPlace au jeu\n");
		}
		
		
		do {
			
			System.out.println( jeu );
			System.out.println("Au joueur " + jeu.getNumJoueur() + " de jouer.");
			
			test = true;
			do {
				String dir="";
				System.out.println("Joueur " + jeu.getNumJoueur() +" - Entrez les coordonnees du batisseur a selectionner ( ex : A1 ) : ");
				dir = sc.nextLine();
				dir = dir.toUpperCase();
				test = dir.length() == 2;
				
				if ( test ) test = jeu.selectBat( dir.charAt(1) - '1' , dir.charAt(0)-'A' );
				if ( !test) System.out.println( "Coordonnees incorrectes" );
				
			} while (!test);
			
			
			test = true;
			if(!jeu.aPerdu())
			{
				do {
					System.out.println( jeu );
					String dir="";
					System.out.println("Joueur " + jeu.getNumJoueur() +" - Entrez la direction ou se deplacer ( ex : NE ) : ");
					dir = sc.nextLine();
					dir = dir.toUpperCase();
					test = dir.length()>0 && dir.length() < 3;
					if ( test ) test = jeu.mouvement( dir );
					if ( !test) System.out.println( "Coordonnees incorrectes" );
				
				} while (!test);
			}
			else 
			{
				System.out.println("Joueur" + jeu.getNumJoueur() +" votre batisseur choisie est coincé, veuillez choisir l'autre'");
				do {
						String dir="";
						System.out.println("Joueur " + jeu.getNumJoueur() +" - Entrez les coordonnees du batisseur a selectionner ( ex : A1 ) : ");
						dir = sc.nextLine();
						dir = dir.toUpperCase();
						test = dir.length() == 2;
				
						if ( test ) test = jeu.selectBat( dir.charAt(1) - '1' , dir.charAt(0)-'A' );
						if ( !test) System.out.println( "Coordonnees incorrectes" );
				
					} while (!test);
					if(jeu.aPerdu()){System.out.println("vos 2 batisseurs sont bloqués , vous avez perdu");System.exit(0);}
					
			}
			
			
			test = true;
			if (!jeu.estFini())
			{
				do {
					System.out.println( jeu );
					String dir="";
					System.out.println("Joueur " + jeu.getNumJoueur() +" - Entrez la direction ou construire( ex : NE ) : ");
					dir = sc.nextLine();
					dir = dir.toUpperCase();
					test = dir.length()>0 && dir.length() < 3;

					if ( test ) test = jeu.construire( dir );
					if ( !test) System.out.println( "Coordonnees incorrectes" );
				
				} while (!test);
			}
			else {System.out.println("Joueur" + jeu.getNumJoueur() +" vous avez gagné !!!!");}
			
			System.out.println( jeu );
			
		} while( !jeu.estFini() );
		
	}
	
	
}
