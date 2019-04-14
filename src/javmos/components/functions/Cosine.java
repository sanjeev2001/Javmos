/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javmos.components.functions;
import javmos.JavmosGUI;
import javmos.enums.FunctionType;

/**
 *
 * @author user
 */
public final class Cosine extends Trigonometric    {

    public Cosine(JavmosGUI gui,  String function) {
        super(gui, function, "cos");
    }
    
    public java.lang.String getFirstDerivative() {
        return "f'(x) = " + -a * k + "sin(" + k + "x)";
    }
    
    public java.lang.String getSecondDerivative() {
        return "f''(x) = " + a * Math.pow(k,2) + "cos(" + k + "x)";
    }

    @Override
    public double getValueAt(double x, FunctionType functionType) {
        double ans = 0.0;
        if (functionType == FunctionType.FIRST_DERIVATIVE) {
            ans = -a * k * Math.sin(k * x);
        } else if (functionType == FunctionType.SECOND_DERIVATIVE) {
            ans = -a * Math.pow(k, 2) * Math.cos(k * x);
        } else if (functionType == FunctionType.THIRD_DERIVATIVE) {
            ans = a * Math.pow(k, 3) * Math.sin(k * x);
        } else {
            ans = a * Math.cos(k * x);
        }
        return ans;
    }
    
}
