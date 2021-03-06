/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javmos.components.functions;

import java.util.HashSet;
import javmos.components.Point;
import javmos.enums.FunctionType;

public final class Tangent extends Trigonometric {

    public Tangent(javmos.JavmosGUI gui, String function) {
        super(gui, function, "tan");
    }

    @Override
    public HashSet<Point> getInflectionPoints() {
        return new HashSet<>();
    }

    @Override
    public HashSet<Point> getCriticalPoints() {
        return new HashSet<>();
    }

    @Override
    public String getFirstDerivative() {
        //Returns a string that contains the first derivative
        return "f'(x) = " + a * k + "sec^2(" + k + "x)";
    }

    @Override
    public String getSecondDerivative() {
        //Returns a string that contains the second derivative
        return "f''(x) = " + 2 * a * Math.pow(k, 2) + "sec^2(" + k + "x) * tan(" + k + "x)";
    }

    @Override
    public double getValueAt(double x, FunctionType functionType) {
        //Calculates the value of the tangent function given the x value and the function type
        switch (functionType) {
            case FIRST_DERIVATIVE:
                return a * k * Math.pow(1 / Math.cos(k * x), 2);
            case SECOND_DERIVATIVE:
                return 2 * a * Math.pow(k, 2) * Math.pow(1 / Math.cos(k * x), 2) * Math.tan(k * x);
            case THIRD_DERIVATIVE:
                return 2 * a * Math.pow(k, 2) * (2 * k * Math.pow(1 / Math.cos(k * x), 2) * Math.pow(Math.tan(k * x), 2) + k * Math.pow(1 / Math.cos(k * x), 4));
            default:
                return a * Math.tan(k * x);
        }
    }
}
