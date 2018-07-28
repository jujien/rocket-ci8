import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy {

    public BufferedImage image;

    public int x;
    public int y;

    public int width;
    public int height;

    public int velocityX;
    public int velocityY;

    public Enemy() {

    }

    public void run() {
        this.x += this.velocityX;
        this.y += this.velocityY;
    }

    public void render(Graphics graphics) {
        graphics.drawImage(this.image, this.x, this.y, this.width, this.height, null);

    }
}
