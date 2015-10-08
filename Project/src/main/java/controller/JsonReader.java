/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import Json.JSONArray;
import Json.JSONObject;
import Json.JSONParser;
import Json.ParseException;

/**
 *
 * @author Stefan
 */


public class JsonReader {
     public static void main(String[] args) {

	JSONParser parser = new JSONParser();
        String filename = "recording001_short.json";

	try {

		Object obj = parser.parse(new FileReader("./Json\\" + filename));

		JSONObject jsonObject = (JSONObject) obj;

		String name = (String) jsonObject.get("name");
		System.out.println(name);

		long age = (Long) jsonObject.get("age");
		System.out.println(age);

		// loop array
		JSONArray msg = (JSONArray) jsonObject.get("messages");
		Iterator<String> iterator = msg.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}

	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} catch (ParseException e) {
		e.printStackTrace();
	}

     }

}
