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

    /**
     * La fonction "afficherPlateau" imprime un plateau de jeu avec une bordure à double ligne.
     */
    public static void afficherPlateau() {
        // Imprimer la bordure supérieure double
        System.out.print("╔");
        for (int j = 0; j < LARGEUR; j++) {
            System.out.print("═══");
            if (j < LARGEUR - 1) {
                System.out.print("╦"); // Jonction supérieure double
            } else {
                System.out.print("╗"); // Coin supérieur droit double
            }
        }
        System.out.println();
    
        for (int i = 0; i < HAUTEUR; i++) {
            System.out.print("║"); // Bordure latérale gauche double
            for (int j = 0; j < LARGEUR; j++) {
                System.out.print(plateau[i][j] + "║");
            }
            System.out.println(); // Passer à la ligne suivante
    
            // Imprimer la séparation entre les lignes avec une double ligne ou préparer la bordure inférieure
            if (i < HAUTEUR - 1) {
                System.out.print("╠"); // Jonction latérale gauche double
                for (int j = 0; j < LARGEUR; j++) {
                    System.out.print("═══");
                    if (j < LARGEUR - 1) {
                        System.out.print("╬"); // Jonction centrale double
                    } else {
                        System.out.print("╣"); // Jonction latérale droite double
                    }
                }
                System.out.println();
            }
        }
        
        // Imprimer la bordure inférieure double
        System.out.print("╚");
        for (int j = 0; j < LARGEUR; j++) {
            System.out.print("═══");
            if (j < LARGEUR - 1) {
                System.out.print("╩"); // Jonction inférieure double
            } else {
                System.out.print("╝"); // Coin inférieur droit double
            }
        }
        System.out.println();
    }
}