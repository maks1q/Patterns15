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
public class MusicalRecording {
    protected ArrayList<MusicIndustryWorker> listSingers;
    protected SongInformation song;
    protected Date dateOfPerformance;
    //Конструкторы
    public MusicalRecording()
    {
        this.listSingers=new ArrayList<>();
        SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
        try {
            this.dateOfPerformance=sdf.parse("1.1.1");
        } catch (ParseException ex) {
        }
        this.song=new SongInformation();
    }
    public MusicalRecording(final String date,final SongInformation song)
    {
        this.song=new SongInformation(song);
        SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
        boolean k=false;
        if(date!=null)
        {
            try {
                this.dateOfPerformance=sdf.parse(date);
                } catch (ParseException ex) {
                    k=true;
            }
        }
        else
            k=true;
        if(k==true)
        {
            try {
                this.dateOfPerformance=sdf.parse("1.1.1");
            } catch (ParseException ex) {
            }
        }
        this.listSingers=new ArrayList<>();
    }
    public MusicalRecording(final MusicalRecording music)
    {
        if(music!=null)
        {
            this.song=new SongInformation(music.song);
            this.listSingers=new ArrayList<>();
            for(MusicIndustryWorker v: music.listSingers)
            {
                this.listSingers.add(new MusicIndustryWorker(v));
            }
             SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
            try {
                this.dateOfPerformance=sdf.parse(music.getDateOfPerformance());
            }   catch (ParseException ex) {
            }
        }
        else
        {
            this.listSingers=new ArrayList<>();
            SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
            try {
                this.dateOfPerformance=sdf.parse("1.1.1");
            } catch (ParseException ex) {
            }
            this.song=new SongInformation();
        }
    }
    //сетеры и гетеры
    public void setDateOfPerformance(final String date)throws ParseException,IllegalArgumentException
    {
        if(date!=null)
        {
            SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
            this.dateOfPerformance=sdf.parse(date);
        }
        else
            throw new IllegalArgumentException("can not take the value: null");
    }
    public String getDateOfPerformance()
    {
        SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(this.dateOfPerformance);
    }
    public void setSong(final SongInformation song)throws IllegalArgumentException
    {
        boolean a=false;
        Voice k=song.getRequirementVoice();
        if(k.equalsVoice(new Voice())==false)
        {
            for(MusicIndustryWorker v:this.listSingers)
            {
                a=this.checkRequirementsVoice(v, k);
                if(a==true)
                    break;
            }
        }
        if(a==true)
        {
            throw new IllegalArgumentException("for a singer's voice does not match the list of performers");
        }
        else
            this.song=new SongInformation(song);
    }
    protected boolean checkRequirementsVoice(MusicIndustryWorker v,Voice k)
    {
        boolean a=false;
        if(v.voice.equalsIgnoreCase(Voice.BARITONE)==true&&k.getBaritone()==false)
            {
                    a=true;
            }
            else
            {
                if(v.voice.equalsIgnoreCase(Voice.BASS)==true&&k.getBass()==false)
                {
                        a=true;
                }
                else
                {
                    if(v.voice.equalsIgnoreCase(Voice.CONTRALTO)==true&&k.getContralto()==false)
                    {
                        a=true;
                    }
                    else
                    {
                        if(v.voice.equalsIgnoreCase(Voice.SOPRANO)==true&&k.getSoprano()==false)
                        {
                            a=true;
                        }
                        else
                        {
                            if(v.voice.equalsIgnoreCase(Voice.TENOR)==true&&k.getTenor()==false)
                            {
                                a=true;
                            }
                        }
                    }
                }
            } 
        return a;
    }
    public SongInformation getSong()
    {
        return new SongInformation(this.song); 
    }
    
    public MusicIndustryWorker getSinger(int index)throws IndexOutOfBoundsException
    {
        return new MusicIndustryWorker(this.listSingers.get(index));
    }
    //другие методы
     public int sizeListSingers()
    {
        return this.listSingers.size();
    }
    public void addListSinger(MusicIndustryWorker music)throws IllegalArgumentException
    {
        boolean t=false;
        if(music.getSinger()==true)
        {
            Voice k=this.song.getRequirementVoice();
            if(k.equalsVoice(new Voice())==false)
            {
                t=this.checkRequirementsVoice(music, k);
            }
            if(t==false)
            {
                for(MusicIndustryWorker v : this.listSingers)
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
            }
            else
                throw new IllegalArgumentException("person does not meet the requirements of the voices");    
            if(t==false)
                this.listSingers.add(new MusicIndustryWorker(music));  
        }
        else
        {
            throw new IllegalArgumentException("man is not a singer");
        }
    }
    public void removeListSingers(int index)throws IndexOutOfBoundsException
    {
        this.listSingers.remove(index);
    }
    public int infexOfListSingers(MusicIndustryWorker music)
    {
        int a=-1;
       for(int i=0;i<this.listSingers.size();i++)
       {
           if(this.listSingers.get(i).equalsMusicIndustryWorker(music)==true)
           {
               a=i;
               break;
           }
       }
           return a;
    }
    
     public boolean equalsMusicalRecording(final MusicalRecording music)
    {
        boolean a=false;
        SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
        if(music!=null)
        {
            String sThis=sdf.format(this.dateOfPerformance);
            String sMan=sdf.format(music.dateOfPerformance);
            int k=0;
            if(new DateCompare().compare(sThis, sMan)==0&&this.listSingers.size()==music.listSingers.size())
                {
                    for(MusicIndustryWorker v:this.listSingers)
                    {
                        for(MusicIndustryWorker t:music.listSingers)
                        {
                            if(v.equalsMusicIndustryWorker(t)==true)
                            {
                                k++;
                                break;
                            }
                        }
                    }
                    if(k==this.listSingers.size()&&this.song.equalsSongInformation(music.song)==true)
                    {
                       a=true;
                    }
                }
        }
        return a;
    }
    public void readFile(DataInputStream file) throws IOException,IllegalArgumentException, ParseException
    {
        SongInformation v=new SongInformation();
        v.readFile(file);
        this.setSong(v);
        this.setDateOfPerformance(file.readUTF());
        int a=file.readInt();
        MusicIndustryWorker t;
        for(int i=0;i<a;i++)
        {
            t=new MusicIndustryWorker();
            t.readFile(file);
            this.addListSinger(t);
        }
    }
    public void writeFile(DataOutputStream file) throws IOException
    {
        this.song.writeFile(file);
        file.writeUTF(this.getDateOfPerformance());
        file.writeInt(this.listSingers.size());
        for(MusicIndustryWorker v: this.listSingers)
        {
            v.writeFile(file);
        }
    }
    
}
