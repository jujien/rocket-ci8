import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class GameCanvas extends JPanel {

    private Star star;
    private BufferedImage backBuffered;
    private BufferedImage playerImage;

    public int positionXPlayer = 600;
    public int positionYPlayer = 200;

    private Graphics graphics;

    private Random random = new Random();


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
        this.playerImage = this.loadImage("resources/images/circle.png");
    }

    private void setupStar() {
        this.star = new Star();
        this.star.x = 1024;
        this.star.y = this.random.nextInt(600);
        this.star.image = this.loadImage("resources/images/star.png");
        this.star.width = 5;
        this.star.height = 5;
        this.star.velocityX = this.random.nextInt(3) + 1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(this.backBuffered, 0, 0, null);
    }

    public void renderAll() {
        this.renderBackground();

        this.star.render(this.graphics);

        this.graphics.drawImage(this.playerImage, this.positionXPlayer, this.positionYPlayer, 20, 20, null);

        this.repaint();
    }

    public void runAll() {
        this.star.run();
        this.playerMove();
    }


    private void playerMove() {
        if (this.positionXPlayer > 1024) {
            this.positionXPlayer = 0;
            this.positionYPlayer = this.random.nextInt(600);
        }
        if (this.positionXPlayer < 0) {
            this.positionXPlayer = 1024;
            this.positionYPlayer = this.random.nextInt(600);
        }
        if (this.positionYPlayer > 600) {
            this.positionXPlayer = this.random.nextInt(1024);
            this.positionYPlayer = 0;
        }
        if (this.positionYPlayer < 0) {
            this.positionXPlayer = this.random.nextInt(1024);
            this.positionYPlayer = 600;
        }
    }

    private void renderBackground() {
        this.graphics.setColor(Color.BLACK);
        this.graphics.fillRect(0, 0, 1024, 600);
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            return null;
        }
    }
}
