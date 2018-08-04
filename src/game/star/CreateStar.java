package game.star;

import base.FrameCounter;
import base.GameObject;
import game.star.Star;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateStar extends GameObject {

    private Random random;
    private FrameCounter frameCounter;
    private List<Star> stars;

    public CreateStar() {
        this.stars = new ArrayList<>();
        this.random = new Random();
        this.frameCounter = new FrameCounter(30);
    }

    @Override
    public void run() {
        super.run();
        if (this.frameCounter.run()) {
            Star star = new Star();
            star.position.set(1024, this.random.nextInt(600));
            star.velocity.set(this.random.nextInt(3) + 1, 0);
            this.stars.add(star);
            this.frameCounter.reset();
        }

        this.stars.forEach(star -> star.run());
    }

    @Override
    public void render(Graphics graphics) {
        this.stars.forEach(star -> star.render(graphics));
    }
}
