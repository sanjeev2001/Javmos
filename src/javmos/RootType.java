package javmos;

import java.awt.Color;

public enum RootType {
    X_INTERCEPT(Color.GREEN),
    CRITICAL_POINT(Color.RED),
    INFLECTION_POINT(Color.BLUE);

    public final Color color;

    RootType(Color color) {
        this.color = color;
    }
    
    public Color getPointColor() {
        // Complete me
        return Color.BLACK;
    }
}
