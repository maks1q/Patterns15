/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main_compare;


import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 *
 * @author константин
 */
public class DateCompare implements Comparator{
    
    @Override
    public int compare( Object obj1, Object obj2)throws NoSuchElementException,NumberFormatException
    {
        int a=0;
        String s1=(String)obj1;
        String s2=(String)obj2;
        if(s1.equalsIgnoreCase(" ")==false||s2.equalsIgnoreCase(" ")==false)
        {
            if(s1.equalsIgnoreCase(" ")==false&&s2.equalsIgnoreCase(" ")==true)
                a=-1;
            else
            {
                if(s1.equalsIgnoreCase(" ")==true&&s2.equalsIgnoreCase(" ")==false)
                {
                    a=1;
                }
                else
                {
                    StringTokenizer str1=new StringTokenizer((String)obj1,".");
                    int dd1=Integer.parseInt(str1.nextToken());
                    int mm1=Integer.parseInt(str1.nextToken());
                    int yy1=Integer.parseInt(str1.nextToken());
        
        
                    StringTokenizer str2=new StringTokenizer((String)obj2,".");
                    int dd2=Integer.parseInt(str2.nextToken());
                    int mm2=Integer.parseInt(str2.nextToken());
                    int yy2=Integer.parseInt(str2.nextToken());
                    if(yy2>yy1)
                        a=-1;
                    else
                    {
                        if(yy2<yy1)
                             a=1;
                        else
                        {
                            if(mm2>mm1)
                                a=-1;
                            else
                            {
                                if(mm2<mm1)
                                    a=1;
                                else
                                {
                                   if(dd2>dd1)
                                    a=-1;
                                    else
                                    {
                                        if(dd2<dd1)
                                            a=1;
                                    }
                                }
                            }
                         }
                    }
                }
            }
        }
        
       
        return a;
    }
}
