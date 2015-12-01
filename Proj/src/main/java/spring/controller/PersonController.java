/*
 * The MIT License
 *
 * Copyright 2015 IS204-5.
 * Application developed for Amsterdam University of Applied Sciences and
 * Gemeente Amsterdam.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package spring.controller;

import java.util.ArrayList;
import java.util.Collections;
import spring.model.Bbox;
import spring.model.JsonObject;
import spring.model.PersonObject;
import spring.model.Timestamp;

/**
 *
 *
 * @author IS204-5
 * @version 1.0
 */
public class PersonController {

    private final String ERROR = "\tCan't compute, list is empty";
    private Bbox average;
    private Timestamp start;
    private Timestamp end;
    private ArrayList<PersonObject> list;
    // tijelijk even een vaste max height en in height
    private final double MIN_HEIGHT = 0.4;
    private final double MAX_HEIGHT = 1.7;
    private final double destroyRadius = 0.5;

    public PersonController() {
        this.list = new ArrayList<>();
    }

    /**
     * Gets start time of PersonObject
     *
     * @return Timestamp
     */
    public Timestamp getStartTime() {
        if (list.isEmpty()) {
            System.out.println("getStartTime()" + ERROR);
            return null;
        }
        return start;
    }

    /**
     * Gets end time of PersonObject
     *
     * @return Timestamp
     */
    public Timestamp getEndTime() {
        if (list.isEmpty()) {
            System.out.println("getEndTime()" + ERROR);
            return null;
        }
        return end;
    }

    /**
     * Gets list of PersonObjects
     *
     * @return ArrayList<\PersonObject>
     */
    public ArrayList<PersonObject> getList() {
        return list;
    }

    public Bbox getAverage() {
        return average;
    }

    public void averageBbox() {
        ArrayList<Bbox> boxes = new ArrayList<>();
    }

    /**
     * This method returns an ArrayList<Integer> that holds track id's of each
     * PersonObject This way the convertJsonToPerson() method can simply check
     * on track id, and fill the ArrayLists<PersonObject> with the correct
     * persons (same track id's)
     *
     * @param jsonList holding JsonObjects to be checked on track id
     * @return ArrayList<\Integer> holding unique track id's
     */
    private ArrayList<Integer> calculateAmountPersons(ArrayList<JsonObject> jsonList) {
        // Returns an array with all unique track_ids.
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
            }
        }
        filterLessThanThreeLines(all, first);
        filterUnusualHeights(first, jsonList);
        filterShortDurations(first, jsonList);
        return first;
    }

    /**
     * Filter out any person with less than three lines of activity.
     *
     * @param all ArrayList <Integer>
     * @param first ArrayList <Integer>
     */
    private void filterLessThanThreeLines(ArrayList<Integer> all, ArrayList<Integer> first) {
        for (Integer i : all) {
            if (Collections.frequency(all, i) < 3) {
                first.remove(i);
            }
        }
    }

    /**
     * #########---- TODO -- #############
     * not sure if does what it has to do
     *
     * @param first
     * @param jsonList Filter out any person with an unusual height
     */
    private void filterUnusualHeights(ArrayList<Integer> first, ArrayList<JsonObject> jsonList) {
        // if bbox.z niet voldoet verwijder resultaat
        // op dit moment zit de array first vol met unieke resultaten(track ids)
        for (Integer i : first) {
            int j = jsonList.get(i).getTrack_id();
            if (!(jsonList.get(j).getBbox().getZ2() >= MIN_HEIGHT && jsonList.get(j).getBbox().getZ2() <= MAX_HEIGHT)) {
                first.remove(i);
            }
        }
    }

//#########---- TODO -- #############
    /**
     * probleem is array out of bounds, het is vast obvious maar ik zie t ff niet - Chris
     * @param first
     * @param jsonList 
     */
    private void filterShortDurations(ArrayList<Integer> first, ArrayList<JsonObject> jsonList) {
// Filter out any person with less than five seconds of activity.
        for(int k = 0; k < first.size(); k++){
        int count = 0;
         for (Integer i : first) {
            int j = jsonList.get(i).getTrack_id();
            if (first.get(k).equals(j)) {
                count+= jsonList.get(i).getTimestamp().secondsTotal();
            }
        }
        if(count < 5){
            first.remove(k);
        }
    }
    }
    /**
     * This method converts a giant JsonList of mixed track IDs to sorted
     * ArrayLists containing PersonObjects. A PersonObject is a collection of
     * JsonObjects with the same track id, supported by a variety of methods to
     * modify the data
     *
     * @param jsonList ArrayList<\JsonObject> containing JSON lines
     */
    public void convertJsonToPerson(ArrayList<JsonObject> jsonList) {
        // Creates a new PersonObject for every track_id.
        // Distributes all JSON lines per track_id over all PersonObjects.
        if (!jsonList.isEmpty()) {
            ArrayList <Integer> p = calculateAmountPersons(jsonList);
            for (int i = 0; i < p.size(); i++) {
                list.add(new PersonObject(new ArrayList<JsonObject>()));
            }
            for (JsonObject jsonObject : jsonList) {
                for (int i = 0; i < p.size(); i++) {
                    if (jsonObject.getTrack_id() == p.get(i)) {
                        list.get(i).add(jsonObject);
                    }
                }
            }
            setStartEndTime();
        } else {
            System.out.println("convertJsonToPerson()" + ERROR);
        }
    }

    /**
     * Sets Start and End time to the first and last time of each PersonObject
     */
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

    /**
     * Not yet finished, but the foundation for the filter is present.
     *
     * @param list a list of JSON objects
     */
    public void mergeTracksIfSimilar(ArrayList<JsonObject> list) {
        ArrayList<Integer> idBlackList = new ArrayList<>();

        for (JsonObject jo : list) {
            ArrayList<JsonObject> result = new ArrayList<>();
            JsonObject j = list.get(0);
            Timestamp t = j.getTimestamp();

            for (int i = 0; i < list.size(); i++) {
                // Is not the same person.
                if (jo.getTrack_id() != list.get(i).getTrack_id()) {
                    // Are the objects less than 5 seconds apart?
                    if (t.secondsTotal() - list.get(i).getTimestamp().secondsTotal() <= 5) {
                        // If new object is within predefined radius.
                        if (j.getBbox().getX1() - list.get(i).getBbox().getX1() <= destroyRadius
                                && j.getBbox().getY1() - list.get(i).getBbox().getY1() <= destroyRadius) {
                            // Remove object // assign same id?
                            idBlackList.add(list.get(i).getTrack_id());
                        }
                    }
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
