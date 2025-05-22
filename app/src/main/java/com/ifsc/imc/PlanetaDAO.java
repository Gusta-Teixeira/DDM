package com.ifsc.imc;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PlanetaDAO {
    ArrayList<Planeta> Listplanetas;

    public PlanetaDAO() {
        Listplanetas = new ArrayList<>();
        String [] planetas = new String[]{"Terra", "Marte", "Júpiter", "Mercúrio", "Netuno", "Saturno", "Sol", "Urano", "Vênus"};
        Integer [] fotos = new Integer [] {R.drawable.earth, R.drawable.mars, R.drawable.jupter, R.drawable.mercury, R.drawable.neptune,
                R.drawable.saturn, R.drawable.sun, R.drawable.uranus, R.drawable.venus};

        for (int i =0; i< planetas.length; i++){
            Listplanetas.add(new Planeta(planetas[i], fotos[i]));
        }
    }
}
