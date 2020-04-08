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

import org.json.JSONException;
import org.json.JSONObject;

/*
       converting the rates
 */

public class Currency_Conversion extends AppCompatActivity {


    String rates; //to store the jason object rates
    double conRate; //to store the calculated eur rate
    JSONObject jsRateObj; //jason object
    String ToCurrency;  // the currency to which the conversion is to be performed. Fetched from the dropdown list
    double CAD_Rate, USD_Rate, AUD_Rate, GBP_Rate, INR_Rate, JPY_Rate, SGD_Rate; // the calculated eur rate converted to the rates of conversion of each currency
    String cfCurrency;  // the currency from which the conversion should be performed. Fetched from the intent passed from mainactivity(that is from the listview)

    double amountDouble =0.0;

    TextView convertfromCountry, conertfromCurrency;
    ImageView convertfromFlag;

    public static String fetData; // the variable that captures the values from the web api passed by the fetchData.java class


    //onclick function on convert button in R.layout.currency_dropdown
    public void convert (View view){

        EditText amountEntered= findViewById(R.id.amountToConvert);
        Spinner mySpinner = findViewById(R.id.spinner1);
        ToCurrency = mySpinner.getSelectedItem().toString();

        amountDouble= Double.parseDouble(amountEntered.getText().toString());

        // fetching the conversion values of each currency from the jason object get from the web api with euro as the base.
        JSONObject jsObj = null;
        try {
            jsObj = new JSONObject(fetData);
            rates = jsObj.getString("rates");
            jsRateObj = new JSONObject(rates);
            CAD_Rate = Double.valueOf(jsRateObj.getString("CAD"));
            USD_Rate = Double.valueOf(jsRateObj.getString("USD"));
            AUD_Rate = Double.valueOf(jsRateObj.getString("AUD"));
            GBP_Rate = Double.valueOf(jsRateObj.getString("GBP"));
            INR_Rate = Double.valueOf(jsRateObj.getString("INR"));
            JPY_Rate = Double.valueOf(jsRateObj.getString("JPY"));
            SGD_Rate = Double.valueOf(jsRateObj.getString("SGD"));
            Log.i("GBP_Rate", String.valueOf(GBP_Rate));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        double displayAmt = 0.0;

        //the entered value to be converted is changed to euro, since the web api only supports euro as the base
        switch(cfCurrency){
            case "USD":
                conRate = amountDouble/USD_Rate;
                break;
            case "AUD":
                conRate = amountDouble/AUD_Rate;
                break;
            case "GBP":
                conRate = amountDouble/GBP_Rate;
                break;
            case "CAD":
                conRate = amountDouble/CAD_Rate;
                break;
            case "INR":
                conRate = amountDouble/INR_Rate;
                break;
            case "JPY":
                conRate = amountDouble/JPY_Rate;
                break;
            case "SGD":
                conRate = amountDouble/SGD_Rate;
                break;
            default:
                conRate = amountDouble; //if nothing else then the cfCurrency is EUR, which need not be converted
        }

        Log.i("conRATe", String.valueOf(conRate));
        Log.i("ToCurrency", String.valueOf(ToCurrency));
        Log.i("amountDouble", String.valueOf(amountDouble));

        //using the converted euro rate(conRate) the entered amount to be converted is changed to the respective required currency
        switch(ToCurrency){
            case "USD":
                displayAmt = conRate*USD_Rate;
                break;
            case "AUD":
                displayAmt = conRate*AUD_Rate;
                break;
            case "GBP":
                displayAmt = conRate*GBP_Rate;
                break;
            case "CAD":
                displayAmt = conRate*CAD_Rate;
                break;
            case "INR":
                displayAmt = conRate*INR_Rate;
                break;
            case "JPY":
                displayAmt = conRate*JPY_Rate;
                break;
            case "SGD":
                displayAmt = conRate*SGD_Rate;
                break;
            default:
                displayAmt = conRate;
                break;
        }

        Toast.makeText(Currency_Conversion.this,String.valueOf(displayAmt),Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currency_dropdown);

        convertfromCountry = findViewById(R.id.convertFromCountry);
        conertfromCurrency = findViewById(R.id.convertFromCurrency);
        convertfromFlag = findViewById(R.id.convertFromFlag);

        Intent intent = getIntent();
        cfCurrency = intent.getStringExtra("currency");
        convertfromCountry.setText(intent.getStringExtra("country"));
        conertfromCurrency.setText(cfCurrency);
        convertfromFlag.setImageResource(intent.getIntExtra("flag",0));

        Spinner mySpinner = findViewById(R.id.spinner1);
        ArrayAdapter<String> myAdapter= new ArrayAdapter(Currency_Conversion.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.currencies));

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);
        fetchData fDataobj =  new fetchData();
        fDataobj.execute();

    }
}