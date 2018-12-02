package edu1.csumb.mike.Library_System;

/**
 * Title: Transaction.java
 * Abstract: This program allows users to administrate a bank. This class controls the
 *  transaction portion of the bank. It creates the transaction object and maintains its variables.
 * Author: Michael Royal
 * Date: 12/9/16
 */

import java.text.NumberFormat;

public class Transaction {
    private String username;
    private int type;
    private double rentalCost;
    private String date;
    private String time;
    private String typePrint;
    private String pickUpDate;
    private String dropOffDate;
    private String title;
    private int id;
    private int active;
    private int pickDayYear;
    private int dropDayYear;
    private int reservation=0;


    NumberFormat formatter = NumberFormat.getCurrencyInstance();

    public Transaction(){}


    //copy constructor
    public Transaction(Transaction transaction){

        this.id=transaction.id;
        this.username = transaction.username;
        this.type = transaction.type;
        this.rentalCost = transaction.rentalCost;
        this.date = transaction.date;
        this.time = transaction.time;
        this.pickUpDate = transaction.pickUpDate;
        this.dropOffDate = transaction.dropOffDate;
        this.title = transaction.title;
        this.typePrint = transaction.typePrint;
        this.active=transaction.active;
        this.dropDayYear=transaction.dropDayYear;
        this.pickDayYear=transaction.pickDayYear;
        this.reservation=transaction.reservation;


    }
    //constructor for book rental
    public Transaction(String username, int type, double rentalCost, String title, String date,
                                String time, String pickUpDate, String dropOffDate, int pickDayYear, int dropDayYear,int reservation){

        this.username = username;
        this.type = type;
        this.rentalCost = rentalCost;
        this.date = date;
        this.time = time;
        this.pickUpDate = pickUpDate;
        this.dropOffDate = dropOffDate;
        this.title = title;
        this.active=1;
        this.pickDayYear = pickDayYear;
        this.dropDayYear=dropDayYear;
        this.reservation=reservation;

        if(type==1){
            typePrint = "Rental" + "(" + formatter.format(rentalCost) + ")";
        }
        else if(type==2){
            typePrint = "Cancel Hold";
        }
        else if(type==3){
            typePrint = "New Account";
        }

    }

    //constructor for all other transaction types
    public Transaction(String username, int type,  String date, String time){
        this.username = username;

        this.type = type;
        this.date = date;
        this.time = time;

        if(type==2){
            typePrint = "Cancel Hold";
        }
        else if(type==3){
            typePrint = "New Account";
        }
    }


    public String toString(){
        return "Transaction: - Username: " + username + " Type:" + typePrint +" "+ date +", "+ time +" Active: " + active;
    }


    //**********Getters****************
    public int getReservation(){return reservation;}
    public int getPickDayYear(){return pickDayYear;}
    public int getDropDayYear(){return dropDayYear;}
    public int getActive(){return active;}
    public int getTypeNumber(){return type;}

    public String getUsername(){
        return username;
    }

    public String getType(){
        return typePrint;
    }

    public double getRentalCost(){
        return rentalCost;
    }

    public String getDate(){
        return date;
    }

    public String getTime(){
        return time;
    }

    public String getTitle(){
        return title;
    }

    public String getPickUpDate(){
        return pickUpDate;
    }

    public String getDropOffDate(){
        return dropOffDate;
    }

    public int getId(){
        return id;
    }

    //*************Setters***************
    public void setReservation(int reservation){this.reservation=reservation;}
    public void setDropDayYear(int dropDayYear){
        this.dropDayYear=dropDayYear;
    }
    public void setPickDayYear(int pickDayYear){
        this.pickDayYear=pickDayYear;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public void setType(String type){
        this.typePrint= type;
    }

    public void setRentalCost(Double rentalCost){
        this.rentalCost=rentalCost;
    }

    public void setDate(String date){
        this.date=date;
    }

    public void setTime(String time){
        this.time = time;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setPickUpDate(String pickUpDate){
        this.pickUpDate=pickUpDate;
    }

    public void setDropOffDate(String dropOffDate){
        this.dropOffDate=dropOffDate;
    }

    public void setId(int id){this.id=id;}

    public void setActive(int active){
        this.active=active;
    }

    public void setTypeNumber(int typeNumber){
        this.type=typeNumber;
    }
}