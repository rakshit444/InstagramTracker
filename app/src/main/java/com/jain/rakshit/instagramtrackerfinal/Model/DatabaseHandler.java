package com.jain.rakshit.instagramtrackerfinal.Model;

/**
 * Created by Rakshit on 6/22/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "RelationshipManager";

    // Contacts table name
    private static final String RELATIONSHIPS = "relationships";

    // Contacts Table Columns names
    private static final String USER_ID = "user_id";
    private static final String CODE = "code";
    private static final String OUTGOIND_STATUS = "outgoing_status";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS" + RELATIONSHIPS + "("
                + USER_ID + " INTEGER PRIMARY KEY," + CODE + " INTEGER,"
                + OUTGOIND_STATUS + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + RELATIONSHIPS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    void addRelations(DBRelations mDBRelations) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_ID, mDBRelations.getUser_id());
        values.put(CODE, mDBRelations.getCode()); // Contact Name
        values.put(OUTGOIND_STATUS, mDBRelations.getOutgoing_status()); // Contact Phone

        // Inserting Row
        db.insert(RELATIONSHIPS, null, values);

        db.close(); // Closing database connection
    }

    // Getting single contact
    DBRelations getRelation(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(RELATIONSHIPS, new String[]{USER_ID,
                        CODE, OUTGOIND_STATUS}, USER_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        DBRelations mDBRelations = new DBRelations(Integer.parseInt(cursor.getString(0)),
                Integer.parseInt(cursor.getString(1)), cursor.getString(2));
        // return mDBRelations
        return mDBRelations;
    }

    // Getting All Contacts
    public List<DBRelations> getAllContacts() {
        List<DBRelations> contactList = new ArrayList<DBRelations>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + RELATIONSHIPS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DBRelations mDBRelations = new DBRelations();
                mDBRelations.setUser_id(Integer.parseInt(cursor.getString(0)));
                mDBRelations.setCode(Integer.parseInt(cursor.getString(1)));
                mDBRelations.setOutgoing_status(cursor.getString(2));
                // Adding mDBRelations to list
                contactList.add(mDBRelations);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // Updating single mDBRelations
    public int updateContact(DBRelations mDBRelations) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CODE, mDBRelations.getCode());
        values.put(OUTGOIND_STATUS, mDBRelations.getOutgoing_status());

        // updating row
        return db.update(RELATIONSHIPS, values, USER_ID + " = ?",
                new String[]{String.valueOf(mDBRelations.getUser_id())});
    }

    // Deleting single mDBRelations
    public void deleteContact(DBRelations mDBRelations) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(RELATIONSHIPS, USER_ID + " = ?",
                new String[]{String.valueOf(mDBRelations.getUser_id())});
        db.close();
    }


    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + RELATIONSHIPS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
