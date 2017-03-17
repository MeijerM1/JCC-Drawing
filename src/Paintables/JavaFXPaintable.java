package Paintables;

import drawing.domain.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


/**
 * Created by max1_ on 21/02/2017.
 * Painting class that uses the JavaFX library
 */
public class JavaFXPaintable implements Paintable {

    GraphicsContext gc;

    public JavaFXPaintable(GraphicsContext gc) {
        this.gc = gc;
    }

    public Color getColor(DrawingItem dItem) {
        switch (dItem.getColor()) {
            case BLACK:
                return Color.BLACK;
            case BLUE:
                return Color.BLUE;
            case GREEN:
                return Color.GREEN;
            case RED:
                return Color.RED;
            case WHITE:
                return Color.WHITE;
        }
        return Color.BLACK;
    }

    @Override
    public void paint(Oval oval) {
        gc.setStroke(getColor(oval));
        gc.strokeOval(oval.getAnchor().getX(), oval.getAnchor().getY(), oval.getWidth(), oval.getHeight());
    }

    @Override
    public void paint(Polygon polygon) {
        // Get all X point
        gc.setStroke(getColor(polygon));
        double[] doublesX = new double[polygon.getVertices().size()];
        for (int i = 0; i < doublesX.length; i++) {
            doublesX[i] = polygon.getVertices().get(i).getX();
        }

        // Get all Y point
        double[] doublesY = new double[polygon.getVertices().size()];
        for (int i = 0; i < doublesY.length; i++) {
            doublesY[i] = polygon.getVertices().get(i).getY();
        }

        gc.strokePolygon(doublesX, doublesY ,(polygon.getVertices().size()));
    }

    @Override
    public void paint(PaintedText text) {
        gc.setStroke(getColor(text));
        Text measure = new Text(text.getContent());
        gc.setFont(new Font(text.getWidth() / measure.getLayoutBounds().getWidth() * 10));
        gc.fillText(text.getContent(), text.getAnchor().getX(), text.getAnchor().getY() + text.getHeight(), text.getWidth());
    }

    @Override
    public void paint(Image image) {
        try {
            gc.setStroke(getColor(image));
        System.out.println("Painting image");
        System.out.println(image.getFile().getAbsoluteFile());
        javafx.scene.image.Image img = new javafx.scene.image.Image(image.getFile().toURI().toString());
        gc.drawImage(img, image.getAnchor().getX(), image.getAnchor().getY(), image.getWidth(), image.getHeight());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
