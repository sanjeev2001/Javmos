package javmos.enums;

import java.awt.Color;
import javmos.JavmosGUI;
import javmos.components.Point;
import javmos.components.functions.Function;

public enum RootType {
    X_INTERCEPT(Color.GREEN, "x-intercept", FunctionType.ORIGINAL, FunctionType.FIRST_DERIVATIVE),
    CRITICAL_POINT(Color.RED, "Critical Point", FunctionType.FIRST_DERIVATIVE, FunctionType.SECOND_DERIVATIVE),
    INFLECTION_POINT(Color.BLUE, "Inflection Point", FunctionType.SECOND_DERIVATIVE, FunctionType.THIRD_DERIVATIVE);
    
    public final int ATTEMPTS = 15;
    public final FunctionType functionOne;
    public final FunctionType functionTwo;
    public final java.awt.Color rootColor;
    public final java.lang.String rootName;

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

    public java.util.HashSet<Point> getRoots(JavmosGUI gui, Function function, double minDomain, double maxDomain) {
        return null;
    }
/*protected Double newtonsMethod(RootType rootType, double guess, int attempts) {
        DecimalFormat thousandth = new DecimalFormat("#.###");
        Polynomial numerator;
        Polynomial denominator;

        //Based on the rootType parameter f(x) (numerator) and f'(x) (denominator) are determined
        switch (rootType.getRootName()) {
            case "x-intercept":
                numerator = new Polynomial(gui, coefficients, degrees);
                denominator = new Polynomial(gui, coefficients, degrees).getDerivative();
                break;
            case "Critical Point":
                numerator = new Polynomial(gui, coefficients, degrees).getDerivative();
                denominator = new Polynomial(gui, coefficients, degrees).getDerivative().getDerivative();
                break;
            default:
                numerator = new Polynomial(gui, coefficients, degrees).getDerivative().getDerivative();
                denominator = new Polynomial(gui, coefficients, degrees).getDerivative().getDerivative().getDerivative();
                break;
        }

        //null is returned if Newtons method is undefined, if new value - old value = 0.000001 a point has been converged on otherwise Newtons method is run again with new guess
        if (attempts == 0 || denominator.getValueAt(guess) == 0) {
            return null;
        } else if (Math.abs((guess - (numerator.getValueAt(guess) / denominator.getValueAt(guess))) - guess) <= 0.000001) {
            return Double.parseDouble(thousandth.format(guess - (numerator.getValueAt(guess) / denominator.getValueAt(guess))));
        } else {
            return newtonsMethod(rootType, guess - (numerator.getValueAt(guess) / denominator.getValueAt(guess)), attempts - 1);
        }
    }*/
    
}
