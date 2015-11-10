/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.controller;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import spring.model.JsonObject;

/**
 *
 * @author Stefan
 */
public class JsonController {

    private final String filename;
    private ArrayList<JsonObject> jsonList;

    public JsonController(String filename) {
        this.filename = filename;
        this.jsonList = readJson(filename);
    }

    // reads without converting JSON file
    public ArrayList<JsonObject> getList() {
        if (this.jsonList.isEmpty()) {
            this.jsonList = readJson(this.filename);
        }
        return this.jsonList;
    }

    public static ArrayList<JsonObject> readJson(String filename) {

        BufferedReader br = null;
        JsonObject jsonObject;
        ArrayList<JsonObject> jsonObjects = new ArrayList<>();

        try {
            String string;
            String[] stringArray;
            br = new BufferedReader(new FileReader(filename));
            extractLine(br, jsonObjects);
        } catch (IOException e) {
            System.out.println("JsonController " + e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
        return jsonObjects;
    }

    private static void extractLine(BufferedReader br, ArrayList<JsonObject> jsonObjects) throws NumberFormatException, IOException {
        String string;
        String[] stringArray;
        JsonObject jsonObject;
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
            if (stringArray[4].length() > 15 || stringArray[5].length() > 15) {
                stringArray[4] = "0.0";
                stringArray[5] = "0.0";
            }
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
    }

    @Override
    public String toString() {
        return "JSONCONTROLLER\n"
                + "\n"
                + getList();
    }
}
