package com.company;

import java.util.Random;
/**
 * Created by Даниил on 01.07.2014.
 */
public class parcel {

    private int number;
    private int weight;
    private int price;
    private double time;
    private double timeF;
    private String startCity;
    private String finishCity;
    private String type;

    public parcel(){
        setNumber(0);
        setWeight(0);
        setPrice(0);
        setStartCity("");
        setFinishCity("");
        setType("");
    }

    public void setNumber(int n) {
        number = n;
    }

    public void setWeight(int w) {
        weight = w;
    }

    public void setPrice(int p) {
        price = (int) p*weight/10;
    }

    public void setTime(double t) {
        time = t;
    }

    public void setTimeF(double ts) {
        timeF = ts;
    }

    public void setStartCity(String s) {
        startCity = s;
    }

    public void setFinishCity(String f) {
        finishCity = f;
    }

    public void setType(String d) {
        type = d;
    }

    public double getTime(){
        return time;
    }

    public double getTimeF(){
        return timeF;
    }

    public int getNumber(){
        return number;
    }

    public int getWeight(){
        return weight;
    }

    public int getPrice(){
        return price;
    }

    public String getStartCity(){
        return startCity;
    }

    public String getFinishCity(){
        return finishCity;
    }

    public String getType(){
        return type;
    }

    public void switchType() {

        Random rnd = new Random(System.currentTimeMillis());

        switch (rnd.nextInt(3)){

            case 0: setType("Автомобильный");   break;
            case 1: setType("Железнодорожный"); break;
            case 2: setType("Воздушный");       break;
        }
    }

    public int switchSettingOne(int n) {

        Random rnd = new Random(System.currentTimeMillis());

        int r = rnd.nextInt(100);

        int flag = 0;

        setNumber(n);

        switch (r % 5) {

            case 0: { setStartCity("Барнаул"); flag = 0; }      break;
            case 1: { setStartCity("Владивосток"); flag = 1; }  break;
            case 2: { setStartCity("Калининград"); flag = 2; }  break;
            case 3: { setStartCity("Москва"); flag = 3; }       break;
            case 4: { setStartCity("Новосибирск"); flag = 4; }  break;
        }

        return flag;
    }

    public int switchSettingTwo(int f){ // передача флага из предыдущей

        Random rnd = new Random(System.currentTimeMillis());

        int r = rnd.nextInt(100);

        while (r % 5 == f) r = rnd.nextInt(100);

        switch (r % 5){

            case 0: setFinishCity("Барнаул");       break;
            case 1: setFinishCity("Владивосток");   break;
            case 2: setFinishCity("Калининград");   break;
            case 3: setFinishCity("Москва");        break;
            case 4: setFinishCity("Новосибирск");   break;

        }

        while (r < 100) r = rnd.nextInt(1000);

        setWeight(r);

        switchType();

        return r%5;
    }
}
