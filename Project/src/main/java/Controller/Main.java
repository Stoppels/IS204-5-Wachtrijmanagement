/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Database.Connector;
import Database.JsonLineInsert;
import java.sql.SQLException;

/**
 * Testing stuff in Main
 * @author Stefan
 */
public class Main {
    
    public static void main(String[] args) {
        
        // Maakt een JsonController om JSON files te verwerken
        JsonController JC = new JsonController("recording001_short.json");
        
        // Maakt een PersonController om JsonObjects tot PersonObjects te verwerken
        PersonController PC = new PersonController();
        PC.convertJsonToPerson(JC.getList());

        
        System.out.println("Start time: \t" + PC.getStartTime().toString());
        System.out.println("End time: \t" + PC.getEndTime().toString());
        
        
        
    }
    
//    public static void main(String[] args) {
//        String fileName = "recording001_short.json";
//        // Maakt een JsonController om JSON files te verwerken
//        JsonController JC = new JsonController(fileName);
//        Connector connect = new Connector();
//        connect.startConnection();
//        // Maakt een PersonController om JsonObjects tot PersonObjects te verwerken
//        PersonController PC = new PersonController();
//        PC.convertJsonToPerson(JC.getList());
//        JsonLineInsert insert = new JsonLineInsert(connect);
//        try {
//            insert.insertData(PC.getList(), fileName);
//        } catch (SQLException ex) {
//            System.out.println("Database Connection Failed");
//        }
//        System.out.println("Start time: \t" + PC.getStartTime().toString());
//        System.out.println("End time: \t" + PC.getEndTime().toString());
//        
//        
//        
//    }
}
