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
/**
 *
 * @author константин
 */
public class MusicIndustryWorker  extends Man{
    
    protected String voice;
    protected boolean singer;
    protected boolean poet;
    protected boolean composer;
    //Конструкторы
    public MusicIndustryWorker()
    {
        super();
        this.composer=false;
        this.poet=false;
        this.singer=false;
        this.voice="soprano";
    }
    public MusicIndustryWorker(final String voice,final boolean singer,final boolean poet,final boolean composer,final String name,final String surname,final String date,final char sex)
    {
        super(name,surname,date,sex);
        this.composer=composer;
        this.poet=poet;
        this.singer=singer;
        if(voice!=null)
        {
            if(this.sex=='m')
            {
                if(voice.equalsIgnoreCase("bass")==true||voice.equalsIgnoreCase("baritone")==true||voice.equalsIgnoreCase("tenor")==true)
                    this.voice=voice.toLowerCase();
                else
                    this.voice="bass";
            }
            else
            {
                if(voice.equalsIgnoreCase("soprano")==true||voice.equalsIgnoreCase("contralto")==true)
                    this.voice=voice.toLowerCase();
                else
                    this.voice="soprano";
            }
        }
        else
        {
            if(this.sex=='w')
                this.voice="soprano";
            else
                this.voice="bass";
        }
    }
    public MusicIndustryWorker(final String voice,final boolean singer,final boolean poet,final boolean composer,final Man man)
    {
        super(man);
        this.composer=composer;
        this.poet=poet;
        this.singer=singer;
        if(voice!=null)
        {
            if(this.sex=='m')
            {
                if(voice.equalsIgnoreCase("bass")==true||voice.equalsIgnoreCase("baritone")==true||voice.equalsIgnoreCase("tenor")==true)
                    this.voice=voice.toLowerCase();
                else
                    this.voice="bass";
            }
            else
            {
                if(voice.equalsIgnoreCase("soprano")==true||voice.equalsIgnoreCase("contralto")==true)
                    this.voice=voice.toLowerCase();
                else
                    this.voice="soprano";
            }
        }
        else
        {
            if(this.sex=='w')
                this.voice="soprano";
            else
                this.voice="bass";
        }
    }
    public MusicIndustryWorker(final MusicIndustryWorker music) 
    {
        super(music);
        if(music!=null)
        {
        this.composer=music.composer;
        this.poet=music.poet;
        this.singer=music.singer;
        this.voice=music.voice;
        }
        else
        {
            this.composer=false;
            this.poet=false;
            this.singer=false;
            this.voice="soprano";
        }
    }
    
    //сетеры и гетеры
    public void setVoice(final String voice)throws IllegalArgumentException
    {
        if(voice!=null)
        {
            if(this.sex=='m')
            {
                if(voice.equalsIgnoreCase("bass")==true||voice.equalsIgnoreCase("baritone")==true||voice.equalsIgnoreCase("tenor")==true)
                    this.voice=voice.toLowerCase();
                else
                    throw new IllegalArgumentException("field <Voice> for man can take on values: 'bass','baritone','tenor'");
            }
            else
            {
                if(voice.equalsIgnoreCase("soprano")==true||voice.equalsIgnoreCase("contralto")==true)
                    this.voice=voice.toLowerCase();
                else
                    throw new IllegalArgumentException("field <Voice> for woman can take on values: 'contralto','soprano'");
            }
        }
        else
            throw new IllegalArgumentException("can not take the value: null");
    }
    public String getVoice()
    {
        return this.voice;
    }
    @Override
    public void setSex(final char sex)throws IllegalArgumentException
    {
        if(sex=='w'||sex=='m') {
                  this.sex=sex;
            if(sex=='w')
                this.voice="soprano";
            else
                this.voice="bass";
        }
        else{
                throw new IllegalArgumentException("field <floor> can only take the value: 'm' and 'w'");
        }
    }
    public void setSinger(final boolean singer)throws IllegalArgumentException
    {
                if(this.singer==true&&singer==false)
                   throw new IllegalArgumentException("if the field is true then it can not change");
                else
                    this.singer=singer;
    }
    public boolean getSinger()
    {
        return this.singer;
    }
    
    public void setPoet(final boolean poet)throws IllegalArgumentException
    {
                if(this.poet==true&&poet==false)
                    throw new IllegalArgumentException("if the field is true then it can not change");
                else
                    this.poet=poet;
    }
    public boolean getPoet()
    {
        return this.poet;
    }
    
    public void setComposer(final boolean composer)throws IllegalArgumentException
    {
                if(this.composer==true&&composer==false)
                    throw new IllegalArgumentException("if the field is true then it can not change");
                else
                    this.composer=composer;
    }
    public boolean getComposer()
    {
        return this.composer;
    }
    //другие методы
    
    public boolean equalsMusicIndustryWorker(final MusicIndustryWorker  music)
    {
        boolean a=false;
        if(music!=null)
        {
            if(super.equalsMan(music)==true&&this.voice.equalsIgnoreCase(music.voice)==true)
            {
                a=true;
            }
        }
        return a;
    }
    @Override
    public void readFile(DataInputStream file) throws IOException,IllegalArgumentException, ParseException
    {
        super.readFile(file);
        this.setComposer(file.readBoolean());
        this.setPoet(file.readBoolean());
        this.setSinger(file.readBoolean());
        this.setVoice(file.readUTF());
    }
    @Override
    public void writeFile(DataOutputStream file) throws IOException
    {
        
        super.writeFile(file);
        file.writeBoolean(this.composer);
        file.writeBoolean(this.poet);
        file.writeBoolean(this.singer);
        file.writeUTF(this.voice);
    }
}
