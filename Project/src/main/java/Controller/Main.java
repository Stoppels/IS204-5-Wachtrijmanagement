/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 * Testing stuff in Main
 * @author Stefan
 */
public class Main {
    
    public static void main(String[] args) {
        
        // Maakt een JsonController om JSON files te verwerken
        JsonController jsoncon = new JsonController("recording001_short.json");
//        System.out.println(jsoncon);
        
        // Maakt een PersonController om JsonObjects tot PersonObjects te verwerken
        PersonController perscon = new PersonController();
        perscon.convertJSONtoPerson(jsoncon.getList());
//        System.out.println(perscon);
        

        int listNr = 5;
        for (int i = 0; i < perscon.getList().get(listNr).getJsonList().size(); i++) {
//            System.out.println(perscon.getList().get(listNr).getJsonList().get(i).toString());
//            System.out.println(perscon.getList().get(listNr).getJsonList().get(i).getTimestamp().toDouble());
        }
        System.out.println(perscon.getStart().toString());
//        System.out.println(perscon.getEnd().toString());
        
        
    }
}
