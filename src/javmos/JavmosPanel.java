package javmos;

import java.awt.BasicStroke;
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
        Graphics2D thinLine = (Graphics2D) graphics;
        Graphics2D boldLine = (Graphics2D) graphics;

        for (int i = 0; i < 800 / 50; i++) {
            thinLine.drawLine(50 * i, 0, 50 * i, 800); //(x1, y1, x2, y2) draws thin vertical line every 50px
            thinLine.drawLine(0, 50 * i, 800, 50 * i);
        }

        boldLine.setStroke(new BasicStroke(3));
        boldLine.drawLine(400, 0, 400, 800); //y-axis
        boldLine.drawLine(0, 400, 800, 400); //x-axis
    }
}
