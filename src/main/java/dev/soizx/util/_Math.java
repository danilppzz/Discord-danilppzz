package dev.soizx.util;

import java.util.Random;

public class _Math {

    public static int rand(int min, int max) {
        Random gen = new Random();

        if (min > max) {
            throw new IllegalArgumentException("El valor mínimo no puede ser mayor que el valor máximo.");
        }

        int rang = max - min + 1;

        return gen.nextInt(rang) + min;
    }
}
