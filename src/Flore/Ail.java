package Flore;

import Flore.Interfaces.IOgm;
import Flore.Interfaces.IRacePure;

import java.util.AbstractMap;
import java.util.HashMap;

public class Ail extends Vegetal implements IRacePure, IOgm {

    public Ail(){
        super();
        dessin[3] = 'a';
        dessin[4] = 'A';
    }

    @Override
    public AbstractMap.SimpleEntry<Integer, Integer> seDupliquer(int longueur, int largeur) {
        int x = (int) (Math.random() * longueur);
        int y = (int) (Math.random() * largeur);
        return new AbstractMap.SimpleEntry<>(x, y);
    }

    @Override
    public void seReproduire(HashMap<String, Integer> panier) {
        panier.put("Ail", panier.getOrDefault("Ail", 0) + 1);
    }
}
