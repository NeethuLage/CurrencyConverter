package com.example.currencyconverter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public boolean insertSearch(String frCurrency, String toCurrency, double enteredamount, double res) {

        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.FROMCURRENCY, frCurrency);
        contentValue.put(DatabaseHelper.TOCURRENCY, toCurrency);
        contentValue.put(DatabaseHelper.AMOUNTENTERED, enteredamount);
        contentValue.put(DatabaseHelper.RESULT, res);

        long result = database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
        Log.d("Count==>", "Insert Done");

        if(result == -1) {
            return false;
        }else{
            return true;

        }
    }




    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper.ID, DatabaseHelper.FROMCURRENCY, DatabaseHelper.TOCURRENCY, DatabaseHelper.AMOUNTENTERED , DatabaseHelper.RESULT };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);


        if (cursor != null) {
            Log.d("Count==>", String.valueOf(cursor.getCount()));
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(int id, String conFromCurren, String conToCurren, double conFromAmt, double resut) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.FROMCURRENCY, conFromCurren);
        contentValues.put(DatabaseHelper.TOCURRENCY, conToCurren);
        contentValues.put(DatabaseHelper.AMOUNTENTERED, conFromAmt);
        contentValues.put(DatabaseHelper.RESULT, resut);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper.ID + " = " + id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.ID + "=" + _id, null);
    }

}
