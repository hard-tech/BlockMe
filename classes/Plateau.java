/**
 * La classe "plateau" représente un plateau de jeu avec une hauteur et une largeur spécifiées, et fournit des méthodes
 * pour initialiser et afficher le plateau de jeu.
 */
package classes;
import fonctions.coordonees;
/**
 * La classe "plateau" n'est pas définie dans le code fourni.
 */
public class Plateau{


    public static final int HAUTEUR = 10;
    public static final int LARGEUR = 11;
    // Remplacer par l'état réel du jeu, où chaque cellule contient soit un identifiant de joueur, soit "--" pour une case vide/détruite
    public static String[][] plateau = new String[HAUTEUR][LARGEUR];

    /**
     * La fonction PlateauDeJeu initialise un plateau de jeu avec des espaces vides et définit les positions des
     * joueurs ou des obstacles.
     */
  
    public static void initialisationPlateauDeJeu(int nombreDeJoueurs, Joueur[] joueurs) {
        // Initialisation du plateau avec des cases vides
        for (int i = 0; i < HAUTEUR; i++) {
            for (int j = 0; j < LARGEUR; j++) {
                plateau[i][j] = "   "; // Case vide
            }
        }
        // Si le nombre de joueurs est 2
        if(nombreDeJoueurs == 2) { 
            // Positionnement des joueurs
            plateau[4][5] = " 1 "; // Joueur 1
            plateau[5][5] = " 2 "; // Joueur 2

            joueurs[0].ligne = 4; joueurs[0].colonne = 5;
            joueurs[1].ligne = 5; joueurs[1].colonne = 5;
        }

        // Si le nombre de joueurs est 3
        else if(nombreDeJoueurs == 3) { 
            // Positionnement des joueurs
            plateau[4][5] = " 1 "; // Joueur 1
            plateau[5][6] = " 2 "; // Joueur 2
            plateau[5][4] = " 3 "; // Joueur 3


            joueurs[0].ligne = 4; joueurs[0].colonne = 5;
            joueurs[1].ligne = 5; joueurs[1].colonne = 6;
            joueurs[2].ligne = 5; joueurs[2].colonne = 4;
        }
        // Si le nombre de joueurs est 4
        else if(nombreDeJoueurs == 4) { 
            // Positionnement des joueurs
            plateau[4][4] = " 1 "; // Joueur 1
            plateau[5][4] = " 2 "; // Joueur 2
            plateau[4][6] = " 3 "; // Joueur 3
            plateau[5][6] = " 4 "; // Joueur 4

            joueurs[0].ligne = 4; joueurs[0].colonne = 4;
            joueurs[1].ligne = 5; joueurs[1].colonne = 4;
            joueurs[2].ligne = 4; joueurs[2].colonne = 6;
            joueurs[3].ligne = 5; joueurs[3].colonne = 6;
        }
    }

    public static void nettoyageCasePrecedente(Joueur[] joueurs) {
        int[][] coJoueurs = coordonees.recupererCoordonnees(joueurs); // Recupère les coordonnées du plateau de jeu
        for (int[] coJoueur : coJoueurs) {
            plateau[ coJoueur[0] ][ coJoueur[1] ] = "   "; // Mise à jour de la case du joueur
        }
    }

    public static void miseAJourPlateauDeJeu(Joueur[] joueurs) {
        int[][] coJoueurs = coordonees.recupererCoordonnees(joueurs); // Recupère les coordonnées du plateau de jeu
        int i = 0;
        
        for (int[] coJoueur : coJoueurs) {
            i++; // incrementation du numéro du joueur
            plateau[ coJoueur[0] ][ coJoueur[1] ] = " "+ i +" "; // Mise à jour de la case du joueur
        }
    }

    public static boolean detruireCase(int [] destination){
        if (plateau[destination[0]][destination[1]]== "   ") {// Si la case est vide
                plateau[destination[0]][destination[1]] = " X ";// Detruire la case
                return true;
        }else{
            return false;
        }
    }

    /**
     * La fonction "afficherPlateau" imprime un plateau de jeu avec une bordure à double ligne.
     */
    public static void afficherPlateau(Joueur joueur) {
    // Afficher le nom du joueur en ASCII art
    System.out.println("       ---------------------------------");
    System.out.println("       |                               |");
    System.out.println("       |    C'est au tour du joueur    |");
    
    // Calculer la largeur maximale disponible pour le nom
    int maxWidth = 33; // Largeur maximale disponible pour le nom du joueur
    
    // Si le nom du joueur dépasse la largeur maximale, le tronquer
    String nomJoueur = joueur.nom;
    if (nomJoueur.length() > maxWidth - 18) {
        nomJoueur = nomJoueur.substring(0, maxWidth - 21) + "...";
    }
    
    // Centrer le nom du joueur dans la ligne
    int espaceGauche = (maxWidth - nomJoueur.length()) / 2;
    int espaceDroite = maxWidth - nomJoueur.length() - espaceGauche;
    System.out.print("       |");
    for (int i = 0; i < espaceGauche - 2; i++) {
        System.out.print(" ");
    }
    System.out.print(nomJoueur);
    for (int i = 0; i < espaceDroite; i++) {
        System.out.print(" ");
    }
    System.out.println("|");

    System.out.println("       |         de jouer !            |");
    System.out.println("       |                               |");
    System.out.println("       ---------------------------------");

    // Imprimer les indices de colonne (lettres A-J)
    System.out.print("    "); // Espaces pour aligner avec les cases du plateau
    for (char lettre = 'A'; lettre < 'A' + LARGEUR; lettre++) {
        System.out.print(" " + lettre + "  "); // Imprimer les lettres pour les colonnes
    }
    System.out.println();

    // Imprimer la bordure supérieure
    System.out.print("   ╔");
    for (int j = 0; j < LARGEUR; j++) {
        System.out.print("═══");
        if (j < LARGEUR - 1) {
            System.out.print("╦");
        } else {
            System.out.print("╗");
        }
    }
    System.out.println();

    for (int i = 0; i < HAUTEUR; i++) {
        // Imprimer l'indice de ligne (chiffre 1-11), ajuster l'espacement pour les nombres à deux chiffres
        System.out.print((i + 1) + " ".repeat(Math.max(0, 3 - (int)(Math.log10(i + 1) + 1))) + "║");

        for (int j = 0; j < LARGEUR; j++) {
            // Modifier ici pour afficher le contenu de 'plateau[i][j]'
            System.out.print(plateau[i][j] + "║"); // Espace ajouté pour alignement
        }
        System.out.println(); // Nouvelle ligne après chaque ligne du plateau

        // Bordure inférieure ou intermédiaire
        if (i < HAUTEUR - 1) {
            System.out.print("   ╠");
            for (int j = 0; j < LARGEUR; j++) {
                System.out.print("═══");
                if (j < LARGEUR - 1) {
                    System.out.print("╬");
                } else {
                    System.out.print("╣");
                }
            }
            System.out.println();
        }
    }

    // Bordure inférieure finale
    System.out.print("   ╚");
    for (int j = 0; j < LARGEUR; j++) {
        System.out.print("═══");
        if (j < LARGEUR - 1) {
            System.out.print("╩");
        } else {
            System.out.print("╝");
        }
    }
    System.out.println();
    }

    public static String[][] recuperePlateau() {
        return plateau;
    }
}