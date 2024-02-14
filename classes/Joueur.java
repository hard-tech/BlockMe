package classes;

public class Joueur {

    /*
     * Création du tableau contenant une liste des noms potentiels des joueurs
     */

    // définition de la varible enVie
    public static boolean enVie = true;
    public static String nom;
    private static final String[] nomsPotentiels = {"joueur1", "joueur2", "joueur3", "joueur4", "joueur5", "joueur6", "joueur7", "joueur8", "joueur9", "joueur10"};

    
    // choisir dans la liste des noms potentiels un nom au hazard
    public static String choisirNomPotentiel() {
        int choix = (int) (Math.random() * nomsPotentiels.length);
        return nomsPotentiels[choix];
    }


}