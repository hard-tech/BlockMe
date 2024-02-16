package fonctions;

import classes.Joueur;
import classes.Plateau;

/**
 * affichageJeu
 */
public class affichageJeu {

    public static void afficher(Joueur[] joueurs, int nombreDeJoueursEnVie){
        // Affichage des elements du jeu (de façon récurante)
                    System.out.print("\033[H\033[2J"); // Clear le terminal
                    Plateau.afficherPlateau(); // Afficher le plateau de jeu
                
                // affichage du tableau de noms des joueurs
                    System.out.println("\n\n");
                    System.out.println("                                                       ╔═╗");
                    System.out.println("   ╔═══════════════════════════════════════════════════╝ ║");
                    System.out.println("   ║ ╔═══════════════════════════════════════════════════╝");
                    System.out.println("   ║ ║                                              ");
                    for (int x = 0; x < joueurs.length; x++) { // Gérer Le tableau contenant le noms des joueurs
                      System.out.println("   ║ ║   Joueur " + (x + 1) + " ── " + joueurs[x].nom + (joueurs[x].enVie ? "" : " est mort au combat ... looser")); // Afficher le nom du joueur
                    }
                    System.out.println("   ║ ║                                                   ");
                    System.out.println("   ║ ╚═══════════════════════════════════════════════════╗");
                    System.out.println("   ╚═══════════════════════════════════════════════════╗ ║");
                    System.out.println("                                                       ╚═╝\n\n");
    }
}