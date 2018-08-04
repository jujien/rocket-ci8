package game.enemy;

import base.FrameCounter;
import base.GameObject;
import game.enemy.Enemy;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateEnemy extends GameObject {

    private Random random;
    private FrameCounter frameCounter;
    private List<Enemy> enemies;

    public CreateEnemy() {
        this.random = new Random();
        this.frameCounter = new FrameCounter(200);
        this.enemies = new ArrayList<>();
    }

    @Override
    public void run() {
        super.run();
        if (this.frameCounter.run()) {
            Enemy enemy = new Enemy();
            enemy.position.set(this.random.nextInt(1024), this.random.nextInt(600));
            enemy.velocity.set(this.random.nextInt(3) + 1, this.random.nextInt(3) + 1);
            this.enemies.add(enemy);
            this.frameCounter.reset();
        }
    }

    @Override
    public void render(Graphics graphics) {
        this.enemies.forEach(enemy -> enemy.render(graphics));
    }
}
