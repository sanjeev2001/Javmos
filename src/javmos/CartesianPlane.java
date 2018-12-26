package javmos;

import java.awt.BasicStroke;
import java.awt.Color;

public class CartesianPlane extends java.lang.Object {

    public final JavmosGUI gui;

    public CartesianPlane(JavmosGUI gui) {
        this.gui = gui;
    }

    public void drawPlane(java.awt.Graphics2D graphics2D) {
        int test = (int) gui.getZoom();
        if (test == 50) {
            for (int i = 0; i < 800 / 50; i++) {
                graphics2D.drawLine(50 * i, 0, 50 * i, 800); //(x1, y1, x2, y2) draws thin vertical line every 50px
                graphics2D.drawLine(0, 50 * i, 800, 50 * i);
            }
            graphics2D.setStroke(new BasicStroke(3));
            graphics2D.drawLine(400, 0, 400, 800); //y-axis
            graphics2D.drawLine(0, 400, 800, 400); //x-axis

            for (int i = 0; i < 800 / 50; i++) {
                graphics2D.drawLine(50 * i, 395, 50 * i, 405); 
                graphics2D.drawLine(395, 50 * i, 405, 50 * i);
            }
        } else {
            graphics2D.clearRect(0, 0, 800, 800);
            for (int i = 0; i < 800 / test; i++) {
                graphics2D.drawLine(test * i, 0, test * i, 800); //(x1, y1, x2, y2) draws thin vertical line every 50px
                graphics2D.drawLine(0, test * i, 800, test * i);
            }
            graphics2D.setStroke(new BasicStroke(3));
            graphics2D.drawLine(400, 0, 400, 800); //y-axis
            graphics2D.drawLine(0, 400, 800, 400); //x-axis

            for (int i = 0; i < 800 / test; i++) {
                graphics2D.drawLine(test * i, 395, test * i, 405); 
                graphics2D.drawLine(395, test * i, 405, test * i);
            }
        }

    }
}
