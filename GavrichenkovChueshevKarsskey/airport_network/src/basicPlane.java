/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
        
public class basicPlane {
    private static String[] namesArray = new String[]{ //массив имен самолетов
                         "АЭРОБУС А310",
                         "АЭРОБУС А320", 
                         "Лучший самолет",
			 "Ту-144",
			 "Cessna 850",
			 "An-225",
			 "Boing 747400",
			 "А380",
			 "Charrikein",
			 "Junkers",
			 "FW-190",
                         "Dornier Do X",
                         "Convair B-36J"
                        };
    private static int[] colvoSameNames = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int weight;
    private int speed;
    private int maxTime; //сколько максимум может лететь самолет	
    private int tekTime; //сколько осталось лететь до пункта назначения
    private int stopTime;
    private String name;
    private String startAirport;
    private String finishAirport;
    public basicPlane () {
        Random rand = new Random();
        weight=rand.nextInt(90000)+10000;
        speed=rand.nextInt(1000)+100;
        maxTime=rand.nextInt(50)+10;
        stopTime=rand.nextInt(4)+1;
        int nameNumber=rand.nextInt(namesArray.length-1);
        colvoSameNames[nameNumber]++;
        name=namesArray[nameNumber] + " #" + Integer.toString(colvoSameNames[nameNumber]); 
        finishAirport=null;
        startAirport=null;
    }
    public basicPlane(int newWeight, int newSpeed, int newMaxTime, int newTekTime, int newStopTime, String newName) {
        weight=newWeight;
        speed=newSpeed;
        maxTime=newMaxTime;
        tekTime=newTekTime;
        stopTime=newStopTime;
        name=newName;
        finishAirport=null;
        startAirport=null;
    }
    
    public void setWeight (int newWeight) {
        weight=newWeight;
    }
    public int getWeight () {
        return weight;
    }
    
    public void setSpeed (int newSpeed) {
        speed=newSpeed;
    }
    public int getSpeed () {
        return speed;
    }
    
    public void setMaxTime (int newMaxTime) {
        maxTime=newMaxTime;
    }
    public int getMaxTime () {
        return maxTime;
    }
    
    public void setTekTime (int newTekTime) {
        tekTime=newTekTime;
    }
    public int getTekTime () {
        return tekTime;
    }
    
    public void setStopTime (int newStopTime) {
        stopTime=newStopTime;
    }
    public int getStopTime () {
        return stopTime;
    }
    
    public void setName (String newName) {
        name=newName;
    }
    public String getName () {
        return name;
    }
    
    public void setFinishAirport (String newFinishAirport) {
        finishAirport=newFinishAirport;
    }
    public String getFinishAirport () {
        return finishAirport;
    }
    
    public void setStartAirport (String newStartAirport) {
        startAirport=newStartAirport;
    }
    public String getStartAirport () {
        return startAirport;
    }
}