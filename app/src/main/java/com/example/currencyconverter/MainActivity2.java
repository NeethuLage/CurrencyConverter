package com.example.currencyconverter;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private ListView listview;

    int [] images = {R.drawable.euro, R.drawable.us, R.drawable.aus, R.drawable.bri, R.drawable.cad, R.drawable.ind,
            R.drawable.jap, R.drawable.sng};

    String [] countries = {"Europe", "U.S.A", "Australia", "Great Britain", "Canada", "India", "Japan", "Singapore"};
    String[] currencies = {"EURO", "USD", "AUD", "GBP", "CAD", "INR", "JPY", "SGD"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);


        listview = findViewById(R.id.listview);
        CustomAdapter adapter = new CustomAdapter();
        listview.setAdapter(adapter);
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
            view = getLayoutInflater().inflate(R.layout.currencylist,null);

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
