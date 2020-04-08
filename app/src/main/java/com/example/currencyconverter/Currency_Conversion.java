package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Currency_Conversion extends AppCompatActivity {

    TextView convertfromCountry, convertfromCurrency;
    ImageView convertfromFlag;
    public void convert (View view){
        EditText amountEntered= findViewById(R.id.amountToConvert);
        Spinner mySpinner = findViewById(R.id.spinner1);
        String ToCurrency = mySpinner.getSelectedItem().toString();


        double displayAmt = 0.0;
        Log.i("Spinner", ToCurrency);

        double amountDouble= Double.parseDouble(amountEntered.getText().toString());

        switch(ToCurrency){
            case "EUR":
                displayAmt = amountDouble*0.92;
                break;
            case "AUD":
                displayAmt = amountDouble*1.67;
                break;
            case "GBP":
                displayAmt = amountDouble*.82;
                break;
            case "CAD":
                displayAmt = amountDouble*1.43;
                break;
            case "INR":
                displayAmt = amountDouble*77;
                break;
            case "JPY":
                displayAmt = amountDouble*108.64;
                break;
            case "SGD":
                displayAmt = amountDouble*1.44;
                break;
            default:
                displayAmt = amountDouble;
                break;
        }
        Toast.makeText(Currency_Conversion.this,String.valueOf(displayAmt),Toast.LENGTH_LONG).show();
        Log.i("amount", amountEntered.getText().toString());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currency_dropdown);


        convertfromCountry = findViewById(R.id.convertFromCountry);
        convertfromCurrency = findViewById(R.id.convertFromCurrency);
        convertfromFlag = findViewById(R.id.convertFromFlag);

        Intent intent = getIntent();
        convertfromCountry.setText(intent.getStringExtra("country"));
        convertfromCurrency.setText(intent.getStringExtra("currency"));
        convertfromFlag.setImageResource(intent.getIntExtra("flag",0));

        Spinner mySpinner = findViewById(R.id.spinner1);
        ArrayAdapter<String> myAdapter= new ArrayAdapter(Currency_Conversion.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.currencies));

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

    }
}