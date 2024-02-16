package fonctions; // Définition du package "fonctions"

import java.io.*; // Importation des classes pour les entrées/sorties
import java.util.Arrays; // Importation de la classe Arrays

import classes.Joueur; // Importation de la classe Joueur depuis le package classes

public class afficherScores { // Définition de la classe afficherScores

    public static Joueur[] trie(Joueur[] tab, boolean ordreCroissant){ // Définition d'une méthode trie prenant un tableau d'entiers en paramètre et retournant un tableau d'entiers
        if (tab.length==1){ // Vérifie si le tableau a une seule valeur
            return tab; // Si c'est le cas, retourne directement le tableau
        }
        else { // Sinon, si le tableau contient plus d'un élément
            for (int i = 1; i<tab.length; i++){ // Parcours du tableau à partir de la deuxième valeur jusqu'à la fin
                int j = i; // Initialisation d'un indice j égal à i
                Joueur tempo = tab[i]; // Sauvegarde de la valeur de tab[i] dans une variable tempo
                if (ordreCroissant) { // Si l'ordre est croissant
                    while ((j>0) && (tempo.score<tab[j-1].score)){ // Tant que j est supérieur à 0 et que la valeur précédente est supérieure à tempo
                        tab[j]=tab[j-1]; // Décalage des éléments vers la droite
                        j=j-1; // Décrémentation de j
                    }
                }
                else{ // Sinon, si l'ordre est décroissant
                    while ((j>0) && (tempo.score>tab[j-1].score)){ // Tant que j est supérieur à 0 et que la valeur précédente est supérieure à tempo
                        tab[j]=tab[j-1]; // Décalage des éléments vers la droite
                        j=j-1; // Décrémentation de j
                    }
                }
                tab[j]=tempo; // Placement de tempo à sa position correcte dans le tableau trié
            }
            return tab; // Retourne le tableau trié
        }
    }
    
    public static void lireScores(String nomFichier, boolean ordreCroissant) { // Définition d'une méthode lireScores prenant en paramètre un nom de fichier et un booléen indiquant l'ordre de tri
        Joueur[] joueurs = Joueur.chargerJoueur(nomFichier); // Chargement des joueurs à partir du fichier
        trie(joueurs, ordreCroissant); // Tri des joueurs
        for (int i = 0; i < joueurs.length; i++){ // Parcours des joueurs triés
            System.out.println(joueurs[i].nom); // Affichage du nom du joueur
            System.out.println(joueurs[i].score); // Affichage du score du joueur
            System.out.println("\n"); // Affichage d'une ligne vide
        }
    }
}
