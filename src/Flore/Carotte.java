package Flore;

import Flore.Interfaces.IOgm;
import Flore.Interfaces.IRacePure;

import java.util.AbstractMap;
import java.util.HashMap;

public class Carotte extends Vegetal implements IRacePure, IOgm {

    public Carotte() {
        super();
        dessin[3] = 'c';
        dessin[4] = 'C';
    }

    @Override
    public AbstractMap.SimpleEntry<Integer, Integer> seDupliquer(int longueur, int largeur) {
        int x = (int) (Math.random() * longueur);
        int y = (int) (Math.random() * largeur);
        return new AbstractMap.SimpleEntry<>(x, y);
    }

    @Override
    public void seReproduire(HashMap<String, Integer> panier) {
        panier.put("Carotte", panier.getOrDefault("Carotte", 0) + 1);
    }
}
