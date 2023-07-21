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
        Scanner sc = new Scanner(System.in);
        System.out.println("Quel est la position X?");
        int x = sc.nextInt();
        System.out.println("Quel est la position Y?");
        int y = sc.nextInt();
        System.out.println("Quel est le végétal ? 1 - Ail | 2 - Betterave | 3 - Carotte | 4 - Tomate");
        int numVeg = sc.nextInt();

        Vegetal vegetal = null;
        String choix = "";

        switch (numVeg) {
            case 1:
                vegetal = new Ail();
                choix = "Ail";
                break;
            case 2:
                vegetal = new Betterave();
                choix = "Betterave";
                break;
            case 3:
                vegetal = new Carotte();
                choix = "Carotte";
                break;
            case 4:
                vegetal = new Tomate();
                choix = "Tomate";
                break;
            default:
                System.out.println("Végétal non reconnu !");
                return;
        }

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

                    // Vérifier si le végétal est de type IRacePure (race pure)
                    if (vegetal instanceof IRacePure) {
                        IRacePure racePureVegetal = (IRacePure) vegetal;
                        racePureVegetal.seReproduire(panier);
                    }
                    // Vérifier si le végétal est de type IOgm (OGM)
                    else if (vegetal instanceof IOgm) {
                        IOgm ogmVegetal = (IOgm) vegetal;
                        AbstractMap.SimpleEntry<Integer, Integer> coordinates = ogmVegetal.seDupliquer(longueur, largeur);
                        int newX = coordinates.getKey();
                        int newY = coordinates.getValue();
                        if (emplacements[newX][newY] == null) {
                            // Modifier l'état du végétal OGM pour qu'il se sème à l'état de graine
                            vegetal.setEtat(Etat.GRAINE);
                            emplacements[newX][newY] = new Emplacement(vegetal);
                        }
                    }

                    // Récolter le végétal
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
