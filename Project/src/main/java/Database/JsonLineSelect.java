//
//package database;
//
//import Model.JsonObject;
//import Model.PersonObject;
//import Resources.Bbox;
//import Resources.Position;
//import Resources.Timestamp;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//
//public class JsonLineSelect {
//    
//    private Connector dbLink;
//    private ResultSet result;
//    private Connection conn;
//    private static final String SELECT_STATEMENT 
//            = "SELECT track_id , time_stamp , event_type , alive_status , "
//            + "position , bbox"
//            + "FROM JsonLine";
//    
//    public JsonLineSelect (){
//        dbLink = new Connector();
//    }
//    
//    public void retrieveConnection(){
//        dbLink.startConnection();
//        conn = dbLink.getConnection();
//    }
//    
//    public ArrayList <PersonObject> retrieveData() throws SQLException{
//        PreparedStatement statement = conn.prepareStatement(SELECT_STATEMENT);
//        result = statement.getResultSet();
//        result.beforeFirst();
//        ArrayList <PersonObject> list = new ArrayList();
//        ArrayList <ArrayList<JsonObject>> listJ = new ArrayList();
//        for (int i = 0;i>listJ.size();i++){
//            listJ.set(i, new ArrayList());
//        }
//        while (result.next() == true){
//                listJ.get(result.getInt(1)).add(new JsonObject(result.getInt(1),
//                new Timestamp(result.getString(2)),
//                result.getInt(3),
//                result.getInt(4),
//                new Position(result.getString(5)),
//                new Bbox(result.getString(6))));
//                
//        }
//        for (ArrayList<JsonObject> listJ1 : listJ) {
//            list.add(new PersonObject(listJ1));
//        }
//        return list;
//    }
//
//    public ArrayList <PersonObject> retrieveData(String sqlStatement) throws SQLException{
//        PreparedStatement statement = conn.prepareStatement(sqlStatement);
//        result = statement.getResultSet();
//        ArrayList <PersonObject> list = new ArrayList();
//        ArrayList <ArrayList<JsonObject>> listJ = new ArrayList();
//        for (int i = 0;i>listJ.size();i++){
//            listJ.set(i, new ArrayList());
//        }
//        result.beforeFirst();
//        while (result.next() == true){
//        listJ.get(result.getInt(1)).add(new JsonObject(result.getInt(1),
//                new Timestamp(result.getString(2)),
//                result.getInt(3),
//                result.getInt(4),
//                new Position(result.getString(5)),
//                new Bbox(result.getString(6))));
//        }
//        for (ArrayList<JsonObject> listJ1 : listJ) {
//            list.add(new PersonObject(listJ1));
//        }
//        return list;
//    }
//}
