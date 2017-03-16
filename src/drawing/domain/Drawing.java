package drawing.domain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by max1_ on 14/02/2017.
 * A collection of Drawable item to displayed on the screen
 */
public class Drawing extends DrawingItem implements Serializable {

    private String name;
    private ArrayList<DrawingItem> items;
    private ObservableList<DrawingItem> observableList;


    public Drawing() {
        items = new ArrayList<>();
        observableList = FXCollections.observableArrayList(items);

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

    public List<DrawingItem> getItems() {
        return Collections.unmodifiableList(items);
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
        observableList.add(item);
        return true;
    }

    public boolean removeItem(DrawingItem item) {
        observableList.remove(item);
        return true;
    }

    public ObservableList<DrawingItem> itemsToObserve() {
        return FXCollections.unmodifiableObservableList(observableList);
    }


    public int findItem(DrawingItem item) {
        return items.indexOf(item);
    }

    public void editItem(DrawingItem newItem, int index) {
        items.set(index, newItem);
    }

    public void paintUsing (Paintable paintable) {
        for (DrawingItem item : observableList) {
            item.paintUsing(paintable);
        }
    }
}
