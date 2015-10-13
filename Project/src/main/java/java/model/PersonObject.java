/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.model;

import java.util.ArrayList;

/**
 *
 * @author Stefan
 */
public class PersonObject {
    
    private int personId;
    private ArrayList<JsonObject> jsonList;

    public PersonObject(ArrayList<JsonObject> jsonList) {
        this.jsonList = jsonList;
        this.personId = jsonList.get(0).getTrack_id();
    }
    
    public void sort() {
        // sort by timestamp
    }
    
    
    
}
