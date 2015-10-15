/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.JsonObject;
import Model.PersonObject;
import Resources.Timestamp;
import java.util.ArrayList;

/**
 * PersonController zet een ArrayList< JsonObject> om naar een PersonObject, per
 * track id. Zo kunnen aparte personen verstuurd worden naar de View, die op
 * Timestamp gesorteerd zijn.
 *
 * @author Stefan
 */
public class PersonController {

    private Timestamp start;
    private Timestamp end;
    private int amountPersons;
    private final ArrayList<PersonObject> list;

    public PersonController() {
        this.list = new ArrayList<PersonObject>();
    }

    public Timestamp getStart() {
        if (list.isEmpty()) {
            System.out.println("getStart() can't compute, list is empty");
            return null;
        }
        return start;
    }

    public Timestamp getEnd() {
        if (list.isEmpty()) {
            System.out.println("getEnd() can't compute, list is empty");
            return null;
        }
        for (PersonObject person : list) {
            end = person.getEnd();
            if (end.compareTo(person.getEnd()) == -1) {
                end = person.getEnd();
            }
        }
        return end;
    }

    public int getAmountPersons() {
        return amountPersons;
    }

    public ArrayList<PersonObject> getList() {
        return list;
    }
    
    private int[] nrOfPersons(ArrayList<JsonObject> jsonList) {
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
        this.amountPersons = first.length - 1;
        return first;
    }

    public void convertJsonToPerson(ArrayList<JsonObject> jsonList) {
        // Maakt een nieuwe PersonObject voor elke track_id
        // Verdeelt alle Json lijnen per track_id over alle PersonObjects
        int[] p = nrOfPersons(jsonList);
        for (int i = 0; i < p.length -1; i++) {
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
    }

    private void setStartEndTime() {
        if (this.list.isEmpty()) {
            System.out.println("setStartEndTime() can't compute, list is empty");
        } else {
            Timestamp s = list.get(0).getStart();
            for (PersonObject personObject : list) {
                if (s.compareTo(personObject.getStart()) == 1) {
                    s = personObject.getStart();
                }
            }
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
