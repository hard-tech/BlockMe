package classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
        ArrayList<String> lignes = new ArrayList<>();

        // Lire les lignes existantes
        try (BufferedReader reader = new BufferedReader(new FileReader(nomFichier))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lignes.add(line);
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }

        // Mettre à jour les scores des joueurs existants ou ajouter de nouveaux joueurs
        for (Joueur joueur : joueurs) {
            boolean joueurExistant = false;
            for (int i = 0; i < lignes.size(); i++) {
                if (lignes.get(i).startsWith("Nom du joueur : " + joueur.nom)) {
                    // Mise à jour du score du joueur existant
                    lignes.set(i + 1, "Score du joueur : " + joueur.score);
                    joueurExistant = true;
                    break;
                }
            }
            if (!joueurExistant) {
                // Ajouter une nouvelle entrée pour le joueur
                lignes.add("Nom du joueur : " + joueur.nom);
                lignes.add("Score du joueur : " + joueur.score);
                lignes.add("---------------------");
            }
        }

        // Écrire les lignes mises à jour dans le fichier
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFichier))) {
            for (String line : lignes) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Les scores ont été sauvegardés avec succès dans le fichier : " + nomFichier);
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture dans le fichier : " + e.getMessage());
        }
    }
}