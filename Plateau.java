package santoriniG15.metier;

/**

 * <b>Plateau est la classe représentant le plateau.</b> 
 * @see Case
 * 
 * @author groupe15
 *

 */
public class Plateau 
{
	/**tabCase un tableau de cases represantant le plateau
	 *@see Case
	 *@see VisuGrille
	 *@see Plateau#Plateau()
	 *@see Plateau#getCase(int,int)
	 *@see Plateau#setCase(int,int)

	 */
	private Case[][]      tabCase;
	
	 /**
	 * Constructeur Plateau.
	 * <p>
	 * A la construction d'un objet Plateau, le tabCase est initialisé.
	 * </p>

	 *@see Plateau#tabCase
	 *@see Case
	 */
	public Plateau()
	{
		tabCase= new Case[5][5];
		//initialisation du tableau de cases
		for ( int lig=0; lig < this.tabCase.length; lig++)
			for ( int col=0; col < this.tabCase[0].length; col++)
				this.tabCase[lig][col] = new Case();
		
	}
	
	/**
	 *Retourne la case du tableau selectioner en parametre 
	 * @param lig
	 *            La nouvelle Ligne.
	 * @param col
	 *            La nouvelle Colonne.
	*@return la case, sous forme d'une {@link Case}.
	*/
	public Case getCase(int lig, int col) { return this.tabCase[lig][col] ;} //retourne la case passée en parametre 
	/**
	*Retourne la taille du plateau 
	*@return la taille, sous forme d'un int.
	*/
	public int length() { return this.tabCase.length ;} //retourne la taille du plateau
	
	/**
     * Met à jour la Case selectionner
	 * @param lig
	 *            La nouvelle Ligne.
	 * @param col
	 *            La nouvelle Colonne.
     * @param c
     *            La nouvelle Case.
     * @see Case
    */
	public void setCase(int lig, int col, Case c) 
	{
		if ( lig >= 0 && col >= 0 && col < this.tabCase[0].length && lig < this.tabCase.length )
			this.tabCase[lig][col] = c;
		
	}
	/**
	 *permet d'identifier la victoire d'un joueur ,c'est à dire si un batisseur se trouve au niveau 3
	 *@return l'etat de la victoire , sous forme d'un booléen
	*/
	public boolean victoire()
	{
		//parcours le tableau de cases et retourne true si un batisseur se trouve au niveau 3
		for ( int lig=0; lig < this.tabCase.length; lig++)
			for ( int col=0; col < this.tabCase[0].length; col++)
			{
				if ( tabCase[lig][col].aUnBatisseur() && this.tabCase[lig][col].getBatisseur().getCase().getNiveau() == 3 )
					return true;
			}
		
		return false;
	}
	
	public int[][] getTabCaseToInt()
	{
		int[][] tabInt = new int[this.tabCase.length][this.tabCase[0].length];
		
		for ( int lig=0; lig < this.tabCase.length; lig++ )
		{
			for ( int col=0; col < this.tabCase[0].length; col++ )
			{
				Case c = this.tabCase[lig][col];
				if ( c.aUnBatisseur() )
				{
					Batisseur bat = c.getBatisseur();
					
					tabInt[lig][col] = c.getNiveau()*10 + (bat.getNumJoueur() == 1 ? 5 : 6 );
				}
				else
				{
					tabInt[lig][col] = c.getNiveau();
				}
			}
		}
		return tabInt;
	}
	/**
	*Retourne la visualisation du plateau 
	*@return une String
	*/
	public String toString()
	{
		//affiche le tableau case par case et affiche les batisseurs lorsqu'il en trouve un par la méthode aUnBatisseur()
		String[][] tabS = new String[5][5];
		
		for ( int lig=0; lig < this.tabCase.length; lig++)
			for ( int col=0; col < this.tabCase[0].length; col++)
			{
				tabS[lig][col] = ( tabCase[lig][col].aUnBatisseur () ? String.format("%14s", this.tabCase[lig][col].getBatisseur() ) : 
				                                                       String.format("%5s", this.tabCase[lig][col] )                  );
			}
			
		return VisuGrille.tabEnString(tabS);
	}
}
