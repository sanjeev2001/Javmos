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
    
    public final int ATTEMPTS = 15;
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
        return null;
    }

    protected Double newtonsMethod(RootType rootType, double guess, int attempts) {
        DecimalFormat thousandth = new DecimalFormat("#.###");
        //Function function = new Function(gui);
        double numerator = 0;
        double denominator = 0;

        //Based on the rootType parameter f(x) (numerator) and f'(x) (denominator) are determined
        switch (rootType.getRootName()) {
            case "x-intercept":
                //numerator = function.getValueAt(guess, functionOne);
                //denominator = new Polynomial(gui, coefficients, degrees).getDerivative();
                break;
            case "Critical Point":
                //numerator = new Polynomial(gui, coefficients, degrees).getDerivative();
                //denominator = new Polynomial(gui, coefficients, degrees).getDerivative().getDerivative();
                break;
            default:
                //numerator = new Polynomial(gui, coefficients, degrees).getDerivative().getDerivative();
                //denominator = new Polynomial(gui, coefficients, degrees).getDerivative().getDerivative().getDerivative();
                break;
        }

        //null is returned if Newtons method is undefined, if new value - old value = 0.000001 a point has been converged on otherwise Newtons method is run again with new guess
        if (attempts == 0 || denominator == 0) {
            return null;
        } else if (Math.abs((guess - (numerator / denominator) - guess)) <= 0.000001) {
            return Double.parseDouble(thousandth.format(guess - (numerator / denominator)));
        } else {
            return newtonsMethod(rootType, guess - (numerator / denominator), attempts - 1);
        }
    }
}
