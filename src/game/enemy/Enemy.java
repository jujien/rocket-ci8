package game.enemy;

import action.*;
import base.FrameCounter;
import base.GameObject;
import base.Vector2D;
import physic.BoxCollider;
import physic.PhysicBody;
import renderer.ImageRenderer;

import java.awt.*;

public class Enemy extends GameObject implements PhysicBody {

    public Vector2D velocity;

    public EnemyShoot enemyShoot;

    private BoxCollider boxCollider;

    public Enemy() {
        this.renderer = new ImageRenderer("resources/images/circle.png", 20, 20);
        this.velocity = new Vector2D();
        this.boxCollider = new BoxCollider(20, 20);
        this.enemyShoot = new EnemyAttack();
    }


    public void configAction() {
        Action moveAction = new ActionAdapter() {
            FrameCounter frameCounter = new FrameCounter(100);

            @Override
            public boolean run(GameObject owner) {
                Enemy enemy = (Enemy) owner;
                enemy.position.addUp(enemy.velocity);
                return frameCounter.run();
            }

            @Override
            public void reset() {
                this.frameCounter.reset();
            }
        };

        Action shootAction = new ActionAdapter() {
            @Override
            public boolean run(GameObject owner) {
                Enemy enemy = (Enemy) owner;
                enemy.enemyShoot.run(enemy);
                return true;
            }
        };

        this.addAction(
                new RepeatActionForever(
                        new SequenceAction(
                                moveAction,
                                shootAction,
                                new WaitAction(40)
                        )
                )
        );
    }

    @Override
    public void run() {
        super.run();
        this.boxCollider.position.set(this.position.x - 10, this.position.y - 10);
        if (this.position.x > 1024 || this.position.x < 0) this.isAlive = false;
        if (this.position.y > 600 || this.position.y < 0) this.isAlive = false;
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    @Override
    public void getHit(GameObject gameObject) {
        this.isAlive = false;
    }
}
