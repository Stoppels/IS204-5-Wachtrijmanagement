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
public class Position {

        private double x;
        private double y;
        
        public Position (String position){
        position = position.replace("[","");
        position = position.replace("]","");
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

        
    }