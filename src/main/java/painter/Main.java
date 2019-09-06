package painter;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        initWindow();
    }

    private static void initWindow() {
        JFrame window = new JFrame();
        window.setSize(500, 500);
        window.setTitle("Painting");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
        PaintingComponent paintingComponent = new PaintingComponent();
        window.add(paintingComponent);
    }

}
