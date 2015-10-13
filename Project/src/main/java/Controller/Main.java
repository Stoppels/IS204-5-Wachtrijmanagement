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
        
        JsonController jsoncon = new JsonController("recording001_short.json");
        
        jsoncon.read();
        
        jsoncon.toString();
        
        PersonController perscon = new PersonController();
//        perscon.convertJSONtoPerson(jsoncon.get());
//        perscon.getAmountPersons();
        
    }
}
