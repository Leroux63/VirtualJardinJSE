package Flore;

import Flore.Interfaces.IOgm;
import Flore.Interfaces.IRacePure;

import java.util.AbstractMap;
import java.util.HashMap;

public class Betterave extends Vegetal implements IRacePure, IOgm {

    public Betterave() {
        super();
        dessin[3] = 'b';
        dessin[4] = 'B';
    }

    @Override
    public AbstractMap.SimpleEntry<Integer, Integer> seDupliquer(int longueur, int largeur) {
        int x = (int) (Math.random() * longueur);
        int y = (int) (Math.random() * largeur);
        return new AbstractMap.SimpleEntry<>(x, y);
    }

    @Override
    public void seReproduire(HashMap<String, Integer> panier) {
        panier.put("Betterave", panier.getOrDefault("Betterave", 0) + 1);
    }
}
