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
package spring.model;

import java.io.Serializable;
/**
 * A JsonLine object represents one line of JSON from an EAGLE JSON file.
 *
 * @author IS204-5
 * @version 1.0
 */

public class JsonObject implements Serializable {
    
	int track_id;
	Timestamp timestamp;
	int event_type;
	int alive_status;
	Position position;
	Bbox bbox;

	/**
	 *
	 */
	public JsonObject() {
		this.position = null;
		this.bbox = null;
	}

	/**
	 *
	 * @param track_id
	 * @param timestamp
	 * @param event_type
	 * @param alive_status
	 * @param position
	 * @param bbox
	 */
	public JsonObject(int track_id, Timestamp timestamp, int event_type, int alive_status, Position position, Bbox bbox) {
		this.track_id = track_id;
		this.timestamp = timestamp;
		this.event_type = event_type;
		this.alive_status = alive_status;
		this.position = position;
		this.bbox = bbox;
	}

	public void setTrack_id(int track_id) {
		this.track_id = track_id;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public void setTimestamp(String string) {
		this.timestamp = new Timestamp(string);
	}

	public void setEvent_type(int event_type) {
		this.event_type = event_type;
	}

	public void setAlive_status(int alive_status) {
		this.alive_status = alive_status;
	}

	public void setPosition(double d1, double d2) {
		this.position = new Position(d1, d2);
	}

	public void setBbox(float x1, float y1, float z1, float x2, float y2, float z2) {
		this.bbox = new Bbox(x1, y1, z1, x2, y2, z2);
	}

	public int getTrack_id() {
		return track_id;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public int getEvent_type() {
		return event_type;
	}

	public int getAlive_status() {
		return alive_status;
	}

	public Position getPosition() {
		return position;
	}

	public Bbox getBbox() {
		return bbox;
	}

	@Override
	public String toString() {
		return "JsonObject: " + this.track_id + " " + this.timestamp + "\n";
	}

	public void mergeJsonObject(JsonObject jsonObject) {
		this.position.setX((this.position.getX() + jsonObject.getPosition().getX()) / 2);
		this.position.setY((this.position.getY() + jsonObject.getPosition().getY()) / 2);
        }
}
