package javmos;

import java.awt.Color;
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
