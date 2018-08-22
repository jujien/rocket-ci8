package renderer;

import base.Vector2D;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TextRenderer implements Renderer {

    private String text;
    private Color color;
    private Font font;

//    public TextRenderer(String text, Color color, String fontName, int fontSize) {
//        this.text = text;
//        this.color = color;
//        this.font = new Font(fontName, Font.PLAIN, fontSize);
//    }


    public TextRenderer(String text, Color color, String path, int fontSize) {
        this.text = text;
        this.color = color;
        try {
            this.font = Font.createFont(Font.PLAIN, new File(path)).deriveFont(Font.PLAIN, fontSize);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(Graphics graphics, Vector2D position) {
        graphics.setColor(this.color);
        graphics.setFont(this.font);
        graphics.drawString(this.text, (int) position.x, (int) position.y);
    }
}
