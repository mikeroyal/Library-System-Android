package edu1.csumb.mike.Library_System;
/**
 * Title: Confirm.java
 * Abstract: This is the class for the ability and activity to confirm the information in a users rental
 * Author: Michael Royal
 * Date: 12/9/16
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;

public class Confirm extends Activity implements View.OnClickListener {

    int reservation = 0;

    // create a database for the app
    MySQLiteHelper db = new MySQLiteHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm);

        //Reservations counter
        ArrayList<Transaction> transactions = new ArrayList<>(db.getAllTransactions());
        for(Transaction transaction : transactions){
            if(transaction.getTypeNumber()==1){reservation++;}
        }

        View test = findViewById(R.id.confirm_button);
        test.setOnClickListener(this);

        TextView main = (TextView) findViewById(R.id.main);

        //***GET PASSED INFO***
        Bundle extras= getIntent().getExtras();
        //PickUp
        String pickUpYear = extras.getString("pickUpYear");
        String pickUpMonth = extras.getString("pickUpMonth");
        String pickUpDay = extras.getString("pickUpDay");
        String pickUpHour = extras.getString("pickUpHour");
        String pickUpAmPm = extras.getString("pickUpAmPm");
        //DropOff
        String dropOffYear = extras.getString("dropOffYear");
        String dropOffMonth = extras.getString("dropOffMonth");
        String dropOffDay = extras.getString("dropOffDay");
        String dropOffHour = extras.getString("dropOffHour");
        String dropOffAmPm = extras.getString("dropOffAmPm");
        //Transaction
        String loggedUsername = extras.getString("username");
        String bookTitle = extras.getString("title");
        double rentalTotal = extras.getDouble("rentalTotal");

        //Transaction Date and time for Pickup
        String pickUpDateTime;
        pickUpDateTime = getMonthNumber(pickUpMonth) +"/" + pickUpDay + "/" + pickUpYear+ " (" + pickUpHour +" "+ pickUpAmPm + ")";
        //Transaction Date and time for Dropoff
        String dropOffDateTime;
        dropOffDateTime = getMonthNumber(dropOffMonth) +"/" + dropOffDay + "/" + dropOffYear + " (" + dropOffHour +" "+ dropOffAmPm + ")";


        //Add info for rental into details box
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        main.setText("");
        main.append("Username: " + loggedUsername + " \n");
        main.append("Pick Up/Date Time: " + pickUpDateTime + " \n");
        main.append("Return/Date Time: " + dropOffDateTime + " \n");
        main.append("Book Title: " + bookTitle + " \n");
        main.append("Reservation Number: " + (reservation+1) + " \n");
        main.append("Total Rental Cost: " + formatter.format(rentalTotal) + " \n");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {

        if (v.getId() == R.id.confirm_button) {

            Intent I = new Intent(getApplicationContext(), MainActivity.class);

            ArrayList<Book> books = new ArrayList<>(db.getAllBooks());

            //***GET PASSED INFO***
            Bundle extras= getIntent().getExtras();
            //PickUp
            int pickUpDayOfYear = extras.getInt("pickUpDayOfYear");
            String pickUpYear = extras.getString("pickUpYear");
            String pickUpMonth = extras.getString("pickUpMonth");
            String pickUpDay = extras.getString("pickUpDay");
            String pickUpHour = extras.getString("pickUpHour");
            String pickUpAmPm = extras.getString("pickUpAmPm");
            //DropOff
            int dropOffDayOfYear = extras.getInt("dropOffDayOfYear");
            String dropOffYear = extras.getString("dropOffYear");
            String dropOffMonth = extras.getString("dropOffMonth");
            String dropOffDay = extras.getString("dropOffDay");
            String dropOffHour = extras.getString("dropOffHour");
            String dropOffAmPm = extras.getString("dropOffAmPm");
            //Transaction
            int rentalHours = extras.getInt("rentalHours");
            String loggedUsername = extras.getString("username");
            int loggedId = extras.getInt("id");
            String bookTitle = extras.getString("title");
            double rentalTotal = extras.getDouble("rentalTotal");

            //Transaction Date and time for Pickup
            String pickUpDateTime;
            pickUpDateTime = getMonthNumber(pickUpMonth) +"/" + pickUpDay + "/" + pickUpYear+ " (" + pickUpHour +" "+ pickUpAmPm + ")";
            //Transaction Date and time for Dropoff
            String dropOffDateTime;
            dropOffDateTime = getMonthNumber(dropOffMonth) +"/" + dropOffDay + "/" + dropOffYear + " (" + dropOffHour +" "+ dropOffAmPm + ")";


            //find the book in the array by title
            for(int i=0;i<books.size();i++){
                if(books.get(i).getTitle().equals(bookTitle)){
                    System.out.println("getAll This is the book by Title: " + books.get(i).getTitle());
                    String[] fifteen;

                    fifteen = books.get(i).getFifteen();

                    //Alter the array of the book for the rental days
                    for (int j = pickUpDayOfYear; j < dropOffDayOfYear+1; j++) {

                        fifteen[j] = "1";
                        //Set the string by passing the new array
                        books.get(i).setFifteenString(fifteen);
                    }

                    //Update the book table with the fifteenString
                    db.updateBook(books.get(i));
                }
            }

            //public Transaction(String username, int type, double rentalCost, String title, String date,
            //String time, String pickUpDate, String dropOffDate)

            //Create a timestamp
            TimeStamp timeStamp = new TimeStamp();
            //Create a new transaction
            Transaction transaction = new Transaction(loggedUsername, 1, rentalTotal, bookTitle, timeStamp.getDate(),
                    timeStamp.getTime(), pickUpDateTime, dropOffDateTime, pickUpDayOfYear, dropOffDayOfYear, (reservation+1));
            //System.out.println("TESTTESTTEST" + transaction.toString() + "USER: " + transaction.getUsername());

            //add transaction
            db.addTransaction(transaction);
            startActivity(I);
        }
    }

    public int getMonthNumber(String month){
        if(month.equals("January")){
            return 1;
        }
        else if(month.equals("February")){
            return 2;
        }
        else if(month.equals("March")){
            return 3;
        }
        else if(month.equals("April")){
            return 4;
        }
        else if(month.equals("May")){
            return 5;
        }
        else if(month.equals("June")){
            return 6;
        }
        else if(month.equals("July")){
            return 7;
        }
        else if(month.equals("August")){
            return 8;
        }
        else if(month.equals("September")){
            return 9;
        }
        else if(month.equals("October")){
            return 10;
        }
        else if(month.equals("November")){
            return 11;
        }
        else if(month.equals("December")){
            return 12;
        }
        return 0;

    }

}