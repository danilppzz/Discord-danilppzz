package dev.soizx.util;

import java.util.Random;

public class MathFunctions {

    public static Integer rand(int min, int max) {
        Random gen = new Random();

        if (min > max) {
            return null;
        }

        int rang = max - min + 1;
        return gen.nextInt(rang) + min;
    }
}
