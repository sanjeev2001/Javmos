package javmos;

import java.awt.Graphics2D;
import java.util.HashSet;
import javmos.exceptions.PolynomialException;

public class Polynomial {

    public final int ATTEMPTS = 15;
    public double[] coefficients;
    public int[] degrees;
    public JavmosGUI polynomialGUI = new JavmosGUI();
    public String polynomial;

    public Polynomial(JavmosGUI gui, String polynomial) throws PolynomialException {
        try {
            System.out.println(polynomial);
            if (polynomial.contains("=")) {
                polynomial = polynomial.substring(polynomial.indexOf("=") + 1, polynomial.length());
            }

            System.out.println(polynomial);
        } catch (Exception exception) {
            throw new PolynomialException(polynomial + " is not a valid polynomial!");
        }
    }

    public Polynomial(JavmosGUI gui, double[] coefficients, int[] degrees) {

    }

    public String getEquation() {
        return "";
    }

    public String getFirstDerivative() {
        // Complete me
        return "";
    }

    public String getSecondDerivative() {
        // Complete me
        return "";
    }

    public HashSet<Point> getRoots(RootType rootType, double minDomain, double maxDomain) {
        return new HashSet<>();
    }

    public void drawPolynomial(Graphics2D graphics2D) {

    }

    public int getDegree() {
        return 0;
    }

    public double getValueAt(double x) {
        return 0.0;
    }

    public Polynomial getDerivative() {

        return new Polynomial(polynomialGUI, coefficients, degrees);
    }

    public Double newtonsMethod(RootType rootType, double guess, int attempts) {
        return 0.0;
    }
}
