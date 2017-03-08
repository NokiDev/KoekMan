package com.kekman.game.Tools.Score;

/**
 * Created by elytum on 08/03/2017.
 */

public class Score {
    private static int score = 0;

    public static int getScore() {
        return score;
    }
    public static void AddScore(int diff) {
        score += diff;
        System.out.println("Score: "+Score.getScore());
    }
}
