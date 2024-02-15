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
    public static void seDeplacer(Joueur joueur, Joueur[] joueurs, String direction, String[][] plateau) {
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
            return;
        }

        boolean caseOccupee = false;
        
        // Vérifier si un autre joueur occupe déjà la case
        for (Joueur autreJoueur : joueurs) {
            if (autreJoueur.ligne == nouvelleLigne && autreJoueur.colonne == nouvelleColonne) {
                caseOccupee = true;
                break;
            }
        }

        if (!caseOccupee) {
            // Déplacer le joueur
            joueur.ligne = nouvelleLigne;
            joueur.colonne = nouvelleColonne;
        } else {
            System.out.println("Il y a déjà un joueur sur cette case !");
        }
    }
    public static boolean verifDeplacement(Joueur[] joueurs, String[][] plateau, Joueur joueur) {
         // Stocker les coordonnées du nouveau déplacement pour le joueur
         int nouvelleLigne = joueur.ligne;
         int nouvelleColonne = joueur.colonne;
          
        // Vérifier si les nouvelles coordonnées sont valides et si la case est vide
        if (nouvelleLigne >= 0 && nouvelleLigne < Plateau.HAUTEUR &&
            nouvelleColonne >= 0 && nouvelleColonne < Plateau.LARGEUR &&
            plateau[nouvelleLigne][nouvelleColonne].equals("   ")
        ) {
            // // Vérifier si un autre joueur occupe déjà la case
            boolean caseOccupee = false;
            // for (Joueur autreJoueur : joueurs) {
            //     if (autreJoueur.ligne == nouvelleLigne && autreJoueur.colonne == nouvelleColonne) {
            //         caseOccupee = true;
            //         break;
            //     }
            // }

            // Vérifier si un autre joueur occupe déjà la case
            for (int[] coJoueur : coordonees.recupererCoordonnees(joueurs)) {

                System.out.println(Arrays.toString(joueurs));
                System.out.println(coJoueur[0] == nouvelleLigne);
                System.out.println(coJoueur[1] == nouvelleColonne);
                if(coJoueur[0] == nouvelleLigne && coJoueur[1] == nouvelleColonne){
                    caseOccupee = true;
                    break;
                }
            }
            
            
            if (!caseOccupee) {
                return true; // Indiquer que le déplacement est réussi
            } else {
                System.out.println("Il y a déjà un joueur sur cette case !");
                return false; // Indiquer que le déplacement n'est pas possible
            }
        } else {
            System.out.println("Déplacement impossible !");
            return false; // Indiquer que le déplacement n'est pas possible
        }
    }
    
}