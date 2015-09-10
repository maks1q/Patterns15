/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main_compare;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 *
 * @author константин
 */
public class StringIntoIntegerCompare implements Comparator{
 @Override
 public int compare( Object obj1, Object obj2)throws NoSuchElementException,NumberFormatException
    {
        int a=0;
        String s1=(String)obj1;
        String s2=(String)obj2;
        int number1=Integer.parseInt(s1);
        int number2=Integer.parseInt(s2);
        if(number1>number2)
            a=1;
        else
        {
            if(number1<number2)
                a=-1;
        }
        return a;
    }
    
}
