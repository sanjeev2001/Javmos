package javmos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;

public class CartesianPlane extends java.lang.Object {

    public final JavmosGUI gui;

    public CartesianPlane(JavmosGUI gui) {
        this.gui = gui;
    }

    public void drawPlane(java.awt.Graphics2D graphics2D) {
        int pixel = (int) gui.getZoom();
        double d = gui.getDomainStep();
        double r = gui.getRangeStep();
        int scaleFactor = 1;
        DecimalFormat thousandth = new DecimalFormat("#.###");
        graphics2D.clearRect(0, 0, 800, 800);
        graphics2D.setFont(new Font("TimesRoman", Font.PLAIN, 14)); //Sets font size and font type

        if (gui.getZoom() == 30.0 || gui.getZoom() == 20.0) {
            scaleFactor = 2;
        } else if (gui.getZoom() == 10.0) {
            scaleFactor = 5;
        }

        for (int i = 0; i < 800 / pixel; i++) {
            graphics2D.setColor(Color.GRAY);
            graphics2D.setStroke(new BasicStroke(1)); //Sets thin line stroke
            graphics2D.drawLine(400 - pixel * i, 0, 400 - pixel * i, 800); //(x1, y1, x2, y2) draws thin vertical lines 
            graphics2D.drawLine(0, 400 - pixel * i, 800, 400 - pixel * i);//(x1, y1, x2, y2) draws thin horizontal line 
            graphics2D.drawLine(400 + pixel * i, 0, 400 + pixel * i, 800); //(x1, y1, x2, y2) draws thin vertical line 
            graphics2D.drawLine(0, 400 + pixel * i, 800, 400 + pixel * i);//(x1, y1, x2, y2) draws thin horizontal line 
            graphics2D.setStroke(new BasicStroke(3));//Sets thick line stroke
            graphics2D.setColor(Color.BLACK);
            graphics2D.drawLine(400, 0, 400, 800); //y-axis
            graphics2D.drawLine(0, 400, 800, 400); //x-axis
            graphics2D.drawLine(400 - pixel * i, 395, 400 - pixel * i, 405); //Draws axis tick marks
            graphics2D.drawLine(395, 400 - pixel * i, 405, 400 - pixel * i);//Draws axis tick marks
            graphics2D.drawLine(400 + pixel * i, 395, 400 + pixel * i, 405); //Draws axis tick marks
            graphics2D.drawLine(395, 400 + pixel * i, 405, 400 + pixel * i);//Draws axis tick marks
            //Draws horizontal axis numbers
            if ((gui.getDomainStep() % 1) == 0) {
                graphics2D.setColor(Color.WHITE);
                graphics2D.fillRect(390 - pixel * (i+1) * scaleFactor, 410, 15, 10);
                graphics2D.setColor(Color.BLACK);
                graphics2D.drawString(String.valueOf((int) (-(i+1) * d * scaleFactor)), 392 - pixel * (i+1) * scaleFactor, 420);
                graphics2D.drawString(String.valueOf((int) ((i+1) * d * scaleFactor)), 397 + pixel * (i+1) * scaleFactor, 420);
            } else {
                graphics2D.setColor(Color.WHITE);
                graphics2D.fillRect(390 - pixel * (i+1) * scaleFactor, 410, 15, 10);
                graphics2D.setColor(Color.BLACK);
                graphics2D.drawString(String.valueOf(thousandth.format((-(i+1)) * d)), 392 - pixel * (i+1), 420);
                graphics2D.drawString(String.valueOf(thousandth.format((i+1) * d)), 397 + pixel * (i+1), 420);
            }

            // Draws vertical axis numbers
            if ((gui.getRangeStep() % 1) == 0) {
                graphics2D.drawString(String.valueOf((int) (-(i+1) * r)), 385, 405 + pixel * (i+1));
                graphics2D.drawString(String.valueOf((int) ((i+1) * r)), 385, 405 - pixel * (i+1));
            } else {
                graphics2D.drawString(String.valueOf(thousandth.format(-(i+1) * r)), 382, 405 + pixel * (i+1));
                graphics2D.drawString(String.valueOf(thousandth.format((i+1) * r)), 385, 405 - pixel * (i+1));
            }
        }
    }
}
