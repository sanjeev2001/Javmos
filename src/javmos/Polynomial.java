package javmos;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import javmos.exceptions.PolynomialException;

public class Polynomial {

    public final int ATTEMPTS = 100;
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
                    if (terms[i].substring(0, 2).equals("x^")) {
                        coefficients[i] = 1;
                    } else {
                        coefficients[i] = Double.parseDouble(terms[i].substring(0, terms[i].indexOf("x")));
                    }
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
        this.polynomial = "";
    }

    public void drawPolynomial(Graphics2D graphics2D) {
        for (double i = gui.getMinDomain(); i < gui.getMaxDomain(); i += 0.01) {
            if (getValueAt(i) <= gui.getMaxRange() && getValueAt(i) >= gui.getMinRange()) {
                double x1 = 400 + i * gui.getZoom() / gui.getDomainStep();
                double x2 = 400 + (i + 0.01) * gui.getZoom() / gui.getDomainStep();
                double y1 = 400 - getValueAt(i) * gui.getZoom() / gui.getRangeStep();
                double y2 = 400 - getValueAt(i + 0.01) * gui.getZoom() / gui.getRangeStep();
                //Sets the origin point at (400,400) and draws from to the left and right
                graphics2D.setStroke(new BasicStroke(2));
                graphics2D.draw(new Line2D.Double(x1, y1, x2, y2));
            }
        }
    }

    private int getDegree() {
        int[] temp = degrees;
        Arrays.sort(temp);
        return temp[temp.length - 1];
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
        Polynomial function = new Polynomial(gui, coefficients, degrees);
        HashSet<Point> roots = new HashSet<>(function.getDegree());
        if (rootType.getPointName().equals("x-intercept")) {
            for (double i = gui.getMinDomain(); i < gui.getMaxDomain(); i += 0.1) {
                roots.add(new Point(gui, rootType, function.newtonsMethod(rootType, i, ATTEMPTS), 0.0));
            }
        } else if (rootType.getPointName().equals("Critical Point")) {
            for (double i = gui.getMinDomain(); i < gui.getMaxDomain(); i += 0.1) {
                roots.add(new Point(gui, rootType, function.newtonsMethod(rootType, i, ATTEMPTS), function.getValueAt(function.newtonsMethod(rootType, i, ATTEMPTS))));
            }
        } else {
            for (double i = gui.getMinDomain(); i < gui.getMaxDomain(); i += 0.1) {
                roots.add(new Point(gui, rootType, function.newtonsMethod(rootType, i, ATTEMPTS), function.getValueAt(function.newtonsMethod(rootType, i, ATTEMPTS))));
            }
        }
        return roots;
    }

    public String getSecondDerivative() {
        Polynomial test = new Polynomial(gui, coefficients, degrees);
        System.out.println(test.newtonsMethod(RootType.X_INTERCEPT, 3, ATTEMPTS));
        return "f''(x)=" + (new Polynomial(gui, coefficients, degrees).getDerivative().getFirstDerivative()).substring(6, new Polynomial(gui, coefficients, degrees).getDerivative().getFirstDerivative().length());
    }

    private double getValueAt(double x) {
        double ans = 0.0;

        for (int i = 0; i < coefficients.length; i++) {
            if (degrees[i] > 0) {
                ans += coefficients[i] * Math.pow(x, degrees[i]);
            } else {
                ans += coefficients[i];
            }
        }

        return ans;
    }

    private Double newtonsMethod(RootType rootType, double guess, int attempts) {
        DecimalFormat thousandth = new DecimalFormat("#.###");
        Polynomial numerator;
        Polynomial denominator;

        //System.out.println(attempts);
        if (rootType.getPointName().equals("x-intercept")) {
            numerator = new Polynomial(gui, coefficients, degrees);
            denominator = numerator.getDerivative();
        } else if (rootType.getPointName().equals("Critical Point")) {
            numerator = new Polynomial(gui, coefficients, degrees).getDerivative();
            denominator = numerator.getDerivative().getDerivative();
        } else {
            numerator = new Polynomial(gui, coefficients, degrees).getDerivative().getDerivative();
            denominator = numerator.getDerivative().getDerivative().getDerivative();
        }
        if (attempts == 0 || denominator.getValueAt(guess) == 0) {
            return null;
        } else if (Math.abs((guess - (numerator.getValueAt(guess) / denominator.getValueAt(guess))) - guess) <= 0.000001) {
            return Double.parseDouble(thousandth.format(guess - (numerator.getValueAt(guess) / denominator.getValueAt(guess))));
        } else {
            return newtonsMethod(rootType, guess - (numerator.getValueAt(guess) / denominator.getValueAt(guess)), attempts - 1);
        }
    }
}
