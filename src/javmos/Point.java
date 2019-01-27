package javmos;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Objects;

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
        point = new Ellipse2D.Double((gui.getPlaneWidth() / 2) + x * gui.getZoom() / gui.getDomainStep() - 5, (gui.getPlaneWidth() / 2) + y * -gui.getZoom() / gui.getRangeStep() - 5, 10, 10);
        graphics2D.setStroke(new BasicStroke(10));
        graphics2D.setColor(rootType.getPointColor());
        graphics2D.draw(point);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (object instanceof Point) {
            Point test = (Point) object;
            if (test.gui == this.gui && test.getRootType() == this.rootType && test.getX() == this.x && test.getY() == this.y) {
                return true;
            }
        }
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
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.gui);
        hash = 37 * hash + Objects.hashCode(this.rootType);
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        return hash;
    }

    @Override
    public String toString() {
        return rootType.name + "(" + String.valueOf(x) + "," + String.valueOf(y) + ")";
    }

}
