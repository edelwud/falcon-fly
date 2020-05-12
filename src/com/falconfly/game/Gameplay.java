package com.falconfly.game;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Gameplay {

    private final int LEFT = 1;
    private final int MIDDLE = 2;
    private final int RIGTH = 3;
    private List<Enemy> enemies;

    private List<Pair<Number, Number>> path;

    private List<Pair<Number, Number>> enemyMovement;
    private int ticks;
    private int staticTime;

    private int enemySpawnTime;
    private int playerPosition;

    private class Enemy {
        private int enemyCoordinateZ;
        private int enemyPosition;


        public Enemy() {
            enemyCoordinateZ = 100;
            enemyPosition = FalconFlyRandom.getRandomNumber(1, 3);
            enemySpawnTime = 0;
        }

        public int getEnemyPosition() {
            return enemyPosition;
        }

        public void setEnemyPosition(int enemyPosition) {
            this.enemyPosition = enemyPosition;
        }

        public int getEnemyCoordinateZ() {
            return enemyCoordinateZ;
        }

        public void setEnemyCoordinateZ(int enemyCoordinateZ) {
            this.enemyCoordinateZ = enemyCoordinateZ;
        }

        public void decEnemyCoordinateZ() {
            enemyCoordinateZ--;
        }
    }

    public Gameplay() {
        playerPosition = MIDDLE;
        this.path = new LinkedList<Pair<Number, Number>>();
        this.enemyMovement = new LinkedList<Pair<Number, Number>>();
        this.enemies = new LinkedList<Enemy>();
        this.ticks = 0;
    }

    public void goToLeft() {
        this.path.add(new Pair(this.staticTime, this.playerPosition));
        if (playerPosition != LEFT) {
            playerPosition--;
            this.staticTime = 0;
        }
    }

    public void goToRight() {
        this.path.add(new Pair(this.staticTime, this.playerPosition));
        if (playerPosition != RIGTH) {
            playerPosition++;
            this.staticTime = 0;
        }
    }

    public int getPlayerPosition() {
        return this.playerPosition;
    }
    public boolean isCollisionWithEnemy() {
        for(Enemy temp : enemies) {
            if (this.playerPosition <= (temp.getEnemyPosition() - 0.5) && (this.playerPosition >= temp.getEnemyPosition() + 0.5) ) {
                return true;
            }
        }
        return false;
    }

    public int getTicks() {
        return this.ticks;
    }

    public void incTicks() {
        this.ticks++;
    }

    public void incStaticTime() {
        this.staticTime++;
    }

    public void start() {
        this.ticks = 0;
        this.path.clear();
        this.enemyMovement.clear();
    }

    public void update() {
        this.incTicks();
        this.incStaticTime();
        for(Enemy temp: enemies)
            temp.decEnemyCoordinateZ();
        for(Enemy temp: enemies) {
            if(temp.getEnemyCoordinateZ() <= 2) {
                enemies.remove(temp);
            }
        }
        if(this.enemySpawnTime == 0) {
            this.enemies.add(new Enemy());
            this.enemySpawnTime = FalconFlyRandom.getRandomNumber(80, 120);
        }
        this.enemySpawnTime--;
    }

    public List<Pair<Number, Number>> getPath() {
        return this.path;
    }

    public List<Pair<Number, Number>> getEnemyMovement() {
        return this.enemyMovement;
    }
}
