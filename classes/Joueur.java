package classes;

import java.io.*;
import java.util.ArrayList;

public class Joueur implements Serializable { // Implémentation pour une sérialisation

    // Définition des variables
    public static boolean enVie = true; // Variable statique pour suivre l'état de vie des joueurs
    public String nom; // Le nom du joueur
    public int colonne; // La colonne actuelle du joueur
    public int ligne; // La ligne actuelle du joueur
    public int score = 0; // Le score du joueur
    private static final String[] nomsPotentiels = {"Syndra", "Teemo", "Warwick", "Maitre Yi", "Queen", "Lux", "Brand", "Fizz", "Kassadin", "Yasuo", "Rengar", "Katarina", "Kayle", "Illaoi", "Trundle"}; // Liste des noms potentiels pour les joueurs

    // Choisir dans la liste des noms potentiels un nom au hasard
    public static String choisirNomPotentiel() {
        int choix = (int) (Math.random() * nomsPotentiels.length); // Génère un indice aléatoire
        return nomsPotentiels[choix]; // Retourne un nom aléatoire
    }

    // Fonction qui sauvegarde les objets dans un fichier
    public static void sauvegarderJoueur(Joueur[] joueurs, String nomFichier) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nomFichier))) {
            for (int i = 0; i < joueurs.length; i++) {
                outputStream.writeObject(joueurs[i]); // Écrit chaque joueur dans le fichier
            }
        } catch (IOException e) {
            e.printStackTrace(); // En cas d'erreur, affiche la trace de l'exception
        }
    }
    
    // Fonction qui charge les objets sérialisés d'un fichier peu importe le nombre d'objets dans le fichier
    public static Joueur[] chargerJoueur() {
        ArrayList<Joueur> joueurs = new ArrayList<>(); // Crée une liste pour stocker les joueurs
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("objets.ser"))) {
            while (true) {
                try {
                    Joueur joueur = (Joueur) inputStream.readObject(); // Lit chaque joueur du fichier
                    joueurs.add(joueur); // Ajoute le joueur à la liste
                } catch (EOFException e) {
                    break; // Fin du fichier atteinte
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(); // En cas d'erreur, affiche la trace de l'exception
        }
        return joueurs.toArray(new Joueur[joueurs.size()]); // Retourne la liste des joueurs sous forme de tableau
    }
}
