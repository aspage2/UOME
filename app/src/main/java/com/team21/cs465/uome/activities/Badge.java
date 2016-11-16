package com.team21.cs465.uome.activities;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Badge extends Object{
    public String name;
    public boolean achieved;
    public Date earnDate;


    public Badge(String n, boolean a,Date d ){

            this.name = n;
            this.achieved = a;


        if (a){
            this.earnDate = d;
        }
        else {
            this.earnDate = null;
        }
    }


}
