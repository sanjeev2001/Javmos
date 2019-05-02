/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javmos.components.functions;

import javmos.enums.FunctionType;

/**
 *
 * @author user
 */
public final class Sine extends Trigonometric {

    public Sine(javmos.JavmosGUI gui, String function) {
        super(gui, function, "sin");
    }

    @Override
    public java.lang.String getFirstDerivative() { //Returns a string that contains the first derivative  
        return "f'(x) = " + a * k + "cos(" + k + "x)";
    }

    @Override
    public java.lang.String getSecondDerivative() { //Returns a string that contains the second derivative
        return "f''(x) = " + -a * Math.pow(k, 2) + "sin(" + k + "x)";
    }

    @Override
    public double getValueAt(double x, FunctionType functionType) { //Calculates the value of the sine function given the x value and the function type
        double ans = 0.0;
        if (functionType == FunctionType.FIRST_DERIVATIVE) {
            ans = a * k * Math.cos(k * x);
        } else if (functionType == FunctionType.SECOND_DERIVATIVE) {
            ans = -a * Math.pow(k, 2) * Math.sin(k * x);
        } else if (functionType == FunctionType.THIRD_DERIVATIVE) {
            ans = -a * Math.pow(k, 3) * Math.cos(k * x);
        } else {
            ans = a * Math.sin(k * x);
        }
        return ans;
    }
}
