import Flore.*;
import Flore.Interfaces.IOgm;
import Flore.Interfaces.IRacePure;

import java.util.*;

public class Jardin {
    private int longueur;
    private int largeur;
    private HashMap<String, Integer> panier;

    /*
        Key : Value

        Ail : 10
        Betterave : 10
        Carotte : 10
        Tomate : 10

        panier.get("Ail") -> 10

         */

    private Emplacement[][] emplacements;

    public Jardin(final int longueur, final int largeur) {
        this.longueur = longueur;
        this.largeur = largeur;
        this.panier = new HashMap<String, Integer>();
        this.emplacements = new Emplacement[this.longueur][this.largeur];
    }

    public void ajouterPanier(final String nomDuVegatal, final int quantite) {
        this.panier.put(nomDuVegatal, this.panier.getOrDefault(nomDuVegatal, 0) + quantite);
    }

    public void semer() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Entrez la position x de l'emplacement : ");
        int x = scanner.nextInt();

        System.out.println("Entrez la position y de l'emplacement : ");
        int y = scanner.nextInt();

        System.out.println("Choisissez le végétal que vous souhaitez semer (Ail, Betterave, Carotte ou Tomate) : ");
        String choix = scanner.next();

        Vegetal vegetal = Vegetal.creerVegetal(choix);

        if (vegetal == null) {
            return; // Sortir de la méthode si le choix du végétal n'est pas valide
        }

        if (x < 0 || x >= longueur || y < 0 || y >= largeur) {
            System.out.println("Position invalide ! Veuillez choisir une position valide.");
            return; // Sortir de la méthode si la position est invalide
        }

        if (emplacements[x][y] != null) {
            System.out.println("Un végétal est déjà planté à cet emplacement !");
            return; // Sortir de la méthode si un végétal est déjà planté à cet emplacement
        }

        emplacements[x][y] = new Emplacement(vegetal);
        panier.put(choix, panier.getOrDefault(choix, 0) - 1); // Retirer une graine du panier

        System.out.println("Graine semée !");
        System.out.println(this); // Afficher l'état du jardin et du panier après avoir semé la graine
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Voici notre jardin :\n");
        for (int i = 0; i < longueur; i++) {
            for (int j = 0; j < largeur; j++) {
                Emplacement e = emplacements[i][j];
                if (e == null) {
                    sb.append("o");
                } else {
                    sb.append(e.toString());
                }
            }
            sb.append("\n");
        }
        sb.append("Et notre panier contient :\n");
        for (String nom : panier.keySet()) {
            Integer quantite = panier.get(nom);
            sb.append(nom + " : " + quantite + " graine(s)\n");
        }

        //panier.forEach((nom,quantite) -> sb.append(nom + " : " + quantite + " graine(s)\n"));

        return sb.toString();
    }

    public void recolter() {
        for (int x = 0; x < longueur; x++) {
            for (int y = 0; y < largeur; y++) {
                Emplacement emplacement = emplacements[x][y];
                if (emplacement != null) {
                    Vegetal vegetal = emplacement.getVegetal();
                    if (vegetal instanceof IRacePure racePureVegetal) {
                        racePureVegetal.seReproduire(panier);
                    } else if (vegetal != null) {
                        IOgm ogmVegetal = (IOgm) vegetal;
                        AbstractMap.SimpleEntry<Integer, Integer> coordinates = ogmVegetal.seDupliquer(longueur, largeur);
                        int newX = coordinates.getKey();
                        int newY = coordinates.getValue();
                        if (emplacements[newX][newY] == null) {
                            emplacements[newX][newY] = new Emplacement(vegetal);
                        }
                    }
                    emplacements[x][y] = null;
                }
            }
        }
    }

    public void saisonSuivante() {
        for (int x = 0; x < longueur; x++) {
            for (int y = 0; y < largeur; y++) {
                if (emplacements[x][y] != null) {
                    emplacements[x][y].getVegetal().grandir();
                }
            }
        }
    }
}
