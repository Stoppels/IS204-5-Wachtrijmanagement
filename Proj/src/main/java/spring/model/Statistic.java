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

import java.util.Arrays;

/**
 *
 * @author IS204-5
 * @version 1.0
 */
public class Statistic {

	int id;
	String name;
	String[] labels;
	int[] data;

	public Statistic(int id, String name, String[] labels, int[] data) {
		this.id = id;
		this.name = name;
		this.labels = labels;
		this.data = data;
	}

	public int getStatId() {
		return id;
	}

	public int[] getStatData() {
		return data;
	}

	public String getStatName() {
		return "[\"" + name + "\"]";
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
		return this.id + " " + this.name + " data: \n"
				+ Arrays.toString(labels)
				+ Arrays.toString(data);
	}
}
