package com.example.currencyconverter;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ListView listview;

    int [] images = {R.drawable.aus, R.drawable.cad, R.drawable.euro,  R.drawable.bri, R.drawable.ind,
            R.drawable.jap, R.drawable.sng, R.drawable.us};

    String [] countries = {"Australia", "Canada", "Europe", "Great Britain", "India", "Japan", "Singapore", "U.S.A"};
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
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {

//                CountryListActivity.this.finish();
                TextView currency = (TextView) view.findViewById(R.id.currency);

                String convertfrom = currency.getText().toString();

                Intent intent = new Intent(MainActivity.this, Currency_Conversion.class);
                //intent.putExtra("convertfrom", currency.getText().toString());
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

        @Override
        public View getView(int pos, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.currencylistelement,null);

            ImageView flag = (ImageView)view.findViewById(R.id.flag);
            TextView country = (TextView)view.findViewById(R.id.country);
            TextView currency = (TextView)view.findViewById(R.id.currency);

            flag.setImageResource(images[pos]);
            country.setText(countries[pos]);
            currency.setText(currencies[pos]);
            return view;
        }
    }
}
