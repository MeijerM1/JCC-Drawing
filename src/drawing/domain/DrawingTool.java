package drawing.domain;

import javafx.JavaFXPaintable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

/**
 * Created by max1_ on 21/02/2017.
 * Tool that does actual drawing
 */
public class DrawingTool extends javafx.application.Application {

    private Drawing drawing;
    private Paintable paintable;

    public DrawingTool ( Drawing drawing, Paintable paintable) {
        this.drawing = drawing;
        this.paintable = paintable;
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

    @Override
    public void start(Stage primaryStage) throws Exception {

        Canvas drawingCanvas = new Canvas(500, 500);
        GraphicsContext gc = drawingCanvas.getGraphicsContext2D();
        this.paintable = new JavaFXPaintable(gc);
        this.drawing.paintUsing(this.paintable);
    }
}
