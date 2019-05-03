/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javmos.components.functions;

import javmos.JavmosGUI;
import javmos.enums.FunctionType;

public final class Sine extends Trigonometric {

    public Sine(JavmosGUI gui, String function) {
        super(gui, function, "sin");
    }

    @Override
    public String getFirstDerivative() {
        //Returns a string that contains the first derivative
        return "f'(x) = " + a * k + "cos(" + k + "x)";
    }

    @Override
    public String getSecondDerivative() {
        //Returns a string that contains the second derivative
        return "f''(x) = " + -a * Math.pow(k, 2) + "sin(" + k + "x)";
    }

    @Override
    public double getValueAt(double x, FunctionType functionType) {
        //Calculates the value of the sine function given the x value and the function type
        switch (functionType) {
            case FIRST_DERIVATIVE:
                return a * k * Math.cos(k * x);
            case SECOND_DERIVATIVE:
                return -a * Math.pow(k, 2) * Math.sin(k * x);
            case THIRD_DERIVATIVE:
                return -a * Math.pow(k, 3) * Math.cos(k * x);
            default:
                return a * Math.sin(k * x);
        }
    }
}
