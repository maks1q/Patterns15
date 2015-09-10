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
import java.util.ArrayList;
import java.util.Date;
import main_compare.DateCompare;


/**
 *
 * @author константин
 */
public class SongInformation {
    public final static String POP;
    public final static String ROCK;
    public final static String JAZZ;
    public final static String CLASSIC;
    static
    {
        POP="pop";
        ROCK="rock";
        JAZZ="jazz";
        CLASSIC="classic";
    }
    protected String name;
    protected Date creationDate;
    protected ArrayList<MusicIndustryWorker> listPoets;
    protected ArrayList<MusicIndustryWorker> listComposers;
    protected String genre;
    protected Voice requirementVoice;
    //Конструкторы
    public SongInformation()
    {
        this.name="NO";
        SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
        try {
            this.creationDate=sdf.parse("1.1.1");
        } catch (ParseException ex) {
        }
        this.listComposers=new ArrayList<>();
        this.listPoets=new ArrayList<>();
        this.genre="rock";
        this.requirementVoice=new Voice(false,false,false,false,false);
    }
    public SongInformation(final String name,final String date,final String genre,final Voice requirementVoice)
    {
        if(name!=null)
            this.name=name;
        else
            this.name="NO";
        
        boolean k=false;
        SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
        if(date!=null)
        {
            try {
                this.creationDate=sdf.parse(date);
                } catch (ParseException ex) {
                    k=true;
            }
        }
        else
            k=true;
        if(k==true)
        {
            try {
                this.creationDate=sdf.parse("1.1.1");
            } catch (ParseException ex) {
            }
        }
        if(genre!=null)
        {
            if(genre.equalsIgnoreCase("rock")==true||genre.equalsIgnoreCase("classic")==true||genre.equalsIgnoreCase("jazz")==true||genre.equalsIgnoreCase("pop")==true)
                this.genre=genre.toLowerCase();
            else
                this.genre="rock";
        }
        else {
            this.genre="rock";
        } 
        if(requirementVoice!=null)
        {
            if(requirementVoice.equalsVoice(new Voice())==true&&this.genre.equalsIgnoreCase(SongInformation.CLASSIC)==true)
                this.requirementVoice=new Voice(true,false,false,true,false);
            else
                this.requirementVoice=new Voice(requirementVoice);
        }
        else
        {
            if(this.genre.equalsIgnoreCase(SongInformation.CLASSIC)==true)
                this.requirementVoice=new Voice(true,false,false,true,false);
            else
                this.requirementVoice=new Voice();
        }
        this.listComposers=new ArrayList<>();
        this.listPoets=new ArrayList<>();
    }
    
    public SongInformation(final SongInformation song)
    {
        if(song!=null)
        {
            this.name=song.name;
            this.genre=song.genre;
            SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
            try {
                this.creationDate=sdf.parse(song.getCreationDate());
            }   catch (ParseException ex) {
            }
            this.requirementVoice=new Voice(song.requirementVoice);
            this.listComposers=new ArrayList<>();
            for(MusicIndustryWorker v: song.listComposers)
            {
                this.listComposers.add(new MusicIndustryWorker(v));
            }
            this.listPoets=new ArrayList<>();
            for(MusicIndustryWorker v: song.listPoets)
            {
                this.listPoets.add(new MusicIndustryWorker(v));
            }
        }
        else
        {
            this.name="NO";
            SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
            try {
                this.creationDate=sdf.parse("1.1.1");
                } catch (ParseException ex) {
            }
            this.listComposers=new ArrayList<>();
            this.listPoets=new ArrayList<>();
            this.genre="rock";
            this.requirementVoice=new Voice(false,false,false,false,false);
        }
    }
    //гетеры и сетеры
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
    
    public void setCreationDate(final String date)throws ParseException,IllegalArgumentException
    {
        if(date!=null)
        {
            SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
            this.creationDate=sdf.parse(date);
        }
        else
            throw new IllegalArgumentException("can not take the value: null");
    }
    public String getCreationDate()
    {
        SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(this.creationDate);
    }
     
    public void setGenre(final String genre)throws IllegalArgumentException
    {
        if(genre!=null)
        {
            if(genre.equalsIgnoreCase("rock")==true||genre.equalsIgnoreCase("classic")==true||genre.equalsIgnoreCase("jazz")==true||genre.equalsIgnoreCase("pop")==true)
            {
                if(this.requirementVoice.equalsVoice(new Voice())==true&&genre.equalsIgnoreCase(SongInformation.CLASSIC)==true)
                    throw new IllegalArgumentException("in classical music should be required to the voices of performers");
                else
                    this.genre=genre.toLowerCase();
            }
            else
                throw new IllegalArgumentException("field <genre> can only take the value: 'pop','rock','classic','jazz'");
        }
        else
            throw new IllegalArgumentException("can not take the value: null");
    }
    public String getGenre()
    {
        return this.genre;
    }
    
    public void setRequirementVoice(final Voice voice)throws IllegalArgumentException
    {
        if(voice!=null)
        {
            if(voice.equalsVoice(new Voice())==true&&this.genre.equalsIgnoreCase(SongInformation.CLASSIC)==true)
                throw new IllegalArgumentException("Field <Voice> genre classics can not simultaneously take all false values");
            else
                this.requirementVoice=new Voice(voice);
        }
        else
            throw new IllegalArgumentException("can not take the value: null");
    }
    public Voice getRequirementVoice()
    {
        return new Voice(this.requirementVoice);
    }
    
    public MusicIndustryWorker getPoet(int index)throws IndexOutOfBoundsException
    {
        return new MusicIndustryWorker(this.listPoets.get(index));
    }
    
    public MusicIndustryWorker getComposer(int index)throws IndexOutOfBoundsException
    {
        return new MusicIndustryWorker(this.listComposers.get(index));
    }
    
    
    //другие методы
    public int sizeListPoets()
    {
        return this.listPoets.size();
    }
    public void addListPoets(MusicIndustryWorker music)throws IllegalArgumentException
    {
        boolean t=false;
        if(music.getPoet()==true)
        {
            for(MusicIndustryWorker v : this.listPoets)
            {
                if(v.equalsMusicIndustryWorker(music)==true)
                {
                    t=true; 
                    if(v.getComposer()==false&&music.getComposer()==true)
                        v.setComposer(true);
                    if(v.getPoet()==false&&music.getPoet()==true)
                        v.setPoet(true);
                    if(v.getSinger()==false&&music.getSinger()==true)
                        v.setSinger(true);
                    break;
                }
            }
             if(t==false)
                this.listPoets.add(new MusicIndustryWorker(music));
        }
        else
            throw new IllegalArgumentException("man is not a poet");
    }
    public void removeListPoets(int index)throws IndexOutOfBoundsException
    {
        this.listPoets.remove(index);
    }
    public int infexOfListPoets(MusicIndustryWorker music)
    {
        int a=-1;
       for(int i=0;i<this.listPoets.size();i++)
       {
           if(this.listPoets.get(i).equalsMusicIndustryWorker(music)==true)
           {
               a=i;
               break;
           }
       }
           return a;
    }
    public int infexOfListComposers(MusicIndustryWorker music)
    {
        int a=-1;
       for(int i=0;i<this.listComposers.size();i++)
       {
           if(this.listComposers.get(i).equalsMusicIndustryWorker(music)==true)
           {
               a=i;
               break;
           }
       }
           return a;
    }
    public int sizeListComposers()
    {
        return this.listComposers.size();
    }
    public void addListComposers(MusicIndustryWorker music)throws IllegalArgumentException
    {
        boolean t=false;
        if(music.getComposer()==true)
        {
            for(MusicIndustryWorker v : this.listComposers)
            {
                 if(v.equalsMusicIndustryWorker(music)==true)
                {
                    t=true;
                    if(v.getComposer()==false&&music.getComposer()==true)
                        v.setComposer(true);
                    if(v.getPoet()==false&&music.getPoet()==true)
                        v.setPoet(true);
                    if(v.getSinger()==false&&music.getSinger()==true)
                        v.setSinger(true);
                    break;
                }
            }
            if(t==false)
                this.listComposers.add(new MusicIndustryWorker(music));
        }
        else
            throw new IllegalArgumentException("man is not a composer");

    }
    public void removeListComposers(int index)throws IndexOutOfBoundsException
    {
        this.listComposers.remove(index);
    }
    
    public boolean equalsSongInformation(final SongInformation music)
    {
        boolean a=false;
        SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
        if(music!=null)
        {
            if(this.name.trim().equalsIgnoreCase(music.name.trim())==true&&this.genre.equalsIgnoreCase(music.genre)==true)
            {
                if(this.requirementVoice.equalsVoice(music.requirementVoice)==true)
                {
                    String sThis=sdf.format(this.creationDate);
                    String sMan=sdf.format(music.creationDate);
                    int k=0;
                if(new DateCompare().compare(sThis, sMan)==0&&this.listComposers.size()==music.listComposers.size())
                {
                    for(MusicIndustryWorker v:this.listComposers)
                    {
                        for(MusicIndustryWorker t:music.listComposers)
                        {
                            if(v.equalsMusicIndustryWorker(t)==true)
                            {
                                k++;
                                break;
                            }
                        }
                    }
                    if(k==this.listComposers.size())
                    {
                        if(this.listPoets.size()==music.listPoets.size())
                        {
                            k=0;
                            for(MusicIndustryWorker v:this.listPoets)
                            {
                                for(MusicIndustryWorker t:music.listPoets)
                                {
                                    if(v.equalsMusicIndustryWorker(t)==true)
                                    {
                                        k++;
                                        break;
                                   }
                                }
                            }
                            if(k==this.listPoets.size())
                                a=true;
                        }
                    }
                }
                }
            }
        }
        return a;
    }
    public void readFile(DataInputStream file) throws IOException,IllegalArgumentException, ParseException
    {
        this.setName(file.readUTF());
        this.setCreationDate(file.readUTF());
        Voice d=new Voice();
        d.readFile(file);
        this.setRequirementVoice(d);
        int a=file.readInt();
        MusicIndustryWorker t;
        for(int i=0;i<a;i++)
        {
            t=new MusicIndustryWorker();
            t.readFile(file);
            this.addListComposers(t);
        }
        int b=file.readInt();
        for(int i=0;i<b;i++)
        {
            t=new MusicIndustryWorker();
            t.readFile(file);
            this.addListPoets(t);
        }
        this.setGenre(file.readUTF());
    }
    public void writeFile(DataOutputStream file) throws IOException
    {
        file.writeUTF(this.name);
        file.writeUTF(this.getCreationDate());
        this.requirementVoice.writeFile(file);
        file.writeInt(this.listComposers.size());
        for(MusicIndustryWorker v: this.listComposers)
        {
            v.writeFile(file);
        }
        file.writeInt(this.listPoets.size());
        for(MusicIndustryWorker v: this.listPoets)
        {
            v.writeFile(file);
        }
        file.writeUTF(this.genre);
    }
}
