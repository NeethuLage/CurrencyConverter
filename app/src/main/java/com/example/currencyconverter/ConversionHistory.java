package com.example.currencyconverter;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ConversionHistory extends AppCompatActivity {

    ListView ConversionHistoryList;
    DBManager DBMangerObj;
    private ArrayList<Conversion> ConverList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversionhistory);

        ConversionHistoryList = findViewById(R.id.cclistview);
        DBMangerObj = new DBManager(this);
        DBMangerObj.open();
        populateListHist();
    }

    private void populateListHist()


    {
        //fetching data from DBManager
        Cursor CursorData = DBMangerObj.fetch();
        String fromCurrency,toCurrency;
        double enteredAmt,resAmt;
        ConverList = new ArrayList<>();

        if (CursorData != null) {
            for (int i = 0; i < CursorData.getCount(); i++) {


                fromCurrency = CursorData.getString(CursorData.getColumnIndex(DatabaseHelper.FROMCURRENCY));
                toCurrency = CursorData.getString(CursorData.getColumnIndex(DatabaseHelper.TOCURRENCY));
                enteredAmt = CursorData.getDouble(CursorData.getColumnIndex(DatabaseHelper.AMOUNTENTERED));
                resAmt = CursorData.getDouble(CursorData.getColumnIndex(DatabaseHelper.RESULT));
                Conversion ConversionObj = new Conversion(fromCurrency,toCurrency,enteredAmt,resAmt);

                ConverList.add(ConversionObj);
                CursorData.moveToNext();

            }

            ItemsAdapter adapter = new ItemsAdapter(this, ConverList);


            ListView listView = (ListView) findViewById(R.id.cclistview);
            listView.setAdapter(adapter);

        }

        //DBMangerObj
    }
}
