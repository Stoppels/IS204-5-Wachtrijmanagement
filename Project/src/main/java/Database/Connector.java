package Database;

import java.sql.*;

/**
 *
 * @author Ryan
 */
public class Connector {

    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String DBURL = "https://oege.ie.hva.nl:3306/zshayann001";
    private static final String DBUSER = "shayann001";
    private static final String DBPASS = "JbJDsT1n/QOnyK";

    private ResultSet result = null;
    private int affectedRows = -1;
    Connection conn = null;
    
    public void startConnection() {
        try {
            
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            
        } catch (SQLException e) {
        } catch (ClassNotFoundException e) {
        }
    }

    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
        }

        conn = null;
    }

    public ResultSet performSelect(PreparedStatement query) {
        try {
            result = query.executeQuery();
        } catch (Exception e) {
        }
        return result;
    }

    public int performUpdate(PreparedStatement pstmt) {
        try {
            affectedRows = pstmt.executeUpdate();
        } catch (Exception e) {
        }
        return affectedRows;
    }

    public Connection getConnection() {
        return conn;
    }
}
