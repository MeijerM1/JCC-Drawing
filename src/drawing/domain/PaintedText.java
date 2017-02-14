package drawing.domain;

/**
 * Created by max1_ on 14/02/2017.
 */
public class PaintedText extends DrawingItem {

    private String content;
    private String fontName;
    private Point anchor;
    private double width;
    private double height;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        setPreviousState();
        this.content = content;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        setPreviousState();
        this.fontName = fontName;
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
        return "PaintedText{" +
                "content='" + content + '\'' +
                ", fontName='" + fontName + '\'' +
                ", anchor=" + anchor +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
