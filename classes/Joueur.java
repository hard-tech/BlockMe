package classes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Joueur {

    /*
     * Création du tableau contenant une liste des noms potentiels des joueurs
     */

    // définition de la varible enVie
    public static boolean enVie = true;
    public String nom;
    public int colonne;
    public int ligne;
    public int score;
    private static final String[] nomsPotentiels = {"Syndra", "Teemo", "Warwick", "Maitre Yi", "Queen", "Lux", "Brand", "Fizz", "Kassadin", "Yasuo", "Rengar", "Katarina", "Kayle", "Illaoi", "Trundle"};

    
    // choisir dans la liste des noms potentiels un nom au hazard
    public static String choisirNomPotentiel() {
        int choix = (int) (Math.random() * nomsPotentiels.length);
        return nomsPotentiels[choix];
    }

    public static void sauvegarderScores(Joueur[] joueurs, String nomFichier) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFichier))) {
            // Écrire les en-têtes ou les informations de formatage nécessaires
            writer.write("Scores des joueurs :\n");
            writer.write("---------------------\n");
            
            // Parcourir le tableau de joueurs et écrire les informations de chaque joueur dans le fichier
            for (Joueur joueur : joueurs) {
                writer.write("Nom du joueur : " + joueur.nom + "\n");
                writer.write("Score du joueur : " + joueur.score + "\n");
                writer.write("---------------------\n");
            }
            
            System.out.println("Les scores ont été sauvegardés avec succès dans le fichier : " + nomFichier);
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde des scores : " + e.getMessage());
        }
    }
}