package com.kekman.game.Tools;

/**
 * Created by elytum on 06/03/2017.
 */

public class Ternary {
    final public static int TRUE = 0;
    final public static int FALSE = 1;
    final public static int UNKNOWN = 2;

    public int  value;

    public Ternary(int state) {
        switch (state) {
            case TRUE:
                value = TRUE;
                break;
            case FALSE:
                value = FALSE;
                break;
            case UNKNOWN:
                value = UNKNOWN;
                break;
            default:
                System.out.println("Unknown value "+state+" for Ternary constructor !");
                value = UNKNOWN;
                break;
        }
    }

    public boolean is(int state) {
        switch (state) {
            case TRUE:
                return value == TRUE;
            case FALSE:
                return value == FALSE;
            case UNKNOWN:
                return value == UNKNOWN;
            default:
                System.out.println("Unknown value "+state+" for Ternary comparison !");
                return value == UNKNOWN;
        }
    }
}
