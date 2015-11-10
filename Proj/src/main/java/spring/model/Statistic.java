/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.model;

import java.util.Arrays;

/**
 *
 * @author Stefan
 */
public class Statistic {
    
    int id;
    String[] labels;
    int[] data;

    public Statistic(int id, String[] labels, int[] data) {
        this.id = id;
        this.labels = labels;
        this.data = data;
    }
    
    public int getStatId() {
        return id;
    }
    
    public String labelArray() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < labels.length; i++) {
            sb.append("\"").append(labels[i]).append("\"");
            if (i + 1 < labels.length) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    public String dataArray() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < data.length; i++) {
            sb.append("\"").append(data[i]).append("\"");
            if (i + 1 < data.length) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public String toString() {
        return this.id + " data: \n"
                + Arrays.toString(labels)
                + Arrays.toString(data);
    }
}
