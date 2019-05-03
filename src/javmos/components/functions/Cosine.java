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
    public String getFirstDerivative() {
        //Returns a string that contains the first derivative
        return "f'(x) = " + -a * k + "sin(" + k + "x)";
    }

    @Override
    public String getSecondDerivative() {
        //Returns a string that contains the second derivative
        return "f''(x) = " + a * Math.pow(k, 2) + "cos(" + k + "x)";
    }

    @Override
    public double getValueAt(double x, FunctionType functionType) {
        //Calculates the value of the cosine function given the x value and the function type
        switch (functionType) {
            case FIRST_DERIVATIVE:
                return -a * k * Math.sin(k * x);
            case SECOND_DERIVATIVE:
                return -a * Math.pow(k, 2) * Math.cos(k * x);
            case THIRD_DERIVATIVE:
                return a * Math.pow(k, 3) * Math.sin(k * x);
            default:
                return a * Math.cos(k * x);
        }
    }
}
