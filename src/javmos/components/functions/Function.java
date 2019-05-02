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

public abstract class Function extends JavmosComponent {

    protected Function(JavmosGUI gui) {
        super(gui);
    }

    @Override
    public void draw(java.awt.Graphics2D graphics2D) {
        String function = gui.getEquationField();
        double a = 0;
        double k = 0;
        String name = "tan";
        double x1, x2, y1, y2;
        for (double i = gui.getMinDomain(); i < gui.getMaxDomain(); i += 0.01) {
            //only draws within the given max and min range
            if (getValueAt(i, FunctionType.ORIGINAL) <= gui.getMaxRange() && getValueAt(i, FunctionType.ORIGINAL) >= gui.getMinRange()) {
                x1 = i * gui.getZoom() / gui.getDomainStep();
                y1 = getValueAt(i, FunctionType.ORIGINAL) * gui.getZoom() / gui.getRangeStep();
                x2 = (i + 0.01) * gui.getZoom() / gui.getDomainStep();
                y2 = getValueAt(i + 0.01, FunctionType.ORIGINAL) * gui.getZoom() / gui.getRangeStep();
                graphics2D.setStroke(new BasicStroke(3));
                graphics2D.setColor(Color.black);
                //Creates the line object to be drawn
                Line2D.Double line = new Line2D.Double(400 + x1, 400 - y1, 400 + x2, 400 - y2);
                //System.out.println("y1: " + line.getY1() + " y2: " + line.getY2());
                if (function.contains("tan")) {
                    //Checks the sign of the a and k value in order to avoid drawing asymptotes
                    if (function.substring(0, 1).equals(Character.toString(name.charAt(0)))) {
                        a = 1;
                    } else if (function.substring(0, 2).equals("-" + name.charAt(0))) {
                        a = -1;
                    } else {
                        a = Double.parseDouble(function.substring(0, function.indexOf(Character.toString(name.charAt(0)))));
                    }

                    if (function.contains("(x")) {
                        k = 1;
                    } else if (function.contains("(-x")) {
                        k = -1;
                    } else {
                        k = Double.parseDouble(function.substring(function.indexOf("(") + 1, function.indexOf("x")));
                    }

                    if (a < 0 && k < 0) {
                        a = 1;
                        k = 1;
                    }
                }
                if (!(function.contains("tan")) || (line.getY1() > line.getY2() && k > 0 && a > 0) || (line.getY2() > line.getY1() && (k < 0 || a < 0))) {
                    graphics2D.draw(line);
                }
            }
        }
    }

    public HashSet<Point> getXIntercepts() {
        return RootType.X_INTERCEPT.getRoots(gui, this, -400 / gui.getZoom() * gui.getDomainStep(), 400 / gui.getZoom() * gui.getDomainStep());//Uses getRoots to find the x-intercepts within the domain that is on screen
    }

    public HashSet<Point> getCriticalPoints() {
        return RootType.CRITICAL_POINT.getRoots(gui, this, -400 / gui.getZoom() * gui.getDomainStep(), 400 / gui.getZoom() * gui.getDomainStep());//Uses getRoots to find the critical points within the domain that is on screen
    }

    public HashSet<Point> getInflectionPoints() {
        return RootType.INFLECTION_POINT.getRoots(gui, this, -400 / gui.getZoom() * gui.getDomainStep(), 400 / gui.getZoom() * gui.getDomainStep());//Uses getRoots to find the inflection points within the domain that is on screen
    }

    public abstract String getFirstDerivative();

    public abstract String getSecondDerivative();

    public abstract double getValueAt(double x, FunctionType functionType);
}
