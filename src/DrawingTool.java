import com.mysql.jdbc.CommunicationsException;
import drawing.domain.*;
import javafx.JavaFXPaintable;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import persistence.DatabaseMediator;
import persistence.SerializationMediator;

import java.io.File;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Created by max1_ on 21/02/2017.
 * Tool that does actual drawing
 */
public class DrawingTool extends javafx.application.Application  {

    private Drawing drawing;

    public void setPaintable(Paintable paintable) {
        this.paintable = paintable;
    }

    private Paintable paintable;
    SerializationMediator sm = new SerializationMediator();
    DatabaseMediator dm = new DatabaseMediator();

    public DrawingTool() {
        // Empty constructor for application launch.
    }

    public void Draw() {
        drawing.paintUsing(paintable);
    }

    public Drawing getDrawing() {
        return drawing;
    }

    public void setDrawing(Drawing drawing) {
        this.drawing = drawing;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Drawing Operations Test");
        Group root = new Group();

        Canvas canvas = new Canvas(700, 500);
        Drawing drawing = new Drawing();

        // Add items to draw on the screen below.

//        // Add an ellipse
//        drawing.addItem(new Oval(new Point(10.0, 10.0), 50.0, 50.0, 40.0, Color.BLUE));
//
//        // Add an image to the screen
//        try {
//            drawing.addItem(new drawing.domain.Image(new File("C:\\Users\\max1_\\OneDrive\\Pictures\\5637-mcdonnell-douglas-f-15-eagle-1920x1080-aircraft-wallpaper.jpg"), new Point(60, 60), 100 ,100));
//            System.out.println(drawing.getItems().toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // Add a new polygon
//        ArrayList<Point> points = new ArrayList<>();
//        points.add(new Point(100, 100));
//        points.add(new Point(200, 100));
//        points.add(new Point(300, 300));
//        points.add(new Point(200, 350));
//        points.add(new Point(500, 400));
//        drawing.addItem(new Polygon(points, 5));
//
//        PaintedText text = new PaintedText("Test Text", "Arial", new Point(500,400), 100, 100);
//        drawing.addItem(text);

        drawing.setName("D01");
        // Load a drawing
        //drawing = sm.load("D01");
        drawing = dm.load("");

        // New drawing tool to draw the entire drawing to the screen
        this.setPaintable(new JavaFXPaintable(canvas.getGraphicsContext2D()));
        this.setDrawing(drawing);
        this.Draw();

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        // Save the object
        //sm.save(drawing);
        try {
            dm.save(drawing);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
