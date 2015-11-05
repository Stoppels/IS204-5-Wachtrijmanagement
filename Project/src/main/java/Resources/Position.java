/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Stefan
 */
@Entity
@Table(name = "Position")
public class Position implements Serializable {

    @Column(name = "positionID")
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "x")
    private double x;
    @Column(name = "y")
    private double y;

    public Position() {
    }

    public Position(String position) {
        position = position.replace("[", "");
        position = position.replace("]", "");
        x = Double.parseDouble(position.split(",")[0]);
        y = Double.parseDouble(position.split(",")[1]);
    }

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

    @Override
    public String toString() {
        return "[" + x + "," + y + "]";
    }

}
