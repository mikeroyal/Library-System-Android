package edu1.csumb.mike.Library_System;
/**
 * Title: AddBook.java
 * Abstract: This is the class for the ability and activity to View the results of a date inquiry and the result are
 * books available
 * Author: Michael Royal
 * Date: 12/9/16
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;

public class BookResults extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    // creates a database for the app

    MySQLiteHelper db = new MySQLiteHelper(this);
    ArrayList<Book> availableBooks;
    public double cost;
    public ArrayList<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
         availableBooks= new ArrayList<>();
         books = new ArrayList<>(db.getAllBooks());


        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookresults);

        View test = findViewById(R.id.choosedate_button);
        test.setOnClickListener(this);

        System.out.println("Results: Passed info");

        //add passed year
        //get passed info
        Bundle passedExtras = getIntent().getExtras();
        int rentalHours = passedExtras.getInt("rentalHours");
        int pickUpDayOfYear = passedExtras.getInt("pickUpDayOfYear");
        int dropOffDayOfYear= passedExtras.getInt("dropOffDayOfYear");
        String loggedUsername = passedExtras.getString("username");
        int loggedId = passedExtras.getInt("id");
        String pickUpYear = passedExtras.getString("pickUpYear");
        String dropOffYear = passedExtras.getString("dropOffYear");

        System.out.println("Passed - rentalHours: " + rentalHours + " puDay: " + pickUpDayOfYear +
                "Pick Up Year: " + pickUpYear + " dropoff day: " + dropOffDayOfYear +
                "Drop Year: " + dropOffYear + "user: " + loggedUsername + " id: " + loggedId);


        //Get all books that have the dates free
        //Between



        //System.out.println("TESTTEST getAll size:" + books.size());

        //Loop Through all books to check if it is available using the days array
        for(Book book: books) {
            boolean bookAvaialble = true;

            String[] fifteen = new String[book.getFifteen().length];
            fifteen = book.getFifteen();

            //System.out.println("getAll index: " + fifteen[0]);

                //if there is a one in the index book available becomes false
                if (pickUpDayOfYear == dropOffDayOfYear) {
                    if (fifteen[pickUpDayOfYear].equals("1")) {
                        //System.out.println("TEST TEST getAll has one");
                        bookAvaialble = false;
                    }
                }
                else {
                    //Loop through the day of pick up and drop off and if any are one it is unavailable
                    for (int i = pickUpDayOfYear; i < dropOffDayOfYear; i++) {
                        if (fifteen[i].equals("1")) {
                            System.out.println("getAll has one");
                            bookAvaialble = false;
                            break;
                        }
                    }
                }

                //Book is available add to availableBooks Array
                if (bookAvaialble) {
                    availableBooks.add(new Book(book));
                }
            }

        TextView main = (TextView) findViewById(R.id.main);
        ArrayList<String> titles = new ArrayList<>();

        //If No Books are avaialable for this date display alert and go to
        if(availableBooks.size()<=0){
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
            dlgAlert.setMessage("No Books Available for Dates and/or Timeframe.\n Press ok to return to main menu!");
            dlgAlert.setTitle("Otter Library System");
            dlgAlert.setPositiveButton("Exit",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent I = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(I);
                        }
                    });
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
        }

        //add to the titles arraylist for spinner label
        System.out.print("getAll available books: " + availableBooks.size());
        for(int i =0; i<availableBooks.size();i++){
            System.out.print("Title: " + availableBooks.get(i).getTitle() + " ");
            titles.add(availableBooks.get(i).getTitle());
            //main.append("Title: " + availableBooks.get(i).getTitle() + " ");
            System.out.println(books.get(i).toString());
        }


        //Books Spinner
        Spinner spinner = (Spinner) findViewById(R.id.book_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> dayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, titles );
        // Specify the layout to use when the list of choices appears
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(dayAdapter);
        spinner.setOnItemSelectedListener(this);

        System.out.println("");
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

    //When a book is selected the details change, this method does it
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub

        //Currency Format
        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        if (arg0.getId() == R.id.book_spinner) {
            //Save the choice as a string
            Object item = arg0.getItemAtPosition(arg2);
            String title = item.toString();

            //Loop through the available books list to see if the title matches
            for(int i=0; i<availableBooks.size(); i++){
                //if it matches change the text of the details
                if(availableBooks.get(i).getTitle().equals(title)){
                    TextView main = (TextView) findViewById(R.id.main);

                    main.setText("");

                    main.append("Title: " + availableBooks.get(i).getTitle() + "\n");
                    main.append("Author: " + availableBooks.get(i).getAuthor() + "\n");
                    main.append("ISBN: " + availableBooks.get(i).getIsbn() + "\n");
                    main.append("Cost Per Hour: " + formatter.format(availableBooks.get(i).getPrice()));
                    cost = availableBooks.get(i).getPrice();

                }
            }


            //get the book in the array with this Title
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parentView) {
        // your code here
    }

    public void onClick(View v) {

        if (v.getId() == R.id.choosedate_button){

            Spinner spinner = (Spinner) findViewById(R.id.book_spinner);
            String bookTitle = spinner.getSelectedItem().toString();

            Intent I = new Intent(getApplicationContext(), LoginHold.class);

            //***GET PASSED INFO***
            Bundle extras= getIntent().getExtras();
            //PickUp
            int pulledPickUpDayOfYear = extras.getInt("pickUpDayOfYear");
            String pulledYear = extras.getString("pickUpYear");
            String pickUpMonth = extras.getString("pickUpMonth");
            String pickUpDay = extras.getString("pickUpDay");
            String pulledPickUpHour = extras.getString("pickUpHour");
            String pulledPickUpAmPm = extras.getString("pickUpAmPm");
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

            //rentalTotal
            Double rentalTotal = cost*rentalHours;

            //pass stuff to results
            Bundle extraInfo = new Bundle();

            extraInfo.putInt("rentalHours", rentalHours);

            //pick up data
            extraInfo.putInt("pickUpDayOfYear", pulledPickUpDayOfYear);
            extraInfo.putString("pickUpMonth", pickUpMonth);
            extraInfo.putString("pickUpDay", pickUpDay);
            extraInfo.putString("pickUpYear", pulledYear);
            extraInfo.putString("pickUpHour", pulledPickUpHour);
            extraInfo.putString("pickUpAmPm", pulledPickUpAmPm);

            //Drop off data
            extraInfo.putInt("dropOffDayOfYear", dropOffDayOfYear);
            extraInfo.putString("dropOffYear", dropOffYear);
            //System.out.println("dropOffYear " + year);
            extraInfo.putString("dropOffMonth", dropOffMonth);
            extraInfo.putString("dropOffDay", dropOffDay);
            extraInfo.putString("dropOffHour", dropOffHour);
            extraInfo.putString("dropOffAmPm", dropOffAmPm);

            //transaction stuff
            extraInfo.putString("title", bookTitle);
            extraInfo.putDouble("rentalTotal", rentalTotal);

            I.putExtras(extraInfo);

            startActivity(I);
        }

    }


}
