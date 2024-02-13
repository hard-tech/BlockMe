import java.util.Scanner;
import out.production.BlockMe.fonctions.regles;

public class Main {
    public static void main(String[] args) {

        // Créer un Scanner pour la saisie utilisateur
        Scanner entre = new Scanner(System.in);

        // Clear le terminal
        System.out.print("\033[H\033[2J");

        // Boucle principale du programme
        while (true) {
            String choix = ""; // Initialise la variable de choix à une chaîne vide

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
            while (!(choix.equals("1") || choix.equals("2") || choix.equals("3") || choix.equals("4"))) {
                choix = entre.next(); // Lire l'entrée utilisateur

                // Si l'option saisie n'est pas valide, afficher un message d'erreur
                if (!(choix.equals("1") || choix.equals("2") || choix.equals("3") || choix.equals("4"))) {
                    System.out.println("Veuillez saisir une option valide : (1 - Jouer, 2 - Règles, 3 - Scores, 4 - Quitter)");
                }
            }
            
            // Clear le terminal
            System.out.print("\033[H\033[2J");

            if (choix.equals("2")) { // Si l'utilisateur saisit les règles
                regles.afficherRegles(); // Afficher les règles

            }

        }
    }
}