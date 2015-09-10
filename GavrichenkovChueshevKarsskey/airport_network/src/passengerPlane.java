/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
public class passengerPlane extends basicPlane{
    private int colvoMen;
    private int maxColvoMen;
    public passengerPlane () {
        super();
        colvoMen=0;
        maxColvoMen=this.getWeight()/400+20;
    }
    public passengerPlane(int newWeight, int newSpeed, int newMaxTime, int newTekTime, int newStopTime, String newName, int newMaxColvoMen) {
        super(newWeight, newSpeed, newMaxTime, newTekTime, newStopTime, newName);
        colvoMen=0;
        maxColvoMen=newMaxColvoMen;
    }
    
    public void setColvoMen (int newColvoMen) {
        colvoMen=newColvoMen;
    }
    public int getColvoMen () {
        return colvoMen;
    }
    
    public void setMaxColvoMen (int newMaxColvoMen) {
        maxColvoMen=newMaxColvoMen;
    }
    public int getMaxColvoMen () {
        return maxColvoMen;
    }
    
    public static void main(String args[]) {
    }
}
