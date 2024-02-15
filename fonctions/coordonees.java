package fonctions;

import classes.Joueur;

public class coordonees {
    public static int[][] recupererCoordonnees(Joueur[] joueurs) {
        int[][] coordonneesJoueurs = new int[joueurs.length][2];

        // pour chaque joueur stocker les coordonnées dans le tableau coordonnéesJoueurs
        for (int i = 0; i < joueurs.length; i++) {
            coordonneesJoueurs[i][0] = joueurs[i].ligne;
            coordonneesJoueurs[i][1] = joueurs[i].colonne;
        }

        return coordonneesJoueurs;
    }
}
