/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.PersonObject;

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
        System.out.println(PC.toString());
        
        for (PersonObject p : PC.getList()) {
            System.out.println(p.getJsonList().toString());
        }

        
        System.out.println("Start time: \t" + PC.getStartTime().toString());
        System.out.println("End time: \t" + PC.getEndTime().toString());
        
        
        
    }
}