package Flore;

public class Vegetal {
    protected char[] dessin ;
    private Etat etat;

    public Vegetal() {
        // Initialisation du tableau dessin
        dessin = new char[6];
        dessin[0] = '_'; // En graine
        dessin[1] = '.'; // En germe
        dessin[2] = 'I'; // En tige
        dessin[5] = '#'; // Mort
        etat = Etat.GRAINE; // Lorsque le végétal est semé, il est à l'état de graine
    }

    public static Vegetal creerVegetal(String choix) {
        switch (choix.toLowerCase()) {
            case "ail":
                return new Ail();
            case "betterave":
                return new Betterave();
            case "carotte":
                return new Carotte();
            case "tomate":
                return new Tomate();
            default:
                System.out.println("Végétal non reconnu !");
                return null; // Renvoyer null en cas de choix invalide
        }
    }

    public void grandir() {
        if (this.etat != Etat.MORT) {
            int nextOrdinal = this.etat.ordinal() + 1;
            if (nextOrdinal < Etat.values().length) {
                this.etat = Etat.values()[nextOrdinal];
            }
        }
    }

    @Override
    public String toString() {
        int indice = etat.ordinal();
        char currentDessin = dessin[indice];
        String str = String.valueOf(currentDessin);
        return str;
    }

    public Etat getEtat() {
        return etat;
    }
}