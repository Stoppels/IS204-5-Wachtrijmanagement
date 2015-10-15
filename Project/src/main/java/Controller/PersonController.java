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
import java.util.Arrays;

/**
 * PersonController zet een ArrayList<JsonObject> om naar een PersonObject, per
 * track id. Zo kunnen aparte personen verstuurd worden naar de View, die op
 * Timestamp gesorteerd zijn.
 *
 * @author Stefan
 */
public class PersonController {

    private Timestamp start;
    private Timestamp end;
    private int amountPersons;
    private ArrayList<PersonObject> list;

    public PersonController() {
        this.list = new ArrayList<PersonObject>();
    }

    private void setStartEndTime() {
        if (!this.list.isEmpty()) {
            Timestamp s = list.get(0).getStart();
            for (PersonObject personObject : list) {
                if (s.compareTo(personObject.getStart()) == 1) {
                    s = personObject.getStart();
                }
            }
            this.start = s;
        }
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
            System.out.println(end);
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

    // return een array met alle unieke track_id's
    // Maakt een nieuwe PersonObject voor elke track_id
    private int[] nrOfPersons(ArrayList<JsonObject> jsonList) {
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
                list.add(new PersonObject(new ArrayList<JsonObject>()));
                second = new int[first.length + 1];
                System.arraycopy(first, 0, second, 0, first.length);
                first = second;
                first[count - 1] = jsonObject.getTrack_id();
            }
        }
        this.amountPersons = first.length - 1;
        return first;
    }

    // Verdeelt alle Json lijnen per track_id over alle PersonObjects
    public void convertJsonToPerson(ArrayList<JsonObject> jsonList) {
        // verdeel arraylist over PersonObjects op basis van trackid
        int[] p = nrOfPersons(jsonList);
        for (JsonObject jsonObject : jsonList) {
            for (int i = 0; i < p.length; i++) {
                if (jsonObject.getTrack_id() == p[i]) {
                    list.get(i).add(jsonObject);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "PERSONOBJECT\n"
                + "\n"
                + getList();
    }
}
