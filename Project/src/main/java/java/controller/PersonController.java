/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.controller;

import java.model.JsonObject;
import java.model.PersonObject;
import java.model.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Stefan
 */
public class PersonController {

    Timestamp start;
    Timestamp end;
    int amountPersons;
    ArrayList<PersonObject> list;
    
    public PersonController() {
        this.list = new ArrayList<PersonObject>();
    }
    
    public ArrayList<PersonObject> convertJSONtoPerson(ArrayList<JsonObject> jsonList) {
        
        // verdeel arraylist over PersonObjects op basis van trackid
        for (JsonObject jsonObject : jsonList) {
            
            // create personObject for each unique track id
            
        }
        
        return list;
    }
    
}
