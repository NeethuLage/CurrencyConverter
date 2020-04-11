package com.example.currencyconverter;

public class Conversion  {
    public String fromCurrency,toCurrency;
    public double  EnteredAmount,ResultAmount;

    //constructor
    public Conversion(String fromCurrency, String toCurrency,  double EnteredAmount, double ResultAmount) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.EnteredAmount = EnteredAmount;
        this.ResultAmount = ResultAmount;

    }




}
