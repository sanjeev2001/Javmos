package javmos;

import java.awt.Color;

public enum RootType {
    X_INTERCEPT(Color.GREEN, "x-intercept"),
    CRITICAL_POINT(Color.RED, "Critical Point"),
    INFLECTION_POINT(Color.BLUE, "Inflection Point");

    public final Color color;
    public final String name;

    RootType(Color color, String name) {
        this.color = color;
        this.name = name;
    }
    
    public Color getPointColor() {
        return color;
    }

    public String getPointName() {
        return name;
    }
}
