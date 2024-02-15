package fonctions;

import java.util.Scanner;

import classes.Joueur;
import classes.Plateau;

public class tourJoueur {
    public static void tourJoueur(Joueur[] joueurs, int joueurIndex,Scanner entre) {

        String directionDeplacement = "";
        boolean caseDetruite = false;

        System.out.println("Dans quelle direction voulez vous vous déplacer ? :"); // Demmander à l'utilisateur où veut'il se déplacer (dans qu'elle dirrection 'z', 'q', 's', 'd')
        directionDeplacement = entre.nextLine().toLowerCase(); // Lire l'entrée utilisateur

        do{ // redemmander à l'utilisateur vers qu'elle dirrection veut'il se déplacer temps que la dirrection est interdite ou invalide
            Plateau.nettoyageCasePrecedente(joueurs); // Nettoyage des coordonnées du plateau de jeu (avant déplacement)
            
            System.out.println("Dans quelle direction voulez vous vous déplacer '"+ joueurs[joueurIndex].nom +"'' ? :");
            directionDeplacement = entre.nextLine().toLowerCase(); // Lire l'entrée utilisateur

        }while (!deplacerJoueur.seDeplacer(joueurs[joueurIndex], joueurs, directionDeplacement, Plateau.recuperePlateau()));
    }
}
