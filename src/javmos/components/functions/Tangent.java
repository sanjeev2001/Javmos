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
public final class Tangent extends Trigonometric{
    public Tangent(javmos.JavmosGUI gui,  String function) {
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

    @Override
    public double getValueAt(double x, FunctionType functionType) {
        return 0.0;
    }
}
