package com.example.currencyconverter;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentResult extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_fragment_result, container, false);

        View view = inflater.inflate(R.layout.fragment_fragment_result, container, false);

        String FromCurrency = getArguments().getString("FromCurrencyKey");
        String ToCurrency = getArguments().getString("ToCurrencyKey");
        String EnterAmt= getArguments().getString("EnterAmtKey");
        String ResultAmt = getArguments().getString("ResultAmtKey");


        String FromCurrencyStr = "From Currency:"+FromCurrency;

        TextView output = (TextView) view.findViewById(R.id.textView1);
        output.setText(String.valueOf(EnterAmt));

        TextView output1 = (TextView) view.findViewById(R.id.textView2);
        output1.setText(FromCurrency);

        TextView output2 = (TextView) view.findViewById(R.id.textView3);
        output2.setText(ResultAmt);

        TextView output3 = (TextView) view.findViewById(R.id.textView4);
        output3.setText(ToCurrency);

        return view;

    }



}
