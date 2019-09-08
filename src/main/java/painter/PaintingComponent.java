package painter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.concurrent.ThreadLocalRandom;

public class PaintingComponent extends JComponent {

    private static final double HORIZON_Y_RATIO = 0.4;
    private static final double SUN_RATIO = 0.15;
    private static final double SUN_OFFSET_RATIO = 0.02;
    private static final int NUM_OF_CLOUDS = 5;
    private static final double CLOUD_MIN_WIDTH_RATIO = 0.07;
    private static final double CLOUD_MAX_WIDTH_RATIO = 0.12;
    private static final double CLOUD_HEIGHT_TO_WIDTH_RATIO = 0.7;
    private static final double NUM_OF_TREES = 3;
    private static final double TREE_SIZE_RATIO = 0.2;

    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        paintSky(graphics2D);
        paintNature(graphics2D);
    }

    private void paintSky(Graphics2D graphics2D) {
        paintSkyBackground(graphics2D);
        paintSun(graphics2D);
        paintClouds(graphics2D);
    }

    private void paintSkyBackground(Graphics2D graphics2D) {
        double heightOfSky = HORIZON_Y_RATIO * getHeight();
        Rectangle2D rectangle = new Rectangle(0, 0, getWidth(), (int) heightOfSky);
        fillShape(graphics2D, rectangle, Color.BLUE);
    }

    private void paintSun(Graphics2D graphics2D) {
        double radius = getWidth() * SUN_RATIO;
        double sunOffset = getWidth() * SUN_OFFSET_RATIO;
        double x = getWidth() - radius - sunOffset;
        Ellipse2D sun = new Ellipse2D.Double(x, sunOffset, radius, radius);
        fillShape(graphics2D, sun, Color.YELLOW);
    }

    private void paintClouds(Graphics2D graphics2D) {
        for (int i = 1; i <= NUM_OF_CLOUDS; i++) {
            paintSingleCloud(graphics2D);
        }
    }

    private void paintSingleCloud(Graphics2D graphics2D) {
        double width = getRandomCloudWidth();
        double height = width * CLOUD_HEIGHT_TO_WIDTH_RATIO;
        int x = ThreadLocalRandom.current().nextInt(0, getWidth());
        int y = ThreadLocalRandom.current().nextInt(0, (int) (getHeight() * HORIZON_Y_RATIO - height));
        Ellipse2D cloud = new Ellipse2D.Double(x, y, width, height);
        fillShape(graphics2D, cloud, Color.WHITE);
    }

    private double getRandomCloudWidth() {
        double minWidth = getWidth() * CLOUD_MIN_WIDTH_RATIO;
        double maxWidth = getWidth() * CLOUD_MAX_WIDTH_RATIO;
        return ThreadLocalRandom.current().nextDouble(minWidth, maxWidth);
    }

    private void paintNature(Graphics2D graphics2D) {
        paintGrass(graphics2D);
        paintSingleTree(graphics2D);
    }

    private void paintGrass(Graphics2D graphics2D) {
        double y = getHeight() * HORIZON_Y_RATIO;
        Rectangle2D grass = new Rectangle2D.Double(0, y, getWidth(), getHeight() - y);
        fillShape(graphics2D, grass, Color.GREEN);
    }

    private void paintTrees(Graphics2D graphics2D) {
        for (int i = 1; i <= NUM_OF_TREES; i++) {
            paintSingleTree(graphics2D);
        }
    }

    private void paintSingleTree(Graphics2D graphics2D) {
        double x = ThreadLocalRandom.current().nextDouble(0, getWidth());
        double y = ThreadLocalRandom.current().nextDouble(getHeight() * HORIZON_Y_RATIO, getHeight());
        paintTrunk(graphics2D, x, y);
        paintCrown(graphics2D, x, y);
    }

    private void paintTrunk(Graphics2D graphics2D, double x, double y) {
        double trunkHeight = TREE_SIZE_RATIO * getHeight();
        double trunkWidth = trunkHeight * 0.2;
        Rectangle2D trunk = new Rectangle2D.Double(x - trunkWidth/2, y, trunkWidth, trunkHeight);
        fillShape(graphics2D, trunk, new Color(102, 41, 0));
    }

    private void paintCrown(Graphics2D graphics2D, double x, double y) {
        double crownRadius = getHeight() * TREE_SIZE_RATIO * 0.7;
        Ellipse2D crown = new Ellipse2D.Double(x - crownRadius/2, y - crownRadius/2, crownRadius, crownRadius);
        fillShape(graphics2D, crown, new Color(0, 102, 0));
    }

    private void fillShape(Graphics2D graphics2D, Shape shape, Color color) {
        graphics2D.setColor(color);
        graphics2D.fill(shape);
    }

}
