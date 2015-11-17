/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.controller;

import java.util.ArrayList;
import spring.model.PersonObject;
import spring.model.Statistic;

/**
 *
 * @author Stefan
 */
public class StatController {

    ArrayList<Statistic> list;
    
    public StatController() {
        this.list = new ArrayList<>();
    }

    public void extractStatistics(ArrayList<PersonObject> list) {
        statTotalTime(list);
    }
    
    private void statTotalTime(ArrayList<PersonObject> personList) {
        int[] data = new int[personList.size()];
        String[] labels = new String[personList.size()];
        for (int i = 0; i < personList.size(); i++) {
            data[i] = personList.get(i).getEnd().secondsTotal() - personList.get(i).getStart().secondsTotal();
            labels[i] = "Person " + personList.get(i).getPersonId();
        }
        Statistic result = new Statistic(list.size(), "Total visit time", labels, data);
        this.list.add(result);
    }

    public ArrayList<Statistic> getList() {
        return list;
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
