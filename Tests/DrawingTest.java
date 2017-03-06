
import java.util.ArrayList;
import drawing.domain.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Max on 2/24/2017.
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
}