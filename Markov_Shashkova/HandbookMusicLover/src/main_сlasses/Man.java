/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main_сlasses;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import main_compare.DateCompare;



/**
 *
 * @author константин
 */
public class Man {
    public final static char WOMAN;
    public final static char MAN;
    static
    {
        WOMAN='w';
        MAN='m';
    }
    protected String name;
    protected String surname;
    protected char sex;
    protected Date date;
    //Конструкторы
    public Man() 
    {
        SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
        try {
            this.date=sdf.parse("1.1.1");
        } catch (ParseException ex) {
        }
        this.name="NO";
        this.sex='w';
        this.surname="NO";
    }
    
    public Man(final String name,final String surname,final String date,final char sex)
    {
        boolean k=false;
        SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
        if(date!=null)
        {
            try {
                this.date=sdf.parse(date);
                } catch (ParseException ex) {
                    k=true;
            }
        }
        else
            k=true;
        if(k==true)
        {
            try {
                this.date=sdf.parse("1.1.1");
            } catch (ParseException ex) {
            }
        }
        if(name!=null)
            this.name=name;
        else
            this.name="NO";
        if(sex=='w'||sex=='m') {
                  this.sex=sex;
        }
        else{
                this.sex='w';
        }
        if(surname!=null)
           this.surname=surname;
        else
            this.surname="NO";
    }
    public Man(final Man man)
    {
        if(man!=null)
        {
            SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
            try {
                this.date=sdf.parse(man.getDate());
            }   catch (ParseException ex) {
            }
            this.name=man.name;
            this.sex=man.sex;
            this.surname=man.surname;
        }
        else
        {
            SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
            try {
                this.date=sdf.parse("1.1.1");
                } catch (ParseException ex) {
                }
            this.name="NO";
            this.sex='w';
             this.surname="NO";
        }
    }
    //Сетиры и гетеры
    public void setName(final String name)throws IllegalArgumentException
    {
        if(name!=null)
            this.name=name;
        else
        {
            throw new IllegalArgumentException("can not take the value: null");
        }
    }
    public String getName()
    {
        return this.name;
    }
    
    public void setSurname(final String surname)throws IllegalArgumentException
    {
        if(surname!=null)
            this.surname=surname;
        else
            throw new IllegalArgumentException("can not take the value: null");
    }
    public String getSurname()
    {
        return this.surname;
    }
    
    public void setDate(final String date)throws ParseException,IllegalArgumentException
    {
        if(date!=null)
        {
            SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
            this.date=sdf.parse(date);
        }
        else
            throw new IllegalArgumentException("can not take the value: null");
    }
    public String getDate()
    {
        SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(this.date);
    }
    
    public void setSex(final char sex)throws IllegalArgumentException
    {
       if(sex=='w'||sex=='m') {
                  this.sex=sex;
        }
        else{
                throw new IllegalArgumentException("field <floor> can only take the value: 'm' and 'w'");
        }
    }
       
    public char getSex()
    {
        return this.sex;
    }
    //Остальные методы
    public boolean equalsMan(final Man man)
    {
        boolean a=false;
        SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
        if(man!=null)
        {
            if(this.name.trim().equalsIgnoreCase(man.name.trim())==true&&this.surname.trim().equalsIgnoreCase(man.surname.trim())==true)
            {
                String sThis=sdf.format(this.date);
                String sMan=sdf.format(man.date);
                if(new DateCompare().compare(sThis, sMan)==0&&this.sex==man.sex)
                {
                    a=true;
                }
            }
        }
        return a;
    }
    public void readFile(DataInputStream file) throws IOException,IllegalArgumentException, ParseException
    {
        this.setName(file.readUTF());
        this.setSurname(file.readUTF());
        this.setSex(file.readChar());
        this.setDate(file.readUTF());
    }
    public void writeFile(DataOutputStream file) throws IOException
    {
        file.writeUTF(this.name);
        file.writeUTF(this.surname);
        file.writeChar(this.sex);
        file.writeUTF(this.getDate());
    }

}
