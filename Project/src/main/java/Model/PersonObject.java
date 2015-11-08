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
    private Timestamp waitingTime;

    public PersonObject() {
    }
    
    public PersonObject(int id){
     this();
     personId = id;
    }
    
    public void setWaitingTime(){
        waitingTime = end.subtract(start);
    }

    public Timestamp getWaitingTime() {
        return waitingTime;
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
        ArrayList<JsonObject> averages = new ArrayList();//Een arrayList voor al berekende gemiddeldes
        ArrayList<JsonObject> toBeAveraged = new ArrayList();//Een arrayList voor objecten waaruit is gebleken dat ze tot dezelfde seconden horen
        if (jsonList.size()==1){//Als er maar een lijn is, hoeft ie geen gemiddeldes uit te rekenen en voegt ie het meteen toe aan de berekende lijst
                JsonObject object = jsonList.get(0);
                averages.add(object);
            }
        else{
        for (int i = 0 ; i<jsonList.size()-1;i++){
            JsonObject object = jsonList.get(i);//Haalt het object(i) op en slaat m op
            JsonObject nextObject = jsonList.get(i+1);//Haalt het daaropvolgende object op en slaat m op
            if (object.getTimestamp().getSecond() == nextObject.getTimestamp().getSecond()&&
                object.getTimestamp().getMinute() == nextObject.getTimestamp().getMinute()){//Als de minuut en seconde hetzelfde zijn doet ie hetvolgende: 
                if (toBeAveraged.isEmpty()){//Als de lijst leeg is , voegt ie allebei de elementen toe aan de lijst om zo exceptions te voorkomen
                    toBeAveraged.add(object);
                    toBeAveraged.add(nextObject);
                }
                else{//anders voegt hij alleen het daaropvolgende object toe
                    toBeAveraged.add(nextObject);
                   
                }
                if (i==toBeAveraged.size()-2&&!toBeAveraged.isEmpty()){//Als I bijna aan het einde van de lijst is , en er zit nog steeds objecten in toBeAveraged, berekent hij die
                averages = AvgBBoxPositionsandTime(toBeAveraged,averages);//Zie comments tweede methode
                toBeAveraged.clear();//Leegt de lijst nadat die is berekent
            }
            
            }
            else {//Als minuut en seconde niet van de huidige object niet hetzelfde zijn als het volgende object
                 if (!toBeAveraged.isEmpty()){//Als er nog objecten moeten worden berekent , doet ie dat 
                     averages = AvgBBoxPositionsandTime(toBeAveraged,averages);//Zie comments tweede methode
                     toBeAveraged.clear();//Leegt de lijst nadat die is berekent
            }
                 else {//Als er geen objecten in de toBeAveraged lijst zitten voegt hij het object toe aan de gemiddeldenlijst
                     averages.add(object);
                 }
                 
         
        }
           
        }
    jsonList = averages;//Als alles klaar is wordt de oude lijst vervangen met de lijst van gemiddeldes
    }}
    
    
    public ArrayList AvgBBoxPositionsandTime(ArrayList <JsonObject> list,ArrayList <JsonObject> averages){
                    float x1=0,x2=0,y1=0,y2=0,z1=0,z2 = 0;//Maakt 6 floats voor bbox
                    int x=0,y=0,second=0,nano=0;//Maakt 4 ints voor position en deel van timestamp
                    Timestamp timestamp = null;//Nieuw timestamp voor later gebruik
                    JsonObject object = list.get(0);//Pakt het eerste object voor later gebruik
                    for (JsonObject objectL: list){//Voegt alle waardes samen in gesplitste nummers
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
                    int divider = list.size();//Aantal personen
                    Bbox bbox = new Bbox(x1/divider,x2/divider,
                            y1/divider,y2/divider,z1/divider,
                            z2/divider);//Een bbox gemiddelde
                    Position position = new Position(x/divider,y/divider);//Een gemiddelde positie
                    Timestamp newTimeStamp = new Timestamp(timestamp.getYear(),timestamp.getMonth(),timestamp.getDate()
                            ,timestamp.getHour(),timestamp.getMinute(),second/divider,nano/divider);//Een gemiddelde timestamp (ik pak alleen secondenen nano secs omdat het toch per seconde berekent wordt; dat zijn de enige waardes die verschillen)
                    averages.add(new JsonObject(object.getTrack_id(),newTimeStamp,object.getEvent_type(),object.getAlive_status(),position,bbox));//Maakt een nieuw JsonObject en voegt m toe aan gemiddelde lijst  
                return averages;}//Geeft de nieuwe lijst terug

    public String toString() {
        return "PersonObject - personId: " + this.personId + " nr of lines: " + this.jsonList.size()
                + "\tbegin time: " + this.start + " end time: " + this.end
                + "\n";
    }

}
