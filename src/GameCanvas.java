import game.background.Background;
import game.enemy.CreateEnemy;
import game.enemyfollow.EnemyFollow;
import game.player.Player;
import game.star.CreateStar;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class GameCanvas extends JPanel {

    private BufferedImage backBuffered;
    private Background background = new Background();

    public Player player;

    private Graphics graphics;

    private Random random = new Random();

    private EnemyFollow enemyFollow;

    private CreateStar createStar;

    private CreateEnemy createEnemy;

    public GameCanvas() {

        this.setSize(1024, 600);

        this.setupBackBuffered();

        this.setupCharacter();

        this.setVisible(true);
    }

    private void setupBackBuffered() {
        this.backBuffered = new BufferedImage(1024, 600, BufferedImage.TYPE_INT_ARGB);
        this.graphics = this.backBuffered.getGraphics();
    }

    private void setupCharacter() {
        this.createStar = new CreateStar();
        this.createEnemy = new CreateEnemy();
        this.setupPlayer();
        this.enemyFollow = new EnemyFollow();
        this.enemyFollow.position.set(this.random.nextInt(1024), this.random.nextInt(600));
    }

    private void setupPlayer() {
        this.player = new Player();
        this.player.position.set(200, 300);
        this.player.velocity.set(3.5f, 0);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(this.backBuffered, 0, 0, null);
    }

    public void renderAll() {
        this.background.render(this.graphics);

        this.createStar.render(this.graphics);

        this.player.render(this.graphics);

        this.createEnemy.render(this.graphics);

        this.enemyFollow.render(this.graphics);
        this.repaint();
    }

    public void runAll() {
        this.createStar.run();

        this.createEnemy.run();

        this.player.run();

        this.enemyFollow.update(this.player.position);
        this.enemyFollow.run();
    }
}
