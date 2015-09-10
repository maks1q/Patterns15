package com.company;

import java.util.Random;

/**
 * Created by Даниил on 02.07.2014.
 */
public class auto extends delivery {

    private int highway;

    public auto(stat obj){

        setFactor(7);
        setSpeed(3);
        setHighway(0);
        setStat(obj);
    }

    public void setHighway(int h){
        highway = h;
    }

    public int getHighway(){
        Random rnd = new Random(System.currentTimeMillis());

        return rnd.nextInt(2);
    }
}
