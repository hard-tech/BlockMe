package fonctions;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import classes.Plateau;
import classes.Joueur;

/**
 * La classe jouer gère le déroulement d'un jeu de plateau. 
 * Elle permet de configurer le jeu, de gérer les tours des joueurs, 
 * et de mettre à jour le plateau de jeu après chaque action.
 * Les joueurs peuvent se déplacer sur le plateau et détruire des cases 
 * selon les règles définies.
 */

public class jouerV2 {
        /**
     * Lance une session de jeu en demandant aux utilisateurs le nombre de joueurs,
     * en initialisant le plateau de jeu, en gérant les tours des joueurs,
     * et en permettant aux joueurs de se déplacer et de détruire des cases sur le plateau.
     * La méthode gère également l'affichage du plateau et des messages à l'utilisateur.
     * 
     * @param entre L'objet Scanner utilisé pour lire les entrées de l'utilisateur.
     */


    public static void jouer(Scanner entre) {
        
        String[] NOMS_COLONNE = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"}, NOMS_LIGNE = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}, nomDesJoueurs; // initialiser les variables de type String[] pour le fonctionnement du jeu
        String nombreDeJoueursVoulu = ""; // initialiser la variable de nombre de joueurs voulu par le joueur (avant vérification du contenue de la variable)
        
        int nombreDeJoueurs = 0, nombreDeJoueursEnVie; // initialiser les variables de nombre de joueurs et nombre de joueurs en vie
        int[] coordoneesCaseADetruire = new int[2]; // initialiser la variables de coordonnées de la case détruite par la joueur

        Joueur[] joueurs; // initialiser le tableau de joueurs

        do { // Demmander les entrées de l'utilisateur (combien de joueurs veules jouer)
            System.out.println("\n----- Veuillez saisir un nombre entre 2 et 4 -----\n");
            nombreDeJoueursVoulu = entre.next(); // Lire l'entrée utilisateur

            if (verificateur.verifChiffreEnEntre(2, 4, nombreDeJoueursVoulu)) {
                nombreDeJoueurs = Integer.parseInt(nombreDeJoueursVoulu);
            } else {
                System.out.print("\033[H\033[2J"); // Clear le terminal
                System.out.println("\n[---- Nombre de joueurs invalide ! ----] \n\n"); // Afficher le message d'erreur
            }
        }while(nombreDeJoueurs < 2 || nombreDeJoueurs > 4);

        // Générer les joueurs
            joueurs = new Joueur[nombreDeJoueurs]; // Instancier un tableau contenant les joueurs
            nomDesJoueurs = new String[nombreDeJoueurs]; // Instancier un tableau contenant les noms des joueurs
            generationJoueurs.generationJoueurs(nomDesJoueurs, nombreDeJoueurs, joueurs); // Générer les joueurs
        //
        
        Plateau.initialisationPlateauDeJeu(nombreDeJoueurs, joueurs); // Initialiser le plateau de jeu
        
        // Affichage des elements du jeu (de façon récurante)
            System.out.print("\033[H\033[2J"); // Clear le terminal

            Plateau.afficherPlateau(); // Afficher le plateau de jeu

            // affichage du tableau de noms des joueurs
                System.out.println("\n");
                System.out.println("   ╔════════════════════════");
                for (int i = 0; i < joueurs.length; i++) { // Gérer Le tableau contenant le noms des joueurs
                    System.out.println("   ║  Joueur " + (i + 1) + " ── " + joueurs[i].nom); // Afficher le nom du joueur
                }
                System.out.println("   ╚════════════════════════");
        //
    }
}
