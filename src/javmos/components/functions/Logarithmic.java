/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javmos.components.functions;

import java.util.HashSet;
import javmos.JavmosGUI;
import javmos.components.Point;
import javmos.enums.FunctionType;
import javmos.enums.RootType;


/**
 *
 * @author user
 */
public class Logarithmic extends Function {
   public double a;
   public double base;
   public double k;

    public Logarithmic(JavmosGUI gui, String function) {
        super(gui);
        function = function.contains("=") ? function.substring(function.indexOf("=") + 1, function.length()) : function;
        

        if (function.substring(0, 1).equals("l")) {
            a = 1;
        } else if (function.substring(0, 2).equals("-l")) {
            a = -1;
        } else {
            a = Double.parseDouble(function.substring(0, function.indexOf("l")));
        }

        if (function.contains("ln")) {
            base = Math.E;
        } else if (function.contains("g(")) {
            base = 10;
        } else {
            base = Double.parseDouble(function.substring(function.indexOf("g") + 1, function.indexOf("(")));
        }

        if (function.contains("(x")) {
            k = 1;
        } else if (function.contains("(-x")) {
            k = -1;
        } else {
            k = Double.parseDouble(function.substring(function.indexOf("(") + 1, function.indexOf("x")));
        }
    }

   @Override
    public HashSet<Point> getCriticalPoints() {
        return new HashSet<>();
    }

   @Override
    public String getFirstDerivative() {
        return base == Math.E ? "f'(x) = " + a + "/" + "x" : "f'(x) = " + a + "/" + "(xln" + base + ")";
    }

   @Override
    public HashSet<Point> getInflectionPoints() {
        return new HashSet<>();
    }

   @Override
    public String getSecondDerivative() {
        String sign = a > 0 ? "-" : "";
        return base == Math.E ? "f'(x) = " + sign + a + "/" + "x^2" : "f'(x) = " + sign + a + "/" + "(x^2ln" + base + ")";
    }

    @Override
    public double getValueAt(double x, FunctionType functionType) {
        if (functionType == FunctionType.FIRST_DERIVATIVE) {
            return a / (x * Math.log(base));
        } else if (functionType == FunctionType.SECOND_DERIVATIVE) {
            return -a / (Math.pow(x, 2) * Math.log(base));
        } else if (functionType == FunctionType.THIRD_DERIVATIVE) {
            return (2 * a) / (Math.log(base) * Math.pow(x, 3));
        } else {
            return a * (Math.log10(k * x) / Math.log10(base));
        }
    }
}
