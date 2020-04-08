package com.example.currencyconverter;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

 /*
          fetching flag images, country and currency from the listview(in R.layout.currencylist) item that is clicked
          and passing it to the Currency_Conversion class
         */

public class MainActivity extends AppCompatActivity {

    ListView listview;

    //array for flag images
    int [] images = {R.drawable.aus, R.drawable.cad, R.drawable.euro,  R.drawable.bri, R.drawable.ind,
            R.drawable.jap, R.drawable.sng, R.drawable.us};

    //array for countries
    String [] countries = {"Australia", "Canada", "Europe", "Great Britain", "India", "Japan", "Singapore", "U.S.A"};
    //array for currencies
    String[] currencies = {"AUD", "CAD", "EUR", "GBP", "INR", "JPY", "SGD", "USD"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currencylist);


        listview = findViewById(R.id.listview);
        CustomAdapter adapter = new CustomAdapter();
        listview.setAdapter(adapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long viewId) {
                Log.i("position",Integer.toString(position));
                Log.i("position",countries[position]);

                //passing the fetched flag image, country and currency from the listview to the Currency_Conversion class
                Intent intent = new Intent(getApplicationContext(), Currency_Conversion.class);
                intent.putExtra("country",countries[position]);

                intent.putExtra("currency", currencies[position]);
                intent.putExtra("flag", images[position]);
                startActivity(intent);
            }
        });
    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        //fetching flag images, country and currency from the listview
        @Override
        public View getView(int pos, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.currencylistelement,null);

            ImageView flag = view1.findViewById(R.id.flag);
            TextView country = view1.findViewById(R.id.country);
            TextView currency = view1.findViewById(R.id.currency);

            flag.setImageResource(images[pos]);
            country.setText(countries[pos]);
            currency.setText(currencies[pos]);
            return view1;
        }
    }
}
