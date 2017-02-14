package drawing.domain;

import java.util.ArrayList;

/**
 * Created by max1_ on 14/02/2017.
 */
public class Polygon extends DrawingItem {

    private ArrayList<Point> vertices;
    private double weight;

    public ArrayList<Point> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Point> vertices) {
        setPreviousState();
        this.vertices = vertices;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        setPreviousState();
        this.weight = weight;
    }

    @Override
    public Point getAnchor() {
        return null;
    }

    @Override
    public double getWidth() {
        return 0;
    }

    @Override
    public double getHeight() {
        return 0;
    }

    @Override
    public String toString() {
        return "Polygon{" +
                "vertices=" + vertices +
                ", weight=" + weight +
                '}';
    }
}
