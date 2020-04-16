package com.example.currencyconverter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "CONVERSION";

    // Table columns
    public static final String ID = "id";
    public static final String FROMCURRENCY = "ConvertFromCurrency";
    public static final String TOCURRENCY = "ConvertToCurrency";
    public static final String AMOUNTENTERED = "AmountEntered";
    public static final String RESULT = "ResultantAmount";


    // Database Information
    static final String DB_NAME = "CONVERSION.DB";

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " +  FROMCURRENCY + " TEXT NOT NULL, " + TOCURRENCY + " TEXT NOT NULL, "+ AMOUNTENTERED + " REAL NOT NULL, " + RESULT + " REAL NOT NULL);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    //update database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
