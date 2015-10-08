/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Stefan
 */
public class JsonLine {

    int track_id;
    Timestamp timestamp;
    int event_type;
    int alive_status;
    Position position;
    Bbox bbox;

    /**
     *
     * @param track_id
     * @param timestamp
     * @param event_type
     * @param alive_status
     * @param position
     * @param bbox
     */
    public JsonLine(int track_id, Timestamp timestamp, int event_type, int alive_status, Position position, Bbox bbox) {
        this.track_id = track_id;
        this.timestamp = timestamp;
        this.event_type = event_type;
        this.alive_status = alive_status;
        this.position = position;
        this.bbox = bbox;
    }

    private class Bbox {

        double xy;
        double zx;
        double yz;

        public Bbox(double xy, double zx, double yz) {
            this.xy = xy;
            this.zx = zx;
            this.yz = yz;
        }
    }

    private class Position {

        double x;
        double y;

        public Position(double x, double y) {
            this.x = x;
            this.y = y;
        }
        
    }

    private class Timestamp {

        int year;   // - the year minus 1900
        int month;  // - 0 to 11
        int date;   // - 1 to 31
        int hour;   // - 0 to 23
        int minute; // - 0 to 59
        int second; // - 0 to 59
        int nano;   // - 0 to 999,999,999

        public Timestamp(int year, int month, int date, int hour, int minute, int second, int nano) {
            this.year = year;
            this.month = month;
            this.date = date;
            this.hour = hour;
            this.minute = minute;
            this.second = second;
            this.nano = nano;
        }

        @Override
        public String toString() {
            return this.year + this.month + this.date + "T" + this.hour + this.minute + this.second + this.nano;
        }
    }
}
