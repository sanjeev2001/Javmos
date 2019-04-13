/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javmos.components.functions;

import java.awt.Graphics2D;
import javmos.JavmosGUI;


/**
 *
 * @author user
 */
public class Logarithmic extends Function {
   public double a;
   public double base;
   public double k;

   public Logarithmic(JavmosGUI gui, String function){
       super(gui);
   }
}
