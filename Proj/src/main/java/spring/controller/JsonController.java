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

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import spring.model.JsonObject;
import spring.model.Timestamp;

/**
 *
 * @author IS204-5
 * @version 1.0
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

	private ArrayList validateList(ArrayList<JsonObject> list) {
		ArrayList<JsonObject> result = new ArrayList<>();
		JsonObject j = list.get(0);
		Timestamp t = j.getTimestamp();
		for (JsonObject jsonObject : list) {
			if (t.compareTo(jsonObject.getTimestamp()) == 0) {
				j.mergeJsonObject(jsonObject);
			} else {
				result.add(j);
				t = jsonObject.getTimestamp();
				j = jsonObject;
			}
		}
		result.add(j);
		jsonList = result;
		return result;
	}

	private ArrayList<JsonObject> readJson(String filename) {

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
		return validateList(jsonObjects);
	}

	private void extractLine(BufferedReader br, ArrayList<JsonObject> jsonObjects) throws NumberFormatException, IOException {
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
