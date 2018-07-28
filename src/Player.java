import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Player {

    public Vector2D position;
    public Vector2D velocity;

    private List<Vector2D> vertices;

    private Polygon polygon;

    public Player() {
        this.position = new Vector2D();
        this.velocity = new Vector2D();

        this.vertices = Arrays.asList(
                new Vector2D(),
                new Vector2D(0, 16),
                new Vector2D(20, 8)
        );
        this.polygon = new Polygon();
    }

    public void run() {
        this.position.addUp(this.velocity);
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.RED);

        this.updateTriangle();

        graphics.fillPolygon(this.polygon);
    }

    private void updateTriangle() {
        this.polygon.reset();
        Vector2D center = this.vertices
                .stream()
                .reduce(new Vector2D(), (v1, v2) -> v1.add(v2))
                .multiply(1.0f / (float) this.vertices.size());

        Vector2D translate = this.position.subtract(center);

        this.vertices
                .stream()
                .map(vector2D -> vector2D.add(translate))
                .forEach(vector2D -> polygon.addPoint((int) vector2D.x, (int) vector2D.y));
    }
}