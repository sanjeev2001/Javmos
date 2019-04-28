package javmos.components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import javmos.JavmosGUI;
import javmos.components.functions.Function;
import javmos.listeners.PointClickListener;

public class JavmosPanel extends javax.swing.JPanel {

    private final JavmosGUI gui;
    private CartesianPlane plane;
    private Function function;
    private final LinkedList<JavmosComponent> points;
    private boolean funcionChanged = false;

    public JavmosPanel(JavmosGUI gui) {
        this.gui = gui;
        this.plane = null;
        this.function = null;
        this.points = new LinkedList<>();
    }

    public Function getFunction() {
        return function;
    }

    public void setPlane(CartesianPlane plane) {
        this.plane = plane;
    }

    public void setFunction(Function function) {
        this.function = function;
        funcionChanged = true;
        points.clear(); //Clears the list so that points that were added from previous polynomials are no longer included
        //points are only added within viewable domain
        points.addAll(function.getXIntercepts());
        points.addAll(function.getCriticalPoints());
        points.addAll(function.getInflectionPoints());
        //Adds all the points to the list
        PointClickListener clickListener = new PointClickListener(gui);
        clickListener.setPoints(points);
        this.addMouseListener(clickListener); //Adds a listener to each point so that they can be clicked
    }

    @Override
    public void paintComponent(Graphics graphics) {
        plane = new CartesianPlane(gui);
        plane.draw((Graphics2D) graphics); //Draws the cartesian plane
        if (funcionChanged == true) { //Only attempts to draw polynomial and points after a polynomial is entered
            function.draw((Graphics2D) graphics);//Draws the polynomial
            setFunction(function);
            for (int i = 0; i < points.size(); i++) {
                points.get(i).draw((Graphics2D) graphics); //Draws all the points that are in the list  
            }
        }
    }
}
