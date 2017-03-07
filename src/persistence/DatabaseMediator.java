package persistence;

import drawing.domain.Drawing;
import java.sql.*;
import java.util.Properties;

/**
 * Created by max1_ on 07/03/2017.
 * Handles saving and loading drawing to a database
 */
public class DatabaseMediator implements PersistencyMediator {

    private Properties props;
    private Connection con;

    @Override
    public Drawing load(String nameDrawing) {
        return null;
        // TODO
    }

    @Override
    public boolean save(Drawing drawing) throws SQLException {
        String SQL_SERIALIZE_OBJECT = "INSERT INTO drawing(Drawing) VALUES (?)";

        Properties prop = new Properties();
        init(prop);
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(SQL_SERIALIZE_OBJECT);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // just setting the class name
        pstmt.setObject(1, drawing);
        pstmt.executeUpdate();

        pstmt.close();
        System.out.println("Java object serialized to database. Object: " + drawing);
        return true;
    } {

    }

    @Override
    public boolean init(Properties props) throws SQLException {
        String dbUrl = "jdbc:mysql://studmysql01.fhict.local/dbi356615";
        String user = "dbi356615";
        String pass = "wachtwoord";
        con = DriverManager.getConnection(dbUrl, user, pass);
        return true;
    }
}
