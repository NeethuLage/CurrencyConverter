package com.example.currencyconverter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemsAdapterFav extends ArrayAdapter<Favorites> {

    public  String HeadingText,FromText,ToText;



    public ItemsAdapterFav(Context context, ArrayList<Favorites> itemObj) {
        super(context, 0, itemObj);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Favorites ConversionItem = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.itemfav, parent, false);
        }
        // Lookup view for data population
        TextView FromCurrencyName = (TextView) convertView.findViewById(R.id.FromCurrencyName);
        TextView ToCurrencyName = (TextView) convertView.findViewById(R.id.ToCurrencyName);
        TextView Heading = (TextView) convertView.findViewById(R.id.HeadingText);

        //getting the values and inserting
        HeadingText = ConversionItem.fromCurrency +" => "+  ConversionItem.toCurrency;
        FromText = ConversionItem.fromCurrency;
        ToText = ConversionItem.toCurrency;

        // Populate the data into the template view using the data object
        FromCurrencyName.setText(FromText);
        ToCurrencyName.setText(ToText);
        Heading.setText(HeadingText);

        // Return the completed view to render on screen
        return convertView;
    }
}