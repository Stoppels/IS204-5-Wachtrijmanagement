/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Model.PersonObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ryan
 */
public class JsonLineInsert {

    private final Connector dbLink;
    private Connection conn;
    private static final String INSERT_STATEMENT
            = "INSERT track_id , time_stamp , event_type , alive_status , "
            + "position , bbox, file_name"
            + "INTO JsonLine"
            + "VALUES ?,?,?,?,?,?,?";

    public JsonLineInsert(Connector c) {
        dbLink = c;
        conn = dbLink.getConnection();
    }

//    public void retrieveConnection() {
//        dbLink.startConnection();
//        conn = dbLink.getConnection();
//    }

    public boolean insertData(ArrayList<PersonObject> list,String fileName) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(INSERT_STATEMENT);
        boolean result = true;
        for (PersonObject list1 : list) {
            for (int i = 0; i < list1.getJsonList().size(); i++) {
                if (list1.getJsonList().get(i) != null&&result == true) {
                    statement.setInt(1, list1.getJsonList().get(i).getTrack_id());
                    statement.setString(2, list1.getJsonList().get(i).getTimestamp().toString());
                    statement.setInt(3, list1.getJsonList().get(i).getEvent_type());
                    statement.setInt(4, list1.getJsonList().get(i).getAlive_status());
                    statement.setString(5,list1.getJsonList().get(i).getPosition().toString());
                    statement.setString(6,list1.getJsonList().get(i).getBbox().toString());
                    statement.setString(7, fileName);
                    result = statement.execute();
                }
            }
        }
        return result;
    }

    public boolean insertData(ArrayList<PersonObject> list, String fileName ,String sqlStatement) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(sqlStatement);
        for (PersonObject list1 : list) {
            for (int i = 0; i < list1.getJsonList().size(); i++) {
                if (list1.getJsonList().get(i) != null) {
                    statement.setInt(1, list1.getJsonList().get(i).getTrack_id());
                    statement.setString(2, list1.getJsonList().get(i).getTimestamp().toString());
                    statement.setInt(3, list1.getJsonList().get(i).getEvent_type());
                    statement.setInt(4, list1.getJsonList().get(i).getAlive_status());
                    statement.setString(5,list1.getJsonList().get(i).getPosition().toString());
                    statement.setString(6,list1.getJsonList().get(i).getBbox().toString());
                }
            }
        }
        return statement.execute();

    }
}
