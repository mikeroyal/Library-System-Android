package edu1.csumb.mike.Library_System;
/**
 * Title: Date.java
 * Abstract: This is the class for the Date.
 * Author: Michael Royal
 * Date: 12/9/16
 */
public class Date {
    String day;
    String month;
    String year;

    public Date(String month, String day, String year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }
    //Method to return the number day of the year
    public int getDayNumber() {
        int dayNumber = 0;
        //For 2016 year
        if (year.equals("2016")) {
            if (month.equals("February")) {
                dayNumber += 31;
            } else if (month.equals("March")) {
                dayNumber += 60;
            } else if (month.equals("April")) {
                dayNumber += 91;
            } else if (month.equals("May")) {
                dayNumber += 121;
            } else if (month.equals("June")) {
                dayNumber += 152;
            } else if (month.equals("July")) {
                dayNumber += 182;
            } else if (month.equals("August")) {
                dayNumber += 213;
            } else if (month.equals("September")) {
                dayNumber += 244;
            } else if (month.equals("October")) {
                dayNumber += 274;
            } else if (month.equals("November")) {
                dayNumber += 305;
            } else if (month.equals("December")) {
                dayNumber += 335;
            }
        }
        //Non Leap Year
        else {
            if (month.equals("February")) {
                dayNumber += 31;
            } else if (month.equals("March")) {
                dayNumber += 59;
            } else if (month.equals("April")) {
                dayNumber += 90;
            } else if (month.equals("May")) {
                dayNumber += 120;
            } else if (month.equals("June")) {
                dayNumber += 151;
            } else if (month.equals("July")) {
                dayNumber += 181;
            } else if (month.equals("August")) {
                dayNumber += 212;
            } else if (month.equals("September")) {
                dayNumber += 243;
            } else if (month.equals("October")) {
                dayNumber += 273;
            } else if (month.equals("November")) {
                dayNumber += 304;
            } else if (month.equals("December")) {
                dayNumber += 334;
            }
        }
        //add day
        dayNumber += Integer.valueOf(day);

        return dayNumber;
    }
}
