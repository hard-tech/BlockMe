package fonctions;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

import classes.Joueur;
import classes.Plateau;

public class tourJoueur {
    public static void tourJoueur(Joueur[] joueurs, int joueurIndex,Scanner entre, int nombreDeJoueursEnVie) {

        String directionDeplacement = ""; // Déclaration d'une variable de type String pour stoquer la direction de déplacement voulu par le joueur
        String[] NOMS_COLONNE = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"}; // Déclaration d'une constante de type STRING (qui nous permettra de vérifier si les coorrodonners donnes sont valide)
        String[] NOMS_LIGNE = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}; // Déclaration d'une constante de type STRING (qui nous permettra de vérifier si les coorrodonners donnes sont valide)

        int[] coordoneesCaseADetruire = new int[2];
                        
        boolean caseDetruite = false;
        boolean voirRegles = false;

        do{
            Plateau.nettoyageCasePrecedente(joueurs); // Nettoyage des coordonnées du plateau de jeu (avant déplacement)
            System.out.println("Dans quelle direction voulez vous vous déplacer '"+ joueurs[joueurIndex].nom +"' ? (haut 'z', bas 's', gauche 'q', droite 'd') :");
            System.out.println(directionDeplacement);
            directionDeplacement = entre.nextLine().toLowerCase(); // Lire l'entrée utilisateur
            if(directionDeplacement.equals("regles") || directionDeplacement.equals("règles")){
                voirRegles = true;
                affichageJeu.afficher(joueurs, nombreDeJoueursEnVie, voirRegles); // Afficher tout le content du plateau de jeu et information
            }
            
        }while(!deplacerJoueur.seDeplacer(joueurs[joueurIndex], joueurs, directionDeplacement, Plateau.recuperePlateau(), nombreDeJoueursEnVie)); // redemmander à l'utilisateur vers qu'elle dirrection veut'il se déplacer temps que la dirrection est interdite ou invalid

        Plateau.miseAJourPlateauDeJeu(joueurs); // mise à jour de la plateau de jeu
        affichageJeu.afficher(joueurs, nombreDeJoueursEnVie, voirRegles); // Afficher tout le content du plateau de jeu et information


        while (!caseDetruite) {
            String positionCase;
            boolean ligneValide = false;
            boolean colonneValide = false;
        
            while (!ligneValide || !colonneValide) {
                do {
                    System.out.println("\nQuelle case voulez-vous détruire ? (Ex : K1) :");
                    positionCase = entre.nextLine().toUpperCase(); // Lire et convertir l'entrée utilisateur en majuscules

                    if(directionDeplacement.toLowerCase().equals("regles") || directionDeplacement.toLowerCase().equals("règles")){
                        voirRegles = true;
                    }
            
                    // Corrige l'expression régulière pour qu'elle accepte les chiffres de 1 à 9 et 10 après les lettres de A à K
                } while (!positionCase.matches("[ABCDEFGHIJK](10|[1-9])")); // Regex pour vérifier que la position est bien valide
            
                // Extraction de la colonne et de la ligne de façon à gérer correctement "10"
                String colonneCase = String.valueOf(positionCase.charAt(0));
                String ligneCase = positionCase.substring(1); // Maintenant cela fonctionne car l'expression régulière garantit une entrée valide
            
                // Vérification de la colonne
                for (String nomColonne : NOMS_COLONNE) {
                    if (nomColonne.equals(colonneCase)) {
                        coordoneesCaseADetruire[1] = Arrays.asList(NOMS_COLONNE).indexOf(nomColonne); // Correction ici
                        colonneValide = true;
                        break;
                    }
                }

                // Vérification de la ligne
                for (String nomLigne : NOMS_LIGNE) {
                    if (nomLigne.equals(ligneCase)) {
                        coordoneesCaseADetruire[0] = Arrays.asList(NOMS_LIGNE).indexOf(nomLigne); // Assurez-vous que NOMS_LIGNE est correct
                        ligneValide = true;
                        break;
                    }
                }
            
                if (!ligneValide || !colonneValide) {
                    System.out.println("Coordonnées invalides, t'es vraiment pas malin :(");
                }
            }
            caseDetruite = Plateau.detruireCase(coordoneesCaseADetruire); // détruire la case de destination
            if (!caseDetruite) {
                System.out.println("La case ne peut pas être détruite, elle est déjà occupée.");
            }
        }
        
        Plateau.miseAJourPlateauDeJeu(joueurs); // mise à jour de la plateau de jeu
    }
}
