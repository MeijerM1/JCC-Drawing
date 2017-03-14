package GUI;
import drawing.domain.Color;
import drawing.domain.Drawing;
import drawing.domain.Oval;
import drawing.domain.Point;
import javafx.JavaFXPaintable;
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
import javafx.scene.input.MouseEvent;
import persistence.SerializationMediator;
import java.net.URL;
import java.util.ResourceBundle;

public class mainController implements Initializable {

    @FXML private Canvas drawingCanvas;
    @FXML private ComboBox shapeCb;
    @FXML private ComboBox colorCb;
    private GraphicsContext gc ;
    private Drawing drawing;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gc = drawingCanvas.getGraphicsContext2D();
        drawing = new Drawing();

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
                gc.moveTo(event.getX(), event.getY());
            }
        });
    }

    public void saySomething(String message) {
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
            saySomething(e.getMessage());
        }
    }

    private void draw(Point point) {
        //TODO
    }
}
