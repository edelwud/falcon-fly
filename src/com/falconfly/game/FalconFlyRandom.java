package com.falconfly.game;

import java.util.Random;

public class FalconFlyRandom {

    public FalconFlyRandom() {
    }

    public static int getRandomNumber(int begin, int end) {
        return begin + (int)(Math.random() * end);
    }
}
