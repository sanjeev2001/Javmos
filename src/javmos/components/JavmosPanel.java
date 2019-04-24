package javmos.components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import javmos.JavmosGUI;
import javmos.components.functions.Polynomial;
import javmos.enums.RootType;
import javmos.listeners.PointClickListener;

public class JavmosPanel extends javax.swing.JPanel {

    private final JavmosGUI gui;
    private CartesianPlane plane;
    private Polynomial polynomial;
    private final LinkedList<JavmosComponent> points;
    private boolean polynomialChanged = false;

    public JavmosPanel(JavmosGUI gui) {
        this.gui = gui;
        this.plane = null;
        this.polynomial = null;
        this.points = new LinkedList<>();
    }

    public Polynomial getPolynomial() {
        return polynomial;
    }

    public void setPlane(CartesianPlane plane) {
        this.plane = plane;
    }

    public void setPolynomial(Polynomial polynomial) {
        this.polynomial = polynomial;
        polynomialChanged = true;
        points.clear(); //Clears the list so that points that were added from previous polynomials are no longer included
        //points are only added within viewable domain
        //points.addAll(polynomial.getRoots(RootType.X_INTERCEPT, -400 / gui.getZoom() * gui.getDomainStep(), 400 / gui.getZoom() * gui.getDomainStep()));
        //points.addAll(polynomial.getRoots(RootType.CRITICAL_POINT, -400 / gui.getZoom() * gui.getDomainStep(), 400 / gui.getZoom() * gui.getDomainStep()));
        //points.addAll(polynomial.getRoots(RootType.INFLECTION_POINT, -400 / gui.getZoom() * gui.getDomainStep(), 400 / gui.getZoom() * gui.getDomainStep()));
        //Adds all the points to the list
        PointClickListener clickListener = new PointClickListener(gui);
        clickListener.setPoints(points);
        this.addMouseListener(clickListener); //Adds a listener to each point so that they can be clicked
    }

    @Override
    public void paintComponent(Graphics graphics) {
        plane = new CartesianPlane(gui);
        plane.draw((Graphics2D) graphics); //Draws the cartesian plane
        if (polynomialChanged == true) { //Only attempts to draw polynomial and points after a polynomial is entered
            //polynomial.drawPolynomial((Graphics2D) graphics);//Draws the polynomial
            setPolynomial(polynomial);
            for (int i = 0; i < points.size(); i++) {
                points.get(i).draw((Graphics2D) graphics); //Draws all the points that are in the list  
            }
        }
    }
}
