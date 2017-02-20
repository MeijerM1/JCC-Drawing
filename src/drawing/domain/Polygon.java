package drawing.domain;

import java.util.ArrayList;

/**
 * Created by max1_ on 14/02/2017.
 * A collection of points
 */
public class Polygon extends DrawingItem {

    private ArrayList<Point> vertices;
    private double weight;

    public Polygon(ArrayList<Point> vertices, double weight) {
        this.vertices = vertices;
        this.weight = weight;
    }

    public ArrayList<Point> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Point> vertices) {
        this.vertices = vertices;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
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
