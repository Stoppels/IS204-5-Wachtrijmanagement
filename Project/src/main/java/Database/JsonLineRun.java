
package database;

import Model.JsonObject;
import Resources.Bbox;
import Resources.Position;
import Resources.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class JsonLineRun {
    
    private ResultSet result;
    private Connector dbLink;
    private Connection conn;
    private static final String SELECT_STATEMENT 
            = "SELECT track_id , time_stamp , event_type , alive_status , "
            + "position , bbox"
            + "FROM JsonLine"
            + "WHERE time_stamp = ? AND track_id = ? "
            + "OR event_type = ?";
    
    public JsonLineRun (){
    }
    
    public void retrieveConnection(){
        dbLink.startConnection();
        conn = dbLink.getConnection();
    }
    
    public ArrayList <JsonObject> retrieveData() throws SQLException{
        PreparedStatement statement = conn.prepareStatement(SELECT_STATEMENT);
        result = statement.getResultSet();
        ArrayList <JsonObject> list = new ArrayList();
        result.beforeFirst();
        while (result.next() == true){
        list.add(new JsonObject(result.getInt(1),
                new Timestamp(result.getString(2)),
                result.getInt(3),
                result.getInt(4),
                new Position(result.getString(5)),
                new Bbox(result.getString(6))));
        }
        return list;
    }
    
    public ArrayList <JsonObject> retrieveData(String sqlStatement) throws SQLException{
        PreparedStatement statement = conn.prepareStatement(sqlStatement);
        result = statement.getResultSet();
        ArrayList <JsonObject> list = new ArrayList();
        result.beforeFirst();
        while (result.next() == true){
        list.add(new JsonObject(result.getInt(1),
                new Timestamp(result.getString(2)),
                result.getInt(3),
                result.getInt(4),
                new Position(result.getString(5)),
                new Bbox(result.getString(6))));
        }
        return list;
    }
}
