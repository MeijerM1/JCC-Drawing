package persistence;

import drawing.domain.Drawing;

import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by max1_ on 07/03/2017.
 * Handles loading saving of drawing
 */
public interface PersistencyMediator {
    Drawing load(String nameDrawing);
    boolean save(Drawing drawing) throws SQLException;
    boolean init(Properties props) throws SQLException;
}
