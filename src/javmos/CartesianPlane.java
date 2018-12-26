package javmos;

import java.awt.BasicStroke;

public class CartesianPlane extends java.lang.Object {

    public final JavmosGUI gui;

    public CartesianPlane(JavmosGUI gui) {
        this.gui = gui;
    }

    public void drawPlane(java.awt.Graphics2D graphics2D) {
        
        for (int i = 0; i < 800 / 50; i++) {
            graphics2D.drawLine(50 * i, 0, 50 * i, 800); //(x1, y1, x2, y2) draws thin vertical line every 50px
            graphics2D.drawLine(0, 50 * i, 800, 50 * i);
        }
        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.drawLine(400, 0, 400, 800); //y-axis
        graphics2D.drawLine(0, 400, 800, 400); //x-axis
        
        for (int i = 0; i < 800 / 50; i++) {
            graphics2D.drawLine(50 * i, 395, 50 * i, 405); //(x1, y1, x2, y2) draws thin vertical line every 50px
            graphics2D.drawLine(395, 50 * i, 405, 50 * i);
        }
    }
}
