package javmos.enums;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.HashSet;
import javmos.JavmosGUI;
import javmos.components.Point;
import javmos.components.functions.Function;

public enum RootType {
    X_INTERCEPT(Color.GREEN, "x-intercept", FunctionType.ORIGINAL, FunctionType.FIRST_DERIVATIVE),
    CRITICAL_POINT(Color.RED, "Critical Point", FunctionType.FIRST_DERIVATIVE, FunctionType.SECOND_DERIVATIVE),
    INFLECTION_POINT(Color.BLUE, "Inflection Point", FunctionType.SECOND_DERIVATIVE, FunctionType.THIRD_DERIVATIVE);
    
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
        HashSet<Point> roots = new HashSet<>();

        //Runs newtons method across a certain domain to find roots based on the given rootType, roots are then added to the hashet to be later used to draw the points
        for (double i = gui.getMinDomain(); i < gui.getMaxDomain(); i += 0.1) {
            if (newtonsMethod(function, i, ATTEMPTS) != null) {
                if (rootName.equals("x-intercept")) {
                    roots.add(new Point(gui, this, this.newtonsMethod(function, i, ATTEMPTS), 0.0));
                } else {
                    roots.add(new Point(gui, this, this.newtonsMethod(function, i, ATTEMPTS), function.getValueAt(this.newtonsMethod(function, i, ATTEMPTS), FunctionType.ORIGINAL)));
                }
            }
        }
        return roots;
    }

    protected Double newtonsMethod(Function function, double guess, int attempts) {
        DecimalFormat thousandth = new DecimalFormat("#.###");
        double numerator = function.getValueAt(guess, functionOne);
        double denominator = function.getValueAt(guess, functionTwo);
        double ans = guess - (numerator / denominator);
        //null is returned if Newtons method is undefined, if new value - old value = 0.000001 a point has been converged on otherwise Newtons method is run again with new guess
        if (attempts == 0 || denominator == 0) {
            return null;
        } else if (Math.abs((Math.abs(ans) - Math.abs(guess))) <= 0.000001 && function.getValueAt(ans, functionOne) <= 0.01 && function.getValueAt(ans, functionOne) >= -0.01) {
            return Double.parseDouble(thousandth.format(ans));
        } else {
            return newtonsMethod(function, ans, attempts - 1);
        }
    }
}
