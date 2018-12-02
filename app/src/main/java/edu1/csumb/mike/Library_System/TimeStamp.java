package edu1.csumb.mike.Library_System;

import java.util.Calendar;

/**
 * Title: TimeStamp.java
 * Abstract: This is the class for the ability to generate a timestamp class.
 * Author: Michael Royal
 * Date: 12/9/16
 */
public class TimeStamp {
    private String date;
    private String time;

    public TimeStamp(){

        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute =  cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);

        int month = cal.get(Calendar.MONTH)+1;
        int year = cal.get(Calendar.YEAR);
        int day = cal.get(Calendar.DATE);

        this.date = String.format("%02d", month) + "/" + String.format("%02d", day) + "/" + year;

        this.time = String.format("%02d", hour) + ":" + String.format("%02d", minute) + ":" + String.format("%02d", second);
    }

    public String getTime(){
        return time;
    }

    public String getDate(){
        return date;
    }

}
