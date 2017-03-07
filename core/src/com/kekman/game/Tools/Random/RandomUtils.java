package com.kekman.game.Tools.Random;

import java.util.Random;

/**
 * Created by elytum on 07/03/2017.
 */

public class RandomUtils {
    static private Random rand = new Random(System.currentTimeMillis());

    public static int randInt(int min, int max) {
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public static int randInt(int max) {
        int randomNum = rand.nextInt(max + 1);
        return randomNum;
    }
}
