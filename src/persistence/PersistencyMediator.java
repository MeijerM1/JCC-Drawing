package persistence;

import drawing.domain.Drawing;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by max1_ on 07/03/2017.
 * Handles loading saving of drawing
 */
public interface PersistencyMediator {
    Drawing load(String nameDrawing) throws SQLException, IOException, ClassNotFoundException;
    boolean save(Drawing drawing, File file) throws SQLException;
    boolean init(Properties props) throws SQLException;
}
