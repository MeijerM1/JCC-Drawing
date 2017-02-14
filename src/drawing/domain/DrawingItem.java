package drawing.domain;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by max1_ on 14/02/2017.
 */
public abstract class DrawingItem {

    private Color color;
    private DrawingItem previousState;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.previousState = this;
        this.color = color;
    }

    public DrawingItem getPreviousState() {
        return previousState;
    }

    public abstract Point getAnchor();

    public abstract double getWidth();

    public abstract double getHeight();

    public void setPreviousState() {
        previousState = this;
    }
}
