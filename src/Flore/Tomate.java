package Flore;

import Flore.Interfaces.IOgm;
import Flore.Interfaces.IRacePure;

import java.util.AbstractMap;
import java.util.HashMap;

public class Tomate extends Vegetal implements IRacePure, IOgm {

    public Tomate() {
        super();
        dessin[3] = 't';
        dessin[4] = 'T';
    }

    @Override
    public AbstractMap.SimpleEntry<Integer, Integer> seDupliquer(int longueur, int largeur) {
        int x = (int) (Math.random() * longueur);
        int y = (int) (Math.random() * largeur);
        return new AbstractMap.SimpleEntry<>(x, y);
    }

    @Override
    public void seReproduire(HashMap<String, Integer> panier) {
        panier.put("Tomate", panier.getOrDefault("Tomate", 0) + 1);
    }
}
