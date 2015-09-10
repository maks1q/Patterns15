/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
public class goodsPlane extends basicPlane{
    private int colvoGoods;
    private int maxColvoGoods;
    public goodsPlane () {
        super();
        maxColvoGoods=this.getWeight()/5*3;
        colvoGoods=0;
    }
    public goodsPlane(int newWeight, int newSpeed, int newMaxTime, int newTekTime, int newStopTime, String newName, int newMaxColvoGoods) {
        super(newWeight, newSpeed, newMaxTime, newTekTime, newStopTime, newName);
        maxColvoGoods=newMaxColvoGoods;
        colvoGoods=0;
    }
    
    public void setColvoGoods (int newColvoGoods) {
        colvoGoods=newColvoGoods;
    }
    public int getColvoGoods () {
        return colvoGoods;
    }
    
    public void setMaxColvoGoods (int newMaxColvoGoods) {
        maxColvoGoods=newMaxColvoGoods;
    }
    public int getMaxColvoGoods () {
        return maxColvoGoods;
    }
}
