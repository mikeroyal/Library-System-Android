package edu1.csumb.mike.Library_System;
/**
 * Title: DropOff.java
 * Abstract: This is the class for the ability for a user to choose the date of the return of the item.
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

import java.util.Calendar;


public class DropOff extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    String[] thirtyOneDays = new String[31];
    String[] twentyEightDays = new String[28];
    String[] twentyNineDays = new String[29];
    String[] thirtyDays = new String[30];
    String[] hours = new String[12];
    String[] ampm = new String[2];
    //DaySpinner

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dropoff);

        View chooseButton = findViewById(R.id.choosedatedrop_button);
        chooseButton.setOnClickListener(this);

        //Array For Day Spinner
        ampm[0] = "AM";
        ampm[1] = "PM";
        Spinner daySpinner = (Spinner) findViewById(R.id.day_spinner);
        Spinner spinner = (Spinner) findViewById(R.id.month_spinner);
        for (int i = 0; i < thirtyOneDays.length; i++) {
            thirtyOneDays[i] = Integer.toString(1 + i);
        }
        for (int i = 0; i < twentyEightDays.length; i++) {
            twentyEightDays[i] = Integer.toString(1 + i);
        }
        for (int i = 0; i < twentyNineDays.length; i++) {
            twentyNineDays[i] = Integer.toString(1 + i);
        }
        for (int i = 0; i < thirtyDays.length; i++) {
            thirtyDays[i] = Integer.toString(1 + i);
        }
        for (int i = 0; i < hours.length; i++) {
            hours[i] = Integer.toString(1 + i) + ":00";
        }

        //Month Spinner
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.month, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        String selected = spinner.getSelectedItem().toString();

        System.out.println("selected: " + selected);
        spinner.setOnItemSelectedListener(this);

        //Day Spinner
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> dayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, thirtyOneDays);
        // Specify the layout to use when the list of choices appears
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        daySpinner.setAdapter(dayAdapter);

        //Year Spinner
        Spinner yearSpinner = (Spinner) findViewById(R.id.year_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> yearAdapter = ArrayAdapter.createFromResource(this,
                R.array.years, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        yearSpinner.setAdapter(yearAdapter);
        yearSpinner.setOnItemSelectedListener(this);

        //Hour Spinner
        Spinner hourSpinner = (Spinner) findViewById(R.id.hour_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> hourAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hours);
        // Specify the layout to use when the list of choices appears
        hourAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        hourSpinner.setAdapter(hourAdapter);

        //ampm Spinner
        Spinner ampmSpinner = (Spinner) findViewById(R.id.ampm_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> ampmAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ampm);
        // Specify the layout to use when the list of choices appears
        ampmAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        ampmSpinner.setAdapter(ampmAdapter);

    }

    //Change Days based on Month choice
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub

        Spinner yearSpinner = (Spinner) findViewById(R.id.year_spinner);
        Spinner monthSpinner = (Spinner) findViewById(R.id.month_spinner);

        System.out.println("Get ID: " + arg0.getId());
        System.out.println("This is the id" + R.id.month_spinner);

        if (arg0.getId() == R.id.month_spinner) {
            Object item = arg0.getItemAtPosition(arg2);

            String month = item.toString();
            String year = yearSpinner.getSelectedItem().toString();

            setDays(month, year);
        }
        if (arg0.getId() == R.id.year_spinner) {
            Object item = arg0.getItemAtPosition(arg2);

            String year = item.toString();
            String month = monthSpinner.getSelectedItem().toString();

            setDays(month, year);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parentView) {
        // your code here
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
        Spinner monthSpinner = (Spinner) findViewById(R.id.month_spinner);
        Spinner daySpinner = (Spinner) findViewById(R.id.day_spinner);
        Spinner yearSpinner = (Spinner) findViewById(R.id.year_spinner);
        Spinner hourSpinner = (Spinner) findViewById(R.id.hour_spinner);
        Spinner ampmSpinner = (Spinner) findViewById(R.id.ampm_spinner);


        if (v.getId() == R.id.choosedatedrop_button) {

            String month = monthSpinner.getSelectedItem().toString();
            String day = daySpinner.getSelectedItem().toString();
            String year = yearSpinner.getSelectedItem().toString();
            String hour = hourSpinner.getSelectedItem().toString();
            String ampm = ampmSpinner.getSelectedItem().toString();

            //get drop off time
            Time dropTime = new Time(hour,ampm);
            int dropOffHour = dropTime.getHour();
            System.out.println("Drop Off Hour: " + dropOffHour);


            Date date = new Date(month, day, year);
            int dayNumber = date.getDayNumber();

            //Gets infor passed from plachold
            Bundle extrasPlaceHold = getIntent().getExtras();
            //date
            String pulledYear = extrasPlaceHold.getString("pickUpYear");
            String pickUpMonth = extrasPlaceHold.getString("pickUpMonth");
            String pickUpDay = extrasPlaceHold.getString("pickUpDay");
            //day of year
            int pulledPickUpDayOfYear = extrasPlaceHold.getInt("pickUpDayOfYear");
            //hour
            String pulledPickUpHour = extrasPlaceHold.getString("pickUpHour");
            String pulledPickUpAmPm = extrasPlaceHold.getString("pickUpAmPm");

            //get pick up time
            Time pickTime = new Time(pulledPickUpHour,pulledPickUpAmPm);
            int pickHour = pickTime.getHour();
            System.out.println("Pick up Hour: " + pickHour);
            //compare date numbers if the number dif is greater than 7 days prompt user to choose
            //new date
            //get current year and day of year
            Calendar calendar = Calendar.getInstance();
            int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
            int currentYear = calendar.get(Calendar.YEAR);

            //Change this for addition of 2016 Calandar 365 <- 358
            if(pulledPickUpDayOfYear<=365&&!(year.equals("2016"))) {
                int difference = dayNumber - pulledPickUpDayOfYear;
                System.out.println("choice This is the difference: " + difference);
                //if the days difference is 7 and hours chosen is greater or past the hours chosen OT
                if (difference <= 7 && difference >= 0) {
                    //check if drop time exceeds the 24 hour mark for 7 day
                    if (dropOffHour > pickHour && difference == 7) {
                        System.out.println("choice 7day Rental exceeds time: " + (dropOffHour - pickHour));
                        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
                        dlgAlert.setMessage("Sorry, The Chosen Time puts you past the 7 day limit by " + (dropOffHour - pickHour) + " hours." );
                        dlgAlert.setTitle("Otter Library System");
                        dlgAlert.setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        dlgAlert.setCancelable(true);
                        dlgAlert.create().show();
                    }
                    //Same Day Rental
                    else if (dropOffHour < pickHour && difference == 0) {
                        System.out.println("choice 7day Rental exceeds time: " + (dropOffHour - pickHour));
                        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
                        dlgAlert.setMessage("Sorry, The Chosen Time is before the pick up time by " + (pickHour-dropOffHour) + " hours." );
                        dlgAlert.setTitle("Otter Library System");
                        dlgAlert.setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        dlgAlert.setCancelable(true);
                        dlgAlert.create().show();
                    }
                    //Success for rental date choices
                    else {
                        System.out.println("choice This is the difference: you are in success " + difference);

                        //if difference and time are good go to bookResults
                        Intent I = new Intent(getApplicationContext(), BookResults.class);

                        //Get How many rental hours for this transaction
                        int rentalHours = rentalHours(difference,pickHour,dropOffHour);
                        System.out.println("rentalHours" + rentalHours);

                        //Pass info to login
                        Bundle extraInfo = new Bundle();
                        //How many hour total
                        extraInfo.putInt("rentalHours", rentalHours);

                        //Pick up
                        extraInfo.putInt("pickUpDayOfYear", pulledPickUpDayOfYear);
                        extraInfo.putString("pickUpMonth", pickUpMonth);
                        extraInfo.putString("pickUpDay", pickUpDay);
                        extraInfo.putString("pickUpYear", pulledYear);
                        extraInfo.putString("pickUpHour", pulledPickUpHour);
                        extraInfo.putString("pickUpAmPm", pulledPickUpAmPm);

                        //Drop off
                        extraInfo.putInt("dropOffDayOfYear", dayNumber);
                        extraInfo.putString("dropOffYear", year);
                        //System.out.println("dropOffYear " + year);
                        extraInfo.putString("dropOffMonth", month);
                        extraInfo.putString("dropOffDay", day);
                        extraInfo.putString("dropOffHour" , hour);
                        extraInfo.putString("dropOffAmPm", ampm);

                        //extraInfo.putString("result2", input2);
                        I.putExtras(extraInfo);

                        startActivity(I);
                    }
                }
                //Rental Choice Exceeds Time Frame
                else if(difference>7){
                    System.out.println("choice Rental exceeds time frame");

                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
                    dlgAlert.setMessage("Sorry, The Chosen Date puts you past the 7 day limit by " + difference + " days." );
                    dlgAlert.setTitle("Otter Library System");
                    dlgAlert.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();
                }
                //User chose a date before the pick up date
                else if(difference<0){
                    System.out.println("choice Rental is before pick up date");
                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
                    dlgAlert.setMessage("Sorry, The Chosen Date is before the pick up date" );
                    dlgAlert.setTitle("Otter Library System");
                    dlgAlert.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();
                }
            }
            //for dates past 360 day not active for current release
            else{
                System.out.println("choice is past dec 24");
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
                dlgAlert.setMessage(" Test for date after 24" );
                dlgAlert.setTitle("Otter Library System");
                dlgAlert.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
            }

        }

    }
    //Method to return The amount of total Rental Hours
    public int rentalHours(int differnce, int hourPick, int hourDrop){
        int hours=0;
        int hourDiffernce;

        //
        hours=differnce*24;

        //subtract hours if the pickup is later than drop off
        if(hourPick>hourDrop){
             hourDiffernce = hourPick-hourDrop;
             hours-=hourDiffernce;
        }
        //add hours if the pickup is earlier than drop off
        else if(hourPick<hourDrop){
             hourDiffernce = hourDrop-hourPick;
             hours+=hourDiffernce;
        }

        return hours;
    }
    //Set Spinner Days
    public void setDays(String month, String year) {
        Spinner daySpinner = (Spinner) findViewById(R.id.day_spinner);

        if (month.equals("January") || month.equals("March") || month.equals("May") || month.equals("July") ||
                month.equals("August") || month.equals("October") || month.equals("December")) {

            ArrayAdapter<String> dayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, thirtyOneDays);
            dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            daySpinner.setAdapter(dayAdapter);
        } else if (month.equals("February") && !(year.equals("2016"))) {
            ArrayAdapter<String> dayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, twentyEightDays);
            dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            daySpinner.setAdapter(dayAdapter);
        } else if (month.equals("February") && year.equals("2016")) {
            ArrayAdapter<String> dayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, twentyNineDays);
            dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            daySpinner.setAdapter(dayAdapter);
        } else if (month.equals("April") || month.equals("June") || month.equals("September") ||
                month.equals("November")) {
            ArrayAdapter<String> dayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, thirtyDays);
            dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            daySpinner.setAdapter(dayAdapter);
        }


    }
}