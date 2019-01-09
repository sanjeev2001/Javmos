package javmos;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Point {

    public final JavmosGUI gui;
    public Ellipse2D.Double point;
    public final int RADIUS = 5;
    public final RootType rootType;
    public double x;
    public double y;

    public Point(JavmosGUI gui, RootType type, double x, double y) {
        this.gui = gui;
        this.rootType = type;
        this.x = x;
        this.y = y;
    }

    public void drawPoint(Graphics2D graphics2D) {
        /*for (int num = 0; num < roots.size() - 1; num++) {
            Ellipse2D.Double oval1 = new Ellipse2D.Double(400 + roots.get(num) + gui.getMaxDomain() / gui.getDomainStep(), 0, 100, 100);
            graphics2D.setColor(rootType.getPointColor());
            graphics2D.fill(oval1);
            graphics2D.draw(oval1);
        }*/
    }

    @Override
    public boolean equals(Object object) {
        // Complete me
        return false;
    }

    public Ellipse2D.Double getPoint() {
        return point;
    }

    public RootType getRootType() {
        // Complete me
        return rootType;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public int hashCode() {
        // Complete me
        return 0;
    }

    @Override
    public String toString() {
        return rootType.name + "(" + String.valueOf(x) + "," + String.valueOf(y) + ")";
    }

}
