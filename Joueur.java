package santoriniG15.metier;

/**
 * <b>Joueur est la classe representant la personne qui joue </b> 
 * 
 * @see Batisseur
 * @see Plateau
 * @author groupe15
 *
 */
public class Joueur
{
	/**nbJoueur variable sequentielle auto-Incremente
	 *@see Joueur#Joueur(int,int)
	 */
	private static int nbJoueur = 0;
	
	/**tabBat un tableau de [@linkBatisseur} 
	 *@see Joueur#initBatisseur(int,int)
	 *@see Joueur#Joueur(int,int)
	 */
	private Batisseur[] tabBat;
	/**jeu represante le {@link Plateau} actuel
	 *@see Plateau
	 *@see Joueur
	 */
	private Plateau     jeu;
	
	/**numJoueur represante le numero du {@link Joueur} actuel
	 *@see Joueur
	 */
	private int         numJoueur;
	
	/**numJoueur represante l'age du {@link Joueur} actuel
	 *@see Joueur
	 */
	private int         age;
	/**
	 * Constructeur Joueur.
	 * <p>
	 * A la construction d'un objet Joueur, le numJoueur est atribue
	 * </p>
	 * @param age
	 *            age du joueur.
	 * @param jeu
	 *            plateau actuel.
	 *@see Joueur#age
	 *@see Joueur#jeu
	 */
	public Joueur(int age, Plateau jeu)
	{
		this.jeu       = jeu;
		this.age       = age;
		this.tabBat    = new Batisseur[2];
		this.numJoueur = ++nbJoueur;
		
		
	}
	/**
	* Met a jour le batisseur a la case du tableau dont les positions sont demandees en parametre
	 * @param lig
	 *            La nouvelle Ligne.
	 * @param col
	 *            La nouvelle Colonne.

	* @see Batisseur
	*/
	public void initBatisseur( int lig, int col )
	{
		Batisseur bat = new Batisseur( this.numJoueur );
		//associe seulement si il n'y a pas deja de batisseur dans la case tableau de {@link Batisseur}
		if ( this.tabBat[0] == null )  this.tabBat[0] = bat;
		else if ( this.tabBat[1] == null ) this.tabBat[1] = bat;
		
		bat.setPos( lig, col );
		//positionnement du batisseur a la case dont les position sont demandé en parametre
		this.jeu.getCase(lig, col).setBatisseur( bat );
		
	}
	
	
}
