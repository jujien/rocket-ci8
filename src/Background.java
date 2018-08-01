import java.awt.*;

public class Background {

    public Vector2D position;

    public Background() {
        this.position = new Vector2D();
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect((int) this.position.x, (int) this.position.y, 1024, 600);
    }
}
