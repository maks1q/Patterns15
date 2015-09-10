package com.company;

/**
 * Created by Даниил on 02.07.2014.
 */
public class delivery{

    private int factorCrash;
    private int speed;
    private stat statDelivery = new stat();

    public delivery() {
        setFactor(1);
        setSpeed(1);
    }

    public void setFactor(int f) {
        factorCrash = f;
    }

    public int getFactor(){
        return factorCrash;
    }

    public void setSpeed(int s) {
        speed = s;
    }

    public int getSpeed(){
        return speed;
    }

    public void setStat(stat obj){

        statDelivery.setLoss(obj.getLoss());
        statDelivery.setProfit(obj.getProfit());
        statDelivery.setTimeS(obj.getTimeS());
    }

    public stat getStat(){
        return statDelivery;
    }
}
