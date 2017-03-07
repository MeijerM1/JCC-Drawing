package drawing.domain;

import java.io.File;
import java.io.Serializable;

/**
 * Created by max1_ on 14/02/2017.
 * An image to be displayed ont eh screen
 */
public class Image extends DrawingItem implements Serializable {

    private File file;
    private Point anchor;
    private double width;
    private double height;

    public Image(File file, Point anchor, double width, double height) {
        this.file = file;
        this.anchor = anchor;
        this.width = width;
        this.height = height;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Point getAnchor() {
        return anchor;
    }

    public void setAnchor(Point anchor) {
        this.anchor = anchor;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public void paintUsing(Paintable paintable) {
        paintable.paint(this);
    }

    @Override
    public String toString() {
        return "Image{" +
                "file=" + file +
                ", anchor=" + anchor +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
