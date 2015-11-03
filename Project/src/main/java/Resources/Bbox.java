/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

import java.io.Serializable;

/**
 *
 * @author Stefan
 */
public class Bbox implements Serializable{

    private float x1;
    private float y1;
    private float z1;
    private float x2;
    private float y2;
    private float z2;

    public Bbox(String bbox){
        bbox = bbox.replace("[","");
        bbox = bbox.replace("]","");
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
        return "["+ x1 +","+ y1 + "," + z1 + "," + x2 + "," + y2 + "," + z2 + "]";
    }

}
