/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import model.JsonLine;

/**
 *
 * @author Stefan
 */
public class ReadJsonFile {

    public static void main(String[] args) {
        readJsonFile();
    }

    public static ArrayList<JsonLine> readJsonFile() {

        String filename = "recording001_short.json";
        BufferedReader br = null;
        JsonLine jsonLine;
        ArrayList<JsonLine> jsonLines = new ArrayList<JsonLine>();

        try {
            int counter = 0;
            String string;
            String[] stringArray;
            br = new BufferedReader(new FileReader("./Json\\" + filename));

            // extracting values from one line of JSON, puts it in JsonLine obj
            while ((string = br.readLine()) != null && counter < 10000) {
                stringArray = string.split(",");
                for (int i = 0; i < stringArray.length; i++) {
                    stringArray[i] = stringArray[i].replaceAll("[\\[\\]\"}]", "");
                    stringArray[i] = stringArray[i].substring(stringArray[i].indexOf(':') + 1).trim();
                    System.out.println("####################################################################################################println");
                    System.out.println(stringArray[i]);
                }
                jsonLine = new JsonLine();
                jsonLine.setTrack_id(Integer.parseInt(stringArray[0]));
                jsonLine.setTimestamp(stringArray[1]);
                jsonLine.setEvent_type(Integer.parseInt(stringArray[2]));
                jsonLine.setAlive_status(Integer.parseInt(stringArray[3]));
                jsonLine.setPosition(Double.parseDouble(stringArray[4]),
                        Double.parseDouble(stringArray[5]));
                jsonLine.setBbox(Float.parseFloat(stringArray[6]),
                        Float.parseFloat(stringArray[7]),
                        Float.parseFloat(stringArray[8]),
                        Float.parseFloat(stringArray[9]),
                        Float.parseFloat(stringArray[10]),
                        Float.parseFloat(stringArray[11]));
                System.out.println("####################################################################################################println");
                System.out.println(jsonLine.toString());
                jsonLines.add(jsonLine);

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

        System.out.println("####################################################################################################println");
        if (!jsonLines.isEmpty()) {
            for (JsonLine a : jsonLines) {
                System.out.println(a.toString());
            }
        }
        return jsonLines;
    }
}
