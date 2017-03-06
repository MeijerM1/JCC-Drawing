package drawing.domain;

/**
 * Created by max1_ on 14/02/2017.
 * Super class for drawable items
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

    public DrawingItem(){
        color = Color.BLACK;
    }

    public DrawingItem getPreviousState() {
        return previousState;
    }

    public abstract Point getAnchor();

    public abstract double getWidth();

    public abstract double getHeight();

    public void paintUsing(Paintable paintable) {
    }
}
