package fonctions;

import classes.Joueur;

/**
 * La classe 'coordonees' fournit des fonctionnalités pour récupérer les coordonnées des joueurs.
 */
public class coordonees {

    /**
     * Récupère les coordonnées des joueurs spécifiés.
     *
     * @param joueurs  Le tableau des joueurs dont les coordonnées doivent être récupérées.
     * @return         Un tableau 2D contenant les coordonnées (ligne, colonne) des joueurs.
     */
    public static int[][] recupererCoordonnees(Joueur[] joueurs) {
        int[][] coordonneesJoueurs = new int[joueurs.length][2];

        // Pour chaque joueur, stocker les coordonnées dans le tableau coordonneesJoueurs
        for (int i = 0; i < joueurs.length; i++) {
            coordonneesJoueurs[i][0] = joueurs[i].ligne;
            coordonneesJoueurs[i][1] = joueurs[i].colonne;
        }

        return coordonneesJoueurs;
    }
}
