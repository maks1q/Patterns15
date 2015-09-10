/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;

public class airport {
    private static String []airportsNames = new String[] { //массив имен аэропортов
        "Шереметьево",
	"Домодедово",
	"Толмачево",
	"Пулково",
        "Сокол",
	"Алтай",
	"Хитроу",
	"Адлер_Сочи",
	"Пушкино",
	"Внуково",
        "Шереметьево-2",
        "Девау"
    };
    public static int getMaxAirportNumber() {
        return airportsNames.length;
    }
    private static int airportNumber=0;
    public static void setNumber (int newNumber){
        airportNumber=newNumber;
    }
    private int coordX, coordY; //координаты аэропорта
    private int airportType; //тип самолета: 1-военный, 0-общий
    private int runwayStatus; //показатель свободности взлетно-посадочной полосы
    private int parkingPlacesNumber; //количество стоянок для самолетов
    private Vector<basicPlane> parkingPlanes; //самолеты, стоящие на данном аэропорту
    private Vector<basicPlane> flyingPlanes; //самолеты, летящие в данный аэропорт
    private String airportsName; //название аэропорта
    public airport (int airportCoordX, int airportCoordY) {
        airportsName=airportsNames[airportNumber];
        coordX=airportCoordX;
        coordY=airportCoordY;
        Random rand = new Random();
        if (airportNumber>=2) { //определение типа аэропорта (как минимум 2 аэропорта должны быть общими)
            airportType=rand.nextInt(2);
        }
        else {
            airportType=0;
        }
        runwayStatus=0;
        parkingPlacesNumber=5+rand.nextInt(31); //определение количества стояночный мест
        int startingPlanesNumber=rand.nextInt(parkingPlacesNumber);//определение количества стоящих на момент создания аэропорта самолетов
        //начальное заполнение аэропорта
        parkingPlanes = new Vector<basicPlane>();
        flyingPlanes = new Vector<basicPlane>();
        for (int i=0; i<startingPlanesNumber; i++) {
            if (airportType==0) {
                switch (rand.nextInt(9)) { //определение типа самолета
                    case 0: case 1: case 2:
                        parkingPlanes.add(new passengerPlane());
                        break;
                    case 3: case 4: case 5:
                        parkingPlanes.add(new goodsPlane());
                        break;
                    case 6: case 7:
                        parkingPlanes.add(new pleasurePlane());
                        break;
                    case 8:
                        parkingPlanes.add(new warPlane());
                        break;
                }
            }
            else {//на военном аэродроме все самолеты могут быть только военными
                parkingPlanes.add(new warPlane());
            }
        }
        airportNumber++;
    }
    
    public int getCoordX () {
        return coordX;
    } 
    public void setCoordX (int newCoordX) {
        coordX=newCoordX;
    }
    public int getCoordY () {
        return coordY;
    }
    public void setCoordY (int newCoordY) {
        coordY=newCoordY;
    }
    
    public int getAirportType () {
        return airportType;
    }
    public void setAirportType (int newAirportType) {
        airportType=newAirportType;
    }
    
    public int getRunwayStatus () {
        return runwayStatus;
    }
    public void setRunwayStatus (int newRunwayStatus) {
        runwayStatus=newRunwayStatus;
    }
    
    public int getParkingPlacesNumber () {
        return parkingPlacesNumber;
    }
    public void setParkingPlacesNumber (int newParkingPlacesNumber) {
        parkingPlacesNumber=newParkingPlacesNumber;
    }
    public Vector<basicPlane> getParkingPlanes () {
        return parkingPlanes;
    }
    public void setParkingPlanes (Vector<basicPlane> newParkingPlanes) {
        parkingPlanes=newParkingPlanes;
    }
    
    public Vector<basicPlane> getFlyingPlanes () {
        return flyingPlanes;
    }
    public void setFlyingPlanes (Vector<basicPlane> newFlyingPlanes) {
        flyingPlanes=newFlyingPlanes;
    }
    
    public String getAirportsName () {
        return airportsName;
    }
    public void setAirportsName (String newAirportsName) {
        airportsName=newAirportsName;
    }
}