/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Workouts;

import java.util.ArrayList;

/**
 *
 * @author AliFetanat
 */
public class wsrData {
   public String e;
    public String p;
   public  String m;
    
     public wsrData(String e,String p,String m)
    {
        this.e=e;
        this.p=p;
        this.m=m;

    }
    
    public class wsr{
        String s;
        String w;
        String r;
        void set(String s, String w, String r)
        {
            this.s=s;
            this.w=w;
            this.r=r;
        }
    }
    
    public ArrayList <wsr> x;

   
    
    public void createWSR(String s, String w, String r)
    {
        wsr WSR=new wsr();
        WSR.set(s, w, r);
        x.add(WSR);
    }
        
    public void printWSR()
    {
        System.out.println(x);
    }
  
}
