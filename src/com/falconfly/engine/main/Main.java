package com.falconfly.engine.main;

import com.falconfly.engine.Engine;
import com.falconfly.game.FalconFly;
import com.falconfly.engine.IGameLogic;

public class Main {
    public static void main(String... arg) {
        boolean vSync = true;
        try {
            IGameLogic falconFly = new FalconFly();
            Engine engine = new Engine("FalconFly", 1366, 768, vSync, falconFly);
            engine.run();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
