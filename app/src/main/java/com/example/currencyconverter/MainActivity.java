package com.example.currencyconverter;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



 /*
          fetching flag images, country and currency from the listview(in R.layout.currencylist) item that is clicked
          and passing it to the Currency_Conversion class
         */

public class MainActivity extends AppCompatActivity {
    ArrayList<String> list;
    ArrayAdapter<String> arrayAdapter;
    ListView listview;
    private DBManager dbManager;

    Toolbar toolbar; //for toolbar

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


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Currency Converter");

        //get data by the adapter
        listview = findViewById(R.id.listview);
        CustomAdapter adapter = new CustomAdapter();

        //Apply the adapter to the listview
        listview.setAdapter(adapter);
        list=new ArrayList<String>();

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,list);



        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter2View, View view, int position, long viewId) {

                //logging informational messages
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

    //adding the menubar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //menubar messages
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String msg = "";
        switch (item.getItemId()){
            case R.id.favorites:
                msg="Favorites";
                break;
            case R.id.history:
                msg = "History";
                break;
            case R.id.search:
                msg = "Search";
                break;
        }
        Toast.makeText(this, msg+" checked", Toast.LENGTH_LONG).show();
        return super.onOptionsItemSelected(item);
    }

    class CustomAdapter extends BaseAdapter{

        public CustomAdapter() {

        }

        public CustomAdapter(MainActivity mainActivity, int simple_list_item_multiple_choice, ArrayList list) {
        }


        //returns length or number of rows of image
        @Override
        public int getCount() {
            return images.length;
        }


        //returns the item for the position
        @Override
        public Object getItem(int position) {
            return null;
        }


        //get the id for the provided position
        @Override
        public long getItemId(int position) {
            return 0;
        }

        //fetching flag images, country and currency from the listview
        @Override
        public View getView(int pos, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.currencylistelement,null);

            //retreiving flag from resources
            ImageView flag = view1.findViewById(R.id.flag);

            //retreiving country from resources
            TextView country = view1.findViewById(R.id.country);

            //retreiving currency from resources
            TextView currency = view1.findViewById(R.id.currency);


            //setting the image resource and the text
            flag.setImageResource(images[pos]);
            country.setText(countries[pos]);
            currency.setText(currencies[pos]);

            //returning view1
            return view1;
        }
    }
}
