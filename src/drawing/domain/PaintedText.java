package drawing.domain;

import javafx.scene.shape.Rectangle;

import java.io.Serializable;

/**
 * Created by max1_ on 14/02/2017.
 * Text that will be drawn on the screen according to the size of the box surrounding it.
 */
public class PaintedText extends DrawingItem implements Serializable {

    private String content;
    private String fontName;
    private Point anchor;
    private double width;
    private double height;

    public PaintedText(String content, String fontName, Point anchor, double width, double height) {
        this.content = content;
        this.fontName = fontName;
        this.anchor = anchor;
        this.width = width;
        this.height = height;
    }

    @Override
    public void paintUsing(Paintable paintable) {
        paintable.paint(this);
    }

    @Override
    public boolean insideBoundingBox(Point point) {
        return (anchor.getX() <= point.getX()) && (point.getX() <= (anchor.getX() + getWidth())) && (anchor.getY() <= point.getY()) && (point.getY() <= (anchor.getY() + getHeight()));
    }

    @Override
    public Rectangle getBoundingBox() {
        return new Rectangle(anchor.getX(), anchor.getY(), width, height);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
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
    public String toString() {
        return "PaintedText{" +
                "content='" + content + '\'' +
                ", fontName='" + fontName + '\'' +
                ", anchor=" + anchor +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
