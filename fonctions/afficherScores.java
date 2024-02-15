package fonctions;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class afficherScores {
    public static void lireScores(String nomFichier) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomFichier))) {
            String ligne;
            // Lire chaque ligne du fichier jusqu'à ce qu'il n'y en ait plus
            while ((ligne = reader.readLine()) != null) {
                // Diviser la ligne en deux parties : nom du joueur et score
                String[] elements = ligne.split(",");
                if (elements.length == 2) {
                    String nomJoueur = elements[0].trim(); // Enlever les espaces autour du nom du joueur
                    int score = Integer.parseInt(elements[1].trim()); // Enlever les espaces autour du score et le convertir en entier
                    // Afficher le nom du joueur et son score de manière esthétique
                    System.out.printf("%-20s : %d\n", nomJoueur, score);
                } else {
                    System.out.println(ligne);
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture des scores : " + e.getMessage());
        }
    }
}
