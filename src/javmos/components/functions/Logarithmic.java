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
public class Logarithmic extends Function {
   public double a;
   public double base;
   public double k;

    public Logarithmic(JavmosGUI gui, String function) {
        super(gui);
        function = function.contains("=") ? function.substring(function.indexOf("=") + 1, function.length()) : function;
        double a;
        double base;
        double k;

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
    public double getValueAt(double x, FunctionType functionType) {
        return 0.0;
    }
}
