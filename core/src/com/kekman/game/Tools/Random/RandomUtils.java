package com.kekman.game.Tools.Random;

import java.util.Random;

/**
 * Created by elytum on 07/03/2017.
 */

public class RandomUtils {
    static private Random rand = new Random(System.currentTimeMillis());

    public static int randInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }

    public static int randInt(int max) {
        return rand.nextInt(max + 1);
    }
}
