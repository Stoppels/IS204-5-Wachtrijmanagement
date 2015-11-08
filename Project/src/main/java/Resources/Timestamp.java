/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

/**
 *
 * @author Stefan
 */
public class Timestamp extends java.sql.Timestamp implements Comparable<java.util.Date> {

    private int year;   // - the year minus 1900
    private int month;  // - 0 to 11
    private int date;   // - 1 to 31
    private int hour;   // - 0 to 23
    private int minute; // - 0 to 59
    private int second; // - 0 to 59
    private int nano;   // - 0 to 999,999

    public Timestamp(long stamp) {
        super(stamp);
    }

    public Timestamp(int year, int month, int date, int hour, int minute, int second, int nano) {
        super(year, month, date, hour, minute, second, nano);
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

    public double toDouble() {
        String s = this.toString();
        s = s.substring(0, 8) + s.substring(9);
        return Double.parseDouble(s);
    }

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

    public Timestamp subtract(Timestamp time) {
        int yearh = this.year - time.year,
                monthh = this.month - time.month,
                dateh = this.date - time.date,
                hourh = this.hour - time.hour,
                minuteh = this.minute - time.minute,
                secondh = this.second - time.second,
                nanoh = this.nano - time.nano;
        while (nanoh < 0 || secondh < 0 || minuteh < 0 || hourh < 0 || dateh < 0) {
            if (nanoh < 0) {
                nanoh += 999999;
                secondh--;
            }
            if (secondh < 0) {
                secondh += 60;
                minuteh--;
            }
            if (minuteh < 0) {
                minuteh += 60;
                hourh--;
            }
            if (hourh < 0) {
                dateh--;
                hourh += 24;
            }
            if (dateh < 0) {
                monthh--;
                dateh += 31;
            }
        }
        return new Timestamp(yearh, monthh, dateh, hourh, minuteh, secondh, nanoh);
    }
    }
