package edu1.csumb.mike.Library_System;
/**
 * Title: Log.java
 * Abstract: This is the class for the ability to retreive all Transaction objects: Rental, Cancel, Creation of Account.
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

import java.util.ArrayList;

public class Log extends Activity implements View.OnClickListener {

    // creates a database for the app
    MySQLiteHelper db = new MySQLiteHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory);

        TextView main = (TextView) findViewById(R.id.maininventory);

        View mainButton = findViewById(R.id.main_button);
        mainButton.setOnClickListener(this);
        mainButton.bringToFront();

        ArrayList<Transaction> transactions = new ArrayList<>(db.getAllTransactions());

        for(Transaction transaction: transactions){

            if(transaction.getTypeNumber()==1||transaction.getTypeNumber()==2) {
                main.append("\n\nTransaction Number: " + transaction.getId());
                main.append("\n     Reservation Number: " + transaction.getReservation());
                main.append("\n     Username: " + transaction.getUsername());
                main.append("\n     Transaction Type: " + transaction.getType());
                main.append("\n     Book Title: " + transaction.getTitle());
                main.append("\n     Pick Up: " + transaction.getPickUpDate());
                main.append("\n     Return: " + transaction.getDropOffDate());
                main.append("\n     Transaction Date: " + transaction.getDate());
                main.append("\n     Transaction Time: " + transaction.getTime());
            }
            else{
                main.append("\n\nTransaction Number: " + transaction.getId());
                main.append("\n     Username: " + transaction.getUsername());
                main.append("\n     Transaction Type: " + transaction.getType());
                main.append("\n     Transaction Date: " + transaction.getDate());
                main.append("\n     Transaction Time: " + transaction.getTime());
            }
        }
        db.getAllBooks();
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
        if (v.getId() == R.id.main_button) {
            Intent I = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(I);
        }
    }

}