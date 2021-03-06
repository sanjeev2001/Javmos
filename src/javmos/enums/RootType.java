package javmos.enums;

import java.awt.Color;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashSet;
import javmos.JavmosGUI;
import javmos.components.Point;
import javmos.components.functions.Function;

public enum RootType {
    CRITICAL_POINT(Color.RED, "Critical Point", FunctionType.FIRST_DERIVATIVE, FunctionType.SECOND_DERIVATIVE),
    INFLECTION_POINT(Color.BLUE, "Inflection Point", FunctionType.SECOND_DERIVATIVE, FunctionType.THIRD_DERIVATIVE),
    X_INTERCEPT(Color.GREEN, "x-intercept", FunctionType.ORIGINAL, FunctionType.FIRST_DERIVATIVE);

    public final int ATTEMPTS = 100;
    public final FunctionType functionOne;
    public final FunctionType functionTwo;
    public final Color rootColor;
    public final String rootName;

    RootType(Color color, String name, FunctionType functionOne, FunctionType functionTwo) {
        this.rootColor = color;
        this.rootName = name;
        this.functionOne = functionOne;
        this.functionTwo = functionTwo;
    }

    public Color getRootColor() {
        return rootColor;
    }

    public String getRootName() {
        return rootName;
    }

    public HashSet<Point> getRoots(JavmosGUI gui, Function function, double minDomain, double maxDomain) {
        DecimalFormat thousandth = new DecimalFormat("#.###");
        thousandth.setRoundingMode(RoundingMode.HALF_DOWN);
        HashSet<Point> roots = new HashSet<>();
        minDomain = -400 / gui.getZoom() * gui.getDomainStep();
        maxDomain = 400 / gui.getZoom() * gui.getDomainStep();
        //Runs newtons method across a certain domain to find roots based on the given rootType, roots are then added to the hashet to be later used to draw the points
        for (double i = minDomain; i < maxDomain; i += 0.1) {
            if (newtonsMethod(function, i, ATTEMPTS) != null) {
                if (rootName.equals("x-intercept")) {
                    roots.add(new Point(gui, this, Double.parseDouble(thousandth.format(newtonsMethod(function, i, ATTEMPTS))), 0.0));
                } else {
                    roots.add(new Point(gui, this, Double.parseDouble(thousandth.format(newtonsMethod(function, i, ATTEMPTS))), function.getValueAt(newtonsMethod(function, i, ATTEMPTS), FunctionType.ORIGINAL)));
                }
            }
        }
        return roots;
    }

    protected Double newtonsMethod(Function function, double guess, int attempts) {
        double numerator = function.getValueAt(guess, functionOne);
        double denominator = function.getValueAt(guess, functionTwo);
        double ans = guess - (numerator / denominator);
        //null is returned if Newtons method is undefined or root not found, if new value - old value <= 0.000001 and root is in fact 0, a point has been converged on otherwise Newtons method is run again with new guess
        if (attempts == 0 || denominator == 0) {
            return null;
        } else if (Math.abs((Math.abs(ans) - Math.abs(guess))) <= 0.000001 && function.getValueAt(ans, functionOne) <= 0.000001 && function.getValueAt(ans, functionOne) >= -0.000001) {
            return ans;
        } else {
            return newtonsMethod(function, ans, attempts - 1);
        }
    }
}
