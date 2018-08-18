package game.enemy;

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

    @Override
    public void run() {
        super.run();
        this.boxCollider.position.set(this.position.x - 10, this.position.y - 10);
        this.position.addUp(this.velocity);
        this.enemyShoot.run(this);
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
