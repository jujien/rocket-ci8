import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameCanvas extends JPanel {

    private List<Star> stars;
    private List<Enemy> enemies;
    private BufferedImage backBuffered;
    private Background background = new Background();

    public Player player;

    private Graphics graphics;

    private Random random = new Random();

    private FrameCounter frameCounterStar = new FrameCounter(30);
    private FrameCounter frameCounter = new FrameCounter(50);
    private EnemyFollow enemyFollow;

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
        this.setupStar();
        this.enemies = new ArrayList<>();
        this.setupPlayer();
        this.enemyFollow = new EnemyFollow();
        this.enemyFollow.position.set(this.random.nextInt(1024), this.random.nextInt(600));
    }

    private void setupPlayer() {
        this.player = new Player();
        this.player.position.set(200, 300);
        this.player.velocity.set(3.5f, 0);
    }

    private void setupStar() {
        this.stars = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(this.backBuffered, 0, 0, null);
    }

    public void renderAll() {
        this.background.render(this.graphics);

        this.stars.forEach(star -> star.render(graphics));

        this.player.render(this.graphics);

        this.enemies.forEach(enemy -> enemy.render(graphics));

        this.enemyFollow.render(this.graphics);
        this.repaint();
    }

    public void runAll() {
        this.createStar();
        this.stars.forEach(star -> star.run());

        this.createEnemy();
        this.enemies.forEach(enemy -> enemy.run());

        this.player.run();

        this.enemyFollow.update(this.player.position);
        this.enemyFollow.run();
    }

    private void createStar() {
        if (this.frameCounterStar.run()) {
            Star star = new Star();
            star.position.set(1024, this.random.nextInt(600));
            star.velocity.set(this.random.nextInt(3) + 1, 0);
            this.stars.add(star);
            this.frameCounterStar.reset();
        }

    }

    private void createEnemy() {
        if (this.frameCounter.run()) {
            Enemy enemy = new Enemy();
            enemy.position.set(this.random.nextInt(1024), this.random.nextInt(600));
            enemy.velocity.set(this.random.nextInt(3) + 1, this.random.nextInt(3) + 1);
            this.enemies.add(enemy);
            this.frameCounter.reset();
        }
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            return null;
        }
    }
}
