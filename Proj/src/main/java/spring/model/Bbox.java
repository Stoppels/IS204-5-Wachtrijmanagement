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
 *
 * @author IS204-5
 * @version 1.0
 */
public class Bbox implements Serializable {

	private float x1;
	private float y1;
	private float z1;
	private float x2;
	private float y2;
	private float z2;

	public Bbox(String bbox) {
		bbox = bbox.replace("[", "");
		bbox = bbox.replace("]", "");
		x1 = Float.parseFloat(bbox.split(",")[0]);
		y1 = Float.parseFloat(bbox.split(",")[1]);
		z1 = Float.parseFloat(bbox.split(",")[2]);
		x2 = Float.parseFloat(bbox.split(",")[3]);
		y2 = Float.parseFloat(bbox.split(",")[4]);
		z2 = Float.parseFloat(bbox.split(",")[5]);
	}

	public Bbox(float x1, float y1, float z1, float x2, float y2, float z2) {
		this.x1 = x1;
		this.y1 = y1;
		this.z1 = z1;
		this.x2 = x2;
		this.y2 = y2;
		this.z2 = z2;
	}

	public float getX1() {
		return x1;
	}

	public void setX1(float x1) {
		this.x1 = x1;
	}

	public float getY1() {
		return y1;
	}

	public void setY1(float y1) {
		this.y1 = y1;
	}

	public float getZ1() {
		return z1;
	}

	public void setZ1(float z1) {
		this.z1 = z1;
	}

	public float getX2() {
		return x2;
	}

	public void setX2(float x2) {
		this.x2 = x2;
	}

	public float getY2() {
		return y2;
	}

	public void setY2(float y2) {
		this.y2 = y2;
	}

	public float getZ2() {
		return z2;
	}

	public void setZ2(float z2) {
		this.z2 = z2;
	}

	@Override
	public String toString() {
		return "[" + x1 + "," + y1 + "," + z1 + "," + x2 + "," + y2 + "," + z2 + "]";
	}

}
