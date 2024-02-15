package fonctions;

import java.util.Scanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import classes.*;
import fonctions.*;

public class jouer {
    public static void jouer(Scanner entre) {


        String nombreDeJoueursVoulu = "";
        // liste des différent nom de ligne (de A à K)
        String[] nomsDeColonne = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
        // liste des différent nom de colonne (de A à K)
        String[] nomsDeLigne = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        int nombreDeJoueurs = 0;
        int[] destinationCaseDetruite = new int[2];
        String[] nomActuelJoueur;

        Joueur[] joueurs;
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
            // Boucle pour chaque joueur
            for (int i = 0; i < nombreDeJoueurs; i++) {
                joueurs[i] = new Joueur(); // Initialisation de chaque joueur
                joueurs[i].nom = Joueur.choisirNomPotentiel(); // Choisir le nom du joueur
                
                // Conversion du tableau de noms en une liste pour la recherche d'occurrence
                ArrayList<String> nomsListe = new ArrayList<>(Arrays.asList(nomActuelJoueur));
                
                // Si le nom du joueur est déjà pris, choisir un nouveau nom
                while (nomsListe.contains(joueurs[i].nom)) {
                    joueurs[i].nom = Joueur.choisirNomPotentiel();
                }
                
                // Ajouter le nom du joueur à la liste
                nomActuelJoueur[i] = joueurs[i].nom;
                
                System.out.println(joueurs[i].nom);
            }
            // Melanger le tableau joueurs
            Random rand = new Random();

            for (int i = nombreDeJoueurs - 1; i > 0; i--) {
                // Générer un index aléatoire entre 0 et i inclusivement
                int j = rand.nextInt(i + 1);

                // Échanger les éléments à l'index i et j
                Joueur temp = joueurs[i];
                joueurs[i] = joueurs[j];
                joueurs[j] = temp;
            }

            Plateau.initialisationPlateauDeJeu(nombreDeJoueurs, joueurs); // initialisation du plateau de jeu
            Plateau.afficherPlateau();

                for (int i = 0; i < nombreDeJoueurs; i++) {

                    String directionDeplacement = "";
                    boolean caseDetruite = false;
                    while (!directionDeplacement.equals("z") && !directionDeplacement.equals("s") && !directionDeplacement.equals("q") && !directionDeplacement.equals("d")){
                        System.out.println("Dans quelle direction voulez vous vous déplacer ? :");
                        directionDeplacement = entre.nextLine(); // Lire l'entrée utilisateur
                    }

                    // Clear le terminal
                    System.out.print("\033[H\033[2J");

                    // Nettoyage des coordonnées du plateau de jeu (avant déplacement)
                    Plateau.nettoyageCasePrecedente(joueurs);

                    // déplacer le joueur
                    deplacerJoueur.seDeplacer(joueurs[i], joueurs, directionDeplacement, Plateau.recuperePlateau());

                    //mise à jour de la plateau de jeu
                    Plateau.miseAJourPlateauDeJeu(joueurs);

                    //afficher le plateau de jeu
                    Plateau.afficherPlateau();
                    while (!caseDetruite) {
                        String positionCase1 = "";
                        boolean choixCase1 = false;
                        //tant que positionCase1 n'est pas present dans nomsDeColonne demander sur quelle ligne se trouve la case que le joueur veux detruire ?
                        while (!choixCase1) {
                            System.out.println("Dans quelle colonne se trouve la case que vous voulez détruire ? :");
                            positionCase1 = entre.nextLine(); // Lire l'entrée utilisateur
                            for (int j = 0; j < nomsDeColonne.length; j++) {
                                if (nomsDeColonne[j].equals(positionCase1)) {// Si le nom de la case est dans le tableau nomsDeColonne, le joueur choisit la colonne avec la cases a detruire
                                    destinationCaseDetruite[1] = j;
                                    choixCase1 = true;
                                }
                            }
                        }
                        String positionCase0 = "";
                        boolean choixCase0 = false;
                        //tant que positionCase0 n'est pas present dans nomsDeLigne demander sur quelle ligne se trouve la case que le joueur veux detruire ?
                        while (!choixCase0) {
                            System.out.println("Dans quelle ligne se trouve la case que vous voulez détruire ? :");
                            positionCase0 = entre.nextLine(); // Lire l'entrée utilisateur
                            for (int j = 0; j < nomsDeLigne.length; j++) {
                                if (nomsDeLigne[j].equals(positionCase0)) {// Si le nom de la case est dans le tableau nomsDeLigne, le joueur choisit la ligne avec la cases a detruire
                                    destinationCaseDetruite[0] = j;
                                    choixCase0 = true;
                                }
                            }
                        }
                        caseDetruite = Plateau.detruireCase(destinationCaseDetruite); // détruire la case de destination
                        if (!caseDetruite) {
                            System.out.println("La case ne peut pas être détruite");
                        }
                    }

                    System.out.print("\033[H\033[2J");

                    Plateau.afficherPlateau(); // afficher le plateau de jeu
                }

            // Clear le terminal
            System.out.print("\033[H\033[2J");
            
            Plateau.miseAJourPlateauDeJeu(joueurs);

            Plateau.afficherPlateau();

        } else {
            System.out.println("Nombre de joueurs invalide !");
        }

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
        // Clear le terminal
        // System.out.print("\033[H\033[2J");
    }
}
