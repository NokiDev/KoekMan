package com.kekman.game.Tools.Random;

import java.util.Random;

/**
 * Created by elytum on 07/03/2017.
 */

public class RandomUtils {
    static private Random rand = new Random(System.currentTimeMillis());

    public static int randInt(int min, int max) {
        if ((max - min) + 1 <= 0)
            return 0;
        return rand.nextInt((max - min) + 1) + min;
    }

    public static int randInt(int max) {
        if (max + 1 <= 0)
            return 0;
        return rand.nextInt(max + 1);
    }
}
