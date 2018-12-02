package edu1.csumb.mike.Library_System;
/**
 * Title: MySqLiteHelper.java
 * Abstract: The Methods inorder to CRUD the database for this application.
 * Author: Michael Royal
 * Date: 12/9/16
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class MySQLiteHelper extends SQLiteOpenHelper {

    // Database Name - BookDB
    private static final String DATABASE_NAME = "BookDB";

    // Table Name - books and users
    private static final String TABLE_BOOKS = "books";
    private static final String TABLE_USERS = "users";
    private static final String TABLE_TRANSACTIONS = "transactions";

    // Columns Names of books Table
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_ISBN = "isbn";
    private static final String KEY_PRICE = "price";
    private static final String KEY_FIFTEEN = "fifteen";
    private static final String KEY_SIXTEEN = "sixteen";
    //Columns Names of users table
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    //Columns Names of transaction table
    private static final String KEY_NUMBER = "number";
    private static final String KEY_USERNAMETRAN = "username";
    private static final String KEY_TYPE = "type";
    private static final String KEY_RETURN = "return";
    private static final String KEY_PICKUP = "pickup";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";
    private static final String KEY_TITLETRAN = "title";
    private static final String KEY_COST = "cost";
    private static final String KEY_TYPENUMBER = "typenumber";
    private static final String KEY_ACTIVE = "active";
    private static final String KEY_PICKDAYYEAR = "pickdayyear";
    private static final String KEY_DROPDAYYEAR = "dropdayyear";
    private static final String KEY_RESERVATION = "reservation";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Log TAG for debugging purpose
    private static final String TAG = "SQLiteAppLog";

    // Constructor
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create a table called "books"
        String CREATE_BOOK_TABLE = "CREATE TABLE books ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, "+
                "author TEXT, " +
                "isbn TEXT, " +
                "price REAL, " +
                "fifteen TEXT, " +
                "sixteen TEXT)";

        String CREATE_USER_TABLE = "CREATE TABLE users ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT, "+
                "password TEXT)";

        String CREATE_TRANSACTION_TABLE = "CREATE TABLE transactions ( " +
                "number INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT, "+
                "type TEXT, "+
                "title TEXT, "+
                "pickup TEXT, " +
                "return TEXT, " +
                "cost REAL, " +
                "date TEXT, " +
                "time TEXT, " +
                "active INTEGER, " +
                "typenumber INTEGER, " +
                "pickdayyear INTEGER, " +
                "dropdayyear INTEGER, "+
                "reservation INTEGER)";

        // execute an SQL statement to create the table
        db.execSQL(CREATE_BOOK_TABLE);
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_TRANSACTION_TABLE);
    }

    // onUpdate() is invoked when you upgrade the database scheme.

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS books");
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS transactions");
        // create fresh books and users table
        this.onCreate(db);
    }
    //******************************METHODS FOR BOOK*******************************************

    public void addBook(Book book){
        Log.d(TAG, "addBook() - " + book.toString());
        //  get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        //  create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, book.getTitle()); // get title
        values.put(KEY_AUTHOR, book.getAuthor()); // get author
        values.put(KEY_ISBN, book.getIsbn()); // get isbn
        values.put(KEY_PRICE, book.getPrice());
        System.out.println("book.getFifteenString()" + book.getFifteenString());
        System.out.println("book.getSixteenString()" + book.getSixteenString());
        values.put(KEY_FIFTEEN, book.getFifteenString());
        values.put(KEY_SIXTEEN, book.getSixteenString());
        //  insert
        db.insert(TABLE_BOOKS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close - release the reference of writable DB
        db.close();
    }

    // Get all books from the database
    public ArrayList<Book> getAllBooks() {
        ArrayList<Book> books = new ArrayList<Book>();

        //  build the query
        String query = "SELECT  * FROM " + TABLE_BOOKS;

        //  get reference to writable DB
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        //  go over each row, build book and add it to list
        Book book = null;
        if (cursor.moveToFirst()) {
            do {
                book = new Book();
                book.setId(Integer.parseInt(cursor.getString(0)));
                //System.out.println("TESTid: " + Integer.parseInt(cursor.getString(0)));
                book.setTitle(cursor.getString(1));
                book.setAuthor(cursor.getString(2));
                book.setIsbn(cursor.getString(3));
                book.setPrice(Double.parseDouble(cursor.getString(4)));
                System.out.println("cursor.getString(5)" + cursor.getString(5));
                book.setFifteenArray(cursor.getString(5));
                System.out.println("cursor.getString(6)" + cursor.getString(6));
                book.setSixteenArray(cursor.getString(6));
                // Add book to books
                books.add(book);
            } while (cursor.moveToNext());
        }

        Log.d(TAG, "getAllBooks() - " + books.toString());

        // return books
        return books;
    }

    // Updating single book
    public int updateBook(Book book) {

        //  get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        //  create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("title", book.getTitle()); // get title
        values.put("author", book.getAuthor()); // get author
        values.put("isbn", book.getIsbn()); // get author
        values.put("price", book.getPrice());
        values.put("fifteen", book.getFifteenString());
        values.put("sixteen", book.getSixteenString());
        //  updating row
        int i = db.update(TABLE_BOOKS, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(book.getId()) }); //selection args

        //  close
        db.close();

        return i;

    }

    // Deleting single book
    public void deleteBook(Book book) {

        //  get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        //  delete
        db.delete(TABLE_BOOKS,
                KEY_ID + " = ?",
                new String[]{String.valueOf(book.getId())});

        //  close
        db.close();

        Log.d(TAG, "deleteBook() - " + book.toString());
    }

    //*****************METHODS FOR USER TABLE***********************

    public void addUser(User user){
        Log.d(TAG, "addUser() - " + user.toString());
        //  get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        //  create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUsername()); // get title
        values.put(KEY_PASSWORD, user.getPassword()); // get author

        //  insert to table
        db.insert(TABLE_USERS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        //  close - release the reference of writable DB
        db.close();
    }

    // Get all books from the database
    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<User>();

        // build the query
        String query = "SELECT  * FROM " + TABLE_USERS;

        //  get reference to writable DB
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // go over each row, build book and add it to list
        User user = null;
        if (cursor.moveToFirst()) {
            do {
                user= new User();
                user.setId(Integer.parseInt(cursor.getString(0)));
                //System.out.println("TESTid: " + Integer.parseInt(cursor.getString(0)));
                user.setUsername(cursor.getString(1));
                user.setPassword(cursor.getString(2));

                // Add book to books
                users.add(user);
            } while (cursor.moveToNext());
        }

        Log.d(TAG, "getAllUsers() - " + users.toString());

        // return books
        return users;
    }

    // Deleting single book
    public void deleteUser(User user) {

        // get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        //  delete
        db.delete(TABLE_USERS,
                KEY_ID + " = ?",
                new String[]{String.valueOf(user.getId())});

        // close
        db.close();

        Log.d(TAG, "deleteUser() - " + user.toString());
    }

    //*****************METHODS FOR TRANSACTIONS TABLE***********************

    public void addTransaction(Transaction transaction){
        Log.d(TAG, "addTransaction() - " + transaction.toString());
        // get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAMETRAN, transaction.getUsername());
        System.out.println("TESTTEST: transaction get username" + transaction.getUsername());
        values.put(KEY_TYPE, transaction.getType());
        values.put(KEY_TITLETRAN, transaction.getTitle());
        values.put(KEY_PICKUP, transaction.getPickUpDate());
        values.put(KEY_RETURN, transaction.getDropOffDate());
        values.put(KEY_COST, transaction.getRentalCost());
        values.put(KEY_DATE, transaction.getDate());
        values.put(KEY_TIME, transaction.getTime());

        System.out.println("TESTTEST: transaction get active" + transaction.getActive());
        values.put(KEY_ACTIVE, transaction.getActive());

        values.put(KEY_TYPENUMBER, transaction.getTypeNumber());
        values.put(KEY_PICKDAYYEAR, transaction.getPickDayYear());
        values.put(KEY_DROPDAYYEAR, transaction.getDropDayYear());
        values.put(KEY_RESERVATION, transaction.getReservation());


        // insert to table
        db.insert(TABLE_TRANSACTIONS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // close - release the reference of writable DB
        db.close();
    }

    // Get all transactions from the database
    public ArrayList<Transaction> getAllTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<>();

        //  build the query
        String query = "SELECT  * FROM " + TABLE_TRANSACTIONS;

        //  get reference to writable DB
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        //  go over each row, build book and add it to list
        Transaction transaction = null;
        if (cursor.moveToFirst()) {
            do {
                transaction= new Transaction();

                transaction.setId(Integer.parseInt(cursor.getString(0)));
                System.out.println("cursor0: " + cursor.getString(0));
                transaction.setUsername(cursor.getString(1));
                System.out.println("acursor1: " + cursor.getString(1));

                System.out.println("acursor2: " + cursor.getString(2));
                System.out.println("acursor3: " + cursor.getString(3));
                System.out.println("acursor4: " + cursor.getString(4));
                System.out.println("acursor5: " + cursor.getString(5));
                System.out.println("acursor6: " + cursor.getString(6));
                System.out.println("acursor7: " + cursor.getString(7));
                System.out.println("acursor8: " + cursor.getString(8));
                System.out.println("acursor9: " + cursor.getString(9));
                System.out.println("acursor10: " + cursor.getString(10));



                transaction.setType(cursor.getString(2));
                transaction.setTitle(cursor.getString(3));
                transaction.setPickUpDate(cursor.getString(4));
                transaction.setDropOffDate(cursor.getString(5));
                transaction.setRentalCost(Double.parseDouble(cursor.getString(6)));
                transaction.setDate(cursor.getString(7));
                transaction.setTime(cursor.getString(8));
                transaction.setActive(Integer.parseInt(cursor.getString(9)));
                transaction.setTypeNumber(Integer.parseInt(cursor.getString(10)));
                transaction.setPickDayYear(Integer.parseInt(cursor.getString(11)));
                transaction.setDropDayYear(Integer.parseInt(cursor.getString(12)));
                transaction.setReservation(Integer.parseInt(cursor.getString(13)));

                // Add book to books
                transactions.add(transaction);
            } while (cursor.moveToNext());
        }

        Log.d(TAG, "getAllTransactions() - " + transactions.toString());

        // return books
        return transactions;
    }

    //update transaction
    public int updateTransaction(Transaction transaction) {

        //  get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        //  create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAMETRAN, transaction.getUsername());
        System.out.println("TESTTEST: transaction get username" + transaction.getUsername());
        values.put(KEY_TYPE, transaction.getType());
        values.put(KEY_TITLETRAN, transaction.getTitle());
        values.put(KEY_PICKUP, transaction.getPickUpDate());
        values.put(KEY_RETURN, transaction.getDropOffDate());
        values.put(KEY_COST, transaction.getRentalCost());
        values.put(KEY_DATE, transaction.getDate());
        values.put(KEY_TIME, transaction.getTime());

        System.out.println("TESTTEST: transaction get active" + transaction.getActive());
        values.put(KEY_ACTIVE, transaction.getActive());

        values.put(KEY_TYPENUMBER, transaction.getTypeNumber());
        values.put(KEY_PICKDAYYEAR, transaction.getPickDayYear());
        values.put(KEY_DROPDAYYEAR, transaction.getDropDayYear());
        values.put(KEY_RESERVATION, transaction.getReservation());

        // updating row
        int i = db.update(TABLE_TRANSACTIONS, //table
                values, // column/value
                KEY_NUMBER+" = ?", // selections
                new String[] { String.valueOf(transaction.getId()) }); //selection args

        // close
        db.close();

        return i;

    }



}