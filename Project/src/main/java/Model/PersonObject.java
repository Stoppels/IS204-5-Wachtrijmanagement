/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Stefan
 */
public class PersonObject {

    private int personId;
    private ArrayList<JsonObject> jsonList;

    public PersonObject(ArrayList<JsonObject> jsonList) {
        this.personId = 0;
        this.jsonList = jsonList;
    }

    public void add(JsonObject jsonObject) {
        this.jsonList.add(jsonObject);
        if (this.personId == 0) {
            this.personId = this.jsonList.get(0).getTrack_id();
        }
    }

    public ArrayList<JsonObject> getJsonList() {
        return jsonList;
    }

    public void sort() {
        // sort by timestamp
    }
    
//    public Timestamp getStartTime() {
//        if (jsonList.isEmpty()) {
//            return null;
//        } 
//        Timestamp t = jsonList.get(0).getTimestamp();
//        for (JsonObject jsonObject : jsonList) {
//            
//        }
//        return 
//    }

    @Override
    public String toString() {
        return "PersonObject - personId: " + this.personId + " nr of lines: " + this.jsonList.size() + "\n";
    }

}
