package drawing.domain;

import javafx.scene.shape.Rectangle;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by max1_ on 14/02/2017.
 * A collection of points
 */
public class Polygon extends DrawingItem implements Serializable {

    private ArrayList<Point> vertices;
    private Point anchor;
    private double weight;

    public Polygon(ArrayList<Point> vertices, double weight) {
        this.vertices = vertices;
        this.weight = weight;
    }

    @Override
    public void paintUsing(Paintable paintable) {
        paintable.paint(this);
    }

    @Override
    public boolean insideBoundingBox(Point point) {
        return (anchor.getX() <= point.getX()) && (point.getX() <= (anchor.getX() + getWidth())) && (anchor.getY() <= point.getY()) && (point.getY() <= (anchor.getY() + getHeight()));
    }

    @Override
    public Rectangle getBoundingBox() {
        return new Rectangle(anchor.getX(), anchor.getY(), getWidth(), getHeight());
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
        return anchor;
    }

    @Override
    public double getWidth() {
        double xLow = 0;
        double xHigh = 0;
        double yHigh = 0;

        for(Point p : vertices) {
            if(p.getX() < xLow) { xLow = p.getX(); }
            if(p.getX() > xHigh) { xHigh = p.getX(); }
            if(p.getY() > yHigh) { yHigh = p.getY(); }
        }

        anchor = new Point(xLow, yHigh);
        return xHigh - xLow;
    }

    @Override
    public double getHeight() {
        double yLow = 0;
        double yHigh = 0;

        for(Point p : vertices) {
            if(p.getY() < yLow) {  yLow = p.getY(); }
            if(p.getY() > yHigh) { yHigh = p.getY(); }
        }
        return yHigh - yLow;
    }

    @Override
    public String toString() {
        return "Polygon{" +
                "vertices=" + vertices +
                ", weight=" + weight +
                '}';
    }
}
