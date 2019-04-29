/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javmos.components.functions;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Line2D;
import java.util.HashSet;
import javmos.JavmosGUI;
import javmos.components.JavmosComponent;
import javmos.components.Point;
import javmos.enums.FunctionType;
import javmos.enums.RootType;

/**
 *
 * @author user
 */
public abstract class Function extends JavmosComponent {
    
    protected Function(JavmosGUI gui) {
        super(gui);
    }
    
    public void draw(java.awt.Graphics2D graphics2D) {
        for (double i = gui.getMinDomain(); i < gui.getMaxDomain(); i += 0.01) {
            if (getValueAt(i, FunctionType.ORIGINAL) <= gui.getMaxRange() && getValueAt(i, FunctionType.ORIGINAL) >= gui.getMinRange()) {//only draws within the given max and min range
                double x1 = i * gui.getZoom() / gui.getDomainStep();
                double y1 = getValueAt(i, FunctionType.ORIGINAL) * gui.getZoom() / gui.getRangeStep();
                double x2 = (i + 0.01) * gui.getZoom() / gui.getDomainStep();
                double y2 = getValueAt(i + 0.01, FunctionType.ORIGINAL) * gui.getZoom() / gui.getRangeStep();
                graphics2D.setStroke(new BasicStroke(3));
                graphics2D.setColor(Color.black);
                graphics2D.draw(new Line2D.Double(400 + x1, 400 - y1, 400 + x2, 400 - y2));
            }
        }
    }

    public HashSet<Point> getCriticalPoints() {
        return RootType.CRITICAL_POINT.getRoots(gui, this, gui.getMinDomain(), gui.getMaxDomain());
    }

    public abstract String getFirstDerivative();

    public HashSet<Point> getInflectionPoints() {
        return RootType.INFLECTION_POINT.getRoots(gui, this, gui.getMinDomain(), gui.getMaxDomain());
    }

    public abstract String getSecondDerivative();

    public abstract double getValueAt(double x, FunctionType functionType);
    
    public HashSet<Point> getXIntercepts() {
        return RootType.X_INTERCEPT.getRoots(gui, this, gui.getMinDomain(), gui.getMaxDomain());
    }
}
