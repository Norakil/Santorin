package santoriniG15.metier;

import santoriniG15.ControleurCUI;

/**
 * <b>Santorini est la classe représentant le jeu, la classe principal </b> 
 * 
 * @see Batisseur
 * @see Plateau
 * @see Joueur
 * @author groupe15
 *
 */
public class Santorini
{
	/**
	 *plateau de type Plateau
	 */
	private Plateau   plateau;
	/**
	 *tabJ de type Joueur
	 *@see Santorini#selectBat(int,int)
	 *@see Santorini
	 */
	private Joueur[]  tabJ;
	/**
	 *nbTour de type int 
	 *@see Santorini#finTour()
	 *@see Santorini
	 */
	private int       nbTour;
	/**
	 *batCourant representant le batisseur actuel 
	 *@see Santorini#selectBat(int,int)
	 */
	private Batisseur batCourant;
	
	/**
	 * Constructeur Santorini.
	 * <p>
	 * A la construction d'un objet Santorini, le plus jeune devient joueur1 , on remplit le tableau de joueur.
	 * </p>

	 *@param age1
	 *          Premier age donné
	 *@param age2	 *          second age donné
	 *@see Santorini
	 */
	public Santorini(int age1, int age2)
	{
		this.tabJ    = new Joueur[2];
		this.plateau = new Plateau();
		this.nbTour  =0;
		
		if ( age1 >= age2 )
		{
			int tmp = age1;
			age1    = age2;
			age2    = tmp;
		}
		
		this.tabJ[0] = new Joueur(age1, this.plateau);
		this.tabJ[1] = new Joueur(age2, this.plateau);
		
	}
	
	/**
	*Retourne le numero du joueur
	*@return la numero, sous forme d'un entier.
	*/
	public int getNumJoueur() { return this.nbTour%2 + 1;}
	
	/**
		 *Permet de construire une piece sur le plateau en fonction d'une direction et du batisseur actuel
		 *@param dir 
		 *          La direction voulu pour la piece 	
		 *@return l'etat de la construction, sous forme d'un booléen
		 *@see Plateau
		 *@see Batisseur#construire(String,Plateau)
	*/
	public boolean construire( String dir )
	{
		boolean b = this.batCourant.construire( dir, plateau );
		if ( b ) this.finTour();
		return b;
	}
	
	public boolean construire( int lig, int col )
	{
		int posLig = this.batCourant.getPosLig();
		int posCol = this.batCourant.getPosCol();
		
		int deltaLig = posLig - lig;
		int deltaCol = posCol - col;
		
		if (  deltaLig < -1 || deltaLig > 1 || deltaCol < -1 || deltaCol > 1 ||
		     (deltaLig == 0 && deltaCol == 0)                                   )
			return false;
		
		String dir ="";
		
		if ( deltaLig == -1 ) dir += "S";
		if ( deltaLig ==  0 ) dir += "";
		if ( deltaLig ==  1 ) dir += "N";
		if ( deltaCol == -1 ) dir += "E";
		if ( deltaCol ==  0 ) dir += "";
		if ( deltaCol ==  1 ) dir += "O";
		
		return this.batCourant.construire ( dir, this.plateau );
	}
	
	/**
		 *Permet de deplace le batisseur sur le plateau en fonction d'une direction et du batisseur actuel
		 *@param dir 
		 *          La direction voulu pour la piece 	 
		 *@return l'etat du deplacement, sous forme d'un booléen
		 *@see Plateau
		 *@see Batisseur#mouvement(String,Plateau)
	*/
	public boolean mouvement ( String dir ) { return this.batCourant.mouvement ( dir, this.plateau ) ;}
	
	public boolean mouvement ( int lig, int col )
	{
		int posLig = this.batCourant.getPosLig();
		int posCol = this.batCourant.getPosCol();
		
		int deltaLig = posLig - lig;
		int deltaCol = posCol - col;
		
		if (  deltaLig < -1 || deltaLig > 1 || deltaCol < -1 || deltaCol > 1 ||
		     (deltaLig == 0 && deltaCol == 0)                                   )
			return false;
		
		String dir ="";
		
		if ( deltaLig == -1 ) dir += "S";
		if ( deltaLig ==  0 ) dir += "";
		if ( deltaLig ==  1 ) dir += "N";
		if ( deltaCol == -1 ) dir += "E";
		if ( deltaCol ==  0 ) dir += "";
		if ( deltaCol ==  1 ) dir += "O";
		
		return this.batCourant.mouvement ( dir, this.plateau );
	}
	
	/**
		 *Permet de selectionner le batisseur sur le plateau en fonction de coordonée
		 *@param lig
		 *          la ligne choisie 
		 *@param col
		 *          la colonne choisie
		 *@return l'etat du deplacement, sous forme d'un booléen
	*/
	public boolean selectBat( int lig, int col )
	{
		//verification de non sortie du plateau 
		if ( lig < 0 || lig >= plateau.length() ||
		     col < 0 || col >= plateau.length()    )
			return false;
		//verification que la case posede un batisseur et association des deux
		if ( plateau.getCase(lig, col).aUnBatisseur() && 
		     plateau.getCase(lig, col).getBatisseur().getNumJoueur() == this.getNumJoueur() )
		{
			this.batCourant = plateau.getCase(lig, col).getBatisseur();
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void finTour() { this.nbTour++ ;}
	/**
     * Met à jour le batisseur a la case du tableau dont les positions sont demandé en parametre
	 * @param numJ
	 *            Le numero du joueur .
	 * @param lig
	 *            La nouvelle Ligne.
	 * @param col
	 *            La nouvelle Colonne.
	 * @return l'etat de l'initialisation , sous forme d'un booléen
     * @see Batisseur
	 * @see Joueur 
    */
	public boolean initBatisseur(int numJ, int lig, int col )
	{
		//verification de la possiblité du placement du batisseur sur le plateau
		if ( lig < 0 || lig >= plateau.length() ||
		     col < 0 || col >= plateau.length() ||
		     plateau.getCase(lig, col).aUnBatisseur())
			 return false;
		
		this.tabJ[numJ].initBatisseur( lig, col );
		
		return true;
	}
	
	// Méthode pour le DEBUG
	public void setCase( int lig, int col, int valeur )
	{
		if ( ControleurCUI.DEBUG )
			this.plateau.setCase( lig, col, new Case( valeur ) );
	}
	
	public int[][] getInfo() { return this.plateau.getTabCaseToInt() ;}
	
	public boolean estFini()
	{
		return plateau.victoire()||this.aPerdu();
	}
	
	public boolean  aPerdu() {return this.batCourant.aPerdu(plateau);}
	/**
	*Retourne la visualisation du plateau 
	*@return une String
	*/
	public String toString() { return this.plateau.toString() ;}
	
}
