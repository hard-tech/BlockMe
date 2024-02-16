package fonctions;

import java.util.Scanner;
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

        boolean exit = false; // Initialiser la variable de type boolean pour le fonctionnement de la
        boolean voirRegle = false; // Initialiser la variable de type boolean pour le fonctionnement de la

        do { // Demmander les entrées de l'utilisateur (combien de joueurs veules jouer)
            System.out.println("\n----- Veuillez saisir le nombre de joueur (entre 2 et 4) -----\n");
            nombreDeJoueursVoulu = entre.next(); // Lire l'entrée utilisateur

            if (verificateur.verifChiffreEnEntre(2, 4, nombreDeJoueursVoulu)) {
                nombreDeJoueurs = Integer.parseInt(nombreDeJoueursVoulu);
            } else {
                System.out.print("\033[H\033[2J"); // Clear le terminal
                System.out.println("\n[---- Nombre de joueurs invalide ! On t'a dit entre 2 et 4 trou de balles ----] \n\n"); // Afficher le message d'erreur
            }
        }while(nombreDeJoueurs < 2 || nombreDeJoueurs > 4);

        // Générer les joueurs
            joueurs = new Joueur[nombreDeJoueurs]; // Instancier un tableau contenant les joueurs
            nomDesJoueurs = new String[nombreDeJoueurs]; // Instancier un tableau contenant les noms des joueurs
            Joueur[] joueurExistant = generationJoueurs.generationJoueurs(nomDesJoueurs, nombreDeJoueurs, joueurs); // Générer les joueurs
        
        Plateau.initialisationPlateauDeJeu(nombreDeJoueurs, joueurs); // Initialiser le plateau de jeu
        nombreDeJoueursEnVie = joueurs.length; // Initialiser le nombre de joueurs en vie

        entre.nextLine(); // Ceci consomme et ignore l'\n restant dans le tampon donc ont l'utilise
        
        while (nombreDeJoueursEnVie != 1 && nombreDeJoueursEnVie != 0) {
            for (int i = 0; i < joueurs.length; i++) {    

                for (Joueur joueur : joueurs) {
                    if(deplacerJoueur.verifDeplacementBloquer(joueurs, Plateau.plateau, joueur)){ joueur.enVie = false; } // virification de la posibilité de déplacement du joueurs (si non il est mort)
                }
                
                nombreDeJoueursEnVie = 0; // remise à 0 du nombre de joueurs en vie pour le comptage

                for (Joueur joueur : joueurs) {
                    if(joueur.ligne == 0 && joueur.colonne == 10) {
                        int indexAleatoire = (int) (Math.random() * joueurs.length - 1);
                        joueurs[indexAleatoire].enVie = false; //
                    }
                }

                for (Joueur joueur : joueurs) { 
                    if(joueur.enVie) { nombreDeJoueursEnVie++; } 
                }// Mise à jours du nombre de joeur en vie

                
                affichageJeu.afficher(joueurs, nombreDeJoueursEnVie, voirRegle); // Afficher tout le content du plateau de jeu et information
                if(joueurs[i].enVie){ 
                    if(nombreDeJoueursEnVie == 1 || nombreDeJoueursEnVie == 0) {
                        break;
                    }

                    tourJoueur.tourJoueur(joueurs, i, entre, nombreDeJoueursEnVie); 
                } // Lancer DU joueur
            }
            if(nombreDeJoueursEnVie == 1 || nombreDeJoueursEnVie == 0) {
                break;
            }
        }

        if(nombreDeJoueursEnVie == 1 || nombreDeJoueursEnVie == 0) {
            try {
                // Afficher le message de fin de jeu
                Joueur.sauvegarderJoueur(joueurExistant, "objets.ser"); // sauvegarde des scores dans le fichier
                System.out.println("" +
                    " ____                         _                                                 __                   _       _   _                                  \n" +
                    "| __ ) _ __ __ ___   _____   | |_ _   _    __ _ ___    __ _  __ _  __ _ _ __   /_/   _ __ ___   __ _(_)___  | |_( ) ___  ___    __ _ _   _  ___ ____\n" +
                    "|  _ \\| '__/ _` \\ \\ / / _ \\  | __| | | |  / _` / __|  / _` |/ _` |/ _` | '_ \\ / _ \\ | '_ ` _ \\ / _` | / __| | __|/ / _ \\/ __|  / _` | | | |/ _ \\_  /\n" +
                    "| |_) | | | (_| |\\ V / (_) | | |_| |_| | | (_| \\__ \\ | (_| | (_| | (_| | | | |  __/ | | | | | | (_| | \\__ \\ | |_  |  __/\\__ \\ | (_| | |_| |  __// / \n" +
                    "|____/|_|  \\__,_| \\_/ \\___/   \\__|\\__,_|  \\__,_|___/  \\__, |\\__,_|\\__, |_| |_|\\___| |_| |_| |_|\\__,_|_|___/  \\__|  \\___||___/  \\__, |\\__,_|\\___/___|\n" +
                    "                                                      |___/       |___/                                                        |___/                " +
                    "");
    
                Thread.sleep(5000);
                System.out.print("\033[H\033[2J"); // Clear le terminal
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
