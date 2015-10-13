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

    public Timestamp getStart() {
        return start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public int getAmountPersons() {
        return amountPersons;
    }

    public ArrayList<PersonObject> getList() {
        return list;
    }

    public ArrayList<PersonObject> convertJSONtoPerson(ArrayList<JsonObject> jsonList) {

        // verdeel arraylist over PersonObjects op basis van trackid
        int[] first = new int[1];
        int[] second;
        for (JsonObject jsonObject : jsonList) {
            int count = 0;
            for (int i = 0; i < first.length; i++) {
                if (jsonObject.getTrack_id() != first[i]) {
                    count++;
                } else {
                    list.get(i).add(jsonObject);
                }
            }
            if (count == first.length) {
                list.add(new PersonObject(new ArrayList<JsonObject>()));
                list.get(count-1).add(jsonObject);
                second = new int[first.length + 1];
                for (int i = 0; i < first.length; i++) {
                    second[i] = first[i];
                }
                first = second;
                first[count - 1] = jsonObject.getTrack_id();
            }
            // create personObject for each unique track id
        }
        this.amountPersons = first.length -1;
        return list;
    }

    @Override
    public String toString() {
        return "PERSONOBJECT\n"
                + "\n"
                + getList();
    }
}
