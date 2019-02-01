package javmos;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.text.DecimalFormat;
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
        if (x > gui.getMinDomain() && x < gui.getMaxDomain() && y > gui.getMinRange() && y < gui.getMaxRange()) {
            point = new Ellipse2D.Double((gui.getPlaneWidth() / 2) + x * gui.getZoom() / gui.getDomainStep() - 5, (gui.getPlaneWidth() / 2) + y * -gui.getZoom() / gui.getRangeStep() - 5, 10, 10);
            graphics2D.setStroke(new BasicStroke(10));
            graphics2D.setColor(rootType.getPointColor());
            graphics2D.draw(point);
        }
    }

    @Override
    public boolean equals(Object object) {
        //the default equals method must be rewritten due to Point being a user defined object therefore this method is used to inform Java of how to differentiate between two Point objects
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
        //the default hashCode method muse be rewritten du`e to Point being a user defined object therefore this method is used to create identical hashcodes if a Point object has the same parameters
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.gui);
        hash = 37 * hash + Objects.hashCode(this.rootType);
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        return hash;
    }

    @Override
    public String toString() {
        DecimalFormat thousandth = new DecimalFormat("#.###");
        return rootType.name + ": (" + String.valueOf(thousandth.format(x)) + "," + String.valueOf(thousandth.format(y)) + ")";
    }

}
