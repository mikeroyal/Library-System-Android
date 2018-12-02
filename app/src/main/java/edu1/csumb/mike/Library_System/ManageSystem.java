package edu1.csumb.mike.Library_System;
/**
 * Title: ManageSystem.java
 * Abstract: This is the class for the ability/activity to access the different abilities of the Librarian/admin
 * Author: Michael Royal
 * Date: 12/9/16
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ManageSystem extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.managesystem);

        View logButton = findViewById(R.id.log_button);
        logButton.setOnClickListener(this);

        View addBookButton = findViewById(R.id.addbook_button);
        addBookButton.setOnClickListener(this);

        View inventoryButton = findViewById(R.id.inventory_button);
        inventoryButton.setOnClickListener(this);

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

        if (v.getId() == R.id.log_button) {
            Intent I = new Intent(getApplicationContext(), Log.class);
            startActivity(I);
        }
        else if (v.getId() == R.id.addbook_button) {
            Intent I = new Intent(getApplicationContext(), AddBook.class);
            startActivity(I);
        }
        else  if(v.getId() == R.id.inventory_button) {
            Intent I = new Intent(getApplicationContext(), Inventory.class);
            startActivity(I);
        }

    }

}