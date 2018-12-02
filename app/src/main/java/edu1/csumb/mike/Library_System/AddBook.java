package edu1.csumb.mike.Library_System;
/**
 * Title: AddBook.java
 * Abstract: This is the class for the ability to add a book to the system.
 * Author: Michael Royal
 * Date: 12/9/2016
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


public class AddBook extends Activity implements View.OnClickListener {

    // create a database for the app
    MySQLiteHelper db = new MySQLiteHelper(this);
    ArrayList<Book> books;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addbook);

        View addBookButton = findViewById(R.id.addbook_button);
        addBookButton.setOnClickListener(this);

        books = new ArrayList<>(db.getAllBooks());
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

    String title;
    String author;
    String isbn;
    String cost;
    double price;

    EditText titleEdit;
    EditText authorEdit;
    EditText isbnEdit;
    EditText priceEdit;

    public void onClick(View v) {

        titleEdit = (EditText) findViewById(R.id.title);
        title = titleEdit.getText().toString();

        authorEdit = (EditText) findViewById(R.id.author);
        author = authorEdit.getText().toString();

        isbnEdit = (EditText) findViewById(R.id.isbn);
        isbn = isbnEdit.getText().toString();

        priceEdit = (EditText) findViewById(R.id.price);
        cost = priceEdit.getText().toString();


        boolean containsDec = false;
        boolean bookExists = false;
        if (v.getId() == R.id.addbook_button) {
            for (Book book : books) {
                if (book.getIsbn().equals(isbn)) {
                    bookExists = true;
                    break;
                }
            }
            //Check for a duplicate book in existing books
            if (bookExists) {
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
                dlgAlert.setMessage("Information is not valid, book already exists");
                dlgAlert.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent I = new Intent(getApplicationContext(), ManageSystem.class);
                                startActivity(I);
                            }
                        });
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
            }
            else{
                //Check if there is a decimal in the price input
                for (int i = 0; i < cost.length(); i++) {
                    if (cost.charAt(i) == '.') {
                        containsDec = true;
                        break;
                    }
                }
                if (containsDec) {
                    price = Double.parseDouble(cost);
                    //Create a new book object from inputs and update DB
                    Book addBook = new Book(title, author, isbn, price);
                    db.addBook(addBook);
                    //Success Alert
                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
                    dlgAlert.setMessage("Successfully added book, Check Inventory ");
                    dlgAlert.setTitle("Otter Library System");
                    dlgAlert.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent I = new Intent(getApplicationContext(), ManageSystem.class);
                                    startActivity(I);
                                }
                            });
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();
                }
                //No decimal exists
                else {
                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
                    dlgAlert.setMessage("Sorry The cost is invalid! Needs a Decimal");
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
    }
}