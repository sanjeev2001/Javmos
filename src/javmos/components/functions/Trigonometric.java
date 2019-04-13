/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javmos.components.functions;

import javmos.JavmosGUI;

/**
 *
 * @author user
 */
public abstract class Trigonometric extends Function {

    protected double a;
    protected double k;

    public Trigonometric(JavmosGUI gui, String function, String name) {
        super(gui);
        
    }

}
