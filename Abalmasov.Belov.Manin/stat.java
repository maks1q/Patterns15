package com.company;

/**
 * Created by Даниил on 01.07.2014.
 */
public class stat {

    private int count;
    private double profit;
    private double loss;
    private double loss_w;
    private double time;
    private double profit_h;
    private double profit_r;

    public stat() {
        setProfit(0);
        setLoss(0);
        setTimeS(0);
        setProfitH(0);
        setProfitR(0);
    }

    public void setProfit(double p) {
        profit += p;
    }

    public void setProfitH(double ph) {
        profit_h += ph;
    }

    public void setProfitR(double pr) {
        profit_r += pr;
    }

    public void setLoss(double l) {
        loss += l;
    }

    public void setLossW(double lw) {
        loss_w += lw;
    }

    public void setTimeS(double t) {
        time += t;
    }

    public void setCounter(int nn) {
        count = nn;
    }

    public double getProfit(){
        return profit;
    }

    public double getProfitH(){
        return profit_h;
    }

    public double getProfitR(){
        return profit_r;
    }

    public double getLoss(){
        return loss;
    }

    public double getLossW(){
        return loss_w;
    }

    public int getTimeS(){

        if (count != 0) return (int)time/count+1;
        else return 0;
    }

    public int getCounter(){
        return count;
    }

}
