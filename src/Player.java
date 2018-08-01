import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class Player {

    public Vector2D position;
    public Vector2D velocity;
    public Renderer renderer;

    public double angle = 0.0;

    public Player() {
        this.position = new Vector2D();
        this.velocity = new Vector2D();

        this.renderer = new PolygonRenderer(Color.RED,
                new Vector2D(),
                new Vector2D(0, 16),
                new Vector2D(20, 8)
        );
    }

    public void run() {
        this.position.addUp(this.velocity);
    }

    public void render(Graphics graphics) {
        this.renderer.render(graphics, this.position);
    }
}
