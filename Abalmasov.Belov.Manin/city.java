package com.company;

import java.util.Random;

/**
 * Created by Даниил on 01.07.2014.
 */
public class city {

    private int point;
    private String name;
    private String weather;
    private stat statCity = new stat();

    public city() {
        setPoint(0);
        setName("");
        setWeather("");
    }

    public city(int p, String n, String w, stat obj){
        setPoint(p);
        setName(n);
        setWeather(w);
        setStat(obj);
    }

    public void setPoint(int p) {
        point = p;
    }

    public void setName(String n) {
        name = n;
    }

    public void setWeather(String w) {
        weather = w;
    }

    public void setStat(stat obj){
        statCity.setLoss(obj.getLoss());
        statCity.setProfit(obj.getProfit());
        statCity.setTimeS(obj.getTimeS());
    }


    public int getPoint(){
        return point;
    }

    public String getName(){
        return name;
    }

    public String getWeather(){
        return weather;
    }

    public stat getStat(){
        return statCity;
    }


    public void switchSettingWeather(){

        Random rnd = new Random(System.currentTimeMillis());

        int r = rnd.nextInt(1000);

        if (r < 700) r = 0;
        else r = 1;

        switch (r){
            case 0: setWeather("Благоприятная"); break;
            case 1: setWeather("Неблагоприятная"); break;
        }
    }
}
