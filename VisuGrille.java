package santoriniG15.metier;

/**
 * <b>VisuGrille est la classe représentant la visualisation du jeu </b> 
 * 
 * @see Batisseur
 * @see Plateau
 * @see Joueur
 * @author groupe15
 *
 */
public class VisuGrille
{
	/**
	 *transforme le tableau de chaine en une chaine de la forme voulu
	 *
	 *@param tab
	 *		le tableau de string
	 *@return la chaine , sous forme de string
	 * @see Plateau#toString()
	 */
	public static String tabEnChaine (String[][] tab)
	{
		
		String s="   ";
		String ligHori;
		
		ligHori = "   +";
		
		for(int col = 0; col < tab[0].length; col++)
		{
			ligHori += "---+";
			
			//construction du numero de coll
			s = s +"  "+ col+ " ";
			// s = s + (char) ('A'+col) + " ";			pour avoir des lettre a la place 
		}
		s = s + '\n'+ ligHori +'\n';

		
		for(int lig = 0; lig< tab.length; lig++)
		{
			s = s + String.format ("%2d",lig)+ " |";
			
			for(int col=0; col< tab[lig].length; col++)
			{
				s = s +"  "+ tab[lig][col] + "|" ;

			}
			s = s + '\n'+ ligHori +'\n';
			

			
		}
		return s;
	}
	
	public static String tabEnString( String[][] tab )
	{
		String s = "    ";
		String ligHori = "    +";
		
		for ( int cpt=0; cpt < tab.length; cpt++ )
			s += "   " + (char) ('A' + cpt) + "  ";
		
		s += "\n";
		
		for ( int col=0; col < tab[0].length; col++ )
		{
			for ( int cara=0; cara < 5; cara++ )
			{
				ligHori += "-";
			}
			ligHori += "+";
		}
		
		ligHori += "\n";
		
		s += ligHori;
		
		for ( int lig=0; lig < tab.length; lig++ )
		{
			s += " " + (lig+1) + ": ";
			for ( int col=0; col < tab[0].length; col++ )
			{
				s += "|" + tab[lig][col];
			}
			
			s += "|\n" + ligHori;
		}
		
		
		
		return s;
	}
	
}

