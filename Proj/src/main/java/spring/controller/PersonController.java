/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.controller;

import java.util.ArrayList;
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
