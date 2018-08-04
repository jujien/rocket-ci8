import java.awt.*;

public class Enemy {

    public Renderer renderer;

    public Vector2D position;

    public Vector2D velocity;

    public EnemyShoot enemyShoot;

    public Enemy() {
        this.renderer = new ImageRenderer("resources/images/circle.png", 20, 20);
        this.position = new Vector2D();
        this.velocity = new Vector2D();
        this.enemyShoot = new EnemyAttack();
    }

    public void run() {
        this.position.addUp(this.velocity);
        this.enemyShoot.run(this);
    }


    public void render(Graphics graphics) {
        this.renderer.render(graphics, this.position);
        ((EnemyAttack) this.enemyShoot)
                .bulletEnemies
                .forEach(bulletEnemy -> bulletEnemy.render(graphics));

    }
}
