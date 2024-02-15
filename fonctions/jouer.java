package fonctions;

import java.util.Scanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import classes.*;
import fonctions.*;

/**
 * La classe jouer gère le déroulement d'un jeu de plateau. 
 * Elle permet de configurer le jeu, de gérer les tours des joueurs, 
 * et de mettre à jour le plateau de jeu après chaque action.
 * Les joueurs peuvent se déplacer sur le plateau et détruire des cases 
 * selon les règles définies.
 */


public class jouer {

    /**
     * Lance une session de jeu en demandant aux utilisateurs le nombre de joueurs,
     * en initialisant le plateau de jeu, en gérant les tours des joueurs,
     * et en permettant aux joueurs de se déplacer et de détruire des cases sur le plateau.
     * La méthode gère également l'affichage du plateau et des messages à l'utilisateur.
     * 
     * @param entre L'objet Scanner utilisé pour lire les entrées de l'utilisateur.
     */


    public static void jouer(Scanner entre) {


        String nombreDeJoueursVoulu = "";
        // liste des différent nom de colonne (de A à K)
        String[] nomsColonne = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
        // liste des différent nom de ligne (de A à K)
        String[] nomsLigne = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        int nombreDeJoueurs = 0;
        int[] destinationCaseDetruite = new int[2];
        String[] nomActuelJoueur;

        Joueur[] joueurs;
        /**
         * Initialisation du plateau des jouers
        */
        System.out.println("Veuillez saisir un nombre entre 2 et 4 : ");
        nombreDeJoueursVoulu = entre.next(); // Lire l'entrée utilisateur
        entre.nextLine();
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


            for (int i = 0; i < nombreDeJoueurs; i++) {
                Plateau.afficherPlateau(joueurs[i]); // affichage du plateau de jeu

                String directionDeplacement = "";
                boolean caseDetruite = false;
                while (!directionDeplacement.equals("z") && !directionDeplacement.equals("s") && !directionDeplacement.equals("q") && !directionDeplacement.equals("d")){
                    System.out.println("Dans quelle direction voulez vous vous déplacer ? :");
                    directionDeplacement = entre.nextLine(); // Lire l'entrée utilisateur
                    directionDeplacement = directionDeplacement.toLowerCase(); // Convertir la direction en lower case
                }

                // Clear le terminal
                System.out.print("\033[H\033[2J");

                // Nettoyage des coordonnées du plateau de jeu (avant déplacement)
                Plateau.nettoyageCasePrecedente(joueurs);

                // déplacer le joueur
                deplacerJoueur.seDeplacer(joueurs[i], joueurs, directionDeplacement, Plateau.recuperePlateau());
                System.out.println(deplacerJoueur.verifDeplacement(joueurs, Plateau.recuperePlateau(), joueurs[i]));
                // if (deplacerJoueur.verifDeplacement(joueurs, Plateau.recuperePlateau(), joueurs[i])){
                //     System.out.println("================================================================= Bien executer =================================================================");
                // }
                // System.out.println("================================================================= Après IF executer =================================================================");

                //mise à jour de la plateau de jeu
                Plateau.miseAJourPlateauDeJeu(joueurs);

                //afficher le plateau de jeu
                Plateau.afficherPlateau(joueurs[i]);
                while (!caseDetruite) {
                    String positionCase = "";
                    boolean ligneValide = false;
                    boolean colonneValide = false;
                
                    while (!ligneValide || !colonneValide) {
                        do {
                            System.out.println("Dans quelle case voulez-vous détruire ? (Ex : B3) :");
                            positionCase = entre.nextLine().toUpperCase(); // Lire et convertir l'entrée utilisateur en majuscules
                    
                            // Corrige l'expression régulière pour qu'elle accepte les chiffres de 1 à 9 et 10 après les lettres de A à K
                        } while (!positionCase.matches("[ABCDEFGHIJK](10|[1-9])")); // Regex pour vérifier que la position est bien valide
                    
                        // Extraction de la colonne et de la ligne de façon à gérer correctement "10"
                        String colonneCase = String.valueOf(positionCase.charAt(0));
                        String ligneCase = positionCase.substring(1); // Maintenant cela fonctionne car l'expression régulière garantit une entrée valide
                    
                        // Vérification de la colonne
                        for (String nomColonne : nomsColonne) {
                            if (nomColonne.equals(colonneCase)) {
                                destinationCaseDetruite[1] = java.util.Arrays.asList(nomsColonne).indexOf(nomColonne);
                                colonneValide = true;
                                break;
                            }
                        }
                    
                        // Vérification de la ligne
                        for (String nomLigne : nomsLigne) {
                            if (nomLigne.equals(ligneCase)) {
                                destinationCaseDetruite[0] = java.util.Arrays.asList(nomsLigne).indexOf(nomLigne);
                                ligneValide = true;
                                break;
                            }
                        }
                    
                        if (!ligneValide || !colonneValide) {
                            System.out.println("Coordonnées invalides, veuillez réessayer.");
                        }
                    }
                    
                    // Supposons que Plateau.detruireCase est une méthode définie pour détruire la case
                    caseDetruite = Plateau.detruireCase(destinationCaseDetruite); // détruire la case de destination
                    if (!caseDetruite) {
                        System.out.println("La case ne peut pas être détruite, veuillez choisir une autre case.");
                    }
                }
                System.out.print("\033[H\033[2J");
            }

            // Clear le terminal
            System.out.print("\033[H\033[2J");
            
            Plateau.miseAJourPlateauDeJeu(joueurs);
            

        } else {
            System.out.println("Nombre de joueurs invalide !");
        }
        
        nombreDeJoueurs = 0; //definir la variable de nombre de joueurs voulu à une chaîne vide
        // Clear le terminal
        // System.out.print("\033[H\033[2J");
    }
}