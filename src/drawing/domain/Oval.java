package drawing.domain;

import javafx.scene.shape.Rectangle;

import java.io.Serializable;

/**
 * Created by max1_ on 14/02/2017.
 * An oval to draw on the screen
 */
public class Oval extends DrawingItem implements Serializable {

    private Point anchor;
    private double width;
    private double height;
    private double weight;

    public Oval(Point anchor, double width, double height, double weight, Color color) {
        this.anchor = anchor;
        this.width = width;
        this.height = height;
        this.weight = weight;
        this.setColor(color);
    }

    public Point getAnchor() {
        return anchor;
    }

    public void setAnchor(Point anchor) {
        this.anchor = anchor;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public void paintUsing(Paintable paintable) {
        paintable.paint(this);
    }

    @Override
    public boolean insideBoundingBox(Point point) {
        if ((anchor.getX() <= point.getX()) && (point.getX() <= (anchor.getX() + getWidth())) && (anchor.getY() <= point.getY()) && (point.getY() <= (anchor.getY() + getHeight()))) {
            return true;
        }
        return false;
    }

    @Override
    public Rectangle getBoundingBox() {
        return new Rectangle(anchor.getX(), anchor.getY(), width, height);
    }

    @Override
    public String toString() {
        return "Oval{" +
                "anchor=" + anchor +
                ", width=" + width +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }
}
