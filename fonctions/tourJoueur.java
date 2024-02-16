package fonctions;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

import classes.Joueur;
import classes.Plateau;

/**
 * La classe 'tourJoueur' fournit des fonctionnalités pour gérer le tour d'un joueur.
 */
public class tourJoueur {

    /**
     * Gère le tour d'un joueur, permettant au joueur de se déplacer et de détruire une case sur le plateau de jeu.
     *
     * @param joueurs              Le tableau des joueurs.
     * @param joueurIndex          L'index du joueur dont c'est le tour.
     * @param entre                Scanner pour lire l'entrée utilisateur.
     * @param nombreDeJoueursEnVie Le nombre de joueurs encore en vie.
     */
    public static void tourJoueur(Joueur[] joueurs, int joueurIndex, Scanner entre, int nombreDeJoueursEnVie) {

        String directionDeplacement = "";
        String[] NOMS_COLONNE = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
        String[] NOMS_LIGNE = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        int[] coordoneesCaseADetruire = new int[2];

        boolean caseDetruite = false;
        boolean voirRegles = false;

        do {
            Plateau.nettoyageCasePrecedente(joueurs);
            System.out.println("Dans quelle direction voulez-vous vous déplacer '" + joueurs[joueurIndex].nom + "' ? (haut 'z', bas 's', gauche 'q', droite 'd') :");
            System.out.println(directionDeplacement);
            directionDeplacement = entre.nextLine().toLowerCase();
            if (directionDeplacement.equals("regles") || directionDeplacement.equals("règles")) {
                voirRegles = true;
                affichageJeu.afficher(joueurs, nombreDeJoueursEnVie, voirRegles);
            }

        } while (!deplacerJoueur.seDeplacer(joueurs[joueurIndex], joueurs, directionDeplacement, Plateau.recuperePlateau(), nombreDeJoueursEnVie));

        Plateau.miseAJourPlateauDeJeu(joueurs);
        affichageJeu.afficher(joueurs, nombreDeJoueursEnVie, voirRegles);

        while (!caseDetruite) {
            String positionCase;
            boolean ligneValide = false;
            boolean colonneValide = false;

            while (!ligneValide || !colonneValide) {
                do {
                    System.out.println("\nQuelle case voulez-vous détruire ? (Ex : K1) :");
                    positionCase = entre.nextLine().toUpperCase();

                    if (directionDeplacement.toLowerCase().equals("regles") || directionDeplacement.toLowerCase().equals("règles")) {
                        voirRegles = true;
                    }

                } while (!positionCase.matches("[ABCDEFGHIJK](10|[1-9])"));

                String colonneCase = String.valueOf(positionCase.charAt(0));
                String ligneCase = positionCase.substring(1);

                for (String nomColonne : NOMS_COLONNE) {
                    if (nomColonne.equals(colonneCase)) {
                        coordoneesCaseADetruire[1] = Arrays.asList(NOMS_COLONNE).indexOf(nomColonne);
                        colonneValide = true;
                        break;
                    }
                }

                for (String nomLigne : NOMS_LIGNE) {
                    if (nomLigne.equals(ligneCase)) {
                        coordoneesCaseADetruire[0] = Arrays.asList(NOMS_LIGNE).indexOf(nomLigne);
                        ligneValide = true;
                        break;
                    }
                }

                if (!ligneValide || !colonneValide) {
                    System.out.println("Coordonnées invalides.");
                }
            }
            caseDetruite = Plateau.detruireCase(coordoneesCaseADetruire);
            if (!caseDetruite) {
                System.out.println("La case ne peut pas être détruite, elle est déjà occupée.");
            }
        }

        Plateau.miseAJourPlateauDeJeu(joueurs);
    }
}
