/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.controller;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.model.JsonObject;

/**
 *
 * @author Stefan
 */
public class JsonController {

    String filename = "recording001_short.json";
    ArrayList<JsonObject> jsonList;

    public JsonController(String filename) {
        this.filename = filename;
        this.jsonList = new ArrayList<JsonObject>();
    }
    
    // reads JSON file
    public ArrayList<JsonObject> read(String filename) {
        this.jsonList = readJsonFileToArrayList(filename);
        return this.jsonList;
    }
    
    // reads without converting JSON file
    public ArrayList<JsonObject> read() {
        if (this.jsonList.isEmpty()) {
            System.out.println("Reads empty ArrayList from JsonController");
            return null;
        }
        return this.jsonList;
    }

    private static ArrayList<JsonObject> readJsonFileToArrayList(String filename) {
        
        BufferedReader br = null;
        JsonObject jsonObject;
        ArrayList<JsonObject> jsonObjects = new ArrayList<JsonObject>();

        try {
            String string;
            String[] stringArray;
            br = new BufferedReader(new FileReader("Json\\" + filename));

            // extracting values from one line of JSON, puts it in JsonLine obj
            while ((string = br.readLine()) != null) {
                stringArray = string.split(",");
                for (int i = 0; i < stringArray.length; i++) {
                    stringArray[i] = stringArray[i].replaceAll("[\\[\\]\"}]", "");
                    stringArray[i] = stringArray[i].substring(stringArray[i].indexOf(':') + 1).trim();
                }
                jsonObject = new JsonObject();
                jsonObject.setTrack_id(Integer.parseInt(stringArray[0]));
                jsonObject.setTimestamp(stringArray[1]);
                jsonObject.setEvent_type(Integer.parseInt(stringArray[2]));
                jsonObject.setAlive_status(Integer.parseInt(stringArray[3]));
                jsonObject.setPosition(Double.parseDouble(stringArray[4]),
                        Double.parseDouble(stringArray[5]));
                jsonObject.setBbox(Float.parseFloat(stringArray[6]),
                        Float.parseFloat(stringArray[7]),
                        Float.parseFloat(stringArray[8]),
                        Float.parseFloat(stringArray[9]),
                        Float.parseFloat(stringArray[10]),
                        Float.parseFloat(stringArray[11]));
                jsonObjects.add(jsonObject);

            }
        } catch (IOException e) {
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
            }
        }
        return jsonObjects;
    }
}
