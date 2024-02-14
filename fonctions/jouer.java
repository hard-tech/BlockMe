package fonctions;

import java.util.Scanner;

import java.util.ArrayList;
import java.util.Arrays;

import classes.*;

public class jouer {
    public static void jouer(Scanner entre) {


        String nombreDeJoueursVoulu = "";
        int nombreDeJoueurs = 0;
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

                } else {
                    System.out.println("Nombre de joueurs invalide !");
                }

                
                Plateau.initialisationPlateauDeJeu(nombreDeJoueurs); // initialisation du plateau de jeu
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
                // Clear le terminal
                // System.out.print("\033[H\033[2J");
    }
}
