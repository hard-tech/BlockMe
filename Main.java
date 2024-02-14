import java.util.Scanner;

import fonctions.*;
import classes.*;

public class Main {
    public static void main(String[] args) {

        // Créer un Scanner pour la saisie utilisateur
        Scanner entre = new Scanner(System.in);

        String nombreDeJoueursVoulu = "";
        int nombreDeJoueurs = 0;
        String[] nomActuelJoueur;
        String choix = ""; // Initialise la variable de choix à une chaîne vide

        Joueur[] joueurs;

        // Clear le terminal
        System.out.print("\033[H\033[2J");

        // Boucle principale du programme
        while (!choix.equals("4")) {

            // Afficher le titre du jeu
            System.out.print("" +
                    "\n" +
                    "  ____  _            _    __  __      \n" +
                    " | __ )| | ___   ___| | _|  \\/  | ___ \n" +
                    " |  _ \\| |/ _ \\ / __| |/ / |\\/| |/ _ \\\n" +
                    " | |_) | | (_) | (__|   <| |  | |  __/\n" +
                    " |____/|_|\\___/ \\___|_|\\_\\_|  |_|\\___|\n" +
                    "            ||          ||             \n"
            );

            // Afficher le menu principal
            System.out.print("" +
                    "            ||          ||     \n" +
                    "        ====================== \n" +
                    "        ||                  || \n" +
                    "        ||    1 - Jouer     || \n" +
                    "        ||    2 - Règles    || \n" +
                    "        ||    3 - Scores    || \n" +
                    "        ||    4 - Quitter   || \n" +
                    "        ||                  || \n" +
                    "        ====================== \n"
            );

            // Tant que l'utilisateur ne saisit pas une option valide, demander à nouveau
            while (!(verificateur.verifChiffreEnEntre(1,4,choix))) {
                choix = entre.next(); // Lire l'entrée utilisateur

                // Si l'option saisie n'est pas valide, afficher un message d'erreur
                if (!(verificateur.verifChiffreEnEntre(1,4,choix))) {
                    System.out.println("\nVeuillez saisir une option valide : (1 - Jouer, 2 - Règles, 3 - Scores, 4 - Quitter) :");
                }
            }
            
            // Clear le terminal
            System.out.print("\033[H\033[2J");

            if (choix.equals("2")) { // Si l'utilisateur saisit les règles
                Regles.afficherRegles(); // Afficher les règles

            }

            if (choix.equals("1")) { // Si l'utilisateur saisit le jeu

                /**
                 * Initialisation du plateau des jouers
                */
                System.out.println("Veuillez saisir un nombre entre 2 et 4 : ");
                nombreDeJoueursVoulu = entre.next(); // Lire l'entrée utilisateur
                if (verificateur.verifChiffreEnEntre(2, 4, nombreDeJoueursVoulu)) {
                    nombreDeJoueurs = Integer.parseInt(nombreDeJoueursVoulu);
                    joueurs = new Joueur[nombreDeJoueurs]; // Initialisation du tableau de joueurs avec la taille appropriée
                    nomActuelJoueur = new String[nombreDeJoueurs];
                    
                    //pour chaque joueur creer un joueur avec un nom different des autres
                    for (int i = 0; i < nombreDeJoueurs; i++) {
                        joueurs[i] = new Joueur(); // Initialisation de chaque joueur
                        joueurs[i].nom = Joueur.choisirNomPotentiel(); // Choisir le nom du joueur
                        
                        //si le nom du joeur est dans nomActuelJoueur prendre un nouveau nom
                        if(nomActuelJoueur.contains(joueurs[i].nom)){
                            
                        }
                        System.out.println(joueurs[i].nom);
                    }

                } else {
                    System.out.println("Nombre de joueurs invalide !");
                }

                
                Plateau.initialisationPlateauDeJeu(); // initialisation du plateau de jeu
                Plateau.afficherPlateau();

                System.out.println(nombreDeJoueurs);

                switch (nombreDeJoueurs) {
                    case 2:
                        // Jeu avec 2 joueurs  
                        System.out.println("Jeu avec 2 joueurs");
                        break;
                    case 3:
                        // Jeu avec 3 joueurs 
                        System.out.println("Jeu avec 3 joueurs");
                        break;
                    case 4:
                        // Jeu avec 4 joueurs 
                        System.out.println("Jeu avec 4 joueurs");
                        break;
                    default:
                        System.out.println("\n Nombre de joueurs invalide ! Nous ne pouvons pas lancer la partie!");
                }
                
                nombreDeJoueurs = 0; //definir la variable de nombre de joueurs voulu à une chaîne vide
                choix = ""; // Réinitialise la variable de choix à une chaîne vide (permet de revenir au menu principal)
                // Clear le terminal
                // System.out.print("\033[H\033[2J");
            }

            if (choix.equals("2")) { // Si l'utilisateur saisit les règles
                Regles.afficherRegles(); // Afficher les règles
                choix = ""; // Réinitialise la variable de choix à une chaîne vide
            }

            if (choix.equals("3")) { // Si l'utilisateur saisit les règles
                Scores.afficherScores(); // Afficher les scores
                choix = ""; // Réinitialise la variable de choix à une chaîne vide
            }
        }
        entre.close(); // fermmer le scanner
    }
}