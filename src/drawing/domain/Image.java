package drawing.domain;

import java.io.File;

/**
 * Created by max1_ on 14/02/2017.
 */
public class Image extends DrawingItem {

    private File file;
    private Point anchor;
    private double width;
    private double height;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        setPreviousState();
        this.file = file;
    }

    public Point getAnchor() {
        return anchor;
    }

    public void setAnchor(Point anchor) {
        setPreviousState();
        this.anchor = anchor;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        setPreviousState();
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        setPreviousState();
        this.height = height;
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
