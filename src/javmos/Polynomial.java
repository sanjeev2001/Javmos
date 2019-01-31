package javmos;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashSet;
import javmos.exceptions.PolynomialException;

public class Polynomial {

    public final int ATTEMPTS = 150;
    public final double[] coefficients;
    public final int[] degrees;
    public final JavmosGUI gui;
    public final String polynomial;

    public Polynomial(JavmosGUI gui, String polynomial) throws PolynomialException {
        try {
            this.gui = gui;
            this.polynomial = polynomial;
            polynomial = polynomial.contains("=") ? polynomial.substring(polynomial.indexOf("=") + 1, polynomial.length()) : polynomial; //if an = sign exists evrything after it is taken as the polynomial otherwise polynomial is taken by itself
            String[] terms = polynomial.charAt(0) == '-' ? polynomial.substring(1, polynomial.length()).split("\\+|\\-") : polynomial.split("\\+|\\-"); //# of terms is equal to the length of an array that splits the + and - signs from polynomial
            coefficients = new double[terms.length]; //# of coeffs = number of total terms
            degrees = new int[terms.length]; //# of degrees = number of total terms
            int termsStart = 0;

            //run a for loop to anazlyze each terms individually to retrieve coeffs and degrees 
            for (int i = 0; i < terms.length; i++) {
                if (terms[i].contains("x^")) {
                    if (terms[i].substring(0, 2).equals("x^")) {
                        coefficients[i] = 1; //if a term beings with x, coeff = 1
                    } else {
                        coefficients[i] = Double.parseDouble(terms[i].substring(0, terms[i].indexOf("x"))); //take everything before the x as coeff
                    }
                    degrees[i] = Integer.parseInt(terms[i].substring(terms[i].indexOf("^") + 1, terms[i].length())); //take everything after the ^ as degree
                } else if (terms[i].contains("x") && !terms[i].contains("^")) {
                    coefficients[i] = terms[i].length() == 1 ? 1 : Double.parseDouble(terms[i].substring(0, terms[i].indexOf("x"))); //if length of term is 1 then coeff must be 1, otherwise everything before the x is taken as coeff
                    degrees[i] = 1; //degree must be 1
                } else {
                    coefficients[i] = Double.parseDouble(terms[i]); //non x term therefore entire terms is parsed
                    degrees[i] = 0; //non x term therefore degree = 0
                }
                coefficients[i] *= (polynomial.contains("-") && polynomial.substring(termsStart, termsStart + 1).equals("-")) ? -1 : 1; //if a - exists in the polynomial and the first character of ther terms is -, multiply coeffeicient by -1
                termsStart += i == 0 && !(polynomial.charAt(0) == '-') ? terms[i].length() : terms[i].length() + 1; //used to refernce where each term begins relative to the entire polynomial string
            }

        } catch (Exception exception) {
            throw new PolynomialException(polynomial + " is not a valid polynomial!"); //polynomial entered is invalid anytime any exception is caught
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
        int[] temp = degrees.clone();
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

        for (double i = minDomain; i < maxDomain; i += 0.1) {
            if (function.newtonsMethod(rootType, i, ATTEMPTS) != null) {
                if (rootType.getPointName().equals("x-intercept")) {
                    roots.add(new Point(gui, rootType, function.newtonsMethod(rootType, i, ATTEMPTS), 0.0));
                } else {
                    roots.add(new Point(gui, rootType, function.newtonsMethod(rootType, i, ATTEMPTS), function.getValueAt(function.newtonsMethod(rootType, i, ATTEMPTS))));
                }
            }
        }
        return roots;
    }

    public String getSecondDerivative() {
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

        switch (rootType.getPointName()) {
            case "x-intercept":
                numerator = new Polynomial(gui, coefficients, degrees);
                denominator = new Polynomial(gui, coefficients, degrees).getDerivative();
                break;
            case "Critical Point":
                numerator = new Polynomial(gui, coefficients, degrees).getDerivative();
                denominator = new Polynomial(gui, coefficients, degrees).getDerivative().getDerivative();
                break;
            default:
                numerator = new Polynomial(gui, coefficients, degrees).getDerivative().getDerivative();
                denominator = new Polynomial(gui, coefficients, degrees).getDerivative().getDerivative().getDerivative();
                break;
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
