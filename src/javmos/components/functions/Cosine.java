/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javmos.components.functions;

import javmos.JavmosGUI;
import javmos.enums.FunctionType;

public final class Cosine extends Trigonometric {

    public Cosine(JavmosGUI gui, String function) {
        super(gui, function, "cos");
    }

    @Override
    public java.lang.String getFirstDerivative() {
        return "f'(x) = " + -a * k + "sin(" + k + "x)"; //Returns a string that contains the first derivative
    }

    @Override
    public java.lang.String getSecondDerivative() {
        return "f''(x) = " + a * Math.pow(k, 2) + "cos(" + k + "x)"; //Returns a string that contains the second derivative
    }

    @Override
    public double getValueAt(double x, FunctionType functionType) { //Calculates the value of the cosine function given the x value and the function type  
        if (functionType == FunctionType.FIRST_DERIVATIVE) {
            return -a * k * Math.sin(k * x);
        } else if (functionType == FunctionType.SECOND_DERIVATIVE) {
            return -a * Math.pow(k, 2) * Math.cos(k * x);
        } else if (functionType == FunctionType.THIRD_DERIVATIVE) {
            return a * Math.pow(k, 3) * Math.sin(k * x);
        } else {
            return a * Math.cos(k * x);
        }
    }
}
