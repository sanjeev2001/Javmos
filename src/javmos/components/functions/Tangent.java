/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javmos.components.functions;

import javmos.components.Point;
import javmos.enums.FunctionType;

/**
 *
 * @author user
 */
public final class Tangent extends Trigonometric {

    public Tangent(javmos.JavmosGUI gui, String function) {
        super(gui, function, "tan");
    }

    public java.lang.String getFirstDerivative() {
        return "f'(x) = " + a * k + "sec^2(" + k + "x)";
    }

    public java.lang.String getSecondDerivative() {
        return "f''(x) = " + 2 * a * Math.pow(k, 2) + "sec^2(" + k + "x) * tan(" + k + "x)";
    }

    public java.util.HashSet<Point> getCriticalPoints() {
        return null;
    }

    public java.util.HashSet<Point> getInflectionPoints() {
        return null;
    }

    @Override
    public double getValueAt(double x, FunctionType functionType) {
        double ans = 0.0;
        if (functionType == FunctionType.FIRST_DERIVATIVE) {
            ans = a * k * Math.pow(1 / Math.cos(k * x), 2);
        } else if (functionType == FunctionType.SECOND_DERIVATIVE) {
            ans = 2 * a * Math.pow(k, 2) * Math.pow(1 / Math.cos(k * x), 2) * Math.tan(k * x);
        } else if (functionType == FunctionType.THIRD_DERIVATIVE) {
            ans = 2 * a * Math.pow(k, 2) * (2 * k * Math.pow(1 / Math.cos(k * x), 2) * Math.pow(Math.tan(k * x), 2) + k * Math.pow(1 / Math.cos(k * x), 4));
        } else {
            ans = a * Math.tan(k * x);
        }
        return ans;
    }
}
