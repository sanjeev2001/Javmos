package javmos;

import javmos.exceptions.PolynomialException;

public class Polynomial {

    public final int ATTEMPTS = 15;

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

    public String getFirstDerivative() {
        // Complete me
        return "";
    }

    public String getSecondDerivative() {
        // Complete me
        return "";
    }
}
