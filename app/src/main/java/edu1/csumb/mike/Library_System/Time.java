package edu1.csumb.mike.Library_System;

/**
 * Title: Time.java
 * Abstract: This is the class for the Time class.
 * Author: Michael Royal
 * Date: 12/9/16
 */
public class Time {
    int hour;
    String ampm;

    Time(String hour, String ampm){

        this.ampm = ampm;

        int dropOffHour = 0;

        if(ampm.equals("PM")){
            dropOffHour+=12;
        }

        //get the hour from the spinner
        if(hour.charAt(0)!='1'){
            System.out.println("The hours is: " + hour.charAt(0));
            dropOffHour+=Character.getNumericValue(hour.charAt(0));
        }
        else if(hour.charAt(0)=='1'&&hour.charAt(1)==':'){
            System.out.println("The hours is: " + hour.charAt(0));
            dropOffHour+=1;
        }
        else if(hour.charAt(0)=='1'&&hour.charAt(1)!=':'){
            System.out.println("The hours is: " + hour.charAt(0) + hour.charAt(1));
            int number = Character.getNumericValue(hour.charAt(1));
            dropOffHour += 10 + number;
        }

        if(dropOffHour==12){
            dropOffHour=0;
        }
        else if(dropOffHour==24){
            dropOffHour=12;
        }

        System.out.println("theHour: " + dropOffHour);

        this.hour = dropOffHour;
    }

    public int getHour(){
        return hour;
    }
}
