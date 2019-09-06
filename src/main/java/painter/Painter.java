package painter;

import java.awt.*;

class Painter {

    private Graphics2D graphics;
    private int width;
    private int height;
    private static final double HORIZON_Y_PART = 0.3;

    void paint(Graphics2D graphics, int width,int height) {
        this.graphics = graphics;
        this.width = width;
        this.height = height;
    }

    void paintSky() {
        Rectangle background = new Rectangle(0, 0, width, (int)Math.round(height * HORIZON_Y_PART));
        graphics.draw(background);
        graphics.draw(new Rectangle(10, 10, 50, 50));

    }

}
