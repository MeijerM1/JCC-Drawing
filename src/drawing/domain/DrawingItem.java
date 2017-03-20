package drawing.domain;

import java.io.Serializable;
import javafx.scene.shape.*;

/**
 * Created by max1_ on 14/02/2017.
 * Super class for drawable items
 */
public abstract class DrawingItem implements Serializable {

    private Color color;
    private DrawingItem previousState;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.previousState = this;
        this.color = color;
    }

    public DrawingItem(){
        color = Color.BLACK;
    }

    public DrawingItem getPreviousState() {
        return previousState;
    }

    public abstract Point getAnchor();

    public abstract double getWidth();

    public abstract double getHeight();

    public abstract void paintUsing(Paintable paintable);

    public abstract boolean insideBoundingBox(Point point);

    public abstract Rectangle getBoundingBox();

    /**
     * Checks if this item in overlapping with the given item.
     * returns true in case of overlap
     *
     * @param item the item to check.
     */
    public boolean overlaps(DrawingItem item) {
        // this items bottom and top coordinates
        double xBotOr = getAnchor().getX();
        double xTopOr = getAnchor().getX() + getWidth();
        double yBotOr = getAnchor().getY();
        double yTopOr = getAnchor().getY() + getHeight();

        // Comparable item bottom and top coordinates
        double xBotCom = item.getAnchor().getX();
        double xTopCom = item.getAnchor().getX() + item.getWidth();
        double yBotCom = item.getAnchor().getY();
        double yTopCom = item.getAnchor().getY() + item.getHeight();

        // Check for no intersection instead of intersection itself
        return !(xTopOr < xBotCom ||
                xTopCom < xBotOr ||
                yTopOr < yBotCom ||
                yTopCom < yBotOr);
    }

}
