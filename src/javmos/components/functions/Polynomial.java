package javmos.components.functions;

import java.util.Arrays;
import javmos.JavmosGUI;
import javmos.enums.FunctionType;

public final class Polynomial extends Function {

    public final double[] coefficients;
    public final int[] degrees;

    public Polynomial(JavmosGUI gui, String function) {
        super(gui);
        //if an = sign exists, evrything after it is taken as the polynomial, otherwise polynomial is taken by itself
        function = function.contains("=") ? function.substring(function.indexOf("=") + 1, function.length()) : function;
        //# of terms is equal to the length of an array that splits the + and - signs from polynomial
        String[] terms = function.charAt(0) == '-' ? function.substring(1, function.length()).split("\\+|\\-") : function.split("\\+|\\-");
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
                //if length of term is 1 then coeff must be 1, otherwise everything before the x is taken as coeff
                coefficients[i] = terms[i].length() == 1 ? 1 : Double.parseDouble(terms[i].substring(0, terms[i].indexOf("x")));
                degrees[i] = 1; //degree must be 1
            } else {
                coefficients[i] = Double.parseDouble(terms[i]); //non x term therefore entire terms is parsed
                degrees[i] = 0; //non x term therefore degree = 0
            }//if a - exists in the polynomial and the first character of ther terms is -, multiply coeffeicient by -1
            coefficients[i] *= (function.contains("-") && function.substring(termsStart, termsStart + 1).equals("-")) ? -1 : 1;
            //used to refernce where each term begins relative to the entire polynomial string
            termsStart += i == 0 && !(function.charAt(0) == '-') ? terms[i].length() : terms[i].length() + 1;
        }
    }

    @Override
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

    @Override
    public String getSecondDerivative() {
        String secondString = "f''(x)=";
        //Applies power rule to every term twice
        for (int i = 0; i < coefficients.length; i++) {
            if (degrees[i] > 2) {
                secondString += (coefficients[i] > 0 && i != 0) ? "+" + String.valueOf(coefficients[i] * degrees[i] * (degrees[i] - 1)) + "x" + (degrees[i] - 2 == 1 ? "" : "^") + String.valueOf(degrees[i] - 2 == 1 ? "" : degrees[i] - 2) : String.valueOf(coefficients[i] * degrees[i] * (degrees[i] - 1)) + "x" + (degrees[i] - 2 == 1 ? "" : "^") + String.valueOf(degrees[i] - 2 == 1 ? "" : degrees[i] - 2);
            } else if (degrees[i] == 2) {
                secondString += (coefficients[i] > 0 && i != 0) ? "+" + String.valueOf(coefficients[i] * degrees[i] * (degrees[i] - 1)) : String.valueOf(coefficients[i] * degrees[i] * (degrees[i] - 1));
            }
        }
        return secondString;
    }

    @Override
    public double getValueAt(double x, FunctionType functionType) {
        int[] temp = degrees.clone();
        Arrays.sort(temp);
        double ans = 0.0;

        /*runs loop for total # of terms and if the terms has an x, it is multiplied by the respective coeff and the respective degree is used as an exponent
        otherwise term is constant, both term types are added to a total value named ans  */
        //If higher degree derivative is needed, derivative is calculated then getValue is run again with new Polynomial object
        if (functionType == FunctionType.ORIGINAL) {
            for (int i = 0; i < coefficients.length; i++) {
                if (degrees[i] > 0) {
                    ans += coefficients[i] * Math.pow(x, degrees[i]);
                } else {
                    ans += coefficients[i];
                }
            }
            return ans;
        } else if (functionType == FunctionType.FIRST_DERIVATIVE && temp[temp.length - 1] >= 1) {
            return new Polynomial(gui, this.getFirstDerivative()).getValueAt(x, FunctionType.ORIGINAL);
        } else if (functionType == FunctionType.SECOND_DERIVATIVE && temp[temp.length - 1] >= 2) {
            return new Polynomial(gui, this.getSecondDerivative()).getValueAt(x, FunctionType.ORIGINAL);
        } else if (functionType == FunctionType.THIRD_DERIVATIVE && temp[temp.length - 1] >= 3) {
            return new Polynomial(gui, new Polynomial(gui, this.getSecondDerivative()).getFirstDerivative()).getValueAt(x, FunctionType.ORIGINAL);
        } else {
            return 0;
        }
    }
}
