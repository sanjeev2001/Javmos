/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javmos.components.functions;

import java.awt.BasicStroke;
import java.awt.geom.Line2D;
import javmos.JavmosGUI;
import javmos.components.JavmosComponent;
import javmos.enums.FunctionType;

/**
 *
 * @author user
 */
public abstract class Function extends JavmosComponent {
    
    protected Function(JavmosGUI gui) {
        super(gui);
    }
    
    public void draw(java.awt.Graphics2D graphics2D) {
        /*for (double i = gui.getMinDomain(); i < gui.getMaxDomain(); i += 0.01) {
            if (getValueAt(i) <= gui.getMaxRange() && getValueAt(i) >= gui.getMinRange()) {//only draws within the given max and min range
                double x1 = i * gui.getZoom() / gui.getDomainStep();
                double y1 = getValueAt(i) * gui.getZoom() / gui.getRangeStep();
                double x2 = (i + 0.01) * gui.getZoom() / gui.getDomainStep();
                double y2 = getValueAt(i + 0.01) * gui.getZoom() / gui.getRangeStep();
                graphics2D.setStroke(new BasicStroke(3));
                /*400 added to x1 and x2 in order to start drawing from the origin 
                y1 and y2 are subtracted from 400 in order to start drawing from the origin 
                graphics2D.draw(new Line2D.Double(400 + x1, 400 - y1, 400 + x2, 400 - y2));
            }
        }*/
    }
    
    public abstract double getValueAt(double x, FunctionType functionType);
    

}
