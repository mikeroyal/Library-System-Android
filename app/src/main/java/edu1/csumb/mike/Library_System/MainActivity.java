package edu1.csumb.mike.Library_System;

/**
 * Title: MainActivity.java
 * Abstract: This program allows users to Place a reservation at the CSUMB Library.
 * Author: Michael Royal
 * Date: 12/9/16
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;


public class MainActivity extends Activity implements View.OnClickListener {
    // creates a database for the app
    MySQLiteHelper db = new MySQLiteHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Views and Listeners
        View CreateButton = findViewById(R.id.createaccount_button);
        CreateButton.setOnClickListener(this);

        View HoldButton = findViewById(R.id.placehold_button);
        HoldButton.setOnClickListener(this);

        View cancelButton = findViewById(R.id.cancelhold_button);
        cancelButton.setOnClickListener(this);

        View manageButton = findViewById(R.id.managesystem_button);
        manageButton.setOnClickListener(this);

        //Add the Books and users that should be always on the DB even at creation of APP

        ArrayList<Book> books = new ArrayList<>(db.getAllBooks());
        ArrayList<User> users = new ArrayList<>(db.getAllUsers());

        if(books.size()<1){
            Book hotJava = new Book("Hot Java", "S. Narayanan", "123-ABC-101", 0.05);
            Book funJava = new Book("Fun Java", "Y. Bun", "ABCDEF-09", 1.00);
            Book algorithm = new Book("Algorithm for Java", "K. Alice", "CDE-777-123", 0.25 );
            db.addBook(hotJava);
            db.addBook(funJava);
            db.addBook(algorithm);
        }

        if(users.size()<1){
            User alice = new User("@csit100","a@lice5");
            User brian = new User("123abc##","$brian7");
            User chris = new User("CHRIS12!!","!chris12!");
            User admin = new User("!admin2", "!admin2");
            db.addUser(alice);
            db.addUser(brian);
            db.addUser(chris);
            db.addUser(admin);
        }
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

        if (v.getId() == R.id.createaccount_button) {
            Intent I = new Intent(getApplicationContext(), CreateClass.class);
            startActivity(I);
        }
        else if (v.getId() == R.id.placehold_button) {
            Intent I = new Intent(getApplicationContext(),PlaceHold.class);
            startActivity(I);
        }
        else if (v.getId() == R.id.cancelhold_button) {
            Intent I = new Intent(getApplicationContext(),LoginCancel.class);
            startActivity(I);
        }
        else if (v.getId() == R.id.managesystem_button) {
            Intent I = new Intent(getApplicationContext(),LoginAdmin.class);
            startActivity(I);
        }

    }

}
