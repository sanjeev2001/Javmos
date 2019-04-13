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

    @Override
    public double getValueAt(double x, FunctionType functionType) {
        return 0.0;
    }
    
}
