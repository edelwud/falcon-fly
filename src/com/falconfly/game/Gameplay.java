package com.falconfly.game;

import com.falconfly.engine.GameItem;
import com.falconfly.engine.graph.Material;
import com.falconfly.engine.graph.Mesh;
import com.falconfly.engine.graph.OBJLoader;
import com.falconfly.engine.graph.Texture;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class Gameplay {

    boolean kastil = true;

    private final int LEFT = 1;
    private final int MIDDLE = 2;
    private final int RIGTH = 3;
    private Vector<GameItem> gameItems;
    private List<Enemy> enemies;

    private List<Pair<Number, Number>> path;

    private List<Pair<Number, Number>> enemyMovement;
    private int ticks;
    private int staticTime;
    private int reachedCoordinate;

    private float playerPosition;

    private class Enemy {
        private float enemyCoordinateZ;
        private float enemyPosition;

        private Mesh grassMesh;
        private Texture grassTexture;
        private Material grassMaterial;
        private GameItem enemy;

        public Enemy() throws Exception {
            enemyCoordinateZ = -240;
            enemyPosition = FalconFlyRandom.getRandomNumber(1, 4);
            //enemySpawnTime = 0;

            float reflectance = 1f;

            grassMesh = OBJLoader.loadMesh("models/house_6");
            grassTexture = new Texture("models/house_6");
            grassMaterial = new Material(grassTexture, reflectance);
            grassMesh.setMaterial(grassMaterial);

            enemy = new GameItem(grassMesh);

            float xCoordinate = -2f;
            if (enemyPosition == 1)
                xCoordinate = -4f;
            else if (enemyPosition == 2)
                xCoordinate = -2f;
            else if (enemyPosition == 3)
                xCoordinate = 2f;
            enemy.setPosition(xCoordinate, -5, -240);

            gameItems.add(enemy);
        }

        public Enemy(int pos) throws Exception {
            enemyCoordinateZ = pos;
            enemyPosition = FalconFlyRandom.getRandomNumber(1, 3);
            //enemySpawnTime = 0;

            float reflectance = 1f;

            grassMesh = OBJLoader.loadMesh("models/house_6");
            grassTexture = new Texture("models/house_6");
            grassMaterial = new Material(grassTexture, reflectance);
            grassMesh.setMaterial(grassMaterial);

            enemy = new GameItem(grassMesh);

            float xCoordinate = 0f;
            if (enemyPosition == 1)
                xCoordinate = -4f;
            else if (enemyPosition == 2)
                xCoordinate = -2f;
            else if (enemyPosition == 3)
                xCoordinate = 2f;
            enemy.setPosition(xCoordinate, -5, pos);

            gameItems.add(enemy);
        }

        public float getEnemyPosition() {
            return enemyPosition;
        }

        public void setEnemyPosition(int enemyPosition) {
            this.enemyPosition = enemyPosition;
        }

        public float getEnemyCoordinateZ() {
            return enemyCoordinateZ;
        }

        public void setEnemyCoordinateZ(int enemyCoordinateZ) {
            this.enemyCoordinateZ = enemyCoordinateZ;
        }

        public void decEnemyCoordinateZ(float step) {
            enemyCoordinateZ += step;
            //enemy.setPosition(enemy.getPosition().x, enemy.getPosition().y, enemy.getPosition().z + step);
        }

        public GameItem getGameItem() {
            return enemy;
        }
    }

    public Gameplay(Vector<GameItem> objVector) throws Exception {
        this.gameItems = objVector;

        playerPosition = MIDDLE;
        this.path = new LinkedList<Pair<Number, Number>>();
        this.enemyMovement = new LinkedList<Pair<Number, Number>>();
        this.enemies = new LinkedList<Enemy>();
        this.ticks = 0;
        this.reachedCoordinate = -250;
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

    public float getPlayerPosition() {
        return this.playerPosition;
    }
    public boolean isCollisionWithEnemy() {
        for(Enemy temp : enemies) {
            if (this.playerPosition == temp.getEnemyPosition() && (temp.getEnemyCoordinateZ() >= -6 && (temp.getEnemyCoordinateZ() <= -4))) {
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

    public void update(float step) throws Exception {
        if (kastil) {
            enemies.add(new Enemy(-40));
            enemies.add(new Enemy(-80));
            enemies.add(new Enemy(-120));
            enemies.add(new Enemy(-160));
            enemies.add(new Enemy(-200));
            kastil = false;
        }
        this.incTicks();
        this.incStaticTime();
        for(Enemy temp: enemies)
            temp.decEnemyCoordinateZ(step);
        for(Enemy temp: enemies) {
            if(temp.getEnemyCoordinateZ() >= -2) {
                //gameItems.remove(temp.getGameItem());
                //enemies.remove(0);
            }
        }
        System.out.println(enemies.size());

        float isReached = 0f;
        for (Enemy temp : enemies) {
            isReached = temp.getEnemyCoordinateZ();
        }
        if (isReached >= this.reachedCoordinate) {
            this.enemies.add(new Enemy());
            this.reachedCoordinate = FalconFlyRandom.getRandomNumber(190, 210) * -1;
        }
    }

    public List<Pair<Number, Number>> getPath() {
        return this.path;
    }

    public List<Pair<Number, Number>> getEnemyMovement() {
        return this.enemyMovement;
    }
}
