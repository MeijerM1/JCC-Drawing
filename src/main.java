import drawing.domain.*;

import java.awt.font.ImageGraphicAttribute;
import java.util.ArrayList;

/**
 * Created by max1_ on 14/02/2017.
 */
public class main {
    public static void main(String[]args) {
        System.out.println("Hello World!");

        Drawing drawing = new Drawing();
        drawing.setName("Test");

        Oval oval = new Oval();
        oval.setHeight(10.0);
        PaintedText text = new PaintedText();
        Polygon polygon = new Polygon();
        Image image = new Image();

        drawing.addItem(oval);
        drawing.addItem(text);
        drawing.addItem(polygon);
        drawing.addItem(image);

        ArrayList<DrawingItem> items = drawing.getItems();

        for(DrawingItem item : items) {
            System.out.println(item.toString());
        }
    }
}
