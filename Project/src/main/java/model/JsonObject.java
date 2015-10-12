/*
 * A JsonLine object represents on line of JSON from an EAGLE JSON file
 */
package model;

/**
 *
 * @author Stefan
 */
public class JsonObject {

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

    public void setPosition(Position position) {
        this.position = position;
    }
    
    public void setPosition(double d1, double d2) {
        this.position = new Position(d1, d2);
    }
    
    public void setBbox(Bbox bbox) {
        this.bbox = bbox;
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

    // Nested Classes Bbox, Position and Timestamp
    private class Bbox {

        private float x1;
        private float y1;
        private float z1;
        private float x2;
        private float y2;
        private float z2;

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

        
        
    }

    private class Position {

        private double x;
        private double y;

        public Position(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }

        
    }

    private class Timestamp {

        private int year;   // - the year minus 1900
        private int month;  // - 0 to 11
        private int date;   // - 1 to 31
        private int hour;   // - 0 to 23
        private int minute; // - 0 to 59
        private int second; // - 0 to 59
        private int nano;   // - 0 to 999,999,999

        public Timestamp() {
        }

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
}
