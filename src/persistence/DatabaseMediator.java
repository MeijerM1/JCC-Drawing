package persistence;

import drawing.domain.Drawing;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
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
    public Drawing load(String nameDrawing) throws SQLException, IOException, ClassNotFoundException {
        init(props);
        ResultSet myRs;
        Statement myStmt;
        myStmt = con.createStatement();
        InputStream input = null;

        String sql = "select Drawing from drawing where ID=2";
        myRs = myStmt.executeQuery(sql);

        if (myRs.next()) {

            input = myRs.getBinaryStream("Drawing");
            System.out.println("Reading drawing from database...");
            System.out.println(sql);

            ObjectInputStream in = new ObjectInputStream(input);
            Drawing drawing = (Drawing) in.readObject();

            System.out.println("\nCompleted successfully!");
            return drawing;
        }
        return null;
    }

    @Override
    public boolean save(Drawing drawing) throws SQLException {
        String SQL_SERIALIZE_OBJECT = "INSERT INTO drawing(Drawing) VALUES (?)";

        init(props);
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(SQL_SERIALIZE_OBJECT);
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
