package classes;

public class Joueur {

    /*
     * Création du tableau contenant une liste des noms potentiels des joueurs
     */

    // définition de la varible enVie
    public static boolean enVie = true;
    public String nom;
    public int colonne;
    public int ligne;
    private static final String[] nomsPotentiels = {"Syndra", "Teemo", "Warwick", "Maitre Yi", "Queen", "Lux", "Brand", "Fizz", "Kassadin", "Yasuo", "Rengar", "Katarina", "Kayle", "Illaoi", "Trundle"};

    
    // choisir dans la liste des noms potentiels un nom au hazard
    public static String choisirNomPotentiel() {
        int choix = (int) (Math.random() * nomsPotentiels.length);
        return nomsPotentiels[choix];
    }
}