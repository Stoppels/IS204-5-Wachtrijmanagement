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
        JsonController JC = new JsonController("recording001_short.json");
//        System.out.println(JC);
        
        // Maakt een PersonController om JsonObjects tot PersonObjects te verwerken
        PersonController PC = new PersonController();
        PC.convertJsonToPerson(JC.getList());
        System.out.println(PC);
        

        int listNr = 5;
//        for (int i = 0; i < PC.getList().get(listNr).getJsonList().size(); i++) {
//            System.out.println(PC.getList().get(listNr).getJsonList().get(i).toString());
//            System.out.println(PC.getList().get(listNr).getJsonList().get(i).getTimestamp().toDouble());
//        }
//        System.out.println(PC.getStart().toString());
//        System.out.println(PC.getEnd().toString());
        
        
    }
}
