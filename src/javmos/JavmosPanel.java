package javmos;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import javax.swing.JPanel;

public class JavmosPanel extends JPanel {

    private final JavmosGUI gui;
    private CartesianPlane plane;
    private Polynomial polynomial;
    private final LinkedList<Point> points;
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
    }

    @Override
    public void paintComponent(Graphics graphics) {
        plane = new CartesianPlane(gui);
        plane.drawPlane((Graphics2D) graphics);
        if (polynomialChanged == true) {
            polynomial.drawPolynomial((Graphics2D) graphics);
        }
    }
}
