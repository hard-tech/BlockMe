package classes;
import classes.Plateau;

public class Deplacement {
    /**
     * La fonction "seDeplacer" permet de déplacer un joueur dans une direction tout en verrifiant s'il peut.
     */
    public void seDeplacer(Joueur joueur, Joueur[] joueurs, String direction) {
        // Si la direction est vers le haut ("z")
        if (direction.equals("z")) {
            // Vérifie si le joueur peut monter (ligne supérieure à 0)
            if (joueur.ligne > 0) {
                joueur.ligne--; // Décrémente la ligne du joueur
            }
        }
        // Si la direction est vers le bas ("s")
        else if (direction.equals("s")) {
            // Vérifie si le joueur peut descendre (ligne inférieure à HAUTEUR - 1)
            if (joueur.ligne < Plateau.HAUTEUR - 1) {
                joueur.ligne++; // Incrémente la ligne du joueur
            }
        } 
        // Si la direction est vers la gauche ("q")
        else if (direction.equals("q")) {
            // Vérifie si le joueur peut aller à gauche (colonne supérieure à 0)
            if (joueur.colonne > 0) {
                joueur.colonne--; // Décrémente la colonne du joueur
            }
        } 
        // Si la direction est vers la droite ("d")
        else if (direction.equals("d")) {
            // Vérifie si le joueur peut aller à droite (colonne inférieure à LARGEUR - 1)
            if (joueur.colonne < Plateau.LARGEUR - 1) {
                joueur.colonne++; // Incrémente la colonne du joueur
            }
        }

        // Si la direction n'est pas valide
        else{
            System.out.println("Tu peux pas aller là débilos ! Relis les règles ou casse toi !"); // Affiche un message d'erreur
        }
    }
}
