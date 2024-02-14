/**
 * La classe Verificateur dans le package fonction fournit une méthode pour vérifier si un nombre donné
 * se trouve dans une plage spécifiée.
 */
package fonction;

public class verificateur {
    public static boolean verifChiffreEnEntre(int debut, int fin, String nombreDeJoueurs) {
        // Vérification que la valeur de nombreDeJoueurs est entre debut et fin
        
        try {
            // Convertir la chaîne nombreDeJoueurs en entier
            int nombreDeJoueursAsInt = Integer.parseInt(nombreDeJoueurs);
            
            // Vérifier si nombreDeJoueursAsInt est entre debut et fin
            if (nombreDeJoueursAsInt >= debut && nombreDeJoueursAsInt <= fin) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            // En cas d'erreur lors de la conversion ou si la chaîne n'est pas un nombre valide
            return false;
        }
    }
}