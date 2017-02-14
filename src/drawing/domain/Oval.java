package drawing.domain;

/**
 * Created by max1_ on 14/02/2017.
 */
public class Oval extends DrawingItem {

    private Point anchor;
    private double width;
    private double height;
    private double weight;

    public Point getAnchor() {
        return anchor;
    }

    public void setAnchor(Point anchor) {
        setPreviousState();
        this.anchor = anchor;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        setPreviousState();
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        setPreviousState();
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        setPreviousState();
        this.weight = weight;
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
