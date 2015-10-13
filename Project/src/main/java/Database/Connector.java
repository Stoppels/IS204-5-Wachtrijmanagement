package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ryan
 */
public class Connector {

    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String DBURL = "https://oege.ie.hva.nl:3306";
    private static final String DBUSER = "zshayann001";
    private static final String DBPASS = "JbJDsT1n/QOnyK";

    private ResultSet result = null;
    private int affectedRows = -1;
    Connection conn = null;
    
    public void startConnection() {
        try {

            Class.forName(DRIVER);
            conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);

        } catch (SQLException e) {

            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        conn = null;
    }

    public ResultSet performSelect(PreparedStatement query) {
        try {
            result = query.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int performUpdate(PreparedStatement pstmt) {
        try {
            affectedRows = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return affectedRows;
    }

    public Connection getConnection() {
        return conn;
    }
}
