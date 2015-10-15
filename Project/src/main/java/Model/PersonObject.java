/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Resources.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Stefan
 */
public class PersonObject {

    private Timestamp start;
    private Timestamp end;
    private int personId;
    private final ArrayList<JsonObject> jsonList;

    public PersonObject(ArrayList<JsonObject> jsonList) {
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

    public Timestamp getStart() {
        setStartEndTime();
        return start;
    }

    public Timestamp getEnd() {
        setStartEndTime();
        return end;
    }

    private void setStartEndTime() {
        if (!this.jsonList.isEmpty()) {
            this.start = this.jsonList.get(0).getTimestamp();
            this.end = this.jsonList.get(jsonList.size() - 1).getTimestamp();
        }
    }

    public void sort() {
        // sort by timestamp
    }

    @Override
    public String toString() {
        return "PersonObject - personId: " + this.personId + " nr of lines: " + this.jsonList.size()
                //#############################################################################################################################   REMOVE
                + "\tbegin time: " + this.start + " end time: " + this.end
        //#############################################################################################################################   REMOVE
                + "\n";
    }

}
