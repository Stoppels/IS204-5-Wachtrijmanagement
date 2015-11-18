/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.controller;

import java.util.ArrayList;
import spring.model.Bbox;
import spring.model.JsonObject;
import spring.model.PersonObject;
import spring.model.Timestamp;

/**
 * PersonController zet een ArrayList< JsonObject> om naar een PersonObject, per
 * track id. Zo kunnen aparte personen verstuurd worden naar de View, die op
 * Timestamp gesorteerd zijn.
 *
 * @author Stefan
 */
public class PersonController {

    private final String ERROR = "\tCan't compute, list is empty";
    private Bbox average;
    private Timestamp start;
    private Timestamp end;
    private ArrayList<PersonObject> list;

    public PersonController() {
        this.list = new ArrayList<>();
    }

    public Timestamp getStartTime() {
        if (list.isEmpty()) {
            System.out.println("getStartTime()" + ERROR);
            return null;
        }
        return start;
    }

    public Timestamp getEndTime() {
        if (list.isEmpty()) {
            System.out.println("getEndTime()" + ERROR);
            return null;
        }
        return end;
    }

    public ArrayList<PersonObject> getList() {
        return list;
    }
    
    // validates the list of personObjects
    public void validatePersons() {
        for (PersonObject personObject : list) {
            validatePerson(personObject);
        }
    }

    /* TODO: Verander de naam van de methode, samen met de variabele
    Feedback van Stefan:
    Wat is average?? We snappen dat het een BBOX is, maar waar komt hij vandaan?
    Waarom staat hij in deze class?
    */
    public Bbox getAverage() {
        return average;
    }
    
    /* TODO: Verander de naam van de methode, geef aan wat hij doet
    Feedback van Stefan
    Wat doet deze methode?
    */
    public void averageBbox(){
        ArrayList <Bbox> boxes = new ArrayList<>();
        for (int i =0;i<list.size();i++){
            if (list.get(i).averageBbox().getX1()<-10||list.get(i).averageBbox().getX1()>0||list.get(i).averageBbox().getY2()<-10){
                list.remove(i);
            }
        }
        for (int i = 0;i<list.size();i++){
                boxes.add(list.get(i).averageBbox());
            }
 
        for (Bbox box : boxes){
            System.out.println(box);
        }
        average = oneBbox(boxes);
        
    }
    
    /* TODO: Nogmaals, dit is misschien wel de meest onduidelijke methode die ik
            ooit heb gezien!
    Feedback van Stefan
    Elke idioot snapt dat hij de gemiddelde waarden van een BBOX probeert terug
    te geven. Maar deze naam slaat alles.
    Waarom staat deze methode in deze class?
    */
    public Bbox oneBbox(ArrayList<Bbox> boxes){
       float x1 = 0,x2 = 0,y1 = 0,y2 = 0,z1 = 0,z2 = 0;
        for (Bbox box:boxes){
            x1+=box.getX1();
            x2+=box.getX2();
            y1+=box.getY1();
            y2+=box.getY2();
            z1+=box.getZ1();
            z2+=box.getZ2();
        }
        x1/= boxes.size();
        x2/= boxes.size();
        y1/= boxes.size();
        y2/= boxes.size();
        z1/= boxes.size();
        z2/= boxes.size();
        return new Bbox(x1,x2,y1,y2,z1,z2);
    }
    
    private void validatePerson(PersonObject personObject) {
        ArrayList<JsonObject> result = new ArrayList<>();
        JsonObject j = personObject.getJsonList().get(0);
        Timestamp t = j.getTimestamp();
        for (JsonObject jsonObject : personObject.getJsonList()) {
            if (t.compareTo(jsonObject.getTimestamp()) == 0) {
                j.mergeJsonObject(jsonObject);
            } else {
                result.add(j);
                t = jsonObject.getTimestamp();
                j = jsonObject;
            }
        }
        result.add(j);
        personObject.setJsonList(result);
    }

    private int[] calculateAmountPersons(ArrayList<JsonObject> jsonList) {
        // geeft een array met alle unieke track_id's
        int[] first = new int[1];
        int[] second;
        for (JsonObject jsonObject : jsonList) {
            int count = 0;
            for (int i = 0; i < first.length; i++) {
                if (jsonObject.getTrack_id() != first[i]) {
                    count++;
                }
            }
            if (count == first.length) {
                second = new int[first.length + 1];
                System.arraycopy(first, 0, second, 0, first.length);
                first = second;
                first[count - 1] = jsonObject.getTrack_id();
            }
        }
        return first;
    }

    public void convertJsonToPerson(ArrayList<JsonObject> jsonList) {
        // Maakt een nieuwe PersonObject voor elke track_id
        // Verdeelt alle Json lijnen per track_id over alle PersonObjects
        if (!jsonList.isEmpty()) {
            int[] p = calculateAmountPersons(jsonList);
            for (int i = 0; i < p.length - 1; i++) {
                list.add(new PersonObject(new ArrayList<JsonObject>()));
            }
            for (JsonObject jsonObject : jsonList) {
                for (int i = 0; i < p.length; i++) {
                    if (jsonObject.getTrack_id() == p[i]) {
                        list.get(i).add(jsonObject);
                    }
                }
            }
            setStartEndTime();
            validatePersons();
        } else {
            System.out.println("convertJsonToPerson()" + ERROR);
        }
    }

    private void setStartEndTime() {
        if (this.list.isEmpty()) {
            System.out.println("setStartEndTime()" + ERROR);
        } else {
            Timestamp s = list.get(0).getStart();
            Timestamp e = list.get(0).getEnd();
            for (PersonObject personObject : list) {
                if (s.compareTo(personObject.getStart()) == 1) {
                    s = personObject.getStart();
                }
                if (e.compareTo(personObject.getEnd()) == -1) {
                    e = personObject.getEnd();
                }
            }
            this.end = e;
            this.start = s;
        }
    }

    @Override
    public String toString() {
        return "PERSONOBJECT\n"
                + "\n"
                + getList();
    }
}
