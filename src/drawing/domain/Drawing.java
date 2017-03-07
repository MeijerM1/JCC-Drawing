package drawing.domain;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by max1_ on 14/02/2017.
 * A collection of Drawable item to displayed on the screen
 */
public class Drawing implements Serializable {
    private String name;
    private ArrayList<DrawingItem> items;

    public Drawing() {
        items = new ArrayList<>();
    }

    public ArrayList<DrawingItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<DrawingItem> items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean addItem(DrawingItem item) {
        items.add(item);
        return true;
    }

    public boolean removeItem(DrawingItem item) {
        items.remove(item);
        return true;
    }

    public int findItem(DrawingItem item) {
        return items.indexOf(item);
    }

    public void editItem(DrawingItem newItem, int index) {
        items.set(index, newItem);
    }

    public void paintUsing (Paintable paintable) {
        for (DrawingItem item : items) {
            item.paintUsing(paintable);
        }
    }
}
