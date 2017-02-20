import drawing.domain.*;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by max1_ on 14/02/2017.
 * Console application for testing purposes
 */
public class Main {
    public static void main(String[]args) {
        System.out.println("Starting test!");

        Drawing drawing = new Drawing();
        drawing.setName("Test");

        Point point = new Point(10.0, 10.0);
        Oval oval = new Oval(point, 10.0, 10.0, 10.0);
        PaintedText text = new PaintedText("Test", "arial", point, 10.0, 10.0);

        ArrayList<Point> points = new ArrayList<>();
        points.add( new Point(2.0, 2.0));
        points.add( new Point(20.0, 20.0));

        Polygon polygon = new Polygon(points, 10.0);

        File file = new File("C:\\Users\\max1_\\OneDrive\\Pictures\\2357257_5511779fd97e9_pic.jpg");
        Image image = new Image(file, point, 10.0, 10.0);

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
