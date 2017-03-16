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
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class mainController implements Initializable {

    @FXML private Canvas drawingCanvas;
    @FXML private ComboBox shapeCb;
    @FXML private ComboBox colorCb;
    @FXML ListView<DrawingItem> drawingItemsListView;
    @FXML private MenuItem deleteButton;
    Point startPoint;

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
                System.out.println(String.format("Mouse clicked at %f, %f", event.getX(), event.getY()));
                startPoint = new Point(event.getX(), event.getY());
            }
        });
        drawingCanvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                System.out.println(String.format("Mouse dragged at %f, %f", event.getX(), event.getY()));
            }
        });
        drawingCanvas.addEventHandler(MouseEvent.MOUSE_RELEASED,new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                addShape(startPoint, (event.getX() - startPoint.getX()), (event.getY() - startPoint.getY()));
            }
        });

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                deleteItem();
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

    private void addShape(Point point, double width, double height) {
        try {
            switch ((String) shapeCb.getValue()) {
                case "Oval":
                    Oval oval = new Oval(point, width, height, 100, Color.valueOf((String) colorCb.getValue()));
                    drawing.addItem(oval);
                    break;
                case "Text":
                    //TODO
                    break;
                case "Image":
                    //TODO
                    break;
                case "Polygon":
                    //TODO
                    break;
                default:
                    return;
            }
        } catch(NullPointerException n) {
            showError(n.getMessage());
        }
        draw();
    }

    private void draw() {
        //TODO
        gc.clearRect(0, 0, 1500, 1500);

        drawing.paintUsing(new JavaFXPaintable(gc));
    }

    private void deleteItem() {
        if(drawingItemsListView.getSelectionModel().getSelectedItem() == null) {
            showError("Select a item first");
            return;
        } else {
          drawing.removeItem(drawingItemsListView.getSelectionModel().getSelectedItem());
          draw();
        }

    }
}
