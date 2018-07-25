import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameCanvas extends JPanel {

    private BufferedImage starImage;
    private BufferedImage backBuffered;
    private BufferedImage playerImage;

    public int positionXStar = 1024;
    public int positionYStar = 200;

    public int positionXPlayer = 600;
    public int positionYPlayer = 200;

    private Graphics graphics;


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
        this.starImage = this.loadImage("resources/images/star.png");

        this.playerImage = this.loadImage("resources/images/circle.png");
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(this.backBuffered, 0, 0, null);

    }

    public void renderAll() {
        this.renderBackground();

        this.graphics.drawImage(this.starImage, this.positionXStar, this.positionYStar, 5, 5, null);

        this.graphics.drawImage(this.playerImage, this.positionXPlayer, this.positionYPlayer, 20, 20, null);

        this.repaint();
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
