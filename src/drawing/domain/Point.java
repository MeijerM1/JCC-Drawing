package drawing.domain;

import java.io.Serializable;

/**
 * Created by max1_ on 14/02/2017.
 * A single point represented by an X, Y coordinate
 */
public class Point implements Serializable {

    private double x;

    public double getX() {
        return x;
    }

    private double y;

    public double getY() {
        return y;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
