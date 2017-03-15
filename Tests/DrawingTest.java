
import java.util.ArrayList;
import drawing.domain.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Max on 2/24/2017.
 * Some simple tests to prove I am not complete fool.
 */
class DrawingTest {
    @org.junit.jupiter.api.BeforeEach
    void setUp() {

    }

    @org.junit.jupiter.api.Test
    void testItems() {
        Drawing drawing = new Drawing();

        ArrayList<DrawingItem> items = new ArrayList<>();
        items.add(new Oval(new Point(10, 10), 15, 20, 10, Color.BLUE));
        drawing.setItems(items);

        assertEquals(items, drawing.getItems());
    }

    @org.junit.jupiter.api.Test
    void testDrawingConstructor() {
        Drawing drawing = new Drawing();
        ArrayList<DrawingItem> items = new ArrayList<>();
        assertEquals(items,drawing.getItems());
    }

    @org.junit.jupiter.api.Test
    void testAddItem() {
        Drawing drawing = new Drawing();
        ArrayList<DrawingItem> items = new ArrayList<>();

        Oval oval = new Oval(new Point(10,10), 100, 100, 100, Color.BLACK);
        items.add(oval);
        drawing.addItem(oval);

        assertEquals(items, drawing.getItems());
    }

    @org.junit.jupiter.api.Test
    void testDrawingName() {
        Drawing drawing = new Drawing();
        String name = "drawing";

        drawing.setName(name);

        assertEquals(name, drawing.getName());
    }

    @org.junit.jupiter.api.Test
    void testRemoveItem() {
        Drawing drawing = new Drawing();
        ArrayList<DrawingItem> items = new ArrayList<>();

        Oval oval = new Oval(new Point(10,10), 100, 100, 100, Color.BLACK);
        items.add(oval);
        drawing.addItem(oval);

        assertEquals(items, drawing.getItems());

        items.remove(oval);
        drawing.removeItem(oval);

        assertEquals(items, drawing.getItems());
    }
}