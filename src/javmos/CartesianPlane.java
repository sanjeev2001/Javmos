package javmos;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class CartesianPlane extends JPanel {

    public CartesianPlane(JavmosGUI gui) {

    }

    public void drawPlane(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
    }
}
