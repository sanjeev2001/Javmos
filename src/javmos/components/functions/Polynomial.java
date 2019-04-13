package javmos.components.functions;

import java.awt.Graphics2D;
import javmos.JavmosGUI;
import javmos.enums.FunctionType;
import javmos.exceptions.PolynomialException;


public final class Polynomial extends Function{

    
    public final double[] coefficients;
    public final int[] degrees;

    public Polynomial(JavmosGUI gui, String function) throws PolynomialException {
        super(gui);
        try {
            function = function.contains("=") ? function.substring(function.indexOf("=") + 1, function.length()) : function; //if an = sign exists evrything after it is taken as the polynomial otherwise polynomial is taken by itself
            String[] terms = function.charAt(0) == '-' ? function.substring(1, function.length()).split("\\+|\\-") : function.split("\\+|\\-"); //# of terms is equal to the length of an array that splits the + and - signs from polynomial
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
                coefficients[i] *= (function.contains("-") && function.substring(termsStart, termsStart + 1).equals("-")) ? -1 : 1; //if a - exists in the polynomial and the first character of ther terms is -, multiply coeffeicient by -1
                termsStart += i == 0 && !(function.charAt(0) == '-') ? terms[i].length() : terms[i].length() + 1; //used to refernce where each term begins relative to the entire polynomial string
            }

        } catch (Exception exception) {
            throw new PolynomialException(function + " is not a valid polynomial!"); //polynomial entered is invalid anytime any exception is caught
        }
    }


    /*public void drawPolynomial(Graphics2D graphics2D) {
        //this method draws very short lines at very small increments so that when they connect together, they form a much larger line
        for (double i = gui.getMinDomain(); i < gui.getMaxDomain(); i += 0.01) {
            if (getValueAt(i) <= gui.getMaxRange() && getValueAt(i) >= gui.getMinRange()) {//only draws within the given max and min range
                double x1 = i * gui.getZoom() / gui.getDomainStep();
                double y1 = getValueAt(i) * gui.getZoom() / gui.getRangeStep();
                double x2 = (i + 0.01) * gui.getZoom() / gui.getDomainStep();
                double y2 = getValueAt(i + 0.01) * gui.getZoom() / gui.getRangeStep();
                graphics2D.setStroke(new BasicStroke(3));
                /*400 added to x1 and x2 in order to start drawing from the origin 
                y1 and y2 are subtracted from 400 in order to start drawing from the origin 
                graphics2D.draw(new Line2D.Double(400 + x1, 400 - y1, 400 + x2, 400 - y2));
            }
        }
    }*/

    /*private int getDegree() {
        int[] temp = degrees.clone();
        Arrays.sort(temp);
        return temp[temp.length - 1];
    }*/

    /*public Polynomial getDerivative() {
        int numOfTerms = 0;
        int offset = 0;

        for (int i = 0; i < degrees.length; i++) {
            numOfTerms += degrees[i] > 0 ? 1 : 0; //# of terms = degrees of f(x) that are > 0 due to d/dx of constants being 0
        }

        double[] firstCoefficients = new double[numOfTerms];
        int[] firstDegrees = new int[numOfTerms];

        //Applies power rule to every term if degree < 0 terms must be constant therefore it is skipped and added as an offset to compensate for index
        for (int i = 0; i < coefficients.length; i++) {
            if (degrees[i] > 0) {
                firstCoefficients[i - offset] = coefficients[i] * degrees[i];
                firstDegrees[i - offset] = degrees[i] - 1;
            } else {
                offset += 1;
            }
        }
        return new Polynomial(gui, firstCoefficients, firstDegrees);
    }*/

    /*public String getEquation() {
        return polynomial.contains("=") ? "f(x)=" + polynomial.substring(polynomial.indexOf("=") + 1, polynomial.length()) : "f(x)=" + polynomial;
    }*/

    public String getFirstDerivative() {
        String firstString = "f'(x)=";
        //Applies power rule to every term
        for (int i = 0; i < coefficients.length; i++) {
            if (degrees[i] > 1) {
                firstString += (coefficients[i] > 0 && i != 0) ? "+" + String.valueOf(coefficients[i] * degrees[i]) + "x" + (degrees[i] - 1 == 1 ? "" : "^") + String.valueOf(degrees[i] - 1 == 1 ? "" : degrees[i] - 1) : String.valueOf(coefficients[i] * degrees[i]) + "x" + (degrees[i] - 1 == 1 ? "" : "^") + String.valueOf(degrees[i] - 1 == 1 ? "" : degrees[i] - 1);
            } else if (degrees[i] == 1) {
                firstString += (coefficients[i] > 0 && i != 0) ? "+" + String.valueOf(coefficients[i] * degrees[i]) : String.valueOf(coefficients[i] * degrees[i]);
            }
        }
        return firstString;
    }

    /*public HashSet<Point> getRoots(RootType rootType, double minDomain, double maxDomain) {
        Polynomial function = new Polynomial(gui, coefficients, degrees);
        HashSet<Point> roots = new HashSet<>(function.getDegree());
        //Runs newtons method across a certain domain to find roots based on the given rootType, roots are then added to the hashet to be later used to draw the points
        for (double i = minDomain; i < maxDomain; i += 0.1) {
            if (function.newtonsMethod(rootType, i, ATTEMPTS) != null) {
                if (rootType.getRootName().equals("x-intercept")) {
                    roots.add(new Point(gui, rootType, function.newtonsMethod(rootType, i, ATTEMPTS), 0.0));
                } else {
                    roots.add(new Point(gui, rootType, function.newtonsMethod(rootType, i, ATTEMPTS), function.getValueAt(function.newtonsMethod(rootType, i, ATTEMPTS))));
                }
            }
        }
        return roots;
    }*/

    public String getSecondDerivative() {
        return null;//new Polynomial(gui, getFirstDerivative()).getFirstDerivative();
    }

    public double getValueAt(double x, FunctionType functionType) {
        double ans = 0.0;

        /*runs loop for total # of terms and if the terms has an x, it is multiplied by the respective coeff and the respective degree is used as an exponent
        otherwise term is constant either term types are added to a total value */
        for (int i = 0; i < coefficients.length; i++) {
            if (degrees[i] > 0) {
                ans += coefficients[i] * Math.pow(x, degrees[i]);
            } else {
                ans += coefficients[i];
            }
        }
        return ans;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    
}
