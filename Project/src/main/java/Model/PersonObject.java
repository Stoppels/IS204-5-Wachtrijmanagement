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
        this.jsonList = jsonList;
    }

    public void add(JsonObject jsonObject) {
        this.jsonList.add(jsonObject);
    }

    public void sort() {
        // sort by timestamp
    }

    @Override
    public String toString() {
        return "PersonObject: " + this.personId + " lines: " + this.jsonList.size() + "\n";
    }

}
