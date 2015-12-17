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
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author IS204-5
 * @version 1.0
 */
public class Timestamp implements Comparable<Timestamp>,Serializable {

	private int year;   // - the year minus 1900
	private int month;  // - 0 to 11
	private int date;   // - 1 to 31
	private int hour;   // - 0 to 23
	private int minute; // - 0 to 59
	private int second; // - 0 to 59
	private int nano;   // - 0 to 999,999,999
	private final int timeCorrection; // - 0 to 2 for Netherlands

	public Timestamp(String string) {
		this.timeCorrection = getTimeCorrection();
		if (string.length() == 22) {
			this.year = Integer.parseInt(string.substring(0, 4));
			this.month = Integer.parseInt(string.substring(4, 6));
			this.date = Integer.parseInt(string.substring(6, 8));
			this.hour = Integer.parseInt(string.substring(9, 11)) + timeCorrection;
			this.minute = Integer.parseInt(string.substring(11, 13));
			this.second = Integer.parseInt(string.substring(13, 15));
			this.nano = Integer.parseInt(string.substring(16));
		}
	}

	public Timestamp(int year, int month, int date, int hour, int minute, int second, int nano) {
		this.timeCorrection = getTimeCorrection();
		this.year = year;
		this.month = month;
		this.date = date;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		this.nano = nano;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public int getNano() {
		return nano;
	}

	public void setNano(int nano) {
		this.nano = nano;
	}

	/**
	 * Determine time zone (UTC+1 or UTC+2 for Netherlands) based on DST.
	 *
	 * @return
	 */
	public int getTimeCorrection() {
		TimeZone tz = TimeZone.getTimeZone("UTC");
		boolean inDst = tz.inDaylightTime(new Date());

		return inDst ? 2 : 1;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(String.format("%04d", this.year));
		s.append(String.format("%02d", this.month));
		s.append(String.format("%02d", this.date));
		s.append("T");
		s.append(String.format("%02d", this.hour));
		s.append(String.format("%02d", this.minute));
		s.append(String.format("%02d", this.second));
		s.append(String.format("%06d", this.nano));
		return s.toString();
	}

	public String hourMinuteSecond() {
		StringBuilder s = new StringBuilder();
		s.append(String.format("%02d", this.hour));
		s.append(":");
		s.append(String.format("%02d", this.minute));
		s.append(":");
		s.append(String.format("%02d", this.second));
		return s.toString();
	}

	public int secondsTotal() {
		return this.hour * 3600 + this.minute * 60 + this.second;
	}

	// will not convert nano
	public double toDouble() {
		String s = this.toString();
		s = s.substring(0, 8) + s.substring(9, 15);
		return Double.parseDouble(s);
	}

	@Override
	public int compareTo(Timestamp o) {
		int result;
		if (this.toDouble() > o.toDouble()) {
			return 1;
		}
		if (this.toDouble() < o.toDouble()) {
			return -1;
		}
		return 0;
	}
}
