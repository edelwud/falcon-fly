package com.falconfly.game;

public class GameSetup {

    static private int dif = 2;

    /* mode */
    /* true - gameplay */
    /* false - replay */
    static private boolean mode = true;
    static private boolean isLose = false;

    static public int getDif() {
        return dif;
    }

    static public boolean getMode() {
        return mode;
    }

    static public boolean getIsLose() {
        return isLose;
    }

    static public void setDif(int var) {
        dif = var;
    }

    static public void setMode(boolean var) {
        mode = var;
    }

    static public void setIsLose(boolean var) {
        isLose = var;
    }
}
