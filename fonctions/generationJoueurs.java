package fonctions;
import java.util.Arrays;
import classes.Joueur;
import java.util.Random;

public class generationJoueurs {
    public static void generationJoueurs(String[] nomDesJoueurs, int nombreDeJoueurs, Joueur[] joueurs) {
        // Générer les joueurs
            for (int i = 0; i < nombreDeJoueurs; i++) {
                joueurs[i] = new Joueur();
                joueurs[i].nom = Joueur.choisirNomPotentiel(); // Générer un nouveau noms aléatoires

                while (Arrays.asList(nomDesJoueurs).contains(joueurs[i].nom)) { // temps que le noms aléatoir est déjà présent dans le tableau
                    joueurs[i].nom = Joueur.choisirNomPotentiel(); // regénérer un nouvau noms
                }
                nomDesJoueurs[i] = joueurs[i].nom; // ajout du nom dans le tableau des du noms des joueurs
            }
        //
        
        // Melanger le tableau joueurs
            Random rand = new Random();
            for (int i = nombreDeJoueurs - 1; i > 0; i--) { 
                // Générer un index aléatoire entre 0 et i inclusivement
                int j = rand.nextInt(i + 1);

                // Échanger les éléments à l'index i et j
                Joueur temp = joueurs[i];
                joueurs[i] = joueurs[j];
                joueurs[j] = temp;
            }
        //
    }
}
