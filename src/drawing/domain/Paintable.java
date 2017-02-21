package drawing.domain;

/**
 * Created by max1_ on 21/02/2017.
 * Interface to Allow painting with multiple graphics libraries
 */
public interface Paintable {
    void paint (Oval oval);
    void paint (Polygon polygon);
    void paint (PaintedText text);
    void paint (Image image);
}
