package javmos;

import java.awt.Graphics2D;
import java.util.HashSet;
import javmos.exceptions.PolynomialException;

public class Polynomial {

    public final int ATTEMPTS = 15;
    public final double[] coefficients;
    public final int[] degrees;
    public final JavmosGUI gui;
    public final String polynomial;

    public Polynomial(JavmosGUI gui, String polynomial) throws PolynomialException {
        try {
            this.gui = gui;
            coefficients = new double[gui.getEquationField().split("\\+|\\-").length];
            degrees = new int[gui.getEquationField().split("\\+|\\-").length];
            this.polynomial = polynomial;

            //System.out.println(polynomial);
            if (polynomial.contains("=")) {
                polynomial = polynomial.substring(polynomial.indexOf("=") + 1, polynomial.length());
            }

            String[] terms = polynomial.split("\\+|\\-");
            int termsStart = 0;

            for (int i = 0; i < terms.length; i++) {
                if (terms[i].contains("x^")) {
                    coefficients[i] = Double.parseDouble(terms[i].substring(0, terms[i].indexOf("x")));
                    degrees[i] = Integer.parseInt(terms[i].substring(terms[i].indexOf("^") + 1, terms[i].length()));
                } else if (terms[i].contains("x") && !terms[i].contains("^")) {
                    coefficients[i] = terms[i].length() == 1 ? 1 : Double.parseDouble(terms[i].substring(0, terms[i].indexOf("x")));
                    degrees[i] = 1;
                } else if (!terms[i].equals("")) {
                    coefficients[i] = Double.parseDouble(terms[i]);
                    degrees[i] = 0;
                }

                if (polynomial.contains("-") && polynomial.substring(termsStart, termsStart + 1).equals("-")) {
                    coefficients[i] *= -1;
                }
                termsStart += i == 0 ? terms[i].length() : 1 + terms[i].length();
            }

            //System.out.println(polynomial);
        } catch (Exception exception) {
            throw new PolynomialException(polynomial + " is not a valid polynomial!");
        }
    }

    public Polynomial(JavmosGUI gui, double[] coefficients, int[] degrees) {
        this.gui = gui;
        coefficients = new double[gui.getEquationField().split("\\+|\\-").length];
        this.coefficients = coefficients;
        this.degrees = degrees;
        polynomial = gui.getEquationField();
        String[] terms = polynomial.split("\\+|\\-");
        /*
        for (int i = 0; i < terms.length; i++) {
            if (polynomial.contains("x^")) {
                coefficients[i] = Double.parseDouble(terms[i].substring(0, terms[i].indexOf("x")));
                degrees[i] = Integer.parseInt(terms[i].substring(terms[i].indexOf("^") + 1, terms[i].length() - 1));
            } else if (polynomial.contains("x") && !polynomial.contains("^")) {
                coefficients[i] = Double.parseDouble(terms[i].substring(0, terms[i].indexOf("x")));
                degrees[i] = 1;
            } else {
                coefficients[i] = Double.parseDouble(terms[i]);
                degrees[i] = 0;
            }
        }
        */
    }

    public String getEquation() {
        return "";
    }

    public String getFirstDerivative() {
        Polynomial test = new Polynomial(gui, coefficients, degrees);
        for (int i = 0; i < coefficients.length; i++) {
            System.out.println(coefficients[i] + " " + degrees[i]);

        }
        return String.valueOf(coefficients[0]);
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

    /*
    public Polynomial getDerivative() {

        return new Polynomial(polynomialGUI, coefficients, degrees);
    }
   */
    public Double newtonsMethod(RootType rootType, double guess, int attempts) {
        return 0.0;
    }
}
