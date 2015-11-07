/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Resources.Bbox;
import Resources.Position;
import Resources.Timestamp;
import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author Stefan
 */
public class PersonObject implements Serializable {
    private Timestamp start;
    private Timestamp end;
    private int personId;
    private ArrayList<JsonObject> jsonList;

    public PersonObject() {
    }
    
    public PersonObject(int id){
     this();
     personId = id;
    }

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
    
    public void setAverages(){
        ArrayList<JsonObject> averages = new ArrayList();
        ArrayList<JsonObject> toBeAveraged = new ArrayList();
        if (jsonList.size()==1){
                JsonObject object = jsonList.get(0);
                averages.add(object);
            }
        else{
        for (int i = 0 ; i<jsonList.size()-1;i++){
            JsonObject object = jsonList.get(i);
            JsonObject nextObject = jsonList.get(i+1);
            if (object.getTimestamp().getSecond() == nextObject.getTimestamp().getSecond()&&
                object.getTimestamp().getMinute() == nextObject.getTimestamp().getMinute()){
                if (toBeAveraged.isEmpty()){
                    toBeAveraged.add(object);
                    toBeAveraged.add(nextObject);
                }
                else{
                    toBeAveraged.add(nextObject);
                   
                }}
            else {
                 if (!toBeAveraged.isEmpty()){
                     averages = AvgBBoxPositionsandTime(toBeAveraged,averages);
                     toBeAveraged.clear();
            }
                 else {
                     averages.add(object);
                 }
         
        }}
    jsonList = averages;
    }}
    
    
    public ArrayList AvgBBoxPositionsandTime(ArrayList <JsonObject> list,ArrayList <JsonObject> averages){
                    float x1=0,x2=0,y1=0,y2=0,z1=0,z2 = 0;
                    int x=0,y=0,second=0,nano=0;
                    Timestamp timestamp = null;
                    JsonObject object = list.get(0);
                    for (JsonObject objectL: list){
                        x1+=objectL.getBbox().getX1();
                        x2+=objectL.getBbox().getX2();
                        y1+=objectL.getBbox().getY1();
                        y2+=objectL.getBbox().getY2();
                        z1+=objectL.getBbox().getZ1();
                        z2+=objectL.getBbox().getZ2();
                        x+=objectL.getPosition().getX();
                        y+=objectL.getPosition().getY();
                        second += objectL.getTimestamp().getSecond();
                        nano += objectL.getTimestamp().getNano();
                        timestamp = objectL.getTimestamp();
                    }
                    int divider = list.size();
                    Bbox bbox = new Bbox(x1/divider,x2/divider,
                            y1/divider,y2/divider,z1/divider,
                            z2/divider);
                    Position position = new Position(x/divider,y/divider);
                    Timestamp newTimeStamp = new Timestamp(timestamp.getYear(),timestamp.getMonth(),timestamp.getDate()
                            ,timestamp.getHour(),timestamp.getMinute(),second/divider,nano/divider);
                    averages.add(new JsonObject(object.getTrack_id(),newTimeStamp,object.getEvent_type(),object.getAlive_status(),position,bbox)); 
                return averages;}

    public String toString() {
        return "PersonObject - personId: " + this.personId + " nr of lines: " + this.jsonList.size()
                + "\tbegin time: " + this.start + " end time: " + this.end
                + "\n";
    }

}
