package javafx;

import drawing.domain.*;
import drawing.domain.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by max1_ on 21/02/2017.
 * Painting class that uses the JavaFX library
 */
public class JavaFXPaintable implements Paintable {

    GraphicsContext gc;

    public JavaFXPaintable(GraphicsContext gc) {
        this.gc = gc;
    }

    @Override
    public void paint(Oval oval) {
        gc.strokeOval(oval.getAnchor().getX(), oval.getAnchor().getY(), oval.getWidth(), oval.getHeight());
    }

    @Override
    public void paint(Polygon polygon) {
        // Get all X point
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
        gc.fillText(text.getContent(), text.getAnchor().getX(), text.getAnchor().getY(), text.getWidth());
    }

    @Override
    public void paint(Image image) {
        try {

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
