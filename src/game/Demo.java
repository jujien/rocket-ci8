package game;

import base.GameObject;
import renderer.TextRenderer;

import java.awt.*;

public class Demo extends GameObject {

    public Demo() {
        this.position.set(100, 100);

        this.renderer = new TextRenderer(
                "Hello, world!",
                Color.RED,
                "resources/FiraMono-Bold.ttf",
                20
        );
    }
}
