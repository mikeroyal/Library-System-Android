package edu1.csumb.mike.Library_System;
/**
 * Title: PlaceHold.java
 * Abstract: This is the class for the ability to choose the pick update for the rental
 * Author: Michael Royal
 * Date: 12/9/16
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class PlaceHold extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener{
    String[] thirtyOneDays = new String[31];
    String[] twentyEightDays = new String[28];
    String[] twentyNineDays = new String[29];
    String[] thirtyDays = new String[30];
    String[] hours = new String[12];
    String[] ampm = new String[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.placehold);

        View chooseButton = findViewById(R.id.choosedate_button);
        chooseButton.setOnClickListener(this);

        //Arrays for days to fill spinners
        ampm[0] = "AM";
        ampm[1] = "PM";
        Spinner daySpinner = (Spinner) findViewById(R.id.day_spinner);
        Spinner spinner = (Spinner) findViewById(R.id.month_spinner);

        for(int i=0;i<thirtyOneDays.length;i++){
            thirtyOneDays[i]=Integer.toString(1+i);
        }
        for(int i=0;i<twentyEightDays.length;i++){
            twentyEightDays[i]=Integer.toString(1+i);
        }
        for(int i=0;i<twentyNineDays.length;i++){
            twentyNineDays[i]=Integer.toString(1+i);
        }
        for(int i=0;i<thirtyDays.length;i++){
            thirtyDays[i]=Integer.toString(1+i);
        }
        for(int i=0;i<hours.length;i++){
            hours[i]=Integer.toString(1+i) + ":00";
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

        //System.out.println("selected: "+selected);
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

    //When a month is selected this method loads the amount of days for that specific month
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub

        Spinner yearSpinner = (Spinner) findViewById(R.id.year_spinner);
        Spinner monthSpinner = (Spinner) findViewById(R.id.month_spinner);

        //System.out.println("Get ID: " + arg0.getId());
        //System.out.println("This is the id" + R.id.month_spinner);

        if (arg0.getId() == R.id.month_spinner) {
            Object item = arg0.getItemAtPosition(arg2);
            String month = item.toString();
            String year = yearSpinner.getSelectedItem().toString();
            //get
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


    if(v.getId()==R.id.choosedate_button){
        String month = monthSpinner.getSelectedItem().toString();
        String day = daySpinner.getSelectedItem().toString();
        String year = yearSpinner.getSelectedItem().toString();
        String hour = hourSpinner.getSelectedItem().toString();
        String ampm = ampmSpinner.getSelectedItem().toString();

        //Create a new date to pass down to confirmation and for math to calculate cost
        Date date = new Date(month,day,year);
        //Daynumber for cost calc and day save
        int dayNumber = date.getDayNumber();
        System.out.println("Day Number: " + dayNumber);



        /*
        ********************UnComment to disallow rentals before the Current Date************************
        //
        Calendar calendar = Calendar.getInstance();
        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        int currentYear = calendar.get(Calendar.YEAR);

        System.out.println("Day of the year today: " + dayOfYear);
        if(dayNumber<dayOfYear&&year.equals(Integer.toString(currentYear))){
            System.out.println("You picked a date in the past ");
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
            dlgAlert.setMessage("Sorry, The Chosen Date Has Passed! Try Again");
            dlgAlert.setTitle("Otter Library System");
            dlgAlert.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
        }
        else{*/

            //Passes All info to the next page DropOff
            Intent I = new Intent(getApplicationContext(), DropOff.class);
            Bundle extraInfo = new Bundle();

            extraInfo.putInt("pickUpDayOfYear", dayNumber);
            extraInfo.putString("pickUpMonth", month);
            extraInfo.putString("pickUpDay", day);
            extraInfo.putString("pickUpYear", year);
            extraInfo.putString("pickUpHour", hour);
            extraInfo.putString("pickUpAmPm", ampm);
            I.putExtras(extraInfo);
            startActivity(I);
        }
    }

    //This is the method that will change the days in the spinner based on month and year, accounting for leap year
    public void setDays(String month, String year){
        Spinner daySpinner = (Spinner) findViewById(R.id.day_spinner);

        if(month.equals("January")||month.equals("March")||month.equals("May")||month.equals("July")||
                month.equals("August")||month.equals("October")||month.equals("December")){

            ArrayAdapter<String> dayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, thirtyOneDays);
            dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            daySpinner.setAdapter(dayAdapter);
        }
        else if(month.equals("February")&&!(year.equals("2016"))){
            ArrayAdapter<String> dayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, twentyEightDays);
            dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            daySpinner.setAdapter(dayAdapter);
        }
        else if(month.equals("February")&&year.equals("2016")){
            ArrayAdapter<String> dayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, twentyNineDays);
            dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            daySpinner.setAdapter(dayAdapter);
        }
        else if(month.equals("April")||month.equals("June")||month.equals("September")||
                month.equals("November")){
            ArrayAdapter<String> dayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, thirtyDays);
            dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            daySpinner.setAdapter(dayAdapter);
        }


    }


}

