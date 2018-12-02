package edu1.csumb.mike.Library_System;

/**
 * Title: Book.java
 * Abstract: This is the class for the book class.
 * Author: Michael Royal
 * Date: 12/4/16
 */
public class Book {

    private int id;
    private String title;
    private String author;
    private double price;
    private String isbn;
    private String[] fifteen = new String[366];
    private String[] sixteen = new String[367];
    //Arrays Converted to string
    private String sixteenString ="";
    private String fifteenString="";

    public Book() {
    }

    public Book(Book book){

        String[] tempFifteen = new String[book.getFifteen().length];
        String[] tempSixteen = new String[book.getSixteen().length];

        for(int i=0; i<book.getFifteen().length;i++){
            this.fifteen[i] = tempFifteen[i];
        }

        for(int i=0; i<book.getSixteen().length;i++){
            this.sixteen[i] = tempSixteen[i];
        }

        setFifteenString(fifteen);
        setSixteenString(sixteen);

        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.isbn = book.getIsbn();
        this.price = book.getPrice();
    }

    public Book(String title, String author, String isbn, double price) {

        for(int i = 0; i<fifteen.length; i++){
            fifteen[i] = "0";
        }
        for(int i = 0; i<sixteen.length; i++){
            sixteen[i] ="0";
        }

        setFifteenString(fifteen);
        setSixteenString(sixteen);
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
    }

    //**********Getters**************
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public String getIsbn(){
        return isbn;
    }

    public String[] getFifteen(){
        return fifteen;
    }

    public String[] getSixteen(){
        return sixteen;
    }

    public String getFifteenString(){
        return fifteenString;
    }

    public String getSixteenString(){
        return sixteenString;
    }

    //**********Setters**************
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setIsbn(String isbn){
        this.isbn = isbn;
    }

    public void setFifteen(String[] fifteen){
        for(int i=0; i<fifteen.length; i++){
            this.fifteen[i] = fifteen[i];
        }
    }

    public void setSixteen(String[] sixteen){
        for(int i=0; i<sixteen.length; i++){
            this.sixteen[i] = sixteen[i];
        }
    }

    //Set Fifteen Array from the string pulled from the database
    public void setFifteenArray(String fifteenString){
        System.out.println("fifteenString " + fifteenString);
        String[] dates = fifteenString.split("__,__");
        setFifteen(dates);
        setFifteenString(dates);
    }
    //Set Sixteen Array from the string pulled from the database
    public void setSixteenArray(String sixteenString){
        System.out.println("sixteenString " + sixteenString);
        String[] dates = sixteenString.split("__,__");
        setSixteen(dates);
        setSixteenString(dates);
    }
    //Set Fifteen String from the array to be saved to DB
    public String setFifteenString(String[] dates){
        String LIST_SEPARATOR = "__,__";


        StringBuffer stringBuffer = new StringBuffer();

        for (String str : dates) {
            stringBuffer.append(str).append(LIST_SEPARATOR);
        }

        // Remove last separator
        int lastIndex = stringBuffer.lastIndexOf(LIST_SEPARATOR);
        stringBuffer.delete(lastIndex, lastIndex + LIST_SEPARATOR.length() + 1);

        fifteenString = stringBuffer.toString();

        return fifteenString;

    }
    //Set Sixteen String from the array to be saved to DB
    public String setSixteenString(String[] dates){
        String LIST_SEPARATOR = "__,__";


        StringBuffer stringBuffer = new StringBuffer();

        for (String str : dates) {
            stringBuffer.append(str).append(LIST_SEPARATOR);
        }

        // Remove last separator
        int lastIndex = stringBuffer.lastIndexOf(LIST_SEPARATOR);
        stringBuffer.delete(lastIndex, lastIndex + LIST_SEPARATOR.length() + 1);

        sixteenString=stringBuffer.toString();

        return sixteenString;
    }

    @Override
    public String toString() {

        System.out.print("ID" + id + "Date Table: 2015 ");


        for(int i =0; i<fifteen.length;i++){
            if((i+1)%10==0){
                System.out.print(fifteen[i]+"\n");

            }
            else{
                System.out.print(fifteen[i]);
            }
        }

        System.out.print("ID" + id + "Date Table: 2016 ");
        for(int i =0; i<sixteen.length;i++){
            if((i+1)%10==0){
                System.out.print(sixteen[i]+"\n");

            }
            else{
                System.out.print(sixteen[i]);
            }
        }
        return "ID: " + id + "\nTitle: " + title + "\nAuthor: " + author +  "\nISBN: " + isbn +  "\nPrice: " + price +"\n\n\n" +
               getFifteenString() + "\n" + getSixteenString() +"\n";
    }

}