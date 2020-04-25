package com.falconfly.game;

public class Gameplay {

    private final int LEFT = -1;
    private final int MIDDLE = 0;
    private final int RIGTH = 1;

    private int playerPosition;

    private class Enemy {
        private int enemyCoordinateZ;
        private int enemyPosition;

        public Enemy() {
            enemyCoordinateZ = 0;
            enemyPosition = 0;
        }

        public int getEnemyPosition() {
            return enemyPosition;
        }

        public void setEnemyPosition(int enemyPosition) {
            this.enemyPosition = enemyPosition;
        }
    }

    public Gameplay() {
        playerPosition = MIDDLE;
    }

    public void goToLeft() {
        if (playerPosition != LEFT) {
            playerPosition--;
        }
    }

    public void goToRigth() {
        if (playerPosition != RIGTH) {
            playerPosition++;
        }
    }

    public boolean isCollisionWithEnemy() {
        return true;
    }
}
