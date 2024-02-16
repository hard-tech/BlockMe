package fonctions;

import classes.Joueur;
import classes.Plateau;

/**
 * La classe 'deplacerJoueur' fournit des fonctionnalités pour déplacer un joueur sur le plateau de jeu.
 */
public class deplacerJoueur {

    /**
     * Déplace un joueur dans une direction spécifiée sur le plateau de jeu.
     *
     * @param joueur              Le joueur à déplacer.
     * @param joueurs             Le tableau des joueurs.
     * @param direction           La direction du déplacement ("z" pour haut, "s" pour bas, "q" pour gauche, "d" pour droite, "exit" pour quitter, "regles" ou "règles" pour afficher les règles).
     * @param plateau             Le plateau de jeu.
     * @param nombreDeJoueursEnVie  Le nombre de joueurs encore en vie.
     * @return                    Vrai si le déplacement a été effectué avec succès, faux sinon.
     */
    public static boolean seDeplacer(Joueur joueur, Joueur[] joueurs, String direction, String[][] plateau, int nombreDeJoueursEnVie) {
        int nouvelleLigne = joueur.ligne;
        int nouvelleColonne = joueur.colonne;

        if (direction.equals("z")) {
            nouvelleLigne--;
        } else if (direction.equals("s")) {
            nouvelleLigne++;
        } else if (direction.equals("q")) {
            nouvelleColonne--;
        } else if (direction.equals("d")) {
            nouvelleColonne++;
        } else if (direction.equals("exit")) {
            nombreDeJoueursEnVie = 0;
        } else if (direction.equals("regles") || direction.equals("règles")) {
            return false;
        } else {
            System.out.println("Direction non valide !");
            return false;
        }

        if (nouvelleLigne >= 0 && nouvelleLigne < Plateau.HAUTEUR &&
            nouvelleColonne >= 0 && nouvelleColonne < Plateau.LARGEUR &&
            plateau[nouvelleLigne][nouvelleColonne].equals("   ")) {
            boolean caseOccupee = false;
            for (int[] coJoueur : coordonees.recupererCoordonnees(joueurs)) {
                if(coJoueur[0] == nouvelleLigne && coJoueur[1] == nouvelleColonne){
                    caseOccupee = true;
                }
            }
            if (!caseOccupee) {
                joueur.ligne = nouvelleLigne;
                joueur.colonne = nouvelleColonne;
                return true;
            } else {
                System.out.println("Il y a déjà un joueur sur cette case tocard !");
                return false;
            }
        } else {
            System.out.println("Déplacement impossible ! Tu sors de la route alcoolique !");
            return false;
        }
    }
    
    /**
     * Vérifie si un joueur est bloqué et ne peut pas se déplacer.
     *
     * @param joueurs   Le tableau des joueurs.
     * @param plateau   Le plateau de jeu.
     * @param joueur    Le joueur à vérifier.
     * @return          Vrai si le joueur est bloqué, faux sinon.
     */
    public static boolean verifDeplacementBloquer(Joueur[] joueurs, String[][] plateau, Joueur joueur) {
        int[][] deplacements = {
            {-1, 0}, // haut
            {1, 0},  // bas
            {0, -1}, // gauche
            {0, 1}   // droite
        };
    
        for (int[] dep : deplacements) {
            int nouvelleLigne = joueur.ligne + dep[0];
            int nouvelleColonne = joueur.colonne + dep[1];
    
            if (nouvelleLigne >= 0 && nouvelleLigne < Plateau.HAUTEUR &&
                nouvelleColonne >= 0 && nouvelleColonne < Plateau.LARGEUR &&
                plateau[nouvelleLigne][nouvelleColonne].equals("   ")) {
    
                boolean caseOccupee = false;
                for (Joueur autreJoueur : joueurs) {
                    if (autreJoueur != joueur && autreJoueur.ligne == nouvelleLigne && autreJoueur.colonne == nouvelleColonne) {
                        caseOccupee = true;
                        break;
                    }
                }
                if (!caseOccupee) {
                    return false;
                }
            }
        }
    
        return true;
    }
}
