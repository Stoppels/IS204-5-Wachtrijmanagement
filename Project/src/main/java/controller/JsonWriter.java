/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileWriter;
import java.io.IOException;
import Json.JSONArray;
import Json.JSONObject;
/**
 *
 * @author Stefan
 */


public class JsonWriter {
     public static void main(String[] args) {
         
        String filename = "zelfgemaaktejsonfile.json";

	JSONObject obj = new JSONObject();
	obj.put("name", "stfan");
	obj.put("age", new Integer(24));

	JSONArray list = new JSONArray();
	list.add("msg 1");
	list.add("msg 2");
	list.add("msg 3");

	obj.put("messages", list);

	try {

		FileWriter file = new FileWriter("./Json\\" + filename);
		file.write(obj.toJSONString());
		file.flush();
		file.close();

	} catch (IOException e) {
		e.printStackTrace();
	}

	System.out.print(obj);

     }

}
