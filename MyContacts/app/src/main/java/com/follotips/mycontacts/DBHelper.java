package com.follotips.mycontacts;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by cobik99 on 06.09.17.
 */

public  class DBHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "data";
    private static final String COLID = "ID";
    private static final String COLNAME = "name";
    private static final String COLSURNAME = "surname";
    private static final String COLPHONENUMBER = "phoneNumber";
    private static final String COLBIRTHDAY = "birthday";


   // DataConstructor dataConstructor = new DataConstructor();


    public DBHelper(Context context) {
        //konstruktor superklassa
        super(context, TABLE_NAME, null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTables = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLNAME + " TEXT, " +
                COLSURNAME + " TEXT, " +
                COLPHONENUMBER + " TEXT, " +
                COLBIRTHDAY + " TEXT)";
        db.execSQL(createTables);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String name, String surname, String phoneNumber, String birthday) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLNAME,name);
        cv.put(COLSURNAME, surname);
        cv.put(COLPHONENUMBER, phoneNumber);
        cv.put(COLBIRTHDAY, birthday);

        Log.d(TAG, "addData: Adding " + name + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, cv);

        //if date as inserted incorrectly it will return -1

        if (result == -1) {
            return false;

        } else {
            return true;
        }
    }


    //Return all data from database
    public Cursor getDataAll() {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);

        return data;
    }
    //return data from database with id
    public Cursor getDataParam(int id, String surname, String phoneNumber, String birthday){



        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT "+ surname + "," + phoneNumber + "," + birthday + " FROM " + TABLE_NAME +
                " WHERE ID = "  + id ;
        Cursor data1 = db.rawQuery(query,null);
        if (data1 != null){
            data1.moveToFirst();
        }
        return data1;

    }
    //update data from database with id
    public void UpdateDataParam( int id, String name, String surname, String phoneNumber, String birthday) {
        SQLiteDatabase db = this.getWritableDatabase();


        String query = "UPDATE " + TABLE_NAME +
                " SET name = '" + name + "'," +
                " surname = '" + surname + "'," +
                " phoneNumber = '" + phoneNumber + "'," +
                " birthday = '" + birthday + "'" +
                " where id = " + id;

        db.execSQL(query);

    }





    public Cursor getItemID(String name /*, String surname, String phoneNumber, String birthday */){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COLID + " FROM " + TABLE_NAME +
                " WHERE " + COLNAME + " = '" + name + "'" ;
//                " AND " + COLSURNAME + " = '" + surname + "'" +
//                " AND " + COLPHONENUMBER + " = '" + phoneNumber + "'" +
//                " AND " + COLBIRTHDAY + " = '" + birthday + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void deleteData(int id ){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,"id = "+ id,null);

    }
}
