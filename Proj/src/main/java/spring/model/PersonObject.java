/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.model;

import java.util.ArrayList;

/**
 *
 * @author Stefan
 */
public class PersonObject {

    private Timestamp start;
    private Timestamp end;
    private int personId;
    private ArrayList<JsonObject> jsonList;

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
    
    public void setJsonList(ArrayList<JsonObject> jsonList) {
        this.jsonList = jsonList;
    }

    public int getPersonId() {
        return personId;
    }
    
    public Bbox averageBbox(){
        float x1 = 0,x2 = 0,y1 = 0,y2 = 0,z1 = 0,z2 = 0;
        for (JsonObject j:jsonList){
            Bbox box = j.getBbox();
            x1+=box.getX1();
            x2+=box.getX2();
            y1+=box.getY1();
            y2+=box.getY2();
            z1+=box.getZ1();
            z2+=box.getZ2();
        }
        x1/= jsonList.size();
        x2/= jsonList.size();
        y1/= jsonList.size();
        y2/= jsonList.size();
        z1/= jsonList.size();
        z2/= jsonList.size();
        return new Bbox(x1,x2,y1,y2,z1,z2);
    }
    public Timestamp getStart() {
        setStartEndTime();
        return start;
    }

    public Timestamp getEnd() {
        setStartEndTime();
        return end;
    }

    // pass timestamps to JS
    public String timestampArray() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < jsonList.size(); i++) {
            sb.append("\"").append(jsonList.get(i).getTimestamp().secondsTotal()).append("\"");
            if (i + 1 < jsonList.size()) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    // pass x coordinates to JS
    public String xArray() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < jsonList.size(); i++) {
            sb.append("\"").append(jsonList.get(i).getPosition().getX()).append("\"");
            if (i + 1 < jsonList.size()) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    // pass y coordinates to JS
    public String yArray() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < jsonList.size(); i++) {
            sb.append("\"").append(jsonList.get(i).getPosition().getY()).append("\"");
            if (i + 1 < jsonList.size()) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private void setStartEndTime() {
        if (!this.jsonList.isEmpty()) {
            this.start = this.jsonList.get(0).getTimestamp();
            this.end = this.jsonList.get(jsonList.size() - 1).getTimestamp();
        }
    }

    @Override
    public String toString() {
        return "PersonObject - personId: " + this.personId + " nr of lines: " + this.jsonList.size()
                + "\tbegin time: " + this.start + " end time: " + this.end
                + "\n";
    }

}
