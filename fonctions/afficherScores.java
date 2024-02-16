package fonctions;

import java.io.*;
import java.util.Arrays;

import classes.Joueur;

/**
 * La classe 'afficherScores' fournit des fonctionnalités pour trier et afficher les scores des joueurs.
 */
public class afficherScores {

    /**
     * Trie un tableau de joueurs selon leur score, dans l'ordre croissant ou décroissant.
     *
     * @param tab             Le tableau de joueurs à trier.
     * @param ordreCroissant  Indique si le tri doit être effectué dans l'ordre croissant (true) ou décroissant (false).
     * @return                Le tableau de joueurs trié.
     */
    public static Joueur[] trie(Joueur[] tab, boolean ordreCroissant) {
        if (tab.length == 1) {
            return tab;
        } else {
            for (int i = 1; i < tab.length; i++) {
                int j = i;
                Joueur tempo = tab[i];
                if (ordreCroissant) {
                    while ((j > 0) && (tempo.score < tab[j - 1].score)) {
                        tab[j] = tab[j - 1];
                        j = j - 1;
                    }
                } else {
                    while ((j > 0) && (tempo.score > tab[j - 1].score)) {
                        tab[j] = tab[j - 1];
                        j = j - 1;
                    }
                }
                tab[j] = tempo;
            }
            return tab;
        }
    }

    /**
     * Lit les scores des joueurs à partir d'un fichier sérialisé et les affiche dans l'ordre spécifié.
     *
     * @param ordreCroissant  Indique si les scores doivent être affichés dans l'ordre croissant (true) ou décroissant (false).
     */
    public static void lireScores(boolean ordreCroissant) {
        File fichier = new File("objets.ser");
        if (fichier.exists()) {
            Joueur[] joueurs = Joueur.chargerJoueur();
            trie(joueurs, ordreCroissant);
            for (int i = 0; i < joueurs.length; i++) {
                System.out.println(joueurs[i].nom);
                System.out.println(joueurs[i].score);
                System.out.println("\n");
            }
        } else {
            System.out.println("Aucun score n'a été enregistré");
        }
    }
}
