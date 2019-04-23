package javmos.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javmos.JavmosGUI;
import javmos.components.JavmosPanel;
import javmos.components.functions.Polynomial;


public class DrawListener implements ActionListener {

    private final JavmosGUI gui;
    private final JavmosPanel panel;

    public DrawListener(JavmosGUI gui, JavmosPanel panel) {
        this.gui = gui;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
            panel.setPolynomial(new Polynomial(gui, gui.getEquationField()));
            gui.setFirstDerivativeLabel(panel.getPolynomial().getFirstDerivative());
            gui.setSecondDerivativeLabel(panel.getPolynomial().getSecondDerivative());
        
        panel.repaint();
    }
}
