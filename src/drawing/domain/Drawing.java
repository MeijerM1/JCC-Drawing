package drawing.domain;

import java.util.ArrayList;

/**
 * Created by max1_ on 14/02/2017.
 */
public class Drawing {
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


}
