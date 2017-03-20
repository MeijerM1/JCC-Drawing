package GUI;
import Paintables.JavaFXPaintable;
import drawing.domain.*;
import drawing.domain.Color;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class mainController implements Initializable {

    @FXML private Canvas drawingCanvas;
    @FXML private ComboBox shapeCb;
    @FXML private ComboBox colorCb;
    @FXML ListView<DrawingItem> drawingItemsListView;
    @FXML private MenuItem deleteButton;
    @FXML private MenuItem addDrawingButton;
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
                        "Polygon",
                        "Get intersect"
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
                //System.out.println(String.format("Mouse dragged at %f, %f", event.getX(), event.getY()));
                draw();
                drawTempShape(startPoint, (event.getX() - startPoint.getX()), (event.getY() - startPoint.getY()));
            }
        });
        drawingCanvas.addEventHandler(MouseEvent.MOUSE_RELEASED,new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if((String) shapeCb.getValue() == "Get intersect") {
                    checkOverlap(new Point(event.getX(), event.getY()));
                } else {
                    addShape(startPoint, (event.getX() - startPoint.getX()), (event.getY() - startPoint.getY()));

                }
            }
        });

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                deleteItem();
            }
        });

        drawingItemsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DrawingItem>() {
            @Override
            public void changed(ObservableValue<? extends DrawingItem> observable, DrawingItem oldValue, DrawingItem newValue) {
                System.out.println("Item selected: " + newValue);
                drawBoundingBox(newValue);
            }
        });

        addDrawingButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Drawing addDrawing = new Drawing();
                addDrawing.addItem(new Oval(new Point(200, 200), 30, 30, 10, Color.BLUE));
                addDrawing.addItem(new Oval(new Point(100, 100), 30, 30, 10, Color.BLUE));
                addDrawing.addItem(new Oval(new Point(300, 300), 30, 30, 10, Color.BLUE));
                drawing.addItem(addDrawing);
                draw();

            }
        });

        // Keyboard shortcuts
        // Not the right place to put them but ohh well
        deleteButton.setAccelerator(new KeyCodeCombination(KeyCode.DELETE));
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
                    if(checkOverlap(oval)) { showError("Overlap detected, aborting"); draw(); return; };
                    drawing.addItem(oval);
                    break;
                case "Text":
                    TextInputDialog textInputDialog = new TextInputDialog("Text");
                    Optional<String> result = textInputDialog.showAndWait();
                    if (result.isPresent()) {
                        PaintedText text = new PaintedText(result.get(), "Arial", point, width, height );
                        drawing.addItem(text);
                    }
                    break;
                case "Image":
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Select image");
                    fileChooser.getExtensionFilters().addAll(
                            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
                    File file = fileChooser.showOpenDialog(drawingCanvas.getScene().getWindow());
                    if (file != null) {
                        Image img = new Image(file, point, width, height);
                        drawing.addItem(img);
                    }
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

    private void drawBoundingBox(DrawingItem item) {
        draw();
        Rectangle rect = item.getBoundingBox();
        gc.setStroke(javafx.scene.paint.Color.BLACK);
        gc.strokeRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }

    private void drawTempShape(Point point, double width, double height) {

        switch ((String) shapeCb.getValue()) {
            case "Oval":
                Oval oval = new Oval(point, width, height, 1, Color.valueOf((String) colorCb.getValue()));
                if(checkOverlap(oval)){
                    gc.setStroke(javafx.scene.paint.Color.RED);
                } else {
                    gc.strokeOval(point.getX(), point.getY(), width, height);
                }
                gc.strokeOval(point.getX(), point.getY(), width, height);
                break;
            case "Text":
                gc.setStroke(getColor(Color.valueOf((String) colorCb.getValue())));
                gc.strokeRect(point.getX(), point.getY(), width, height);
                break;
            case "Image":
                gc.setStroke(getColor(Color.valueOf((String) colorCb.getValue())));
                gc.strokeRect(point.getX(), point.getY(), width, height);
                break;
            case "Polygon":
                //TODO
                break;
            default:
                return;
        }
    }

    public javafx.scene.paint.Color getColor(Color color) {
        switch (color) {
            case BLACK:
                return javafx.scene.paint.Color.BLACK;
            case BLUE:
                return javafx.scene.paint.Color.BLUE;
            case GREEN:
                return javafx.scene.paint.Color.GREEN;
            case RED:
                return javafx.scene.paint.Color.RED;
            case WHITE:
                return javafx.scene.paint.Color.WHITE;
        }
        return javafx.scene.paint.Color.BLACK;
    }

    public boolean checkOverlap(DrawingItem item) {
        for(DrawingItem i : drawing.getItems()) {
            if(i.overlaps(item)) {
                System.out.println("Overlap detected between" + item + " and " + i);
                return true;
            }
        }
        return false;
    }

    public boolean checkOverlap(Point point) {
        for(DrawingItem i : drawing.getItems()) {
            if(i.insideBoundingBox(point)) {
                System.out.println("Point is withing the bounding box of " + i );
                return true;
            }
        }
        System.out.println("Point is not within any items");
        return false;
    }
}
