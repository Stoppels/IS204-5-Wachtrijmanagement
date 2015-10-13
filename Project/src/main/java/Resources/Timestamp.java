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
public class Timestamp {

        private int year;   // - the year minus 1900
        private int month;  // - 0 to 11
        private int date;   // - 1 to 31
        private int hour;   // - 0 to 23
        private int minute; // - 0 to 59
        private int second; // - 0 to 59
        private int nano;   // - 0 to 999,999,999


        public Timestamp(String string) {
            if (string.length() == 22) {
                this.year = Integer.parseInt(string.substring(0, 4));
                this.month = Integer.parseInt(string.substring(4, 6));
                this.date = Integer.parseInt(string.substring(6, 8));
                this.hour = Integer.parseInt(string.substring(9, 11));
                this.minute = Integer.parseInt(string.substring(11, 13));
                this.second = Integer.parseInt(string.substring(13, 15));
                this.nano = Integer.parseInt(string.substring(16));
            }
        }

        public Timestamp(int year, int month, int date, int hour, int minute, int second, int nano) {
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
            return this.year + this.month + this.date + "T" + this.hour + this.minute + this.second + this.nano;
        }
    }
