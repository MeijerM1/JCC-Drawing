package GUI;
import Paintables.JavaFXPaintable;
import drawing.domain.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import persistence.SerializationMediator;
import java.net.URL;
import java.util.ResourceBundle;

public class mainController implements Initializable {

    @FXML private Canvas drawingCanvas;
    @FXML private ComboBox shapeCb;
    @FXML private ComboBox colorCb;
    @FXML ListView<DrawingItem> drawingItemsListView;

    private GraphicsContext gc ;
    private Drawing drawing;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gc = drawingCanvas.getGraphicsContext2D();
        drawing = new Drawing();
        drawingItemsListView.setItems(drawing.itemsToObserve());

        ObservableList<String> shapeOptions =
                FXCollections.observableArrayList(
                        "Image",
                        "Oval",
                        "Text",
                        "Polygon"
                );
        shapeCb.setItems(shapeOptions);

        ObservableList<String> colorOptions = FXCollections.observableArrayList();
        String[] colors = Color.names();
        for (String color: colors) {
            colorOptions.add(color);
        }
        colorCb.setItems(colorOptions);


        // This is important, learn this
        drawingCanvas.addEventHandler(MouseEvent.MOUSE_PRESSED,new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                addShape(new Point(event.getX(), event.getY()));
            }
        });
    }

    public void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Warning");
        alert.setHeaderText("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void Load(ActionEvent actionEvent) {
        try {
            SerializationMediator sm = new SerializationMediator();
            // draw(sm.load("D01"));
        } catch(Exception e) {
            showError(e.getMessage());
        }
    }

    private void addShape(Point point) {
        Oval oval = new Oval(point, 100, 100, 100, Color.BLACK);
        drawing.addItem(oval);

        draw();
    }

    private void draw() {
        //TODO
        drawing.paintUsing(new JavaFXPaintable(gc));
    }
}
