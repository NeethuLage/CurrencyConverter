package com.example.currencyconverter;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ConversionFav1 extends AppCompatActivity {

    ListView ConversionHistoryList;
    DBManager DBMangerObj;
    private ArrayList<Favorites> ConverList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversionfav);

        ConversionHistoryList = findViewById(R.id.cclistview);

        //creating an object to get data from DBManager
        DBMangerObj = new DBManager(this);
        DBMangerObj.open();
        populateListHist();



    }

    private void populateListHist()
    {
        //fetching data from DBManager
        Cursor CursorData = DBMangerObj.fetchFav();
        String fromCurrency,toCurrency;

        ConverList = new ArrayList<>();

        if (CursorData != null) {
            for (int i = 0; i < CursorData.getCount(); i++) {

                fromCurrency = CursorData.getString(CursorData.getColumnIndex(DatabaseHelper.FROMCURRENCY));
                toCurrency = CursorData.getString(CursorData.getColumnIndex(DatabaseHelper.TOCURRENCY));

                Favorites ConversionObj = new Favorites(fromCurrency,toCurrency);

                ConverList.add(ConversionObj);
                CursorData.moveToNext();
            }

            //creating adapter object
            ItemsAdapterFav adapter = new ItemsAdapterFav(this, ConverList);
//           / ItemsAdapter adapter = new ItemsAdapter(this, ConverList);

            //retrieving from resources
            ListView listView = (ListView) findViewById(R.id.cclistview);
            listView.setAdapter(adapter);
        }
        //DBMangerObj
    }

}
