/*
 * A JsonLine object represents on line of JSON from an EAGLE JSON file
 */
package Model;

import Resources.Bbox;
import Resources.Position;
import Resources.Timestamp;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Type;

/**
 *
 * @author Stefan
 */

@Entity
@Table(name = "JsonLine")

public class JsonObject implements Serializable {
    private int track_id;
    private Timestamp timePrint;
    private int event_type;
    private int alive_status;
    private Position position;
    private Bbox bbox;

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
        this.timePrint = timestamp;
        this.event_type = event_type;
        this.alive_status = alive_status;
        this.position = position;
        this.bbox = bbox;
    }

    public void setTrack_id(int track_id) {
        this.track_id = track_id;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timePrint = timestamp;
    }

    public void setTimestamp(String string) {
        this.timePrint = new Timestamp(string);
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

    @Id
    @Column(name = "track_id")
    public int getTrack_id() {
        return track_id;
    }

    @Id
    @Type(type="Resources.Timestamp")
    @Column(name = "time_stamp")
    public Timestamp getTimestamp() {
        return timePrint;
    }

    @Column(name = "event_type")
    public int getEvent_type() {
        return event_type;
    }

    @Column(name = "alive_status")
    public int getAlive_status() {
        return alive_status;
    }

    @Id
    @Type(type="Resources.Position")
    @Column(name = "position")
    public Position getPosition() {
        return position;
    }

    @Column(name = "Bbox")
    @Type(type="Resources.Bbox")
    public Bbox getBbox() {
        return bbox;
    }

    @Override
    public String toString() {
        return "JsonObject: " + this.track_id + " " + this.timePrint + "\n";
    }
}
