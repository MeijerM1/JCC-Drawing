import drawing.domain.Drawing;
import drawing.domain.DrawingTool;
import drawing.domain.Oval;
import drawing.domain.Point;

import javafx.JavaFXPaintable;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.print.DocFlavor;
import java.io.File;
import java.net.MalformedURLException;

/**
 * Created by max1_ on 21/02/2017.
 *
 */
public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Drawing Operations Test");
        Group root = new Group();

        Canvas canvas = new Canvas(700, 500);
        Drawing drawing = new Drawing();

        // Add items to draw on the screen.
        drawing.addItem(new Oval(new Point(10.0, 10.0), 50.0, 50.0, 40.0));

        try {
            drawing.addItem(new drawing.domain.Image(new File("C:\\Users\\max1_\\OneDrive\\Pictures\\5637-mcdonnell-douglas-f-15-eagle-1920x1080-aircraft-wallpaper.jpg"), new Point(60, 60), 100 ,100));
            System.out.println(drawing.getItems().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // New drawing tool to draw the entire drawing to the screen.
        DrawingTool dt = new DrawingTool(drawing, new JavaFXPaintable(canvas.getGraphicsContext2D()));
        dt.Draw();

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
