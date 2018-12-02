package edu1.csumb.mike.Library_System;
/**
 * Title: LoginHold.java
 * Abstract: This is the class for the ability to login for the place hold flow.
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
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginHold extends Activity implements View.OnClickListener {

    // creates a database for the app
    MySQLiteHelper db = new MySQLiteHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        View LoginButton = findViewById(R.id.login_button);
        LoginButton.setOnClickListener(this);

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

    String input1;
    String input2;
    EditText cinput1;
    EditText cinput2;
    static int loggedId;
    static String loggedUsername;
    User loggedUser = null;

    public void onClick(View v) {
        cinput1 = (EditText) findViewById(R.id.username_field);
        input1 = cinput1.getText().toString();

        cinput2 = (EditText) findViewById(R.id.password_field);
        input2 = cinput2.getText().toString();

        if (v.getId() == R.id.login_button) {

            if(checkFormat(input1)&&checkFormat(input2)) {

                //Get Users
                ArrayList<User> users = new ArrayList<>(db.getAllUsers());

                //Contains matching user credentials
                boolean contains = false;

                //Loop Through the list of users to see if the inputs match any
                for(int i=0; i<users.size();i++){

                    if(users.get(i).getUsername().equals(input1) && users.get(i).getPassword().equals(input2)){

                        contains=true;
                        if(contains){
                            System.out.println("Contains==True");
                            //Save logged username by creating a new user
                            loggedUser = new User(users.get(i));
                            loggedId = loggedUser.getId();
                            loggedUsername = loggedUser.getUsername();

                            break;
                        }
                    }
                }

                //User DB contains the login from user
                if(contains) {
                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
                    dlgAlert.setMessage("Successfully Logged in");
                    dlgAlert.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent I = new Intent(getApplicationContext(), Confirm.class);

                                    //GET AND SEND TO BOOK RESULTS
                                    //***GET PASSED INFO***
                                    Bundle extras= getIntent().getExtras();
                                    int rentalHours = extras.getInt("rentalHours");
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

                                    String bookTitle = extras.getString("title");
                                    double rentalTotal = extras.getDouble("rentalTotal");

                                    //***PASS INFO TO RESULTS***
                                    Bundle extraInfo = new Bundle();
                                    //Pick up
                                    extraInfo.putInt("pickUpDayOfYear", pulledPickUpDayOfYear);
                                    extraInfo.putString("pickUpMonth", pickUpMonth);
                                    extraInfo.putString("pickUpDay", pickUpDay);
                                    extraInfo.putString("pickUpYear", pulledYear);
                                    extraInfo.putString("pickUpHour", pulledPickUpHour);
                                    extraInfo.putString("pickUpAmPm", pulledPickUpAmPm);
                                    //Drop off
                                    extraInfo.putInt("dropOffDayOfYear", dropOffDayOfYear);
                                    extraInfo.putString("dropOffYear", dropOffYear);
                                        System.out.println("dropOffYear " + dropOffYear);
                                    extraInfo.putString("dropOffMonth", dropOffMonth);
                                    extraInfo.putString("dropOffDay", dropOffDay);
                                    extraInfo.putString("dropOffHour", dropOffHour);
                                    extraInfo.putString("dropOffAmPm", dropOffAmPm);
                                    //transactionStuff
                                    extraInfo.putInt("rentalHours", rentalHours);
                                    extraInfo.putString("username", loggedUsername );
                                    extraInfo.putInt("id", loggedId);
                                    extraInfo.putString("title", bookTitle);
                                    extraInfo.putDouble("rentalTotal", rentalTotal);

                                    I.putExtras(extraInfo);

                                    startActivity(I);
                                }
                            });
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();
                }
                //Username or password incorrect
                if (!contains) {
                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
                    dlgAlert.setMessage("Sorry The Account Does Not Exist or Passowrd Is Incorrect.");
                    dlgAlert.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();
                }
            }
            //Wrong Format
            else {
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
                dlgAlert.setMessage("Your Username or Password are not correctly formatted! Please Retry!");
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

    //Check format for Login
    public boolean checkFormat(String username){

        ArrayList<Character> charList = new ArrayList<Character>();
        Collections.addAll(charList, '!', '$', '#', '@');

        boolean containsSpecial = false;

        for(Character character: charList) {
            if (username.contains(Character.toString(character))) {
                containsSpecial = true;
            }
        }

        int numberCharacters = 0;

        if(containsSpecial){
            for(int i=0; i<username.length();i++){
                String symbol = String.valueOf(username.charAt(i));

                Pattern pattern = Pattern.compile("[A-z]");

                Matcher matcher = pattern.matcher(symbol);

                if(matcher.matches()){
                    numberCharacters++;
                    System.out.println("Match: " + numberCharacters);
                }
            }
        }

        if(numberCharacters>2){
            return true;
        }
        else {
            return false;
        }
    }

}
