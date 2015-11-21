/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.controller;

import java.util.ArrayList;
import java.util.Collections;
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

    public Bbox getAverage() {
        return average;
    }

    public void averageBbox() {
        ArrayList<Bbox> boxes = new ArrayList<>();
    }
    
    private ArrayList<Integer> calculateAmountPersons(ArrayList<JsonObject> jsonList) {
        // geeft een array met alle unieke track_id's
        ArrayList<Integer> first = new ArrayList<>();
        ArrayList<Integer> all = new ArrayList<>();
        first.add(0);
        for (JsonObject jsonObject : jsonList) {
            all.add(jsonObject.getTrack_id());
            int count = 0;
            for (int i = 0; i < first.size(); i++) {
                if (jsonObject.getTrack_id() != first.get(i)) {
                    count++;
                }
            }
            if (count == first.size()) {
                first.set(count - 1, jsonObject.getTrack_id());
                first.add(0);
        }}
        for (Integer i:all){
            if (Collections.frequency(all, i)==1){
                first.remove(i);
            }
        }
        return first;
    }

    public void convertJsonToPerson(ArrayList<JsonObject> jsonList) {
        // Maakt een nieuwe PersonObject voor elke track_id
        // Verdeelt alle Json lijnen per track_id over alle PersonObjects
        if (!jsonList.isEmpty()) {
            ArrayList p = calculateAmountPersons(jsonList);
            for (int i = 0; i < p.size() - 1; i++) {
                list.add(new PersonObject(new ArrayList<JsonObject>())); 
            }
            for (JsonObject jsonObject : jsonList) {
                for (int i = 0; i < p.size(); i++) {
                    if (jsonObject.getTrack_id() == (int) p.get(i)) {
                        list.get(i).add(jsonObject);
                    }
                }
            }
            setStartEndTime();
        } else {
            System.out.println("convertJsonToPerson()" + ERROR);
        }
    }

//    public void PersonCheckOneLine(){
//        for (){
//            if (o.getJsonList().size() == 1){
//                list.remove(o);
//            }
//        }
//    }
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
