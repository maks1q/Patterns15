/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main_сlasses;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author константин
 */
public class Voice {
   public final static String BASS;
    public final static String BARITONE;
    public final static String CONTRALTO;
    public final static String SOPRANO;
    public final static String TENOR;
    static
    {
        BASS="bass";
        BARITONE="baritone";
        CONTRALTO="contralto";
        SOPRANO="soprano";
        TENOR="tenor";
    }
protected boolean bass;
protected boolean baritone;
protected boolean tenor;
protected boolean soprano;
protected boolean contralto;
//Конструкторы
public Voice()
{
    this.baritone=false;
    this.bass=false;
    this.contralto=false;
    this.soprano=false;
    this.tenor=false;
}
public Voice(final boolean bass,final boolean baritone,final boolean tenor,final boolean soprano,final boolean contralto)
{
    this.baritone=baritone;
    this.bass=bass;
    this.contralto=contralto;
    this.soprano=soprano;
    this.tenor=tenor;
   
}
public Voice(final Voice voice)
{
    if(voice!=null)
    {
        this.baritone=voice.baritone;
        this.bass=voice.bass;
        this.contralto=voice.contralto;
        this.soprano=voice.soprano;
        this.tenor=voice.tenor;
    }
    else
    {
        this.baritone=false;
        this.bass=false;
        this.contralto=false;
        this.soprano=false;
        this.tenor=false;
    }
 
}
//гетеры и сетеры
public void setBass(final boolean bass)
{
        this.bass=bass;
}
public boolean getBass()
{
        return this.bass;
}

public void setBaritone(final boolean baritone)
{
        this.baritone=baritone;
}
public boolean getBaritone()
{
        return this.baritone;
}

public void setTenor(final boolean tenor)
{
        this.tenor=tenor;
}
public boolean getTenor()
{
        return this.tenor;
}

public void setSoprano(final boolean soprano)
{
        this.soprano=soprano;
}
public boolean getSoprano()
{
        return this.soprano;
}

public void setContralto(final boolean contralto)
{
        this.contralto=contralto;
}
public boolean getContralto()
{
        return this.contralto;
}
//Остальные методы
public boolean equalsVoice(final Voice voice)
{
       boolean a=false;
       if(voice!=null)
       {
       if(this.baritone==voice.baritone&&this.bass==voice.bass)
       {
           if(this.contralto==voice.contralto&&this.soprano==voice.soprano)
           {
               if(this.tenor==voice.tenor) {
                   a=true;
               }
           }
       }
       }
       return a;
}
    public void readFile(DataInputStream file) throws IOException
    {
        this.setBaritone(file.readBoolean());
        this.setBass(file.readBoolean());
        this.setContralto(file.readBoolean());
        this.setSoprano(file.readBoolean());
        this.setTenor(file.readBoolean());
    }
    public void writeFile(DataOutputStream file) throws IOException
    {
            file.writeBoolean(this.baritone);
            file.writeBoolean(this.bass);
            file.writeBoolean(this.contralto);
            file.writeBoolean(this.soprano);
            file.writeBoolean(this.tenor);
    }
}
