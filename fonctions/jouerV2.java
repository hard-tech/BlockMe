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
        
        String[] 
            NOMS_COLONNE = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"}, // Déclaration d'une constante de type STRING (qui nous permettra de vérifier si les coorrodonners donnes sont valide)
            NOMS_LIGNE = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}, // Déclaration d'une constante de type STRING (qui nous permettra de vérifier si les coorrodonners donnes sont valide)
            nomDesJoueurs; // initialiser les variables de type String[] pour le fonctionnement du jeu
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
        
        Plateau.initialisationPlateauDeJeu(nombreDeJoueurs, joueurs); // Initialiser le plateau de jeu
        nombreDeJoueursEnVie = joueurs.length; // Initialiser le nombre de joueurs en vie
        
        while (nombreDeJoueursEnVie > 1) {
            for (int i = 0; i < joueurs.length; i++) {    
                // Affichage des elements du jeu (de façon récurante)
                    System.out.print("\033[H\033[2J"); // Clear le terminal
                    Plateau.afficherPlateau(); // Afficher le plateau de jeu

                for (Joueur joueur : joueurs) {
                    if(deplacerJoueur.verifDeplacementBloquer(joueurs, Plateau.plateau, joueur)){ joueur.enVie = false; } // virification de la posibilité de déplacement du joueurs (si non il est mort)
                }
                
                nombreDeJoueursEnVie = 0; // remise à 0 du nombre de joueurs en vie pour le comptage
                for (Joueur joueur : joueurs) { 
                    if(joueur.enVie) { nombreDeJoueursEnVie++; } 
                }// Mise à jours du nombre de joeur en vie
                
                System.out.println("alive : " + nombreDeJoueursEnVie);
                for (Joueur joueur : joueurs) {
                    System.out.println(joueur.nom);
                    System.out.println(joueur.enVie);
                }
                
                // affichage du tableau de noms des joueurs
                    System.out.println("\n\n");
                    System.out.println("   ╔════════════════════════════════════════════════");
                    for (int x = 0; x < joueurs.length; x++) { // Gérer Le tableau contenant le noms des joueurs
                      System.out.println("   ║  Joueur " + (x + 1) + " ── " + joueurs[x].nom + (joueurs[x].enVie ? "" : " est mort au combat ... looser")); // Afficher le nom du joueur
                    }
                    System.out.println("   ╚════════════════════════════════════════════════ \n\n");

                if(joueurs[i].enVie){ tourJoueur.tourJoueur(joueurs, i, entre); } // Lancer DU joueur


            }
        }
    }
}
