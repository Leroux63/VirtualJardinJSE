import java.util.Scanner;

public class Terre {

    public static void main(String[] args) {
        Jardin j = new Jardin(5, 4);

        j.ajouterPanier("Ail", 10);
        j.ajouterPanier("Betterave", 10);
        j.ajouterPanier("Carotte", 10);
        j.ajouterPanier("Tomate", 10);

        System.out.println(j);

        while (true) {
            // Demander à l'utilisateur ce qu'il veut faire
            Scanner scanner = new Scanner(System.in);
            System.out.println("Veuillez saisir un chiffre entre 1 et 4 correspondant au choix suivant\n" +
                    "1. Semer une graine ; \n" +
                    "2. Récolter toutes les plantes qui sont matures ; \n" +
                    "3. Passer à la saison suivante (toutes les plantes grandissent) ; \n" +
                    "4. Quitter l’application. ");

            int res = scanner.nextInt();
            switch (res) {
                case 1:
                    j.semer();
                    break;
                case 2:
                    j.recolter();
                    break;
                case 3:
                    j.saisonSuivante();
                    break;
                case 4:
                    System.exit(0); // Quitter l'application
                    break;
                default:
                    System.out.println("Choix invalide !");
                    break;
            }

            System.out.println(j); // Afficher l'état du jardin après chaque action
        }
    }
}