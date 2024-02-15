package fonctions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import classes.Joueur;
import classes.Plateau;
import fonctions.coordonees;

public class deplacerJoueur {
    /**
     * La fonction "seDeplacer" permet de déplacer un joueur dans une direction tout en vérifiant s'il peut.
     */
    public static boolean seDeplacer(Joueur joueur, Joueur[] joueurs, String direction, String[][] plateau) {
        // Stocker les coordonnées du nouveau déplacement pour le joueur
        int nouvelleLigne = joueur.ligne;
        int nouvelleColonne = joueur.colonne;

        // Si la direction est vers le haut ("z")
        if (direction.equals("z")) {
            nouvelleLigne--; // Déplacer vers le haut
        }
        // Si la direction est vers le bas ("s")
        else if (direction.equals("s")) {
            nouvelleLigne++; // Déplacer vers le bas
        }
        // Si la direction est vers la gauche ("q")
        else if (direction.equals("q")) {
            nouvelleColonne--; // Déplacer vers la gauche
        }
        // Si la direction est vers la droite ("d")
        else if (direction.equals("d")) {
            nouvelleColonne++; // Déplacer vers la droite
        }
        // Si la direction n'est pas valide
        else {
            System.out.println("Direction non valide !");
            return false;
        }

        // Vérifier si les nouvelles coordonnées sont valides et si la case est vide
        if (nouvelleLigne >= 0 && nouvelleLigne < Plateau.HAUTEUR &&
            nouvelleColonne >= 0 && nouvelleColonne < Plateau.LARGEUR &&
            plateau[nouvelleLigne][nouvelleColonne].equals("   ")) {
            // Vérifier si un autre joueur occupe déjà la case
            boolean caseOccupee = false;
            for (int[] coJoueur : coordonees.recupererCoordonnees(joueurs)) {
                if(coJoueur[0] == nouvelleLigne && coJoueur[1] == nouvelleColonne){
                    caseOccupee = true;
                }
            }
            if (!caseOccupee) {
                // Déplacer le joueur
                joueur.ligne = nouvelleLigne;
                joueur.colonne = nouvelleColonne;
                return true;
            } else {
                System.out.println("Il y a déjà un joueur sur cette case !");
                return false;
            }
        } else {
            System.out.println("Déplacement impossible !");
            return false;
        }
    }
    
    public static boolean verifDeplacement(Joueur[] joueurs, String[][] plateau, Joueur joueur) {
        // Définir les déplacements possibles : haut, bas, gauche, droite
        int[][] deplacements = {
            {-1, 0}, // haut
            {1, 0},  // bas
            {0, -1}, // gauche
            {0, 1}   // droite
        };
    
        // Parcourir chaque déplacement possible
        for (int[] dep : deplacements) {
            int nouvelleLigne = joueur.ligne + dep[0];
            int nouvelleColonne = joueur.colonne + dep[1];
    
            // Vérifier si les nouvelles coordonnées sont dans les limites du plateau
            if (nouvelleLigne >= 0 && nouvelleLigne < Plateau.HAUTEUR &&
                nouvelleColonne >= 0 && nouvelleColonne < Plateau.LARGEUR) {
                // Vérifier si la case est vide
                if (plateau[nouvelleLigne][nouvelleColonne].equals("   ")) {
                    // Vérifier si un autre joueur occupe déjà la case
                    boolean caseOccupee = false;
                    for (Joueur autreJoueur : joueurs) {
                        if (autreJoueur.ligne == nouvelleLigne && autreJoueur.colonne == nouvelleColonne) {
                            caseOccupee = true;
                            break; // Sortir de la boucle si une case occupée est trouvée
                        }
                    }
                    if (!caseOccupee) {
                        // Au moins une case adjacente est libre et non occupée
                        return true;
                    }
                }
            }
        }
    
        // Si on arrive ici, aucune case adjacente n'est libre et non occupée
        System.out.println("Déplacement impossible sur les cases adjacentes !");
        return false;
    }
}