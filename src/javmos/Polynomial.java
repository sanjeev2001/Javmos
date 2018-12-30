package javmos;

import java.awt.Graphics2D;
import java.util.Arrays;
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
            this.polynomial = polynomial;
            polynomial = polynomial.contains("=") ? polynomial.substring(polynomial.indexOf("=") + 1, polynomial.length()) : polynomial;
            String[] terms = polynomial.charAt(0) == '-' ? polynomial.substring(1, polynomial.length()).split("\\+|\\-") : polynomial.split("\\+|\\-");
            coefficients = new double[terms.length];
            degrees = new int[terms.length];
            int termsStart = 0;

            for (int i = 0; i < terms.length; i++) {
                if (terms[i].contains("x^")) {
                    coefficients[i] = Double.parseDouble(terms[i].substring(0, terms[i].indexOf("x")));
                    degrees[i] = Integer.parseInt(terms[i].substring(terms[i].indexOf("^") + 1, terms[i].length()));
                } else if (terms[i].contains("x") && !terms[i].contains("^")) {
                    coefficients[i] = terms[i].length() == 1 ? 1 : Double.parseDouble(terms[i].substring(0, terms[i].indexOf("x")));
                    degrees[i] = 1;
                } else {
                    coefficients[i] = Double.parseDouble(terms[i]);
                    degrees[i] = 0;
                }

                coefficients[i] *= (polynomial.contains("-") && polynomial.substring(termsStart, termsStart + 1).equals("-")) ? -1 : 1;
                termsStart += i == 0 && !(polynomial.charAt(0) == '-') ? terms[i].length() : terms[i].length() + 1;
            }

        } catch (Exception exception) {
            throw new PolynomialException(polynomial + " is not a valid polynomial!");
        }
    }

    public Polynomial(JavmosGUI gui, double[] coefficients, int[] degrees) {
        this.gui = gui;
        this.coefficients = coefficients;
        this.degrees = degrees;
        this.polynomial = gui.getEquationField();
    }

    private void drawPolynomial(Graphics2D graphics2D) {

    }

    private int getDegree() {
        Arrays.sort(degrees);
        return degrees[degrees.length - 1];
    }

    public Polynomial getDerivative() {
        int numOfTerms = 0;
        int offset = 0;

        for (int i = 0; i < degrees.length; i++) {
            numOfTerms += degrees[i] > 0 ? 1 : 0;
        }

        double[] firstCoefficients = new double[numOfTerms];
        int[] firstDegrees = new int[numOfTerms];

        for (int i = 0; i < coefficients.length; i++) {
            if (degrees[i] > 0) {
                firstCoefficients[i - offset] = coefficients[i] * degrees[i];
                firstDegrees[i - offset] = degrees[i] - 1;
            } else {
                offset += 1;
            }
        }

        return new Polynomial(gui, firstCoefficients, firstDegrees);
    }

    public String getEquation() {
        return polynomial.contains("=") ? "f(x)=" + polynomial.substring(polynomial.indexOf("=") + 1, polynomial.length()) : "f(x)=" + polynomial;
    }

    public String getFirstDerivative() {
        String firstString = "f'(x)=";

        Polynomial test = new Polynomial(gui, coefficients, degrees);
        test.getDerivative();

        for (int i = 0; i < coefficients.length; i++) {
            if (degrees[i] > 1) {
                firstString += (coefficients[i] > 0 && i != 0) ? "+" + String.valueOf(coefficients[i] * degrees[i]) + "x" + (degrees[i] - 1 == 1 ? "" : "^") + String.valueOf(degrees[i] - 1 == 1 ? "" : degrees[i] - 1) : String.valueOf(coefficients[i] * degrees[i]) + "x" + (degrees[i] - 1 == 1 ? "" : "^") + String.valueOf(degrees[i] - 1 == 1 ? "" : degrees[i] - 1);
            } else if (degrees[i] == 1) {
                firstString += (coefficients[i] > 0 && i != 0) ? "+" + String.valueOf(coefficients[i] * degrees[i]) : String.valueOf(coefficients[i] * degrees[i]);
            }
        }

        return firstString;
    }

    public HashSet<Point> getRoots(RootType rootType, double minDomain, double maxDomain) {
        return new HashSet<>();
    }

    public String getSecondDerivative() {
        String secondString = "f''(x)=";

        for (int i = 0; i < coefficients.length; i++) {
            if (degrees[i] - 2 > 0) {
                secondString += (coefficients[i] > 0 && i != 0) ? "+" + String.valueOf(coefficients[i] * degrees[i] * (degrees[i] - 1)) + "x" + (degrees[i] - 2 == 1 ? "" : "^") + String.valueOf(degrees[i] - 2 == 1 ? "" : degrees[i] - 2) : String.valueOf(coefficients[i] * degrees[i] * (degrees[i] - 1)) + "x" + (degrees[i] - 2 == 1 ? "" : "^") + String.valueOf(degrees[i] - 2 == 1 ? "" : degrees[i] - 2);
            } else if (degrees[i] - 2 == 0) {
                secondString += (coefficients[i] > 0 && i != 0) ? "+" + String.valueOf(coefficients[i] * degrees[i] * (degrees[i] - 1)) : String.valueOf(coefficients[i] * degrees[i] * (degrees[i] - 1));
            }
        }

        return secondString;
    }

    private double getValueAt(double x) {
        return 0.0;
    }

    private Double newtonsMethod(RootType rootType, double guess, int attempts) {
        return 0.0;
    }
}
